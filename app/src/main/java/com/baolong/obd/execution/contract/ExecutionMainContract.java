package com.baolong.obd.execution.contract;

import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;
import com.baolong.obd.execution.data.entity.GetStatisticCountModel;
import com.baolong.obd.execution.presenter.ExecutionMainPresenter;

public abstract interface ExecutionMainContract {

    public static abstract interface Presenter extends BasePresenter<ExecutionMainContract.View> {
        public abstract void getData();
    }

    public static abstract interface View extends BaseView<ExecutionMainPresenter> {

        public abstract void hideLoading();

        public abstract void setData(GetStatisticCountModel paramGetStatisticalExecutionResponseModel);

        public abstract void showFail(String paramString);

        public abstract void showLoading();
    }
}
