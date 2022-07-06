package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogoutPage extends BasePage {

    private String logoutPAgeURL = "https://id.atlassian.com/logout";
    private By logoutButtonLocator = By.id("logout-submit");


    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return logoutButtonLocator;
    }

    public String getLogoutPAgeURL() {
        return logoutPAgeURL;
    }

    public WebElement getLogoutButton() {
        return waitsService.waitForVisibilityLocatedBy(logoutButtonLocator);
    }
}
