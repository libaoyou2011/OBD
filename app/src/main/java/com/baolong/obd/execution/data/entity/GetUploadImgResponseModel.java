package com.baolong.obd.execution.data.entity;

public class GetUploadImgResponseModel {
    private String fileClientName;
    private String fileClientPath;
    private String fileId;
    private String filePath;
    private boolean isNewRecord;

    public String getFileClientName() {
        return this.fileClientName;
    }

    public String getFileClientPath() {
        return this.fileClientPath;
    }

    public String getFileId() {
        return this.fileId;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public boolean isNewRecord() {
        return this.isNewRecord;
    }

    public void setFileClientName(String paramString) {
        this.fileClientName = paramString;
    }

    public void setFileClientPath(String paramString) {
        this.fileClientPath = paramString;
    }

    public void setFileId(String paramString) {
        this.fileId = paramString;
    }

    public void setFilePath(String paramString) {
        this.filePath = paramString;
    }

    public void setNewRecord(boolean paramBoolean) {
        this.isNewRecord = paramBoolean;
    }
}
