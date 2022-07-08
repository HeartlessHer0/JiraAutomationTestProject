package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProjectSettingPage extends BasePage {

    private String projectSettingPageURL;

    private By projectSettingPageIdentifierLocator = By.xpath("//*[@aria-label='Breadcrumbs']//li[3]//span");
    private By projectNameFieldLocator = By.xpath("//*[@name='projectName']");
    private By projectKeyFieldLocator = By.xpath("//*[@name='projectKey']");
    private By saveChangesButton = By.xpath("//button[@type='submit']");
    private By droplistTriggerLocator = By.xpath("//*[@aria-label='Еще']");
    private By changesNotificationLocator = By.xpath("//*[@role='alert']");




    public ProjectSettingPage(WebDriver driver) {
        super(driver) ;
    }

    @Override
    protected By getPageIdentifier() {
        return projectSettingPageIdentifierLocator;
    }

    public String getProjectSettingPageURL(String projectKey) {
        projectSettingPageURL = "https://tms-aqa18.atlassian.net/jira/software/projects/" + projectKey + "/settings/details";
        return projectSettingPageURL;
    }

    public WebElement getProjectSettingPageIdentifierLocator() {
        return waitsService.waitForVisibilityLocatedBy(projectSettingPageIdentifierLocator);
    }

    public WebElement getProjectNameFieldLocator() {
        return waitsService.waitForVisibilityLocatedBy(projectNameFieldLocator);
    }

    public WebElement getProjectKeyFieldLocator() {
        return waitsService.waitForVisibilityLocatedBy(projectKeyFieldLocator);
    }

    public WebElement getSaveChangesButton() {
        return waitsService.waitForClickableElement(saveChangesButton);
    }

    public WebElement getDroplistTriggerLocator() {
        return waitsService.waitForClickableElement(droplistTriggerLocator);
    }

    public WebElement getChangesNotificationLocator() {
        return waitsService.waitForVisibilityLocatedBy(changesNotificationLocator);
    }
}
