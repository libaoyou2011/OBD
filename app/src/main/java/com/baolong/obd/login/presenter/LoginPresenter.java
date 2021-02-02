package com.baolong.obd.login.presenter;

import androidx.annotation.NonNull;
import android.util.Log;

import com.baolong.obd.R;
import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.login.LoginApis;
import com.baolong.obd.login.contract.ILoginContract;
import com.baolong.obd.login.model.ResponseLoginModel;

import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter implements ILoginContract.Presenter {
    private static final String TAG = LoginPresenter.class.getSimpleName();

    @NonNull
    private final ILoginContract.View mLoginView;

    public LoginPresenter(@NonNull ILoginContract.View paramView) {
        this.mLoginView = paramView;
    }

    @Override
    public void dropView() {
    }

    public void login(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
        ((LoginApis.Login) RetrofitManager.getInstance().createReq(LoginApis.Login.class))
                .req(paramString1, paramString2, paramString3, paramString4, paramString5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Observer<ResponseWrapper>) new Observer<ResponseWrapper>() {
                    public void onCompleted() {
                        Log.i("oyj", "Login onCompleted");
                    }

                    public void onError(Throwable t) {
                        t.printStackTrace();
                        Log.i("oyj", "Login onError");
                        if (LoginPresenter.this.mLoginView != null) {
                            LoginPresenter.this.mLoginView.loginFail(BaseApplication.getIns().getString(R.string.login_fail));
                        }
                    }

                    public void onNext(ResponseWrapper responseWrapper) {
                        //ToastUtils.longToast(new Gson().toJson(responseWrapper));
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("wrapperRspEntity is ");
                        stringBuilder.append(responseWrapper.getCode());
                        Log.i("oyj", stringBuilder.toString());
                        if (LoginPresenter.this.mLoginView == null) {
                            return;
                        }
                        if (responseWrapper.getCode() == 200) {
                            //LoginPresenter.this.mLoginView.loginSuccess();
                            return;
                        }
                        LoginPresenter.this.mLoginView.loginFail(responseWrapper.getError());
                    }
                });
    }

    public void login2(RequestBody requestBody) {
        ((LoginApis.Login2) RetrofitManager.getInstance().createReq(LoginApis.Login2.class))
                .req(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Observer<ResponseLoginModel>) new Observer<ResponseLoginModel>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "Login onCompleted");
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                        Log.i(TAG, "Login onError");
                        if (LoginPresenter.this.mLoginView != null) {
                            LoginPresenter.this.mLoginView.loginFail(BaseApplication.getIns().getString(R.string.login_fail));
                        }
                    }
                    @Override
                    public void onNext(ResponseLoginModel responseWrapper) {
                        //LogUtil.i(TAG,new Gson().toJson(responseWrapper) );

                        if (LoginPresenter.this.mLoginView == null) {
                            return;
                        }
                        if (responseWrapper.getCode() == 200) {
                            LoginPresenter.this.mLoginView.loginSuccess(responseWrapper);
                        } else {
                            LoginPresenter.this.mLoginView.loginFail(responseWrapper.getMsg());
                        }
                    }
                });
    }

    @Override
    public void takeView(ILoginContract.View paramView) {
    }
}