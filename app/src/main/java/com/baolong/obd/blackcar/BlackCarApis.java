package com.baolong.obd.blackcar;

import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.blackcar.data.entity.BlackCountModel;
import com.baolong.obd.blackcar.data.entity.ResponseVehicleInfoListModel;
import com.baolong.obd.common.network.ResponseWrapper;
import com.baolong.obd.common.network.ResponseWrapperList;
import com.baolong.obd.common.network.ResponseWrapperListOld;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import rx.Observable;

public class BlackCarApis {

    /*
     * 黑烟车数量
     */
    public static abstract interface GetBlackCarCount {
        @POST("modules/blackcarinfo/selectcount")
        public abstract Observable<BlackCountModel> req();
    }

    /**
     * 黑烟车: 待审核数据
     */
    public static abstract interface GetBlackDshList {
        @GET("/modules/blackcar/app/listbyDsh")
        public abstract Observable<ResponseWrapperList<Exhaust>> req(
                @Query("pdjg") String paramString1,
                @Query("pageSize") int paramString2,
                @Query("pageNum") int paramString3,
                @Query("orderByColumn") String paramString4,
                @Query("isAsc") String paramString5,

                @Query("dwbhs") String paramString6, //点位名称 字符串
                @Query("cpys") String paramString7, //车牌颜色 字符串 (0-蓝牌 1-黄牌 2-白牌 3-黑牌)
                @Query("hphm") String paramString8, //号牌号码 字符串
                @Query("clgs") String paramString9, //车辆归属地 字符串 (0-本地车，1-外地车 )
                @Query("rlzl") String paramString10,    //燃料种类 字符串 (A-汽油 B-柴油 C-其他 字符串)
                @Query("pdjg") String paramString11,   //判定结果 字符串 (0-超标 )
                @Query("shzt") String paramString12,       //审核状态 字符串

                @Query("lgmhdysf") String paramString13,     //林格曼黑度 (1-大于 2-小于 3-等于)
                @Query("lgmhd") Integer paramString14,
                @Query("sbzxdysf") String paramString15,     //识别置信度 (1-大于 2-小于 3-等于)
                @Query("sbzxd") Float paramString16,
                @Query("cbcsysf") String paramString17,      //超标次数 字符串 (1-大于 2-小于 3-等于)
                @Query("cbcs") Integer paramString18,

                @Query("beginTime") String paramString19,   //超标开始时间 yyyy-MM-dd HH:mm:ss Date
                @Query("endTime") String paramString20,     //超标结束时间 yyyy-MM-dd HH:mm:ss Date
                @Query("sjd") String paramString21    //时间段 (白天6;17  晚上18;23 早高峰7;8  晚高峰18;19)
        );
    }

    /**
     * 黑烟车: 已审核数据
     */
    public static abstract interface GetBlackYshList {
        @GET("/modules/blackcar/app/listbyYsh")
        public abstract Observable<ResponseWrapperList<Exhaust>> req(
                @Query("pdjg") String paramString0,
                @Query("sfsh") String paramString1,
                @Query("pageSize") int paramString2,
                @Query("pageNum") int paramString3,
                @Query("orderByColumn") String paramString4,
                @Query("isAsc") String paramString5,

                @Query("dwbhs") String paramString6, //点位名称 字符串
                @Query("cpys") String paramString7, //车牌颜色 字符串 (0-蓝牌 1-黄牌 2-白牌 3-黑牌)
                @Query("hphm") String paramString8, //号牌号码 字符串
                @Query("clgs") String paramString9, //车辆归属地 字符串 (0-本地车，1-外地车 )
                @Query("rlzl") String paramString10,    //燃料种类 字符串 (A-汽油 B-柴油 C-其他 字符串)
                @Query("pdjg") String paramString11,   //判定结果 字符串 (0-超标 )
                @Query("shzt") String paramString12,       //审核状态 字符串

                @Query("lgmhdysf") String paramString13,     //林格曼黑度 (1-大于 2-小于 3-等于)
                @Query("lgmhd") Integer paramString14,
                @Query("sbzxdysf") String paramString15,     //识别置信度 (1-大于 2-小于 3-等于)
                @Query("sbzxd") Float paramString16,
                @Query("cbcsysf") String paramString17,      //超标次数 字符串 (1-大于 2-小于 3-等于)
                @Query("cbcs") Integer paramString18,

                @Query("beginTime") String paramString19,   //超标开始时间 yyyy-MM-dd HH:mm:ss Date
                @Query("endTime") String paramString20,     //超标结束时间 yyyy-MM-dd HH:mm:ss Date
                @Query("sjd") String paramString21    //时间段 (白天6;17  晚上18;23 早高峰7;8  晚高峰18;19)
        );
    }

    /**
     * 黑烟车: 所有数据
     */
    public static abstract interface GetBlackAllList {
        @GET("/modules/blackcar/app/list")
        public abstract Observable<ResponseWrapperList<Exhaust>> req(
                @Query("pageSize") int paramString2,
                @Query("pageNum") int paramString3,
                @Query("orderByColumn") String paramString4,
                @Query("isAsc") String paramString5,

                @Query("dwbhs") String paramString6, //点位名称 字符串
                @Query("cpys") String paramString7, //车牌颜色 字符串 (0-蓝牌 1-黄牌 2-白牌 3-黑牌)
                @Query("hphm") String paramString8, //号牌号码 字符串
                @Query("clgs") String paramString9, //车辆归属地 字符串 (0-本地车，1-外地车 )
                @Query("rlzl") String paramString10,    //燃料种类 字符串 (A-汽油 B-柴油 C-其他 字符串)
                @Query("pdjg") String paramString11,   //判定结果 字符串 (0-超标 )
                @Query("shzt") String paramString12,       //审核状态 字符串

                @Query("lgmhdysf") String paramString13,     //林格曼黑度 (1-大于 2-小于 3-等于)
                @Query("lgmhd") Integer paramString14,
                @Query("sbzxdysf") String paramString15,     //识别置信度 (1-大于 2-小于 3-等于)
                @Query("sbzxd") Float paramString16,
                @Query("cbcsysf") String paramString17,      //超标次数 字符串 (1-大于 2-小于 3-等于)
                @Query("cbcs") Integer paramString18,

                @Query("beginTime") String paramString19,   //超标开始时间 yyyy-MM-dd HH:mm:ss Date
                @Query("endTime") String paramString20,     //超标结束时间 yyyy-MM-dd HH:mm:ss Date
                @Query("sjd") String paramString21    //时间段 (白天6;17  晚上18;23 早高峰7;8  晚高峰18;19)
        );
    }


    /*
     * 过滤条件集合对象
     */
    public static abstract interface GetBlackFilterListAll {
        @GET("/modules/overproof/app/selectfilter")
        public abstract Observable<ResponseWrapperListOld<FilterCategoryModel>> req();
    }

    /*
     * 过滤结果
     */
    public static abstract interface GetExecutionFilterListAll {
        @GET("/modules/overproof/app/selectfilter")
        public abstract Observable<ResponseWrapperListOld<FilterCategoryModel>> req();
    }

    /*
     * 黑烟车审核V3
     */
    public static abstract interface AddSh {
        @PUT("/modules/blackcar/app/audited")
        public abstract Observable<ResponseWrapper<String>> req(@Body RequestBody requestBody);
    }

    /*
     * 车辆基本信息
     */
    public static abstract interface GetVehicleBasicInfo {
        @FormUrlEncoded
        @POST("/datatable/vehicleinfo/app/list")
        public abstract Observable<ResponseWrapper<ResponseVehicleInfoListModel>> req(@Field("hphm") String paramString1, @Field("hpys") String paramString2);
    }

}
