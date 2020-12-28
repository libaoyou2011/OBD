package com.baolong.obd.common.network;

import java.util.List;

public class ResponseWrapperListOld<T> {
    private int code;
    private List<T> data;
    private String msg;
    private String error;

    public int getCode() {
        return this.code;
    }

    public List<T> getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getError() {
        return this.error;
    }

    public void setCode(int paramInt) {
        this.code = paramInt;
    }

    public void setData(List<T> paramList) {
        this.data = paramList;
    }

    public void setError(String paramString) {
        this.error = paramString;
    }

    public void setMsg(String paramString) {
        this.msg = paramString;
    }
}
