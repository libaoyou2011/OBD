package com.baolong.obd.common.injector;

import com.baolong.obd.common.injector.module.ApplicationModule;
import com.baolong.obd.common.injector.component.DaggerApplicationComponent;

import android.content.Context;

import com.baolong.obd.common.injector.component.ApplicationComponent;
import com.baolong.obd.common.injector.module.NetworkModule;

public class GlobalAppComponent {
    // 单例的有效范围是整个Application
    private static volatile ApplicationComponent mAppComponent;  // 注意是静态

    // 对外提供ApplicationComponent
    public static ApplicationComponent getAppliationComponent() {
        if (GlobalAppComponent.mAppComponent == null) {
            throw new NullPointerException("GlobalAppComponent必须在application中进行init初始化");
        }
        return GlobalAppComponent.mAppComponent;
    }

    public static void init(final Context context) {
        if (GlobalAppComponent.mAppComponent == null) {
            synchronized (GlobalAppComponent.class) {
                if (GlobalAppComponent.mAppComponent == null) {
                    GlobalAppComponent.mAppComponent = DaggerApplicationComponent.builder()
                            .applicationModule(new ApplicationModule(context.getApplicationContext()))
                            .networkModule(new NetworkModule())
                            .build();
                }
            }
        }
    }
}
