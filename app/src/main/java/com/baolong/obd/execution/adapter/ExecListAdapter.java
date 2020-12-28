package com.baolong.obd.execution.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.common.widget.XCRoundRectImageView;
import com.baolong.obd.component.media.AppImageDisplay;

import java.util.ArrayList;
import java.util.List;

public class ExecListAdapter extends RecyclerView.Adapter<ExecListAdapter.MyViewHolder> {
    private Context mContext;
    private String mType;
    private List<Exhaust> mLists;
    private OnItemClickListener mItemClickListener;
    private OnItemLongClickListener mItemLongClickListener;
    private OnActionClickListener mOnActionClickListener;

    public ExecListAdapter(Context paramContext, String paramString) {
        this.mContext = paramContext;
        this.mType = paramString;
    }

    public void setData(List<Exhaust> paramList) {
        this.mLists = paramList;
        notifyDataSetChanged();
    }

    public List<Exhaust> getData() {
        if (this.mLists == null) {
            return new ArrayList<Exhaust>();
        } else {
            return this.mLists;
        }
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.mItemClickListener = paramOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener paramOnItemLongClickListener) {
        this.mItemLongClickListener = paramOnItemLongClickListener;
    }

    public void setOnActionClickListener(OnActionClickListener paramOnActionClickListener) {
        this.mOnActionClickListener = paramOnActionClickListener;
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
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exec_list, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Exhaust exhaust = (Exhaust) this.mLists.get(position);

        //root点击事件在ExeListFragment中回调，将该item数据返回
        holder.root.setTag(exhaust);

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
        if (exhaust.getSiteInfo() != null) {
            holder.stationName.setText(exhaust.getSiteInfo().getDwmc());
        }
        // item项右上角审核状态
        if ("0".equals(this.mType)) {
            holder.actionContainer.setBackgroundResource(R.drawable.exec_no_do);
            holder.isDone.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, this.mContext.getResources().getDrawable(R.drawable.ic_exec_do), (Drawable) null, (Drawable) null);
            holder.isDone.setText(R.string.execution_do);
        } else {
            holder.actionContainer.setBackgroundResource(R.drawable.exec_done);
            holder.isDone.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, this.mContext.getResources().getDrawable(R.drawable.ic_exec_look), (Drawable) null, (Drawable) null);
            holder.isDone.setText(R.string.execution_look);
        }

        if (this.mItemClickListener != null) {
            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(exhaust, mType);
                }
            });
        }

        if (this.mItemLongClickListener != null) {
            holder.root.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mItemLongClickListener.onItemLongClick(exhaust);
                }
            });
        }

        if (this.mOnActionClickListener != null) {
            holder.actionContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnActionClickListener.onActionClick(exhaust, mType);
                }
            });
        }
    }

    public static abstract interface OnActionClickListener {
        public abstract void onActionClick(Exhaust exhaust, String paramString);
    }

    public static abstract interface OnItemLongClickListener {
        public abstract boolean onItemLongClick(Exhaust exhaust);
    }

    public static abstract interface OnItemClickListener {
        public abstract void onItemClick(Exhaust exhaust, String paramString);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout actionContainer;
        TextView addTime;
        TextView carNo;
        XCRoundRectImageView header;
        TextView isDone;
        TextView recordNo;
        RelativeLayout root;
        TextView stationName;

        MyViewHolder(View paramView) {
            super(paramView);
            this.root = ((RelativeLayout) paramView.findViewById(R.id.rl_root));
            this.header = ((XCRoundRectImageView) paramView.findViewById(R.id.img_url));
            this.actionContainer = ((LinearLayout) paramView.findViewById(R.id.ll_action_container));
            this.recordNo = ((TextView) paramView.findViewById(R.id.tv_id));
            this.addTime = ((TextView) paramView.findViewById(R.id.tv_time));
            this.carNo = ((TextView) paramView.findViewById(R.id.tv_car_no));
            this.stationName = ((TextView) paramView.findViewById(R.id.txt_station_name));
            this.isDone = ((TextView) paramView.findViewById(R.id.txt_is_done));
        }
    }
}

