package com.baolong.obd.common.injector.module;

import android.content.Context;

import com.baolong.obd.common.injector.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module  // 注明本类是Module
public class ApplicationModule {
    private final Context mContext;

    public ApplicationModule(Context paramContext) {
        this.mContext = paramContext;
    }

    @ApplicationScope
    @Provides   // 注明该方法是用来提供依赖对象的方法
    public Context provideApplicationContext() {
        return this.mContext;
    }
}
