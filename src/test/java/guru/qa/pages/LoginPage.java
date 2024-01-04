package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    SelenideElement
            emailField = $(byName("email")),
            passwordField = $(byName("password")),
            logIn = $(".btn-success");


    public LoginPage fillInLoginForm(String email, String password) {
        emailField.setValue(email);
        passwordField.setValue(password);
        logIn.click();
        return this;
    }

    public void checkWrongPassMessage(String wrongPass) {
        logIn.shouldHave(text(wrongPass));
    }

    public void checkMissingPassMessage(String missingPass) {
        logIn.shouldHave(text(missingPass));
    }

    public void checkMissingEmailMessage(String missingEmail) {
        logIn.shouldHave(text(missingEmail));
    }
}
