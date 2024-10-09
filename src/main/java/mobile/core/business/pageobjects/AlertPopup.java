package mobile.core.business.pageobjects;

import io.appium.java_client.AppiumBy;
import mobile.core.driver.DriverManager;
import mobile.core.entities.Element;
import mobile.core.interactions.ElementActions;
import mobile.core.waiters.BaseWaits;
import org.openqa.selenium.By;

public class AlertPopup {

    protected DriverManager driverManager;
    protected BaseWaits baseWaits;
    protected ElementActions elementActions;

    public AlertPopup(DriverManager driverManager, BaseWaits baseWaits, ElementActions elementActions) {
        this.driverManager = driverManager;
        this.baseWaits = baseWaits;
        this.elementActions = elementActions;
    }

    private static final Element ALERT_TITLE = new Element(
            AppiumBy.id("android:id/alertTitle"),
            By.name("N/A"));

    private static final Element ALERT_MESSAGE = new Element(
            AppiumBy.id("android:id/message"),
            By.name("N/A"));

    private static final Element OK_BUTTON = new Element(
            AppiumBy.id("android:id/button1"),
            By.name("N/A"));

    public boolean isAlertPresent() {
        return baseWaits.waitForElementPresence(ALERT_TITLE);
    }

    public String getAlertTitle() {
        baseWaits.waitForElementPresence(ALERT_TITLE);
        return elementActions.getText(ALERT_TITLE);
    }

    public String getAlertMessage() {
        baseWaits.waitForElementPresence(ALERT_MESSAGE);
        return elementActions.getText(ALERT_MESSAGE);
    }

    public void clickOkButton() {
        baseWaits.waitForElementPresence(OK_BUTTON);
        elementActions.tap(OK_BUTTON);
    }
}
