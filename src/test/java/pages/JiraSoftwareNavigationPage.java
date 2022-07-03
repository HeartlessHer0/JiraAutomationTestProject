package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JiraSoftwareNavigationPage extends BasePage {

    private By appSwitcherButtonLocator = By.xpath("//*[@aria-label='Appswitcher Icon']");
    private By jiraSoftwareMainButtonLocator = By.xpath("//*[@href='/jira']");
    private By yourWorkButtonLocator = By.xpath("//button/span[contains (text(), 'Ваша работа')]");
    private By projectsButtonLocator = By.xpath("//button/span[contains (text(), 'Проекты')]");
    private By createButtonLocator = By.id("createGlobalItem");
    private By settingButtonLocator = By.xpath("//*[@aria-label='Настройки']");
    private By profileButtonLocator = By.xpath("//*[@data-test-id='ak-spotlight-target-profile-spotlight']");

    public JiraSoftwareNavigationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return jiraSoftwareMainButtonLocator;
    }
}
