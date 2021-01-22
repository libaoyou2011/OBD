package com.baolong.obd.execution.adapter;

import android.content.Context;
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
import com.baolong.obd.execution.data.entity.OBDCar;

import java.util.ArrayList;
import java.util.List;

public class ExecListAdapter extends RecyclerView.Adapter<ExecListAdapter.MyViewHolder> {
    private Context mContext;
    private String mType;
    private List<OBDCar> mLists;
    private OnItemClickListener mItemClickListener;
    private OnItemLongClickListener mItemLongClickListener;
    private OnActionClickListener mOnActionClickListener;

    public ExecListAdapter(Context paramContext, String paramString) {
        this.mContext = paramContext;
        this.mType = paramString;
    }

    public void setData(List<OBDCar> paramList) {
        this.mLists = paramList;
        notifyDataSetChanged();
    }

    public List<OBDCar> getData() {
        if (this.mLists == null) {
            return new ArrayList<OBDCar>();
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

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exec_list, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final OBDCar exhaust = (OBDCar) this.mLists.get(position);

        //root点击事件在ExeListFragment中回调，将该item数据返回
        holder.root.setTag(exhaust);

        if (exhaust != null) {
            holder.hphmTv.setText(TextUtils.isEmpty(exhaust.getHphm()) ? "--" : exhaust.getHphm());
            holder.vinTV.setText(TextUtils.isEmpty(exhaust.getVin()) ? "--" : exhaust.getVin());
            holder.obdbhTv.setText(TextUtils.isEmpty(exhaust.getObdbh()) ? "--" : exhaust.getObdbh());
            holder.ssqyTv.setText(TextUtils.isEmpty(exhaust.getSszz()) ? "--" : exhaust.getSszz());
        }

        // item项右上角审核状态
//        if ("0".equals(this.mType)) {
//            holder.actionContainer.setBackgroundResource(R.drawable.exec_no_do);
//            holder.isDone.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, this.mContext.getResources().getDrawable(R.drawable.ic_exec_do), (Drawable) null, (Drawable) null);
//            holder.isDone.setText(R.string.execution_do);
//        } else {
//            holder.actionContainer.setBackgroundResource(R.drawable.exec_done);
//            holder.isDone.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, this.mContext.getResources().getDrawable(R.drawable.ic_exec_look), (Drawable) null, (Drawable) null);
//            holder.isDone.setText(R.string.execution_look);
//        }

        /*if (this.mItemClickListener != null) {
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
        }*/
    }

    public static abstract interface OnActionClickListener {
        public abstract void onActionClick(OBDCar exhaust, String paramString);
    }

    public static abstract interface OnItemLongClickListener {
        public abstract boolean onItemLongClick(OBDCar exhaust);
    }

    public static abstract interface OnItemClickListener {
        public abstract void onItemClick(OBDCar exhaust, String paramString);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout root;
        TextView hphmTv; //车牌号
        TextView vinTV; //VIN
        TextView obdbhTv; //obd编号
        TextView ssqyTv; //所属企业

        LinearLayout actionContainer;
        TextView isDone;

        MyViewHolder(View paramView) {
            super(paramView);
            this.root = ((RelativeLayout) paramView.findViewById(R.id.rl_root));
            this.hphmTv = ((TextView) paramView.findViewById(R.id.tv_vin_content));
            this.vinTV = ((TextView) paramView.findViewById(R.id.tv_cph_content));
            this.obdbhTv = ((TextView) paramView.findViewById(R.id.tv_zdh_content));
            this.ssqyTv = ((TextView) paramView.findViewById(R.id.txt_time_content));

            this.actionContainer = ((LinearLayout) paramView.findViewById(R.id.ll_action_container));
            this.isDone = ((TextView) paramView.findViewById(R.id.txt_is_done));
            this.actionContainer.setVisibility(View.GONE);
        }
    }
}

