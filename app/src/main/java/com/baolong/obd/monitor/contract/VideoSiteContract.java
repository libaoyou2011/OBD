package com.baolong.obd.monitor.contract;

import com.baolong.obd.blackcar.data.entity.SiteVideoInfo;
import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;

public abstract interface VideoSiteContract {

    public static abstract interface Presenter extends BasePresenter<VideoSiteContract.View> {

        public abstract void getData(String paramString1);
    }

    public static abstract interface View extends BaseView<VideoSiteContract.Presenter> {

        public abstract void showLoading();

        public abstract void hideLoading();

        public abstract void setData(SiteVideoInfo paramSiteVideoInfo);

        public abstract void showFail(String paramString);
    }
}