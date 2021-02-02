package com.baolong.obd.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;

import com.baolong.obd.common.base.BaseApplication;

public class Utils {
    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void addFragmentToActivity(@NonNull FragmentManager paramFragmentManager, @NonNull Fragment paramFragment, int paramInt) {
        checkNotNull(paramFragmentManager);
        checkNotNull(paramFragment);

        FragmentTransaction transaction = paramFragmentManager.beginTransaction();
        transaction.add(paramInt, paramFragment);
        transaction.commit();
    }

    public static <T> T checkNotNull(T paramT) {
        if (paramT == null) {
            throw new NullPointerException();
        }
        return paramT;
    }

    @NonNull
    public static Activity getActivity(View paramView) {
        for (Object localObject = paramView.getContext(); (localObject instanceof ContextWrapper); localObject = ((ContextWrapper) localObject).getBaseContext()) {
            if ((localObject instanceof Activity)) {
                return (Activity) localObject;
            }
        }
        StringBuilder localObject = new StringBuilder();
        ((StringBuilder) localObject).append("View ");
        ((StringBuilder) localObject).append(paramView);
        ((StringBuilder) localObject).append(" is not attached to an Activity");
        throw new IllegalStateException(((StringBuilder) localObject).toString());
    }

    public static Context getContext() {
        if (context != null) {
            return context;
        } else {
            return BaseApplication.getInstance();
        }
    }

    public static void init(Context paramContext) {
        context = paramContext.getApplicationContext();
    }

    public static String getString(@StringRes int paramInt) {
        return context.getResources().getString(paramInt);
    }

    public static boolean isAppDebug() {
        if (StringUtils.isSpace(context.getPackageName())) {
            return false;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            if ((applicationInfo != null) && ((applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0)) {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}