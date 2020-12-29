package com.baolong.obd.main;

import android.app.Activity;
import android.util.Log;

import com.baolong.obd.analysis.fragment.AnalysisMainFragment;
import com.baolong.obd.blackcar.fragment.BlackCarMainFragment;
import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.base.BaseFragment;
import com.baolong.obd.execution.fragment.ExecutionMainFragment;
import com.baolong.obd.my.fragment.MyMainFragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

//@Keep
public class ViewManager {
    private static Stack<Activity> activityStack = new Stack<Activity>();
    private static final BaseFragment[] fragmentsArray = new BaseFragment[BaseApplication.MainPageSize];

    //静态变量 饿汉式
    private static final ViewManager INSTANCE = new ViewManager();

    //私有构造方法
    private ViewManager() {
    }

    // 获取单例
    public static ViewManager getInstance() {
        return INSTANCE;
    }


    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    public Activity currentActivity() {
        return (Activity) activityStack.lastElement();
    }

    public void finishActivity() {
        finishActivity((Activity) activityStack.lastElement());
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    public void finishActivity(Class<?> paramClass) {
        Iterator localIterator = activityStack.iterator();
        while (localIterator.hasNext()) {
            Activity localActivity = (Activity) localIterator.next();
            if (localActivity.getClass().equals(paramClass)) {
                finishActivity(localActivity);
                return;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        int j = activityStack.size();
        int i = 0;
        while (i < j) {
            if (activityStack.get(i) != null) {
                ((Activity) activityStack.get(i)).finish();
            }
            i += 1;
        }
        activityStack.clear();
    }

    public void exitApp() {
        try {
            finishAllActivity();
//            ((ActivityManager)paramContext.getSystemService(Context.ACTIVITY_SERVICE)).killBackgroundProcesses(paramContext.getPackageName());
        } catch (Exception exception) {
            StringBuilder sb = new StringBuilder();
            sb.append("app exit");
            sb.append(exception.getMessage());
            Log.e("ActivityManager", sb.toString());
        }
    }

    public void addFragment(int i, BaseFragment baseFragment) {
        if (i > BaseApplication.MainPageSize) {
            return;
        }
        fragmentsArray[i] = baseFragment;
    }

    public BaseFragment getFragment(int paramInt) {
        int i = fragmentsArray.length;
        BaseFragment localBaseFragment = null;
        if (paramInt < i) {
            localBaseFragment = fragmentsArray[paramInt];
        }
        return localBaseFragment;
    }

    public List<BaseFragment> getAllFragment() {
        ArrayList<BaseFragment> baseFragmentList = new ArrayList<BaseFragment>();
        int length = fragmentsArray.length;
        int i = 0;
        while (i < length) {
            BaseFragment baseFragment = fragmentsArray[i];
            if (baseFragment != null) {
                baseFragmentList.add(baseFragment);
            } else {
                switch (i) {
                    case 0:
                        baseFragmentList.add(BlackCarMainFragment.newInstance());
                        break;
                    case 1:
                        baseFragmentList.add(ExecutionMainFragment.newInstance());
                        break;
                    case 2:
                        baseFragmentList.add(AnalysisMainFragment.newInstance());
                        break;
                    case 3:
                        baseFragmentList.add(MyMainFragment.newInstance());
                        break;
                    default:
                        break;
                }
            }
            i += 1;
        }
        return baseFragmentList;
    }

}

