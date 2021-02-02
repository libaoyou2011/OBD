package com.baolong.obd.login.presenter;

import androidx.annotation.NonNull;

import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.common.sharepreferemces.UserSP;
import com.baolong.obd.login.LoginApis;
import com.baolong.obd.login.contract.IAccessTokenContract;
import com.baolong.obd.login.model.AccessTokenResponseModel;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetTokenPresenter
        implements IAccessTokenContract.Presenter {
    @NonNull
    private IAccessTokenContract.View accessTokenView;

    public GetTokenPresenter(@NonNull IAccessTokenContract.View paramView) {
        this.accessTokenView = paramView;
    }

    public void dropView() {
    }

    public void getToken(String paramString1, String paramString2) {
        ((LoginApis.GetToken) RetrofitManager.getInstance().createReq(LoginApis.GetToken.class))
                .req(paramString1, paramString2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapper<AccessTokenResponseModel>>() {
                    public void onCompleted() {
                    }

                    public void onError(Throwable t) {
                        GetTokenPresenter.this.accessTokenView.getTokenFail("获取授权失败");
                    }

                    public void onNext(ResponseWrapper<AccessTokenResponseModel> responseWrapper) {
                        if (GetTokenPresenter.this.accessTokenView == null) {
                            return;
                        }
                        if (responseWrapper.getCode() == 200) {
                            AccessTokenResponseModel accessTokenResponseModel = (AccessTokenResponseModel) responseWrapper.getData();
                            UserSP.setUserToken(accessTokenResponseModel.getToken());
                            GetTokenPresenter.this.accessTokenView.getTokenSuccess(accessTokenResponseModel.getToken());
                            return;
                        }
                        GetTokenPresenter.this.accessTokenView.getTokenFail("获取授权失败");
                    }
                });
    }

    public void takeView(IAccessTokenContract.View paramView) {
    }
}