package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import services.WaitsService;

public class TrashPage extends BasePage {

    private String trashPageURL = "https://tms-aqa18.atlassian.net/jira/settings/projects/trash/";

    private By trashPageIdentifierLocator = By.xpath("//h1[contains(text(),'Корзина')]");
    private String boardActionsButton = "//tr//*[contains (text(), 'Replace')]//ancestor::tr//*[@aria-label='Еще']";
    private By deleteFromTrashButton = By.xpath("//*[contains (text(), 'Удалить без возможности восстановления')]");
    private By restoreFromTrashButton = By.xpath("//*[contains (text(), 'Восстановить')]");
    private By confirmDeleteButton = By.xpath("//*[@data-test-id='project-permanent-delete-modal.ui.actions.delete-button-wrapper']");
    private By confirmRestoreButton = By.xpath("//*[@data-test-id='project-restore-modal.ui.actions.restore-button-wrapper']");
    private By deleteNotificationLocator = By.xpath("//*[contains(text(), 'Идет удаление проекта')]");

    public TrashPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return trashPageIdentifierLocator;
    }

    public String getTrashPageURL() {
        return trashPageURL;
    }

    public WebElement getTrashPageIdentifierLocator() {
        return waitsService.waitForVisibilityLocatedBy(trashPageIdentifierLocator);
    }

    public WebElement getBoardActionsButton(String projectName) {
        return waitsService.waitForVisibilityLocatedBy(By.xpath(boardActionsButton.replace("Replace", projectName)));
    }

    public WebElement getDeleteFromTrashButton() {
        return waitsService.waitForVisibilityLocatedBy(deleteFromTrashButton);
    }

    public WebElement getRestoreFromTrashButton() {
        return waitsService.waitForVisibilityLocatedBy(restoreFromTrashButton);
    }

    public WebElement getConfirmDeleteButton() {
        return waitsService.waitForVisibilityLocatedBy(confirmDeleteButton);
    }

    public WebElement getConfirmRestoreButton() {
        return waitsService.waitForVisibilityLocatedBy(confirmRestoreButton);
    }

    public WebElement getDeleteNotificationLocator() {
        return waitsService.waitForVisibilityLocatedBy(deleteNotificationLocator);
    }
}
