package com.baolong.obd.analysis.presenter;

import com.baolong.obd.analysis.AnalysisApis;
import com.baolong.obd.analysis.contract.CarNumContract;
import com.baolong.obd.analysis.data.entity.CarNumModel;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;

import com.baolong.obd.common.network.ResponseWrapperList;
import com.baolong.obd.common.network.RetrofitManager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CarNumPresenter implements CarNumContract.Presenter {
    private CarNumContract.View mView;

    public CarNumPresenter(CarNumContract.View paramView) {
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
        String cpys = null;
        String siteInfo$hphm = null;
        String placeBelong = null;
        String rlzl = null;
        String jsdstatus = null;
        BigDecimal cljsd = null;
        String clsdstatus = null;
        BigDecimal clsd = null;
        String lgmhdstatus = null;
        Integer lgmhd = null;
        String judge = null;
        String number = null;
        String beginTime = null;
        String endTime = null;

        /*if (filterCategoryModelList != null) {
            final FilterDwbhParams SetParters = new FilterDwbhParams(filterCategoryModelList).init();
            dwbh = SetParters.getDwbh();
//            cpys = SetParters.getCpys();
//            siteInfo$hphm = SetParters.getSiteInfo$hphm();
//            placeBelong = SetParters.getPlaceBelong();
//            rlzl = SetParters.getRlzl();
//            jsdstatus = SetParters.getJsdstatus();
//            cljsd = SetParters.getCljsd();
//            clsdstatus = SetParters.getClsdstatus();
//            clsd = SetParters.getClsd();
//            lgmhdstatus = SetParters.getLgmhdstatus();
//            lgmhd = SetParters.getLgmhd();
//            judge = SetParters.getJudge();
//            number = SetParters.getNumber();
            beginTime = SetParters.getBeginTime();
            endTime = SetParters.getEndTime();
        }*/


        ((AnalysisApis.GEtAnalysisCarNum) RetrofitManager.getInstance()
                .createReq(AnalysisApis.GEtAnalysisCarNum.class))
                .req( dwbh,
//                        cpys, siteInfo$hphm, placeBelong, rlzl,
//                        jsdstatus, cljsd, clsdstatus, clsd, lgmhdstatus, lgmhd,
//                        judge, number,
                        beginTime, endTime).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperList<CarNumModel>>() {
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
                            if (CarNumPresenter.this.mView != null) {
                                CarNumPresenter.this.mView.showFail("统计车牌查询失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperList<CarNumModel> paramAnonymousResponseWrapper) {
//                        if (CarNumPresenter.this.mView == null) {
//                            return;
//                        }
                        //CarNumPresenter.this.mView.hideLoading();
                        if (CarNumPresenter.this.mView != null && paramAnonymousResponseWrapper.getCode() == 200) {
                            CarNumPresenter.this.mView.hideLoading();
                            CarNumPresenter.this.mView.setmData((List<CarNumModel>) paramAnonymousResponseWrapper.getData());
                        } else if (CarNumPresenter.this.mView != null) {
                            CarNumPresenter.this.mView.hideLoading();
                            CarNumPresenter.this.mView.showFail("统计车牌查询失败！");
                        }

                    }
                });
    }


    @Override
    public void takeView(CarNumContract.View paramView) {
    }
}

