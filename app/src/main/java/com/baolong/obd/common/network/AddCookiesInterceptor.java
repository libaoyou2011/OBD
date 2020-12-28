package com.baolong.obd.common.network;

import android.util.Log;

import com.baolong.obd.common.sharepreferemces.UserSP;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Action1;

//非首次请求：在请求的 header中，添加cookie
public class AddCookiesInterceptor implements Interceptor {
    private static final String TAG = AddCookiesInterceptor.class.getSimpleName();
    private String lang;

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (chain == null) {
            Log.d("TAG", "Add_chain == null");
        }
        final Request.Builder builder = chain.request().newBuilder();
        Observable.just(UserSP.getCookie())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String cookie) {
                        if (cookie.contains("lang=ch")) {
                            cookie = cookie.replace("lang=ch", "lang=" + lang);
                        }
                        if (cookie.contains("lang=en")) {
                            cookie = cookie.replace("lang=en", "lang=" + lang);
                        }
                        //添加cookie
                        //Log.d(TAG, "cookie:" + cookie);
                        builder.addHeader("Cookie", cookie);
                        //builder.addHeader("Set-Cookie", cookie);
                        /*
                         * Set-Cookie报头包含于Web服务器的响应头（ResponseHeader）中
                         * Cookie报头包含在浏览器客户端请求头（ReguestHeader）中
                         *
                         */

                    }
                });

        Response response = chain.proceed(builder.build());

        return response;
    }
}

