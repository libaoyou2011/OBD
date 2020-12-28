package com.baolong.obd.component.attachment;

import android.os.Parcel;
import android.os.Parcelable;

public class MyFile implements Parcelable {
    public static final Parcelable.Creator<MyFile> CREATOR;
    public static final int FAIL = 0;
    public static final int LOADING = 2;
    public static final int PAUSE = 1;
    public static final int PLAY = 0;
    public static final int SUCCESS = 1;
    private long currentTime;
    private float length;
    private String localFile;
    private int playState;
    private int progress;
    private String remoteFile;
    private int state;
    private String time;

    static {
        CREATOR = (Parcelable.Creator) new Parcelable.Creator<MyFile>() {
            public MyFile createFromParcel(final Parcel parcel) {
                return new MyFile(parcel);
            }

            public MyFile[] newArray(final int n) {
                return new MyFile[n];
            }
        };
    }

    public MyFile() {
        this.state = -1;
        this.playState = -1;
    }

    protected MyFile(final Parcel parcel) {
        this.state = -1;
        this.playState = -1;
        this.localFile = parcel.readString();
        this.remoteFile = parcel.readString();
        this.state = parcel.readInt();
        this.progress = parcel.readInt();
        this.currentTime = parcel.readLong();
        this.playState = parcel.readInt();
        this.length = parcel.readFloat();
        this.time = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public long getCurrentTime() {
        return this.currentTime;
    }

    public float getLength() {
        return this.length;
    }

    public String getLocalFile() {
        return this.localFile;
    }

    public int getPlayState() {
        return this.playState;
    }

    public int getProgress() {
        return this.progress;
    }

    public String getRemoteFile() {
        return this.remoteFile;
    }

    public int getState() {
        return this.state;
    }

    public String getTime() {
        return this.time;
    }

    public void setCurrentTime(final long currentTime) {
        this.currentTime = currentTime;
    }

    public void setLength(final float length) {
        this.length = length;
    }

    public MyFile setLocalFile(final String localFile) {
        this.localFile = localFile;
        return this;
    }

    public void setPlayState(final int playState) {
        this.playState = playState;
    }

    public void setProgress(final int progress) {
        this.progress = progress;
    }

    public MyFile setRemoteFile(final String remoteFile) {
        this.remoteFile = remoteFile;
        return this;
    }

    public void setState(final int state) {
        this.state = state;
    }

    public void setTime(final String time) {
        this.time = time;
    }

    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.localFile);
        parcel.writeString(this.remoteFile);
        parcel.writeInt(this.state);
        parcel.writeInt(this.progress);
        parcel.writeLong(this.currentTime);
        parcel.writeInt(this.playState);
        parcel.writeFloat(this.length);
        parcel.writeString(this.time);
    }
}
