package mobile.core.driver;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import lombok.Getter;
import mobile.core.business.businessObjects.DeviceData;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

import static io.appium.java_client.Setting.ALLOW_INVISIBLE_ELEMENTS;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static io.appium.java_client.remote.MobilePlatform.IOS;

public class DriverManager {

    @Getter
    private String mainPlatform;
    private DeviceData deviceData;
    protected AppiumDriverLocalService service;
    protected Map<String, Object> capabilities = new HashMap<>();
    protected AppiumDriver driver;

    public DriverManager(String mainPlatform, DeviceData deviceData) {
        this.mainPlatform = mainPlatform;
        this.deviceData = deviceData;
    }

    public AppiumDriver getMobileDriver() {
        initLocalService(4723);
        switch (mainPlatform) {
            case ANDROID:
                return initAndroidDriver();
            case IOS:
                return initIOSDriver();
            default:
                throw new RuntimeException("Unsupported OS");
        }
    }

    protected void initLocalService(int port) {
        service = AppiumServiceHelper.getLocalService(port);
    }

    protected AppiumDriver initIOSDriver() {
        //TODO
        return null;
    }

    public void shutDown() {
        if (service.isRunning()) {
            service.stop();
        }
    }

    protected AppiumDriver initAndroidDriver() {
        capabilities.put("appium:" + ALLOW_INVISIBLE_ELEMENTS.toString(), true);
        capabilities.put("appium:udid", deviceData.getUdid());
        capabilities.put("platformName", mainPlatform);
        capabilities.put("appium:automationName", "UiAutomator2");
        capabilities.put("appium:appPackage", "com.wdiodemoapp");
        capabilities.put("appium:appActivity", "com.wdiodemoapp.MainActivity");

        return new AndroidDriver(service.getUrl(), new DesiredCapabilities(capabilities));
    }


}
