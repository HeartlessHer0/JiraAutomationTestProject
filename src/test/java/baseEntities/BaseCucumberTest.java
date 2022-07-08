package baseEntities;

import io.restassured.RestAssured;
import models.Board;
import org.openqa.selenium.WebDriver;

public class BaseCucumberTest {
    public static WebDriver driver;
    public static RestAssured restAssured;

    public static Board baseBoard;
}
