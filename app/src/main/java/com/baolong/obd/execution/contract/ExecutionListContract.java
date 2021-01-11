package com.baolong.obd.execution.contract;

import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;
import com.baolong.obd.execution.data.entity.OBDCar;
import com.baolong.obd.execution.presenter.ExecutionListPresenter;

import java.util.List;

public abstract interface ExecutionListContract {

    //Presenter
    public static abstract interface Presenter extends BasePresenter<ExecutionListContract.View> {
        public abstract void getWcfData(String pdjg, String sfcf, int pageSize, int pageNum, String orderByColumn, String isAsc, List<FilterCategoryModel> filterCategoryModelList);

        public abstract void getYcfData(String pdjg, String sfcf, int pageSize, int pageNum, String orderByColumn, String isAsc, List<FilterCategoryModel> filterCategoryModelList);

        public abstract void getOverproofData(String pdjg, String sfcf, int pageSize, int pageNum, String orderByColumn, String isAsc, List<FilterCategoryModel> filterCategoryModelList);

        public abstract void getTelemetryData(int pageSize, int pageNum, String orderByColumn, String isAsc, List<FilterCategoryModel> filterCategoryModelList);

        public abstract void getOBDCarData(int pageSize, int pageNum, String clzt, String hphm, List<FilterCategoryModel> filterCategoryModelList);

    }

    //View
    public static abstract interface View extends BaseView<ExecutionListPresenter> {

        public abstract void hideLoading();

        public abstract void setWcfData(List<Exhaust> exhaustList);

        public abstract void setYcfooAllData(List<Exhaust> exhaustList);

        public abstract void setTelemetryData(List<Exhaust> exhaustList);

        public abstract void setOBDCarData(List<OBDCar> exhaustList, int total);

        public abstract void showFail(String paramString);

        public abstract void showLoading();
    }
}


