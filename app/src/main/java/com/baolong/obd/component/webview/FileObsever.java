//package com.baolong.edsp.component.webview;
//
//import com.baolong.edsp.common.presenter.BaseView;
//import com.baolong.edsp.common.utils.FileUtils;
//
//import java.io.File;
//
//import okhttp3.ResponseBody;
//
//public abstract class FileObsever extends BaseObserver<ResponseBody> {
//    private String path;
//
//    public FileObsever(BaseView view, String path) {
//        super(view);
//        this.path = path;
//    }
//
//    @Override
//    protected void onStart() {
//    }
//
//    @Override
//    public void onComplete() {
//    }
//
//    @Override
//    public void onSuccess(ResponseBody o) {
//
//    }
//
//    @Override
//    public void onError(String msg) {
//
//    }
//
//    @Override
//    public void onNext(ResponseBody o) {
//        File file = FileUtils.saveFile(path, o);
//        if (file != null && file.exists()) {
//            onSuccess(file);
//        } else {
//            onErrorMsg("file is null or file not exists");
//        }
//    }
//
//    @Override
//    public void onError(Throwable e) {
//        onErrorMsg(e.toString());
//    }
//
//
//    public abstract void onSuccess(File file);
//
//    public abstract void onErrorMsg(String msg);
//}
