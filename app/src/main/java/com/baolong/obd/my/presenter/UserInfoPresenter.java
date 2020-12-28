package com.baolong.obd.my.presenter;

import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.ResponseWrapperListOld;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.my.MyApis;
import com.baolong.obd.my.contract.UserInfoContract;
import com.baolong.obd.my.data.entity.UserInfoModel;
import com.google.gson.Gson;

import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserInfoPresenter implements UserInfoContract.Presenter {

    private UserInfoContract.View mView;

    public UserInfoPresenter(UserInfoContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    @Override
    public void dropView() {
        this.mView = null;
    }

    @Override
    public void takeView(UserInfoContract.View paramView) {
    }

    @Override
    public void getUserInfo() {
        ((MyApis.GetUserInfoApi) RetrofitManager.getInstance().createReq(MyApis.GetUserInfoApi.class))
                .req()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapperListOld<UserInfoModel>>() {
                    public void onCompleted() {
                    }

                    public void onError(Throwable paramAnonymousThrowable) {
                        if (UserInfoPresenter.this.mView != null) {
                            UserInfoPresenter.this.mView.showFail("获取用户信息失败！");
                        }
                    }

                    public void onNext(ResponseWrapperListOld<UserInfoModel> responseWrapper) {
                        //LogUtil.i("UserInfoPresenter:", new Gson().toJson(responseWrapper));
                        if (UserInfoPresenter.this.mView == null) {
                            return;
                        }
                        if ((responseWrapper.getCode() == 200) && (UserInfoPresenter.this.mView != null)) {
                            if (responseWrapper.getData() != null) {
                                UserInfoPresenter.this.mView.showUserInfo((UserInfoModel) responseWrapper.getData().get(0));
                            }

                        } else if (UserInfoPresenter.this.mView != null) {
                            UserInfoPresenter.this.mView.showFail("获取用户信息失败！");
                        }
                    }
                });
    }

    @Override
    public void saveUserInfo(RequestBody requestBody) {
        ((MyApis.SavedUserInfoApi) RetrofitManager.getInstance().createReq(MyApis.SavedUserInfoApi.class))
                .req(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapper<String>>() {
                    public void onCompleted() {
                    }

                    public void onError(Throwable paramAnonymousThrowable) {
                        if (UserInfoPresenter.this.mView != null) {
                            UserInfoPresenter.this.mView.showFail("保存用户信息失败！");
                        }
                    }

                    public void onNext(ResponseWrapper<String> responseWrapper) {
                        LogUtil.i("UserInfoPresenter:", new Gson().toJson(responseWrapper));
                        if (UserInfoPresenter.this.mView == null) {
                            return;
                        }
                        if ((responseWrapper.getCode() == 200) && (UserInfoPresenter.this.mView != null)) {
                            UserInfoPresenter.this.mView.showSaveUserInfo("保存用户信息成功！");

                        } else if (UserInfoPresenter.this.mView != null) {
                            UserInfoPresenter.this.mView.showFail("保存用户信息失败！");
                        }
                    }
                });
    }

}