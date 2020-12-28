package com.baolong.obd.login.contract;

import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.login.model.ResponseLoginModel;

public abstract interface ILoginContract {

    public static abstract interface Presenter extends BasePresenter<ILoginContract.View> {
        public abstract void login(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5);
    }

    public static abstract interface View {

        public abstract void loginFail(String str);

        public abstract void loginSuccess(ResponseLoginModel responseLoginModel);

    }
}
