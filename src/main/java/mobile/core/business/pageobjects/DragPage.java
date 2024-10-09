package mobile.core.business.pageobjects;

import io.appium.java_client.AppiumBy;
import mobile.core.driver.DriverManager;
import mobile.core.entities.Element;
import org.openqa.selenium.By;

public class DragPage extends BasePage {

    public DragPage(DriverManager driverManager) {
        super(driverManager);
    }

    private static final Element DRAG_AND_DROP_TEXT = new Element(
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Drag and Drop\")"),
            By.name("N/A"));

    private static final Element RETRY_BUTTON = new Element(
            AppiumBy.accessibilityId("button-Retry"),
            By.name("N/A"));

    private static final Element DRAG_L1_OPTION = new Element(
            AppiumBy.accessibilityId("drag-l1"),
            By.name("N/A"));

    private static final Element DROP_L1_OPTION = new Element(
            AppiumBy.accessibilityId("drop-l1"),
            By.name("N/A"));

    private static final Element DRAG_L2_OPTION = new Element(
            AppiumBy.accessibilityId("drag-l2"),
            By.name("N/A"));

    private static final Element DROP_L2_OPTION = new Element(
            AppiumBy.accessibilityId("drop-l2"),
            By.name("N/A"));

    private static final Element DRAG_L3_OPTION = new Element(
            AppiumBy.accessibilityId("drag-l3"),
            By.name("N/A"));

    private static final Element DROP_L3_OPTION = new Element(
            AppiumBy.accessibilityId("drop-l3"),
            By.name("N/A"));

    private static final Element DRAG_C1_OPTION = new Element(
            AppiumBy.accessibilityId("drag-c1"),
            By.name("N/A"));

    private static final Element DROP_C1_OPTION = new Element(
            AppiumBy.accessibilityId("drop-c1"),
            By.name("N/A"));

    private static final Element DRAG_C2_OPTION = new Element(
            AppiumBy.accessibilityId("drag-c2"),
            By.name("N/A"));

    private static final Element DROP_C2_OPTION = new Element(
            AppiumBy.accessibilityId("drop-c2"),
            By.name("N/A"));

    private static final Element DRAG_C3_OPTION = new Element(
            AppiumBy.accessibilityId("drag-c3"),
            By.name("N/A"));

    private static final Element DROP_C3_OPTION = new Element(
            AppiumBy.accessibilityId("drop-c3"),
            By.name("N/A"));

    private static final Element DRAG_R1_OPTION = new Element(
            AppiumBy.accessibilityId("drag-r1"),
            By.name("N/A"));

    private static final Element DROP_R1_OPTION = new Element(
            AppiumBy.accessibilityId("drop-r1"),
            By.name("N/A"));

    private static final Element DRAG_R2_OPTION = new Element(
            AppiumBy.accessibilityId("drag-r2"),
            By.name("N/A"));

    private static final Element DROP_R2_OPTION = new Element(
            AppiumBy.accessibilityId("drop-r2"),
            By.name("N/A"));

    private static final Element DRAG_R3_OPTION = new Element(
            AppiumBy.accessibilityId("drag-r3"),
            By.name("N/A"));

    private static final Element DROP_R3_OPTION = new Element(
            AppiumBy.accessibilityId("drop-r3"),
            By.name("N/A"));

    public void dragAndDropElements() {
        elementActions.dragAndDrop(DRAG_L1_OPTION, DROP_L1_OPTION);
        elementActions.dragAndDrop(DRAG_L2_OPTION, DROP_L2_OPTION);
        elementActions.dragAndDrop(DRAG_L3_OPTION, DROP_L3_OPTION);

        elementActions.dragAndDrop(DRAG_C1_OPTION, DROP_C1_OPTION);
        elementActions.dragAndDrop(DRAG_C2_OPTION, DROP_C2_OPTION);
        elementActions.dragAndDrop(DRAG_C3_OPTION, DROP_C3_OPTION);

        elementActions.dragAndDrop(DRAG_R1_OPTION, DROP_R1_OPTION);
        elementActions.dragAndDrop(DRAG_R2_OPTION, DROP_R2_OPTION);
        elementActions.dragAndDrop(DRAG_R3_OPTION, DROP_R3_OPTION);
    }

    public void waitPageAppears() {
        baseWaits.waitElementAppeared(DRAG_AND_DROP_TEXT);
    }

    public boolean isRetryButtonDisplayed() {
        return baseWaits.waitForElementPresence(RETRY_BUTTON);
    }

}
