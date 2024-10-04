package mobile.core.business.pageObjects;

import mobile.core.driver.DriverManager;
import mobile.core.entities.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebviewPage extends BaseMobilePage {

    public WebviewPage(DriverManager driverManager) {
        super(driverManager);
    }

    private static final Element WEB_VIEW_NATIVE_PAGE = new Element(
            "Web view native app page",
            By.id("android:id/content"),
            By.name("N/A"));


    private static final By HERO_SUBTITLE_WEBVIEW_CONTENT = By.xpath("//p[@class='hero__subtitle']");

    public void waitNativePageAppears() {
        baseWaits.waitElementAppeared(WEB_VIEW_NATIVE_PAGE);
    }

    public void verifyWebViewContentDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(HERO_SUBTITLE_WEBVIEW_CONTENT));
    }
}
