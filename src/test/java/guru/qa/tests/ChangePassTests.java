package guru.qa.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static guru.qa.tests.TestData.*;
import static io.qameta.allure.Allure.step;

public class ChangePassTests extends TestBase {

    @Test
    @Tag("positive")
    public void positiveChangePassTest() {
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
        step("Click 'Change password' button", () -> {
            personalAccountPage.clickChangePassButton();
        });
        step("Set new password value and check success message", () -> {
            personalAccountPage.setNewPass(userPassword, userPassword)
                    .clickSavePassButton()
                    .checkInfoChangedMessage(passwordChangedMessage);
        });
    }

    @Test
    @Tag("negative")
    public void negativeChangePassWithWrongConfirmationTest() {
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
        step("Click 'Change password' button", () -> {
            personalAccountPage.clickChangePassButton();
        });
        step("Set new password value and check error message", () -> {
            personalAccountPage.setNewPass(userPassword, faker.internet().password())
                    .clickSavePassButton()
                    .checkPasswordsDifferMessage(passwordsDifferMessage);
        });
    }
}
