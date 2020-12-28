package com.baolong.obd.common.update.contract;

import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;
import com.baolong.obd.common.update.model.CheckVersionResponseModel;
import com.baolong.obd.common.update.presenter.CheckVersionPresenter;

public abstract interface ICheckVersionContract {

    public static abstract interface Presenter
            extends BasePresenter<ICheckVersionContract.View> {
        public abstract void getData(String paramString);
    }

    public static abstract interface View
            extends BaseView<CheckVersionPresenter> {
        public abstract void showData(CheckVersionResponseModel paramCheckVersionResponseModel);
    }
}
