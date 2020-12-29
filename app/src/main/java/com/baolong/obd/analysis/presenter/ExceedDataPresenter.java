package com.baolong.obd.analysis.presenter;

import com.baolong.obd.analysis.AnalysisApis;
import com.baolong.obd.analysis.contract.DataContract;
import com.baolong.obd.analysis.contract.ExceedDataContract;
import com.baolong.obd.analysis.data.entity.DataModel;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
//import com.baolong.obd.blackcar.data.entity.FilterDwbhParams;
import com.baolong.obd.common.network.ResponseWrapperList;
import com.baolong.obd.common.network.RetrofitManager;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ExceedDataPresenter implements ExceedDataContract.Presenter {
    private ExceedDataContract.View mView;

    public ExceedDataPresenter(ExceedDataContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    @Override
    public void dropView() {
        this.mView = null;
    }

    @Override
    public void getNoData( List<FilterCategoryModel> filterCategoryModelList) {
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


        ((AnalysisApis.GEtAnalysisNoExceed) RetrofitManager.getInstance()
                .createReq(AnalysisApis.GEtAnalysisNoExceed.class))
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
                            if (ExceedDataPresenter.this.mView != null) {
                                ExceedDataPresenter.this.mView.showFail("NO超标数据统计查询失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperList<DataModel> paramAnonymousResponseWrapper) {
//                        if (CarNumPresenter.this.mView == null) {
//                            return;
//                        }
                        //CarNumPresenter.this.mView.hideLoading();
                        if (ExceedDataPresenter.this.mView != null && paramAnonymousResponseWrapper.getCode() == 200) {
                            ExceedDataPresenter.this.mView.hideLoading();
                            ExceedDataPresenter.this.mView.setNoData((List<DataModel>) paramAnonymousResponseWrapper.getData());
                        } else if (ExceedDataPresenter.this.mView != null) {
                            ExceedDataPresenter.this.mView.hideLoading();
                            ExceedDataPresenter.this.mView.showFail("NO超标数据统计查询失败！");
                        }

                    }
                });
    }

    @Override
    public void getCoData( List<FilterCategoryModel> filterCategoryModelList) {
        if (this.mView != null) {
            this.mView.showLoading();
        }

        String dwbh = null;
        String beginTime = null;
        String endTime = null;

       /* if (filterCategoryModelList != null) {
            final FilterDwbhParams SetParters = new FilterDwbhParams(filterCategoryModelList).init();
            dwbh = SetParters.getDwbh();
            beginTime = SetParters.getBeginTime();
            endTime = SetParters.getEndTime();
        }*/


        ((AnalysisApis.GEtAnalysisCoExceed) RetrofitManager.getInstance()
                .createReq(AnalysisApis.GEtAnalysisCoExceed.class))
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
                            if (ExceedDataPresenter.this.mView != null) {
                                ExceedDataPresenter.this.mView.showFail("CO超标数据统计查询失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperList<DataModel> paramAnonymousResponseWrapper) {
//                        if (CarNumPresenter.this.mView == null) {
//                            return;
//                        }
                        //CarNumPresenter.this.mView.hideLoading();
                        if (ExceedDataPresenter.this.mView != null && paramAnonymousResponseWrapper.getCode() == 200) {
                            ExceedDataPresenter.this.mView.hideLoading();
                            ExceedDataPresenter.this.mView.setCoData((List<DataModel>) paramAnonymousResponseWrapper.getData());
                        } else if (ExceedDataPresenter.this.mView != null) {
                            ExceedDataPresenter.this.mView.hideLoading();
                            ExceedDataPresenter.this.mView.showFail("CO超标数据统计查询失败！");
                        }

                    }
                });
    }

    @Override
    public void getHcData( List<FilterCategoryModel> filterCategoryModelList) {
        if (this.mView != null) {
            this.mView.showLoading();
        }

        String dwbh = null;
        String beginTime = null;
        String endTime = null;

       /* if (filterCategoryModelList != null) {
            final FilterDwbhParams SetParters = new FilterDwbhParams(filterCategoryModelList).init();
            dwbh = SetParters.getDwbh();
            beginTime = SetParters.getBeginTime();
            endTime = SetParters.getEndTime();
        }*/


        ((AnalysisApis.GEtAnalysisHcExceed) RetrofitManager.getInstance()
                .createReq(AnalysisApis.GEtAnalysisHcExceed.class))
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
                            if (ExceedDataPresenter.this.mView != null) {
                                ExceedDataPresenter.this.mView.showFail("HC超标数据统计查询失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperList<DataModel> paramAnonymousResponseWrapper) {
//                        if (CarNumPresenter.this.mView == null) {
//                            return;
//                        }
                        //CarNumPresenter.this.mView.hideLoading();
                        if (ExceedDataPresenter.this.mView != null && paramAnonymousResponseWrapper.getCode() == 200) {
                            ExceedDataPresenter.this.mView.hideLoading();
                            ExceedDataPresenter.this.mView.setHcData((List<DataModel>) paramAnonymousResponseWrapper.getData());
                        } else if (ExceedDataPresenter.this.mView != null) {
                            ExceedDataPresenter.this.mView.hideLoading();
                            ExceedDataPresenter.this.mView.showFail("HC超标数据统计查询失败！");
                        }

                    }
                });
    }


    @Override
    public void takeView(ExceedDataContract.View paramView) {
    }
}

