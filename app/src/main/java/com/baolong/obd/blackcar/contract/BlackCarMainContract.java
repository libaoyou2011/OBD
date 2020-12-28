package com.baolong.obd.blackcar.contract;

import com.baolong.obd.blackcar.data.entity.BlackCountModel;
import com.baolong.obd.blackcar.presenter.BlackCarMainPresenter;
import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;

public abstract interface BlackCarMainContract {

    public static abstract interface Presenter extends BasePresenter<BlackCarMainContract.View> {
        public abstract void getData();
    }

    public static abstract interface View extends BaseView<BlackCarMainPresenter> {
        public abstract void hideLoading();

        public abstract void setData(BlackCountModel paramBlackCountModel);

        public abstract void showFail(String paramString);

        public abstract void showLoading();
    }
}
