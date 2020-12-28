package com.baolong.obd.login;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baolong.obd.R;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.common.sharepreferemces.UserSP;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.login.contract.ILoginContract;
import com.baolong.obd.login.model.ResponseLoginModel;
import com.baolong.obd.login.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity
        implements View.OnClickListener, ILoginContract.View {
    private EditText mEt_phone;
    private EditText mPwsEt;
    private Button mBt_login;
    private LoginPresenter mLoginPresenter;

    protected void initView() {
        this.mEt_phone = ((EditText) findViewById(R.id.et_login_phone));
        this.mPwsEt = ((EditText) findViewById(R.id.et_login_pwd));
        this.mBt_login = ((Button) findViewById(R.id.bt_login));
        this.mBt_login.setOnClickListener(this);
        String userName;
        if (TextUtils.isEmpty(UserSP.getUserName())) {
            userName = "";
        } else {
            userName = UserSP.getUserName();
        }
        this.mEt_phone.setText(userName);
        // 光标再最后一个文字后
        this.mEt_phone.setSelection(userName.length());
    }

    public void onClick(View view) {
        if (view.getId() == R.id.bt_login) {
            String tips;
            if (TextUtils.isEmpty(this.mEt_phone.getText().toString().trim())) {
                tips = "请输入账号信息！";
            } else {
                if (TextUtils.isEmpty(this.mPwsEt.getText().toString().trim())) {
                    tips = "请输入密码！";
                } else {
                    doLogin(this.mEt_phone.getText().toString().trim(), this.mPwsEt.getText().toString().trim());
                    return;
                }
            }
            ToastUtils.shortToast(tips);
        }
    }

    protected void initData() {
        try {
            String str = BaseApplication.getIns().getPackageManager().getPackageInfo(BaseApplication.getIns().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initPresenter() {
        this.mLoginPresenter = new LoginPresenter(this);
    }

    private void doLogin(String userName, String userPws) {
        showLoading(this.mBt_login);
        this.mLoginPresenter.login(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, userName, userPws);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_login);

        initView();
        initData();
        initPresenter();
    }

    public void loginFail(String s) {
        hideLoading();
        ToastUtils.shortToast(s);

        //loginSuccess();

    }

    public void loginSuccess(ResponseLoginModel responseLoginModel) {
        hideLoading();
        UserSP.setUserName(this.mEt_phone.getText().toString().trim());
        UserSP.setUserPwd(this.mPwsEt.getText().toString().trim());
        ARouter.getInstance().build("/main/MainActivity").navigation();
        finish();
    }
}