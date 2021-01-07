package com.baolong.obd.blackcar.presenter;

import com.baolong.obd.blackcar.BlackCarApis;
import com.baolong.obd.blackcar.contract.ProcessListContract;
import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.blackcar.data.entity.ExhaustZC;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.blackcar.data.entity.FilterBlackParams;
import com.baolong.obd.blackcar.event.UpdateBlackSumEvent;
import com.baolong.obd.common.network.ResponseWrapperList;
import com.baolong.obd.common.network.RetrofitManager;
import com.hwangjr.rxbus.RxBus;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BlackCarListPresenter implements ProcessListContract.Presenter {
    private ProcessListContract.View mView;

    public BlackCarListPresenter(ProcessListContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    @Override
    public void getDshData(String pdjg1, int pageSize, int pageNum,
                           String orderByColumn, String isAsc, List<FilterCategoryModel> filterCategoryModelList) {
        if (this.mView != null) {
            this.mView.showLoading();
        }
        // if (filterCategoryModelList != null){
        //     LogUtil.i(TAG,  "不为空" + filterCategoryModelList.toString());
        // } else {
        //     LogUtil.i(TAG, "getData2 filterCategoryModelList 为空");
        //  }

        String dwbh = null;
        String cpys = null;
        String hphm = null;
        String clgs = null;
        String rlzl = null;
        String pdjg = null;
        String shzt = null;

        String lgmhdysf = null;
        Integer lgmhd = null;

        String sbzxdysf = null;
        Float sbzxd = null;

        String cbcsysf = null;
        Integer cbcs = null;

        String beginTime = null;
        String endTime = null;

        String sjd = null;

        if (filterCategoryModelList != null) {
            FilterBlackParams filterBlackParams = new FilterBlackParams(filterCategoryModelList).init();

            dwbh = filterBlackParams.getDwbh();
            cpys = filterBlackParams.getCpys();
            hphm = filterBlackParams.getHphm();
            clgs = filterBlackParams.getClgs();
            rlzl = filterBlackParams.getRlzl();
            pdjg = filterBlackParams.getPdjg();
            shzt = filterBlackParams.getShzt();


            lgmhdysf = filterBlackParams.getLgmhdysf();
            lgmhd = filterBlackParams.getLgmhd();

            sbzxdysf = filterBlackParams.getSbzxdysf();
            sbzxd = filterBlackParams.getSbzxd();

            cbcsysf = filterBlackParams.getCbcsysf();
            cbcs = filterBlackParams.getCbcs();

            beginTime = filterBlackParams.getBeginTime();
            endTime = filterBlackParams.getEndTime();

            sjd = filterBlackParams.getSjd();

        }
//        LogUtil.i(TAG, "beginTime: " + beginTime);
//        LogUtil.i(TAG, "endTime: " + endTime);

        RetrofitManager.getInstance().createReq(BlackCarApis.GetBlackDshList.class)
                .req(pdjg1, pageSize, pageNum, orderByColumn, isAsc,
                        dwbh, cpys, hphm, clgs, rlzl, pdjg, shzt,
                        lgmhdysf, lgmhd, sbzxdysf, sbzxd, cbcsysf, cbcs,
                        beginTime, endTime, sjd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperList<Exhaust>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        try {
                            throwable.printStackTrace();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        } finally {
                            if (BlackCarListPresenter.this.mView != null) {
                                BlackCarListPresenter.this.mView.showFail("黑烟车数据查询失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperList<Exhaust> responseWrapperList) {
                        if (BlackCarListPresenter.this.mView == null) {
                            return;
                        }
                        if (responseWrapperList.getCode() == 200) {
                            //LogUtil.i(TAG  + "_" + type, new Gson().toJson(responseWrapperList.getRows()).toString());
                            //1.更新列表数据
                            BlackCarListPresenter.this.mView.setData((List<Exhaust>) responseWrapperList.getRows());
                            //2.更新总数统计
                            RxBus.get().post((Object) new UpdateBlackSumEvent("wsh", responseWrapperList.getTotal(), -1, -1));
                        } else {
                            BlackCarListPresenter.this.mView.showFail("黑烟车数据查询失败！");
                        }
                        BlackCarListPresenter.this.mView.hideLoading();
                    }
                });
    }

    @Override
    public void getYshData(String pdjg1, String sfsh, int pageSize, int pageNum,
                           String orderByColumn, String isAsc, List<FilterCategoryModel> filterCategoryModelList) {
        if (this.mView != null) {
            this.mView.showLoading();
        }

        String dwbh = null;
        String cpys = null;
        String hphm = null;
        String clgs = null;
        String rlzl = null;
        String pdjg = null;
        String shzt = null;

        String lgmhdysf = null;
        Integer lgmhd = null;

        String sbzxdysf = null;
        Float sbzxd = null;

        String cbcsysf = null;
        Integer cbcs = null;

        String beginTime = null;
        String endTime = null;

        String sjd = null;

        if (filterCategoryModelList != null) {
            FilterBlackParams filterBlackParams = new FilterBlackParams(filterCategoryModelList).init();

            dwbh = filterBlackParams.getDwbh();
            cpys = filterBlackParams.getCpys();
            hphm = filterBlackParams.getHphm();
            clgs = filterBlackParams.getClgs();
            rlzl = filterBlackParams.getRlzl();
            pdjg = filterBlackParams.getPdjg();
            shzt = filterBlackParams.getShzt();


            lgmhdysf = filterBlackParams.getLgmhdysf();
            lgmhd = filterBlackParams.getLgmhd();

            sbzxdysf = filterBlackParams.getSbzxdysf();
            sbzxd = filterBlackParams.getSbzxd();

            cbcsysf = filterBlackParams.getCbcsysf();
            cbcs = filterBlackParams.getCbcs();

            beginTime = filterBlackParams.getBeginTime();
            endTime = filterBlackParams.getEndTime();

            sjd = filterBlackParams.getSjd();

        }

        RetrofitManager.getInstance().createReq(BlackCarApis.GetBlackYshList.class)
                .req(pdjg1, sfsh, pageSize, pageNum, orderByColumn, isAsc,
                        dwbh, cpys, hphm, clgs, rlzl, pdjg, shzt,
                        lgmhdysf, lgmhd, sbzxdysf, sbzxd, cbcsysf, cbcs,
                        beginTime, endTime, sjd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperList<Exhaust>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        try {
                            throwable.printStackTrace();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        } finally {
                            if (BlackCarListPresenter.this.mView != null) {
                                BlackCarListPresenter.this.mView.showFail("黑烟车数据查询失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperList<Exhaust> responseWrapperList) {
                        if (BlackCarListPresenter.this.mView == null) {
                            return;
                        }
                        if ((responseWrapperList.getCode() == 200) && (BlackCarListPresenter.this.mView != null)) {
                            //1.更新列表数据
                            BlackCarListPresenter.this.mView.setData((List<Exhaust>) responseWrapperList.getRows());
                            //2.更新总数统计
                            RxBus.get().post((Object) new UpdateBlackSumEvent("wsh", -1, responseWrapperList.getTotal(), -1));
                        } else {
                            BlackCarListPresenter.this.mView.showFail("黑烟车数据查询失败！");
                        }
                        BlackCarListPresenter.this.mView.hideLoading();
                    }
                });
    }

    @Override
    public void getAllData(int pageSize, int pageNum, String orderByColumn, String isAsc, List<FilterCategoryModel> filterCategoryModelList) {
        if (this.mView != null) {
            this.mView.showLoading();
        }

        String dwbh = null;
        String cpys = null;
        String hphm = null;
        String clgs = null;
        String rlzl = null;
        String pdjg = null;
        String shzt = null;

        String lgmhdysf = null;
        Integer lgmhd = null;

        String sbzxdysf = null;
        Float sbzxd = null;

        String cbcsysf = null;
        Integer cbcs = null;

        String beginTime = null;
        String endTime = null;

        String sjd = null;

        if (filterCategoryModelList != null) {
            FilterBlackParams filterBlackParams = new FilterBlackParams(filterCategoryModelList).init();

            dwbh = filterBlackParams.getDwbh();
            cpys = filterBlackParams.getCpys();
            hphm = filterBlackParams.getHphm();
            clgs = filterBlackParams.getClgs();
            rlzl = filterBlackParams.getRlzl();
            pdjg = filterBlackParams.getPdjg();
            shzt = filterBlackParams.getShzt();


            lgmhdysf = filterBlackParams.getLgmhdysf();
            lgmhd = filterBlackParams.getLgmhd();

            sbzxdysf = filterBlackParams.getSbzxdysf();
            sbzxd = filterBlackParams.getSbzxd();

            cbcsysf = filterBlackParams.getCbcsysf();
            cbcs = filterBlackParams.getCbcs();

            beginTime = filterBlackParams.getBeginTime();
            endTime = filterBlackParams.getEndTime();

            sjd = filterBlackParams.getSjd();

        }

        RetrofitManager.getInstance().createReq(BlackCarApis.GetBlackAllList.class)
                .req(pageSize, pageNum, orderByColumn, isAsc,
                        dwbh, cpys, hphm, clgs, rlzl, pdjg, shzt,
                        lgmhdysf, lgmhd, sbzxdysf, sbzxd, cbcsysf, cbcs,
                        beginTime, endTime, sjd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperList<Exhaust>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        try {
                            throwable.printStackTrace();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        } finally {
                            if (BlackCarListPresenter.this.mView != null) {
                                BlackCarListPresenter.this.mView.showFail("黑烟车数据查询失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperList<Exhaust> responseWrapperList) {
                        if (BlackCarListPresenter.this.mView == null) {
                            return;
                        }
                        if ((responseWrapperList.getCode() == 200) && (BlackCarListPresenter.this.mView != null)) {
                            //1.更新列表数据
                            BlackCarListPresenter.this.mView.setData((List<Exhaust>) responseWrapperList.getRows());
                            //2.更新总数统计
                            RxBus.get().post((Object) new UpdateBlackSumEvent("wsh", -1, -1, responseWrapperList.getTotal()));
                        } else {
                            BlackCarListPresenter.this.mView.showFail("黑烟车数据查询失败！");
                        }
                        BlackCarListPresenter.this.mView.hideLoading();
                    }
                });
    }

    @Override
    public void getZcListData(String pdjg, int pageSize, int pageNum, String orderByColumn, String isAsc, List<FilterCategoryModel> filterCategoryModelList) {
        if (this.mView != null) {
            this.mView.showLoading();
        }

        RetrofitManager.getInstance().createReq(BlackCarApis.GetZcList.class)
                .req(pdjg, pageSize, pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperList<ExhaustZC>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        try {
                            throwable.printStackTrace();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        } finally {
                            if (BlackCarListPresenter.this.mView != null) {
                                BlackCarListPresenter.this.mView.showFail("黑烟车数据查询失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperList<ExhaustZC> responseWrapperList) {
                        if (BlackCarListPresenter.this.mView == null) {
                            return;
                        }
                        if (responseWrapperList.getCode() == 200) {
                            BlackCarListPresenter.this.mView.setZcData((List<ExhaustZC>) responseWrapperList.getRows(), responseWrapperList.getTotal());
                        } else {
                            BlackCarListPresenter.this.mView.showFail("黑烟车数据查询失败！");
                        }
                        BlackCarListPresenter.this.mView.hideLoading();
                    }
                });
    }

    public void takeView(ProcessListContract.View paramView) {
    }

    public void dropView() {
        this.mView = null;
    }

}
