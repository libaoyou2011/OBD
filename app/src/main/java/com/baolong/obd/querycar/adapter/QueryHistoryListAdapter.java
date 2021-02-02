package com.baolong.obd.querycar.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.querycar.data.entity.GetVehicleQueryListResponseModel;

import java.util.ArrayList;
import java.util.List;

public class QueryHistoryListAdapter extends RecyclerView.Adapter<QueryHistoryListAdapter.MyViewHolder> {
    private Context mContext;
    private List<GetVehicleQueryListResponseModel> mLists;
    private OnHistoryLongClickListener mOnLongClickListener;
    private OnHistoryClickListener mOnHistoryClickListener;

    public QueryHistoryListAdapter(final Context mContext, final OnHistoryLongClickListener mOnLongClickListener, final OnHistoryClickListener mOnHistoryClickListener) {
        this.mContext = mContext;
        this.mOnLongClickListener = mOnLongClickListener;
        this.mOnHistoryClickListener = mOnHistoryClickListener;
    }

    public void setData(final List<GetVehicleQueryListResponseModel> mLists) {
        this.mLists = mLists;
        this.notifyDataSetChanged();
    }

    public List<GetVehicleQueryListResponseModel> getData() {
        if (this.mLists == null) {
            return new ArrayList<GetVehicleQueryListResponseModel>();
        }
        return this.mLists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_query_history, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final GetVehicleQueryListResponseModel model = this.mLists.get(position);
        holder.root.setTag((Object)model);
        holder.carTv.setText((CharSequence)model.getHphm());
        holder.timeTv.setText((CharSequence)model.getQueryTime());

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnHistoryClickListener != null) {
                    mOnHistoryClickListener.onItemClick(model);
                }
            }
        });
        holder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnLongClickListener != null) {
                    mOnLongClickListener.onLongClick(model);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (this.mLists == null ? 0 : this.mLists.size());
    }

    public interface OnHistoryClickListener {
        void onItemClick(final GetVehicleQueryListResponseModel p0);
    }

    public interface OnHistoryLongClickListener {
        void onLongClick(final GetVehicleQueryListResponseModel p0);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView carTv;
        LinearLayout root;
        TextView timeTv;

        MyViewHolder(final View view) {
            super(view);
            this.root = (LinearLayout) view.findViewById(R.id.rl_root);
            this.timeTv = (TextView) view.findViewById(R.id.tv_search_time);
            this.carTv = (TextView) view.findViewById(R.id.tv_car_no);
        }
    }
}
