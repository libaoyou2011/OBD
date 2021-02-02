package com.baolong.obd.common.update.presenter;

import androidx.annotation.NonNull;

import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.common.update.UpdataApis;
import com.baolong.obd.common.update.contract.ICheckVersionContract;
import com.baolong.obd.common.update.model.CheckVersionRequestModel;
import com.baolong.obd.common.update.model.CheckVersionResponseModel;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CheckVersionPresenter implements ICheckVersionContract.Presenter {
    @NonNull
    private ICheckVersionContract.View mCheckVersionView;

    public CheckVersionPresenter(@NonNull ICheckVersionContract.View paramView) {
        this.mCheckVersionView = paramView;
        this.mCheckVersionView.setPresenter(this);
    }

    public void dropView() {
    }

    public void getData(String s) {
        CheckVersionRequestModel checkVersionRequestModel = new CheckVersionRequestModel();
        checkVersionRequestModel.setSystem("0");
        RetrofitManager.getInstance().createReq(UpdataApis.CheckVersion.class)
                .req(checkVersionRequestModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapper<CheckVersionResponseModel>>() {
                    public void onCompleted() {
                    }

                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    public void onNext(ResponseWrapper<CheckVersionResponseModel> responseWrapper) {
                        if (CheckVersionPresenter.this.mCheckVersionView != null) {
                            CheckVersionPresenter.this.mCheckVersionView.showData(responseWrapper.getData());
                        }
                    }
                });
    }

    public void takeView(ICheckVersionContract.View paramView) {
    }
}

