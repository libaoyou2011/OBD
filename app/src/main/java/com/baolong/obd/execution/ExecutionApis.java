package com.baolong.obd.execution;

import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.ResponseWrapperList;
import com.baolong.obd.execution.data.entity.GetStatisticCountModel;
import com.baolong.obd.execution.data.entity.GetUploadImgResponseModel;
import com.baolong.obd.execution.data.entity.SearchMonitoringDataResponseModel;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import rx.Observable;

public class ExecutionApis {
    //超标车处罚
    public static abstract interface AddPunish {
        @PUT("/modules/overproof/app/punish")
        public abstract Observable<ResponseWrapper<String>> req(@Body RequestBody requestBody);
    }

    //统计：今日检测数据量
    public static abstract interface GetStatisticalExecution {
        @GET("/modules/overproof/app/selectcount")
        public abstract Observable<ResponseWrapper<GetStatisticCountModel>> req();
    }

    // 接口: 1.超标_未处罚
    public static abstract interface GetExeWcfList {
        // Get方法不能用下面的方法
        // @FormUrlEncoded
        // @FieldMap Map<String, Object> map
        // 改为 ,@Query("scheme_id") String scheme_id
        @GET("/modules/overproof/app/wcflist")
        public abstract Observable<ResponseWrapperList<Exhaust>> req(@Query("pdjg") String paramString1, //判断结果 字符串 必填 0-不合格 1-合格 2 无效
                                                                     @Query("sfcf") String paramString2, //处罚状态 int类型 必填 0-未处罚 1-已处罚
                                                                     @Query("pageSize") int paramString3, //每页显示记录数 int类型 必填
                                                                     @Query("pageNum") int paramString4,  //当前记录起始索引  int类型  必填
                                                                     @Query("orderByColumn") String paramString5, //排序列 字符串 必填
                                                                     @Query("isAsc") String paramString6,     //排序方向 “desc”或“asc”

                                                                     @Query("dwbhs") String paramString7, //点位名称 字符串
                                                                     @Query("cpys") String paramString8,  //车牌颜色 字符串 (0-蓝牌 1-黄牌 2-白牌 3-黑牌)
                                                                     @Query("hphm") String paramString9,  //号牌号码 字符串
                                                                     @Query("clgs") String paramString10, //车辆归属地 字符串 (0-本地车，1-外地车 )
                                                                     @Query("rlzl") String paramString11, //燃料种类 字符串 (A-汽油 B-柴油 C-其他 字符串)

                                                                     @Query("cojgysf") String paramString12,
                                                                     @Query("cojg") Float paramString13,
                                                                     @Query("nojgysf") String paramString14,
                                                                     @Query("nojg") Float paramString15,
                                                                     @Query("hcjgysf") String paramString16,
                                                                     @Query("hcjg") Float paramString17,
                                                                     @Query("btgdjgysf") String paramString18,
                                                                     @Query("btgdjg") Float paramString19,
                                                                     @Query("co2sczysf") String paramString20,
                                                                     @Query("co2scz") Float paramString21,

                                                                     @Query("fsysf") String paramString22,
                                                                     @Query("fs") Float paramString23,
                                                                     @Query("vspysf") String paramString24,
                                                                     @Query("vsp") Float paramString25,
                                                                     @Query("hjwdysf") String paramString26,
                                                                     @Query("hjwd") Float paramString27,
                                                                     @Query("sdysf") String paramString28,
                                                                     @Query("sd") Float paramString29,
                                                                     @Query("dqyysf") String paramString30,
                                                                     @Query("dqy") Float paramString31,

                                                                     @Query("cbcsysf") String paramString32, //超标次数判断 字符串 (1-大于 2-小于 3-等于)
                                                                     @Query("cbcs") Integer paramString33,
                                                                     @Query("beginTime") String paramString34,     //超标开始时间 yyyy-MM-dd HH:mm:ss Date
                                                                     @Query("endTime") String paramString35);      //超标结束时间 yyyy-MM-dd HH:mm:ss Date);
    }

    // 接口: 2.超标_未处罚
    public static abstract interface GetExeYcfList {
        @GET("/modules/overproof/app/list")
        public abstract Observable<ResponseWrapperList<Exhaust>> req(@Query("pdjg") String paramString1, //判断结果 字符串 必填 0-不合格 1-合格 2 无效
                                                                     @Query("sfcf") String paramString2, //处罚状态 int类型 必填 0-未处罚 1-已处罚
                                                                     @Query("pageSize") int paramString3, //每页显示记录数 int类型 必填
                                                                     @Query("pageNum") int paramString4,  //当前记录起始索引  int类型  必填
                                                                     @Query("orderByColumn") String paramString5, //排序列 字符串 必填
                                                                     @Query("isAsc") String paramString6,     //排序方向 “desc”或“asc”

                                                                     @Query("dwbhs") String paramString7, //点位名称 字符串
                                                                     @Query("cpys") String paramString8,  //车牌颜色 字符串 (0-蓝牌 1-黄牌 2-白牌 3-黑牌)
                                                                     @Query("hphm") String paramString9,  //号牌号码 字符串
                                                                     @Query("clgs") String paramString10, //车辆归属地 字符串 (0-本地车，1-外地车 )
                                                                     @Query("rlzl") String paramString11, //燃料种类 字符串 (A-汽油 B-柴油 C-其他 字符串)

                                                                     @Query("cojgysf") String paramString12,
                                                                     @Query("cojg") Float paramString13,
                                                                     @Query("nojgysf") String paramString14,
                                                                     @Query("nojg") Float paramString15,
                                                                     @Query("hcjgysf") String paramString16,
                                                                     @Query("hcjg") Float paramString17,
                                                                     @Query("btgdjgysf") String paramString18,
                                                                     @Query("btgdjg") Float paramString19,
                                                                     @Query("co2sczysf") String paramString20,
                                                                     @Query("co2scz") Float paramString21,

                                                                     @Query("fsysf") String paramString22,
                                                                     @Query("fs") Float paramString23,
                                                                     @Query("vspysf") String paramString24,
                                                                     @Query("vsp") Float paramString25,
                                                                     @Query("hjwdysf") String paramString26,
                                                                     @Query("hjwd") Float paramString27,
                                                                     @Query("sdysf") String paramString28,
                                                                     @Query("sd") Float paramString29,
                                                                     @Query("dqyysf") String paramString30,
                                                                     @Query("dqy") Float paramString31,

                                                                     @Query("cbcsysf") String paramString32, //超标次数判断 字符串 (1-大于 2-小于 3-等于)
                                                                     @Query("cbcs") Integer paramString33,
                                                                     @Query("beginTime") String paramString34,     //超标开始时间 yyyy-MM-dd HH:mm:ss Date
                                                                     @Query("endTime") String paramString35);      //超标结束时间 yyyy-MM-dd HH:mm:ss Date);
    }

    // 接口: 3.超标_所有
    public static abstract interface GetExeOverProofList {
        @GET("/modules/overproof/app/list")
        public abstract Observable<ResponseWrapperList<Exhaust>> req(@Query("pdjg") String paramString1, //判断结果 字符串 必填 0-不合格 1-合格 2 无效
                                                                     @Query("sfcf") String paramString2, //处罚状态 int类型 必填 0-未处罚 1-已处罚
                                                                     @Query("pageSize") int paramString3, //每页显示记录数 int类型 必填
                                                                     @Query("pageNum") int paramString4,  //当前记录起始索引  int类型  必填
                                                                     @Query("orderByColumn") String paramString5, //排序列 字符串 必填
                                                                     @Query("isAsc") String paramString6,     //排序方向 “desc”或“asc”

                                                                     @Query("dwbhs") String paramString7, //点位名称 字符串
                                                                     @Query("cpys") String paramString8,  //车牌颜色 字符串 (0-蓝牌 1-黄牌 2-白牌 3-黑牌)
                                                                     @Query("hphm") String paramString9,  //号牌号码 字符串
                                                                     @Query("clgs") String paramString10, //车辆归属地 字符串 (0-本地车，1-外地车 )
                                                                     @Query("rlzl") String paramString11, //燃料种类 字符串 (A-汽油 B-柴油 C-其他 字符串)

                                                                     @Query("cojgysf") String paramString12,
                                                                     @Query("cojg") Float paramString13,
                                                                     @Query("nojgysf") String paramString14,
                                                                     @Query("nojg") Float paramString15,
                                                                     @Query("hcjgysf") String paramString16,
                                                                     @Query("hcjg") Float paramString17,
                                                                     @Query("btgdjgysf") String paramString18,
                                                                     @Query("btgdjg") Float paramString19,
                                                                     @Query("co2sczysf") String paramString20,
                                                                     @Query("co2scz") Float paramString21,

                                                                     @Query("fsysf") String paramString22,
                                                                     @Query("fs") Float paramString23,
                                                                     @Query("vspysf") String paramString24,
                                                                     @Query("vsp") Float paramString25,
                                                                     @Query("hjwdysf") String paramString26,
                                                                     @Query("hjwd") Float paramString27,
                                                                     @Query("sdysf") String paramString28,
                                                                     @Query("sd") Float paramString29,
                                                                     @Query("dqyysf") String paramString30,
                                                                     @Query("dqy") Float paramString31,

                                                                     @Query("cbcsysf") String paramString32, //超标次数判断 字符串 (1-大于 2-小于 3-等于)
                                                                     @Query("cbcs") Integer paramString33,
                                                                     @Query("beginTime") String paramString34,     //超标开始时间 yyyy-MM-dd HH:mm:ss Date
                                                                     @Query("endTime") String paramString35);      //超标结束时间 yyyy-MM-dd HH:mm:ss Date);
    }

    // 获取处罚数据接口(未处罚、已出发、所有)  V2 全部用POST请求
    /*public static abstract interface GetMonitoringDataListExe {
        @FormUrlEncoded
        @POST("/modules/overproof/app/list")
        //public abstract Observable<ResponseWrapper<GetMonitoringDataListExeResponseModel>> req(@Field("token") String paramString1, @Field("username") String paramString2, @Field("appid") String paramString3, @Field("jzbh") String paramString4, @Field("pdjg") String paramString5, @Field("loginname") String paramString6, @Field("sfycl") String paramString7, @Field("pageNo") String paramString8, @Field("pageSize") String paramString9);
        public abstract Observable<ResponseWrapper<ResponseExhaustListModel>> req(@Field("pdjg") String paramString1, //判断结果 字符串 必填 0-不合格 1-合格 2 无效
                                                                                  @Field("isornopunish") String paramString2, //处罚状态 int类型 必填 0-未处罚 1-已处罚
                                                                                  @Field("pageSize") int paramString3, //每页显示记录数 int类型 必填
                                                                                  @Field("pageNum") int paramString4,  //当前记录起始索引  int类型  必填
                                                                                  @Field("orderByColumn") String paramString5, //排序列 字符串 必填
                                                                                  @Field("isAsc") String paramString6,     //排序方向 “desc”或“asc”

                                                                                  @Field("siteInfo.dwmc") String paramString7, //点位名称 字符串
                                                                                  @Field("cpys") String paramString8,          //车牌颜色 字符串 (0-蓝牌 1-黄牌 2-白牌 3-黑牌)
                                                                                  @Field("siteInfo.hphm") String paramString9, //号牌号码 字符串
                                                                                  @Field("placeBelong") String paramString10, //车辆归属地 字符串 (0-本地车，1-外地车 )
                                                                                  @Field("rlzl") String paramString11,        //燃料种类 字符串 (A-汽油 B-柴油 C-其他 字符串)
                                                                                  @Field("jsdstatus") String paramString12,       //车辆加速度判断 字符串 (1-大于 2-小于 3-等于)
                                                                                  @Field("cljsd") BigDecimal paramString13,       //车辆加速度 BigDecimal
                                                                                  @Field("clsdstatus") String paramString14,  //车辆速度判断 字符串 (1-大于 2-小于 3-等于)
                                                                                  @Field("clsd") BigDecimal paramString15,    //车辆速度 BigDecimal
                                                                                  @Field("lgmhdstatus") String paramString16,    //林格曼黑度判断 字符串 (1-大于 2-小于 3-等于)
                                                                                  @Field("lgmhd") Integer paramString17,         //林格曼黑度 Integer
                                                                                  @Field("judge") String paramString18,     //超标次数判断 字符串 (1-大于 2-小于 3-等于)
                                                                                  @Field("number") String paramString19,    //超标次数 字符串
                                                                                  @Field("beginTime") String paramString20,     //超标开始时间 yyyy-MM-dd HH:mm:ss Date
                                                                                  @Field("endTime") String paramString21);      //超标结束时间 yyyy-MM-dd HH:mm:ss Date
    }*/

    // 接口: 4.遥测数据
    public static abstract interface GetTelemetryDataListExe {
        @GET("/modules/exhaust/app/list")
        public abstract Observable<ResponseWrapperList<Exhaust>> req(@Query("pageSize") int paramString3, //每页显示记录数 int类型 必填
                                                                     @Query("pageNum") int paramString4,  //当前记录起始索引  int类型  必填
                                                                     @Query("orderByColumn") String paramString5, //排序列 字符串 必填
                                                                     @Query("isAsc") String paramString6,     //排序方向 “desc”或“asc”

                                                                     @Query("dwbhs") String paramString7, //点位名称 字符串
                                                                     @Query("cpys") String paramString8,  //车牌颜色 字符串 (0-蓝牌 1-黄牌 2-白牌 3-黑牌)
                                                                     @Query("hphm") String paramString9,  //号牌号码 字符串
                                                                     @Query("clgs") String paramString10, //车辆归属地 字符串 (0-本地车，1-外地车 )
                                                                     @Query("rlzl") String paramString11, //燃料种类 字符串 (A-汽油 B-柴油 C-其他 字符串)

                                                                     @Query("cojgysf") String paramString12,
                                                                     @Query("cojg") Float paramString13,
                                                                     @Query("nojgysf") String paramString14,
                                                                     @Query("nojg") Float paramString15,
                                                                     @Query("hcjgysf") String paramString16,
                                                                     @Query("hcjg") Float paramString17,
                                                                     @Query("btgdjgysf") String paramString18,
                                                                     @Query("btgdjg") Float paramString19,
                                                                     @Query("co2sczysf") String paramString20,
                                                                     @Query("co2scz") Float paramString21,

                                                                     @Query("fsysf") String paramString22,
                                                                     @Query("fs") Float paramString23,
                                                                     @Query("vspysf") String paramString24,
                                                                     @Query("vsp") Float paramString25,
                                                                     @Query("hjwdysf") String paramString26,
                                                                     @Query("hjwd") Float paramString27,
                                                                     @Query("sdysf") String paramString28,
                                                                     @Query("sd") Float paramString29,
                                                                     @Query("dqyysf") String paramString30,
                                                                     @Query("dqy") Float paramString31,

                                                                     @Query("cbcsysf") String paramString32, //超标次数判断 字符串 (1-大于 2-小于 3-等于)
                                                                     @Query("cbcs") Integer paramString33,
                                                                     @Query("beginTime") String paramString34,     //超标开始时间 yyyy-MM-dd HH:mm:ss Date
                                                                     @Query("endTime") String paramString35);      //超标结束时间 yyyy-MM-dd HH:mm:ss Date);
    }


    public static abstract interface InsertMonitoringData {
        @FormUrlEncoded
        @POST("blzxjcpt/api/app/insertMonitoringData")
        public abstract Observable<ResponseWrapper<String>> req(@Field("token") String paramString1, @Field("username") String paramString2, @Field("appid") String paramString3, @Field("loginname") String paramString4, @Field("testNo") String paramString5, @Field("disposePerson") String paramString6, @Field("disposeTime") String paramString7, @Field("siteDetail") String paramString8, @Field("disposalResults") String paramString9, @Field("disposalExplain") String paramString10, @Field("photo") String paramString11, @Field("tp") String paramString12, @Field("co") String paramString13, @Field("co2") String paramString14, @Field("no") String paramString15, @Field("yd") String paramString16, @Field("btgd") String paramString17, @Field("hc") String paramString18);
    }

    public static abstract interface SearchMonitoringData {
        @FormUrlEncoded
        @POST("blzxjcpt/api/app/searchMonitoringData")
        public abstract Observable<ResponseWrapper<SearchMonitoringDataResponseModel>> req(@Field("token") String paramString1, @Field("username") String paramString2, @Field("appid") String paramString3, @Field("testNo") String paramString4);
    }

    public static abstract interface UploadingImges {
        @FormUrlEncoded
        @POST("blzxjcpt/api/app/uploadingFiles")
        public abstract Observable<ResponseWrapper<GetUploadImgResponseModel>> req(@Field("token") String paramString1, @Field("username") String paramString2, @Field("appid") String paramString3, @Field("fileStr") String paramString4, @Field("fileName") String paramString5, @Field("fileType") String paramString6, @Field("loginname") String paramString7);
    }

}
