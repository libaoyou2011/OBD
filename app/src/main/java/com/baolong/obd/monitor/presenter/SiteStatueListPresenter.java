package com.baolong.obd.monitor.presenter;

import com.baolong.obd.common.network.ResponseWrapperList;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.monitor.MonitorApis.GetWarningSites;
import com.baolong.obd.monitor.contract.SiteStatueListContract;
import com.baolong.obd.monitor.data.entity.SiteInfoItemV3;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SiteStatueListPresenter implements SiteStatueListContract.Presenter {
    private SiteStatueListContract.View mView;

    public SiteStatueListPresenter(SiteStatueListContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    public void getWarningData() {
        ((GetWarningSites) RetrofitManager.getInstance().createReq(GetWarningSites.class))
                .req()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperList<SiteInfoItemV3>>() {
                    public void onCompleted() {
                    }

                    public void onError(Throwable t) {
                        try {
                            t.printStackTrace();
                            if (SiteStatueListPresenter.this.mView != null) {
                                SiteStatueListPresenter.this.mView.showFail("设备预警列表数据查询失败！");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    public void onNext(ResponseWrapperList<SiteInfoItemV3> paramResponseWrapperList) {
                        if (SiteStatueListPresenter.this.mView == null) {
                            return;
                        }
                        if ((paramResponseWrapperList.getCode() == 200)) {
                            SiteStatueListPresenter.this.mView.showWarningSites((List<SiteInfoItemV3>) paramResponseWrapperList.getRows());
                            return;
                        }
                        SiteStatueListPresenter.this.mView.showFail("设备预警列表数据查询失败！");
                    }
                });
    }

    public void takeView(SiteStatueListContract.View paramView) {
    }

    public void dropView() {
        this.mView = null;
    }
}

