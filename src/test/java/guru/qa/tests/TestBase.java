package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import guru.qa.config.WebConfig;
import guru.qa.helpers.Attach;
import guru.qa.pages.LoginPage;
import guru.qa.pages.MainPage;
import guru.qa.pages.PersonalAccountPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@ExtendWith(TextReportExtension.class)
public class TestBase {

    static WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());

    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    PersonalAccountPage personalAccountPage = new PersonalAccountPage();

    Faker faker = new Faker();

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
        Configuration.pollingInterval = 400;
        Configuration.timeout = 4000;
        Configuration.baseUrl = config.baseUrl();
        Configuration.browser = config.browser();
        Configuration.browserSize = config.browserSize();
        if (config.isRemote()) {
            Configuration.remote = config.remoteUrl();
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void beforeEach() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
