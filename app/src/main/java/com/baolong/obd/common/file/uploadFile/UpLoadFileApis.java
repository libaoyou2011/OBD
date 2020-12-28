package com.baolong.obd.common.file.uploadFile;

import retrofit2.http.Multipart;

import com.baolong.obd.common.network.ResponseWrapperListOld;

import retrofit2.http.PartMap;

import java.util.Map;

import retrofit2.http.Url;
import retrofit2.http.POST;

import com.baolong.obd.common.network.ResponseWrapper;

import rx.Observable;
import retrofit2.http.Body;

import java.util.HashMap;

public class UpLoadFileApis {

    public interface UploadFiles {
        @POST("upload/deleteFile")
        Observable<ResponseWrapper<Object>> deleteFile(@Body final HashMap<String, String> p0);

        @Multipart
        @POST
        Observable<ResponseWrapperListOld<NewContractPhotoModel>> uploadFiles(@Url final String p0, @PartMap final Map<String, UploadFileRequestBody> p1);
    }
}