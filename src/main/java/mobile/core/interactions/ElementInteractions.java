package mobile.core.interactions;


import mobile.core.driver.DriverManager;
import mobile.core.entities.Element;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;

public class ElementInteractions {

    protected DriverManager driverManager;

    public ElementInteractions(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public void tap(WebElement webElement) {
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

    public void swipeVertical(WebElement webElement) {
        Point point = webElement.getRect().getPoint();
        int startX = point.getX() / 2; // Середина экрана по ширине
        int startY = (int) (point.getY() * 0.20);  // Начало на 20% от высоты экрана
        int endY = (int) (point.getY() * 0.80);    // Конец на 80% от высоты экрана


        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driverManager.getMobileDriver().perform(Arrays.asList(swipe));
    }

    public void swipeHorizontal(WebElement webElement) {
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

    public void tap(Element element) {
        WebElement webElement = element.$(driverManager);
        tap(webElement);
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
