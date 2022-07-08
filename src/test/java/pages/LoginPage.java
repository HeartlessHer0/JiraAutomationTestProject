package pages;


import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage extends BasePage {

    private String loginPageURL = "https://id.atlassian.com/login";
    //Блок описания селекторов для элементов
    private By emailInputLocator = By.id("username");
    private By pswInputLocator = By.id("password");
    private By logInSubmitButtonLocator = By.id("login-submit");
    private By passwordIsNullTextLocator = By.id("password-error");
    private By incorrectEmailOrPasswordTextLocator = By.id("login-error");

    //Блок инициализации
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return emailInputLocator;
    }

    //Блок атомарных методов


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
        return waitsService.waitForVisibilityLocatedBy(passwordIsNullTextLocator);
    }

}
