package com.baolong.obd.common.network;

import java.io.Serializable;

public class ResponseWrapper<T> implements Serializable {
    private static final long serialVersionUID = -6407750792350975175L;
    private int code;
    private T data;
    private String msg;
    private String error;

    public int getCode() {
        return this.code;
    }

    public T getData() {
        return (T) this.data;
    }

    public String getError() {
        return this.error;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setCode(int paramInt) {
        this.code = paramInt;
    }

    public void setData(T paramT) {
        this.data = paramT;
    }

    public void setError(String paramString) {
        this.error = paramString;
    }

    public void setMsg(String paramString) {
        this.msg = paramString;
    }

}
