package com.baolong.obd.my;

import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.ResponseWrapperListOld;
import com.baolong.obd.my.data.entity.GetDefaultStationResponseModel;
import com.baolong.obd.my.data.entity.GetStationListAllResponseModel;
import com.baolong.obd.my.data.entity.UserInfoModel;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;

public class MyApis {

    public static abstract interface GetDefaultStation {
        @FormUrlEncoded
        @POST("blzxjcpt/api/app/getDefaultStation")
        public abstract Observable<ResponseWrapper<GetDefaultStationResponseModel>> req(@Field("token") String paramString1, @Field("username") String paramString2, @Field("appid") String paramString3, @Field("loginname") String paramString4);
    }

    public static abstract interface GetStationListAll {
        @FormUrlEncoded
        @POST("blzxjcpt/api/app/getSiteListAll")
        public abstract Observable<ResponseWrapperListOld<GetStationListAllResponseModel>> req(@Field("token") String paramString1, @Field("username") String paramString2, @Field("appid") String paramString3);
    }

    public static abstract interface SetDefaultStation {
        @FormUrlEncoded
        @POST("blzxjcpt/api/app/setDefaultStation")
        public abstract Observable<ResponseWrapper<String>> req(@Field("token") String paramString1, @Field("username") String paramString2, @Field("appid") String paramString3, @Field("loginname") String paramString4, @Field("jzbh") String paramString5, @Field("jzmc") String paramString6);
    }


    public static interface UploadFile {
        @Multipart
        @POST("upload/file")
        public abstract Observer<ResponseWrapper<String>> upload(
                @Part("description") RequestBody requestBody,
                @Part MultipartBody.Part file
        );
    }

    public static interface UploadImage {
        @Multipart
        @POST("upload/image")
        public abstract Observer<ResponseWrapper<String>> upload(@Part("description") RequestBody requestBody);
    }

    public static interface UploadImages {
        @Multipart
        @POST("upload/imaes")
        public abstract Observer<ResponseWrapper<String>> upload(
                @Path("url") String url,
                @Part("filename") String description,
                @PartMap() Map<String, RequestBody> maps
        );
    }

    // 同时传递字符串参数和一张图片的服务接口
    public interface UploadAvatarService {
        @Multipart
        @POST("user/updateAvatar.do")
        Observer<ResponseWrapper<String>> updateAvatar(@Query("des") String description, @Part("uploadFile\"; filename=\"test.jpg\"") RequestBody imgs);
    }

    // 意见反馈和图片上传, common/network/UploadHelper 图片上传工具类
    public interface AdviceReportImagesUpload {
        @Multipart
        @POST("upload/report")
        Observer<ResponseWrapper<String>> upload(@PartMap Map<String, RequestBody> maps);
    }

    public static abstract interface GetUserInfoApi {
        @POST("/system/user/profile/app/getAppprofile")
        public abstract Observable<ResponseWrapperListOld<UserInfoModel>> req();
    }

    public static abstract interface SavedUserInfoApi {
        @POST("/system/user/profile/app/appUpdate")
        public abstract Observable<ResponseWrapper<String>> req(@Body RequestBody requestBody);
    }

    public static abstract interface ResetPassword {
        @FormUrlEncoded
        @POST("/system/user/profile/app/appResetPwd")
        public abstract Observable<ResponseWrapper<String>> req(@Field("oldPassword") String oldPassWord, @Field("newPassword") String newPassword);
    }
}
