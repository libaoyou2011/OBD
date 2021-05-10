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
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.common.widget.DialogManager;
import com.baolong.obd.login.contract.IAccessTokenContract;
import com.baolong.obd.login.presenter.GetTokenPresenter;

public class WelcomeActivity extends BaseActivity
        implements IAccessTokenContract.View {
    private static final String TAG = WelcomeActivity.class.getSimpleName();
    Handler handler = new Handler();
    private TextView jumpBtn;
    private ProgressBar loadingPb;
    private TextView loadingTxt;
    private GetTokenPresenter mGetTokenPresenter;
    private Runnable runnable;
    private Button retryBtn;
    private Button setServerBtn;

    private void gotoLogin() {
        startActivity(new Intent(this, LoginActivity.class));
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

    protected void initView() {
        setContentView(R.layout.activity_welcom);

        // 默认初始化一个BASE_URL
        if (TextUtils.isEmpty(UserSP.getUserServerUrl())){
            UserSP.setUserServerUrl(RetrofitManager.BASE_URL);
        }

        this.jumpBtn = ((TextView) findViewById(R.id.btn_jump));
        this.loadingTxt = ((TextView) findViewById(R.id.txt_loading));
        this.loadingPb = ((ProgressBar) findViewById(R.id.pb_loading));
        this.retryBtn = ((Button) findViewById(R.id.btn_retry_token));
        this.setServerBtn = ((Button) findViewById(R.id.btn_set_server_url));
        this.retryBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WelcomeActivity.this.UpdateUiGetToken(true);
                WelcomeActivity.this.mGetTokenPresenter.getToken(RetrofitManager.mAppId, RetrofitManager.mUseName);
            }
        });
        this.jumpBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                WelcomeActivity.this.handler.removeCallbacks(WelcomeActivity.this.runnable);
                WelcomeActivity.this.jumpTo();
            }
        });
        this.setServerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogManager.showServerInputDialog(WelcomeActivity.this
                        , "修改服务器地址"
                        , new DialogManager.OnItemClickListener() {
                            public void onItemClick(View view1, String s) {
                            }
                        }
                        , "取消"
                        , new DialogManager.OnItemClickListener() {
                            public void onItemClick(View view1, String s) {
                                UserSP.setUserServerUrl(s);
                                RetrofitManager.BASE_URL = UserSP.getUserServerUrl();
                                RetrofitManager.restartRetrofitManager();
                                WelcomeActivity.this.UpdateUiGetToken(true);
                                WelcomeActivity.this.mGetTokenPresenter.getToken(RetrofitManager.mAppId, RetrofitManager.mUseName);
                            }
                        }
                        , "确认"
                        , UserSP.getUserServerUrl()
                ).show();
            }
        });
    }

    private void initPresenter() {
        this.mGetTokenPresenter = new GetTokenPresenter(this);
    }

    // 获取token时 界面的状态
    private void UpdateUiGetToken(boolean paramBoolean) {
        if (paramBoolean) {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initPresenter();
        this.mGetTokenPresenter.getToken(RetrofitManager.mAppId, RetrofitManager.mUseName);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void getTokenFail(String paramString) {
        UpdateUiGetToken(false);
        ToastUtils.shortToast(paramString);
    }

    @Override
    public void getTokenSuccess(String paramString) {
        //Log.d(TAG, "token : " + paramString);
        this.runnable = new Runnable() {
            public void run() {
                WelcomeActivity.this.jumpTo();
            }
        };
        this.handler.postDelayed(this.runnable, 1500L);
    }

    void jumpTo() {
        if (!TextUtils.isEmpty(UserSP.getUserToken())) {
            if ((!TextUtils.isEmpty(UserSP.getUserPwd())) && (!TextUtils.isEmpty(UserSP.getUserName()))) {
                ARouter.getInstance().build("/main/MainActivity").navigation();
                this.finish();
            } else {
                gotoLogin();
            }
        } else {
            ToastUtils.shortToast("获取授权成功后，才能使用！");
            UpdateUiGetToken(false);
        }
    }
}
