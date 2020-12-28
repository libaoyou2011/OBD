package com.baolong.obd.querycar.contract;

import com.baolong.obd.blackcar.data.entity.VehicleInfo;
import com.baolong.obd.querycar.data.entity.GetVehicleQueryListResponseModel;

import java.util.List;

import com.baolong.obd.querycar.presenter.QueryCarMainPresenter;
import com.baolong.obd.common.presenter.BaseView;
import com.baolong.obd.common.presenter.BasePresenter;

public interface QueryCarMainContract {

    public interface Presenter extends BasePresenter<View> {
        void deleteVehicleQueryInfo(final String p0, final String p1, final String p2, final String p3, final String p4);

        void getVehicleBasicInfo(final String p0, final String p1);

        void getVehicleQueryList(final String p0, final String p1, final String p2, final String p3);

        void insertVehicleQuery(final String p0, final String p1, final String p2, final String p3, final String p4, final String p5);
    }

    public interface View extends BaseView<QueryCarMainPresenter> {
        void hideLoading();

        void refreshHistoryList();

        void showCarInfo(final VehicleInfo p0);

        void showFail(final String p0);

        void showLoading();

        void showQueryHistoryList(final List<GetVehicleQueryListResponseModel> p0);
    }
}
