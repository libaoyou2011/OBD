package com.baolong.obd.blackcar.contract;


import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.blackcar.data.entity.ExhaustZC;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.blackcar.presenter.BlackCarListPresenter;
import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;

import java.util.List;

public abstract interface ProcessListContract {

    public static abstract interface Presenter extends BasePresenter<ProcessListContract.View> {

        public abstract void getDshData(String pdjg, int pageSize, int pageNum, String orderByColumn, String isAsc, List<FilterCategoryModel> list);

        public abstract void getYshData(String pdjg, String sfsh, int pageSize, int pageNum, String orderByColumn, String isAsc, List<FilterCategoryModel> list);

        public abstract void getAllData(int pageSize, int pageNum, String orderByColumn, String isAsc, List<FilterCategoryModel> list);

        public abstract void getZcListData(String pdjg, int pageSize, int pageNum, String orderByColumn, String isAsc, List<FilterCategoryModel> list);

    }

    public static abstract interface View extends BaseView<BlackCarListPresenter> {

        public abstract void hideLoading();

        public abstract void setData(List<Exhaust> paramListExhaust);

        public abstract void setZcData(List<ExhaustZC> paramListExhaust, int total);

        public abstract void showFail(String paramString);

        public abstract void showLoading();
    }
}
