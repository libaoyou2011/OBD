package com.baolong.obd.querycar.adapter;

import com.baolong.obd.R;
import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.common.widget.XCRoundRectImageView;

import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.Button;

import com.baolong.obd.common.utils.DateUtils;

import com.baolong.obd.component.media.AppImageDisplay;

import java.util.ArrayList;

import android.view.View;

import java.util.List;

import android.content.Context;

public class RemoteCheckListAdapter extends RecyclerView.Adapter<RemoteCheckListAdapter.MyViewHolder> {
    private static final String TAG = RemoteCheckListAdapter.class.getSimpleName();
    private Context mContext;
    private List<Exhaust> mLists;
    private OnItemClickListener mOnItemClickListener;

    public RemoteCheckListAdapter(final Context mContext, final OnItemClickListener mOnItemClickListener) {
        this.mContext = mContext;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setData(final List<Exhaust> mLists) {
        this.mLists = mLists;
        this.notifyDataSetChanged();
    }

    public List<Exhaust> getData() {
        if (this.mLists == null) {
            return new ArrayList<Exhaust>();
        } else {
            return this.mLists;
        }
    }

    @Override
    public int getItemCount() {
        return ((this.mLists == null) ? 0 : this.mLists.size());
    }

    @Override
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int n) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_remote_check_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Exhaust exhaust = this.mLists.get(position);

        holder.root.setTag((Object) exhaust);

        //glide加载图片
        //AppImageDisplay.showImg("blzxjcpt", exhaust.getUrl(), this.mContext, R.drawable.img_monitor_pic, (ImageView) holder.urlImg);
        AppImageDisplay.showImg("profile", exhaust.getTp1(), this.mContext, R.drawable.img_monitor_pic, holder.urlImg);

        holder.testNoTv.setText(exhaust.getJlbh());

        holder.timeTv.setText(DateUtils.dateTimeToString(DateUtils.str2Date(exhaust.getJcrq())));

        if (exhaust.getSiteInfo() != null) {
            holder.stationTv.setText(exhaust.getSiteInfo().getDwmc());
        }

        // 0：不合格  1:合格   2：无效
        if ("0".equals(exhaust.getPdjg())) {
            holder.statusBtn.setBackgroundResource(R.drawable.btn_red_bg);
            holder.statusBtn.setText(R.string.car_yc_buhege);
        } else if ("1".equals(exhaust.getPdjg())) {
            holder.statusBtn.setBackgroundResource(R.drawable.btn_blue1_bg);
            holder.statusBtn.setText(R.string.car_yc_hege);
        } else if ("2".equals(exhaust.getPdjg())) {
            holder.statusBtn.setBackgroundResource(R.drawable.btn_gray_bg);
            holder.statusBtn.setText(R.string.car_yc_wuxiao);
        }

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RemoteCheckListAdapter.this.mOnItemClickListener != null) {
                    RemoteCheckListAdapter.this.mOnItemClickListener.onItemClick(exhaust);
                }
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(final Exhaust p0);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout root;
        TextView stationTv;
        Button statusBtn;
        TextView testNoTv;
        TextView timeTv;
        XCRoundRectImageView urlImg;

        MyViewHolder(final View view) {
            super(view);
            this.root = (RelativeLayout) view.findViewById(R.id.rl_root);
            this.urlImg = (XCRoundRectImageView) view.findViewById(R.id.img_url);
            this.testNoTv = (TextView) view.findViewById(R.id.tv_test_no);
            this.timeTv = (TextView) view.findViewById(R.id.tv_time);
            this.stationTv = (TextView) view.findViewById(R.id.tv_station_no);
            this.statusBtn = (Button) view.findViewById(R.id.btn_status);
        }
    }
}

