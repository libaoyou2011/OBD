package com.baolong.obd.querycar;

import com.baolong.obd.blackcar.data.entity.HuanjianModel;
import com.baolong.obd.blackcar.data.entity.ResponseExhaustListModel;
import com.baolong.obd.blackcar.data.entity.ResponseListModel;
import com.baolong.obd.blackcar.data.entity.ResponseVehicleInfoListModel;
import com.baolong.obd.querycar.data.entity.GetVehicleQueryListResponseModel;
import com.baolong.obd.common.network.ResponseWrapperListOld;

import retrofit2.http.POST;
import retrofit2.http.FormUrlEncoded;

import com.baolong.obd.common.network.ResponseWrapper;

import rx.Observable;
import retrofit2.http.Field;

public class QueryCarApis {

    public interface GetVehicleQueryList {
        @FormUrlEncoded
        @POST("blzxjcpt/api/app/getVehicleQueryList")
        Observable<ResponseWrapperListOld<GetVehicleQueryListResponseModel>> req(@Field("token") final String p0, @Field("username") final String p1, @Field("appid") final String p2, @Field("loginname") final String p3);
    }

    public interface InsertVehicleQuery {
        @FormUrlEncoded
        @POST("blzxjcpt/api/app/insertVehicleQuery")
        Observable<ResponseWrapper<String>> req(@Field("token") final String p0, @Field("username") final String p1, @Field("appid") final String p2, @Field("loginname") final String p3, @Field("hphm") final String p4, @Field("hpys") final String p5);
    }

    public interface DeleteVehicleQueryInfo {
        @FormUrlEncoded
        @POST("blzxjcpt/api/app/deleteVehicleQueryInfo")
        Observable<ResponseWrapper<String>> req(@Field("token") final String p0, @Field("username") final String p1, @Field("appid") final String p2, @Field("loginname") final String p3, @Field("id") final String p4);
    }

    // 查询车辆基本信息
    public interface GetVehicleBasicInfo {
        @FormUrlEncoded
        @POST("/datatable/vehicleinfo/app/list")
        Observable<ResponseWrapper<ResponseVehicleInfoListModel>> req(@Field("hphm") final String hphm, @Field("hpys") final String cpys);
    }

    // 查询车辆年检/检验纪录
    public interface GetCheckListByVehicle {
        @FormUrlEncoded
        @POST("/modules/inspection/app/inspection")
        Observable<ResponseWrapper<ResponseListModel<HuanjianModel>>> req(@Field("pageSize") int paramString3, //每页显示记录数 int类型 必填
                                                                          @Field("pageNum") int paramString4,  //当前记录起始索引  int类型  必填
                                                                          @Field("orderByColumn") String paramString5, //排序列 字符串 必填
                                                                          @Field("isAsc") String paramString6,     //排序方向 “desc”或“asc”

                                                                          @Field("hphm") String paramString7,//号牌号码 字符串
                                                                          @Field("cpys") String paramString8 //车牌颜色 字符串 (0-蓝牌 1-黄牌 2-白牌 3-黑牌
        );
    }

    // 查询指定车辆的遥测数据
    public interface GetYcDataByCar {
        @FormUrlEncoded
        @POST("/modules/exhaust/app/list")
        Observable<ResponseWrapper<ResponseExhaustListModel>> req(@Field("isBlackCar") int paramString2, //是否是黑烟车 int类型 必填 (0-不是、 1-是)
                                                                  @Field("pageSize") int paramString3, //每页显示记录数 int类型 必填
                                                                  @Field("pageNum") int paramString4,  //当前记录起始索引  int类型  必填
                                                                  @Field("orderByColumn") String paramString5, //排序列 字符串 必填
                                                                  @Field("isAsc") String paramString6,     //排序方向 “desc”或“asc”

                                                                  @Field("siteInfo.hphm") String paramString7,//号牌号码 字符串
                                                                  @Field("cpys") String paramString8 //车牌颜色 字符串 (0-蓝牌 1-黄牌 2-白牌 3-黑牌)
        );
    }

    // 查询指定车辆的超标数据
    public interface GetCbDataByCar {
        @FormUrlEncoded
        @POST("/modules/overproof/app/list")
        Observable<ResponseWrapper<ResponseExhaustListModel>> req(@Field("pdjg") int paramString2, //判定结果 int类型 必填 (0-不合格、 1-合格、 2-无效)
                                                                  @Field("pageSize") int paramString3, //每页显示记录数 int类型 必填
                                                                  @Field("pageNum") int paramString4,  //当前记录起始索引  int类型  必填
                                                                  @Field("orderByColumn") String paramString5, //排序列 字符串 必填
                                                                  @Field("isAsc") String paramString6,     //排序方向 “desc”或“asc”

                                                                  @Field("siteInfo.hphm") String paramString7,//号牌号码 字符串
                                                                  @Field("cpys") String paramString8 //车牌颜色 字符串 (0-蓝牌 1-黄牌 2-白牌 3-黑牌)
        );
    }
}

