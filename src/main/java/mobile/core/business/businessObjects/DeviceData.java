package mobile.core.business.businessObjects;

import lombok.Getter;

@Getter
public class DeviceData {

    private String udid;
    private DeviceType deviceType;
    private String mobileDeviceName;

    public DeviceData(String udid, DeviceType deviceType, String mobileDeviceName) {
        this.udid = udid;
        this.deviceType = deviceType;
        this.mobileDeviceName = mobileDeviceName;
    }

    public boolean isFakeDevice() {
        return deviceType.equals(DeviceType.FAKE);
    }
}
