package com.baolong.obd.common.update;

import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.update.model.CheckVersionRequestModel;
import com.baolong.obd.common.update.model.CheckVersionResponseModel;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public class UpdataApis {
    public static abstract interface CheckVersion {
        @POST("selectLatestVersion")
        public abstract Observable<ResponseWrapper<CheckVersionResponseModel>> req(@Body CheckVersionRequestModel paramCheckVersionRequestModel);
    }
}

