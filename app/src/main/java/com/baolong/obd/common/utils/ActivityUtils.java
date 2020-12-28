package com.baolong.obd.common.utils;

import android.app.Activity;
import android.content.Intent;

import com.baolong.obd.R;

/**
 * Created by Libaoyou on 2019/1/9.
 */
public class ActivityUtils {

    public final static int PATH_RIGHT_TO_LEFT = 1;
    public final static int PATH_LEFT_TO_RIGHT = 2;
    public final static int PATH_RIGHT_TO_0 = 3;
    public final static int PATH_LEFT_TO_0 = 4;
    private final static String TAG = ActivityUtils.class.getSimpleName();

//    /**
//     * 切换到新的页面，默认可回退，从右向左
//     * @param activity
//     * @param clazz
//     */
//    public static void activitySwitch(Activity activity, Class<?> clazz) {
//        activitySwitch(activity, clazz, true);
//    }

    /**
     * 切换到新的页面, 默认 从右向左
     *
     * @param activity
     * @param clazz
     * @param backable
     */
    public static void activitySwitch(Activity activity, Class<?> clazz, boolean backable) {
        activitySwitch(activity, clazz, backable, PATH_RIGHT_TO_LEFT);
    }

    /**
     * 切换到新的页面
     *
     * @param activity
     * @param clazz
     * @param backable
     * @param path
     */
    public static void activitySwitch(Activity activity, Class<?> clazz, boolean backable, int path) {
        activity.startActivity(new Intent(activity, clazz));
        if (path == PATH_RIGHT_TO_LEFT) {
            activity.overridePendingTransition(R.anim.in_righttoleft, R.anim.out_righttoleft);
        } else if (path == PATH_LEFT_TO_RIGHT) {
            activity.overridePendingTransition(R.anim.in_lefttoright, R.anim.out_lefttoright);
        } else if (path == PATH_RIGHT_TO_0) {
            activity.overridePendingTransition(R.anim.in_righttoleft, R.anim.inout_0);
        } else if (path == PATH_LEFT_TO_0) {
            activity.overridePendingTransition(R.anim.in_lefttoright, R.anim.inout_0);
        }

        if (!backable) {
            activity.finish();
        }
    }

    /**
     * 切换到新的Activity, 可返回，从右向左
     *
     * @param activity
     * @param intent
     */
    public static void activitySwitch(Activity activity, Intent intent) {
        activitySwitch(activity, intent, true);
    }

    /**
     * 切换到新的Activity，从右向左
     *
     * @param activity
     * @param intent
     * @param backable
     */
    public static void activitySwitch(Activity activity, Intent intent, boolean backable) {
        activitySwitch(activity, intent, backable, PATH_RIGHT_TO_LEFT);
    }

    /**
     * 切换到新的Activity
     *
     * @param activity
     * @param intent
     * @param backable
     * @param path
     */
    public static void activitySwitch(Activity activity, Intent intent, boolean backable, int path) {
        activity.startActivity(intent);
        if (path == PATH_RIGHT_TO_LEFT) {
            activity.overridePendingTransition(R.anim.in_righttoleft, R.anim.out_righttoleft);
        } else if (path == PATH_LEFT_TO_RIGHT) {
            activity.overridePendingTransition(R.anim.in_lefttoright, R.anim.out_lefttoright);
        } else if (path == PATH_RIGHT_TO_0) {
            activity.overridePendingTransition(R.anim.in_righttoleft, R.anim.inout_0);
        } else if (path == PATH_LEFT_TO_0) {
            activity.overridePendingTransition(R.anim.in_lefttoright, R.anim.inout_0);
        }

        if (!backable) {
            activity.finish();
        }
    }
}
