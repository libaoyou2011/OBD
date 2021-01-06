package com.baolong.obd.analysis.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class CQJRModel implements Parcelable {
    /**
     * sszz : 亚美科技
     * num : 2
     */

    private String sszz;
    private int num;

    protected CQJRModel(Parcel in) {
        sszz = in.readString();
        num = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sszz);
        dest.writeInt(num);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CQJRModel> CREATOR = new Creator<CQJRModel>() {
        @Override
        public CQJRModel createFromParcel(Parcel in) {
            return new CQJRModel(in);
        }

        @Override
        public CQJRModel[] newArray(int size) {
            return new CQJRModel[size];
        }
    };

    public String getSszz() {
        return sszz;
    }

    public void setSszz(String sszz) {
        this.sszz = sszz;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
