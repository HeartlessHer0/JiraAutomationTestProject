package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoardPage extends BasePage {

    private String boardPageURL;

    private String issueActionsButtonLocator = "//*[@aria-label='действие с задачей, 'Replace'']"; //подставлять после  "действие с задачей" название задачи
    private By actionDeleteIssueButtonLocator = By.xpath("//*[@role='menuitem']//span[contains( text(), 'Удалить')]");
    private By confirmDeleteIssueButtonLocator = By.xpath("//*[@role='dialog']//button//span[contains( text(), 'Удалить')]");
    private By currentBoardButtonLocator = By.xpath("//*[@aria-label='Breadcrumbs']//li[2]//span");
    private String createIssueButtonLocator = "//h2/div[contains(text(), 'Replace')]//ancestor::div[@data-test-id='platform-board-kit.ui.column.draggable-column.styled-wrapper']//button[@data-testid='platform-inline-card-create.ui.trigger.visible.button']"; // вместо Replace вписать группу в которой создать задачу (Сделать, В работе, Проверка, Готово)
    private By searchFieldLocator = By.xpath("//*[@aria-label='Поиск по доске']");
    private By cleanSearchFieldButtonLocator = By.xpath("//*[@aria-label='Очистить']");
    private By projectSettingButton = By.xpath("//*[@href='/jira/software/projects/TAD/settings']");
    private By closeOrOpenNavigationPanelButton = By.xpath("//*[@data-testid='ContextualNavigation-resize-button']");
    private By createColumnButtonLocator = By.xpath("//*[@aria-label='Создать столбец']");
    //private By createColumnNameFieldLocator не могу поймать локатор, разберемся при встрече
    private By issueNameFieldLocator = By.xpath("//*[@placeholder='Что нужно сделать?']");
    private String openIssueButtonLocator = "//*[@data-test-id='platform-card.ui.card.focus-container']//span[contains(text(),'Replace')]"; //Replace = issueName
    private By issueOptionsButtonLocator = By.xpath("//*[@role='presentation']//span[contains(text(),'Настроить')]");
    private By issueCommentFieldLocator = By.xpath("//*[@placeholder='Добавить комментарий...']");
    private By commentSaveButtonLocator = By.xpath("//*[@data-testid='comment-save-button']");
    private String commentLocator = "//*[@data-renderer-start-pos and contains(text(), 'Replace')]"; //Replace = comment
    private By closeIssueButton = By.xpath("//*[@aria-label='Закрыть']");



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

    public WebElement getIssueActionsButton(String issueName){return waitsService.waitForPresenceOfElement(By.xpath(issueActionsButtonLocator.replace("Replace",issueName)));}

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
        return waitsService.waitForVisibilityLocatedBy(projectSettingButton);
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

    public WebElement getCommentSaveButtonLocator() {
        return waitsService.waitForVisibilityLocatedBy(commentSaveButtonLocator);
    }

    public WebElement getCommentLocator(String comment) {
        return waitsService.waitForVisibilityLocatedBy(By.xpath(commentLocator.replace("Replace", comment)));
    }

    public WebElement getCloseIssueButton() {
        return waitsService.waitForVisibilityLocatedBy(closeIssueButton);
    }
}
