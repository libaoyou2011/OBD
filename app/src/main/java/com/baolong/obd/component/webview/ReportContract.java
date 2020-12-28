package com.baolong.obd.component.webview;

import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;

import java.io.File;

public interface ReportContract {

    interface Presenter extends BasePresenter<ReportContract.View> {

        void productOneFileInServer(String paramString1);

        void productTwoFileInServer(String paramString1);

        void downloadFile(String url, String path, String fileName);

        void deleteFileInServer(String paramString1);

    }

    interface View extends BaseView<ReportContract.Presenter> {

        void showLoading();

        void hideLoading();

        void showProductOneFileSuccess(String str);

        void showProductTwoFileSuccess(String str);

        void showDownloadFileSuccess(File file);

        void showDeleteFileSuccess(String str);

        void showFail(String paramString);
    }
}
