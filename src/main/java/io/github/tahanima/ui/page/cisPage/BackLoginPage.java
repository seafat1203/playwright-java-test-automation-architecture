package io.github.tahanima.ui.page.cisPage;

import com.microsoft.playwright.Locator;
import io.github.tahanima.factory.BasePageFactory;
import io.github.tahanima.ui.page.demoPage.BasePage;
import io.qameta.allure.Step;

import static io.github.tahanima.config.ConfigurationManager.config;

/**
 * @author tahanima
 */
public final class BackLoginPage extends BasePage {

    private final String loginField = "//*[@id='login'] | //*[@id='username']";
    private final String passwordField = "id=password";
    private final String submitBtn = "id=kc-login";


    @Step("Navigate to GRAMS login page")
    public BackLoginPage open() {
        page.navigate(config().gramsUrl());
        return this;
    }

    @Step("Type <username> into 'Username' textbox")
    public BackLoginPage typeLogin(final String login) {
        page.fill(loginField, login);
        return this;
    }

    @Step("Type <password> into 'Password' textbox")
    public BackLoginPage typePassword(final String password) {
        page.fill(passwordField, password);
        return this;
    }

    @Step("Error message")
    public Locator errorMessage() {
        return page.locator("#input-error");
    }

    @Step("Click on the 'Login' button")
    public RiskModulePage clickOnLoginBtn() {
        page.click(submitBtn);
        return BasePageFactory.createInstance(page, RiskModulePage.class);
    }

    @Step("Click on the 'Login' button")
    public BackLoginPage clickOnLoginBtnButFail() {
        page.click(submitBtn);
        return this;
    }


}
