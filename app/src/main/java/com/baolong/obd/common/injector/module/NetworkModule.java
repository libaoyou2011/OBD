package com.baolong.obd.common.injector.module;

import com.baolong.obd.common.injector.scope.ApplicationScope;
import com.baolong.obd.common.network.RetrofitManager;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {
    @ApplicationScope
    @Provides
    public RetrofitManager provideRetrofitManager() {
        return RetrofitManager.getInstance();
    }
}