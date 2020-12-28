package com.baolong.obd.component.imagereview;

import android.os.Parcel;
import android.os.Parcelable;

public class RemarkList implements Parcelable {
    private String imageId;
    private String creatorId;
    private long createTime;
    private String targetId;
    private String targetType;
    private String remarkId;
    private String remarkContent;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getRemarkContent() {
        return remarkContent;
    }

    public void setRemarkContent(String remarkContent) {
        this.remarkContent = remarkContent;
    }

    public String getRemarkId() {
        return remarkId;
    }

    public void setRemarkId(String remarkId) {
        this.remarkId = remarkId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.createTime);
        dest.writeString(this.creatorId);
        dest.writeString(this.imageId);
        dest.writeString(this.remarkContent);
        dest.writeString(this.remarkId);
        dest.writeString(this.targetId);
        dest.writeString(this.targetType);
    }

    public RemarkList() {
    }

    protected RemarkList(Parcel in) {
        this.createTime = in.readLong();
        this.creatorId = in.readString();
        this.imageId = in.readString();
        this.remarkContent = in.readString();
        this.remarkId = in.readString();
        this.targetId = in.readString();
        this.targetType = in.readString();
    }

    public static final Creator<RemarkList> CREATOR = new Creator<RemarkList>() {
        @Override
        public RemarkList createFromParcel(Parcel source) {
            return new RemarkList(source);
        }

        @Override
        public RemarkList[] newArray(int size) {
            return new RemarkList[size];
        }
    };
}

