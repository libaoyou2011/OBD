package com.baolong.obd.blackcar.contract;

import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.blackcar.presenter.FilterListPresenter;
import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;

import java.util.List;

public abstract interface FilterActivityContract {

    public static abstract interface Presenter extends BasePresenter<FilterActivityContract.View> {

        public abstract void getBlackFilterListAll();

        public abstract void getExecutionFilterListAll();

    }

    public static abstract interface View extends BaseView<FilterListPresenter> {
        public abstract void hideLoading();

        public abstract void showFail(String paramString);

        public abstract void showLoading();

        public abstract void showBlackFilterListAll(List<FilterCategoryModel> paramList);

        public abstract void showExecutionFilterListAll(List<FilterCategoryModel> paramList);

    }
}