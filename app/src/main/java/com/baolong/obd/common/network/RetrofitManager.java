package com.baolong.obd.common.network;


import android.annotation.SuppressLint;

import com.baolong.obd.common.sharepreferemces.UserSP;
import com.baolong.obd.common.utils.LogUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private static final String TAG = RetrofitManager.class.getSimpleName();
    public static String BASE_URL = "";
    //public static String BASE_IMG_URL = "";
    //public static String SHOP_BASE_IMG_URL = "";

    public static String mAppId = "231997168209100881";
    public static String mUseName = "yuzhanli";
    private static final int DEFAULT_TIME = 30;  //单位秒，服务器性能不行，设置长点如120s

    private Retrofit mRetrofit;

    // 单例
    private static RetrofitManager mInstance;

    // 私有构造方法
    private RetrofitManager() {
        initRetrofit();
    }

    // 获取单例方法
    public static RetrofitManager getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitManager.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitManager();
                }
            }
        }
        return mInstance;
    }

    private void initRetrofit() {
        // 初始化-日志控制
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印日志
                LogUtil.i(TAG, "HttpLoggingInterceptor = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //解析日期 DATE
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .create();


        // 初始化-OkHttp
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS)//设置请求超时时间
                .readTimeout(DEFAULT_TIME, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(DEFAULT_TIME, TimeUnit.SECONDS)//设置写入超时时间
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接
                .addInterceptor(new MyInterceptor())//添加拦截器
//                .addInterceptor(new AddCookiesInterceptor()//添加Cookies
//                .addInterceptor(new ReceivedCookiesInterceptor(BaseApplication.getInstance())) //获取Cookies
                .addInterceptor(loggingInterceptor)
                .build();

        // 初始化Retrofit
        this.mRetrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(UserSP.getStationLocalHost())
                .addConverterFactory(GsonConverterFactory.create(gson)) //添加Gson解析
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        LogUtil.i(TAG, "init Retrofit and host:" + UserSP.getStationLocalHost());

    }

    // 重启单例
    public static void restartRetrofitManager() {
        synchronized (RetrofitManager.class) {
            RetrofitManager.mInstance = new RetrofitManager();
        }
    }

    //返回一个泛型类
    public <T> T createReq(Class<T> paramClass) {
        return (T) this.mRetrofit.create(paramClass);
    }

    //拦截器
    private static class ResponseInterceptor implements Interceptor {
        //构造方法
        private ResponseInterceptor() {
        }

        @Override
        public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
            okhttp3.Response response = chain.proceed(chain.request());

//            if (response.networkResponse().request().url().toString().contains("login")){
//                LogUtil.i(TAG, "cookie 过期！");
//
//                UserSP.clearData();
//                Intent intent = new Intent(BaseApplication.getInstance(), LoginActivity1.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                BaseApplication.getInstance().startActivity(intent);
//
//            }

//            response.code();
            // 注意：OkHttp请求回调中response.body().string()只能有效调用一次
            // 注意：否则出现异常：okhttp的java.lang.IllegalStateException: closed错误

            //Log.v("OkHttp", "Received header: " + response.headers().toString());
            //Log.v("OkHttp", "Received Body: " + response.body().string());
            return response;
        }
    }


    private static class DateDeserializer implements JsonDeserializer<Date> {

        @SuppressLint("SimpleDateFormat")
        private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json != null) {
                final String jsonString = json.getAsString();
                try {
                    return dateFormat.parse(jsonString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                final long jsonLong = json.getAsLong();
                try {
                    return new Date(jsonLong);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

}
