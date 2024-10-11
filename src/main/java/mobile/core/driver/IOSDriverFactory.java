package mobile.core.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import mobile.core.business.businessobjects.DeviceData;

public class IOSDriverFactory implements DriverFactory {

    @Override
    public AppiumDriver createDriver(String mainPlatform, DeviceData deviceData, AppiumDriverLocalService service) {
        return null;
    }
}
