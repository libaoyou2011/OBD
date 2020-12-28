package com.baolong.obd.blackcar.presenter;

import com.baolong.obd.blackcar.BlackCarApis;
import com.baolong.obd.blackcar.contract.BlackDetailContract;
import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.RetrofitManager;

import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BlackDetailPresenter implements BlackDetailContract.Presenter {
    private BlackDetailContract.View mView;

    public BlackDetailPresenter(BlackDetailContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    public void takeView(BlackDetailContract.View paramView) {
    }

    public void dropView() {
        this.mView = null;
    }

    public void addSh(RequestBody requestBody) {
        ((BlackCarApis.AddSh) RetrofitManager.getInstance().createReq(BlackCarApis.AddSh.class))
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
                            if (BlackDetailPresenter.this.mView != null) {
                                BlackDetailPresenter.this.mView.showFail("黑烟车审核失败!");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapper<String> paramAnonymousResponseWrapper) {
                        if (BlackDetailPresenter.this.mView == null) {
                            return;
                        }
                        if ((paramAnonymousResponseWrapper.getCode() == 0) && (BlackDetailPresenter.this.mView != null)) {
                            BlackDetailPresenter.this.mView.showAddSh(paramAnonymousResponseWrapper.getMsg());
                            return;
                        }
                        BlackDetailPresenter.this.mView.showFail("黑烟车审核失败!");
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
