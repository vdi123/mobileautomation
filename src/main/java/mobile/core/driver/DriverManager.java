package mobile.core.driver;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import lombok.Getter;
import mobile.core.business.businessobjects.DeviceData;

import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static io.appium.java_client.remote.MobilePlatform.IOS;

public class DriverManager {

    @Getter
    public static String mainPlatform;
    private DeviceData deviceData;
    protected AppiumDriverLocalService service;
    protected AppiumDriver driver;

    public DriverManager(String mainPlatform, DeviceData deviceData) {
        this.mainPlatform = mainPlatform;
        this.deviceData = deviceData;
    }

    public AppiumDriver getMobileDriver() {
        if (this.driver == null) {
            service = initLocalService(4723);
            DriverFactory driverFactory = createDriverFactory();
            driver = driverFactory.createDriver(mainPlatform, deviceData, service);
        }
        return driver;
    }

    private DriverFactory createDriverFactory() {
        switch (mainPlatform) {
            case ANDROID:
                return new AndroidDriverFactory();
            case IOS:
                return new IOSDriverFactory();
            default:
                throw new RuntimeException("Unsupported OS: " + mainPlatform);
        }
    }

    protected AppiumDriverLocalService initLocalService(int port) {
        return AppiumServiceHelper.getLocalService(port);
    }

    public void shutDown() {
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }
}
