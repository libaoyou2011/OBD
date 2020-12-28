package com.baolong.obd.my.data.entity;

import java.io.Serializable;

public class GetDefaultStationResponseModel implements Serializable {
    private static final long serialVersionUID = 8633299996744734594L;
    private boolean isNewRecord;
    private String jzbh;
    private String jzmc;

    public String getJzbh() {
        return this.jzbh;
    }

    public String getJzmc() {
        return this.jzmc;
    }

    public boolean isNewRecord() {
        return this.isNewRecord;
    }

    public void setJzbh(String paramString) {
        this.jzbh = paramString;
    }

    public void setJzmc(String paramString) {
        this.jzmc = paramString;
    }

    public void setNewRecord(boolean paramBoolean) {
        this.isNewRecord = paramBoolean;
    }
}

