package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JiraWorkPage extends BasePage {

    private String jiraWorkPageURL = "https://tms-aqa18.atlassian.net/jira/your-work";

    private By allProjectsButtonLocator = By.xpath("//*[contains (text(), 'Все проекты')]");
    private String mainProjectButtonLocator = "//*[contains (text(), 'Replace')]";
    private By summaryFieldLocator = By.xpath("//div/input[@id='summary-field']");
    private By descriptionFieldLocator = By.className("ak-editor-content-area");
    private By createIssueButtonLocator = By.xpath("//*[@data-testid='issue-create.common.ui.footer.create-button']");
    private By jiraCreateIssueAlertLocator = By.xpath("//*[@data-testid='jira-issue-create.modal.create-form.success-flag']");
    private By dismissAlertButtonLocator = By.xpath("//*[@aria-label='Dismiss']");

    public JiraWorkPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return allProjectsButtonLocator;
    }

    public String getJiraWorkPageURL() {
        return jiraWorkPageURL;
    }

    public WebElement getAllProjectsButton() {
        return waitsService.waitForVisibilityLocatedBy(allProjectsButtonLocator);
    }

    public WebElement getMainProjectButton(String projectName) {
        return waitsService.waitForVisibilityLocatedBy(By.xpath(mainProjectButtonLocator.replace("Replace", projectName)));
    }

    public WebElement getSummaryField() {
        return waitsService.waitForPresenceOfElement(summaryFieldLocator);
    }

    public WebElement getDescriptionField() {
        return waitsService.waitForPresenceOfElement(descriptionFieldLocator);
    }

    public WebElement getCreateIssueButton() {
        return waitsService.waitForPresenceOfElement(createIssueButtonLocator);
    }

    public WebElement getJiraCreateIssueAlert() {
        return waitsService.waitForVisibilityLocatedBy(jiraCreateIssueAlertLocator);
    }

    public WebElement getDismissAlertButton() {
        return waitsService.waitForClickableElement(dismissAlertButtonLocator);
    }
}
