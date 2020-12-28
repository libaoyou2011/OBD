package com.baolong.obd.monitor.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.common.widget.XCRoundRectImageView;
import com.baolong.obd.component.media.AppImageDisplay;

import java.util.ArrayList;
import java.util.List;

public class StationListAdapter extends RecyclerView.Adapter<StationListAdapter.MyViewHolder> {
    private Context mContext;
    private View.OnClickListener mOnItemClickListener;
    private List<Exhaust> mLists;

    public StationListAdapter(Context paramContext, View.OnClickListener paramOnClickListener) {
        this.mContext = paramContext;
        this.mOnItemClickListener = paramOnClickListener;
    }

    public void setData(List<Exhaust> paramList) {
        this.mLists = paramList;
        notifyDataSetChanged();
    }

    public List<Exhaust> getData() {
        if (this.mLists == null) {
            return new ArrayList();
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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_station_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Exhaust exhaust = (Exhaust) this.mLists.get(position);

        //root点击事件在StationListFragment中回调，将该item数据返回
        holder.root.setTag(exhaust);
        holder.root.setOnClickListener(mOnItemClickListener);

        //将所有的图片拼接到一起
        StringBuilder urlSB = new StringBuilder();
        if (!TextUtils.isEmpty(exhaust.getTp1())) {
            urlSB.append(exhaust.getTp1());
        }
        if (!TextUtils.isEmpty(exhaust.getTp2())) {
            if (!TextUtils.isEmpty(urlSB.toString())) {
                urlSB.append(";");
            }
            urlSB.append(exhaust.getTp2());
        }
        if (!TextUtils.isEmpty(exhaust.getTp3())) {
            if (!TextUtils.isEmpty(urlSB.toString())) {
                urlSB.append(";");
            }
            urlSB.append(exhaust.getTp3());
        }
        if (!TextUtils.isEmpty(exhaust.getTp4())) {
            if (!TextUtils.isEmpty(urlSB.toString())) {
                urlSB.append(";");
            }
            urlSB.append(exhaust.getTp4());
        }
        if (!TextUtils.isEmpty(exhaust.getTp5())) {
            if (!TextUtils.isEmpty(urlSB.toString())) {
                urlSB.append(";");
            }
            urlSB.append(exhaust.getTp5());
        }
        //显示的首张图片URL
        String[] urlArray = urlSB.toString().split(";");
        String pictureUrl = null;
        if (urlArray.length > 0) {
            pictureUrl = urlArray[0];
        }
        //glide加载图片
        if (!TextUtils.isEmpty(pictureUrl)) {
            AppImageDisplay.showImg("profile", pictureUrl, this.mContext, R.drawable.img_monitor_pic, holder.header);
        }

        holder.recordNo.setText(exhaust.getJlbh());
        holder.carNo.setText(exhaust.getHphm());
        holder.addTime.setText(exhaust.getJcrq());
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView addTime;
        TextView carNo;
        XCRoundRectImageView header;
        TextView recordNo;
        RelativeLayout root;

        MyViewHolder(View paramView) {
            super(paramView);
            this.root = ((RelativeLayout) paramView.findViewById(R.id.rl_root));
            this.recordNo = ((TextView) paramView.findViewById(R.id.tv_code));
            this.header = ((XCRoundRectImageView) paramView.findViewById(R.id.img_url));
            this.addTime = ((TextView) paramView.findViewById(R.id.tv_time));
            this.carNo = ((TextView) paramView.findViewById(R.id.tv_car_no));
        }
    }
}

