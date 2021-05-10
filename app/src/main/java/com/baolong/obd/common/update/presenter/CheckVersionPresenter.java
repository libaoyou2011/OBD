package com.baolong.obd.common.update.presenter;

import androidx.annotation.NonNull;

import com.baolong.obd.common.network.ResponseWrapperList;
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

    public void getData(String platform, String category, String versionCode) {
        CheckVersionRequestModel checkVersionRequestModel = new CheckVersionRequestModel();
        checkVersionRequestModel.setSystem("0");
        RetrofitManager.getInstance().createReq(UpdataApis.CheckVersion.class)
                .req(platform, category, versionCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperList<CheckVersionResponseModel>>() {
                    public void onCompleted() {
                    }

                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    public void onNext(ResponseWrapperList<CheckVersionResponseModel> responseWrapperList) {
                        if (CheckVersionPresenter.this.mCheckVersionView != null) {
                            if (responseWrapperList != null) {
                                if (responseWrapperList.getRows() != null && responseWrapperList.getRows().size() > 0) {
                                    CheckVersionPresenter.this.mCheckVersionView.showData(responseWrapperList.getRows().get(0));
                                }

                            }

                        }
                    }
                });
    }

    public void takeView(ICheckVersionContract.View paramView) {
    }
}

