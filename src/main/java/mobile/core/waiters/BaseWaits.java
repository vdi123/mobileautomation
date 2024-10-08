package mobile.core.waiters;

import mobile.core.driver.DriverManager;
import mobile.core.entities.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseWaits extends FluentWait<DriverManager> {

    protected DriverManager driverManager;

    public BaseWaits(DriverManager driverManager) {
        super(driverManager);
        this.driverManager = driverManager;
    }

    public void waitElementAppeared(Element element) {
        Wait<DriverManager> wait = new FluentWait<>(driverManager)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        wait.until(driverManager -> element.$(driverManager).isDisplayed() && element.$(driverManager).isEnabled());
    }

    public boolean waitForElementPresence(Element element) {
        WebDriverWait wait = new WebDriverWait(driverManager.getMobileDriver(), Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(element.getLocator()));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean waitForElementPresence(By locator) {
        WebDriverWait wait = new WebDriverWait(driverManager.getMobileDriver(), Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
