package com.baolong.obd.monitor;

import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.blackcar.data.entity.SiteVideoInfo;
import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.ResponseWrapperList;
import com.baolong.obd.common.network.ResponseWrapperListOld;
import com.baolong.obd.monitor.data.entity.GetMonitoringDataDetailNewModel;
import com.baolong.obd.monitor.data.entity.GetMonitoringDataDetailResponseModel;
import com.baolong.obd.monitor.data.entity.GetStationListAllResponseModel;
import com.baolong.obd.monitor.data.entity.GetTodayAmountModel2;
import com.baolong.obd.monitor.data.entity.SiteAqiInfoItemV3;
import com.baolong.obd.monitor.data.entity.SiteInfoItemV3;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public class MonitorApis {
    public static abstract interface GetMonitoringDataDetail {
        @FormUrlEncoded
        @POST("blzxjcpt/api/app/getMonitoringDataDetail")
        public abstract Observable<ResponseWrapper<GetMonitoringDataDetailResponseModel>> req(@Field("token") String paramString1, @Field("username") String paramString2, @Field("appid") String paramString3, @Field("jlid") String paramString4);
    }

    public static abstract interface GetMonitoringDataDetailNew {
        @FormUrlEncoded
        @POST("blzxjcpt/api/app/getMonitoringDataDetail")
        public abstract Observable<ResponseWrapperListOld<GetMonitoringDataDetailNewModel>> req(@Field("token") String paramString1, @Field("username") String paramString2, @Field("appid") String paramString3, @Field("jlid") String paramString4);
    }

    //指定点位今日采集数据（1合格、0不合格）
    public static abstract interface GetMonitoringDataList {
        //V2版本
        // @FormUrlEncoded
        // @POST("/modules/exhaust/app/listToday")
        // public abstract Observable<ResponseWrapper<ResponseExhaustListModel>> req(@Field("dwbh") String paramString1, @Field("pdjg") String paramString2, @Field("pageSize") String paramString3, @Field("pageNum") String paramString4, @Field("orderByColumn") String paramString5, @Field("isAsc") String paramString6);

        //V3版本
        @GET("/modules/exhaust/app/listToday")
        public abstract Observable<ResponseWrapperList<Exhaust>> req(@Query("dwbh") String paramString1,
                                                                     @Query("pdjg") String paramString2,
                                                                     @Query("pageSize") int paramString3, //每页显示记录数 int类型 必填
                                                                     @Query("pageNum") int paramString4,  //当前记录起始索引  int类型  必填
                                                                     @Query("orderByColumn") String paramString5, //排序列 字符串 必填
                                                                     @Query("isAsc") String paramString6);   //排序方向 “desc”或“asc”
    }


    public static abstract interface GetSiteListAll {
        // V1版本
        // @FormUrlEncoded
        // @POST("blzxjcpt/api/app/getSiteListAll")
        // public abstract Observable<ResponseWrapperList<GetStationListAllResponseModel>> req(@Field("token") String paramString1, @Field("username") String paramString2, @Field("appid") String paramString3);

        // V2版本
        // @POST("/system/siteinfo/app/getSiteInfo")
        // public abstract Observable<ResponseWrapperList<SiteInfoItem>> req();

        // V3版本
        @GET("/modules/siteinfo/app/list")
        public abstract Observable<ResponseWrapperList<SiteInfoItemV3>> req();
    }

    //点位状态预警
    public static abstract interface GetWarningSites {
        @GET("/modules/siteinfo/app/list")
        public abstract Observable<ResponseWrapperList<SiteInfoItemV3>> req();
    }

    public static abstract interface GetAqiListAll {
        @POST("/modules/siteinfo/app/v2/getSiteInfo")
        public abstract Observable<ResponseWrapperListOld<SiteAqiInfoItemV3>> req();
    }

    public static abstract interface GetStationQuery {
        // @FormUrlEncoded
        // @POST("blzxjcpt/api/app/getStationQuery")
        // public abstract Observable<ResponseWrapperList<GetStationListAllResponseModel>> req(@Field("token") String paramString1, @Field("username") String paramString2, @Field("appid") String paramString3, @Field("jzmc") String paramString4);
        @FormUrlEncoded
        @POST("blzxjcpt/api/app/getSiteListAll")
        public abstract Observable<ResponseWrapperListOld<GetStationListAllResponseModel>> req(@Field("token") String paramString1, @Field("username") String paramString2, @Field("appid") String paramString3, @Field("jzmc") String paramString4);

    }

    //今日检测数据量
    public static abstract interface GetTodayAmount {
        @POST("/modules/map/app/getAppJczl")
        public abstract Observable<ResponseWrapperListOld<GetTodayAmountModel2>> req();
    }

    //该点位-今日检测数据量
    public static abstract interface GetSiteTodayAmount {
        @POST("/modules/map/app/getAppJczlOne")
        public abstract Observable<ResponseWrapperListOld<GetTodayAmountModel2>> req(@Body RequestBody requestBody);
    }

    //点位球机
    public static abstract interface GetSiteVideo {
        @GET("/modules/sitevideo/app/{dwbh}")
        public abstract Observable<ResponseWrapper<SiteVideoInfo>> req(@Path("dwbh") String dwbh);
    }
}
