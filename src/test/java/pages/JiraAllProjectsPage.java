package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JiraAllProjectsPage extends BasePage {

    private String jiraAllProjectsPageURL = "https://tms-aqa18.atlassian.net/jira/projects";

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
    private By goToTrashPageButton = By.xpath("//*[@href='/jira/settings/projects/trash']");

    //Locators for creating a Board
    private By addBoardButton = By.xpath("//*[@data-test-id='global-pages.directories.projects-directory-v2.create-projects-button.button.button']");
    private String boardTypeBoardButton = "//button[@aria-label='Replace']"; // Replace = {'Kanban' , 'Scrum', 'Отслеживание багов'}
    private By usePatternButton = By.xpath("//*[contains(text(),'Использовать шаблон')]");
    private By teamManagedButton = By.xpath("//*[@data-testid='project-template-select-v2.ui.layout.screens.project-types.footer.select-project-button-team-managed']");
    private By boardNameField = By.xpath("//*[@id='project-create.create-form.name-field.input']");
    private By boardKeyField = By.xpath("//*[@id='project-create.create-form.advanced-dropdown.key-field.input']");
    private By createBoardWithParametersButton = By.xpath("//*[@data-test-id='project-create.create-form.create-screen.submit-button']");
    private By titleTooShortMessage = By.xpath("//*[contains(text(),'Слишком короткое название')]");
    private By titleTooLongMessage = By.xpath("//*[contains(text(),'Слишком длинное название')]");
    private By needTitleMessage = By.xpath("//div[contains(text(),'Проект должен иметь имя')]");
    private By needKeyMessage = By.xpath("//div[contains(text(),'Проект должен иметь ключ')]");

    public JiraAllProjectsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return projectPageIdentifierLocator;
    }

    public String getJiraAllProjectsPageURL() {
        return jiraAllProjectsPageURL;
    }

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

    public WebElement getGoToTrashPageButton() {
        return waitsService.waitForVisibilityLocatedBy(goToTrashPageButton);
    }

    //Getters for creating a Board
    public WebElement getAddBoardButton() {
        return waitsService.waitForVisibilityLocatedBy(addBoardButton);
    }

    public WebElement getBoardTypeBoardButton(String boardType) {
        return waitsService.waitForVisibilityLocatedBy(By.xpath(boardTypeBoardButton.replace("Replace", boardType)));
    }

    public WebElement getUsePatternButton() {
        return waitsService.waitForVisibilityLocatedBy(usePatternButton);
    }

    public WebElement getTeamManagedButton() {
        return waitsService.waitForVisibilityLocatedBy(teamManagedButton);
    }

    public WebElement getBoardNameField() {
        return waitsService.waitForVisibilityLocatedBy(boardNameField);
    }

    public WebElement getBoardKeyField() {
        return waitsService.waitForVisibilityLocatedBy(boardKeyField);
    }

    public WebElement getCreateBoardWithParametersButton() {
        return waitsService.waitForClickableElement(createBoardWithParametersButton);
    }

    public WebElement getTitleTooShortMessage() {
        return waitsService.waitForVisibilityLocatedBy(titleTooShortMessage);
    }

    public WebElement getTitleTooLongMessage() {
        return waitsService.waitForVisibilityLocatedBy(titleTooLongMessage);
    }

    public WebElement getNeedTitleMessage() {
        return waitsService.waitForVisibilityLocatedBy(needTitleMessage);
    }

    public WebElement getNeedKeyMessage() {
        return waitsService.waitForVisibilityLocatedBy(needKeyMessage);
    }
}
