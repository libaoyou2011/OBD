package com.baolong.obd.analysis.presenter;

import com.baolong.obd.analysis.AnalysisApis;
import com.baolong.obd.analysis.contract.CQJRContract;
import com.baolong.obd.analysis.data.entity.CQJRModel;

import com.baolong.obd.common.network.ResponseWrapperListOld;
import com.baolong.obd.common.network.RetrofitManager;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CQJRPresenter implements CQJRContract.Presenter {
    private CQJRContract.View mView;

    public CQJRPresenter(CQJRContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    @Override
    public void dropView() {
        this.mView = null;
    }

    @Override
    public void getData(String type) {
        if (this.mView != null) {
            this.mView.showLoading();
        }

        ((AnalysisApis.GEtAnalysisCQJR) RetrofitManager.getInstance().createReq(AnalysisApis.GEtAnalysisCQJR.class))
                .req(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperListOld<CQJRModel>>() {
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
                            if (CQJRPresenter.this.mView != null) {
                                CQJRPresenter.this.mView.showFail("统计数据查询失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperListOld<CQJRModel> paramAnonymousResponseWrapper) {
//                        if (CarNumPresenter.this.mView == null) {
//                            return;
//                        }
                        //CarNumPresenter.this.mView.hideLoading();
                        if (CQJRPresenter.this.mView != null && paramAnonymousResponseWrapper.getCode() == 200) {
                            CQJRPresenter.this.mView.hideLoading();
                            CQJRPresenter.this.mView.setData((List<CQJRModel>) paramAnonymousResponseWrapper.getData());
                        } else if (CQJRPresenter.this.mView != null) {
                            CQJRPresenter.this.mView.hideLoading();
                            CQJRPresenter.this.mView.showFail("统计数据查询失败！");
                        }

                    }
                });
    }


    @Override
    public void takeView(CQJRContract.View paramView) {
    }
}

