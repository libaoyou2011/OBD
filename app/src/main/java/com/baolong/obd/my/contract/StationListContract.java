package com.baolong.obd.my.contract;


import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;
import com.baolong.obd.my.data.entity.GetDefaultStationResponseModel;
import com.baolong.obd.my.data.entity.GetStationListAllResponseModel;
import com.baolong.obd.my.presenter.StationListPresenter;

import java.util.List;

public abstract interface StationListContract {

    public static abstract interface Presenter
            extends BasePresenter<StationListContract.View> {

        public abstract void getDefaultStation(String paramString1, String paramString2, String paramString3, String paramString4);

        public abstract void getStationListAll(String paramString1, String paramString2, String paramString3);

        public abstract void setDefaultStation(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6);
    }

    public static abstract interface View
            extends BaseView<StationListPresenter> {

        public abstract void hideLoading();

        public abstract void showLoading();

        public abstract void showFail(String paramString);

        public abstract void showStationListAll(List<GetStationListAllResponseModel> paramList);

        public abstract void showChangeStationSuccess(String paramString);

        public abstract void showCurrentStation(GetDefaultStationResponseModel paramGetDefaultStationResponseModel);
    }
}
