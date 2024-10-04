package mobile.core.business.pageObjects;

import mobile.core.driver.DriverManager;
import mobile.core.entities.Element;
import org.openqa.selenium.By;

public class HomePage extends BaseMobilePage {

    public HomePage(DriverManager driverManager) {
        super(driverManager);
    }

    private static final Element WEBDRIVER_IO_TEXT = new Element(
            "Webdriver text",
            By.xpath("//android.widget.TextView[@text=\"WEBDRIVER\"]"),
            By.name("N/A"));

    public void waitPageAppears() {
        baseWaits.waitElementAppeared(WEBDRIVER_IO_TEXT);
    }

}
