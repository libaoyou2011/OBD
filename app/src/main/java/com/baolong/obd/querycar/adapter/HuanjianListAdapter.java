package com.baolong.obd.querycar.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;
import android.content.Context;

import com.baolong.obd.R;
import com.baolong.obd.blackcar.data.entity.HuanjianModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 车辆 (环检/年检) Adapter
 */
public class HuanjianListAdapter extends RecyclerView.Adapter<HuanjianListAdapter.MyViewHolder> {
    private Context mContext;
    private List<HuanjianModel> mLists;    //private List<GetJyListResponseModel.ModelChild> mLists;
    private final OnItemClickListener mOnItemClickListener;

    public HuanjianListAdapter(final Context mContext, final OnItemClickListener mOnItemClickListener) {
        this.mContext = mContext;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setData(final List<HuanjianModel> mLists) {
        this.mLists = mLists;
        this.notifyDataSetChanged();
    }

    public List<HuanjianModel> getData() {
        if (this.mLists == null) {
            return new ArrayList<HuanjianModel>();
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
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int n) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_check_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final HuanjianModel model = this.mLists.get(position);
        holder.root.setTag(model);

        holder.bhTv.setText(model.getTestNo());
        holder.carNumTV.setText((CharSequence) model.getHphm());
        holder.testTime.setText((CharSequence) model.getTestDate());
        if (!TextUtils.isEmpty(model.getResult())) {
            switch (model.getResult()) {
                case "0":
                    holder.jgBt.setText("不合格");
                    break;
                case "1":
                    holder.jgBt.setText("合格");
                    break;
                case "2":
                    holder.jgBt.setText("无效");
                    break;
            }
        }

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HuanjianListAdapter.this.mOnItemClickListener != null) {
                    HuanjianListAdapter.this.mOnItemClickListener.onItemClick(model);
                }
            }
        });

    }

    public interface OnItemClickListener {
        void onItemClick(final HuanjianModel p0);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout root;
        TextView bhTv;
        TextView carNumTV;
        TextView testTime;
        Button jgBt;

        MyViewHolder(final View view) {
            super(view);
            this.root = (RelativeLayout) view.findViewById(R.id.rl_root);
            this.bhTv = (TextView) view.findViewById(R.id.tv_code);
            this.carNumTV = (TextView) view.findViewById(R.id.tv_car_num);
            this.testTime = (TextView) view.findViewById(R.id.tv_time);
            this.jgBt = (Button) view.findViewById(R.id.btn_status);
        }
    }
}
