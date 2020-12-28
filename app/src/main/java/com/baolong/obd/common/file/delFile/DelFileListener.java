package com.baolong.obd.common.file.delFile;

public abstract interface DelFileListener {

    public abstract void onComplete();

    public abstract void onError(int paramInt, String paramString);

}