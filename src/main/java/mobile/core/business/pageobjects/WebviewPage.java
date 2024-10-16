package mobile.core.business.pageobjects;

import mobile.core.driver.DriverManager;
import mobile.core.entities.Element;
import org.openqa.selenium.By;

public class WebviewPage extends BaseWebviewPage {

    public WebviewPage(DriverManager driverManager) {
        super(driverManager);
    }

    private static final Element WEB_VIEW_NATIVE_PAGE = new Element(
            By.id("android:id/content"),
            By.name("N/A"));


    private static final By HERO_SUBTITLE_WEBVIEW_CONTENT = By.xpath("//p[@class='hero__subtitle']");

    public void waitNativePageAppears() {
        baseWaits.waitElementAppeared(WEB_VIEW_NATIVE_PAGE);
    }

    public boolean isWebViewContentDisplayed() {
        return baseWaits.waitForElementPresence(HERO_SUBTITLE_WEBVIEW_CONTENT);
    }
}
