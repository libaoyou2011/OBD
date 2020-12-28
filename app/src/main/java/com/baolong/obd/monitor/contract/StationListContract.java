package com.baolong.obd.monitor.contract;

import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;
import com.baolong.obd.monitor.presenter.StationListPresenter;

import java.util.List;

public abstract interface StationListContract {

    public static abstract interface Presenter extends BasePresenter<StationListContract.View> {

        public abstract void getTodayData(String dwbh, String pdjg, int pageSize, int pageNum, String orderByColumn, String isAsc);
    }

    public static abstract interface View extends BaseView<StationListPresenter> {

        public abstract void showLoading();

        public abstract void hideLoading();

        public abstract void showFail(String paramString);

        public abstract void showTodayData(List<Exhaust> paramGetMonitoringDataListResponseModel);
    }
}