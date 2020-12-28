package com.baolong.obd.monitor.contract;

import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;
import com.baolong.obd.monitor.data.entity.SiteInfoItemV3;
import com.baolong.obd.monitor.presenter.SiteStatueListPresenter;

import java.util.List;

public abstract interface SiteStatueListContract {

    public static abstract interface Presenter extends BasePresenter<SiteStatueListContract.View> {

        public abstract void getWarningData();
    }

    public static abstract interface View extends BaseView<SiteStatueListPresenter> {

        public abstract void showLoading();

        public abstract void hideLoading();

        public abstract void showFail(String paramString);

        public abstract void showWarningSites(List<SiteInfoItemV3> paramList);
    }
}
