package com.baolong.obd.common.network;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.sharepreferemces.UserSP;
import com.baolong.obd.login.LoginActivityV2;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.Interceptor;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

//首次请求的处理：
public class ReceivedCookiesInterceptor implements Interceptor {
    private static final String TAG = ReceivedCookiesInterceptor.class.getSimpleName();

    private final Context context;

    public ReceivedCookiesInterceptor(Context context) {
        super();
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (chain == null) {
            Log.d(TAG, "Received_chain == null");
        }
        Response originalResponse = chain.proceed(chain.request());
        Log.d(TAG, "originalResponse: " + originalResponse.toString());
        // 其他接口访问成功
        // originalResponse: Response{protocol=http/1.1, code=200, message=, url=http://10.10.10.243:8181/modules/blackcarinfo/list}
        // 登录成功 || 其他接口访问失败
        // originalResponse: Response{protocol=http/1.1, code=200, message=, url=http://10.10.10.243:8181/login}

        // 接受并保存cookie (注：set-cookie 只有在首次请求 || cookie失效时  才会返回)
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            // Set-Cookie: JSESSIONID=4990a71a-82ed-42d5-a6b0-a65c3a73ac4f; Path=/; HttpOnly
            // Set-Cookie: rememberMe=deleteMe; Path=/; Max-Age=0; Expires=Thu, 07-Nov-2019 06:00:33 GMT
            // Set-Cookie: rememberMe=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; Path=/; Max-Age=2592000; Expires=Sun, 08-Dec-2019 06:00:33 GMT; HttpOnly

            final StringBuffer tempCookieBuffer = new StringBuffer();
            Observable.from(originalResponse.headers("Set-Cookie"))  //返回3个List，每个List里是key-value键值对集合
                    .map(new Func1<String, String>() {
                        @Override
                        public String call(String s) {
                            String[] cookieArray = s.split(";");
                            Log.d(TAG, "originalResponse --- cookieArray: " + Arrays.toString(cookieArray));
                            Log.d(TAG, "originalResponse --- cookieArray[0]: " + cookieArray[0]);

                            //if (tempCookieBuffer.length() == 0) {
                            if (cookieArray.length > 0) {
                                return cookieArray[0];
                            } else {
                                return null;
                            }
                        }
                    })
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String cookie) {
                            tempCookieBuffer.append(cookie).append(";");
                        }
                    });

            Log.d(TAG, "cookie:" + tempCookieBuffer.toString());
            //cookie:JSESSIONID=f0025c4a-5739-456c-b8e8-687d4704ca07;rememberMe=deleteMe;rememberMe=U520K...;

            // cookie转成数组
            String[] cookieArray = tempCookieBuffer.toString().split(";");
            Log.d(TAG, "cookieSize:" + cookieArray.length);

            // cookie获取需要的部分
            StringBuilder needCookieBuilder = new StringBuilder();
            if (cookieArray.length > 1) {
                //添加 rememberMe 字段
                needCookieBuilder.append(cookieArray[cookieArray.length - 1]);
                needCookieBuilder.append("; ");
                //添加 Cookie 字段
                needCookieBuilder.append(cookieArray[0]);
            } else if (cookieArray.length > 0) {
                //添加之前旧的 rememberMe 字段
                if (!TextUtils.isEmpty(BaseApplication.cookie)) {
                    String[] oldCookieArray = BaseApplication.cookie.split(";");
                    if (oldCookieArray.length > 0) {
                        needCookieBuilder.append(oldCookieArray[0]);
                        needCookieBuilder.append("; ");
                    }
                }
                //添加 Cookie 字段
                needCookieBuilder.append(cookieArray[0]);
            }
            Log.d(TAG, "needCookie:" + needCookieBuilder);


            if (cookieArray.length == 3) {  //登录成功 [JSESSIONID=、rememberMe=、rememberMe=]
                // 保存cookie
                UserSP.setCookie(needCookieBuilder.toString());
                // 首次登录时，cookie在BaseApplication通过SharedPreferences初始化为空，
                // 为了保证cookie在BaseApplication中有值，在此赋值
                BaseApplication.cookie = needCookieBuilder.toString();

            } else if (cookieArray.length == 1 || cookieArray.length == 2) {  //其它接口访问失败, cookie 过期
                //Log.d(TAG, "需要重新获取登录cookie！");
                Log.d(TAG, "cookie过期，保存刷新后的cookie！");

                // 保存cookie
                UserSP.setCookie(needCookieBuilder.toString());
                // 首次登录时，cookie在BaseApplication通过SharedPreferences初始化为空，
                // 为了保证cookie在BaseApplication中有值，在此赋值
                BaseApplication.cookie = needCookieBuilder.toString();

            } else {    // cookie 其它异常情况
                Log.d(TAG, "cookie其他情况异常！");

                // 清除账户信息
                UserSP.clearData();
                BaseApplication.cookie = null;

                // 跳转到登录页面
                Intent intent = new Intent();
                intent.setClass(context, LoginActivityV2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }

        }

        return originalResponse;
    }

}


