package com.baolong.obd.common.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baolong.obd.R;
import com.baolong.obd.common.model.notice.ImNoticeModel;
import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.utils.NoTitleUtil;
import com.baolong.obd.main.ViewManager;
import com.bumptech.glide.Glide;
//import com.hzx.huanping.common.model.notice.AlerterBusiness;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.common.utils.Utils;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.tapadoo.alerter.Alerter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import pub.devrel.easypermissions.EasyPermissions;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN;

public abstract class BaseActivity extends AppCompatActivity {
    public static final int ACTIVITY_REQUEST_CODE_ALBUM = 0;
    public static final int ACTIVITY_REQUEST_CODE_CAMERA = 1;
    public static final int ACTIVITY_REQUEST_CODE_FILE = 2;
    protected KProgressHUD Progress;
    private BasePresenter basePresenter;

    public static PopupWindow initLoadingWindows(Context paramContext) {
        PopupWindow localPopupWindow = new PopupWindow(-1, -1);
        localPopupWindow.setFocusable(true);
        localPopupWindow.setTouchable(true);
        ImageView localImageView = new ImageView(paramContext);
        localImageView.setScaleType(ImageView.ScaleType.CENTER);
        Glide.with(paramContext).asGif().load(R.mipmap.gif_common_refresh).into(localImageView);
        localPopupWindow.setContentView(localImageView);
        return localPopupWindow;
    }

    public boolean ExistSDCard() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return true;
        }
        ToastUtils.shortToast("SD不可用");
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void noticeFunction(ImNoticeModel paramImNoticeModel) {
        Alerter.hide();
//        AlerterBusiness.showAlert(getApplicationContext(), paramImNoticeModel, Alerter.create(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置 无标题栏
        NoTitleUtil.noTitle(this);
        // 设置 状态栏颜色
        if(Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_VISIBLE;
            decorView.setSystemUiVisibility(option);
            //getWindow().setStatusBarColor(getResources().getColor(R.color.status_bar));
        }
        // 设置 隐藏输入法
        getWindow().setSoftInputMode(SOFT_INPUT_STATE_HIDDEN);

        //activity创建的时候，将该新创建的activity加到栈表中
        ViewManager.getInstance().addActivity(this);

        //进度条
        this.Progress = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("...")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5F);
        //this.setLabel("Please wait")
        //this.setDetailsLabel("Downloading data")
        // this.Progress.isShowing();
        // this.Progress.dismiss();

        //注册 EventBus
        EventBus.getDefault().register(this);

        // 在父类对 ARouter.inject注入 进行封装，子类都不要写此行代码了
        ARouter.getInstance().inject(this);
    }

    @Override
    protected void onPause() {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册 EventBus
        EventBus.getDefault().unregister(this);
        //取消进度条
        if ((this.Progress != null) && (this.Progress.isShowing())) {
            this.Progress.dismiss();
            this.Progress = null;
        }
        //将该activity从栈中移除
        ViewManager.getInstance().finishActivity(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, new Object[]{this});
    }


    //管理Fragment 6个方法
    protected void addFragment(BaseFragment paramBaseFragment, @IdRes int paramInt) {
        Utils.checkNotNull(paramBaseFragment);
        getSupportFragmentManager().beginTransaction().add(paramInt, paramBaseFragment, paramBaseFragment.getClass().getSimpleName()).addToBackStack(paramBaseFragment.getClass().getSimpleName()).commitAllowingStateLoss();
    }

    protected void removeFragment(BaseFragment baseFragment) {
        Utils.checkNotNull(baseFragment);
        getSupportFragmentManager().beginTransaction().remove(baseFragment).commitAllowingStateLoss();
    }

    protected void replaceFragment(BaseFragment paramBaseFragment, @IdRes int paramInt) {
        Utils.checkNotNull(paramBaseFragment);
        getSupportFragmentManager().beginTransaction().replace(paramInt, paramBaseFragment, paramBaseFragment.getClass().getSimpleName()).addToBackStack(paramBaseFragment.getClass().getSimpleName()).commitAllowingStateLoss();
    }

    protected void hideFragment(BaseFragment paramBaseFragment) {
        Utils.checkNotNull(paramBaseFragment);
        getSupportFragmentManager().beginTransaction().hide(paramBaseFragment).commitAllowingStateLoss();
    }

    protected void showFragment(BaseFragment paramBaseFragment) {
        Utils.checkNotNull(paramBaseFragment);
        getSupportFragmentManager().beginTransaction().show(paramBaseFragment).commitAllowingStateLoss();
    }

    protected void popFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
            return;
        }
        finish();
    }

    // 显示通知消息模块 3个方法
    public void showDialogMsg(String paramString) {
        try {
            this.Progress.setLabel(paramString);
            if (!this.Progress.isShowing()) {
                this.Progress.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void showLoading(View paramView) {
        try {
            if (!this.Progress.isShowing()) {
                this.Progress.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void hideLoading() {
        try {
            if (this.Progress.isShowing()) {
                this.Progress.dismiss();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    // 输入法管理模块 2个方法
    public void toggleInput() {
        InputMethodManager localInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if ((localInputMethodManager != null) && (getCurrentFocus() != null)) {
            localInputMethodManager.toggleSoftInput(0, 2);
        }
    }

    public void closeInput() {
        InputMethodManager localInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if ((localInputMethodManager != null) && (getCurrentFocus() != null)) {
            localInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        }
    }

}

