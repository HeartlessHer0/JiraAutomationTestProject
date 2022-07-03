package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        return null;
    }
}
