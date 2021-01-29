package com.baolong.obd.execution.presenter;

import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.execution.data.entity.OBDCar;
import com.baolong.obd.execution.event.UpdateExeSumEvent;
import com.baolong.obd.common.network.ResponseWrapperList;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.execution.ExecutionApis;
import com.baolong.obd.execution.contract.ExecutionListContract;
import com.baolong.obd.execution.data.entity.ExeFilterParams;
import com.hwangjr.rxbus.RxBus;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ExecutionListPresenter implements ExecutionListContract.Presenter {
    private ExecutionListContract.View mView;

    public ExecutionListPresenter(ExecutionListContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    // 过滤条件字段
    String dwbh = null;
    String cpys = null;
    String hphm = null;
    String clgs = null;
    String rlzl = null;

    String cojgysf = null;
    Float cojg = null;
    String nojgysf = null;
    Float nojg = null;
    String hcjgysf = null;
    Float hcjg = null;
    String btgdjgysf = null;
    Float btgdjg = null;
    String co2sczysf = null;
    Float co2scz = null;

    String fsysf = null;
    Float fs = null;
    String vspysf = null;
    Float vsp = null;
    String hjwdysf = null;
    Float hjwd = null;
    String sdysf = null;
    Float sd = null;
    String dqyysf = null;
    Float dqy = null;

    String cbcsysf = null;
    Integer cbcs = null;
    String beginTime = null;
    String endTime = null;

    @Override
    public void getWcfData(String pdjg, String sfcf, int pageSize, int pageNum, String orderByColumn, String isAsc, List<FilterCategoryModel> filterCategoryModelList) {
        if (this.mView != null) {
            this.mView.showLoading();
        }

        if (filterCategoryModelList != null) {
            ExeFilterParams filterParams = new ExeFilterParams(filterCategoryModelList).init();
            dwbh = filterParams.getDwbh();
            cpys = filterParams.getCpys();
            hphm = filterParams.getHphm();
            clgs = filterParams.getClgs();
            rlzl = filterParams.getRlzl();

            cojgysf = filterParams.getCojgysf();
            cojg = filterParams.getCojg();
            nojgysf = filterParams.getNojgysf();
            nojg = filterParams.getNojg();
            hcjgysf = filterParams.getHcjgysf();
            hcjg = filterParams.getHcjg();
            btgdjgysf = filterParams.getBtgdjgysf();
            btgdjg = filterParams.getBtgdjg();
            co2sczysf = filterParams.getCo2sczysf();
            co2scz = filterParams.getCo2scz();

            fsysf = filterParams.getFsysf();
            fs = filterParams.getFs();
            vspysf = filterParams.getVspysf();
            vsp = filterParams.getVsp();
            hjwdysf = filterParams.getHjwdysf();
            hjwd = filterParams.getHjwd();
            sdysf = filterParams.getSdysf();
            sd = filterParams.getSd();
            dqyysf = filterParams.getDqyysf();
            dqy = filterParams.getDqy();

            cbcsysf = filterParams.getCbcsysf();
            cbcs = filterParams.getCbcs();
            beginTime = filterParams.getBeginTime();
            endTime = filterParams.getEndTime();
        }

        ((ExecutionApis.GetExeWcfList) RetrofitManager.getInstance().createReq(ExecutionApis.GetExeWcfList.class))
                .req(pdjg, sfcf, pageSize, pageNum, orderByColumn, isAsc,
                        dwbh, cpys, hphm, clgs, rlzl,
                        cojgysf, cojg, nojgysf, nojg, hcjgysf, hcjg, btgdjgysf, btgdjg, co2sczysf, co2scz,
                        fsysf, fs, vspysf, vsp, hjwdysf, hjwd, sdysf, sd, dqyysf, dqy,
                        cbcsysf, cbcs, beginTime, endTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperList<Exhaust>>() {
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
                            if (ExecutionListPresenter.this.mView != null) {
                                ExecutionListPresenter.this.mView.showFail("列表数据查询失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperList<Exhaust> responseWrapperList) {
                        if (ExecutionListPresenter.this.mView == null) {
                            return;
                        }
                        if (responseWrapperList.getCode() == 200) {
                            //1.更新列表数据
                            ExecutionListPresenter.this.mView.setWcfData((List<Exhaust>) responseWrapperList.getRows());
                            //2.更新总数统计
                            RxBus.get().post((Object) new UpdateExeSumEvent("wcf", responseWrapperList.getTotal(), -1, -1));
                        } else {
                            ExecutionListPresenter.this.mView.showFail("列表数据查询失败！");
                        }
                        ExecutionListPresenter.this.mView.hideLoading();
                    }
                });
    }

    @Override
    public void getYcfData(String pdjg, String sfcf, int pageSize, int pageNum, String orderByColumn, String isAsc, List<FilterCategoryModel> filterCategoryModelList) {
        if (this.mView != null) {
            this.mView.showLoading();
        }

        if (filterCategoryModelList != null) {
            ExeFilterParams filterParams = new ExeFilterParams(filterCategoryModelList).init();
            dwbh = filterParams.getDwbh();
            cpys = filterParams.getCpys();
            hphm = filterParams.getHphm();
            clgs = filterParams.getClgs();
            rlzl = filterParams.getRlzl();

            cojgysf = filterParams.getCojgysf();
            cojg = filterParams.getCojg();
            nojgysf = filterParams.getNojgysf();
            nojg = filterParams.getNojg();
            hcjgysf = filterParams.getHcjgysf();
            hcjg = filterParams.getHcjg();
            btgdjgysf = filterParams.getBtgdjgysf();
            btgdjg = filterParams.getBtgdjg();
            co2sczysf = filterParams.getCo2sczysf();
            co2scz = filterParams.getCo2scz();
            fsysf = filterParams.getFsysf();
            fs = filterParams.getFs();
            vspysf = filterParams.getVspysf();
            vsp = filterParams.getVsp();
            hjwdysf = filterParams.getHjwdysf();
            hjwd = filterParams.getHjwd();
            sdysf = filterParams.getSdysf();
            sd = filterParams.getSd();
            dqyysf = filterParams.getDqyysf();
            dqy = filterParams.getDqy();
            cbcsysf = null;
            cbcs = null;
            beginTime = filterParams.getBeginTime();
            endTime = filterParams.getEndTime();
        }

        ((ExecutionApis.GetExeYcfList) RetrofitManager.getInstance().createReq(ExecutionApis.GetExeYcfList.class))
                .req(pdjg, sfcf, pageSize, pageNum, orderByColumn, isAsc,
                        dwbh, cpys, hphm, clgs, rlzl,
                        cojgysf, cojg, nojgysf, nojg, hcjgysf, hcjg, btgdjgysf, btgdjg, co2sczysf, co2scz,
                        fsysf, fs, vspysf, vsp, hjwdysf, hjwd, sdysf, sd, dqyysf, dqy,
                        cbcsysf, cbcs, beginTime, endTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperList<Exhaust>>() {
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
                            if (ExecutionListPresenter.this.mView != null) {
                                ExecutionListPresenter.this.mView.showFail("列表数据查询失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperList<Exhaust> responseWrapperList) {
                        if (ExecutionListPresenter.this.mView == null) {
                            return;
                        }
                        if (responseWrapperList.getCode() == 200) {
                            // 1.更新列表数据
                            ExecutionListPresenter.this.mView.setYcfooAllData((List<Exhaust>) responseWrapperList.getRows());
                            // 2.更新总数统计
                            RxBus.get().post((Object) new UpdateExeSumEvent("ycf", -1, responseWrapperList.getTotal(), -1));
                        } else {
                            ExecutionListPresenter.this.mView.showFail("列表数据查询失败！");
                        }
                        ExecutionListPresenter.this.mView.hideLoading();
                    }
                });
    }

    @Override
    public void getOverproofData(String pdjg, String sfcf, int pageSize, int pageNum, String orderByColumn, String isAsc, List<FilterCategoryModel> filterCategoryModelList) {
        if (this.mView != null) {
            this.mView.showLoading();
        }

        if (filterCategoryModelList != null) {
            ExeFilterParams filterParams = new ExeFilterParams(filterCategoryModelList).init();
            dwbh = filterParams.getDwbh();
            cpys = filterParams.getCpys();
            hphm = filterParams.getHphm();
            clgs = filterParams.getClgs();
            rlzl = filterParams.getRlzl();

            cojgysf = filterParams.getCojgysf();
            cojg = filterParams.getCojg();
            nojgysf = filterParams.getNojgysf();
            nojg = filterParams.getNojg();
            hcjgysf = filterParams.getHcjgysf();
            hcjg = filterParams.getHcjg();
            btgdjgysf = filterParams.getBtgdjgysf();
            btgdjg = filterParams.getBtgdjg();
            co2sczysf = filterParams.getCo2sczysf();
            co2scz = filterParams.getCo2scz();
            fsysf = filterParams.getFsysf();
            fs = filterParams.getFs();
            vspysf = filterParams.getVspysf();
            vsp = filterParams.getVsp();
            hjwdysf = filterParams.getHjwdysf();
            hjwd = filterParams.getHjwd();
            sdysf = filterParams.getSdysf();
            sd = filterParams.getSd();
            dqyysf = filterParams.getDqyysf();
            dqy = filterParams.getDqy();
            cbcsysf = filterParams.getCbcsysf();
            cbcs = filterParams.getCbcs();
            beginTime = filterParams.getBeginTime();
            endTime = filterParams.getEndTime();
        }

        ((ExecutionApis.GetExeOverProofList) RetrofitManager.getInstance().createReq(ExecutionApis.GetExeOverProofList.class))
                .req(pdjg, sfcf, pageSize, pageNum, orderByColumn, isAsc,
                        dwbh, cpys, hphm, clgs, rlzl,
                        cojgysf, cojg, nojgysf, nojg, hcjgysf, hcjg, btgdjgysf, btgdjg, co2sczysf, co2scz,
                        fsysf, fs, vspysf, vsp, hjwdysf, hjwd, sdysf, sd, dqyysf, dqy,
                        cbcsysf, cbcs, beginTime, endTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperList<Exhaust>>() {
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
                            if (ExecutionListPresenter.this.mView != null) {
                                ExecutionListPresenter.this.mView.showFail("列表数据查询失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperList<Exhaust> responseWrapperList) {
                        if (ExecutionListPresenter.this.mView == null) {
                            return;
                        }
                        if (responseWrapperList.getCode() == 200) {
                            // 更新列表数据
                            ExecutionListPresenter.this.mView.setYcfooAllData((List<Exhaust>) responseWrapperList.getRows());
                            // 更新总数统计
                            // do nothing
                        } else {
                            ExecutionListPresenter.this.mView.showFail("列表数据查询失败！");
                        }
                        ExecutionListPresenter.this.mView.hideLoading();
                    }
                });
    }

    @Override
    public void getTelemetryData(int pageSize, int pageNum, String orderByColumn, String isAsc, List<FilterCategoryModel> filterCategoryModelList) {
        if (this.mView != null) {
            this.mView.showLoading();
        }

        if (filterCategoryModelList != null) {
            ExeFilterParams filterParams = new ExeFilterParams(filterCategoryModelList).init();
            dwbh = filterParams.getDwbh();
            cpys = filterParams.getCpys();
            hphm = filterParams.getHphm();
            clgs = filterParams.getClgs();
            rlzl = filterParams.getRlzl();

            cojgysf = filterParams.getCojgysf();
            cojg = filterParams.getCojg();
            nojgysf = filterParams.getNojgysf();
            nojg = filterParams.getNojg();
            hcjgysf = filterParams.getHcjgysf();
            hcjg = filterParams.getHcjg();
            btgdjgysf = filterParams.getBtgdjgysf();
            btgdjg = filterParams.getBtgdjg();
            co2sczysf = filterParams.getCo2sczysf();
            co2scz = filterParams.getCo2scz();
            fsysf = filterParams.getFsysf();
            fs = filterParams.getFs();
            vspysf = filterParams.getVspysf();
            vsp = filterParams.getVsp();
            hjwdysf = filterParams.getHjwdysf();
            hjwd = filterParams.getHjwd();
            sdysf = filterParams.getSdysf();
            sd = filterParams.getSd();
            dqyysf = filterParams.getDqyysf();
            dqy = filterParams.getDqy();
            cbcsysf = filterParams.getCbcsysf();
            cbcs = filterParams.getCbcs();
            beginTime = filterParams.getBeginTime();
            endTime = filterParams.getEndTime();
        }

        ((ExecutionApis.GetTelemetryDataListExe) RetrofitManager.getInstance().createReq(ExecutionApis.GetTelemetryDataListExe.class))
                .req(pageSize, pageNum, orderByColumn, isAsc,
                        dwbh, cpys, hphm, clgs, rlzl,
                        cojgysf, cojg, nojgysf, nojg, hcjgysf, hcjg, btgdjgysf, btgdjg, co2sczysf, co2scz,
                        fsysf, fs, vspysf, vsp, hjwdysf, hjwd, sdysf, sd, dqyysf, dqy,
                        cbcsysf, cbcs, beginTime, endTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperList<Exhaust>>() {
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
                            if (ExecutionListPresenter.this.mView != null) {
                                ExecutionListPresenter.this.mView.showFail("列表数据查询失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperList<Exhaust> responseWrapperList) {
                        if (ExecutionListPresenter.this.mView == null) {
                            return;
                        }
                        if (responseWrapperList.getCode() == 200) {
                            // 更新列表数据
                            ExecutionListPresenter.this.mView.setYcfooAllData((List<Exhaust>) responseWrapperList.getRows());
                            // 更新总数统计
                            // 遥测数据 do nothing
                        } else {
                            ExecutionListPresenter.this.mView.showFail("列表数据查询失败！");
                        }
                        ExecutionListPresenter.this.mView.hideLoading();
                    }
                });
    }


    @Override
    public void getOBDCarData(int pageSize, int pageNum, String clzt, String hphm, List<FilterCategoryModel> filterCategoryModelList) {
        if (this.mView != null) {
            this.mView.showLoading();
        }

        /*if (filterCategoryModelList != null) {
            ExeFilterParams filterParams = new ExeFilterParams(filterCategoryModelList).init();
            dwbh = filterParams.getDwbh();
            cpys = filterParams.getCpys();
            hphm = filterParams.getHphm();
            clgs = filterParams.getClgs();
            rlzl = filterParams.getRlzl();

            cojgysf = filterParams.getCojgysf();
            cojg = filterParams.getCojg();
            nojgysf = filterParams.getNojgysf();
            nojg = filterParams.getNojg();
            hcjgysf = filterParams.getHcjgysf();
            hcjg = filterParams.getHcjg();
            btgdjgysf = filterParams.getBtgdjgysf();
            btgdjg = filterParams.getBtgdjg();
            co2sczysf = filterParams.getCo2sczysf();
            co2scz = filterParams.getCo2scz();
            fsysf = filterParams.getFsysf();
            fs = filterParams.getFs();
            vspysf = filterParams.getVspysf();
            vsp = filterParams.getVsp();
            hjwdysf = filterParams.getHjwdysf();
            hjwd = filterParams.getHjwd();
            sdysf = filterParams.getSdysf();
            sd = filterParams.getSd();
            dqyysf = filterParams.getDqyysf();
            dqy = filterParams.getDqy();
            cbcsysf = filterParams.getCbcsysf();
            cbcs = filterParams.getCbcs();
            beginTime = filterParams.getBeginTime();
            endTime = filterParams.getEndTime();
        }*/

        ((ExecutionApis.GetOBDCarList) RetrofitManager.getInstance().createReq(ExecutionApis.GetOBDCarList.class))
                .req(pageSize, pageNum, clzt)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperList<OBDCar>>() {
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
                            if (ExecutionListPresenter.this.mView != null) {
                                ExecutionListPresenter.this.mView.showFail("车辆状态查询失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperList<OBDCar> responseWrapperList) {
                        if (ExecutionListPresenter.this.mView == null) {
                            return;
                        }
                        if (responseWrapperList.getCode() == 200) {
                            ExecutionListPresenter.this.mView.setOBDCarData((List<OBDCar>) responseWrapperList.getRows(),responseWrapperList.getTotal());

                        } else {
                            ExecutionListPresenter.this.mView.showFail("车辆状态查询失败！");
                        }
                        ExecutionListPresenter.this.mView.hideLoading();
                    }
                });
    }

    @Override
    public void takeView(ExecutionListContract.View paramView) {
    }

    @Override
    public void dropView() {
        this.mView = null;
    }
}

