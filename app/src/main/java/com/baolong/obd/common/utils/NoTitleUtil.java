package com.baolong.obd.common.utils;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Window;


public class NoTitleUtil {

    public static void noTitle(Activity activity) {

        if (activity instanceof AppCompatActivity) {
            //继承AppCompatActivity时使用：
            ((AppCompatActivity)activity).supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        } else if (activity instanceof Activity){
            //继承activity时使用：
            activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
    }
}
