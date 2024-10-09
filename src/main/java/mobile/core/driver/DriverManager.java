package mobile.core.driver;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import lombok.Getter;
import mobile.core.business.businessObjects.DeviceData;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.appium.java_client.Setting.ALLOW_INVISIBLE_ELEMENTS;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static io.appium.java_client.remote.MobilePlatform.IOS;

public class DriverManager {

    protected static final int DEFAULT_COMMAND_TIMEOUT = 60000;

    @Getter
    public static String mainPlatform;
    private DeviceData deviceData;
    protected AppiumDriverLocalService service;
    protected Map<String, Object> capabilities = new HashMap<>();
    protected AppiumDriver driver;

    public DriverManager(String mainPlatform, DeviceData deviceData) {
        this.mainPlatform = mainPlatform;
        this.deviceData = deviceData;
    }

    public AppiumDriver getMobileDriver() {
        if (this.driver == null) {
            initLocalService(4723);
            switch (mainPlatform) {
                case ANDROID:
                    driver = initAndroidDriver();
                    break;
                case IOS:
                    driver = initIOSDriver();
                    break;
                default:
                    throw new RuntimeException("Unsupported OS");
            }
        }
        return driver;
    }

    protected void initLocalService(int port) {
        service = AppiumServiceHelper.getLocalService(port);
    }

    protected AppiumDriver initIOSDriver() {
        //TODO:
        return null;
    }

    public void shutDown() {
        if (service != null && service.isRunning()) {
            service.stop();
        }
    }

    protected AppiumDriver initAndroidDriver() {
        capabilities.put("appium:" + ALLOW_INVISIBLE_ELEMENTS.toString(), true);
        capabilities.put("appium:udid", deviceData.getUdid());
        capabilities.put("platformName", mainPlatform);
        capabilities.put("appium:newCommandTimeout", DEFAULT_COMMAND_TIMEOUT);
        capabilities.put("appium:automationName", "UiAutomator2");

        String chromedriverPath = new File(DriverManager.class.getClassLoader().getResource("chromedriver/chromedriver.exe")
                .getFile()).getAbsolutePath();
        String appPath = new File(DriverManager.class.getClassLoader().getResource(deviceData.getAppPath())
                .getFile()).getAbsolutePath();
        capabilities.put("appium:chromedriverExecutable", chromedriverPath);
        capabilities.put("appium:app", appPath);

        return new AndroidDriver(service.getUrl(), new DesiredCapabilities(capabilities));
    }


}
