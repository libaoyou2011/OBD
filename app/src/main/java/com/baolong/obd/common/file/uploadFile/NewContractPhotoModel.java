package com.baolong.obd.common.file.uploadFile;

import android.os.Parcel;
import android.os.Parcelable;

public class NewContractPhotoModel implements Parcelable {
    private String fileName;
    private String fileUri;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fileName);
        dest.writeString(this.fileUri);
    }

    public NewContractPhotoModel() {
    }

    private NewContractPhotoModel(Parcel in) {
        this.fileName = in.readString();
        this.fileUri = in.readString();
    }

    public static final Creator<NewContractPhotoModel> CREATOR = new Creator<NewContractPhotoModel>() {
        @Override
        public NewContractPhotoModel createFromParcel(Parcel source) {
            return new NewContractPhotoModel(source);
        }

        @Override
        public NewContractPhotoModel[] newArray(int size) {
            return new NewContractPhotoModel[size];
        }
    };
}
