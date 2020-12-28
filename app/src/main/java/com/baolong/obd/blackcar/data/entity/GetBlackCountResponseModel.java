package com.baolong.obd.blackcar.data.entity;

public class GetBlackCountResponseModel {
    private boolean isNewRecord;
    private String wshBlackCarCount;
    private String ysBlackCarCount;
    private String yshBlackCarCount;

    public String getWshBlackCarCount() {
        return this.wshBlackCarCount;
    }

    public String getYsBlackCarCount() {
        return this.ysBlackCarCount;
    }

    public String getYshBlackCarCount() {
        return this.yshBlackCarCount;
    }

    public boolean getIsNewRecord() {
        return this.isNewRecord;
    }

    public boolean isNewRecord() {
        return this.isNewRecord;
    }

    public void setNewRecord(boolean paramBoolean) {
        this.isNewRecord = paramBoolean;
    }

    public void setWshBlackCarCount(String paramString) {
        this.wshBlackCarCount = paramString;
    }

    public void setYsBlackCarCount(String paramString) {
        this.ysBlackCarCount = paramString;
    }

    public void setYshBlackCarCount(String paramString) {
        this.yshBlackCarCount = paramString;
    }
}
