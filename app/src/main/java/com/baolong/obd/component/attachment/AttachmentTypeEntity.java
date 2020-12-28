package com.baolong.obd.component.attachment;

import java.util.List;

import android.os.Parcel;

import java.util.ArrayList;

import android.os.Parcelable;

public class AttachmentTypeEntity implements Parcelable {
    public static final Parcelable.Creator<AttachmentTypeEntity> CREATOR;
    private ArrayList<MyFile> files;
    private String id;
    private String name;
    private String type;

    static {
        CREATOR = (Parcelable.Creator) new Parcelable.Creator<AttachmentTypeEntity>() {
            public AttachmentTypeEntity createFromParcel(final Parcel parcel) {
                return new AttachmentTypeEntity(parcel);
            }

            public AttachmentTypeEntity[] newArray(final int n) {
                return new AttachmentTypeEntity[n];
            }
        };
    }

    public AttachmentTypeEntity() {
    }

    protected AttachmentTypeEntity(final Parcel parcel) {
        this.name = parcel.readString();
        this.type = parcel.readString();
        this.files = (ArrayList<MyFile>) parcel.createTypedArrayList((Parcelable.Creator) MyFile.CREATOR);
        this.id = parcel.readString();
    }

    public AttachmentTypeEntity(final String name, final String type) {
        this.name = name;
        this.type = type;
    }

    public int describeContents() {
        return 0;
    }

    public ArrayList<MyFile> getFiles() {
        return this.files;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public void setFiles(final ArrayList<MyFile> files) {
        this.files = files;
    }

    public AttachmentTypeEntity setId(final String id) {
        this.id = id;
        return this;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.name);
        parcel.writeString(this.type);
        parcel.writeTypedList((List) this.files);
        parcel.writeString(this.id);
    }
}
