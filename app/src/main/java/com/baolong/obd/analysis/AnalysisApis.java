package com.baolong.obd.analysis;

import com.baolong.obd.analysis.data.entity.CQJRModel;
import com.baolong.obd.analysis.data.entity.CarFlowModel;
import com.baolong.obd.analysis.data.entity.CarNumModel;
import com.baolong.obd.analysis.data.entity.DataModel;
import com.baolong.obd.analysis.data.entity.JCJLModel;
import com.baolong.obd.analysis.data.entity.NOxModel;
//import com.baolong.obd.blackcar.data.entity.ResponseBlackCountModel;
import com.baolong.obd.common.network.ResponseWrapperList;
import com.baolong.obd.common.network.ResponseWrapperListOld;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public class AnalysisApis {
    /*
     * 统计：1.车辆（车企）接入统计
     */
    public static abstract interface GEtAnalysisCQJR {
        //@GET("http://10.10.10.243:8383/prod-api/modules/zcsjfx/cljrtj")
        @GET("/modules/zcsjfx/app/cljrtj")
        public abstract Observable<ResponseWrapperListOld<CQJRModel>> req(
                @Query("type") String paramString6 //type=clzs、zxsl、lxsl、bjsl
        );

    }

    /*
     * 统计：2.监测记录统计
     */
    public static abstract interface GEtAnalysisJCJL {
        //@GET("http://10.10.10.243:8383/prod-api/modules/zcsjfx/zcpfDataBymonth")
        @GET("/modules/zcsjfx/app/zcpfDataBymonth")
        public abstract Observable<ResponseWrapperListOld<JCJLModel>> req(
                @Query("yearxz") String paramString1,
                @Query("hphm") String paramString2,
                @Query("beginTime") String paramString3,
                @Query("endTime") String paramString4
        );

    }

    /*
     * 统计：3.NOx排放统计
     */
    public static abstract interface GEtAnalysisNOx {
        //@GET("http://10.10.10.243:8383/prod-api/modules/zcsjfx/findNox")
        @GET("/modules/zcsjfx/app/findNox")
        public abstract Observable<ResponseWrapperListOld<NOxModel>> req(
                @Query("hphm") String paramString1,
                @Query("beginTime") String paramString2,
                @Query("endTime") String paramString3
        );

    }


    /*
     * 数据统计分析
     */
    public static abstract interface GEtAnalysisData {
        @FormUrlEncoded
        @POST("/modules/analyze/dataStatisticalAppjk")
        public abstract Observable<ResponseWrapperList<DataModel>> req(
                @Field("dwbh") String paramString6, //点位名称 字符串
                @Field("beginTime") String paramString19,   //超标开始时间 yyyy-MM-dd HH:mm:ss Date
                @Field("endTime") String paramString20);    //超标结束时间 yyyy-MM-dd HH:mm:ss Date
    }

    /*
     * 车流量统计分析
     */
    public static abstract interface GEtAnalysisCarFlow {
        @FormUrlEncoded
        @POST("/modules/analyze/carFlowAnalyzeAppjk")
        public abstract Observable<ResponseWrapperList<CarFlowModel>> req(
                @Field("dwbh") String paramString6, //点位名称 字符串
                @Field("beginTime") String paramString19,   //超标开始时间 yyyy-MM-dd HH:mm:ss Date
                @Field("endTime") String paramString20);    //超标结束时间 yyyy-MM-dd HH:mm:ss Date
    }


    /*
     * 超标数据统计分析——NO
     */
    public static abstract interface GEtAnalysisNoExceed {
        @FormUrlEncoded
        @POST("/modules/analyze/NOanalyzeDataAppjk")
        public abstract Observable<ResponseWrapperList<DataModel>> req(
                @Field("dwbh") String paramString6, //点位名称 字符串
                @Field("beginTime") String paramString19,   //超标开始时间 yyyy-MM-dd HH:mm:ss Date
                @Field("endTime") String paramString20);    //超标结束时间 yyyy-MM-dd HH:mm:ss Date
    }

    /*
     * 超标数据统计分析——CO
     */
    public static abstract interface GEtAnalysisCoExceed {
        @FormUrlEncoded
        @POST("/modules/analyze/COanalyzeDataAppjk")
        public abstract Observable<ResponseWrapperList<DataModel>> req(
                @Field("dwbh") String paramString6, //点位名称 字符串
                @Field("beginTime") String paramString19,   //超标开始时间 yyyy-MM-dd HH:mm:ss Date
                @Field("endTime") String paramString20);    //超标结束时间 yyyy-MM-dd HH:mm:ss Date
    }

    /*
     * 超标数据统计分析——HC
     */
    public static abstract interface GEtAnalysisHcExceed {
        @FormUrlEncoded
        @POST("/modules/analyze/HCanalyzeDataAppjk")
        public abstract Observable<ResponseWrapperList<DataModel>> req(
                @Field("dwbh") String paramString6, //点位名称 字符串
                @Field("beginTime") String paramString19,   //超标开始时间 yyyy-MM-dd HH:mm:ss Date
                @Field("endTime") String paramString20);    //超标结束时间 yyyy-MM-dd HH:mm:ss Date
    }

    /*
     * 车牌号码统计
     */
    public static abstract interface GEtAnalysisCarNum {
        @FormUrlEncoded
        @POST("/modules/analyze/carAscriptionAppjk")
        public abstract Observable<ResponseWrapperList<CarNumModel>> req(
                @Field("dwbh") String paramString6, //点位名称 字符串
//                @Field("cpys") String paramString7, //车牌颜色 字符串 (0-蓝牌 1-黄牌 2-白牌 3-黑牌)
//                @Field("siteInfo.hphm") String paramString8, //号牌号码 字符串
//                @Field("placeBelong") String paramString9, //车辆归属地 字符串 (0-本地车，1-外地车 )
//                @Field("rlzl") String paramString10,    //燃料种类 字符串 (A-汽油 B-柴油 C-其他 字符串)
//                @Field("jsdstatus") String paramString11,   //车辆加速度判断 字符串 (1-大于 2-小于 3-等于)
//                @Field("cljsd") BigDecimal paramString12,       //车辆加速度 BigDecimal
//                @Field("clsdstatus") String paramString13,      //车辆速度判断 字符串 (1-大于 2-小于 3-等于)
//                @Field("clsd") BigDecimal paramString14,            //车辆速度 BigDecimal
//                @Field("lgmhdstatus") String paramString15,  //林格曼黑度判断 字符串 (1-大于 2-小于 3-等于)
//                @Field("lgmhd") Integer paramString16,        //林格曼黑度 Integer
//                @Field("judge") String paramString17,   //超标次数判断 字符串 (1-大于 2-小于 3-等于)
//                @Field("number") String paramString18,  //超标次数 字符串
                @Field("beginTime") String paramString19,   //超标开始时间 yyyy-MM-dd HH:mm:ss Date
                @Field("endTime") String paramString20);    //超标结束时间 yyyy-MM-dd HH:mm:ss Date
    }


}
