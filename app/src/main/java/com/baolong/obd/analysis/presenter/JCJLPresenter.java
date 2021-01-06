package com.baolong.obd.analysis.presenter;

import com.baolong.obd.analysis.AnalysisApis;
import com.baolong.obd.analysis.contract.JCJLContract;
import com.baolong.obd.analysis.data.entity.JCJLModel;
import com.baolong.obd.common.network.ResponseWrapperListOld;
import com.baolong.obd.common.network.RetrofitManager;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class JCJLPresenter implements JCJLContract.Presenter {
    private JCJLContract.View mView;

    public JCJLPresenter(JCJLContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    @Override
    public void dropView() {
        this.mView = null;
    }

    @Override
    public void getData(String yearxz, String hphm, String beginTime, String endTime) {
        if (this.mView != null) {
            this.mView.showLoading();
        }

        ((AnalysisApis.GEtAnalysisJCJL) RetrofitManager.getInstance().createReq(AnalysisApis.GEtAnalysisJCJL.class))
                .req(yearxz, hphm, beginTime, endTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperListOld<JCJLModel>>() {
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
                            if (JCJLPresenter.this.mView != null) {
                                JCJLPresenter.this.mView.showFail("统计数据查询失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperListOld<JCJLModel> paramAnonymousResponseWrapper) {
//                        if (CarNumPresenter.this.mView == null) {
//                            return;
//                        }
                        //CarNumPresenter.this.mView.hideLoading();
                        if (JCJLPresenter.this.mView != null && paramAnonymousResponseWrapper.getCode() == 200) {
                            JCJLPresenter.this.mView.hideLoading();
                            JCJLPresenter.this.mView.setData((List<JCJLModel>) paramAnonymousResponseWrapper.getData());
                        } else if (JCJLPresenter.this.mView != null) {
                            JCJLPresenter.this.mView.hideLoading();
                            JCJLPresenter.this.mView.showFail("统计数据查询失败！");
                        }

                    }
                });
    }


    @Override
    public void takeView(JCJLContract.View paramView) {
    }

}

