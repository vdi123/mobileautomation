package mobile.core.business.pageObjects;

import io.appium.java_client.AppiumBy;
import mobile.core.driver.DriverManager;
import mobile.core.entities.Element;
import mobile.core.interactions.ElementInteractions;
import mobile.core.waiters.BaseWaits;
import org.openqa.selenium.By;

public class AlertPopup {

    protected DriverManager driverManager;
    protected BaseWaits baseWaits;
    protected ElementInteractions actions;

    public AlertPopup(DriverManager driverManager, BaseWaits baseWaits, ElementInteractions actions) {
        this.driverManager = driverManager;
        this.baseWaits = baseWaits;
        this.actions = actions;
    }

    private static final Element ALERT_TITLE = new Element(
            "Alert Title",
            AppiumBy.id("android:id/alertTitle"),
            By.name("N/A"));

    private static final Element ALERT_MESSAGE = new Element(
            "Alert Message",
            AppiumBy.id("android:id/message"),
            By.name("N/A"));

    private static final Element OK_BUTTON = new Element(
            "OK Button",
            AppiumBy.id("android:id/button1"),
            By.name("N/A"));

    public boolean isAlertPresent() {
        return baseWaits.waitForElementPresence(ALERT_TITLE);
    }

    public String getAlertTitle() {
        baseWaits.waitForElementPresence(ALERT_TITLE);
        return actions.getText(ALERT_TITLE);
    }

    public String getAlertMessage() {
        baseWaits.waitForElementPresence(ALERT_MESSAGE);
        return actions.getText(ALERT_MESSAGE);
    }

    public void clickOkButton() {
        baseWaits.waitForElementPresence(OK_BUTTON);
        actions.tap(OK_BUTTON);
    }
}
