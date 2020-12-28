package com.baolong.obd.my.contract;


import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;

public abstract interface ResetPasswordContract {

    public static abstract interface Presenter extends BasePresenter<ResetPasswordContract.View> {

        public abstract void setChangePassword(String oldPassWord, String newPassword);
    }

    public static abstract interface View extends BaseView<ResetPasswordContract.Presenter> {

        public abstract void hideLoading();

        public abstract void showLoading();

        public abstract void showFail(String paramString);

        public abstract void showChangePasswordSuccess(String paramString);

    }
}
