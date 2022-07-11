package baseEntities;

import io.restassured.RestAssured;
import models.Project;
import org.openqa.selenium.WebDriver;

public class BaseCucumberTest {
    public static WebDriver driver;
    public static RestAssured restAssured;

    public static Project baseProject;
}
