package com.baolong.obd.login;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baolong.obd.R;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.common.sharepreferemces.UserSP;
import com.baolong.obd.common.widget.DialogManager;
import com.baolong.obd.login.presenter.GetTokenPresenter;

public class WelcomeActivityV2 extends BaseActivity
//        implements IAccessTokenContract.View
{
    private static final String TAG = WelcomeActivityV2.class.getSimpleName();
    Handler handler = new Handler();

    private TextView jumpBtn;
    private ProgressBar loadingPb;
    private TextView loadingTxt;
    private GetTokenPresenter mGetTokenPresenter;
    private Runnable runnable;
    private Button retryBtn;
    private Button setServerBtn;

    private void gotoLogin() {
        startActivity(new Intent(this, LoginActivityV2.class));
        finish();
    }

    public static boolean isApkInDebug() {
        try {
            if ((BaseApplication.getInstance().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

//    private void startCheckVersion() {
//        Intent intent = new Intent();
//        intent.setClass(this, UpdataService.class);
//        startService(intent);
//    }

//    private void stopCheckVision() {
//        Intent intent = new Intent();
//        intent.setClass(this, UpdataService.class);
//        stopService(intent);
//    }

    // 获取token时 界面的状态
    private void UpdateUiGetToken(boolean isGetTokenStatus) {
        if (isGetTokenStatus) {
            this.loadingTxt.setVisibility(View.VISIBLE);
            this.loadingPb.setVisibility(View.VISIBLE);
            this.retryBtn.setVisibility(View.GONE);
            this.setServerBtn.setVisibility(View.GONE);
        } else {
            this.loadingTxt.setVisibility(View.INVISIBLE);
            this.loadingPb.setVisibility(View.INVISIBLE);
            this.retryBtn.setVisibility(View.VISIBLE);
            this.setServerBtn.setVisibility(View.VISIBLE);
        }
    }

    protected void initView() {
        setContentView(R.layout.activity_welcom);

//        // 默认初始化一个BASE_URL
//        if (TextUtils.isEmpty(UserSP.getUserServerUrl())){
//            UserSP.setUserServerUrl(RetrofitManager.BASE_URL);
//        }

        this.jumpBtn = ((TextView) findViewById(R.id.btn_jump));
        this.loadingTxt = ((TextView) findViewById(R.id.txt_loading));
        this.loadingPb = ((ProgressBar) findViewById(R.id.pb_loading));
        this.retryBtn = ((Button) findViewById(R.id.btn_retry_token));
        this.setServerBtn = ((Button) findViewById(R.id.btn_set_server_url));
        this.retryBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//                WelcomeActivity1.this.UpdateUiGetToken(true);
//                WelcomeActivity1.this.mGetTokenPresenter.getToken(RetrofitManager.mAppId, RetrofitManager.mUseName);
            }
        });
        this.jumpBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                WelcomeActivityV2.this.handler.removeCallbacks(WelcomeActivityV2.this.runnable);
                WelcomeActivityV2.this.jumpToEvent();
            }
        });
        this.setServerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogManager.showServerInputDialog(WelcomeActivityV2.this
                        , "修改服务器地址"
                        , new DialogManager.OnItemClickListener() {
                            public void onItemClick(View view1, String s) {
                            }
                        }
                        , "取消"
                        , new DialogManager.OnItemClickListener() {
                            public void onItemClick(View view1, String userServerUrl) {
                                UserSP.setUserServerUrl(userServerUrl);
                                RetrofitManager.BASE_URL = UserSP.getUserServerUrl();
                                RetrofitManager.restartRetrofitManager();
//                                WelcomeActivity1.this.UpdateUiGetToken(true);
//                                WelcomeActivity1.this.mGetTokenPresenter.getToken(RetrofitManager.mAppId, RetrofitManager.mUseName);
                            }
                        }
                        , "确认"
                        , UserSP.getUserServerUrl()
                ).show();
            }
        });
        this.runnable = new Runnable() {
            @Override
            public void run() {
                WelcomeActivityV2.this.jumpToEvent();
            }
        };
        this.handler.postDelayed(this.runnable, 2000L);
    }

//    private void initPresenter() {
//        this.mGetTokenPresenter = new GetTokenPresenter(this);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 添加过期Cookie，测试使用，模拟cookie过期
        //UserSP.setCookie("446a2e47-5480-4c33-a7ed-b70fdda8b035");


        initView();
//        initPresenter();
//        this.mGetTokenPresenter.getToken(RetrofitManager.mAppId, RetrofitManager.mUseName);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

//    @Override
//    public void getTokenFail(String paramString) {
//        UpdateUiGetToken(false);
//        ToastUtils.shortToast(paramString);
//    }

//    @Override
//    public void getTokenSuccess(String paramString) {
//        //Log.d(TAG, "token : " + paramString);
//        this.runnable = new Runnable() {
//            public void run() {
//                WelcomeActivity1.this.jumpToEvent();
//            }
//        };
//        this.handler.postDelayed(this.runnable, 1500L);
//    }

    void jumpToEvent() {
        if (!TextUtils.isEmpty((CharSequence) UserSP.getUserPwd()) && !TextUtils.isEmpty((CharSequence) UserSP.getUserName())) {
            ARouter.getInstance().build("/main/MainActivity").navigation();
            this.finish();
            return;
        }
        this.gotoLogin();
    }
}
