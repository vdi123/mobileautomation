package mobile.core.business.businessObjects;

import lombok.Getter;

@Getter
public class DeviceData {

    private String udid;
    private DeviceType deviceType;
    private String mobileDeviceName;
    private String appPath;

    public DeviceData(String udid, DeviceType deviceType, String mobileDeviceName, String appPath) {
        this.udid = udid;
        this.deviceType = deviceType;
        this.mobileDeviceName = mobileDeviceName;
        this.appPath = appPath;
    }

    public boolean isFakeDevice() {
        return deviceType.equals(DeviceType.FAKE);
    }
}
