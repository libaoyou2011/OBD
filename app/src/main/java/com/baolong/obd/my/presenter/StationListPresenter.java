package com.baolong.obd.my.presenter;

import com.baolong.obd.my.MyApis;
import com.baolong.obd.my.contract.StationListContract;
import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.ResponseWrapperListOld;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.my.data.entity.GetDefaultStationResponseModel;
import com.baolong.obd.my.data.entity.GetStationListAllResponseModel;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class StationListPresenter implements StationListContract.Presenter {

    private StationListContract.View mView;

    public StationListPresenter(StationListContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    @Override
    public void dropView() {
        this.mView = null;
    }

    @Override
    public void takeView(StationListContract.View paramView) {
    }

    @Override
    public void getDefaultStation(String paramString1, String paramString2, String paramString3, String paramString4) {
        ((MyApis.GetDefaultStation) RetrofitManager.getInstance().createReq(MyApis.GetDefaultStation.class))
                .req(paramString1, paramString2, paramString3, paramString4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapper<GetDefaultStationResponseModel>>() {
                    public void onCompleted() {
                    }

                    public void onError(Throwable paramAnonymousThrowable) {
                        if (StationListPresenter.this.mView != null) {
                            StationListPresenter.this.mView.showFail("默认监测站点获取失败！");
                        }
                    }

                    public void onNext(ResponseWrapper<GetDefaultStationResponseModel> paramAnonymousResponseWrapper) {
                        if (StationListPresenter.this.mView == null) {
                            return;
                        }
                        if ((paramAnonymousResponseWrapper.getCode() == 200) && (StationListPresenter.this.mView != null)) {
                            if (StationListPresenter.this.mView != null) {
                                StationListPresenter.this.mView.showCurrentStation((GetDefaultStationResponseModel) paramAnonymousResponseWrapper.getData());
                            }
                        } else if (StationListPresenter.this.mView != null) {
                            StationListPresenter.this.mView.showFail("默认监测站点获取失败！");
                        }
                    }
                });
    }

    @Override
    public void getStationListAll(String paramString1, String paramString2, String paramString3) {
        if (this.mView != null) {
            this.mView.showLoading();
        }
        ((MyApis.GetStationListAll) RetrofitManager.getInstance().createReq(MyApis.GetStationListAll.class))
                .req(paramString1, paramString2, paramString3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperListOld<GetStationListAllResponseModel>>() {
                    public void onCompleted() {
                    }

                    public void onError(Throwable paramAnonymousThrowable) {
                        if (StationListPresenter.this.mView != null) {
                            StationListPresenter.this.mView.hideLoading();
                            StationListPresenter.this.mView.showFail("监测站点列表查询失败！");
                        }
                    }

                    public void onNext(ResponseWrapperListOld<GetStationListAllResponseModel> paramAnonymousResponseWrapperList) {
                        if (StationListPresenter.this.mView == null) {
                            return;
                        }
                        if ((paramAnonymousResponseWrapperList.getCode() == 200) && (StationListPresenter.this.mView != null)) {
                            if (StationListPresenter.this.mView != null) {
                                StationListPresenter.this.mView.showStationListAll(paramAnonymousResponseWrapperList.getData());
                            }
                        } else if (StationListPresenter.this.mView != null) {
                            StationListPresenter.this.mView.showFail("监测站点列表查询失败！");
                        }
                        StationListPresenter.this.mView.hideLoading();
                    }
                });
    }

    @Override
    public void setDefaultStation(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, final String paramString6) {
        if (this.mView != null) {
            this.mView.showLoading();
        }
        ((MyApis.SetDefaultStation) RetrofitManager.getInstance().createReq(MyApis.SetDefaultStation.class))
                .req(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapper<String>>() {
                    public void onCompleted() {
                    }

                    public void onError(Throwable paramAnonymousThrowable) {
                        if (StationListPresenter.this.mView != null) {
                            StationListPresenter.this.mView.hideLoading();
                            StationListPresenter.this.mView.showFail("默认监测站点设置失败！");
                        }
                    }

                    public void onNext(ResponseWrapper<String> paramAnonymousResponseWrapper) {
                        if (StationListPresenter.this.mView == null) {
                            return;
                        }
                        if ((paramAnonymousResponseWrapper.getCode() == 200) && (StationListPresenter.this.mView != null)) {
                            if (StationListPresenter.this.mView != null) {
                                StationListPresenter.this.mView.showChangeStationSuccess(paramString6);
                            }
                        } else if (StationListPresenter.this.mView != null) {
                            StationListPresenter.this.mView.showFail("默认监测站点设置失败！");
                        }
                        StationListPresenter.this.mView.hideLoading();
                    }
                });
    }
}