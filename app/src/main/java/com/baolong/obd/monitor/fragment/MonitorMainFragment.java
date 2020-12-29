/**
package com.baolong.obd.monitor.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;
import com.baolong.obd.R;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.base.BaseFragment;
import com.baolong.obd.common.sharepreferemces.UserSP;
import com.baolong.obd.common.utils.ActivityUtils;
import com.baolong.obd.common.utils.BitmapUtils;
import com.baolong.obd.common.utils.CommonUtils;
import com.baolong.obd.common.utils.Converter;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.monitor.activity.SearchStationActivity;
import com.baolong.obd.monitor.activity.StationListActivity;
import com.baolong.obd.monitor.activity.SitesStatueActivity;
import com.baolong.obd.monitor.adapter.MonitorInfoWindowAdapter;
import com.baolong.obd.monitor.contract.MonitorMainContract;
import com.baolong.obd.monitor.data.entity.GetStationListAllResponseModel;
import com.baolong.obd.monitor.data.entity.GetTodayAmountModel2;
import com.baolong.obd.monitor.data.entity.MarkerObject;
import com.baolong.obd.monitor.data.entity.SiteAqiInfoItemV3;
import com.baolong.obd.monitor.data.entity.SiteInfoItem;
import com.baolong.obd.monitor.data.entity.SiteInfoItemV3;
import com.baolong.obd.monitor.event.JumpToMapEvent;
import com.baolong.obd.monitor.presenter.MonitorMainPresenter;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;


public class MonitorMainFragment extends BaseFragment
        implements View.OnClickListener, MonitorMainContract.View,
        LocationSource, AMapLocationListener, INaviInfoCallback {
    private final static String TAG = MonitorMainFragment.class.getSimpleName();

    private boolean isLocationFromNavi; //是否点击的导航按钮，不是定位按钮
    private LatLng locationLatLng;  //定位的经纬度
    private String locationAoiName;

    private LatLng mapCenterLatLng;  //地图中心经纬度
    private float mapZoom;  //地图缩放等级

    private boolean isAqiShowing = false;

    private MonitorMainPresenter mMonitorMainPresenter;

    //站点集合、空气质量集合
    ArrayList<SiteInfoItemV3> mSiteInfoList = new ArrayList<>();
    ArrayList<SiteAqiInfoItemV3> mAqiInfoList = new ArrayList<>();

    //marker集合：管理marker,如控制marker控制显示或则隐藏:marker.visible
    ArrayList<Marker> mSiteMarkerList = new ArrayList<>();
    ArrayList<Marker> mAqiMarkerList = new ArrayList<>();

    private View mView;
    private MapView mMapView;
    private AMap aMap;
    private MonitorInfoWindowAdapter mMonitorInfoWindowAdapter;

    // 定位功能
    private AMapLocationClient mAMapLocationClient;
    private AMapLocationClientOption mAMapLocationClientOption;
    private LocationSource.OnLocationChangedListener mOnLocationChangedListener;

    private ImageView mStationListImg;

    private ImageView mImageViewZoomIn;
    private ImageView mImageViewZoomOut;

    private LinearLayout mTrafficLayout;
    private ImageView mTrafficImg;

    private LinearLayout mSatelliteLayout;
    private ImageView mSatelliteImg;

    private LinearLayout mAirLayout;
    private ImageView mAirImg;

    protected Marker mCurrentMark;
    protected boolean isClickFromMark = false;
    protected GetStationListAllResponseModel mGetStationListAllResponseModel;

    private EditText searchStationEt;
    private ImageView msgImg;
    private TextView msgTxt;
    private ImageView locationImg;
    private ImageView warningImg;
    private ImageView naviImg;
    private LinearLayout legentLL;
    private ViewStub legendSiteViewStub;
    private ConstraintLayout legentSiteView;
    private ViewStub legendAqiViewStub;
    private LinearLayout legentAqiView;
    //public BDLocationListener myListener = new MyLocationListener();

    public static MonitorMainFragment newInstance() {
        return new MonitorMainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.i(TAG, "onCreateView");

        RxBus.get().register(this);

        initView(layoutInflater);

        initMap(savedInstanceState);

        //初始化定位服务
        initLocationService();

        setListener();

        initPresenter();

        getDataByPresenter();

        return this.mView;
    }

    @SuppressLint("InflateParams")
    private void initView(LayoutInflater paramLayoutInflater) {
        this.mView = paramLayoutInflater.inflate(R.layout.fragment_monitor, null);
        this.mMapView = (MapView) mView.findViewById(R.id.map_view);

        this.mStationListImg = (ImageView) mView.findViewById(R.id.img_map_menu);

        this.mImageViewZoomIn = (ImageView) mView.findViewById(R.id.img_zoom_in);
        this.mImageViewZoomOut = (ImageView) mView.findViewById(R.id.img_zoom_out);

        this.mTrafficLayout = ((LinearLayout) this.mView.findViewById(R.id.ll_traffic_container));
        this.mSatelliteLayout = ((LinearLayout) this.mView.findViewById(R.id.ll_satellite_container));
        this.mAirLayout = ((LinearLayout) this.mView.findViewById(R.id.ll_air_container));
        // this.mWeatherLayout = ((LinearLayout) this.mView.findViewById(R.id.ll_weather_container));
        this.mTrafficImg = ((ImageView) this.mView.findViewById(R.id.img_traffic));
        this.mSatelliteImg = ((ImageView) this.mView.findViewById(R.id.img_satellite));
        this.mAirImg = ((ImageView) this.mView.findViewById(R.id.img_atmosphere));

        this.msgImg = ((ImageView) this.mView.findViewById(R.id.img_msg));
        this.msgTxt = ((TextView) this.mView.findViewById(R.id.txt_msg));
        this.locationImg = ((ImageView) this.mView.findViewById(R.id.img_location));
        this.warningImg = ((ImageView) this.mView.findViewById(R.id.img_error));
        this.naviImg = ((ImageView) this.mView.findViewById(R.id.img_navi));

        this.legentLL = (LinearLayout) this.mView.findViewById(R.id.legend_container);

        this.legendSiteViewStub = (ViewStub) this.mView.findViewById(R.id.viewstub_legend_site);
        this.legendSiteViewStub.inflate();
        this.legentSiteView = (ConstraintLayout) this.mView.findViewById(R.id.viewstub_layout_site);
        this.legentSiteView.setVisibility(View.GONE);

        this.legendAqiViewStub = (ViewStub) this.mView.findViewById(R.id.viewstub_legend_aqi);
        this.legendAqiViewStub.inflate();
        this.legentAqiView = (LinearLayout) this.mView.findViewById(R.id.viewstub_layout_aqi);
        this.legentAqiView.setVisibility(View.GONE);

        this.searchStationEt = ((EditText) this.mView.findViewById(R.id.et_search_station));

    }

    private void initMap(Bundle savedInstanceState) {
        // 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
        this.mMapView.onCreate(savedInstanceState);

        //初始化地图控制器对象
        if (this.aMap == null) {
            this.aMap = mMapView.getMap();
        }

        //地图类型，默认普通地图模式
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);

        // 显示实时交通状况
        aMap.setTrafficEnabled(false);

        //隐藏缩放按钮
        aMap.getUiSettings().setZoomControlsEnabled(false);

        //地图非旋转
        aMap.getUiSettings().setRotateGesturesEnabled(false);

        //设置地图的中心点、地图缩放等级
        //（地图的缩放级别一共分为 17 级，从 3 到 19。数字越大，展示的图面信息越精细）
        //LatLng latlng = new LatLng(36.09, 114.31);
        try {
            double lat = Double.parseDouble(UserSP.getAddressCenterLat());
            double lng = Double.parseDouble(UserSP.getAddressCenterLng());
            LatLng latlng = new LatLng(lat, lng);
            mapZoom = Float.parseFloat(UserSP.getMapZoom());

            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, mapZoom));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        UiSettings mUiSettings = aMap.getUiSettings();
        mUiSettings.setZoomPosition(AMapOptions.ZOOM_POSITION_RIGHT_CENTER);

        //为marker设置弹窗 WindowInfo
        //aMap.setInfoWindowAdapter(new MonitorInfoWindowAdapter(getContext(), aMap, mMonitorMainPresenter));
        mMonitorInfoWindowAdapter = new MonitorInfoWindowAdapter(this.getContext());
        aMap.setInfoWindowAdapter(mMonitorInfoWindowAdapter);

        // 定义 Marker 点击事件监听
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // 点击定位图标不弹出弹窗
                if (!mSiteMarkerList.contains(marker)) {
                    //marker.setClickable(false);
                    //marker.remove();
                    return true;
                }

                isClickFromMark = true;

                if (mCurrentMark != null && mCurrentMark.equals(marker)) {
                    LogUtil.i(TAG, "点击了marker：preMark == currentMark : " + marker.getId());
                } else if (mCurrentMark != null) {
                    LogUtil.i(TAG, "点击了marker：preMark != currentMark : " + marker.getId());
                    mCurrentMark = marker;
                } else {
                    LogUtil.i(TAG, "点击了marker：preMark null : " + marker.getId());
                    mCurrentMark = marker;
                }

                InfoWindowControl();
                return false;
                //marker.setInfoWindowEnable(false);
                // 返回:true 表示该点击事件已被处理，不再往下传递(点击marker后, marker不会移动到地图中心)
                // 返回:false 则继续往下传递,(点击marker后,marker会自动移动到地图中心)
            }
        });

        // 定义 map 点击事件监听
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                LogUtil.i(TAG, "点击了aMap：");

                if (mCurrentMark != null) {
                    if (mCurrentMark.isInfoWindowShown() && !isClickFromMark) {
                        mCurrentMark.hideInfoWindow();
                        mCurrentMark = null;
                    }
                }

                isClickFromMark = false;
            }
        });

        // 定义 InfoWindow 点击事件监听
        aMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                MarkerObject markerObject = (MarkerObject) marker.getObject();

                if (markerObject != null) {
                    SiteInfoItemV3 siteAqiInfoItem = markerObject.getSiteInfoItem();
                    if (siteAqiInfoItem != null) {
                        String dwbh = siteAqiInfoItem.getDwbh();
                        if (!TextUtils.isEmpty(dwbh)) {
                            Intent intent = new Intent();
                            intent.setClass(getContext(), StationListActivity.class);
                            intent.putExtra("dwbh", dwbh);
                            ActivityUtils.activitySwitch((Activity) getContext(), intent, true);
                        }
                    }
                }

                marker.hideInfoWindow();
            }
        });

        // 定义 地图移动 监听事件
        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                if (cameraPosition != null) {
                    mapCenterLatLng = cameraPosition.target;
                    mapZoom = cameraPosition.zoom;
                    //LogUtil.i(TAG,"onCameraChange:" + " lat=" + mapCenterLatlng.latitude + " lng=" +mapCenterLatlng.longitude);
                }
            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {

            }
        });
    }

    private void initLocationService() {
        //ToastUtils.longToast("initLocationService...");
        if (aMap != null) {
            // 地图监听定位  (实现 LocationSource 接口)
            aMap.setLocationSource(this);
            // 设置默认定位按钮是否显示，非必需设置。
            aMap.getUiSettings().setMyLocationButtonEnabled(false);
            // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
            aMap.setMyLocationEnabled(true);

            //定位图标样式
            MyLocationStyle myLocationStyle = new MyLocationStyle();
            //设置定位蓝点的icon图标方法
            myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.local));
            //设置定位蓝点精度圆圈的边框颜色的方法。
            myLocationStyle.strokeColor(Color.BLACK);
            //设置定位蓝点精度圆圈的填充颜色的方法。
            myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));
            //设置定位蓝点精度圈的边框宽度的方法。
            myLocationStyle.strokeWidth(1.0f);
            //定位一次，且将视角移动到地图中心点。
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
            // 设置定位蓝点的Style
            aMap.setMyLocationStyle(myLocationStyle);

            //定位配置
            if (mAMapLocationClient == null) {
                // 声明 AMapLocationClient 对象
                mAMapLocationClient = new AMapLocationClient(getContext());
                //初始化定位参数
                mAMapLocationClientOption = new AMapLocationClientOption();
                // 设置定位间隔,单位毫秒,默认为2000ms
                mAMapLocationClientOption.setInterval(2000);
                // 单次定位
                mAMapLocationClientOption.setOnceLocation(true);
                // 设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
                mAMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
                // 设置定位参数
                mAMapLocationClient.setLocationOption(mAMapLocationClientOption);
                // 设置定位监听 (实现 AMapLocationListener 接口）
                mAMapLocationClient.setLocationListener(this);
                // 启动定位, 在点击定位或者导航按钮时启动定位
                //mAMapLocationClient.startLocation();
            }

        }
    }

    private void setListener() {
        // this.gotoStationListImg.setOnClickListener(this);
        this.mStationListImg.setOnClickListener(this);
        this.msgImg.setOnClickListener(this);
        this.locationImg.setOnClickListener(this);
        this.warningImg.setOnClickListener(this);
        this.naviImg.setOnClickListener(this);
        this.searchStationEt.setOnClickListener(this);

        this.mTrafficLayout.setOnClickListener(this);
        this.mSatelliteLayout.setOnClickListener(this);
        this.mAirLayout.setOnClickListener(this);
        //this.mWeatherLayout.setOnClickListener(this);

        this.mImageViewZoomIn.setOnClickListener(this);
        this.mImageViewZoomOut.setOnClickListener(this);

        this.legentLL.setOnClickListener(this);
        this.legentSiteView.setOnClickListener(this);
        this.legentAqiView.setOnClickListener(this);

    }

    private void initPresenter() {
        this.mMonitorMainPresenter = new MonitorMainPresenter(this);
    }

    private void getDataByPresenter() {
        this.mMonitorMainPresenter.getTodayAmount();
        this.mMonitorMainPresenter.getSiteListAll();
        this.mMonitorMainPresenter.getAqiListAll();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View paramView) {
        switch (paramView.getId()) {
            case R.id.img_map_menu:
                if (mapCenterLatLng != null) {
                    UserSP.setAddressCenterLat(String.valueOf(mapCenterLatLng.latitude));
                    UserSP.setAddressCenterLng(String.valueOf(mapCenterLatLng.longitude));
                    UserSP.setMapZoom(String.valueOf(mapZoom));
                    ToastUtils.shortToast("已保存地图状态");
                }
                break;
            case R.id.img_zoom_in:
                aMap.animateCamera(CameraUpdateFactory.zoomIn(), 1000, null);
                break;
            case R.id.img_zoom_out:
                aMap.animateCamera(CameraUpdateFactory.zoomOut(), 1000, null);
                break;
            case R.id.ll_traffic_container:
                if (this.aMap.isTrafficEnabled()) {
                    this.aMap.setTrafficEnabled(false);
                    this.mTrafficImg.setImageResource(R.mipmap.map_traffic_off);
                } else {
                    this.aMap.setTrafficEnabled(true);
                    this.mTrafficImg.setImageResource(R.mipmap.map_traffic_open);
                }
                break;
            case R.id.ll_satellite_container:
                if (this.aMap.getMapType() == AMap.MAP_TYPE_NORMAL) {
                    this.aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
                    this.mSatelliteImg.setImageResource(R.mipmap.map_satllite_ok);
                } else {
                    this.aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                    this.mSatelliteImg.setImageResource(R.mipmap.map_satellite_no);
                }
                break;
            case R.id.ll_air_container:
                if (isAqiShowing) {
                    //隐藏AQI
                    this.mAirImg.setImageResource(R.mipmap.map_air_off);
                    this.removeAqiMark();
                    this.showSiteMark(mSiteInfoList);
                } else {
                    //显示AQI
                    this.mAirImg.setImageResource(R.mipmap.map_air_on);
                    this.removeSiteMark();
                    this.showAqiMark(mAqiInfoList);
                }
                mCurrentMark = null;
                isAqiShowing = !isAqiShowing;
                legendControl(true);
                break;
            case R.id.et_search_station:
                //切换成点位模式
                if (isAqiShowing) {
                    //隐藏AQI
                    this.mAirImg.setImageResource(R.mipmap.map_air_off);
                    this.removeAqiMark();
                    this.showSiteMark(mSiteInfoList);
                }
                isAqiShowing = !isAqiShowing;
                legendControl(true);
                //跳转
                goSearch();
                break;
            case R.id.img_msg:
                if (this.msgTxt.getVisibility() == View.VISIBLE) {
                    this.msgTxt.setVisibility(View.GONE);
                } else {
                    if (!TextUtils.isEmpty(this.msgTxt.getText())) {
                        this.msgTxt.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.img_error:
                //切换成点位模式
                if (isAqiShowing) {
                    //隐藏AQI
                    this.mAirImg.setImageResource(R.mipmap.map_air_off);
                    this.removeAqiMark();
                    this.showSiteMark(mSiteInfoList);
                }
                isAqiShowing = !isAqiShowing;
                legendControl(true);
                //跳转
                startActivity(new Intent(getActivity(), SitesStatueActivity.class));
                break;
            case R.id.img_location:
                RxPermissions rxPermissions = new RxPermissions(getActivity());
                rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION)
                        .subscribe((Observer<Boolean>) new Observer<Boolean>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull Boolean aBoolean) {
                                if (aBoolean) {
                                    // 定位配置重新初始化
                                    if (mAMapLocationClient == null) {
                                        initLocationService();
                                    }
                                    // 启动定位
                                    isLocationFromNavi = false;
                                    mAMapLocationClient.startLocation();
                                } else {
                                    Toast.makeText(getContext(), R.string.permission_request_denied, Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
            case R.id.img_navi:
                RxPermissions rxPermissions2 = new RxPermissions(this);
                rxPermissions2.requestEachCombined(Manifest.permission.ACCESS_COARSE_LOCATION)
                        .subscribe(permission -> {
                            Log.i(TAG, "权限名称:" + permission.name + ",申请结果:" + permission.granted);
                            // 定位配置重新初始化
                            if (mAMapLocationClient == null) {
                                initLocationService();
                            }
                            // 启动定位
                            isLocationFromNavi = true;
                            mAMapLocationClient.startLocation();

                        });
                break;
            case R.id.legend_container:  //图例图标被点击时
                if (isAqiShowing) {
                    if (!legentAqiView.isShown()) {
                        legentAqiView.setVisibility(View.VISIBLE);
                    }
                } else {
                    if (!legentSiteView.isShown()) {
                        legentSiteView.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.viewstub_layout_site:
            case R.id.viewstub_layout_aqi:
                // 站点/aqi 图例视图view
                legendControl(false);
                break;
            default:
                break;
        }

    }

    // 控制当前marker弹窗  (显示 -> 隐藏, 隐藏 -> 显示 )
    protected void InfoWindowControl() {
        if (mCurrentMark != null) {
            if (mCurrentMark.isInfoWindowShown()) {
                mCurrentMark.hideInfoWindow();
                //mCurrentMark = null;
            } else {
                mCurrentMark.setInfoWindowEnable(true);

                //网络请求
                MarkerObject markerObject = (MarkerObject) mCurrentMark.getObject();
                if (markerObject != null) {
                    SiteInfoItemV3 siteInfoItem = (SiteInfoItemV3) markerObject.getSiteInfoItem();
                    if (siteInfoItem != null && !TextUtils.isEmpty(siteInfoItem.getDwbh())) {
                        MonitorMainFragment.this.showLoading();

                        JSONObject tempJSONObject = new JSONObject();
                        try {
                            tempJSONObject.put("dwbh",siteInfoItem.getDwbh());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), tempJSONObject.toString());

                        mMonitorMainPresenter.getSiteInfoItemCount(requestBody);
                    }
                }

                //直接加载
//                mCurrentMark.showInfoWindow();
            }
        }
    }

    private void showSiteMark(List<SiteInfoItemV3> paramSiteInfoList) {
        if (paramSiteInfoList == null) {
            return;
        }

        for (SiteInfoItemV3 siteInfoItem : paramSiteInfoList) {

            // 创建MarkerOptions：是设置Marker参数变量的类
            MarkerOptions markerOption = new MarkerOptions()
                    .position(new LatLng(siteInfoItem.getDdwd(), siteInfoItem.getDdjd()))
                    .title(siteInfoItem.getDwmc() + "," + siteInfoItem.getDwbh())
                    .snippet(siteInfoItem.getDwmc() + "," + siteInfoItem.getDwbh())
                    .icon(BitmapDescriptorFactory.fromBitmap(BitmapUtils.bitMapScale(BitmapDescriptorFactory.fromResource(getSiteBitmap(siteInfoItem)).getBitmap(), CommonUtils.DpToPx(getContext(), (float) 1.2))));
            // .icon(BitmapDescriptorFactory.fromResource(getSiteBitmap(siteInfoItem)));
            //.snippet(siteInfoItem.getProvince() + "," + siteInfoItem.getJzmc() + "," + siteInfoItem.getJzbh());

            Marker marker = aMap.addMarker(markerOption);

            MarkerObject markerObject = new MarkerObject();
            markerObject.setSiteInfoItem(siteInfoItem);
            marker.setObject(markerObject);

            marker.setVisible(true);
            mSiteMarkerList.add(marker);
        }
    }

    private void showAqiMark(List<SiteAqiInfoItemV3> paramAqiInfoList) {

        if (paramAqiInfoList == null) {
            return;
        }

        // 显示Aqi
        for (SiteAqiInfoItemV3 siteAqiInfoItemV3 : paramAqiInfoList) {
            // 点位名称为空，则说明没有该点位
            if (TextUtils.isEmpty(siteAqiInfoItemV3.getDwmc()) ){
                continue;
            }

            // 创建MarkerOptions：是设置Marker参数变量的类
            MarkerOptions markerOption = new MarkerOptions()
                    .position(new LatLng(siteAqiInfoItemV3.getDDWD(), siteAqiInfoItemV3.getDDJD()))
                    .title(siteAqiInfoItemV3.getDwmc() + "," + siteAqiInfoItemV3.getDwmc())
                    .snippet(siteAqiInfoItemV3.getDwmc() + "," + siteAqiInfoItemV3.getDwmc())
                    .icon(BitmapDescriptorFactory.fromBitmap(getAirBitmap(getContext(), siteAqiInfoItemV3)));
            //.icon(BitmapDescriptorFactory.fromBitmap(BitmapUtils.bitMapScale(BitmapDescriptorFactory.fromResource(getSiteBitmap(siteAqiInfoItem)).getSiteBitmap(), CommonUtils.DpToPx(getContext(), (float) 1.2))));

            Marker marker = aMap.addMarker(markerOption);

            MarkerObject markerObject = new MarkerObject();
            markerObject.setSiteAqiInfoItem(siteAqiInfoItemV3);
            marker.setObject(markerObject);

            marker.setClickable(false);
            marker.setVisible(true);
            mAqiMarkerList.add(marker);
        }
    }

    private void removeSiteMark() {
        if (mSiteMarkerList == null || mSiteMarkerList.size() == 0) {
            return;
        }
        // 移除点位
        LogUtil.i(TAG, "site length before:" + mSiteMarkerList.size());

        Iterator<Marker> iterator = mSiteMarkerList.iterator();
        while (iterator.hasNext()) {
            Marker marker = iterator.next();
            marker.remove();
            iterator.remove();
        }

        LogUtil.i(TAG, "site length after:" + mSiteMarkerList.size());

    }

    private void removeAqiMark() {
        if (mAqiMarkerList == null || mAqiMarkerList.size() == 0) {
            return;
        }

        LogUtil.i(TAG, "aqi length before:" + mAqiMarkerList.size());

        // 移除点位
        Iterator<Marker> iterator = mAqiMarkerList.iterator();
        while (iterator.hasNext()) {
            Marker marker = iterator.next();
            marker.remove();
            iterator.remove();
        }
        LogUtil.i(TAG, "aqi length after:" + mAqiMarkerList.size());
    }

    */
/**
     * 画点位图片
     *//*

    protected int getSiteBitmap(SiteInfoItemV3 stationInfo) {
        if ("A".equalsIgnoreCase(stationInfo.getDwlx())) {           //点位类型  A:垂直  B:水平  C:移动
            if ("1".equalsIgnoreCase(stationInfo.getDwzt())) {
                return R.mipmap.icon1_1;
            } else if ("2".equalsIgnoreCase(stationInfo.getDwzt())) {
                return R.mipmap.icon1_2;
            } else if ("3".equalsIgnoreCase(stationInfo.getDwzt())) {
                return R.mipmap.icon1_3;
            }
        } else if ("B".equalsIgnoreCase(stationInfo.getDwlx())) {
            if ("1".equalsIgnoreCase(stationInfo.getDwzt())) {
                return R.mipmap.icon2_1;
            } else if ("2".equalsIgnoreCase(stationInfo.getDwzt())) {
                return R.mipmap.icon2_2;
            } else if ("3".equalsIgnoreCase(stationInfo.getDwzt())) {
                return R.mipmap.icon2_3;
            }
        } else if ("C".equalsIgnoreCase(stationInfo.getDwlx())) {
            if ("1".equalsIgnoreCase(stationInfo.getDwzt())) {
                return R.mipmap.icon3_1;
            } else if ("2".equalsIgnoreCase(stationInfo.getDwzt())) {
                return R.mipmap.icon3_2;
            } else if ("3".equalsIgnoreCase(stationInfo.getDwzt())) {
                return R.mipmap.icon3_3;
            }
        } else if ("D".equalsIgnoreCase(stationInfo.getDwlx())) {
            if ("1".equalsIgnoreCase(stationInfo.getDwzt())) {
                return R.mipmap.icon4_1;
            } else if ("2".equalsIgnoreCase(stationInfo.getDwzt())) {
                return R.mipmap.icon4_2;
            } else if ("3".equalsIgnoreCase(stationInfo.getDwzt())) {
                return R.mipmap.icon4_3;
            }
        }

        return R.mipmap.legend2_1;
    }

    */
/**
     * 画空气质量图片
     *//*

    public Bitmap getAirBitmap(Context context, SiteAqiInfoItemV3 siteAqiInfoItemV3) {

        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(Converter.sp2px(context, 14.0f));
        textPaint.setColor(Color.WHITE);

        int abordWidth = Converter.dip2px(context, 2.0f);
        String aqiStr = String.valueOf(siteAqiInfoItemV3.getAQI());

        Rect bounds = new Rect();
        textPaint.getTextBounds(aqiStr, 0, aqiStr.length(), bounds);

        int textWidth = bounds.width() + abordWidth;
        int textHeight = bounds.height();

        Bitmap bitmap = BitmapDescriptorFactory.fromResource(R.drawable.nav_tou).getBitmap();
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, Converter.dip2px(context, 30),
                Converter.dip2px(context, 30));

        Canvas canvas = new Canvas(bitmap);

        Paint p = new Paint();

        p.setStrokeWidth(Converter.dip2px(context, 0.5f));
        p.setTextSize(Converter.dip2px(context, 20.0f));
        p.setAntiAlias(false);
        p.setStyle(Paint.Style.FILL);


        */
/**
         * 先画一个圆当边线 在画一个内圆做背景
         *//*

//        p.setColor(Color.WHITE);
//        canvas.drawCircle(Converter.dip2px(context, 15.0f), Converter.dip2px(context, 15.0f), Converter.dip2px(context, 15.0f), p);
        p.setColor(getAirColor(siteAqiInfoItemV3.getAQI()));

        canvas.drawCircle(Converter.dip2px(context, 15.0f), Converter.dip2px(context, 15.0f), Converter.dip2px(context, 14.0f), p);

        canvas.drawText(aqiStr + "", (Converter.dip2px(context, 30.0f) - textWidth) / 2, Converter.dip2px(context, 11.0f) + (Converter.dip2px(context, 30.0f) - textHeight) / 2, textPaint);// 设置bitmap上面的文字位置

        return bitmap;
    }

    */
/**
     * 画空气质量颜色
     *
     * @param aqiValue 空气质量--值
     * @return 空气质量--颜色
     *//*

    public int getAirColor(double aqiValue) {
        int color = Color.parseColor("#6ec129");
        if (aqiValue > 50) {
            color = Color.parseColor("#6ec129");
        } else if (aqiValue <= 50) {
            color = Color.parseColor("#e0cf22");
        } else if (aqiValue <= 150) {
            color = Color.parseColor("#fd5b30");
        } else if (aqiValue <= 200) {
            color = Color.parseColor("#e10724");
        } else if (aqiValue <= 250) {
            color = Color.parseColor("#8f0c50");
        } else {
            color = Color.parseColor("#410468");
        }
        return color;
    }

    public void scaleLargeMoveMap(LatLng latlng, float scaleValue) {
        //aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, scaleValue));
        //aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 11));
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, scaleValue));
    }

    public void scaleLargeMoveMap(String lonStr, String latStr, float scaleValue) {
        try {
            Double lon = Double.parseDouble(lonStr);
            Double lat = Double.parseDouble(latStr);
            Double temp;
            if (lon < lat) {
                temp = lat;
                lat = lon;
                lon = temp;
            }
            LatLng latLng = new LatLng(lat, lon);
            scaleLargeMoveMap(latLng, scaleValue);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void goSearch() {
        Intent intent = new Intent(getContext(), SearchStationActivity.class);
        intent.putExtra("type", "station");
        intent.putExtra("data", mSiteInfoList);
        startActivityForResult(intent, 0);
    }

    // 图例显示、隐藏、切换
    public void legendControl(boolean isSiteAirChange) {
        if (isSiteAirChange) {
            if (legentAqiView.isShown()) {
                legentAqiView.setVisibility(View.GONE);
                legentSiteView.setVisibility(View.VISIBLE);
            } else if (legentSiteView.isShown()) {
                legentSiteView.setVisibility(View.GONE);
                legentAqiView.setVisibility(View.VISIBLE);
            }
        } else {
            if (isAqiShowing) { // aqi显示
                if (legentAqiView.isShown()) {
                    legentAqiView.setVisibility(View.GONE);
                }
            } else {  // 点位显示
                if (legentSiteView.isShown()) {
                    legentSiteView.setVisibility(View.GONE);
                }
            }
        }
    }

    public void startMyNav() {
        if (mCurrentMark != null) {
            MarkerObject markerObject = (MarkerObject) mCurrentMark.getObject();
            if (markerObject != null) {
                SiteInfoItemV3 siteInfoItem = markerObject.getSiteInfoItem();
                if (siteInfoItem != null) {
                    try {
                        String dwmc = siteInfoItem.getDwmc();
                        LatLng endLatLng = new LatLng(siteInfoItem.getDdwd(), siteInfoItem.getDdjd());
                        // LatLng p1 = new LatLng(39.904556, 116.427231);//北京站
                        // LatLng p2 = new LatLng(39.917337, 116.397056);//故宫博物院
                        // AmapNaviParams amapNaviParams = new AmapNaviParams(new Poi("北京站", p1, ""), null, new Poi("故宫博物院", p2, ""), AmapNaviType.DRIVER);
                        AmapNaviParams amapNaviParams = new AmapNaviParams(new Poi(locationAoiName, locationLatLng, ""), null, new Poi(dwmc, endLatLng, ""), AmapNaviType.DRIVER);
                        amapNaviParams.setUseInnerVoice(true);
                        AmapNaviPage.getInstance().showRouteActivity(getActivity().getApplicationContext(), amapNaviParams, MonitorMainFragment.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            ToastUtils.shortToast("导航前请选择监测站点！");
        }
    }

    */
/**
     * 实现 LocationSource接口, 用于激活定位，实例化对象，设置监听等
     *//*

    @Override
    public void activate(LocationSource.OnLocationChangedListener listener) {
        // TODO Auto-generated method stub
        // ToastUtils.shortToast("activate...");
        mOnLocationChangedListener = listener;
    }

    */
/**
     * 实现 LocationSource接口, 用于停止定位，释放资源
     *//*

    @Override
    public void deactivate() {
        // TODO Auto-generated method stub
        // ToastUtils.shortToast("deactivate...");

        mOnLocationChangedListener = null;
        if (mAMapLocationClient != null) {
            mAMapLocationClient.stopLocation();
            mAMapLocationClient.onDestroy();
        }
        mAMapLocationClient = null;
        mAMapLocationClientOption = null;
    }

    //实现 AMapLocationListener接口, 用于接收异步返回的定位结果
    @Override
    public void onLocationChanged(AMapLocation amaplocation) {
        // TODO Auto-generated method stub
        Log.i(TAG, "onLocationChanged...");

        if (amaplocation != null && mOnLocationChangedListener != null) {
            if (amaplocation.getErrorCode() == 0) {
                Log.i(TAG, "onLocationChanged  " + "定位成功！");

                locationLatLng = new LatLng(amaplocation.getLatitude(), amaplocation.getLongitude());
                locationAoiName = amaplocation.getAoiName();

                if (!isLocationFromNavi) {
                    Log.i(TAG, "startLocalImg...");
                    Log.i(TAG, amaplocation.toString());
                    mOnLocationChangedListener.onLocationChanged(amaplocation);
                } else {
                    Log.i(TAG, "startMyNav...");
                    startMyNav();
                }

            } else {
                String errText = "failed to locate," + amaplocation.getErrorCode() + ": " + amaplocation.getErrorInfo();
                Log.e(TAG, "定位失败：error " + errText);
                ToastUtils.shortToast("定位失败：error " + errText);
            }
            //取消定位请求
            //mAMapLocationClient.stopLocation();
        }
    }

    @Override
    public void onResume() {
        LogUtil.i(TAG, "onResume");
        super.onResume();
        this.mMapView.onResume();
        if (mSiteMarkerList != null && mSiteMarkerList.size() == 0) {
            getDataByPresenter();
        }
    }

    @Override
    public void onPause() {
        LogUtil.i(TAG, "onPause");
        super.onPause();
        this.mMapView.onPause();
        deactivate();
    }

    */
/**
     * 方法必须重写
     *//*

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        LogUtil.i(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(bundle);
        this.mMapView.onSaveInstanceState(bundle);
    }

    @Override
    public void onDestroyView() {
        LogUtil.i(TAG, "onDestroyView");
        super.onDestroyView();
        mOnLocationChangedListener = null;

        //销毁定位客户端
        if (mAMapLocationClient != null) {
            mAMapLocationClient.stopLocation();
            mAMapLocationClient.onDestroy();
        }
        mAMapLocationClient = null;
        mAMapLocationClientOption = null;
        this.mMapView.onDestroy();

        RxBus.get().unregister(this);
    }

    @Override
    public void onDestroy() {
        LogUtil.i(TAG, "onDestroy");
        super.onDestroy();
    }


    @Override  // 搜索页面返回
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        LogUtil.i(TAG, "requestCode = " + requestCode + "  resultCode = " + resultCode);
        super.onActivityResult(requestCode, resultCode, intent);

        if ((requestCode == 0) && (resultCode == -1)) {
            String jzbhStr = intent.getStringExtra("jzbh");
            String lonStr = intent.getStringExtra("long");
            String latStr = intent.getStringExtra("lat");

            if ((!TextUtils.isEmpty(lonStr)) && (!TextUtils.isEmpty(latStr)) && (!TextUtils.isEmpty(jzbhStr))) {

                scaleLargeMoveMap(lonStr, latStr, 11);

                if (mCurrentMark != null) {
                    MarkerObject markerObject = (MarkerObject) mCurrentMark.getObject();
                    if (markerObject != null) {
                        SiteInfoItemV3 siteInfoItem = (SiteInfoItemV3) markerObject.getSiteInfoItem();
                        if (siteInfoItem != null && jzbhStr.equals(siteInfoItem.getDwbh())) {
                            //1.如果是当前marker，弹窗先隐藏再从新加载
                            mCurrentMark.hideInfoWindow();
                            InfoWindowControl();
                        } else {
                            //2.如果不是当前marker，隐藏当前marker的弹窗
                            //遍历marker列表，把当前站点设置成此站点，重新加载
                            if (mCurrentMark.isInfoWindowShown()) {
                                mCurrentMark.hideInfoWindow();
                            }
                            if (mSiteMarkerList != null) {
                                for (Marker marker : mSiteMarkerList) {
                                    MarkerObject tempMarkerObject = (MarkerObject) marker.getObject();
                                    if (tempMarkerObject != null) {
                                        SiteInfoItemV3 tempSiteInfoItem = tempMarkerObject.getSiteInfoItem();
                                        if (jzbhStr.equals(tempSiteInfoItem.getDwbh())) {
                                            mCurrentMark = marker;
                                            break;
                                        }
                                    }
                                }
                                InfoWindowControl();
                            }
                        }
                    }

                } else {
                    // 3. mCurrentMark为空
                    //遍历marker列表，把当前站点设置成此站点，重新加载
                    if (mSiteMarkerList != null) {
                        for (Marker marker : mSiteMarkerList) {
                            MarkerObject markerObject = (MarkerObject) marker.getObject();
                            if (markerObject != null) {
                                SiteInfoItemV3 siteInfoItem = markerObject.getSiteInfoItem();
                                if (siteInfoItem != null && jzbhStr.equals(siteInfoItem.getDwbh())) {
                                    mCurrentMark = marker;
                                    break;
                                }
                            }
                        }
                        InfoWindowControl();
                    }
                }
            }
        }
    }

    @Subscribe  //预警列表页面定位站点
    public void showMarkerOnMap(final JumpToMapEvent jumpToMapEvent) {
        final SiteInfoItem model = jumpToMapEvent.getModel();

        String lonStr = String.valueOf(model.getDDJD());
        String latStr = String.valueOf(model.getDDWD());
        String jzbhStr = model.getDWBH();

        if ((!TextUtils.isEmpty(lonStr)) && (!TextUtils.isEmpty(latStr)) && (!TextUtils.isEmpty(jzbhStr))) {

            scaleLargeMoveMap(lonStr, latStr, 11);

            if (mCurrentMark != null) {
                MarkerObject markerObject = (MarkerObject) mCurrentMark.getObject();
                if (markerObject != null) {
                    SiteInfoItemV3 siteInfoItem = (SiteInfoItemV3) markerObject.getSiteInfoItem();
                    if (siteInfoItem != null && jzbhStr.equals(siteInfoItem.getDwbh())) {
                        //1.如果是当前marker，弹窗先隐藏再从新加载
                        mCurrentMark.hideInfoWindow();
                        InfoWindowControl();
                    } else {
                        //2.如果不是当前marker，隐藏当前marker的弹窗
                        //遍历marker列表，把当前站点设置成此站点，重新加载
                        if (mCurrentMark.isInfoWindowShown()) {
                            mCurrentMark.hideInfoWindow();
                        }
                        if (mSiteMarkerList != null) {
                            for (Marker marker : mSiteMarkerList) {
                                MarkerObject tempMarkerObject = (MarkerObject) marker.getObject();
                                if (tempMarkerObject != null) {
                                    SiteInfoItemV3 tempSiteInfoItem = tempMarkerObject.getSiteInfoItem();
                                    if (jzbhStr.equals(tempSiteInfoItem.getDwbh())) {
                                        mCurrentMark = marker;
                                        break;
                                    }
                                }
                            }
                            InfoWindowControl();
                        }
                    }
                }

            } else {
                // 3. mCurrentMark为空
                //遍历marker列表，把当前站点设置成此站点，重新加载
                if (mSiteMarkerList != null) {
                    for (Marker marker : mSiteMarkerList) {
                        MarkerObject markerObject = (MarkerObject) marker.getObject();
                        if (markerObject != null) {
                            SiteInfoItemV3 siteInfoItem = markerObject.getSiteInfoItem();
                            if (siteInfoItem != null && jzbhStr.equals(siteInfoItem.getDwbh())) {
                                mCurrentMark = marker;
                                break;
                            }
                        }
                    }
                    InfoWindowControl();
                }
            }
        }
    }

    public void refresh() {
    }

    @Override
    public void setPresenter(MonitorMainPresenter paramMonitorMainPresenter) {
    }

    @Override
    public void showLoading() {
        if ((BaseActivity) getActivity() != null) {
            ((BaseActivity) getActivity()).showLoading(this.mView);
        }
    }

    @Override
    public void hideLoading() {
        if ((BaseActivity) getActivity() != null) {
            ((BaseActivity) getActivity()).hideLoading();
        }
    }

    @Override
    public void showFail(String paramString) {
        ToastUtils.shortToast(paramString);
        hideLoading();
    }

    @Override
    public void showTodayAmount(String totalNum, String unqualifiedNum, String percentage) {
        if (isAdded()) {
            String msg = getResources().getString(R.string.monitor_msg_demo, totalNum, unqualifiedNum, percentage);
            this.msgTxt.setVisibility(View.VISIBLE);
            this.msgTxt.setText(msg);
        }

    }

    @Override
    public void showSiteListAll(List<SiteInfoItemV3> list) {
        //this.mSiteInfoList = (ArrayList<SiteInfoItem>) list;
        // 有视频的点位后绘制，保证在上方显示
        if (list != null) {
            this.mSiteInfoList.clear();
            for (SiteInfoItemV3 siteInfoItem : list) {
                if (!siteInfoItem.isSfysp()) {
                    this.mSiteInfoList.add(siteInfoItem);
                }
            }
            for (SiteInfoItemV3 siteInfoItem : list) {
                if (siteInfoItem.isSfysp()) {
                    this.mSiteInfoList.add(siteInfoItem);
                }
            }
        }

        if (!isAqiShowing) {
            this.showSiteMark(mSiteInfoList);
        }
    }

    @Override
    public void showAqiListAll(List<SiteAqiInfoItemV3> list) {
        this.mAqiInfoList = (ArrayList<SiteAqiInfoItemV3>) list;
        if (isAqiShowing) {
            this.showAqiMark(mAqiInfoList);
        }
    }

    @Override
    public void showSitInfoItemCount(GetTodayAmountModel2 paramSiteInfoItemCount) {
        hideLoading();

        //RxBus.get().post(paramGetStationInfoResponseModel);

        if (mCurrentMark != null) {
            //LogUtil.i(TAG, "获取站点的详细信息：" + paramGetStationInfoResponseModel.getJzmc());
            MarkerObject markerObject = (MarkerObject) mCurrentMark.getObject();
            markerObject.setSiteInfoItemCount(paramSiteInfoItemCount);

            mCurrentMark.setObject(markerObject);
            mCurrentMark.showInfoWindow();

        }
    }

    public void showStationSearchList(List<GetStationListAllResponseModel> paramList) {
    }

//    public class MyLocationListener implements BDLocationListener {
//        public MyLocationListener() {
//        }
//
//        public void onReceiveLocation(BDLocation paramBDLocation) {
//            MonitorMainFragment.access$202(MonitorMainFragment.this, new LatLng(paramBDLocation.getLatitude(), paramBDLocation.getLongitude()));
//            Object localObject1 = new MyLocationData.Builder().accuracy(paramBDLocation.getRadius()).direction(100.0F).latitude(paramBDLocation.getLatitude()).longitude(paramBDLocation.getLongitude()).build();
//            MonitorMainFragment.this.bdMap.setMyLocationData((MyLocationData) localObject1);
//            if (MonitorMainFragment.this.isFirstLoc) {
//                MonitorMainFragment.access$402(MonitorMainFragment.this, false);
//                localObject1 = new LatLng(paramBDLocation.getLatitude(), paramBDLocation.getLongitude());
//                Object localObject2 = new MapStatus.Builder();
//                ((MapStatus.Builder) localObject2).target((LatLng) localObject1).zoom(18.0F);
//                MonitorMainFragment.this.bdMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(((MapStatus.Builder) localObject2).build()));
//                if (paramBDLocation.getLocType() == 61) {
//                    localObject1 = MonitorMainFragment.this;
//                    localObject2 = ((MonitorMainFragment) localObject1).getActivity();
//                    localObject1 = paramBDLocation.getAddrStr();
//                    paramBDLocation = (BDLocation) localObject2;
//                }
//                for (; ; ) {
//                    Toast.makeText(paramBDLocation, (CharSequence) localObject1, 0).show();
//                    return;
//                    if (paramBDLocation.getLocType() == 161) {
//                        localObject1 = MonitorMainFragment.this;
//                        break;
//                    }
//                    if (paramBDLocation.getLocType() == 66) {
//                        localObject1 = MonitorMainFragment.this;
//                        break;
//                    }
//                    if (paramBDLocation.getLocType() == 167) {
//                        paramBDLocation = MonitorMainFragment.this.getActivity();
//                        localObject1 = "服务器错误，强检查";
//                    } else if (paramBDLocation.getLocType() == 63) {
//                        paramBDLocation = MonitorMainFragment.this.getActivity();
//                        localObject1 = "网络错误，请检查";
//                    } else {
//                        if (paramBDLocation.getLocType() != 62) {
//                            return;
//                        }
//                        paramBDLocation = MonitorMainFragment.this.getActivity();
//                        localObject1 = "手机模式错误，请检查是否飞行";
//                    }
//                }
//            }
//        }
//    }


    @Override
    public void onInitNaviFailure() {

    }

    @Override
    public void onGetNavigationText(String s) {

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

    }

    @Override
    public void onArriveDestination(boolean b) {

    }

    @Override
    public void onStartNavi(int i) {

    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {

    }

    @Override
    public void onCalculateRouteFailure(int i) {

    }

    @Override
    public void onStopSpeaking() {

    }

    @Override
    public void onReCalculateRoute(int i) {

    }

    @Override
    public void onExitPage(int i) {

    }

    @Override
    public void onStrategyChanged(int i) {

    }

    @Override
    public View getCustomNaviBottomView() {
        //返回null则不显示自定义区域
        //return getCustomView("底部自定义区域");

        return null;
    }

    @Override
    public View getCustomNaviView() {
        //返回null则不显示自定义区域
        ///return getCustomView("中部自定义区域");
        return null;
    }

    @Override
    public void onArrivedWayPoint(int i) {

    }

}

*/
