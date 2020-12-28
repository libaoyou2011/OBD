package com.baolong.obd.monitor.data.entity;

@Deprecated
public class GetTodayAmountModel {
    private boolean isNewRecord;
    private String testAmount;
    private String unqualifiedTimes;
    private String unqualifiedRate;

    public String getTestAmount() {
        return this.testAmount;
    }

    public String getUnqualifiedRate() {
        return this.unqualifiedRate;
    }

    public String getUnqualifiedTimes() {
        return this.unqualifiedTimes;
    }

    public boolean isNewRecord() {
        return this.isNewRecord;
    }

    public void setNewRecord(boolean paramBoolean) {
        this.isNewRecord = paramBoolean;
    }

    public void setTestAmount(String paramString) {
        this.testAmount = paramString;
    }

    public void setUnqualifiedRate(String paramString) {
        this.unqualifiedRate = paramString;
    }

    public void setUnqualifiedTimes(String paramString) {
        this.unqualifiedTimes = paramString;
    }
}
