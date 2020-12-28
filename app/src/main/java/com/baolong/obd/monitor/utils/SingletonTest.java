package com.baolong.obd.monitor.utils;

import android.content.Context;

/*单例，用于模拟内存泄漏*/
public class SingletonTest {
    private static SingletonTest singleton;
    private Context context;

    private SingletonTest(Context context) {
        this.context = context;
    }

    public static SingletonTest newInstance(Context context) {
        if (singleton == null) {
            synchronized (SingletonTest.class) {
                if (singleton == null){//双重检查锁定
                    singleton = new SingletonTest(context);
                }
            }
        }
        return singleton;
    }

    public void dealData() {
    }
}
