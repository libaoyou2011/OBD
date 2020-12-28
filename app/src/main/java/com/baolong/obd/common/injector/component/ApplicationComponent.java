package com.baolong.obd.common.injector.component;

import android.content.Context;

import com.baolong.obd.common.injector.module.ApplicationModule;
import com.baolong.obd.common.injector.module.NetworkModule;
import com.baolong.obd.common.injector.scope.ApplicationScope;
import com.baolong.obd.common.network.RetrofitManager;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class, NetworkModule.class}) // 指明Component查找Module的位置
public abstract interface ApplicationComponent { // 必须定义为接口，Dagger2框架将自动生成Component的实现类，对应的类名是Dagger×××××，这里对应的实现类是DaggerApplicationComponent

    public abstract Context getContext();

    public abstract RetrofitManager getRetrofitManager();

}