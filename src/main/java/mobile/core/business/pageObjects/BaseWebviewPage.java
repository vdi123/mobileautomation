package mobile.core.business.pageObjects;

import io.appium.java_client.AppiumDriver;
import mobile.core.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseWebviewPage extends BaseMobilePage {

    public BaseWebviewPage(DriverManager driverManager) {
        super(driverManager);
    }

    protected static boolean waitForElementPresence(AppiumDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
