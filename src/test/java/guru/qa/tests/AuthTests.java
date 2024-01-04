package guru.qa.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static guru.qa.tests.TestData.*;
import static io.qameta.allure.Allure.step;

public class AuthTests extends TestBase {

    @Test
    @Tag("positive")
    public void positiveAuthTest() {
        step("Open QA GURU Main Page", () -> {
            mainPage.openMainPage();
        });
        step("Click 'Personal account' button", () -> {
            mainPage.clickLoginButton();
        });
        step("Fill in authorization form and click 'Personal account' button once again", () -> {
            loginPage.fillInLoginForm(userEmail, userPassword);
            mainPage.clickLoginButton();
        });
        step("Check authorized user", () -> {
            personalAccountPage.checkUserGreeting(userGreetingText, authInfo)
                    .checkUserEmail(userEmail);
        });
    }

    @Test
    @Tag("negative")
    public void negativeAuthWithWrongPasswordTest() {
        step("Open QA GURU Main Page", () -> {
            mainPage.openMainPage();
        });
        step("Click 'Personal account' button", () -> {
            mainPage.clickLoginButton();
        });
        step("Fill in authorization form, check error message", () -> {
            loginPage.fillInLoginForm(userEmail, faker.internet().password())
                    .checkWrongPassMessage(wrongPasswordMessage);
        });
    }

    @Test
    @Tag("negative")
    public void negativeAuthWithMissingPasswordTest() {
        step("Open QA GURU Main Page", () -> {
            mainPage.openMainPage();
        });
        step("Click 'Personal account' button", () -> {
            mainPage.clickLoginButton();
        });
        step("Fill in authorization form, check error message", () -> {
            loginPage.fillInLoginForm(userEmail, null)
                    .checkMissingPassMessage(missingPasswordMessage);
        });
    }

    @Test
    @Tag("negative")
    public void negativeAuthWithMissingEmailTest() {
        step("Open QA GURU Main Page", () -> {
            mainPage.openMainPage();
        });
        step("Click 'Personal account' button", () -> {
            mainPage.clickLoginButton();
        });
        step("Fill in authorization form, check Login Form", () -> {
            loginPage.fillInLoginForm(null, userPassword)
                    .checkMissingEmailMessage(missingEmailMessage);
        });
    }
}
