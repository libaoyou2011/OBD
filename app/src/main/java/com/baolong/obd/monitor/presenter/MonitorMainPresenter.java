package com.baolong.obd.monitor.presenter;

import android.text.TextUtils;

import com.baolong.obd.common.network.ResponseWrapperList;
import com.baolong.obd.common.network.ResponseWrapperListOld;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.monitor.MonitorApis;
import com.baolong.obd.monitor.contract.MonitorMainContract;
import com.baolong.obd.monitor.data.entity.GetTodayAmountModel2;
import com.baolong.obd.monitor.data.entity.SiteAqiInfoItemV3;
import com.baolong.obd.monitor.data.entity.SiteInfoItemV3;

import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MonitorMainPresenter implements MonitorMainContract.Presenter {

    private MonitorMainContract.View mView;

    public MonitorMainPresenter(MonitorMainContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    public void dropView() {
        this.mView = null;
    }

    public void getSiteInfoItemCount(RequestBody requestBody) {
        mView.showLoading();
        ((MonitorApis.GetSiteTodayAmount) RetrofitManager.getInstance().createReq(MonitorApis.GetSiteTodayAmount.class))
                .req(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Observer<ResponseWrapperListOld<GetTodayAmountModel2>>) new Observer<ResponseWrapperListOld<GetTodayAmountModel2>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        try {
                            if (MonitorMainPresenter.this.mView != null) {
//                                MonitorMainPresenter.this.mView.showFail("监测站点详情查询失败!");
                                // 单站点统计查询失败，WindowInfo仍然要显示
                                MonitorMainPresenter.this.mView.showSitInfoItemCount(new GetTodayAmountModel2());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperListOld<GetTodayAmountModel2> paramResponseWrapperList) {
                        if ((paramResponseWrapperList.getCode() == 200) && (MonitorMainPresenter.this.mView != null)) {
                            if (paramResponseWrapperList.getData() != null && paramResponseWrapperList.getData().size() > 0) {
                                MonitorMainPresenter.this.mView.showSitInfoItemCount((GetTodayAmountModel2) paramResponseWrapperList.getData().get(0));
                            } else {
                                // 单站点统计查询失败，WindowInfo仍然要显示
                                MonitorMainPresenter.this.mView.showSitInfoItemCount(new GetTodayAmountModel2());
                            }

                        } else if (MonitorMainPresenter.this.mView != null) {
//                            MonitorMainPresenter.this.mView.showFail("监测站点详情查询失败!");
                            // 单站点统计查询失败，WindowInfo仍然要显示
                            MonitorMainPresenter.this.mView.showSitInfoItemCount(new GetTodayAmountModel2());

                        }
                    }
                });
    }

    /**
     * public void getSiteListAll(String userToken, String mUseName, String mAppId) {
     * (RetrofitManager.getInstance().createReq(MonitorApis.GetStationListAll.class))
     * .req(userToken, mUseName, mAppId)
     * .subscribeOn(Schedulers.io())
     * .observeOn(AndroidSchedulers.mainThread())
     * .subscribe(new Observer<ResponseWrapperList<GetStationListAllResponseModel>>() {
     * public void onCompleted() {
     * }
     * <p>
     * public void onError(Throwable t) {
     * try {
     * t.printStackTrace();
     * if (MonitorMainPresenter.this.mView != null) {
     * MonitorMainPresenter.this.mView.showFail("监测站点查询失败!");
     * }
     * } catch (Exception e) {
     * e.printStackTrace();
     * }
     * }
     * <p>
     * public void onNext(ResponseWrapperList<GetStationListAllResponseModel> response) {
     * if ((response.getCode() == 200) && (MonitorMainPresenter.this.mView != null)) {
     * if (MonitorMainPresenter.this.mView != null) {
     * MonitorMainPresenter.this.mView.showSiteListAll(response.getWarningData());
     * }
     * } else if (MonitorMainPresenter.this.mView != null) {
     * MonitorMainPresenter.this.mView.showFail("监测站点查询失败!");
     * }
     * }
     * });
     * }
     */


    public void getSiteListAll() {
        RetrofitManager.getInstance().createReq(MonitorApis.GetSiteListAll.class)
                .req()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperList<SiteInfoItemV3>>() {
                    public void onCompleted() {
                    }

                    public void onError(Throwable t) {
                        try {
                            t.printStackTrace();
                            if (MonitorMainPresenter.this.mView != null) {
                                MonitorMainPresenter.this.mView.showFail("监测站点查询失败!");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    public void onNext(ResponseWrapperList<SiteInfoItemV3> response) {
                        if ((response.getCode() == 200) && (MonitorMainPresenter.this.mView != null)) {
                            MonitorMainPresenter.this.mView.showSiteListAll(response.getRows());

                        } else if (MonitorMainPresenter.this.mView != null) {
                            MonitorMainPresenter.this.mView.showFail("监测站点查询失败!");
                        }
                    }
                });
    }

    @Override
    public void getAqiListAll() {
        RetrofitManager.getInstance().createReq(MonitorApis.GetAqiListAll.class)
                .req()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperListOld<SiteAqiInfoItemV3>>() {
                    public void onCompleted() {
                    }

                    public void onError(Throwable t) {
                        try {
                            t.printStackTrace();
                            if (MonitorMainPresenter.this.mView != null) {
                                MonitorMainPresenter.this.mView.showFail("AQI站点查询失败!");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    public void onNext(ResponseWrapperListOld<SiteAqiInfoItemV3> response) {
                        if ((response.getCode() == 200) && (MonitorMainPresenter.this.mView != null)) {
                            MonitorMainPresenter.this.mView.showAqiListAll(response.getData());

                        } else if (MonitorMainPresenter.this.mView != null) {
                            MonitorMainPresenter.this.mView.showFail("AQI站点查询失败!");
                        }
                    }
                });
    }

    public void getTodayAmount() {
        ((MonitorApis.GetTodayAmount) RetrofitManager.getInstance().createReq(MonitorApis.GetTodayAmount.class))
                .req()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperListOld<GetTodayAmountModel2>>() {
                    public void onCompleted() {
                    }

                    public void onError(Throwable t) {
                        try {
                            t.printStackTrace();
                            if (MonitorMainPresenter.this.mView != null) {
                                MonitorMainPresenter.this.mView.showFail("今日监测数据查询失败!");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    public void onNext(ResponseWrapperListOld<GetTodayAmountModel2> response) {
                        if ((response.getCode() == 200) && (MonitorMainPresenter.this.mView != null)) {
                            GetTodayAmountModel2 model = (GetTodayAmountModel2) response.getData().get(0);
                            if (model != null) {
                                String testAmount = model.getCountJcsj();
                                String unqualifiedTimes = model.getCountJcsjCb();
                                String percent = model.getPercent();

                                if ("0".equals(percent)) {
                                    percent = "0.00%";
                                }

                                if (!TextUtils.isEmpty(testAmount) && !TextUtils.isEmpty(unqualifiedTimes) && !TextUtils.isEmpty(percent)) {
                                    MonitorMainPresenter.this.mView.showTodayAmount(testAmount, unqualifiedTimes, percent);
                                } else {
                                    MonitorMainPresenter.this.mView.showTodayAmount(" ", " ", " %");
                                }
                            }
                        } else if (MonitorMainPresenter.this.mView != null) {
                            MonitorMainPresenter.this.mView.showFail("今日监测数据查询失败!");
                        }
                    }
                });
    }

    public void takeView(MonitorMainContract.View paramView) {
    }
}
