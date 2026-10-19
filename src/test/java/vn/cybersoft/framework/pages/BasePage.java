
package vn.cybersoft.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import vn.cybersoft.framework.config.ConfigReader;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;

        int timeout = ConfigReader.getInt("explicit.wait", 10);

        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(timeout));
    }

    protected WebElement waitVisible(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitClickable(By locator) {
        return wait.until(
                ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        waitClickable(locator).click();
    }

    protected void type(By locator, String value) {
        WebElement element = waitVisible(locator);
        element.clear();
        element.sendKeys(value);
    }

    protected String getText(By locator) {
        return waitVisible(locator).getText().trim();
    }

    protected boolean isVisible(By locator) {
        try {
            return waitVisible(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    protected void hover(By locator) {
        WebElement element = waitVisible(locator);
        new Actions(driver).moveToElement(element).perform();
    }

    protected void waitForUrlContains(String text) {
        wait.until(ExpectedConditions.urlContains(text));
    }

    protected void waitForPageTitleContains(String text) {
        wait.until(ExpectedConditions.titleContains(text));
    }

    protected boolean waitForText(By locator, String expectedText) {
        try {
            return wait.until(
                    ExpectedConditions.textToBePresentInElementLocated(
                            locator, expectedText));
        } catch (Exception e) {
            return false;
        }
    }
}