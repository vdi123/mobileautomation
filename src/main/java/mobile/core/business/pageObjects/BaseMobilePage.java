package mobile.core.business.pageObjects;


import io.appium.java_client.AppiumDriver;
import mobile.core.driver.DriverManager;

public class BaseMobilePage {

    protected final AppiumDriver driver;

    public BaseMobilePage(DriverManager driverManager) {
        this.driver = driverManager.getMobileDriver();
    }

}
