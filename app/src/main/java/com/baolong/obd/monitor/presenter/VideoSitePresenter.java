package com.baolong.obd.monitor.presenter;

import com.baolong.obd.blackcar.data.entity.SiteVideoInfo;
import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.monitor.MonitorApis;
import com.baolong.obd.monitor.contract.VideoSiteContract;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class VideoSitePresenter implements VideoSiteContract.Presenter {
    private VideoSiteContract.View mView;

    public VideoSitePresenter(VideoSiteContract.View paramView) {
        this.mView = paramView;
        this.mView.setPresenter(this);
    }

    @Override
    public void takeView(VideoSiteContract.View paramView) {
    }

    @Override
    public void getData(String paramString1) {
        ((MonitorApis.GetSiteVideo) RetrofitManager.getInstance()
                .createReq(MonitorApis.GetSiteVideo.class))
                .req(paramString1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapper<SiteVideoInfo>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable paramAnonymousThrowable) {
                        try {
                            if (VideoSitePresenter.this.mView != null) {
                                VideoSitePresenter.this.mView.showFail("摄像机信息获取失败！");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            return;
                        }
                    }

                    @Override
                    public void onNext(ResponseWrapper<SiteVideoInfo> responseWrapper) {
                        if (VideoSitePresenter.this.mView == null) {
                            return;
                        }
                        if ((responseWrapper.getCode() == 200) && (VideoSitePresenter.this.mView != null)) {
                            VideoSitePresenter.this.mView.setData((SiteVideoInfo) responseWrapper.getData());
                            return;
                        }
                        VideoSitePresenter.this.mView.showFail("摄像机信息获取失败！");
                    }
                });
    }

    @Override
    public void dropView() {
        this.mView = null;
    }

}