package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StartPage extends BasePage {

    //Блок описания селекторов для элементов
    private By profileButtonLocator = By.xpath("//*[@data-testid='nav__profile-menu-trigger']/span/span/div");
    private By jiraSoftwareButtonLocator = By.xpath("//*[contains (text(), 'Jira Software')]");
    private By jiraWorkManagementButtonLocator = By.xpath("//*[contains (text(), 'Jira Work Management')]");
    private By confluenceButtonLocator = By.xpath("//*[contains (text(), 'Confluence')]");
    private By trelloButtonLocator = By.xpath("//*[contains (text(), 'Trello')]");
    private By accountSettingButtonLocator = By.xpath("//*[contains (text(), 'Trello')]");
    private By projectButton = By.className("name");



    //Блок инициализации
    public StartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return jiraSoftwareButtonLocator;
    }

    //Блок атомарных методов
    public WebElement getProfileButton() {
        return waitsService.waitForVisibilityLocatedBy(profileButtonLocator);
    }

    public WebElement getJiraSoftwareButton() {
        return waitsService.waitForVisibilityLocatedBy(jiraSoftwareButtonLocator);    }

    public WebElement getJiraWorkManagementButton() {
        return waitsService.waitForVisibilityLocatedBy(jiraWorkManagementButtonLocator);    }

    public WebElement getConfluenceButton() {
        return waitsService.waitForVisibilityLocatedBy(confluenceButtonLocator);    }

    public WebElement getTrelloButton() {
        return waitsService.waitForVisibilityLocatedBy(trelloButtonLocator);    }

    public WebElement getAccountSettingButton() {
        return waitsService.waitForVisibilityLocatedBy(accountSettingButtonLocator);    }

    public WebElement getProjectButton() {
        return waitsService.waitForVisibilityLocatedBy(profileButtonLocator);    }
}
