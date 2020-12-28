package com.baolong.obd.common.file.delFile;

import com.baolong.obd.common.network.ResponseWrapper;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

public class DelFileApis
{
    public static abstract interface DeleteFile
    {
        @FormUrlEncoded
        @POST
        public abstract Observable<ResponseWrapper> delete(@Url String paramString1, @Field("x") String paramString2);
    }
}
