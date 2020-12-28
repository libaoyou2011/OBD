package com.baolong.obd.blackcar.contract;

import com.baolong.obd.blackcar.presenter.BlackDetailPresenter;
import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;

import okhttp3.RequestBody;

public abstract interface BlackDetailContract {

    public static abstract interface Presenter extends BasePresenter<BlackDetailContract.View> {

        public abstract void addSh(RequestBody requestBody);

    }

    public static abstract interface View extends BaseView<BlackDetailPresenter> {

        public abstract void hideLoading();


        public abstract void showAddSh(String paramString);

        public abstract void showFail(String paramString);

        public abstract void showLoading();
    }
}
