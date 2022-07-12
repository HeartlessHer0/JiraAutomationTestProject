package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfileSettingsPage extends BasePage {

    private String profileSettingsPageURL = "https://id.atlassian.com/manage-profile/profile-and-visibility";

    private By profileSettingPageIdentifierLocator = By.xpath("//*[@data-test-selector='profile-hover-info']");

    public ProfileSettingsPage(WebDriver driver) {
        super(driver);
    }
    @Override
    protected By getPageIdentifier() {
        return profileSettingPageIdentifierLocator;
    }

    public String getProfileSettingsPageURL() {
        return profileSettingsPageURL;
    }

    public WebElement getProfileSettingPageIdentifierLocator() {
        return waitsService.waitForVisibilityLocatedBy(profileSettingPageIdentifierLocator);
    }
}
