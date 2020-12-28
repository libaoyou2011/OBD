package com.baolong.obd.monitor.contract;

import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;
import com.baolong.obd.monitor.presenter.StationDetailPresenter;

import okhttp3.RequestBody;

public abstract interface StationDetailContract {

    public static abstract interface Presenter extends BasePresenter<StationDetailContract.View> {

        public abstract void addPunish(RequestBody requestBody);

    }

    public static abstract interface View extends BaseView<StationDetailPresenter> {

        public abstract void hideLoading();


        public abstract void showAddCfSuccess(String paramString);

        public abstract void showFail(String paramString);

        public abstract void showLoading();
    }
}
