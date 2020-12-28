package com.baolong.obd.common.base;

/*
* ARouter上面的注释需要我们写 “路径标识”
* 我们可以写一个常量文件，在这里统一管理“路径标签”
*/

public class Constance {
    public static final String TAG = Constance.class.getSimpleName();
    public static final boolean UserInterceptor = true;

    public static final String ACTIVITY_URL_MAIN = "/main/MainActivity";
    public static final String ACTIVITY_URL_Search = "/monitor/SearchStationActivity";

    public static final String ACTIVITY_URL_BlackCarDetailActivity = "/blackcar/activity/BlackCarDetailActivity";
    public static final String ACTIVITY_URL_StationDetailActivity = "/monitor/activity/BlackCarDetailActivity";

}
