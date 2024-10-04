package mobile.core.driver;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppiumServiceHelper {

    public static AppiumDriverLocalService getLocalService(int port) {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.usingPort(port);

        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error:debug");

        AppiumDriverLocalService service = builder.build();
        service.start();
        assertService(service);

        log.info("Started with URL {} ", service.getUrl());
        return service;
    }

    private static void assertService(AppiumDriverLocalService service) {
        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node wasn't started!");
        }
    }

}
