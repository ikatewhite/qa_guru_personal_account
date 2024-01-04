package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PersonalAccountPage {
    SelenideElement
            userGreeting = $(".logined-form"),
            userMenuButton = $(".menu-item-profile"),
            accountEmail = $(".row"),
            changePassButton = $(byText("Установить пароль")),
            newPassInputField = $(byName("ChangePasswordForm[password]")),
            newPassConfirmField = $(byName("ChangePasswordForm[confirmPassword]")),
            savePassButton = $(byName("save")),
            infoChangedMessage = $(".alert-success"),
            diffPasswordsMessage = $(".errorSummary"),
            firstName = $("#User_first_name"),
            lastName = $("#User_last_name"),
            userGender = $("select#User_gender"),
            userPictureUpload = $("input#User_profile_image"),
            userCountry = $("input#User_country"),
            userCity = $("input#User_city"),
            aboutMyselfField = $(byName("user_content")),
            saveAllInfoButton = $(".row.buttons [name='save']");

    public PersonalAccountPage checkUserGreeting(String greeting, String info) {
        userGreeting.shouldHave(text(greeting))
                .shouldHave(text(info));
        return this;
    }

    public void checkUserEmail(String email) {
        userMenuButton.click();
        userMenuButton.click();
        accountEmail.shouldHave(text(email));
    }

    public void clickChangePassButton() {
        changePassButton.click();
    }

    public PersonalAccountPage setNewPass(String pass, String confirmPass) {
        newPassInputField.setValue(pass);
        newPassConfirmField.setValue(confirmPass);
        return this;
    }

    public PersonalAccountPage clickSavePassButton() {
        savePassButton.click();
        return this;
    }

    public void checkInfoChangedMessage(String message) {
        infoChangedMessage.shouldHave(text(message));
    }

    public void checkPasswordsDifferMessage(String message) {
        diffPasswordsMessage.shouldHave(text(message));
    }

    public void setUserFullName(String name, String surname) {
        firstName.setValue(name);
        lastName.setValue(surname);
    }

    public void setUserGender(String value) {
        userGender.click();
        userGender.selectOptionByValue(value);
    }

    public void uploadPicture(String userPicture) {
        userPictureUpload.uploadFromClasspath(userPicture);
    }

    public PersonalAccountPage setCountry(String country) {
        userCountry.setValue(country);
        return this;
    }

    public void setCity(String city) {
        userCity.setValue(city);
    }

    public void fillInAboutMyselfField(String text) {
        aboutMyselfField.setValue(text);
    }

    public PersonalAccountPage clickSaveAllInfoButton() {
        saveAllInfoButton.click();
        return this;
    }
}
