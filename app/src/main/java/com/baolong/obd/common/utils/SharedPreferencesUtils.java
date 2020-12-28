package com.baolong.obd.common.utils;

import android.content.Context;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesUtils {
    private static final String APP_SP_NAME = "com.baolong.edsp";
    public static final String SEARCH_TYPE_STATION = "station";
    public static final String SP_TOKEN_KEY = "token";
    public static final String SP_USERID_KEY = "userId";

    public static String read(Context context, String key, String defValue) {
        return context.getSharedPreferences(APP_SP_NAME, MODE_PRIVATE).getString(key, defValue);
    }

    public static void write(Context paramContext, String key, String value) {
        paramContext.getSharedPreferences(APP_SP_NAME, MODE_PRIVATE).edit().putString(key, value).apply();
    }
}