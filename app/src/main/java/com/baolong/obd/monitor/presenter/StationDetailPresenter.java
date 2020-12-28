package com.baolong.obd.monitor.presenter;

import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.execution.ExecutionApis;
import com.baolong.obd.monitor.contract.StationDetailContract;

import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class StationDetailPresenter implements StationDetailContract.Presenter {
    private StationDetailContract.View mView;

    public StationDetailPresenter(StationDetailContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    public void takeView(StationDetailContract.View paramView) {
    }

    public void dropView() {
        this.mView = null;
    }

    public void addPunish(RequestBody requestBody) {
        ((ExecutionApis.AddPunish) RetrofitManager.getInstance().createReq(ExecutionApis.AddPunish.class))
                .req(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapper<String>>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        try {
                            throwable.printStackTrace();
                            if (StationDetailPresenter.this.mView != null) {
                                StationDetailPresenter.this.mView.showFail("超标车处罚失败!");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapper<String> paramAnonymousResponseWrapper) {
                        if (StationDetailPresenter.this.mView == null) {
                            return;
                        }
                        if (paramAnonymousResponseWrapper.getCode() == 200) {
                            StationDetailPresenter.this.mView.showAddCfSuccess("超标车辆处罚成功");
                            return;
                        }
                        StationDetailPresenter.this.mView.showFail("超标车处罚失败!");
                    }
                });
    }

//    public void getWarningData(String paramString1, String paramString2, String paramString3, String paramString4) {
//        ((MonitorApis.GetMonitoringDataDetailNew) RetrofitManager.getInstance()
//                .createReq(MonitorApis.GetMonitoringDataDetailNew.class))
//                .req(paramString1, paramString2, paramString3, paramString4)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ResponseWrapperList<GetMonitoringDataDetailNewModel>>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        try {
//                            if (StationDetailPresenter.this.mView != null) {
//                                StationDetailPresenter.this.mView.showFail("详情数据查询失败");
//                            }
//                            throwable.printStackTrace();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onNext(ResponseWrapperList<GetMonitoringDataDetailNewModel> paramAnonymousResponseWrapperList) {
//                        if (StationDetailPresenter.this.mView == null) {
//                            return;
//                        }
//                        if ((paramAnonymousResponseWrapperList.getCode() == 200) && (StationDetailPresenter.this.mView != null)) {
//                            StationDetailPresenter.this.mView.showWarmingSuccess((GetMonitoringDataDetailNewModel) paramAnonymousResponseWrapperList.getWarningData().get(0));
//                            return;
//                        }
//                        StationDetailPresenter.this.mView.showFail("详情数据查询失败");
//                    }
//                });
//    }

}
