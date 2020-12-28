package com.baolong.obd.monitor.contract;

import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;
import com.baolong.obd.monitor.data.entity.GetStationListAllResponseModel;
import com.baolong.obd.monitor.presenter.SearchStationPresenter;

import java.util.List;

public abstract interface SearchStationContract {

    public static abstract interface Presenter
            extends BasePresenter<SearchStationContract.View> {

        public abstract void getStationQuery(String paramString1, String paramString2, String paramString3, String paramString4);
    }

    public static abstract interface View
            extends BaseView<SearchStationPresenter> {

        public abstract void hideLoading();

        public abstract void showLoading();

        public abstract void showFail(String paramString);

        public abstract void showStationSearchList(List<GetStationListAllResponseModel> paramList);

    }
}
