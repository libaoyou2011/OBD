package com.baolong.obd.analysis.contract;


import com.baolong.obd.analysis.data.entity.DataModel;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;

import java.util.List;

public abstract interface DataContract {

    public static abstract interface Presenter extends BasePresenter<DataContract.View> {
        public abstract void getData(List<FilterCategoryModel> list);
    }

    public static abstract interface View extends BaseView<DataContract.Presenter> {

        public abstract void hideLoading();

        public abstract void setData(List<DataModel> paramListExhaust);

        public abstract void showFail(String paramString);

        public abstract void showLoading();
    }
}
