package com.baolong.obd.querycar.presenter;

import com.baolong.obd.blackcar.data.entity.HuanjianModel;
import com.baolong.obd.blackcar.data.entity.ResponseExhaustListModel;
import com.baolong.obd.blackcar.data.entity.ResponseListModel;
import com.baolong.obd.blackcar.data.entity.ResponseVehicleInfoListModel;
import com.baolong.obd.blackcar.data.entity.VehicleInfo;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.common.network.ResponseWrapper;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.querycar.QueryCarApis;
import com.baolong.obd.querycar.contract.QueryCarListContract;

import java.util.List;

public class QueryCarListPresenter implements QueryCarListContract.Presenter {
    private static final String TAG = QueryCarListPresenter.class.getSimpleName();

    private QueryCarListContract.View mView;

    public QueryCarListPresenter(final QueryCarListContract.View mView) {
        (this.mView = mView).setPresenter(this);
    }

    @Override
    public void dropView() {
        this.mView = null;
    }

    @Override
    public void getVehicleBasicInfo(final String hphm, final String cpys) {
        RetrofitManager.getInstance()
                .createReq(QueryCarApis.GetVehicleBasicInfo.class)
                .req(hphm, cpys)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapper<ResponseVehicleInfoListModel>>() {
                    public void onCompleted() {
                    }

                    public void onError(final Throwable t) {
                        try {
                            t.printStackTrace();
                            if (QueryCarListPresenter.this.mView != null) {
                                QueryCarListPresenter.this.mView.hideLoading();
                                QueryCarListPresenter.this.mView.showFail("查询车辆基本信息失败！");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    public void onNext(final ResponseWrapper<ResponseVehicleInfoListModel> paramResponseWrapper) {
                        if (QueryCarListPresenter.this.mView != null) {
                            QueryCarListPresenter.this.mView.hideLoading();

                            if (paramResponseWrapper.getCode() == 200) {
                                List<VehicleInfo> vehicleInfoList = paramResponseWrapper.getData().getRows();
                                if (vehicleInfoList != null && vehicleInfoList.size() > 0) {
                                    LogUtil.i(TAG, "查询到车辆信息条数：" + vehicleInfoList.size());
                                    QueryCarListPresenter.this.mView.showCarInfo(vehicleInfoList.get(0));
                                }
                            } else {
                                QueryCarListPresenter.this.mView.showFail("未查询到车辆信息!");
                            }
                        }
                    }
                });
    }


    @Override
    public void getJyListData(final int s1, final int s2, final String s3, final String s4, final String s5, final String s6) {
        RetrofitManager.getInstance()
                .createReq(QueryCarApis.GetCheckListByVehicle.class)
                .req(s1, s2, s3, s4, s5, s6)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapper<ResponseListModel<HuanjianModel>>>() {
                    public void onCompleted() {
                    }

                    public void onError(final Throwable t) {
                        try {
                            t.printStackTrace();
                            if (QueryCarListPresenter.this.mView != null) {
                                QueryCarListPresenter.this.mView.hideLoading();
                                QueryCarListPresenter.this.mView.showFail("查询车辆检验信息失败！");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    public void onNext(final ResponseWrapper<ResponseListModel<HuanjianModel>> responseWrapper) {
                        if (QueryCarListPresenter.this.mView != null) {
                            QueryCarListPresenter.this.mView.hideLoading();

                            if (responseWrapper.getCode() == 200) {
                                QueryCarListPresenter.this.mView.showJyListData(responseWrapper.getData());
                            } else {
                                QueryCarListPresenter.this.mView.showFail("查询车辆检验信息失败！");
                            }
                        }
                    }
                });
    }

    @Override
    public void getYcListData(int isBlackCar, int pageSize, int pageNum, String orderByColumn, String isAsc, String hphm, String cpys) {
        RetrofitManager.getInstance()
                .<QueryCarApis.GetYcDataByCar>createReq(QueryCarApis.GetYcDataByCar.class)
                .req(isBlackCar, pageSize, pageNum, orderByColumn, isAsc, hphm, cpys)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapper<ResponseExhaustListModel>>() {
                    public void onCompleted() {
                    }

                    public void onError(final Throwable t) {
                        try {
                            t.printStackTrace();
                            if (QueryCarListPresenter.this.mView != null) {
                                QueryCarListPresenter.this.mView.hideLoading();
                                QueryCarListPresenter.this.mView.showFail("遥测记录列表数据查询失败！");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    public void onNext(final ResponseWrapper<ResponseExhaustListModel> responseWrapper) {
                        if (QueryCarListPresenter.this.mView != null) {
                            QueryCarListPresenter.this.mView.hideLoading();

                            if (responseWrapper.getCode() == 200) {
                                QueryCarListPresenter.this.mView.showYcListData((ResponseExhaustListModel)responseWrapper.getData());
                            } else {
                                QueryCarListPresenter.this.mView.showFail("遥测记录列表数据查询失败！");
                            }
                        }
                    }
                });
    }

    @Override
    public void getCbListData(int pdjg, int pageSize, int pageNum, String orderByColumn, String isAsc, String hphm, String cpys) {
        RetrofitManager.getInstance()
                .createReq(QueryCarApis.GetCbDataByCar.class)
                .req(pdjg, pageSize, pageNum, orderByColumn, isAsc, hphm, cpys)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapper<ResponseExhaustListModel>>() {
                    public void onCompleted() {
                    }

                    public void onError(final Throwable t) {
                        try {
                            t.printStackTrace();
                            if (QueryCarListPresenter.this.mView != null) {
                                QueryCarListPresenter.this.mView.hideLoading();
                                QueryCarListPresenter.this.mView.showFail("数据查询失败！");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    public void onNext(final ResponseWrapper<ResponseExhaustListModel> responseWrapper) {
                        if (QueryCarListPresenter.this.mView != null) {
                            QueryCarListPresenter.this.mView.hideLoading();

                            if (responseWrapper.getCode() == 200) {
                                QueryCarListPresenter.this.mView.showCbListData((ResponseExhaustListModel)responseWrapper.getData());
                            } else {
                                QueryCarListPresenter.this.mView.showFail("数据查询失败！");
                            }
                        }
                    }
                });
    }

    @Override
    public void takeView(final QueryCarListContract.View view) {
    }
}
