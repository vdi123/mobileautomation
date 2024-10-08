package mobile.core.business.pageObjects;

import io.appium.java_client.AppiumBy;
import mobile.core.business.businessObjects.User;
import mobile.core.driver.DriverManager;
import mobile.core.entities.Element;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    public LoginPage(DriverManager driverManager) {
        super(driverManager);
    }

    private static final Element LOGIN_BUTTON = new Element(
            AppiumBy.accessibilityId("button-LOGIN"),
            By.name("N/A"));

    private static final Element EMAIL_INPUT = new Element(
            AppiumBy.accessibilityId("input-email"),
            By.name("N/A"));

    private static final Element PASSWORD_INPUT = new Element(
            AppiumBy.accessibilityId("input-password"),
            By.name("N/A"));


    public void login(User user) {
        elementActions.setValue(EMAIL_INPUT, user.getUsername());
        elementActions.setValue(PASSWORD_INPUT, user.getPassword());
        elementActions.tap(LOGIN_BUTTON);
    }

    public void waitPageAppears() {
        baseWaits.waitElementAppeared(LOGIN_BUTTON);
    }
}
