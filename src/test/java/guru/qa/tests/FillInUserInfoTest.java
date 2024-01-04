package guru.qa.tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static guru.qa.tests.TestData.*;
import static io.qameta.allure.Allure.step;

public class FillInUserInfoTest extends TestBase {

    @Test
    @Tag("positive")
    public void positiveFillInUserInfoTest() {
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
        step("Set user name & last name", () -> {
            personalAccountPage.setUserFullName(userName, userLastName);
        });
        step("Set user gender", () -> {
            personalAccountPage.setUserGender(faker.options().option("", "male", "female"));
        });
        step("Upload user avatar", () -> {
            personalAccountPage.uploadPicture(userAvatar);
        });
        step("Set user country & city", () -> {
            personalAccountPage.setCountry(faker.address().country())
                               .setCity(faker.address().city());
        });
        step("Fill in 'About myself' text field", () -> {
            personalAccountPage.fillInAboutMyselfField(selfInfo);
        });
        step("Click 'Save' button", () -> {
            personalAccountPage.clickSaveAllInfoButton()
                               .checkInfoChangedMessage(profileChangedMessage);
        });
    }
}
