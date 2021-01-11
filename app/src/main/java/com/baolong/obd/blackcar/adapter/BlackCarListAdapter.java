package com.baolong.obd.blackcar.adapter;

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
import com.baolong.obd.blackcar.data.entity.ExhaustZC;
import com.baolong.obd.common.widget.XCRoundRectImageView;
import com.baolong.obd.component.media.AppImageDisplay;

import java.util.ArrayList;
import java.util.List;

public class BlackCarListAdapter extends RecyclerView.Adapter<BlackCarListAdapter.MyViewHolder> {
    private Context mContext;
    private String mType;
    private OnItemClickListener mItemClickListener;
    private OnItemLongClickListener mItemLongClickListener;
    private OnActionClickListener mOnActionClickListener;
    private List<ExhaustZC> mLists;

    public BlackCarListAdapter(Context paramContext, String paramString) {
        this.mContext = paramContext;
        this.mType = paramString;
    }

    public void setData(List<ExhaustZC> paramList) {
        this.mLists = paramList;
        notifyDataSetChanged();
    }

    public List<ExhaustZC> getData() {
        if (this.mLists == null) {
            return new ArrayList<ExhaustZC>();
        }
        return this.mLists;
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
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exec_list_black_car, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        ExhaustZC exhaust = (ExhaustZC) this.mLists.get(position);
        //root点击事件在ExeListFragment中回调，将该item数据返回
        myViewHolder.root.setTag(exhaust);

        if (exhaust != null) {
            myViewHolder.vinTv.setText(TextUtils.isEmpty(exhaust.getVin()) ? "--" : exhaust.getVin());
            myViewHolder.cphTV.setText(TextUtils.isEmpty(exhaust.getHphm()) ? "--" : exhaust.getHphm());
            myViewHolder.zdhTv.setText(TextUtils.isEmpty(exhaust.getObdbh()) ? "--" : exhaust.getObdbh());
            myViewHolder.cjsjTv.setText(TextUtils.isEmpty(exhaust.getCjsj()) ? "--" : exhaust.getCjsj());
        }

        // item项右上角审核状态
        //if (exhaust.getBlackSmokeReview() == 0) {
//        if (Table_wsh.equals(this.mType)) {
//            //未审核 --> 审核
//            myViewHolder.actionContainer.setBackgroundResource(R.drawable.exec_no_do);
//            Drawable drawable = this.mContext.getResources().getDrawable(R.drawable.ic_exec_do);
//            myViewHolder.isDone.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
//            myViewHolder.isDone.setText(R.string.execution_sh);
//
//        } else {
            //已审核 --> 查看
            myViewHolder.actionContainer.setBackgroundResource(R.drawable.exec_done);
            Drawable drawable = this.mContext.getResources().getDrawable(R.drawable.ic_exec_look);
            myViewHolder.isDone.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            myViewHolder.isDone.setText(R.string.execution_look);
//        }

        // 是否黑烟车项：是否显示
//        if (Table_wsh.equals(this.mType) || Table_telemetry.equals(this.mType)) {
//            //如果是 "未审核" 导航页，则不显示该项
//            myViewHolder.isBlackCarLl.setVisibility(View.GONE);
//        } else {
            //如果是 "已审核"、"所有数据" 导航页，则显示是否是黑烟车
//V3报错
//            if (exhaust.getBlackSmokeReview() != 0) {  //已审核
//                if (exhaust.getIsBlackCar() == 1) {
//                    //显示：是黑烟车
//                    myViewHolder.isBlackCarLl.setVisibility(View.VISIBLE);
//                    myViewHolder.isBlackCar.setVisibility(View.VISIBLE);
//                    myViewHolder.isBlackCar.setText("是");
//                } else {
//                    //显示：不是黑烟车
//                    myViewHolder.isBlackCarLl.setVisibility(View.VISIBLE);
//                    myViewHolder.isBlackCar.setVisibility(View.VISIBLE);
//                    myViewHolder.isBlackCar.setText("否");
//                }
//            }
//        }

        if (this.mItemClickListener != null) {
            myViewHolder.root.setOnClickListener((view) -> {
                this.mItemClickListener.onItemClick(exhaust, this.mType);
            });
        }

//        if (this.mItemLongClickListener != null) {
//            myViewHolder.root.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    return mItemLongClickListener.onItemLongClick(exhaust);
//                }
//            });
//        }

        if (this.mOnActionClickListener != null) {
            myViewHolder.actionContainer.setOnClickListener((view) -> {
                this.mOnActionClickListener.onActionClick(exhaust, this.mType);
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ExhaustZC dataDetail, String s);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(ExhaustZC exhaust);
    }

    public interface OnActionClickListener {
        void onActionClick(ExhaustZC dataDetail, String mType);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout root;
        TextView vinTv; //车架号
        TextView cphTV; //车牌号
        TextView zdhTv; //终端号
        TextView cjsjTv; //采集时间

        LinearLayout actionContainer;
        TextView isDone;

        MyViewHolder(View paramView) {
            super(paramView);
            this.root = ((RelativeLayout) paramView.findViewById(R.id.rl_root));
            this.vinTv = ((TextView) paramView.findViewById(R.id.tv_vin_content));
            this.cphTV = ((TextView) paramView.findViewById(R.id.tv_cph_content));
            this.zdhTv = ((TextView) paramView.findViewById(R.id.tv_zdh_content));
            this.cjsjTv = ((TextView) paramView.findViewById(R.id.txt_time_content));

            this.actionContainer = ((LinearLayout) paramView.findViewById(R.id.ll_action_container));
            this.isDone = ((TextView) paramView.findViewById(R.id.txt_is_done));
        }
    }
}
