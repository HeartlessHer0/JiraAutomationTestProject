package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainBoardPage extends BasePage {


    private String issueActionsButtonLocator = "//*[@aria-label='Replace']";
    private String currentBoardButton

    public MainBoardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return null;
    }

    public WebElement getIssueActionsButton(String path){return waitsService.waitForPresenceOfElement(By.xpath(issueActionsButtonLocator.replace("Replace",path)));}
}
