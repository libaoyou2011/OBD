package com.baolong.obd.common.file.delFile;

import android.util.Log;

import com.google.gson.Gson;
import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.common.sharepreferemces.UserSP;
import com.baolong.obd.common.utils.AESUtil;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class DelFileImpl {
    public void delFile(final String s, final String s2, final DelFileListener delFileListener) {
        final StringBuffer sb = new StringBuffer();
        sb.append("token=");
        sb.append(UserSP.getUserToken());
        sb.append("&fileName=");
        sb.append(s2);
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("token", UserSP.getUserToken());
        hashMap.put("fileName", s2);
        try {
            ((DelFileApis.DeleteFile) RetrofitManager.getInstance().createReq(DelFileApis.DeleteFile.class))
                    .delete(s, AESUtil.encrypt(new Gson().toJson((Object) hashMap)))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Observer<ResponseWrapper>() {
                        public void onCompleted() {
                        }

                        public void onError(final Throwable t) {
                            t.printStackTrace();
                            final StringBuilder sb = new StringBuilder();
                            sb.append("onError: ");
                            sb.append(t.getMessage());
                            Log.i("dj", sb.toString());
                        }

                        public void onNext(final ResponseWrapper responseWrapper) {
                            final StringBuilder sb = new StringBuilder();
                            sb.append("onNext: ");
                            sb.append(responseWrapper.toString());
                            Log.i("dj", sb.toString());
                            if (delFileListener == null) {
                                return;
                            }
                            if (responseWrapper.getCode() == 0) {
                                delFileListener.onComplete();
                                return;
                            }
                            delFileListener.onError(responseWrapper.getCode(), responseWrapper.getError());
                        }
                    });
        } catch (NoSuchPaddingException ex) {
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException ex2) {
            ex2.printStackTrace();
        } catch (BadPaddingException ex3) {
            ex3.printStackTrace();
        } catch (InvalidKeyException ex4) {
            ex4.printStackTrace();
        } catch (IllegalBlockSizeException ex5) {
            ex5.printStackTrace();
        } catch (UnsupportedEncodingException ex6) {
            ex6.printStackTrace();
        }
    }
}
