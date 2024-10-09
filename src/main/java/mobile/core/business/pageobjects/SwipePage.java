package mobile.core.business.pageobjects;

import io.appium.java_client.AppiumBy;
import mobile.core.driver.DriverManager;
import mobile.core.entities.Element;
import mobile.core.utils.Sleeper;
import org.openqa.selenium.By;

public class SwipePage extends BasePage {

    public SwipePage(DriverManager driverManager) {
        super(driverManager);
    }

    private static final Element SWIPE_HORIZONTAL_TEXT = new Element(
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Swipe horizontal\")"),
            By.name("N/A"));

    private static final Element CARD_TO_SWIPE = new Element(
            AppiumBy.accessibilityId("card"),
            By.name("N/A"));

    private static final Element CARD_TEXT = new Element(
            By.xpath("//android.view.ViewGroup[@content-desc=\"slideTextContainer\"]//android.widget.TextView[@index='0']"),
            By.name("N/A"));

    public void swipeCardLeft() {
        elementActions.swipeLeft(CARD_TO_SWIPE);
        Sleeper.sleepTight(300);
    }

    public String getCardText() {
        return elementActions.getText(CARD_TEXT);
    }

    public void waitPageAppears() {
        baseWaits.waitElementAppeared(SWIPE_HORIZONTAL_TEXT);
    }
}
