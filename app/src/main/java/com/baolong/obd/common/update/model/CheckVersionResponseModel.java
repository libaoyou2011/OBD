package com.baolong.obd.common.update.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CheckVersionResponseModel implements Parcelable {

    /**
     * searchValue : null
     * createBy : null
     * createTime : null
     * updateBy : null
     * updateTime : 2021-05-06
     * remark : null
     * dataScope : null
     * params : {}
     * id : 1
     * versionCode : 2
     * versionName : 1.0.2
     * platform : Android
     * category : 车主版
     * updateContent : 1.修复部分bug
     * publishStatus : null
     * publishTime : 2021-05-06
     * fileApp : http://10.10.10.142:81/dev-api/profile/1.0.2.apk
     * fileSize : null
     */

    private Object searchValue;
    private Object createBy;
    private Object createTime;
    private Object updateBy;
    private String updateTime;
    private Object remark;
    private Object dataScope;
    private ParamsBean params;
    private int id;
    private int versionCode;
    private String versionName;
    private String platform;
    private String category;
    private String updateContent;
    private Object publishStatus;
    private String publishTime;
    private String fileApp;
    private Object fileSize;


    public CheckVersionResponseModel(Parcel in) {
        updateTime = in.readString();
        id = in.readInt();
        versionCode = in.readInt();
        versionName = in.readString();
        platform = in.readString();
        category = in.readString();
        updateContent = in.readString();
        publishTime = in.readString();
        fileApp = in.readString();
    }

    public static final Creator<CheckVersionResponseModel> CREATOR = new Creator<CheckVersionResponseModel>() {
        @Override
        public CheckVersionResponseModel createFromParcel(Parcel in) {
            return new CheckVersionResponseModel(in);
        }

        @Override
        public CheckVersionResponseModel[] newArray(int size) {
            return new CheckVersionResponseModel[size];
        }
    };

    public Object getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(Object searchValue) {
        this.searchValue = searchValue;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public Object getDataScope() {
        return dataScope;
    }

    public void setDataScope(Object dataScope) {
        this.dataScope = dataScope;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }

    public Object getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Object publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getFileApp() {
        return fileApp;
    }

    public void setFileApp(String fileApp) {
        this.fileApp = fileApp;
    }

    public Object getFileSize() {
        return fileSize;
    }

    public void setFileSize(Object fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(updateTime);
        dest.writeInt(id);
        dest.writeInt(versionCode);
        dest.writeString(versionName);
        dest.writeString(platform);
        dest.writeString(category);
        dest.writeString(updateContent);
        dest.writeString(publishTime);
        dest.writeString(fileApp);
    }

    @Override
    public String toString() {
        return "CheckVersionResponseModel{" +
                "searchValue=" + searchValue +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", updateTime='" + updateTime + '\'' +
                ", remark=" + remark +
                ", dataScope=" + dataScope +
                ", params=" + params +
                ", id=" + id +
                ", versionCode=" + versionCode +
                ", versionName='" + versionName + '\'' +
                ", platform='" + platform + '\'' +
                ", category='" + category + '\'' +
                ", updateContent='" + updateContent + '\'' +
                ", publishStatus=" + publishStatus +
                ", publishTime='" + publishTime + '\'' +
                ", fileApp='" + fileApp + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }

    public static class ParamsBean {
    }
}
