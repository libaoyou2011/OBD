package com.baolong.obd.common.base;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import androidx.multidex.MultiDex;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baolong.obd.BuildConfig;
import com.baolong.obd.common.sharepreferemces.UserSP;
import com.baolong.obd.common.utils.Utils;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
//import com.alibaba.android.arouter.utils.ClassUtils;
//import com.tencent.bugly.Bugly;
//import com.tencent.bugly.beta.Beta;

import java.util.ArrayList;
import java.util.List;

public class BaseApplication extends Application {
    private final static String TAG = BaseApplication.class.getSimpleName();
    private static BaseApplication sInstance;
    public static int MainPageSize = 5;
    private List<IApplicationDelegate> mAppDelegateList = new ArrayList<>();
    // 加载图片使用glid框架，在AppImageDisplay类中设置cookie，这样才能访问到图片
    // 首次登录，获取cookie后，在ReceivedCookiesInterceptor给cookie赋值
    // 非首次登录，从SharedPrferences中赋值
    public static String cookie;

    public static String host;

    public static BaseApplication getIns() {
        return sInstance;
    }

    public static Application getInstance() {
        return sInstance;
    }

    private static RefWatcher sRefWatcher;

    public static RefWatcher getRefWatcher() {
        return sRefWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // 注册LeakCanary
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        // 测试内存泄漏 在WarningListActivity中模拟内存泄漏
        sRefWatcher = LeakCanary.install(this);

        sInstance = this;

        Utils.init(this);

        //GlobalAppComponent.init(this);

        host = UserSP.getStationLocalHost();
        cookie = UserSP.getCookie();

        // 初始化 dbFlow
        FlowManager.init(new FlowConfig.Builder(getInstance()).openDatabasesOnInit(true).build());

        // 初始化 ARouter， 在onTerminate()生命周期中销毁
        // 注入：已在 BaseActivity.onCreate()方法里统一注入
        // 注解：在Activity/Fragment类上面写上 Route path 注解，eg：@Route(path = 类注释标签路径 Constance.ACTIVITY_URL_MAIN)
        // 使用：跳转到指定界面，ARouter.getInstance().build(目标界面对应的路径 Constance.ACTIVITY_URL_MAIN).navigation();
        // 参数传递：1.参数传递 .withString("name", "张三").withParcelable("bean", new Bean())
        //          2.参数获取 @Autowired(name="name") String name;  @Autowired(name="bean") Bean bean;
        // 回传数据：1.跳转新页面：navigation(MainActivity，requesCode：123)
        //          2.返回上一页面：setResult(123)
        //          3.重写onActivityResult()方法
        initRouter();

        for (IApplicationDelegate iApplicationDelegate : this.mAppDelegateList) {
            iApplicationDelegate.onCreate();
        }
        setStrictMode();

//        this.mAppDelegateList = ClassUtils.getObjectsWithInterface(this, IApplicationDelegate.class, ROOT_PACKAGE);

        //腾讯热修复
//        Bugly.init(getApplicationContext(), BUGLY_APP_ID, Utils.isAppDebug());
//        Beta.enableHotfix = true;
//        Beta.canAutoDownloadPatch = true;
//        Beta.canNotifyUserRestart = false;
//        Beta.canAutoPatch = true;

    }

    private void initRouter() {
        try {
            if (BuildConfig.DEBUG) {
                // 这两行必须写在init之前，否则这些配置在init过程中将无效
                ARouter.openLog();     // 打印日志
                ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            }
            Log.i(TAG, "Debug=" + BuildConfig.DEBUG);
            // 尽可能早，推荐在Application中初始化
            ARouter.init(BaseApplication.this);
            Log.i(TAG, "ARouter.init...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TargetApi(9)
    protected void setStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }


    @Override
    protected void attachBaseContext(Context paramContext) {
        super.attachBaseContext(paramContext);
        MultiDex.install(this);
//        Beta.installTinker();   //腾讯热修复
    }

    //Android的整个App应用退出后的回调，只在模拟器上回调，真机上不会回调
    @Override
    public void onTerminate() {
        super.onTerminate();
        for (IApplicationDelegate iApplicationDelegate : this.mAppDelegateList) {
            iApplicationDelegate.onTerminate();
        }

        // 销毁ARouter
        ARouter.getInstance().destroy();
    }

    /**
     * onTrimMemory 回调是 Android 4.0 之后提供的一个API。
     * 它的主要用来提示开发者在系统内存不足的时候，根据当前内存情况(level)，释放相关资源以减小系统内存压力，这样可以减少app进程被系统杀死的可能性。
     * 尽可能的保存app进程，等到用户在下一次使用的时候，启动速度就会比较快。
     */
    @Override
    public void onTrimMemory(int paramInt) {
        super.onTrimMemory(paramInt);
        for (IApplicationDelegate iApplicationDelegate : this.mAppDelegateList) {
            iApplicationDelegate.onTrimMemory(paramInt);
        }
    }

    /**
     * 在Android 4.0之前都是onLowMemory来处理这类逻辑的，onLowMemory和onTrimMemory中的TRIM_MEMORY_COMPLETE级别相同。
     * 如果想兼容Android4.0之前的系统可以实现该方法，否则只需要处理onTrimMemory方法。
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        for (IApplicationDelegate iApplicationDelegate : this.mAppDelegateList) {
            iApplicationDelegate.onLowMemory();
        }
    }

}
