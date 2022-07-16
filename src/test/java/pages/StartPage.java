package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StartPage extends BasePage {

    private String startPageURL = "https://start.atlassian.com";

    private By profileButtonLocator = By.xpath("//*[@data-testid='nav__profile-menu-trigger']/span/span/div");
    private By jiraSoftwareButtonLocator = By.xpath("//*[contains (text(), 'Jira Software')]");
    private By accountSettingsButton = By.xpath("//*[contains(text(), 'Настройки аккаунта')]");
    private By quitButton = By.xpath("//*[contains(text(), 'Выйти')]");

    public StartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return jiraSoftwareButtonLocator;
    }

    public String getStartPageURL() {
        return startPageURL;
    }

    public WebElement getJiraSoftwareButton() {
        return waitsService.waitForVisibilityLocatedBy(jiraSoftwareButtonLocator);
    }

    public WebElement getProfileButton() {
        return waitsService.waitForVisibilityLocatedBy(profileButtonLocator);
    }

    public WebElement getAccountSettingsButton() {
        return waitsService.waitForVisibilityLocatedBy(accountSettingsButton);
    }

    public WebElement getQuitButton() {
        return waitsService.waitForVisibilityLocatedBy(quitButton);
    }
}
