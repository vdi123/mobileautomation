package mobile.core.business.pageObjects;

import io.appium.java_client.AppiumBy;
import mobile.core.driver.DriverManager;
import mobile.core.entities.Element;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    public LoginPage(DriverManager driverManager) {
        super(driverManager);
    }

    private static final Element LOGIN_BUTTON = new Element(
            "Login button",
            AppiumBy.accessibilityId("button-LOGIN"),
            By.name("N/A"));

    private static final Element EMAIL_INPUT = new Element(
            "Email input",
            AppiumBy.accessibilityId("input-email"),
            By.name("N/A"));

    private static final Element PASSWORD_INPUT = new Element(
            "Password input",
            AppiumBy.accessibilityId("input-password"),
            By.name("N/A"));


    public void login(String email, String password) {
        actions.setValue(EMAIL_INPUT, email);
        actions.setValue(PASSWORD_INPUT, email);
        actions.tap(LOGIN_BUTTON);
    }

    public void waitPageAppears() {
        baseWaits.waitElementAppeared(LOGIN_BUTTON);
    }
}
