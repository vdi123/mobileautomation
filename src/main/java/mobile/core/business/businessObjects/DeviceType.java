package mobile.core.business.businessObjects;

import com.google.gson.annotations.SerializedName;

public enum DeviceType {

    @SerializedName("real") REAL("real"),
    @SerializedName("fake") FAKE("fake");

    private String deviceType;

    DeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Override
    public String toString() {
        return deviceType;
    }
}
