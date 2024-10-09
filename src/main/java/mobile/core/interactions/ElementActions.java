package mobile.core.interactions;


import mobile.core.driver.DriverManager;
import mobile.core.entities.Element;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ElementActions {

    protected DriverManager driverManager;

    public ElementActions(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public void tap(Element element) {
        WebElement webElement = element.$(driverManager);
        tap(webElement);
    }

    //Can use just click(), but this sequence is more natural way to tap
    private void tap(WebElement webElement) {
        Point point = webElement.getRect().getPoint();
        int centerX = point.getX() + (webElement.getSize().getWidth() / 2);
        int centerY = point.getY() + (webElement.getSize().getHeight() / 2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(100)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driverManager.getMobileDriver().perform(Arrays.asList(tap));
    }

    public void scrollElementIntoView(Element element) {
        WebElement webElement = element.$(driverManager);
        scrollElementIntoView(webElement);
    }

    private void scrollElementIntoView(WebElement webElement) {
        RemoteWebElement remoteElement = (RemoteWebElement) webElement;
        driverManager.getMobileDriver().executeScript("gesture: scrollElementIntoView", Map.of("scrollableView", remoteElement.getId(),
                "strategy", "accessibility id",
                "selector", "WebdriverIO logo",
                "percentage", 50,
                "direction", "up",
                "maxCount", 3));
    }

    private void swipeUp(WebElement webElement) {
        RemoteWebElement remoteElement = (RemoteWebElement) webElement;
        driverManager.getMobileDriver()
                .executeScript("gesture: swipe",
                        Map.of("elementId", remoteElement.getId(),
                                "percentage", 50, "direction", "up"));
    }

    private void swipeDown(WebElement webElement) {
        RemoteWebElement remoteElement = (RemoteWebElement) webElement;
        driverManager.getMobileDriver()
                .executeScript("gesture: swipe",
                        Map.of("elementId", remoteElement.getId(),
                                "percentage", 50, "direction", "down"));
    }

    private void swipeLeft(WebElement webElement) {
        RemoteWebElement remoteElement = (RemoteWebElement) webElement;
        driverManager.getMobileDriver()
                .executeScript("gesture: swipe",
                        Map.of("elementId", remoteElement.getId(),
                                "percentage", 50, "direction", "left"));
    }

    private void swipeRight(WebElement webElement) {
        RemoteWebElement remoteElement = (RemoteWebElement) webElement;
        driverManager.getMobileDriver()
                .executeScript("gesture: swipe",
                        Map.of("elementId", remoteElement.getId(),
                                "percentage", 50, "direction", "right"));
    }

    public void scrollToElementAndTap(Element element) {
        JavascriptExecutor js = (JavascriptExecutor) driverManager.getMobileDriver();

        for (int i = 0; i < 5; i++) {
            if (!driverManager.getMobileDriver().findElements(element.getLocator()).isEmpty() &&
                    driverManager.getMobileDriver().findElements(element.getLocator()).get(0).isDisplayed()) {
                WebElement webElement = element.$(driverManager);
                tap(webElement);
                return;
            }


            Map<String, Object> params = new HashMap<>();
            params.put("left", 100);
            params.put("top", 100);
            params.put("width", 200);
            params.put("height", 200);
            params.put("direction", "down");
            params.put("percent", 3.0);
            js.executeScript("mobile: scrollGesture", params);
        }

        throw new NoSuchElementException("Element not found after 5 scroll attempts: " + element.getLocator());
    }

    public void dragAndDrop(Element source, Element target) {
        WebElement sourceElement = source.$(driverManager);
        WebElement targetElement = target.$(driverManager);

        dragAndDrop(sourceElement, targetElement);
    }

    private void dragAndDrop(WebElement source, WebElement target) {
        RemoteWebElement sourceElement = (RemoteWebElement) source;
        RemoteWebElement targetElement = (RemoteWebElement) target;
        driverManager.getMobileDriver()
                .executeScript("gesture: dragAndDrop",
                        Map.of("sourceId", sourceElement.getId(), "destinationId", targetElement.getId()));
    }

    private void setValue(WebElement webElement, String value) {
        webElement.clear();
        webElement.sendKeys(value);
    }

    public void setValue(Element element, String value) {
        WebElement webElement = element.$(driverManager);
        setValue(webElement, value);
    }

    public String getAttribute(Element element, String attribute) {
        return element.$(driverManager).getAttribute(attribute);
    }

    public void swipeUp(Element element) {
        WebElement webElement = element.$(driverManager);
        swipeUp(webElement);
    }

    public void swipeDown(Element element) {
        WebElement webElement = element.$(driverManager);
        swipeDown(webElement);
    }

    public void swipeLeft(Element element) {
        WebElement webElement = element.$(driverManager);
        swipeLeft(webElement);
    }

    public void swipeRight(Element element) {
        WebElement webElement = element.$(driverManager);
        swipeRight(webElement);
    }

    public String getText(Element element) {
        return element.$(driverManager).getText();
    }
}
