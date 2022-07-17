package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private String loginPageURL = "https://id.atlassian.com/login";

    private By emailInputLocator = By.id("username");
    private By pswInputLocator = By.id("password");
    private By logInSubmitButtonLocator = By.id("login-submit");
    private By passwordIsNullTextLocator = By.id("password-error");
    private By incorrectEmailOrPasswordTextLocator = By.id("login-error");
    private By signupSubmitButtonLocator = By.id("signup-submit");
    private By emailIsNullLocator = By.xpath("//div//*[contains(text(), 'Введите адрес электронной почты')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return emailInputLocator;
    }

    public String getLoginPageURL() {
        return loginPageURL;
    }

    public WebElement getEmailInput() {
        return waitsService.waitForVisibilityLocatedBy(emailInputLocator);
    }

    public WebElement getPswInput() {
        return waitsService.waitForVisibilityLocatedBy(pswInputLocator);
    }

    public WebElement getSubmitButton() {
        return waitsService.waitForVisibilityLocatedBy(logInSubmitButtonLocator);
    }

    public WebElement getPasswordIsNullElement() {
        return waitsService.waitForVisibilityLocatedBy(passwordIsNullTextLocator);
    }

    public WebElement getIncorrectPasswordOrEmailElement() {
        return waitsService.waitForVisibilityLocatedBy(incorrectEmailOrPasswordTextLocator);
    }

    public WebElement getSignupSubmitButton() {
        return waitsService.waitForVisibilityLocatedBy(signupSubmitButtonLocator);
    }

    public WebElement getEmailIsNull() {
        return waitsService.waitForVisibilityLocatedBy(emailIsNullLocator);
    }
}
