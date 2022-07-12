package baseEntities;

import io.restassured.RestAssured;
import models.Board;
import org.openqa.selenium.WebDriver;
import pages.*;

public class BaseCucumberTest {
    public static WebDriver driver;
    public static RestAssured restAssured;

    public static Board baseBoard;

    public static LoginPage loginPage;
    public static StartPage startPage ;
    public static JiraAllProjectsPage jiraAllProjectsPage;
    public static JiraSoftwareNavigationPage jiraSoftwareNavigationPage;
    public static JiraWorkPage jiraWorkPage;
    public static BoardPage boardPage;
    public static LogoutPage logoutPage;
    public static ProjectSettingPage projectSettingPage;
    public static TrashPage trashPage;
    public static ProfileSettingsPage profileSettingsPage;



}
