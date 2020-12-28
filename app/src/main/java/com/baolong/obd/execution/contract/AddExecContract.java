package com.baolong.obd.execution.contract;

import com.baolong.obd.common.file.uploadFile.NewContractPhotoModel;
import com.baolong.obd.common.presenter.BasePresenter;
import com.baolong.obd.common.presenter.BaseView;
import com.baolong.obd.execution.data.entity.GetUploadImgResponseModel;
import com.baolong.obd.execution.data.entity.SearchMonitoringDataResponseModel;
import com.baolong.obd.execution.presenter.AddExecPresenter;

import java.io.File;
import java.util.List;

public abstract interface AddExecContract {

    public static abstract interface Presenter extends BasePresenter<AddExecContract.View> {

        public abstract void addAttachment(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8);

        public abstract void insertMonitoringData(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10, String paramString11, String paramString12, String paramString13, String paramString14, String paramString15, String paramString16, String paramString17, String paramString18);

        public abstract void queryMonitoringData(String paramString1, String paramString2, String paramString3, String paramString4);
    }

    public static abstract interface View extends BaseView<AddExecPresenter> {

        public abstract void fileUploadFail(String paramString1, String paramString2);

        public abstract void fileUploadProgress(File paramFile, int paramInt, String paramString);

        public abstract void fileUploadSuccess(GetUploadImgResponseModel paramGetUploadImgResponseModel);

        public abstract void hideLoading();

        public abstract void imageUploadFail(String paramString);

        public abstract void imageUploadProgress(File paramFile, int paramInt);

        public abstract void imageUploadSuccess(GetUploadImgResponseModel paramGetUploadImgResponseModel);

        public abstract void showFail(String paramString);

        public abstract void showInsertMonitorDataSuccess(String paramString);

        public abstract void showLoading();

        public abstract void showQueryData(SearchMonitoringDataResponseModel paramSearchMonitoringDataResponseModel);

        public abstract void videoUploadFail(String paramString);

        public abstract void videoUploadProgress(File paramFile, int paramInt);

        public abstract void videoUploadSuccess(List<NewContractPhotoModel> paramList);
    }
}
