package mobile.core.business.pageObjects;

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
            "Swipe Horizontal text",
            By.xpath("//android.widget.TextView[@text=\"Swipe horizontal\"]"),
            By.name("N/A"));

    private static final Element CARD_TO_SWIPE = new Element(
            "Card to swipe",
            AppiumBy.accessibilityId("card"),
            By.name("N/A"));

    private static final Element CARD_TEXT = new Element(
            "Card text",
            By.xpath("//android.view.ViewGroup[@content-desc=\"slideTextContainer\"]//android.widget.TextView[@index='0']"),
            By.name("N/A"));

    public void swipeCardHorizontal() {
        actions.swipeHorizontal(CARD_TO_SWIPE);
        Sleeper.sleepTight(300);
    }

    public String getCardText() {
        return actions.getText(CARD_TEXT);
    }

    public void waitPageAppears() {
        baseWaits.waitElementAppeared(SWIPE_HORIZONTAL_TEXT);
    }
}
