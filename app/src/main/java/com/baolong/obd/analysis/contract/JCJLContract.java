package com.baolong.obd.analysis.contract;

import com.baolong.obd.analysis.data.entity.JCJLModel;
import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;

import java.util.List;

public abstract interface JCJLContract {

    public static abstract interface Presenter extends BasePresenter<JCJLContract.View> {
        public abstract void getData(String yearxz, String hphm, String beginTime, String endTime);
    }

    public static abstract interface View extends BaseView<JCJLContract.Presenter> {

        public abstract void hideLoading();

        public abstract void setData(List<JCJLModel> models);

        public abstract void showFail(String paramString);

        public abstract void showLoading();
    }
}
