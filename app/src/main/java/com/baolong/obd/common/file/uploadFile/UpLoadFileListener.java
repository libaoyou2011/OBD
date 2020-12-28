package com.baolong.obd.common.file.uploadFile;

import java.io.File;

import com.baolong.obd.common.network.ResponseWrapperListOld;

public abstract interface UpLoadFileListener {
    public abstract void onComplete(ResponseWrapperListOld<NewContractPhotoModel> paramResponseWrapperList);

    public abstract void onError(int paramInt, String paramString);

    public abstract void onProgress(File paramFile, int paramInt);
}

