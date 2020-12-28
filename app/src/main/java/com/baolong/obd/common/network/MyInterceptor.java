package com.baolong.obd.common.network;

import com.baolong.obd.common.sharepreferemces.UserSP;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json;charset=UTF-8")
                .addHeader("Authorization", UserSP.getCookie())
                //.addHeader("Cookie", UserSP.getCookie())
                .build();
        return chain.proceed(request);
    }

}