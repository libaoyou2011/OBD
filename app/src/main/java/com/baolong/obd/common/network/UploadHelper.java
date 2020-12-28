package com.baolong.obd.common.network;

import android.net.Uri;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 包名：com.baolong.edsp.common.network
 * <p>
 * 文件名：   UploadHelper
 * <p>
 * 创建时间：   2018/7/19 18:50
 * <p>
 * 作者：
 * <p>
 * 作用：图文上传工具类
 */

public class UploadHelper {

    private volatile static UploadHelper mInstance;
    public static Map<String, RequestBody> params;
    private Uri photoUri;

    private UploadHelper() {
    }

    //单例模式
    public static UploadHelper getInstance() {
        if (mInstance == null) {
            synchronized (UploadHelper.class) {
                if (mInstance == null)
                    mInstance = new UploadHelper();
                params = new HashMap<>();
            }
        }
        return mInstance;
    }

    //根据传进来的Object对象来判断是String还是File类型的参数
    public UploadHelper addParameter(String key, Object o) {
        RequestBody body = null;
        if (o instanceof String) {
            body = RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"), (String) o);
        } else if (o instanceof File) {
            body = RequestBody.create(MediaType.parse("multipart/form-data;charset=UTF-8"), (File) o);
        }
        params.put(key, body);
        return this;
    }

    //建造者模式
    public Map<String, RequestBody> builder() {
        return params;
    }

    //清除参数
    public void clear() {
        params.clear();
    }

}

