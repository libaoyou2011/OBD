package com.baolong.obd.analysis;

import com.baolong.obd.analysis.data.entity.CarFlowModel;
import com.baolong.obd.analysis.data.entity.CarNumModel;
import com.baolong.obd.analysis.data.entity.DataModel;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
//import com.baolong.obd.blackcar.data.entity.ResponseBlackCountModel;
import com.baolong.obd.blackcar.data.entity.ResponseExhaustListModel;
import com.baolong.obd.blackcar.data.entity.ResponseVehicleInfoListModel;
import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.ResponseWrapperList;

import java.math.BigDecimal;
import java.util.Date;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public class AnalysisApis {
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
