package com.baolong.obd.common.file.uploadFile;

import java.io.File;
import java.util.Map;

import com.baolong.obd.common.network.ResponseWrapper;

import rx.Observer;

public class UploadFilePresenter {
    private UpLoadFileImpl mUpLoadFileImpl;

    public void deleteFile(final String s, final Observer<? super ResponseWrapper<Object>> observer) {
        if (this.mUpLoadFileImpl == null) {
            this.mUpLoadFileImpl = new UpLoadFileImpl();
        }
        this.mUpLoadFileImpl.deleteFile(s, observer);
    }

    public void upLoadFile(final String s, final Map<String, String> map, final UpLoadFileListener upLoadFileListener, final File... array) {
        if (this.mUpLoadFileImpl == null) {
            this.mUpLoadFileImpl = new UpLoadFileImpl();
        }
        this.mUpLoadFileImpl.uploadFiles(s, map, upLoadFileListener, array);
    }
}

