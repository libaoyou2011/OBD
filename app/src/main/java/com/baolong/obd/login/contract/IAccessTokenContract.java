package com.baolong.obd.login.contract;

import com.baolong.obd.common.presenter.BasePresenter;

public abstract interface IAccessTokenContract {

    public static abstract interface Presenter
            extends BasePresenter<IAccessTokenContract.View> {
        public abstract void getToken(String paramString1, String paramString2);
    }

    public static abstract interface View {

        public abstract void getTokenFail(String paramString);

        public abstract void getTokenSuccess(String paramString);
    }
}
