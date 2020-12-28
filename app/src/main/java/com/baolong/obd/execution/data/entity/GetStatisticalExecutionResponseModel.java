package com.baolong.obd.execution.data.entity;

public class GetStatisticalExecutionResponseModel {
    private String alreadyprocessed;
    private String implementationrate;
    private boolean isNewRecord;
    private String waitprocessed;

    public String getAlreadyprocessed() {
        return this.alreadyprocessed;
    }

    public String getImplementationrate() {
        return this.implementationrate;
    }

    public String getWaitprocessed() {
        return this.waitprocessed;
    }

    public boolean isNewRecord() {
        return this.isNewRecord;
    }

    public void setAlreadyprocessed(String paramString) {
        this.alreadyprocessed = paramString;
    }

    public void setImplementationrate(String paramString) {
        this.implementationrate = paramString;
    }

    public void setNewRecord(boolean paramBoolean) {
        this.isNewRecord = paramBoolean;
    }

    public void setWaitprocessed(String paramString) {
        this.waitprocessed = paramString;
    }
}
