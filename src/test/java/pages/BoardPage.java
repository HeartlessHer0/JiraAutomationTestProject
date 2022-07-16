package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoardPage extends BasePage {

    private String boardPageURL;

    private String issueActionsButtonLocator = "//*[@aria-label='действия с задачей, Replace']"; //подставлять после "действие с задачей" название задачи
    private By actionDeleteIssueButtonLocator = By.xpath("//*[@role='menuitem']//span[contains( text(), 'Удалить')]");
    private By confirmDeleteIssueButtonLocator = By.xpath("//*[@role='dialog']//button//span[contains( text(), 'Удалить')]");
    private By currentBoardButtonLocator = By.xpath("//*[@aria-label='Breadcrumbs']//li[2]//span");
    private String createIssueButtonLocator = "//h2/div[contains(text(), 'Replace')]//ancestor::div[@data-test-id='platform-board-kit.ui.column.draggable-column.styled-wrapper']//button[@data-testid='platform-inline-card-create.ui.trigger.visible.button']"; // вместо Replace вписать группу в которой создать задачу (Сделать, В работе, Проверка, Готово)
    private By searchFieldLocator = By.xpath("//*[@aria-label='Поиск по доске']");
    private By cleanSearchFieldButtonLocator = By.xpath("//*[@aria-label='Очистить']");
    private By projectSettingButton = By.xpath("//*[@data-testid='navigation-apps-sidebar-common.ui.shortcuts.shortcuts-add-link']/following-sibling::a/span");
    private By closeOrOpenNavigationPanelButton = By.xpath("//*[@data-testid='ContextualNavigation-resize-button']");
    private By createColumnButtonLocator = By.xpath("//*[@aria-label='Создать столбец']");
    private String columnHeaderLocator = "//*[@data-test-id='platform-board-kit.common.ui.column-header.editable-title.column-title.column-title' and @aria-label='Replace']";
    //private By createColumnNameFieldLocator не могу поймать локатор, разберемся при встрече
    private By issueNameFieldLocator = By.xpath("//*[@placeholder='Что нужно сделать?']");
    private String openIssueButtonLocator = "//*[@data-test-id='platform-card.ui.card.focus-container']//span[contains(text(),'Replace')]"; //Replace = issueName
    private By issueOpenNameFieldLocator = By.xpath("//*[@data-test-id='issue.views.issue-base.foundation.summary.heading']");
    private By issueOpenDescriptionField = By.xpath("//*[contains(text(),'Добавить описание...')]");
    private By addAttachmentToIssueButton = By.xpath("//*[@aria-label='Прикрепить']");
    private By issueOptionsButtonLocator = By.xpath("//*[@role='presentation']//span[contains(text(),'Настроить')]");
    private By issueCommentFieldLocator = By.xpath("//*[@placeholder='Добавить комментарий...']");
    private By commentOrDescriptionSaveButtonLocator = By.xpath("//*[@data-testid='comment-save-button']");
    private By activatedCommentFieldLocator = By.xpath("//*[@aria-label='Main content area']");
    private String commentLocator = "//*[@data-renderer-start-pos and contains(text(), 'Replace')]"; //Replace = comment
    private By closeIssueButton = By.xpath("//*[@aria-label='Закрыть']");
    private By fileUploadingLocator = By.xpath("//*[@type='file']");
    private By imageLocator = By.xpath("//*[@data-testid='media-image']");
    private By imageActionsLocator = By.xpath("//*[@aria-label='more']");
    private By imageDeleteButton = By.xpath("//*[contains(text(), 'Удалить')]");
    private By confirmDeleteImageButton = By.xpath("//*[@data-testid='common.components.confirmation-modal.confirm-button']");

    public BoardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return currentBoardButtonLocator;
    }

    public String getBoardPageURL(String boardKey, String boardId) {
        boardPageURL = "https://tms-aqa18.atlassian.net/jira/software/projects/" + boardKey + "/boards/" + boardId;
        return boardPageURL;
    }

    public WebElement getIssueActionsButton(String issueName) {
        return waitsService.waitForVisibilityLocatedBy(By.xpath(issueActionsButtonLocator.replace("Replace", issueName)));
    }

    public WebElement getConfirmDeleteIssueButton() {
        return waitsService.waitForVisibilityLocatedBy(confirmDeleteIssueButtonLocator);
    }

    public WebElement getActionDeleteIssueButton() {
        return waitsService.waitForVisibilityLocatedBy(actionDeleteIssueButtonLocator);
    }

    public WebElement getCurrentBoardButton() {
        return waitsService.waitForVisibilityLocatedBy(currentBoardButtonLocator);
    }

    public WebElement getCreateIssueButton(String group) {
        return waitsService.waitForPresenceOfElement(By.xpath(createIssueButtonLocator.replace("Replace", group)));
    }

    public WebElement getSearchField() {
        return waitsService.waitForVisibilityLocatedBy(searchFieldLocator);
    }

    public WebElement getCleanSearchFieldButton() {
        return waitsService.waitForVisibilityLocatedBy(cleanSearchFieldButtonLocator);
    }

    public WebElement getProjectSettingButton() {
        return waitsService.waitForPresenceOfElement(projectSettingButton);
    }

    public WebElement getCloseOrOpenNavigationPanelButton() {
        return waitsService.waitForPresenceOfElement(closeOrOpenNavigationPanelButton);
    }

    public WebElement getCreateColumnButton() {
        return waitsService.waitForVisibilityLocatedBy(createColumnButtonLocator);
    }

    public WebElement getIssueNameFieldLocator() {
        return waitsService.waitForVisibilityLocatedBy(issueNameFieldLocator);
    }

    public WebElement getOpenIssueButtonLocator(String issueName) {
        return waitsService.waitForPresenceOfElement(By.xpath(openIssueButtonLocator.replace("Replace", issueName)));
    }

    public WebElement getIssueOptionsButtonLocator() {
        return waitsService.waitForVisibilityLocatedBy(issueOptionsButtonLocator);
    }

    public WebElement getIssueCommentFieldLocator() {
        return waitsService.waitForVisibilityLocatedBy(issueCommentFieldLocator);
    }

    public WebElement getCommentOrDescriptionSaveButtonLocator() {
        return waitsService.waitForVisibilityLocatedBy(commentOrDescriptionSaveButtonLocator);
    }

    public WebElement getCommentLocator(String comment) {
        return waitsService.waitForVisibilityLocatedBy(By.xpath(commentLocator.replace("Replace", comment)));
    }

    public WebElement getCloseIssueButton() {
        return waitsService.waitForVisibilityLocatedBy(closeIssueButton);
    }

    public WebElement getIssueOpenNameFieldLocator() {
        return waitsService.waitForVisibilityLocatedBy(issueOpenNameFieldLocator);
    }

    public WebElement getIssueOpenDescriptionField() {
        return waitsService.waitForVisibilityLocatedBy(issueOpenDescriptionField);
    }

    public WebElement getAddAttachmentToIssueButton() {
        return waitsService.waitForVisibilityLocatedBy(addAttachmentToIssueButton);
    }

    public WebElement getColumnHeaderLocator(String columnName) {
        return waitsService.waitForVisibilityLocatedBy(By.xpath(columnHeaderLocator.replace("Replace", columnName)));
    }

    public WebElement getFileUploadingLocator() {
        return waitsService.waitForPresenceOfElement(fileUploadingLocator);
    }

    public WebElement getImageLocator() {
        return waitsService.waitForVisibilityLocatedBy(imageLocator);
    }

    public By getImageInvisLocator() {
        return imageLocator;
    }

    public WebElement getImageActionsLocator() {
        return waitsService.waitForPresenceOfElement(imageActionsLocator);
    }

    public WebElement getImageDeleteButton() {
        return waitsService.waitForVisibilityLocatedBy(imageDeleteButton);
    }

    public WebElement getConfirmDeleteImageButton() {
        return waitsService.waitForVisibilityLocatedBy(confirmDeleteImageButton);
    }

    public String getCommentInvLocator(String comment) {
        commentLocator = "//*[@data-renderer-start-pos and contains(text(), '" + comment + "')]";
        return commentLocator;
    }

    public WebElement getActivatedCommentFieldLocator() {
        return waitsService.waitForVisibilityLocatedBy(activatedCommentFieldLocator);
    }

    public String getOpenIssueButtonInvLocator(String issueName) {
        openIssueButtonLocator = "//*[@data-test-id='platform-card.ui.card.focus-container']//span[contains(text(),'" + issueName + "')]";
        return openIssueButtonLocator;
    }
}
