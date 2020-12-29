package com.baolong.obd.analysis.presenter;

import com.baolong.obd.analysis.AnalysisApis;
import com.baolong.obd.analysis.contract.DataContract;
import com.baolong.obd.analysis.data.entity.DataModel;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.common.network.ResponseWrapperList;
import com.baolong.obd.common.network.RetrofitManager;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DataPresenter implements DataContract.Presenter {
    private DataContract.View mView;

    public DataPresenter(DataContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    @Override
    public void dropView() {
        this.mView = null;
    }

    @Override
    public void getData( List<FilterCategoryModel> filterCategoryModelList) {
        if (this.mView != null) {
            this.mView.showLoading();
        }

        String dwbh = null;
        String beginTime = null;
        String endTime = null;

        /*if (filterCategoryModelList != null) {
            final FilterDwbhParams SetParters = new FilterDwbhParams(filterCategoryModelList).init();
            dwbh = SetParters.getDwbh();
            beginTime = SetParters.getBeginTime();
            endTime = SetParters.getEndTime();
        }*/


        ((AnalysisApis.GEtAnalysisData) RetrofitManager.getInstance()
                .createReq(AnalysisApis.GEtAnalysisData.class))
                .req( dwbh, beginTime, endTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperList<DataModel>>() {
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
                            if (DataPresenter.this.mView != null) {
                                DataPresenter.this.mView.showFail("统计数据查询失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperList<DataModel> paramAnonymousResponseWrapper) {
//                        if (CarNumPresenter.this.mView == null) {
//                            return;
//                        }
                        //CarNumPresenter.this.mView.hideLoading();
                        if (DataPresenter.this.mView != null && paramAnonymousResponseWrapper.getCode() == 200) {
                            DataPresenter.this.mView.hideLoading();
                            DataPresenter.this.mView.setData((List<DataModel>) paramAnonymousResponseWrapper.getData());
                        } else if (DataPresenter.this.mView != null) {
                            DataPresenter.this.mView.hideLoading();
                            DataPresenter.this.mView.showFail("统计数据查询失败！");
                        }

                    }
                });
    }


    @Override
    public void takeView(DataContract.View paramView) {
    }
}

