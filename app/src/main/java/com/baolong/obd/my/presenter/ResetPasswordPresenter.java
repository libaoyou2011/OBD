package com.baolong.obd.my.presenter;

import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.my.MyApis;
import com.baolong.obd.my.contract.ResetPasswordContract;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ResetPasswordPresenter implements ResetPasswordContract.Presenter {

    private ResetPasswordContract.View mView;

    public ResetPasswordPresenter(ResetPasswordContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    @Override
    public void dropView() {
        this.mView = null;
    }

    @Override
    public void takeView(ResetPasswordContract.View paramView) {
    }

    @Override
    public void setChangePassword(String olderPassWord, String newPassword) {
        ((MyApis.ResetPassword) RetrofitManager.getInstance().createReq(MyApis.ResetPassword.class))
                .req(olderPassWord, newPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapper<String>>() {
                    public void onCompleted() {
                    }

                    public void onError(Throwable paramAnonymousThrowable) {
                        if (ResetPasswordPresenter.this.mView != null) {
                            ResetPasswordPresenter.this.mView.showFail("修改密码失败！");
                        }
                    }

                    public void onNext(ResponseWrapper<String> responseWrapper) {
                        if (ResetPasswordPresenter.this.mView == null) {
                            return;
                        }
                        if ((responseWrapper.getCode() == 200) && (ResetPasswordPresenter.this.mView != null)) {
                            if (ResetPasswordPresenter.this.mView != null) {
                                ResetPasswordPresenter.this.mView.showChangePasswordSuccess("修改密码成功！");
                            }
                        } else if ((responseWrapper.getCode() == 400) && (ResetPasswordPresenter.this.mView != null)) {
                            if (ResetPasswordPresenter.this.mView != null) {
                                ResetPasswordPresenter.this.mView.showChangePasswordSuccess((String) responseWrapper.getMsg());
                            }
                        } else if (ResetPasswordPresenter.this.mView != null) {
                            ResetPasswordPresenter.this.mView.showFail("修改密码失败！");
                        }
                    }
                });
    }

}