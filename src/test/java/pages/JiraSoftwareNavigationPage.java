package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JiraSoftwareNavigationPage extends BasePage {

    private By appSwitcherButtonLocator = By.xpath("//*[@aria-label='Appswitcher Icon']");
    private By jiraSoftwareMainButtonLocator = By.xpath("//*[@href='/jira']");
    private By yourWorkButtonLocator = By.xpath("//button/span[contains (text(), 'Ваша работа')]");
    private By projectsButtonLocator = By.xpath("//button/span[contains (text(), 'Проекты')]");
    private By createButtonLocator = By.id("createGlobalItem");
    private By settingButtonLocator = By.xpath("//*[@aria-label='Настройки']");
    private By profileButtonLocator = By.xpath("//*[@data-test-id='ak-spotlight-target-profile-spotlight']");

    public WebElement getAppSwitcherButton() {
        return  waitsService.waitForVisibilityLocatedBy(appSwitcherButtonLocator);
    }

    public WebElement getJiraSoftwareMainButton() {
        return waitsService.waitForVisibilityLocatedBy(jiraSoftwareMainButtonLocator);
    }

    public WebElement getYourWorkButton() {
        return  waitsService.waitForVisibilityLocatedBy(yourWorkButtonLocator);
    }

    public WebElement getProjectsButton() {
        return  waitsService.waitForVisibilityLocatedBy(projectsButtonLocator);
    }

    public WebElement getCreateButton() {
        return waitsService.waitForVisibilityLocatedBy(createButtonLocator);
    }

    public WebElement getSettingButton() {
        return waitsService.waitForVisibilityLocatedBy(settingButtonLocator);
    }

    public WebElement getProfileButton() {
        return waitsService.waitForVisibilityLocatedBy(profileButtonLocator);
    }

    public JiraSoftwareNavigationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return jiraSoftwareMainButtonLocator;
    }
}
