package com.baolong.obd.monitor.contract;

import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;
import com.baolong.obd.monitor.data.entity.GetTodayAmountModel2;
import com.baolong.obd.monitor.data.entity.SiteAqiInfoItemV3;
import com.baolong.obd.monitor.data.entity.SiteInfoItemV3;
import com.baolong.obd.monitor.presenter.MonitorMainPresenter;

import java.util.List;

import okhttp3.RequestBody;

public abstract interface MonitorMainContract {

    public static abstract interface Presenter extends BasePresenter<MonitorMainContract.View> {

        public abstract void getTodayAmount();

        public abstract void getSiteListAll();

        public abstract void getAqiListAll();

        public abstract void getSiteInfoItemCount(RequestBody requestBody);
    }

    public static abstract interface View extends BaseView<MonitorMainPresenter> {

        public abstract void hideLoading();

        public abstract void showLoading();

        public abstract void showFail(String paramString);

        public abstract void showTodayAmount(String paramString1, String paramString2, String paramString3);

        public abstract void showSiteListAll(List<SiteInfoItemV3> paramList);

        public abstract void showAqiListAll(List<SiteAqiInfoItemV3> paramList);

        public abstract void showSitInfoItemCount(GetTodayAmountModel2 paramGetTodayAmountModel2);
    }
}
