package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static guru.qa.tests.TestData.schoolDescription;

public class MainPage {
    SelenideElement
            headerDescription = $("h1.title"),
            loginButton = $(".main-header__login");

    public void openMainPage() {
        open(baseUrl);
        headerDescription.shouldHave(text(schoolDescription));
    }

    public void clickLoginButton() {
        loginButton.click();
    }
}
