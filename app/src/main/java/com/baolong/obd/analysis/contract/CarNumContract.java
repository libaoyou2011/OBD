package com.baolong.obd.analysis.contract;


import com.baolong.obd.analysis.data.entity.CarNumModel;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;

import java.util.List;

public abstract interface CarNumContract {

    public static abstract interface Presenter extends BasePresenter<CarNumContract.View> {
        public abstract void getData(List<FilterCategoryModel> list);
    }

    public static abstract interface View extends BaseView<CarNumContract.Presenter> {

        public abstract void hideLoading();

        public abstract void setmData(List<CarNumModel> paramListExhaust);

        public abstract void showFail(String paramString);

        public abstract void showLoading();
    }
}
