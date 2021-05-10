package com.baolong.obd.common.update.contract;

import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;
import com.baolong.obd.common.update.model.CheckVersionResponseModel;

public abstract interface ICheckVersionContract {

    public static abstract interface Presenter extends BasePresenter<View> {
        public abstract void getData(String platform, String category, String versionCode);
    }

    public static abstract interface View extends BaseView<Presenter> {
        public abstract void showData(CheckVersionResponseModel paramCheckVersionResponseModel);
    }

}
