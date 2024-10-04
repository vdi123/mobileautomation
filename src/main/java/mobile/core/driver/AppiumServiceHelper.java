package mobile.core.driver;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import lombok.extern.slf4j.Slf4j;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@Slf4j
public class AppiumServiceHelper {

    public static AppiumDriverLocalService getLocalService(int port) {
        return getService(null, port);
    }

    public static AppiumDriverLocalService getService(String ip, int port) {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();

        if (isNotEmpty(ip)) {
            builder.withIPAddress(ip);
            log.info("Service will start with ip {}", ip);
        }
        builder.usingPort(port);

        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error:debug");

        AppiumDriverLocalService service = builder.build();

        if (isEmpty(ip)) {
            service.start();
            assertService(service);
        }
        log.info("Started with URL {} ", service.getUrl());

        return service;
    }

    private static void assertService(AppiumDriverLocalService service) {
        if (service == null || !service.isRunning()) {
            throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node wasn't started!");
        }
    }

}
