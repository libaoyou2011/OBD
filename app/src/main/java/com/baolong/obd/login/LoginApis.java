package com.baolong.obd.login;

import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.login.model.AccessTokenResponseModel;
import com.baolong.obd.login.model.ResponseLoginModel;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public class LoginApis {
    public static abstract interface GetToken {
        @FormUrlEncoded
        @POST("blzxjcpt/dc/auth/access_token")
        public abstract Observable<ResponseWrapper<AccessTokenResponseModel>> req(@Field("appid") String paramString1, @Field("username") String paramString2);
    }

    public static abstract interface Login {
        @FormUrlEncoded
        @POST("blzxjcpt/api/app/userLogin")
        public abstract Observable<ResponseWrapper> req(@Field("token") String paramString1, @Field("username") String paramString2, @Field("appid") String paramString3, @Field("loginname") String paramString4, @Field("password") String paramString5);
    }

    public static abstract interface Login2 {
        //V1版本中使用
        // public abstract Observable<ResponseLoginModel> req(@Field("username") String paramString1, @Field("password") String paramString2, @Field("rememberMe") Boolean paramString3);

        //V2版本中使用
        // @FormUrlEncoded
        // @POST("login")
        // public abstract Observable<ResponseLoginModel> req(@Field("username") String paramString1, @Field("password") String paramString2, @Field("rememberMe") Boolean paramString3);

        //V3版本中使用
        //@Headers({ "Content-Type: application/json;charset=UTF-8"}) //在 MyInterceptor中统一添加 headers
        @POST("/app/login")
        public abstract Observable<ResponseLoginModel> req(@Body RequestBody requestBody);

        // body可以使用两种方式
        // 方式一：直接传递java对象这个对象是能够被gson解析成json字符串的，
        // public abstract Observable<ResponseLoginModel> req(@Body Login_User obj);
        // 方式二：传递RequestBody对象，其实不管是那种方式最后传出去的参数都是json格式的
        // public abstract Observable<ResponseLoginModel> req(@Body RequestBody requestBody);

    }

    public static abstract interface AutoLogin {
        @FormUrlEncoded
        @POST("login")
        public abstract Observable<ResponseLoginModel> req(@Field("username") String paramString1, @Field("password") String paramString2, @Field("rememberMe") Boolean paramString3);
    }
}
