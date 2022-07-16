package baseEntities;

import io.restassured.RestAssured;
import models.Issue;
import models.Project;
import org.openqa.selenium.WebDriver;
import pages.*;
import services.DataBaseService;
import tables.ProjectsTable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BaseCucumberTest {
    public static final String LOGIN = System.getProperty("login");
    public static final String PASSWORD = System.getProperty("password");
    public static final String TOKEN = System.getProperty("token");

    public static WebDriver driver;

    public static LoginPage loginPage;
    public static StartPage startPage;
    public static JiraAllProjectsPage jiraAllProjectsPage;
    public static JiraSoftwareNavigationPage jiraSoftwareNavigationPage;
    public static JiraWorkPage jiraWorkPage;
    public static BoardPage boardPage;
    public static LogoutPage logoutPage;
    public static ProjectSettingPage projectSettingPage;
    public static TrashPage trashPage;
    public static ProfileSettingsPage profileSettingsPage;

    public static RestAssured restAssured;

    public static Project baseProject;
    public static Issue baseIssue;

    public static ProjectsTable projectsTable;
    public static DataBaseService dataBaseService;
    public static ResultSet rs;

    public static ArrayList<String> boardNames;
    public static ArrayList<String> boardKeys;
}
