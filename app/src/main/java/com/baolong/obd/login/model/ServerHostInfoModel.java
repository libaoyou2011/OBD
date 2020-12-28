package com.baolong.obd.login.model;

import java.io.Serializable;

public class ServerHostInfoModel implements Serializable {
    private static final long serialVersionUID = -6604826011101007019L;

    private boolean IsCheck;
    private String addressCenterLat;
    private String addressCenterLng;
    private String cityName;
    private boolean enable;
    private String hostUrl;

    public String getAddressCenterLat() {
        return this.addressCenterLat;
    }

    public String getAddressCenterLng() {
        return this.addressCenterLng;
    }

    public String getCityName() {
        return this.cityName;
    }

    public String getHostUrl() {
        return this.hostUrl;
    }

    public boolean isCheck() {
        return this.IsCheck;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setAddressCenterLat(final String addressCenterLat) {
        this.addressCenterLat = addressCenterLat;
    }

    public void setAddressCenterLng(final String addressCenterLng) {
        this.addressCenterLng = addressCenterLng;
    }

    public void setCheck(final boolean isCheck) {
        this.IsCheck = isCheck;
    }

    public void setCityName(final String cityName) {
        this.cityName = cityName;
    }

    public void setEnable(final boolean enable) {
        this.enable = enable;
    }

    public void setHostUrl(final String hostUrl) {
        this.hostUrl = hostUrl;
    }

    @Override
    public String toString() {
        return "ServerHostInfoModel{" +
                "IsCheck=" + IsCheck +
                ", addressCenterLat='" + addressCenterLat + '\'' +
                ", addressCenterLng='" + addressCenterLng + '\'' +
                ", cityName='" + cityName + '\'' +
                ", enable=" + enable +
                ", hostUrl='" + hostUrl + '\'' +
                '}';
    }
}
