package com.baolong.obd.my.data.entity;

import java.io.Serializable;

public class GetStationListAllResponseModel implements Serializable {
    private static final long serialVersionUID = 8633299996744734593L;
    private String ddjd;
    private String ddwd;
    private boolean isNewRecord;
    private String jzbh;
    private String jzlx;
    private String jzmc;
    private String jzzt;

    public String getDdjd() {
        return this.ddjd;
    }

    public String getDdwd() {
        return this.ddwd;
    }

    public String getJzbh() {
        return this.jzbh;
    }

    public String getJzlx() {
        return this.jzlx;
    }

    public String getJzmc() {
        return this.jzmc;
    }

    public String getJzzt() {
        return this.jzzt;
    }

    public boolean isNewRecord() {
        return this.isNewRecord;
    }

    public void setDdjd(String paramString) {
        this.ddjd = paramString;
    }

    public void setDdwd(String paramString) {
        this.ddwd = paramString;
    }

    public void setJzbh(String paramString) {
        this.jzbh = paramString;
    }

    public void setJzlx(String paramString) {
        this.jzlx = paramString;
    }

    public void setJzmc(String paramString) {
        this.jzmc = paramString;
    }

    public void setJzzt(String paramString) {
        this.jzzt = paramString;
    }

    public void setNewRecord(boolean paramBoolean) {
        this.isNewRecord = paramBoolean;
    }
}
