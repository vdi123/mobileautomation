package mobile.core.business.pageobjects;

import io.appium.java_client.AppiumBy;
import mobile.core.driver.DriverManager;
import mobile.core.entities.Element;
import org.openqa.selenium.By;

public class FormsPage extends BasePage {

    public FormsPage(DriverManager driverManager) {
        super(driverManager);
    }

    private static final Element FORM_COMPONENTS_TEXT = new Element(
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Form components\")"),
            By.name("N/A"));

    private static final Element INPUT_FILED = new Element(
            AppiumBy.accessibilityId("text-input"),
            By.name("N/A"));

    private static final Element SWITCH_OPTION = new Element(
            AppiumBy.accessibilityId("switch"),
            By.name("N/A"));

    private static final Element DROPDOWN = new Element(
            AppiumBy.accessibilityId("Dropdown"),
            By.name("N/A"));

    private static final Element DROPDOWN_OPTION_APPIUM = new Element(
            AppiumBy.androidUIAutomator("new UiSelector().text(\"Appium is awesome\")"),
            By.name("N/A"));

    private static final Element ACTIVATE_BUTTON = new Element(
            AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollIntoView(new UiSelector().text(\"Active\"))"),
            By.name("N/A"));


    public void setInputFiled(String text) {
        elementActions.setValue(INPUT_FILED, text);
    }

    public void turnOnSwitchOption() {
        if (!isSwitchOptionOn()) {
            elementActions.tap(SWITCH_OPTION);
        }
    }

    public boolean isSwitchOptionOn() {
        return Boolean.parseBoolean(elementActions.getAttribute(SWITCH_OPTION, "checked"));
    }

    public void chooseOptionFromDropdown(){
        elementActions.tap(DROPDOWN);
        baseWaits.waitElementAppeared(DROPDOWN_OPTION_APPIUM);
        elementActions.tap(DROPDOWN_OPTION_APPIUM);

    }

    public void tapActivateButton(){
        elementActions.scrollElementIntoView(ACTIVATE_BUTTON);
        elementActions.tap(ACTIVATE_BUTTON);

    }

    public void waitPageAppears() {
        baseWaits.waitElementAppeared(FORM_COMPONENTS_TEXT);
    }

    public void fillForm(String inputText){
        setInputFiled(inputText);
        turnOnSwitchOption();
        chooseOptionFromDropdown();
        tapActivateButton();
    }
}
