package com.baolong.obd.analysis.presenter;

import com.baolong.obd.analysis.AnalysisApis;
import com.baolong.obd.analysis.contract.NOxContract;
import com.baolong.obd.analysis.data.entity.NOxModel;
import com.baolong.obd.common.network.ResponseWrapperListOld;
import com.baolong.obd.common.network.RetrofitManager;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NOxPresenter implements NOxContract.Presenter {
    private NOxContract.View mView;

    public NOxPresenter(NOxContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    @Override
    public void dropView() {
        this.mView = null;
    }

    @Override
    public void getData(String hphm, String beginTime, String endTime) {
        if (this.mView != null) {
            this.mView.showLoading();
        }

        ((AnalysisApis.GEtAnalysisNOx) RetrofitManager.getInstance().createReq(AnalysisApis.GEtAnalysisNOx.class))
                .req(hphm, beginTime, endTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperListOld<NOxModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable paramAnonymousThrowable) {
                        try {
                            paramAnonymousThrowable.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (NOxPresenter.this.mView != null) {
                                NOxPresenter.this.mView.showFail("统计数据查询失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperListOld<NOxModel> paramAnonymousResponseWrapper) {
//                        if (CarNumPresenter.this.mView == null) {
//                            return;
//                        }
                        //CarNumPresenter.this.mView.hideLoading();
                        if (NOxPresenter.this.mView != null && paramAnonymousResponseWrapper.getCode() == 200) {
                            NOxPresenter.this.mView.hideLoading();
                            NOxPresenter.this.mView.setData((List<NOxModel>) paramAnonymousResponseWrapper.getData());
                        } else if (NOxPresenter.this.mView != null) {
                            NOxPresenter.this.mView.hideLoading();
                            NOxPresenter.this.mView.showFail("统计数据查询失败！");
                        }

                    }
                });
    }


    @Override
    public void takeView(NOxContract.View paramView) {
    }
}

