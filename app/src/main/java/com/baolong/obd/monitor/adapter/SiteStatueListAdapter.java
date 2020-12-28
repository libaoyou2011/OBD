package com.baolong.obd.monitor.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.monitor.data.entity.SiteInfoItem;
import com.baolong.obd.monitor.data.entity.SiteInfoItemV3;

import java.util.ArrayList;
import java.util.List;

public class SiteStatueListAdapter extends RecyclerView.Adapter<SiteStatueListAdapter.MyViewHolder> {
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private List<SiteInfoItemV3> mLists;

    public SiteStatueListAdapter(Context paramContext, OnItemClickListener paramOnItemClickListener) {
        this.mContext = paramContext;
        this.mOnItemClickListener = paramOnItemClickListener;
    }

    public void setData(List<SiteInfoItemV3> paramList) {
        this.mLists = paramList;
        notifyDataSetChanged();
    }

    public List<SiteInfoItemV3> getData() {
        if (this.mLists == null) {
            return new ArrayList<>();
        }
        return this.mLists;
    }

    @Override
    public int getItemCount() {
        if (this.mLists == null) {
            return 0;
        }
        return this.mLists.size();
    }

    @Override
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sitestatue_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SiteInfoItemV3 siteInfoItem = mLists.get(position);
        holder.root.setTag(siteInfoItem);
        holder.stationCodeTxt.setText(siteInfoItem.getDwmc());

        //点位类型  A:垂直  B:水平  C:移动
        StringBuilder typeSB = new StringBuilder();
        typeSB.append("--- ");
        if ("A".equalsIgnoreCase(siteInfoItem.getDwlx())) {
            typeSB.append("垂直设备");
        } else if ("B".equalsIgnoreCase(siteInfoItem.getDwlx())) {
            typeSB.append("水平设备");
        } else if ("C".equalsIgnoreCase(siteInfoItem.getDwlx())) {
            typeSB.append("移动设备");
        } else {
            typeSB.append("设备");
        }
        holder.errorDeviceTxt.setText(typeSB.toString());

        //点位状态  1:正常  2：维护  3：停用
        StringBuilder statusSB = new StringBuilder();
        statusSB.append("设备状态：");
        if ("1".equalsIgnoreCase(siteInfoItem.getDwzt())) {
            statusSB.append("正常");
        } else if ("2".equalsIgnoreCase(siteInfoItem.getDwzt())) {
            statusSB.append("维护");
        } else if ("3".equalsIgnoreCase(siteInfoItem.getDwzt())) {
            statusSB.append("停用");
        } else {
            statusSB.append("");
        }
        holder.timeTxt.setText(statusSB);

        StringBuilder timeSB = new StringBuilder();
        timeSB.append("设备安装时间：");
        timeSB.append(siteInfoItem.getYxrq());
        holder.errorDetailTxt.setText(timeSB);

        holder.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    //定位到地图
                    //mOnItemClickListener.onItemClick(siteInfoItem);

                    // 自动校准
                    ToastUtils.shortToast(mContext.getResources().getString(R.string.monitor_station_remake_tips));
                }
            }
        });
    }

    public static abstract interface OnItemClickListener {
        public abstract void onItemClick(SiteInfoItem paramGetFaultstateData);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout root;
        TextView stationCodeTxt;
        TextView errorDeviceTxt;
        TextView timeTxt;
        TextView location;
        TextView errorDetailTxt;

        MyViewHolder(View paramView) {
            super(paramView);
            this.root = ((LinearLayout) paramView.findViewById(R.id.rl_root));
            this.stationCodeTxt = ((TextView) paramView.findViewById(R.id.tv_code));
            this.errorDeviceTxt = ((TextView) paramView.findViewById(R.id.tv_error));
            this.timeTxt = ((TextView) paramView.findViewById(R.id.tv_time_title));
            this.location = ((TextView) paramView.findViewById(R.id.btn_location_map));
            this.errorDetailTxt = ((TextView) paramView.findViewById(R.id.tv_error_detail));
        }
    }
}

