package wrappers;

import org.openqa.selenium.*;
import services.WaitsService;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UIElement implements WebElement {
    private WebDriver driver;
    private By by;
    private WaitsService waits;
    private WebElement webElement;

    public UIElement(WebDriver driver, By by) {
        this.driver = driver;
        this.by = by;
        this.waits = new WaitsService(driver, Duration.ofSeconds(20));
        this.webElement = driver.findElement(by);
    }

    public UIElement(WebDriver driver, WebElement webElement) {
        this.driver = driver;
        this.waits = new WaitsService(driver, Duration.ofSeconds(20));
        this.webElement = webElement;
    }

    @Override
    public void click() {
        try {
            webElement.click();
        } catch (ElementNotInteractableException ex) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", webElement);
            webElement.click();
        }
    }

    @Override
    public void submit() {
        webElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", webElement);
        webElement.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        webElement.clear();
    }

    @Override
    public String getTagName() {
        return webElement.getTagName();
    }

    @Override
    public String getDomProperty(String name) {
        return webElement.getDomProperty(name);
    }

    @Override
    public String getDomAttribute(String name) {
        return webElement.getDomAttribute(name);
    }

    @Override
    public String getAttribute(String name) {
        return webElement.getAttribute(name);
    }

    @Override
    public String getAriaRole() {
        return webElement.getAriaRole();
    }

    @Override
    public String getAccessibleName() {
        return webElement.getAccessibleName();
    }

    @Override
    public boolean isSelected() {
        return webElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return webElement.isEnabled();
    }

    @Override
    public String getText() {
        return webElement.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return webElement.findElements(by);
    }

    @Override
    public UIElement findElement(By by) {
        return new UIElement(driver, webElement.findElement(by));
    }

    @Override
    public SearchContext getShadowRoot() {
        return WebElement.super.getShadowRoot();
    }

    @Override
    public boolean isDisplayed() {
        return webElement.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return webElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        return webElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        return webElement.getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return webElement.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return webElement.getScreenshotAs(target);
    }

    public ArrayList<UIElement> findUIElements(By by) {
        ArrayList<UIElement> list = new ArrayList<>();
        for (WebElement element : webElement.findElements(by)) {
            list.add(new UIElement(driver, element));
        }
        return list;
    }
}
