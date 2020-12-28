package com.baolong.obd.common.utils;

import android.widget.Toast;

public class ToastUtils {
    public static void toast(String paramString) {
        Toast.makeText(Utils.getContext(), paramString, Toast.LENGTH_SHORT).show();
    }

    public static void longToast(String paramString) {
        Toast.makeText(Utils.getContext(), paramString, Toast.LENGTH_SHORT).show();
    }

    public static void shortToast(String paramString) {
        toast(paramString);
    }

    public static void toast(String paramString, int paramInt) {
        Toast toast = Toast.makeText(Utils.getContext(), paramString, Toast.LENGTH_SHORT);
        toast.setGravity(paramInt, 0, 0);
        toast.show();
    }
}