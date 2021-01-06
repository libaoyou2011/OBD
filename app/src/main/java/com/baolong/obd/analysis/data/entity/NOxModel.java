package com.baolong.obd.analysis.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class NOxModel implements Parcelable {

    /**
     * cjsj : 2020-12-17
     * noxjg : 151.00
     */

    private String cjsj;
    private String noxjg;

    protected NOxModel(Parcel in) {
        cjsj = in.readString();
        noxjg = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cjsj);
        dest.writeString(noxjg);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NOxModel> CREATOR = new Creator<NOxModel>() {
        @Override
        public NOxModel createFromParcel(Parcel in) {
            return new NOxModel(in);
        }

        @Override
        public NOxModel[] newArray(int size) {
            return new NOxModel[size];
        }
    };

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public String getNoxjg() {
        return noxjg;
    }

    public void setNoxjg(String noxjg) {
        this.noxjg = noxjg;
    }
}
