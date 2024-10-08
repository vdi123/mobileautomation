package com.base.tests;

import io.qameta.allure.Description;
import mobile.core.business.businessObjects.User;
import mobile.core.business.pageObjects.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static mobile.core.utils.RandomStringGenerator.generateRandomString;
import static org.assertj.core.api.Assertions.assertThat;

public class DemoAppTest extends BaseMobileTest {

    private HomePage homePage;
    private SwipePage swipePage;
    private WebviewPage webviewPage;
    private LoginPage loginPage;
    private User user;
    private FormsPage formsPage;
    private DragPage dragPage;

    @BeforeClass
    public void beforeClass() {
        homePage = new HomePage(driverManager);
        swipePage = new SwipePage(driverManager);
        webviewPage = new WebviewPage(driverManager);
        loginPage = new LoginPage(driverManager);
        formsPage = new FormsPage(driverManager);
        dragPage = new DragPage(driverManager);

        user = User.createDefaultUser();
        homePage.waitPageAppears();
    }

    @Test(priority = 1)
    @Description("Verify home page")
    public void verifyHomePage() {
        homePage.tapToHomePage();
        homePage.waitPageAppears();
    }

    @Test(priority = 2)
    @Description("Verify web view page")
    public void verifyWebviewContent() {
        webviewPage.tapToWebViewPage();
        webviewPage.waitNativePageAppears();
        webviewPage.switchToWebview();

        assertThat(webviewPage.isWebViewContentDisplayed())
                .as("Webview content is not displayed")
                .isTrue();

        webviewPage.switchToDefaultContent();
    }

    @Test(priority = 3)
    @Description("Verify login page")
    public void verifyLoginPage() {
        loginPage.tapToLoginPage();
        loginPage.waitPageAppears();
        loginPage.login(user);

        assertThat(loginPage.isAlertPresent()).as("Alert is not present").isTrue();
        loginPage.closeAlert();
    }

    @Test(priority = 4)
    @Description("Verify forms page")
    public void verifyFormsPage() {
        formsPage.tapToFormsPage();
        formsPage.waitPageAppears();
        formsPage.fillForm(generateRandomString(10));

        assertThat(formsPage.isAlertPresent()).as("Alert is not present").isTrue();
        formsPage.closeAlert();
    }


    @Test(priority = 5)
    @Description("Verify swipe page")
    public void verifySwipePage() {
        swipePage.tapToSwipePage();
        swipePage.waitPageAppears();
        String cardText1 = swipePage.getCardText();
        swipePage.swipeCardHorizontal();
        String cardText2 = swipePage.getCardText();

        assertThat(cardText1)
                .as("Swipe didn't work correctly, after swipe the same card with the same text")
                .isNotEqualTo(cardText2);
    }

    @Test(priority = 6)
    @Description("Verify drag page")
    public void verifyDragPage() {
        dragPage.tapToDragPage();
        dragPage.waitPageAppears();
        dragPage.dragAndDropElements();

        assertThat(dragPage.isRetryButtonDisplayed())
                .as("Drag and drop proccess is not finished, retry button is not displayed").isTrue();
    }
}
