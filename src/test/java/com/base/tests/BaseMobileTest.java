package com.base.tests;

import lombok.extern.slf4j.Slf4j;
import mobile.core.driver.DriverManager;
import mobile.core.utils.CommandLineUtils;
import mobile.core.utils.Dictionary;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.URISyntaxException;

import static mobile.core.utils.CommandLineUtils.killNodeProcess;
import static mobile.core.utils.JsonUtils.getDeviceData;

@Slf4j
public class BaseMobileTest {

    public static DriverManager driverManager;

    @Parameters({Dictionary.PLATFORM_NAME, Dictionary.MOBILE_DEVICE})
    @BeforeSuite(description = "Before suite")
    public void beforeSuite(String platformName, String mobileDevice, ITestContext testContext) throws IOException, URISyntaxException {
        log.info("{} started", testContext.getName());
        killNodeProcess(); // To be sure that appium service stopped from previous session
        driverManager = new DriverManager(platformName, getDeviceData(mobileDevice));
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        driverManager.shutDown();
    }


}
