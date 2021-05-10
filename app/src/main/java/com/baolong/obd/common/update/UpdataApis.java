package com.baolong.obd.common.update;

import com.baolong.obd.common.network.ResponseWrapperList;
import com.baolong.obd.common.update.model.CheckVersionResponseModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public class UpdataApis {
    public static abstract interface CheckVersion {
        @GET("/modules/appbb/app/list")
        public abstract Observable<ResponseWrapperList<CheckVersionResponseModel>> req(@Query("platform") String paramString1,
                                                                                       @Query("category") String paramString2,
                                                                                       @Query("versionCode") String paramString3);

//        public abstract Observable<ResponseWrapper<CheckVersionResponseModel>> req(@Body RequestBody requestBody);
    }
}

