package com.baolong.obd.common.update.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CheckVersionResponseModel implements Parcelable {
    private String downloadUrl;
    private String publishTime;
    private String updateContent;
    private String updateType;
    private String versionCode;
    private String versionName;
    private String versionSize;

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public String getPublishTime() {
        return this.publishTime;
    }

    public String getUpdateContent() {
        return this.updateContent;
    }

    public String getUpdateType() {
        return this.updateType;
    }

    public String getVersionCode() {
        return this.versionCode;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public String getVersionSize() {
        return this.versionSize;
    }

    public void setDownloadUrl(String paramString) {
        this.downloadUrl = paramString;
    }

    public void setPublishTime(String paramString) {
        this.publishTime = paramString;
    }

    public void setUpdateContent(String paramString) {
        this.updateContent = paramString;
    }

    public void setUpdateType(String paramString) {
        this.updateType = paramString;
    }

    public void setVersionCode(String paramString) {
        this.versionCode = paramString;
    }

    public void setVersionName(String paramString) {
        this.versionName = paramString;
    }

    public void setVersionSize(String paramString) {
        this.versionSize = paramString;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.downloadUrl);
        dest.writeString(this.publishTime);
        dest.writeString(this.updateContent);
        dest.writeString(this.updateType);
        dest.writeString(this.versionCode);
        dest.writeString(this.versionName);
        dest.writeString(this.versionSize);
    }

    public CheckVersionResponseModel() {
    }

    protected CheckVersionResponseModel(Parcel in) {
        this.downloadUrl = in.readString();
        this.publishTime = in.readString();
        this.updateContent = in.readString();
        this.updateType = in.readString();
        this.versionCode = in.readString();
        this.versionName = in.readString();
        this.versionSize = in.readString();
    }

    public static final Creator<CheckVersionResponseModel> CREATOR = new Creator<CheckVersionResponseModel>() {
        @Override
        public CheckVersionResponseModel createFromParcel(Parcel source) {
            return new CheckVersionResponseModel(source);
        }

        @Override
        public CheckVersionResponseModel[] newArray(int size) {
            return new CheckVersionResponseModel[size];
        }
    };
}
