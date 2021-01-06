package com.baolong.obd.analysis.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class JCJLModel implements Parcelable {
    /**
     * numArr : ["2020","16192","2019","0"]
     * month : 12
     * tbzz : 0
     */

    private String month;
    private String tbzz;
    private List<String> numArr;

    protected JCJLModel(Parcel in) {
        month = in.readString();
        tbzz = in.readString();
        numArr = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(month);
        dest.writeString(tbzz);
        dest.writeStringList(numArr);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<JCJLModel> CREATOR = new Creator<JCJLModel>() {
        @Override
        public JCJLModel createFromParcel(Parcel in) {
            return new JCJLModel(in);
        }

        @Override
        public JCJLModel[] newArray(int size) {
            return new JCJLModel[size];
        }
    };

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTbzz() {
        return tbzz;
    }

    public void setTbzz(String tbzz) {
        this.tbzz = tbzz;
    }

    public List<String> getNumArr() {
        return numArr;
    }

    public void setNumArr(List<String> numArr) {
        this.numArr = numArr;
    }


}
