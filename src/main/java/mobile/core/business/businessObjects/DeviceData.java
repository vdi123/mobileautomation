package mobile.core.business.businessObjects;

import lombok.Getter;

@Getter
public class DeviceData {

    private String udid;
    private String appPath;

    public DeviceData(String udid, String appPath) {
        this.udid = udid;
        this.appPath = appPath;
    }
}
