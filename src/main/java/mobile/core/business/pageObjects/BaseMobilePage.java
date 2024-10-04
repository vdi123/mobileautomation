package mobile.core.business.pageObjects;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import mobile.core.driver.DriverManager;
import mobile.core.entities.Element;
import mobile.core.interactions.ElementInteractions;
import mobile.core.waiters.BaseWaits;
import org.openqa.selenium.By;

import java.util.Set;

public class BaseMobilePage {

    protected AppiumDriver driver;
    protected DriverManager driverManager;
    protected ElementInteractions actions;
    protected BaseWaits baseWaits;

    public BaseMobilePage(DriverManager driverManager) {
        this.driver = driverManager.getMobileDriver();
        this.driverManager = driverManager;
        this.actions = new ElementInteractions(driverManager);
        this.baseWaits = new BaseWaits(driverManager);
    }

    private static final Element HOME_PAGE = new Element(
            "Webdriver text",
            AppiumBy.accessibilityId("Home"),
            By.name("N/A"));

    private static final Element WEBVIEW_PAGE = new Element(
            "Webdriver text",
            AppiumBy.accessibilityId("Webview"),
            By.name("N/A"));


    private static final Element LOGIN_PAGE = new Element(
            "Webdriver text",
            AppiumBy.accessibilityId("Login"),
            By.name("N/A"));

    private static final Element FORMS_PAGE = new Element(
            "Webdriver text",
            AppiumBy.accessibilityId("Forms"),
            By.name("N/A"));

    private static final Element SWIPE_PAGE = new Element(
            "Webdriver text",
            AppiumBy.accessibilityId("Swipe"),
            By.name("N/A"));

    private static final Element DRAG_PAGE = new Element(
            "Webdriver text",
            AppiumBy.accessibilityId("Drag"),
            By.name("N/A"));

    public void tapToHomePage() {
        actions.tap(HOME_PAGE);
    }

    public void tapToWebViewPage() {
        actions.tap(WEBVIEW_PAGE);
    }

    public void tapToLoginPage() {
        actions.tap(LOGIN_PAGE);
    }

    public void tapToFormsPage() {
        actions.tap(FORMS_PAGE);
    }

    public void tapToSwipePage() {
        actions.tap(SWIPE_PAGE);
    }

    public void tapToDragPage() {
        actions.tap(DRAG_PAGE);
    }

    public void switchToWebview() {
        Set<String> contexts = ((AndroidDriver) driver).getContextHandles();
        for (String contextName : contexts) {
            if (contextName.contains("WEBVIEW")) {
                ((AndroidDriver) driver).context(contextName);
                break;
            }
        }
    }

    public void switchToDefaultContent() {
        Set<String> contexts = ((AndroidDriver) driver).getContextHandles();
        for (String contextName : contexts) {
            if (contextName.contains("NATIVE_APP")) {
                ((AndroidDriver) driver).context(contextName);
                break;
            }
        }
    }

}
