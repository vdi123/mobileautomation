package mobile.core.interactions;


import mobile.core.driver.DriverManager;
import mobile.core.entities.Element;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ElementInteractions {

    protected DriverManager driverManager;

    public ElementInteractions(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

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

    private void swipeVertical(WebElement webElement) {
        Point point = webElement.getRect().getPoint();
        int startX = point.getX() / 2;
        int startY = (int) (point.getY() * 0.20);
        int endY = (int) (point.getY() * 0.80);


        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driverManager.getMobileDriver().perform(Arrays.asList(swipe));
    }

    private void swipeHorizontal(WebElement webElement) {
        Point point = webElement.getRect().getPoint();
        int startX = point.getX() + (int) (webElement.getSize().getWidth() * 0.80);
        int endX = point.getX() + (int) (webElement.getSize().getWidth() * 0.20);
        int centerY = point.getY() + (webElement.getSize().getHeight() / 2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, centerY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, centerY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driverManager.getMobileDriver().perform(Arrays.asList(swipe));
    }

    public void scrollToElementAndTap(Element element) {
        JavascriptExecutor js = (JavascriptExecutor) driverManager.getMobileDriver();

        while (driverManager.getMobileDriver().findElements(element.getLocator()).isEmpty()
                || !driverManager.getMobileDriver().findElements(element.getLocator()).get(0).isDisplayed()) {
            Map<String, Object> params = new HashMap<>();
            params.put("left", 100);
            params.put("top", 100);
            params.put("width", 200);
            params.put("height", 200);
            params.put("direction", "down");
            params.put("percent", 3.0);
            js.executeScript("mobile: scrollGesture", params);
        }

        WebElement webElement = element.$(driverManager);
        tap(webElement);
    }

    public void dragAndDrop(Element source, Element target) {
        WebElement sourceElement = source.$(driverManager);
        WebElement targetElement = target.$(driverManager);

        int startX = sourceElement.getRect().getX() + sourceElement.getRect().getWidth() / 2;
        int startY = sourceElement.getRect().getY() + sourceElement.getRect().getHeight() / 2;
        int endX = targetElement.getRect().getX() + targetElement.getRect().getWidth() / 2;
        int endY = targetElement.getRect().getY() + targetElement.getRect().getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragAndDrop = new Sequence(finger, 0);

        dragAndDrop.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY)); // Начальная позиция
        dragAndDrop.addAction(finger.createPointerDown(PointerInput.Kind.TOUCH.ordinal()));
        dragAndDrop.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY)); // Перемещение к конечной позиции
        dragAndDrop.addAction(finger.createPointerUp(PointerInput.Kind.TOUCH.ordinal()));

        driverManager.getMobileDriver().perform(Arrays.asList(dragAndDrop));
    }

    private void setValue(WebElement webElement, String value) {
        webElement.clear();
        webElement.sendKeys(value);
    }

    public void setValue(Element element, String value) {
        WebElement webElement = element.$(driverManager);
        setValue(webElement, value);
    }

    public void tap(Element element) {
        WebElement webElement = element.$(driverManager);
        tap(webElement);
    }

    public String getAttribute(Element element, String attribute) {
        return element.$(driverManager).getAttribute(attribute);
    }

    public void swipeVertical(Element element) {
        WebElement webElement = element.$(driverManager);
        swipeVertical(webElement);
    }

    public void swipeHorizontal(Element element) {
        WebElement webElement = element.$(driverManager);
        swipeHorizontal(webElement);
    }

    public String getText(Element element) {
        return element.$(driverManager).getText();
    }
}
