package com.baolong.obd.common.base;

import android.support.annotation.Keep;

@Keep
public abstract interface IApplicationDelegate {
    public abstract void onCreate();

    public abstract void onLowMemory();

    public abstract void onTerminate();

    public abstract void onTrimMemory(int paramInt);
}