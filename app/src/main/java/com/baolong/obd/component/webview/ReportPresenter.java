package com.baolong.obd.component.webview;

import androidx.annotation.NonNull;
import android.util.Log;

import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.common.utils.FileUtils;

import java.io.File;

import okhttp3.ResponseBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ReportPresenter implements ReportContract.Presenter {
    private static final String TAG = ReportPresenter.class.getSimpleName();
    @NonNull
    private ReportContract.View mView;

    public ReportPresenter(@NonNull ReportContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    @Override
    public void dropView() {
    }

    @Override
    public void productOneFileInServer(String paramString1) {
        ((ReportApis.ProductOneFileInServer) RetrofitManager.getInstance().createReq(ReportApis.ProductOneFileInServer.class))
                .req(paramString1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Observer<ResponseWrapper<String>>) new Observer<ResponseWrapper<String>>() {
                    @Override
                    public void onError(Throwable t) {
                        try {
                            t.printStackTrace();
                            ReportPresenter.this.mView.showFail("生成检测报告失败!");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(ResponseWrapper<String> responseWrapper) {
                        /* {
                            "msg": "皖ATU262-20191028113951000.zip",
                                "code": 0,
                                "data": null
                        } */
                        if (responseWrapper.getCode() == 0) {
                            ReportPresenter.this.mView.showProductOneFileSuccess(responseWrapper.getMsg());
                        } else {
                            ReportPresenter.this.mView.showFail("生成检测报告失败!");
                        }
                    }
                });
    }

    @Override
    public void productTwoFileInServer(String paramString1) {
        ((ReportApis.ProductOneFileInServer) RetrofitManager.getInstance().createReq(ReportApis.ProductOneFileInServer.class))
                .req(paramString1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Observer<ResponseWrapper<String>>) new Observer<ResponseWrapper<String>>() {
                    @Override
                    public void onError(Throwable t) {
                        try {
                            t.printStackTrace();
                            ReportPresenter.this.mView.showFail("生成检测报告失败!");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(ResponseWrapper<String> responseWrapper) {
                        /* {
                            "msg": "皖ATU262-20191028113951000.zip",
                                "code": 0,
                                "data": null
                        } */
                        if (responseWrapper.getCode() == 0) {
                            ReportPresenter.this.mView.showProductTwoFileSuccess(responseWrapper.getMsg());
                        } else {
                            ReportPresenter.this.mView.showFail("生成检测报告失败!");
                        }
                    }
                });
    }

    @Override
    public void downloadFile(String url, String path, String fileName) {
        ((ReportApis.DownloadFile) RetrofitManager.getInstance().createReq(ReportApis.DownloadFile.class))
                .downloadFile(url)
                .map(new Func1<ResponseBody, File>() {
                    @Override
                    public File call(ResponseBody body) {
                        return FileUtils.saveFile(path, fileName, body);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Observer<File>) new Observer<File>() {
                    @Override
                    public void onError(Throwable t) {
                        try {
                            Log.i(TAG, "Login onError");
                            t.printStackTrace();
                            ReportPresenter.this.mView.showFail("下载文件失败");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "downLoadFile onCompleted");
                    }

                    @Override
                    public void onNext(File file) {
                        Log.i(TAG, "onNext");

                        if (file != null) {
                            ReportPresenter.this.mView.showDownloadFileSuccess(file);
                        } else {
                            ReportPresenter.this.mView.showFail("下载文件失败");
                        }
                    }
                });
    }

    @Override
    public void deleteFileInServer(String paramString1) {
        ((ReportApis.DeleteFileInServer) RetrofitManager.getInstance().createReq(ReportApis.DeleteFileInServer.class))
                .req(paramString1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Observer<ResponseWrapper<String>>) new Observer<ResponseWrapper<String>>() {
                    @Override
                    public void onError(Throwable t) {
                        try {
                            t.printStackTrace();
                            ReportPresenter.this.mView.showFail("删除检测报告失败!");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onNext(ResponseWrapper<String> responseWrapper) {
                        /*{
                            "msg": "删除成功",
                                "code": 0,
                                "data": null
                        }*/
                        if (responseWrapper.getCode() == 0) {
                            ReportPresenter.this.mView.showDeleteFileSuccess(responseWrapper.getMsg());
                        } else {
                            ReportPresenter.this.mView.showFail("删除检测报告失败!");
                        }
                    }
                });
    }

    @Override
    public void takeView(ReportContract.View paramView) {
    }
}