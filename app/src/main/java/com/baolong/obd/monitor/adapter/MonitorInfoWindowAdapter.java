package com.baolong.obd.monitor.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Marker;
import com.baolong.obd.R;
import com.baolong.obd.common.utils.DateUtils;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.monitor.data.entity.GetTodayAmountModel2;
import com.baolong.obd.monitor.data.entity.MarkerObject;
import com.baolong.obd.monitor.data.entity.SiteInfoItemV3;
import com.baolong.obd.monitor.haikang.HKPreviewActivity;

/**
 * InfoWindowAdapter  initMap时调用
 */
public class MonitorInfoWindowAdapter implements AMap.InfoWindowAdapter {
    private static final String TAG = MonitorInfoWindowAdapter.class.getSimpleName();
    private final Context mContext;

    public MonitorInfoWindowAdapter(Context context) {
        super();
        mContext = context;
    }

    @Override
    public View getInfoWindow(final Marker marker) {
        View infoWindowView = LayoutInflater.from(mContext).inflate(R.layout.monitor_marker_infowindow, null);
        render(marker, infoWindowView);

        return infoWindowView;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    /**
     * 自定义InfoWindow窗口
     */
    public void render(Marker marker, View view) {
        LogUtil.i(TAG, "显示InfoWindow reder()");
        TextView stationCode = (TextView) view.findViewById(R.id.txt_station_code);
        TextView stationName = (TextView) view.findViewById(R.id.txt_station_name);
        TextView stationSum = (TextView) view.findViewById(R.id.txt_monitor_sum);
        TextView stationOver = (TextView) view.findViewById(R.id.txt_over_times);
        //TextView stationAverage = (TextView) view.findViewById(R.id.txt_average);
        TextView stationType = (TextView) view.findViewById(R.id.txt_location_type);
        TextView stationDirection = (TextView) view.findViewById(R.id.txt_direction);
        TextView stationCDS = (TextView) view.findViewById(R.id.txt_cds);
        TextView stationAddDateTxt = (TextView) view.findViewById(R.id.txt_add_date);
        TextView stationVidioTxt = (TextView) view.findViewById(R.id.site_video);

        MarkerObject markerObject = (MarkerObject) marker.getObject();
        if (markerObject != null) {
            SiteInfoItemV3 siteInfoItem = markerObject.getSiteInfoItem();
            GetTodayAmountModel2 siteInfoItemCount = markerObject.getSiteInfoItemCount();

            if (siteInfoItem != null) {
                stationCode.setText(siteInfoItem.getDwbh());
                stationName.setText(siteInfoItem.getDwmc());
                if (siteInfoItemCount != null) {
                    stationSum.setText(new StringBuilder().append(siteInfoItemCount.getCountJcsj()).toString());
                    stationOver.setText(new StringBuilder().append(siteInfoItemCount.getCountJcsjCb()).toString());
                }
                //stationAverage.setText(new StringBuilder().append(siteInfoItem.getSpeed().toString()));

                String type = null;
                if ("A".equalsIgnoreCase(siteInfoItem.getDwlx())) {
                    type = "垂直";
                } else if ("B".equalsIgnoreCase(siteInfoItem.getDwlx())) {
                    type = "水平";
                } else if ("C".equalsIgnoreCase(siteInfoItem.getDwlx())) {
                    type = "移动";
                }
                stationType.setText(type);

                stationDirection.setText("1".equals(siteInfoItem.getClfx()) ? "上行" : "下行");
                stationCDS.setText(String.valueOf(siteInfoItem.getCdsl()));
                stationAddDateTxt.setText(DateUtils.dateToString2(DateUtils.stringToDate(siteInfoItem.getYxrq(), "yyyy-MM-dd HH:mm:SS")));

                if (siteInfoItem.isSfysp()) {
                    stationVidioTxt.setVisibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(siteInfoItem.getDwbh())) {
                        stationVidioTxt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext, HKPreviewActivity.class);
                                intent.putExtra("dwbh", siteInfoItem.getDwbh());
                                mContext.startActivity(intent);
                            }
                        });
                    } else {
                        ToastUtils.longToast("此点位无球机");
                    }
                } else {
                    stationVidioTxt.setVisibility(View.GONE);
                }
            }
        }

    }
}