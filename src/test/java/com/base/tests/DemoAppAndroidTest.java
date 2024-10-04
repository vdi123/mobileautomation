package com.base.tests;

import mobile.core.business.pageObjects.HomePage;
import mobile.core.business.pageObjects.SwipePage;
import mobile.core.business.pageObjects.WebviewPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DemoAppAndroidTest extends BaseMobileTest {

    private HomePage homePage;
    private SwipePage swipePage;
    private WebviewPage webviewPage;

    @BeforeClass
    public void beforeClass() {
        homePage = new HomePage(driverManager);
        swipePage = new SwipePage(driverManager);
        webviewPage = new WebviewPage(driverManager);
        homePage.waitPageAppears();
    }

    @Test(enabled = false)
    public void verifyHomePage() {
        homePage.tapToHomePage();
        homePage.waitPageAppears();
    }

    @Test
    public void verifyWebviewPage() {
        webviewPage.tapToWebViewPage();
        webviewPage.waitNativePageAppears();
        webviewPage.switchToWebview();
        webviewPage.verifyWebViewContentDisplayed();
        webviewPage.switchToDefaultContent();
    }


    @Test(enabled = false)
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
}
