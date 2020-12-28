package com.baolong.obd.common.file.uploadFile;

import com.google.gson.Gson;
import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.ResponseWrapperListOld;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.common.sharepreferemces.UserSP;
import com.baolong.obd.common.utils.AESUtil;
import com.baolong.obd.common.utils.ToastUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UpLoadFileImpl {

    public void deleteFile(final String s, final Observer<? super ResponseWrapper<Object>> observer) {
        final HashMap<String, String> hashMap = new HashMap<String, String>(2);
        hashMap.put("token", UserSP.getUserToken());
        hashMap.put("fileUri", s);
        RetrofitManager.getInstance().<UpLoadFileApis.UploadFiles>createReq(UpLoadFileApis.UploadFiles.class).deleteFile(hashMap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Observer) observer);
    }

    public void uploadFiles(final String s, final Map<String, String> map, final UpLoadFileListener upLoadFileListener, final File... array) {
        final HashMap<String, UploadFileRequestBody> hashMap = new HashMap<String, UploadFileRequestBody>();
        try {
            final MediaType parse = MediaType.parse("multipart/form-data");
            final StringBuilder sb = new StringBuilder();
            sb.append("A");
            sb.append(AESUtil.encrypt(new Gson().toJson((Object) map)));
            hashMap.put("x", (UploadFileRequestBody) RequestBody.create(parse, sb.toString()));
        } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        final FileUploadObserver<ResponseWrapperListOld<NewContractPhotoModel>> fileUploadObserver = new FileUploadObserver<ResponseWrapperListOld<NewContractPhotoModel>>() {
            public void onCompleted() {
            }

            public void onError(final Throwable t) {
                t.printStackTrace();
                upLoadFileListener.onError(t.hashCode(), t.getMessage());
                ToastUtils.shortToast(t.getMessage());
            }

            public void onNext(final ResponseWrapperListOld<NewContractPhotoModel> list) {
                if (upLoadFileListener == null) {
                    return;
                }
                if (list.getCode() == 0) {
                    upLoadFileListener.onComplete(list);
                    return;
                }
                upLoadFileListener.onError(list.getCode(), list.getError());
            }

            @Override
            public void onProgress(final File file, final int n) {
                if (upLoadFileListener != null) {
                    upLoadFileListener.onProgress(file, n);
                }
            }
        };
        for (int length = array.length, i = 0; i < length; ++i) {
            final File file = array[i];
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("file\"; filename=\"");
            sb2.append(file.getPath());
            hashMap.put(sb2.toString(), new UploadFileRequestBody(file, fileUploadObserver));
        }
        ((UpLoadFileApis.UploadFiles)RetrofitManager.getInstance()
                .createReq(UpLoadFileApis.UploadFiles.class))
                .uploadFiles(s, hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Observer) fileUploadObserver);
    }
}

