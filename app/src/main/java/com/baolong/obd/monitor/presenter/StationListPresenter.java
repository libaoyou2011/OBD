package com.baolong.obd.monitor.presenter;

import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.common.network.ResponseWrapperList;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.monitor.MonitorApis;
import com.baolong.obd.monitor.contract.StationListContract;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class StationListPresenter implements StationListContract.Presenter {
    private StationListContract.View mView;

    public StationListPresenter(StationListContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    @Override
    public void takeView(StationListContract.View paramView) {
    }

    @Override
    public void getTodayData(String dwbh, String pdjg, int pageSize, int pageNum, String orderByColumn, String isAsc) {
        ((MonitorApis.GetMonitoringDataList) RetrofitManager.getInstance()
                .createReq(MonitorApis.GetMonitoringDataList.class))
                .req(dwbh, pdjg, pageSize, pageNum, orderByColumn, isAsc)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperList<Exhaust>>() {

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable paramAnonymousThrowable) {
                        try {
                            if (StationListPresenter.this.mView != null) {
                                StationListPresenter.this.mView.showFail("列表数据查询失败！");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperList<Exhaust> wrapperList) {
                        if (StationListPresenter.this.mView == null) {
                            return;
                        }
                        if (wrapperList.getCode() == 200) {
                            StationListPresenter.this.mView.showTodayData((List<Exhaust>) wrapperList.getRows());
                            return;
                        }
                        StationListPresenter.this.mView.showFail("列表数据查询失败！");
                    }
                });
    }

    @Override
    public void dropView() {
        this.mView = null;
    }

}