package com.baolong.obd.common.utils;

import android.text.TextUtils;
import android.util.Log;

import com.baolong.obd.BuildConfig;

public class LogUtil {
    private static int LOG_MAXLENGTH = 2000;
    //Log开关，debug模式下打印 ，release模式下不打印
    private static final boolean DEBUG = BuildConfig.LOG;

    public static void i(String tag, String content) {
        if (DEBUG) {
            if (TextUtils.isEmpty(content)){
                content = "";
            }
            int contentLength = content.length();

            int start = 0;
            int end = LOG_MAXLENGTH;

            int i = 0;
            while (i < 10) {
                if (contentLength > end) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(tag);
                    stringBuilder.append(i);
                    Log.i(stringBuilder.toString(), content.substring(start, end));

                    start = end;
                    end = end + LOG_MAXLENGTH;
                    i += 1;

                } else {
                    Log.i(tag, content.substring(start, contentLength));
                    break;
                }
            }
        }

    }
}
