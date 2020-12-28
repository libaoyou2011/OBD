package com.baolong.obd.monitor.activity;

/*public class AMapNaviActivity extends BaseActivity implements AMapNaviViewListener, AMapNaviListener {
    Poi sPoi;
    Poi ePoi;

    List<Poi> sList = new ArrayList<>();
    List<Poi> eList = new ArrayList<>();
    List<Poi> mWayPointList = new ArrayList<>();
    private AMapNaviView mAmapNaviView;
    private AMapNavi mAMapNavi;

    @Override  //当 AMapNavi 对象初始化成功后，回调
    public void onInitNaviSuccess() {
        *//**
         * 方法:
         *   int strategy=mAMapNavi.strategyConvert(congestion, avoidhightspeed, cost, hightspeed, multipleroute);
         * 参数:
         * @congestion 躲避拥堵
         * @avoidhightspeed 不走高速
         * @cost 避免收费
         * @hightspeed 高速优先
         * @multipleroute 多路径
         *
         * 说明:
         *      以上参数都是boolean类型，其中multipleroute参数表示是否多条路线，如果为true则此策略会算出多条路线。
         * 注意:
         *      不走高速与高速优先不能同时为true
         *      高速优先与避免收费不能同时为true
         *//*
        int strategy = 0;
        try {
            strategy = mAMapNavi.strategyConvert(true, false, false, false, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mAMapNavi.calculateDriveRoute(sPoi, ePoi, strategy);
    }

    @Override
    public void onCalculateRouteSuccess() {
        super.onCalculateRouteSuccess();
        mAMapNavi.startNavi(NaviType.GPS);
    }

    @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_amap_navi);

        Poi sPoi = new Poi("三元桥", new LatLng(39.96087,116.45798), "");
        sList.add(sPoi);
        *//**终点传入的是北京站坐标,但是POI的ID "B000A83M61"对应的是北京西站，所以实际算路以北京西站作为终点**//*
        Poi ePoi = new Poi("北京站", new LatLng(39.904556, 116.427231), "B000A83M61");
        eList.add(ePoi);
        //AmapNaviPage.getInstance().showRouteActivity(this, new AmapNaviParams(start, null, end, AmapNaviType.DRIVER), naviInfoCallback);

        //获取 AMapNaviView 实例：默认的导航界面
        mAmapNaviView = (AMapNaviView) findViewById(R.id.navi_view);
        mAmapNaviView.setAMapNaviViewListener(this);
        mAmapNaviView.onCreate(paramBundle);

        //获取AMapNavi实例: 导航对外控制类
        mAMapNavi = AMapNavi.getInstance(getApplicationContext());
        //添加监听回调，用于处理算路成功
        mAMapNavi.addAMapNaviListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAmapNaviView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAmapNaviView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAmapNaviView.onDestroy();
    }
}*/
