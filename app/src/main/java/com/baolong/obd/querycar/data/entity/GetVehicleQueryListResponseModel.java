package com.baolong.obd.querycar.data.entity;

public class GetVehicleQueryListResponseModel {
    private String hphm;
    private String hpysname;
    private String id;
    private boolean isNewRecord;
    private String queryTime;

    public String getHphm() {
        return this.hphm;
    }

    public String getHpysname() {
        return this.hpysname;
    }

    public String getId() {
        return this.id;
    }

    public String getQueryTime() {
        return this.queryTime;
    }

    public boolean isNewRecord() {
        return this.isNewRecord;
    }

    public void setHphm(final String hphm) {
        this.hphm = hphm;
    }

    public void setHpysname(final String hpysname) {
        this.hpysname = hpysname;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setNewRecord(final boolean isNewRecord) {
        this.isNewRecord = isNewRecord;
    }

    public void setQueryTime(final String queryTime) {
        this.queryTime = queryTime;
    }
}

