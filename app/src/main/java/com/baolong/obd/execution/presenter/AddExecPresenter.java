package com.baolong.obd.execution.presenter;

import com.baolong.obd.common.file.uploadFile.UploadFilePresenter;
import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.execution.ExecutionApis;
import com.baolong.obd.execution.contract.AddExecContract;
import com.baolong.obd.execution.data.entity.GetUploadImgResponseModel;
import com.baolong.obd.execution.data.entity.SearchMonitoringDataResponseModel;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddExecPresenter implements AddExecContract.Presenter {
    private UploadFilePresenter mUploadPresenter;
    private AddExecContract.View mView;

    public AddExecPresenter(AddExecContract.View paramView) {
        this.mView = paramView;
        this.mUploadPresenter = new UploadFilePresenter();
        this.mView.setPresenter(this);
    }

    public void addAttachment(String paramString1, String paramString2, String paramString3, String paramString4, final String paramString5, String paramString6, String paramString7, final String paramString8) {
        if (this.mView != null) {
            this.mView.showLoading();
        }
        ((ExecutionApis.UploadingImges) RetrofitManager.getInstance()
                .createReq(ExecutionApis.UploadingImges.class))
                .req(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapper<GetUploadImgResponseModel>>() {
                    public void onCompleted() {
                    }

                    public void onError(Throwable paramAnonymousThrowable) {
                        paramAnonymousThrowable.printStackTrace();
                        if (AddExecPresenter.this.mView != null) {
                            AddExecPresenter.this.mView.showFail("图片上传失败！");
                            AddExecPresenter.this.mView.hideLoading();
                        }
                    }

                    public void onNext(ResponseWrapper<GetUploadImgResponseModel> paramAnonymousResponseWrapper) {
                        if (AddExecPresenter.this.mView == null) {
                            return;
                        }
                        if ((paramAnonymousResponseWrapper.getCode() == 200) && (AddExecPresenter.this.mView != null)) {
                            GetUploadImgResponseModel localGetUploadImgResponseModel = (GetUploadImgResponseModel) paramAnonymousResponseWrapper.getData();
                            localGetUploadImgResponseModel.setFileClientName(paramString5);
                            localGetUploadImgResponseModel.setFileClientPath(paramString8);
                            AddExecPresenter.this.mView.imageUploadSuccess((GetUploadImgResponseModel) paramAnonymousResponseWrapper.getData());
                        } else {
                            AddExecPresenter.this.mView.showFail("图片上传失败！");
                        }
                        AddExecPresenter.this.mView.hideLoading();
                    }
                });
    }

    public void dropView() {
        this.mView = null;
    }

    public void insertMonitoringData(String paramString1, String paramString2, String paramString3, String paramString4, final String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14, String paramString15, String paramString16, String paramString17, String paramString18) {
        if (this.mView != null) {
            this.mView.showLoading();
        }
        ((ExecutionApis.InsertMonitoringData) RetrofitManager.getInstance()
                .createReq(ExecutionApis.InsertMonitoringData.class))
                .req(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, paramString8, paramString9, paramString10, paramString11, paramString12, paramString13, paramString14, paramString15, paramString16, paramString17, paramString18)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapper<String>>() {
                    public void onCompleted() {
                    }

                    public void onError(Throwable t) {
                        t.printStackTrace();
                        if (AddExecPresenter.this.mView != null) {
                            AddExecPresenter.this.mView.hideLoading();
                            AddExecPresenter.this.mView.showFail("监测纪录上传失败！");
                        }
                    }

                    public void onNext(ResponseWrapper<String> paramAnonymousResponseWrapper) {
                        if (AddExecPresenter.this.mView == null) {
                            return;
                        }
                        if ((paramAnonymousResponseWrapper.getCode() == 200) && (AddExecPresenter.this.mView != null)) {
                            AddExecPresenter.this.mView.showInsertMonitorDataSuccess(paramString5);
                        } else {
                            AddExecPresenter.this.mView.showFail("监测纪录上传失败！");
                        }
                        AddExecPresenter.this.mView.hideLoading();
                    }
                });
    }

    public void queryMonitoringData(String paramString1, String paramString2, String paramString3, String paramString4) {
        if (this.mView != null) {
            this.mView.showLoading();
        }
        ((ExecutionApis.SearchMonitoringData) RetrofitManager.getInstance()
                .createReq(ExecutionApis.SearchMonitoringData.class))
                .req(paramString1, paramString2, paramString3, paramString4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapper<SearchMonitoringDataResponseModel>>() {
                    public void onCompleted() {
                    }

                    public void onError(Throwable t) {
                        try {
                            t.printStackTrace();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        } finally {
                            if (AddExecPresenter.this.mView != null) {
                                AddExecPresenter.this.mView.hideLoading();
                                AddExecPresenter.this.mView.showFail("监测纪录处理信息读取失败！");
                            }
                        }
                    }

                    public void onNext(ResponseWrapper<SearchMonitoringDataResponseModel> paramAnonymousResponseWrapper) {
                        if (AddExecPresenter.this.mView == null) {
                            return;
                        }
                        if ((paramAnonymousResponseWrapper.getCode() == 200) && (AddExecPresenter.this.mView != null)) {
                            AddExecPresenter.this.mView.showQueryData((SearchMonitoringDataResponseModel) paramAnonymousResponseWrapper.getData());
                        } else {
                            AddExecPresenter.this.mView.showFail("监测纪录处理信息读取失败！");
                        }
                        AddExecPresenter.this.mView.hideLoading();
                    }
                });
    }

    public void takeView(AddExecContract.View paramView) {
    }
}
