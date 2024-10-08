package mobile.core.entities;

import lombok.Getter;
import mobile.core.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static io.appium.java_client.remote.MobilePlatform.IOS;

@Getter
public class Element {

    private By androidLocator;
    private By iosLocator;

    public Element(By androidLocator, By iosLocator) {
        this.androidLocator = androidLocator;
        this.iosLocator = iosLocator;
    }

    public WebElement $(DriverManager driverManager) {
        switch (DriverManager.getMainPlatform()) {
            case ANDROID:
                return findElementOnScreen(driverManager);
            case IOS:
                return findElementOnScreen(driverManager);
            default:
                throw new RuntimeException("Unknown Mobile Platform");
        }
    }

    public By getAndroidLocator() {
        return androidLocator;
    }

    public By getIosLocator() {
        return iosLocator;
    }

    public WebElement findElementOnScreen(DriverManager driverManager) {
        List<WebElement> elements = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            try {
                if (!(elements = findElementsInViewport(driverManager)).isEmpty()) {

                    if (elements.get(0).isDisplayed() && elements.get(0).isEnabled()) {
                        return elements.get(0);
                    }
                }
            } catch (StaleElementReferenceException e) {

            }
        }

        return elements.get(0);
    }

    public List<WebElement> findElementsInViewport(DriverManager driverManager) {
        return driverManager.getMobileDriver().findElements(getLocator())
                .stream()
                .filter(WebElement::isDisplayed)
                .toList();
    }


    public By getLocator() {
        switch (DriverManager.getMainPlatform()) {
            case ANDROID:
                return androidLocator;
            case IOS:
                return iosLocator;
            default:
                throw new RuntimeException("Unknown Mobile Platform");
        }
    }

    public String getLocatorAsString(DriverManager driverManager) {
        switch (driverManager.getMainPlatform()) {
            case ANDROID:
                return androidLocator.toString();
            case IOS:
                return iosLocator.toString();
            default:
                throw new RuntimeException("Unknown Mobile Platform");
        }
    }

    @Override
    public String toString() {
        String locatorString;
        switch (DriverManager.getMainPlatform()) {
            case ANDROID:
                locatorString = androidLocator.toString();
                break;
            case IOS:
                locatorString = iosLocator.toString();
                break;
            default:
                throw new RuntimeException("Unknown Mobile Platform");
        }
        return String.format(DriverManager.getMainPlatform() + " {%s}", locatorString);
    }
}
