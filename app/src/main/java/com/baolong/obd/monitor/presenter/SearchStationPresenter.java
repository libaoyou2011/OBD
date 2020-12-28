package com.baolong.obd.monitor.presenter;

import com.baolong.obd.common.network.ResponseWrapperListOld;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.monitor.MonitorApis;
import com.baolong.obd.monitor.contract.SearchStationContract;
import com.baolong.obd.monitor.data.entity.GetStationListAllResponseModel;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchStationPresenter implements SearchStationContract.Presenter {
    private static final String TAG = SearchStationPresenter.class.getSimpleName();

    private SearchStationContract.View mView;

    public SearchStationPresenter(SearchStationContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    public void dropView() {
        this.mView = null;
    }

    public void getStationQuery(String paramString1, String paramString2, String paramString3, String paramString4) {
        ((MonitorApis.GetStationQuery) RetrofitManager.getInstance().createReq(MonitorApis.GetStationQuery.class))
                .req(paramString1, paramString2, paramString3, paramString4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperListOld<GetStationListAllResponseModel>>() {
                    public void onCompleted() {
                        LogUtil.i(TAG, "onCompleted");
                    }

                    public void onError(Throwable paramAnonymousThrowable) {
                        LogUtil.i(TAG, "onError");
                    }

                    public void onNext(ResponseWrapperListOld<GetStationListAllResponseModel> paramAnonymousResponseWrapperList) {
                        if ((paramAnonymousResponseWrapperList.getCode() == 200) && (SearchStationPresenter.this.mView != null)) {
                            LogUtil.i(TAG, paramAnonymousResponseWrapperList.getData().toString());
                            SearchStationPresenter.this.mView.showStationSearchList(paramAnonymousResponseWrapperList.getData());
                        }
                    }
                });
    }

    public void takeView(SearchStationContract.View paramView) {
    }
}
