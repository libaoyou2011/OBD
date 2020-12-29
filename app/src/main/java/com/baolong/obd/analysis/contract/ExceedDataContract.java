package com.baolong.obd.analysis.contract;


import com.baolong.obd.analysis.data.entity.DataModel;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;

import java.util.List;

public abstract interface ExceedDataContract {

    public static abstract interface Presenter extends BasePresenter<ExceedDataContract.View> {

        public abstract void getNoData(List<FilterCategoryModel> list);

        public abstract void getCoData(List<FilterCategoryModel> list);

        public abstract void getHcData(List<FilterCategoryModel> list);

    }

    public static abstract interface View extends BaseView<ExceedDataContract.Presenter> {

        public abstract void hideLoading();

        public abstract void setNoData(List<DataModel> paramListExhaust);

        public abstract void setCoData(List<DataModel> paramListExhaust);

        public abstract void setHcData(List<DataModel> paramListExhaust);

        public abstract void showFail(String paramString);

        public abstract void showLoading();
    }
}
