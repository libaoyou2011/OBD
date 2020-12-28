package com.baolong.obd.common.file.delFile;

public class DelFilePresenter {
    private DelFileImpl mDelFileImpl;

    public void delFile(String paramString1, String paramString2, DelFileListener paramDelFileListener) {
        if (this.mDelFileImpl == null) {
            this.mDelFileImpl = new DelFileImpl();
        }
        this.mDelFileImpl.delFile(paramString1, paramString2, paramDelFileListener);
    }
}
