package com.baolong.obd.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Converter {

    private final static String TAG = Converter.class.getSimpleName();

    public static String Date2String(Date date, String pattern) {
        return Date2String(date, pattern, TimeZone.getTimeZone("GMT+08:00"));
    }

    public static String Date2String(Date date, String pattern, TimeZone timeZone) {

        if (date == null) {
            return "";
        }

        DateFormat df = new SimpleDateFormat(pattern);
        df.setTimeZone(timeZone);
        try {
            String s = df.format(date);
            return s;
        } catch (Exception ex) {
            return "";
        }
    }

    public static Date String2Date(String date, String pattern) {
        return String2Date(date, pattern, TimeZone.getTimeZone("GMT+08:00"));
    }

    public static Date String2Date(String date, String pattern, TimeZone timeZone) {

        DateFormat df = new SimpleDateFormat(pattern);
        df.setTimeZone(timeZone);

        try {
            Date time = df.parse(date);
            return time;
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    /**
     * 对中文URL进行编码
     *
     * @param str
     * @return
     */
    public static String URLEncoder(String str) {
        try {
            if (isNullOrEmpty(str)) {
                return "";
            } else {
                str = URLEncoder.encode(str.trim(), "UTF-8");
            }
        } catch (Exception ex) {
        }
        return str;
    }

    /**
     * 将多个目录层次拼接成为一个完整的路径。主要目的是为了处理每个目录名字开头或结尾的 File.separatorChar
     *
     * @param path
     * @return
     */
    public static String CombinePath(String... path) {

        String full = "";

        for (int i = 0; i < path.length; i++) {
            if (full == "") {
                full = path[i];
            } else if (full.endsWith(String.valueOf(File.separatorChar)) && path[i].startsWith(String.valueOf(File.separatorChar))) {
                full += path[i].substring(0);
            } else if (full.endsWith(String.valueOf(File.separatorChar)) || path[i].startsWith(String.valueOf(File.separatorChar))) {
                full += path[i];
            } else {
                full += (File.separatorChar + path[i]);
            }
        }

        return full;
    }

    /**
     * 将多个层次的URL拼接成一个，重点是处理多段之间的 / 连接符，避免连续两个挨着的。
     *
     * @param url
     * @return
     */
    public static String CombineUrl(String... url) {
        String result = "";

        for (int i = 0; i < url.length; i++) {
            if (TextUtils.isEmpty(result)) {
                result = url[i];
            } else if (result.endsWith("/") && url[i].startsWith("/")) {
                result += url[i].substring(1);
            } else if (result.endsWith("/") || url[i].startsWith("/")) {
                result += url[i];
            } else {
                result += ("/" + url[i]);
            }
        }

        return result;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isEmpty(String s) {
        return s == null || "".equals(s.trim());
    }

    public static boolean isNotEmpty(String s) {
        return s != null && !"".equals(s.trim());
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dpi(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static float sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) spValue * scale;
    }

    public static float px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) pxValue / scale;
    }

    /**
     * 判断target是否被数组包含
     *
     * @param array
     * @param target
     * @return
     */
    public static boolean ArraysContains(String[] array, String target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }

}
