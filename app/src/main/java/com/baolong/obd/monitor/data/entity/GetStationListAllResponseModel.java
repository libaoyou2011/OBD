package com.baolong.obd.monitor.data.entity;


import java.io.Serializable;

public class GetStationListAllResponseModel implements Serializable {
    private static final long serialVersionUID = -2002267956821497187L;
    private String ddjd;
    private String ddwd;
    private boolean isNewRecord;
    private String jzbh;
    private String jzlx;
    private String jzmc;
    private String jzzt;


    public String getDdjd() {
        return ddjd;
    }

    public void setDdjd(String ddjd) {
        this.ddjd = ddjd;
    }

    public String getDdwd() {
        return ddwd;
    }

    public void setDdwd(String ddwd) {
        this.ddwd = ddwd;
    }

    public boolean isNewRecord() {
        return isNewRecord;
    }

    public void setNewRecord(boolean newRecord) {
        isNewRecord = newRecord;
    }

    public String getJzbh() {
        return jzbh;
    }

    public void setJzbh(String jzbh) {
        this.jzbh = jzbh;
    }

    public String getJzlx() {
        return jzlx;
    }

    public void setJzlx(String jzlx) {
        this.jzlx = jzlx;
    }

    public String getJzmc() {
        return jzmc;
    }

    public void setJzmc(String jzmc) {
        this.jzmc = jzmc;
    }

    public String getJzzt() {
        return jzzt;
    }

    public void setJzzt(String jzzt) {
        this.jzzt = jzzt;
    }
}
