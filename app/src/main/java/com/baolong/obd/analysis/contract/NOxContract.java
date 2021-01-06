package com.baolong.obd.analysis.contract;


import com.baolong.obd.analysis.data.entity.NOxModel;
import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;

import java.util.List;

public abstract interface NOxContract {

    public static abstract interface Presenter extends BasePresenter<NOxContract.View> {
        public abstract void getData(String hphm, String beginTime, String endTime);
    }

    public static abstract interface View extends BaseView<NOxContract.Presenter> {

        public abstract void hideLoading();

        public abstract void setData(List<NOxModel> paramList);

        public abstract void showFail(String paramString);

        public abstract void showLoading();
    }
}
