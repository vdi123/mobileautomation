package mobile.core.driver.factory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import mobile.core.business.businessobjects.DeviceData;

public interface DriverFactory {

    int DEFAULT_COMMAND_TIMEOUT = 60000;

    AppiumDriver createDriver(String mainPlatform, DeviceData deviceData, AppiumDriverLocalService service);
}
