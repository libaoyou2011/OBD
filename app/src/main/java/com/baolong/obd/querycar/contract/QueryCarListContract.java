package com.baolong.obd.querycar.contract;

import com.baolong.obd.blackcar.data.entity.HuanjianModel;
import com.baolong.obd.blackcar.data.entity.ResponseExhaustListModel;
import com.baolong.obd.blackcar.data.entity.ResponseListModel;
import com.baolong.obd.blackcar.data.entity.VehicleInfo;
import com.baolong.obd.querycar.presenter.QueryCarListPresenter;
import com.baolong.obd.common.presenter.BaseView;
import com.baolong.obd.common.presenter.BasePresenter;

public interface QueryCarListContract {

    public interface Presenter extends BasePresenter<View> {
        // 车辆基本信息
        void getVehicleBasicInfo(final String p0, final String p1);

        // 车辆检验(年检/环检)
        void getJyListData(int p0, int p1, final String p2, final String p3, final String p4, final String p5);

        //车辆遥测记录
        void getYcListData(int p0, int p1, int p2, final String p3, final String p4, final String p5, final String p6);

        //车辆超标记录
        void getCbListData(int p0, int p1, int p2, final String p3, final String p4, final String p5, final String p6);
    }

    public interface View extends BaseView<QueryCarListPresenter> {
        void hideLoading();

        void showFail(final String p0);

        void showLoading();

        void showCarInfo(final VehicleInfo p0);

        void showJyListData(final ResponseListModel<HuanjianModel> p0);

        void showYcListData(final ResponseExhaustListModel p0);

        void showCbListData(final ResponseExhaustListModel p0);

    }
}

