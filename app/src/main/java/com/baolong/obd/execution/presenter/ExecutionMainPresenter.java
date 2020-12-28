package com.baolong.obd.execution.presenter;

import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.execution.ExecutionApis;
import com.baolong.obd.execution.contract.ExecutionMainContract;
import com.baolong.obd.execution.data.entity.GetStatisticCountModel;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ExecutionMainPresenter implements ExecutionMainContract.Presenter {

    private ExecutionMainContract.View mView;

    public ExecutionMainPresenter(ExecutionMainContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    @Override
    public void dropView() {
        this.mView = null;
    }

    @Override
    public void getData() {
        ((ExecutionApis.GetStatisticalExecution) RetrofitManager.getInstance()
                .createReq(ExecutionApis.GetStatisticalExecution.class))
                .req()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapper<GetStatisticCountModel>>() {
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
                            if (ExecutionMainPresenter.this.mView != null) {
                                ExecutionMainPresenter.this.mView.showFail("监测纪录处理统计查询数据查询失败！");
                            }
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapper<GetStatisticCountModel> paramAnonymousResponseWrapperList) {
                        if (ExecutionMainPresenter.this.mView == null) {
                            return;
                        }
                        if ((paramAnonymousResponseWrapperList.getCode() == 200) && (ExecutionMainPresenter.this.mView != null)) {
                            ExecutionMainPresenter.this.mView.setData((GetStatisticCountModel) paramAnonymousResponseWrapperList.getData());
                            return;
                        }
                        ExecutionMainPresenter.this.mView.showFail("监测纪录处理统计查询数据查询失败！");
                    }
                });
    }

    @Override
    public void takeView(ExecutionMainContract.View paramView) {
    }
}
