package mobile.core.business.pageObjects;

import io.appium.java_client.AppiumBy;
import mobile.core.driver.DriverManager;
import mobile.core.entities.Element;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    public HomePage(DriverManager driverManager) {
        super(driverManager);
    }

    private static final Element WEBDRIVER_IO_TEXT = new Element(
            AppiumBy.androidUIAutomator("new UiSelector().text(\"WEBDRIVER\")"),
            By.name("N/A"));

    public void waitPageAppears() {
        baseWaits.waitElementAppeared(WEBDRIVER_IO_TEXT);
    }

}
