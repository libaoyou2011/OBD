package com.baolong.obd.my.contract;


import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;
import com.baolong.obd.my.data.entity.UserInfoModel;

import okhttp3.RequestBody;

public abstract interface UserInfoContract {

    public static abstract interface Presenter extends BasePresenter<UserInfoContract.View> {

        public abstract void getUserInfo();

        public abstract void saveUserInfo(RequestBody requestBody);
    }

    public static abstract interface View extends BaseView<UserInfoContract.Presenter> {

        public abstract void hideLoading();

        public abstract void showLoading();

        public abstract void showFail(String paramString);

        public abstract void showUserInfo(UserInfoModel paramUserInfoModel);

        public abstract void showSaveUserInfo(String paramString);

    }
}
