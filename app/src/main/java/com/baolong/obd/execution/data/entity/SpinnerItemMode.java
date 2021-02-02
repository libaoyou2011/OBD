package com.baolong.obd.execution.data.entity;

import androidx.annotation.NonNull;

public class SpinnerItemMode {
    private String id = "";
    private String value = "";

    public SpinnerItemMode(String _ID, String _Value) {
        id = _ID;
        value = _Value;
    }

    public String GetID() {
        return id;
    }

    public String GetValue() {
        return value;
    }

    @NonNull
    @Override
    public String toString() {
        // 为什么要重写toString()呢？因为适配器在显示数据的时候，如果传入适配器的对象不是字符串的情况下，直接就使用对象.toString()
        // TODO Auto-generated method stub
        return value;
    }

}
