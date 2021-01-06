package com.baolong.obd.analysis.contract;


import com.baolong.obd.analysis.data.entity.CQJRModel;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;

import java.util.List;

public abstract interface CQJRContract {

    public static abstract interface Presenter extends BasePresenter<CQJRContract.View> {
        public abstract void getData(String type);
    }

    public static abstract interface View extends BaseView<CQJRContract.Presenter> {

        public abstract void hideLoading();

        public abstract void setData(List<CQJRModel> paramListExhaust);

        public abstract void showFail(String paramString);

        public abstract void showLoading();
    }
}
