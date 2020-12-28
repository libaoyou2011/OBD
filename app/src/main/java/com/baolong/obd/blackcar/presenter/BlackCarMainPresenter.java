package com.baolong.obd.blackcar.presenter;

import com.baolong.obd.blackcar.BlackCarApis;
import com.baolong.obd.blackcar.contract.BlackCarMainContract;
import com.baolong.obd.blackcar.data.entity.BlackCountModel;
import com.baolong.obd.common.network.RetrofitManager;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BlackCarMainPresenter implements BlackCarMainContract.Presenter {
    private BlackCarMainContract.View mView;

    public BlackCarMainPresenter(BlackCarMainContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    public void dropView() {
        this.mView = null;
    }

    public void getData() {
        ((BlackCarApis.GetBlackCarCount) RetrofitManager.getInstance()
                .createReq(BlackCarApis.GetBlackCarCount.class))
                .req()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BlackCountModel>() {
                    public void onCompleted() {
                    }

                    public void onError(final Throwable t) {
                        try {
                            t.printStackTrace();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        } finally {
                            if (BlackCarMainPresenter.this.mView != null) {
                                BlackCarMainPresenter.this.mView.showFail("黑烟车处理统计数据查询失败！");
                            }
                        }
                    }

                    public void onNext(final BlackCountModel blackCountModel) {
                        if (BlackCarMainPresenter.this.mView == null) {
                            return;
                        }
                        if ((blackCountModel.getCode() == 200) && (BlackCarMainPresenter.this.mView != null)) {
                            BlackCarMainPresenter.this.mView.setData(blackCountModel);
                            return;
                        }
                        BlackCarMainPresenter.this.mView.showFail("黑烟车处理统计数据查询失败！");
                    }
                });
    }

    public void takeView(BlackCarMainContract.View paramView) {
    }
}
