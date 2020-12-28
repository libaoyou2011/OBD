package com.baolong.obd.common.injector.module;

import android.app.Activity;
import android.content.Context;

import com.baolong.obd.common.injector.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity paramActivity) {
        this.mActivity = paramActivity;
    }

    @ActivityScope
    @Provides
    public Context provideActivityContext() {
        return this.mActivity;
    }
}
