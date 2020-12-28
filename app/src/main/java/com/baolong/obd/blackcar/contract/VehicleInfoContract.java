package com.baolong.obd.blackcar.contract;

import com.baolong.obd.blackcar.data.entity.VehicleInfo;
import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;

/**
 * 请求车辆信息
 */
public abstract interface VehicleInfoContract {

    public static abstract interface Presenter extends BasePresenter<VehicleInfoContract.View> {

        public abstract void getVehicleInfo(String paramString1, String paramString2);

    }

    public static abstract interface View extends BaseView<VehicleInfoContract.Presenter> {

        public abstract void showLoading();

        public abstract void hideLoading();

        public abstract void showVehicleInfo(VehicleInfo paramVehicleInfo);

        public abstract void showFail(String paramString);
    }
}
