package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JiraAllProjectsPage extends BasePage {

    private By projectPageIdentifierLocator = By.xpath("//h1[contains (text(), 'Проекты')]");
    private String projectButtonLocator = "//*[@aria-label='Сведения о проектах']//*[contains(text(), 'Replace')]";
    private String projectActionsButton = "//tr//*[contains (text(), 'Replace')]//ancestor::tr//*[@aria-label='Еще']";
    private By projectSettingButtonLocator = By.xpath("//tr//*[contains (text(), 'Настройки проекта')]");
    private By moveToTrashProjectButtonLocator = By.xpath("//tr//*[contains (text(), 'Переместить в корзину')]");
    private By searchFieldLocator = By.xpath("//*[@aria-describedby='search-description']");
    private By cleanSearchFieldButtonLocator = By.xpath("//*[@aria-label='Очистить'] ");
    private By confirmMoveToTrashProjectButtonLocator = By.xpath("//*[@data-test-id='project-soft-delete-modal.ui.modal-footer.move-to-trash-button-wrapper']");
    private By closeMoveToTrashButtonLocator = By.xpath("data-test-id='project-soft-delete-modal.ui.modal-footer.close-button-wrapper'");
    private By successMoveToTrashNotificationLocator = By.xpath("//*[@data-test-id='project-soft-delete-modal.ui.flags.moved-to-trash-success']");
    private By closeNotificationButton = By.xpath("//*[@data-testid='project-soft-delete-modal.ui.flags.moved-to-trash-success-dismiss']");

    public JiraAllProjectsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return projectPageIdentifierLocator;
    }

    // public WebElement getIssueActionsButton(String path){return waitsService.waitForPresenceOfElement(By.xpath(issueActionsButtonLocator.replace("Replace",path)));}


    public WebElement getProjectButton(String projectName) {
        return waitsService.waitForPresenceOfElement(By.xpath(projectButtonLocator.replace("Replace", projectName)));
    }

    public WebElement getProjectActionsButton(String projectName) {
        return waitsService.waitForPresenceOfElement(By.xpath(projectActionsButton.replace("Replace", projectName)));
    }

    public WebElement getProjectSettingButton() {
        return waitsService.waitForPresenceOfElement(projectSettingButtonLocator);
    }

    public WebElement getMoveToTrashProjectButton() {
        return waitsService.waitForPresenceOfElement(moveToTrashProjectButtonLocator);
    }

    public WebElement getSearchField() {
        return waitsService.waitForPresenceOfElement(searchFieldLocator);
    }

    public WebElement getCleanSearchFieldButton() {
        return waitsService.waitForPresenceOfElement(cleanSearchFieldButtonLocator);
    }

    public WebElement getConfirmMoveToTrashProjectButton() {
        return waitsService.waitForVisibilityLocatedBy(confirmMoveToTrashProjectButtonLocator);
    }

    public WebElement getCloseMoveToTrashButton() {
        return waitsService.waitForVisibilityLocatedBy(closeMoveToTrashButtonLocator);
    }

    public WebElement getSuccessMoveToTrashNotification() {
        return waitsService.waitForVisibilityLocatedBy(successMoveToTrashNotificationLocator);
    }

    public WebElement getCloseNotificationButton() {
        return waitsService.waitForVisibilityLocatedBy(closeNotificationButton);
    }
}
