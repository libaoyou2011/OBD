package com.baolong.obd.component.imagereview;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class RemarkImage implements Parcelable {
    private String imageId;  // 图片在服务器上对应的 id
    private String imageName;
    private String imageAddress;  //图片在服务器上对应的绝对路径 url
    private String creatorId;
    private long createTime;
    private String targetId;
    private String targetType; //图片在服务器上对应的相对文件夹
    private List<RemarkList> imageRemarkList;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.createTime);
        dest.writeString(this.creatorId);
        dest.writeString(this.imageAddress);
        dest.writeString(this.imageId);
        dest.writeString(this.imageName);
        dest.writeTypedList(this.imageRemarkList);
        dest.writeString(this.targetId);
        dest.writeString(this.targetType);
    }

    public RemarkImage() {
    }

    protected RemarkImage(Parcel in) {
        this.createTime = in.readLong();
        this.creatorId = in.readString();
        this.imageAddress = in.readString();
        this.imageId = in.readString();
        this.imageName = in.readString();
        this.imageRemarkList = in.createTypedArrayList(RemarkList.CREATOR);
        this.targetId = in.readString();
        this.targetType = in.readString();
    }

    public static final Creator<RemarkImage> CREATOR = new Creator<RemarkImage>() {
        @Override
        public RemarkImage createFromParcel(Parcel source) {
            return new RemarkImage(source);
        }

        @Override
        public RemarkImage[] newArray(int size) {
            return new RemarkImage[size];
        }
    };

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

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public List<RemarkList> getImageRemarkList() {
        return imageRemarkList;
    }

    public void setImageRemarkList(List<RemarkList> imageRemarkList) {
        this.imageRemarkList = imageRemarkList;
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

    public static Creator<RemarkImage> getCREATOR() {
        return CREATOR;
    }
}
