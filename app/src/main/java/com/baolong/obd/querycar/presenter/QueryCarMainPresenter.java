package com.baolong.obd.querycar.presenter;

import com.baolong.obd.blackcar.data.entity.ResponseVehicleInfoListModel;
import com.baolong.obd.blackcar.data.entity.VehicleInfo;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.querycar.data.entity.GetVehicleQueryListResponseModel;

import java.util.List;

import com.baolong.obd.common.network.ResponseWrapperListOld;
import com.baolong.obd.common.network.ResponseWrapper;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.querycar.QueryCarApis;
import com.baolong.obd.querycar.contract.QueryCarMainContract;

public class QueryCarMainPresenter implements QueryCarMainContract.Presenter {
    private static final String TAG = QueryCarMainPresenter.class.getSimpleName();

    private QueryCarMainContract.View mView;

    public QueryCarMainPresenter(final QueryCarMainContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void deleteVehicleQueryInfo(final String s, final String s2, final String s3, final String s4, final String s5) {
        if (this.mView != null) {
            this.mView.showLoading();
        }
        RetrofitManager.getInstance()
                .<QueryCarApis.DeleteVehicleQueryInfo>createReq(QueryCarApis.DeleteVehicleQueryInfo.class)
                .req(s, s2, s3, s4, s5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Observer<ResponseWrapper<String>>) new Observer<ResponseWrapper<String>>() {
                    public void onCompleted() {
                    }

                    public void onError(final Throwable t) {
                        try {
                            t.printStackTrace();
                            if (QueryCarMainPresenter.this.mView != null) {
                                QueryCarMainPresenter.this.mView.hideLoading();
                                QueryCarMainPresenter.this.mView.showFail("删除查询历史记录失败！");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    public void onNext(final ResponseWrapper<String> responseWrapper) {
                        if (QueryCarMainPresenter.this.mView != null) {
                            QueryCarMainPresenter.this.mView.hideLoading();
                            if (responseWrapper.getCode() == 200) {
                                QueryCarMainPresenter.this.mView.refreshHistoryList();
                            } else {
                                QueryCarMainPresenter.this.mView.showFail("查询历史记录删除失败！");
                            }
                        }
                    }
                });
    }

    @Override
    public void dropView() {
        this.mView = null;
    }

    @Override
    public void getVehicleBasicInfo(final String s1, final String s2) {
        RetrofitManager.getInstance()
                .createReq(QueryCarApis.GetVehicleBasicInfo.class)
                .req(s1, s2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapper<ResponseVehicleInfoListModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(final Throwable t) {
                        try {
                            t.printStackTrace();
                            if (QueryCarMainPresenter.this.mView != null) {
                                QueryCarMainPresenter.this.mView.hideLoading();
                                QueryCarMainPresenter.this.mView.showFail("查询车辆基本信息失败！");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(final ResponseWrapper<ResponseVehicleInfoListModel> paramResponseWrapper) {
                        if (QueryCarMainPresenter.this.mView != null) {
                            QueryCarMainPresenter.this.mView.hideLoading();

                            if (paramResponseWrapper.getCode() == 200) {
                                List<VehicleInfo> vehicleInfoList = paramResponseWrapper.getData().getRows();
                                if (vehicleInfoList != null && vehicleInfoList.size() > 0) {
                                    LogUtil.i(TAG, "查询到车辆信息条数：" + vehicleInfoList.size());
                                    QueryCarMainPresenter.this.mView.showCarInfo(vehicleInfoList.get(0));
                                }
                            } else {
                                QueryCarMainPresenter.this.mView.showFail("查询车辆基本信息失败！");
                            }
                        }
                    }
                });
    }

    @Override
    public void getVehicleQueryList(final String s, final String s2, final String s3, final String s4) {
        RetrofitManager.getInstance()
                .<QueryCarApis.GetVehicleQueryList>createReq(QueryCarApis.GetVehicleQueryList.class)
                .req(s, s2, s3, s4).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperListOld<GetVehicleQueryListResponseModel>>() {
                    public void onCompleted() {
                    }

                    public void onError(final Throwable t) {
                        try {
                            t.printStackTrace();
                            if (QueryCarMainPresenter.this.mView != null) {
                                QueryCarMainPresenter.this.mView.hideLoading();
                                QueryCarMainPresenter.this.mView.showFail("查询历史记录获取失败！");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    public void onNext(final ResponseWrapperListOld<GetVehicleQueryListResponseModel> list) {
                        if (QueryCarMainPresenter.this.mView != null) {
                            QueryCarMainPresenter.this.mView.hideLoading();

                            if (list.getCode() == 200) {
                                QueryCarMainPresenter.this.mView.showQueryHistoryList(list.getData());
                            } else {
                                QueryCarMainPresenter.this.mView.showFail("查询历史记录获取失败！");
                            }
                        }
                    }
                });
    }

    @Override
    public void insertVehicleQuery(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        RetrofitManager.getInstance()
                .<QueryCarApis.InsertVehicleQuery>createReq(QueryCarApis.InsertVehicleQuery.class)
                .req(s, s2, s3, s4, s5, s6)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Observer<ResponseWrapper<String>>) new Observer<ResponseWrapper<String>>() {
                    public void onCompleted() {
                    }

                    public void onError(final Throwable t) {
                        try{
                            t.printStackTrace();
                            if (QueryCarMainPresenter.this.mView != null) {
                                QueryCarMainPresenter.this.mView.hideLoading();
                                QueryCarMainPresenter.this.mView.showFail("插入车辆查询纪录失败！");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    public void onNext(final ResponseWrapper<String> responseWrapper) {
                        if (QueryCarMainPresenter.this.mView != null) {
                            QueryCarMainPresenter.this.mView.hideLoading();

                            if (responseWrapper.getCode() == 200) {
                                QueryCarMainPresenter.this.mView.refreshHistoryList();
                            } else {
                                QueryCarMainPresenter.this.mView.showFail("插入车辆查询纪录失败！");
                            }
                        }
                    }
                });
    }

    @Override
    public void takeView(final QueryCarMainContract.View view) {
    }
}
