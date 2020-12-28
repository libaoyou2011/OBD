package com.baolong.obd.my.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.network.RsaUtil;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.my.contract.ResetPasswordContract;
import com.baolong.obd.my.presenter.ResetPasswordPresenter;

public class ResetPasswordActivity extends BaseActivity
        implements ResetPasswordContract.View, View.OnClickListener {

    private EditText mOldPassword;
    private EditText mNewPassword;
    private Button mConfirm;

    private ResetPasswordPresenter resetPasswordPresenter;

    private void initTitle() {
        ((ImageView) findViewById(R.id.image_title_back)).setOnClickListener((view) -> {
            this.onBackPressed();
        });
        ((TextView) findViewById(R.id.tv_title)).setText(getResources().getString(R.string.my_change_password_title));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initTitle();

        mOldPassword = (EditText) this.findViewById(R.id.older_password);
        mNewPassword = (EditText) this.findViewById(R.id.new_password);
        mConfirm = (Button) this.findViewById(R.id.bt_confirm);

        mOldPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(" ")) {
                    String[] str = s.toString().split(" ");
                    StringBuilder sb = new StringBuilder();
                    for (String value : str) {
                        sb.append(value);
                    }
                    mOldPassword.setText(sb.toString());
                    mOldPassword.setSelection(start);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(" ")) {
                    String[] str = s.toString().split(" ");
                    StringBuilder sb = new StringBuilder();
                    for (String value : str) {
                        sb.append(value);
                    }
                    mNewPassword.setText(sb.toString());
                    mNewPassword.setSelection(start);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mConfirm.setOnClickListener(this);

        resetPasswordPresenter = new ResetPasswordPresenter(this);

    }

    @Override
    public void onClick(final View view) {
        //重置密码
        if (view.getId() == R.id.bt_confirm) {
            String oldPassWord = mOldPassword.getText().toString();
            String newPassword = mNewPassword.getText().toString();
            if (!TextUtils.isEmpty(oldPassWord) && !TextUtils.isEmpty(newPassword)) {
//                JSONObject tempJSONObject = new JSONObject();
//                try {
//                    tempJSONObject.put("oldPassword", olderPassWord);
//                    tempJSONObject.put("newPassword", newPassword);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), tempJSONObject.toString());
                try {
                    oldPassWord = RsaUtil.encryptByPubKey(oldPassWord);
                    LogUtil.i("ResetPasswordActivity", "olderPassWord 加密:" + oldPassWord);

                    newPassword = RsaUtil.encryptByPubKey(newPassword);
                    LogUtil.i("ResetPasswordActivity", "newPassword 加密:" + newPassword);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                this.resetPasswordPresenter.setChangePassword(oldPassWord, newPassword);

            } else if (TextUtils.isEmpty(oldPassWord)) {
                ToastUtils.shortToast("请输入旧密码！");

            } else if (TextUtils.isEmpty(newPassword)) {
                ToastUtils.shortToast("请输入新密码！");
            }

        }
    }

    @Override
    public void setPresenter(ResetPasswordContract.Presenter paramT) {

    }

    public void showLoading() {
    }

    public void hideLoading() {
        ((BaseActivity) this).hideLoading();
    }

    public void showFail(String paramString) {
        ToastUtils.shortToast(paramString);
    }


    public void showChangePasswordSuccess(String paramString) {
        ToastUtils.shortToast(paramString);
    }
}

