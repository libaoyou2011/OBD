package com.baolong.obd.blackcar.presenter;

import com.baolong.obd.blackcar.BlackCarApis;
import com.baolong.obd.blackcar.contract.VehicleInfoContract;
import com.baolong.obd.blackcar.data.entity.ResponseVehicleInfoListModel;
import com.baolong.obd.blackcar.data.entity.VehicleInfo;
import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.common.utils.LogUtil;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class VehicleInfoPresenter implements VehicleInfoContract.Presenter {
    private static final String TAG = VehicleInfoPresenter.class.getSimpleName();
    private VehicleInfoContract.View mView;

    public VehicleInfoPresenter(VehicleInfoContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    public void takeView(VehicleInfoContract.View paramView) {
        this.mView = paramView;
    }

    public void dropView() {
        this.mView = null;
    }

    public void getVehicleInfo(String paramString1, String paramString2) {
        ((BlackCarApis.GetVehicleBasicInfo) RetrofitManager.getInstance().createReq(BlackCarApis.GetVehicleBasicInfo.class))
                .req(paramString1, paramString2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapper<ResponseVehicleInfoListModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        try {
                            throwable.printStackTrace();
                            if (VehicleInfoPresenter.this.mView != null) {
                                VehicleInfoPresenter.this.mView.showFail("车辆信息查询失败!");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapper<ResponseVehicleInfoListModel> paramResponseWrapper) {
                        if (VehicleInfoPresenter.this.mView == null) {
                            return;
                        }
                        if ((paramResponseWrapper.getCode() == 200) && (VehicleInfoPresenter.this.mView != null)) {
                            List<VehicleInfo> vehicleInfoList = paramResponseWrapper.getData().getRows();
                            if (vehicleInfoList != null && vehicleInfoList.size() > 0) {
                                LogUtil.i(TAG, "查询到车辆信息条数：" + vehicleInfoList.size());
                                VehicleInfoPresenter.this.mView.showVehicleInfo(vehicleInfoList.get(0));
                                return;
                            }
                        }
                        VehicleInfoPresenter.this.mView.showFail("未查询到车辆信息!");
                    }
                });
    }

}
