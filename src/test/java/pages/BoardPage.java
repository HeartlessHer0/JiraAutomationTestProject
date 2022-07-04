package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoardPage extends BasePage {


    private String issueActionsButtonLocator = "//*[@aria-label='действие с задачей, 'Replace'']"; //подставлять после  "действие с задачей" название задачи
    private By currentBoardButtonLocator = By.xpath("//*[@aria-label='Breadcrumbs']//li[2]//span");
    private String createIssueButtonLocator = "//h2/div[contains(text(), 'Replace')]//ancestor::div[@data-test-id='platform-board-kit.ui.column.draggable-column.styled-wrapper']//button[@data-testid='platform-inline-card-create.ui.trigger.visible.button']"; // вместо Replace вписать группу в которой создать задачу (Сделать, В работе, Проверка, Готово)
    private By searchFieldLocator = By.xpath("//*[@aria-label='Поиск по доске']");
    private By cleanSearchFieldButtonLocator = By.xpath("//*[@aria-label='Очистить']");


    public BoardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return currentBoardButtonLocator;
    }

    public WebElement getIssueActionsButton(String path){return waitsService.waitForPresenceOfElement(By.xpath(issueActionsButtonLocator.replace("Replace",path)));}
}
