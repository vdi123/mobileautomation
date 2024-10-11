package mobile.core.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import mobile.core.business.businessobjects.DeviceData;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.appium.java_client.Setting.ALLOW_INVISIBLE_ELEMENTS;

public class AndroidDriverFactory implements DriverFactory {

    @Override
    public AppiumDriver createDriver(String mainPlatform, DeviceData deviceData, AppiumDriverLocalService service) {
        Map<String, Object> capabilities = new HashMap<>();
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
