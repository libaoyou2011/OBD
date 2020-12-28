package com.baolong.obd.blackcar.presenter;

import com.baolong.obd.blackcar.BlackCarApis;
import com.baolong.obd.blackcar.contract.FilterActivityContract;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.common.network.ResponseWrapperListOld;
import com.baolong.obd.common.network.RetrofitManager;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FilterListPresenter implements FilterActivityContract.Presenter {
    private FilterActivityContract.View mView;

    public FilterListPresenter(FilterActivityContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    @Override
    public void takeView(FilterActivityContract.View paramView) {
    }

    @Override
    public void dropView() {
        this.mView = null;
    }

    @Override
    public void getBlackFilterListAll() {
        if (this.mView != null) {
            this.mView.showLoading();
        }
        ((BlackCarApis.GetBlackFilterListAll) RetrofitManager.getInstance().createReq(BlackCarApis.GetBlackFilterListAll.class))
                .req()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperListOld<FilterCategoryModel>>() {
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
                            if (FilterListPresenter.this.mView != null) {
                                FilterListPresenter.this.mView.hideLoading();
                                FilterListPresenter.this.mView.showFail("查询条件获取失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperListOld<FilterCategoryModel> responseWrapperList) {
                        if (FilterListPresenter.this.mView == null) {
                            return;
                        }
                        if ((responseWrapperList.getCode() == 200) && (FilterListPresenter.this.mView != null)) {
                            FilterListPresenter.this.mView.showBlackFilterListAll(responseWrapperList.getData());

                        } else if (FilterListPresenter.this.mView != null) {
                            FilterListPresenter.this.mView.showFail("查询条件获取失败！");
                        }
                        FilterListPresenter.this.mView.hideLoading();
                    }
                });
    }

    @Override
    public void getExecutionFilterListAll() {
        if (this.mView != null) {
            this.mView.showLoading();
        }
        ((BlackCarApis.GetExecutionFilterListAll) RetrofitManager.getInstance()
                .createReq(BlackCarApis.GetExecutionFilterListAll.class))
                .req()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperListOld<FilterCategoryModel>>() {
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
                            if (FilterListPresenter.this.mView != null) {
                                FilterListPresenter.this.mView.hideLoading();
                                FilterListPresenter.this.mView.showFail("查询条件获取失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapperListOld<FilterCategoryModel> responseWrapperList) {
                        if (FilterListPresenter.this.mView == null) {
                            return;
                        }
                        if ((responseWrapperList.getCode() == 200) && (FilterListPresenter.this.mView != null)) {
                            FilterListPresenter.this.mView.showBlackFilterListAll(responseWrapperList.getData());

                        } else if (FilterListPresenter.this.mView != null) {
                            FilterListPresenter.this.mView.showFail("查询条件获取失败！");
                        }
                        FilterListPresenter.this.mView.hideLoading();
                    }
                });
    }
}
