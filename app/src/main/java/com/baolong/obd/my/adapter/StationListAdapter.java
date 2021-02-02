package com.baolong.obd.my.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.my.data.entity.GetStationListAllResponseModel;

import java.util.ArrayList;
import java.util.List;

public class StationListAdapter extends RecyclerView.Adapter<StationListAdapter.MyViewHolder> {
    private Context mContext;
    private List<GetStationListAllResponseModel> mLists;
    private OnItemClickListener mOnItemClickListener;

    public StationListAdapter(Context paramContext, OnItemClickListener paramOnItemClickListener) {
        this.mContext = paramContext;
        this.mOnItemClickListener = paramOnItemClickListener;
    }

    public void setData(List<GetStationListAllResponseModel> paramList) {
        this.mLists = paramList;
        notifyDataSetChanged();
    }

    public List<GetStationListAllResponseModel> getData() {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_item_station_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GetStationListAllResponseModel model = (GetStationListAllResponseModel) this.mLists.get(position);
        holder.root.setTag(model);
        holder.stationNoTv.setText(model.getJzbh());
        holder.stationNameTv.setText(model.getJzmc());
        holder.stationTypeTv.setText(model.getJzlx());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(model);
                }
            }
        });
    }


    public interface OnItemClickListener {
        void onItemClick(GetStationListAllResponseModel model);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout root;
        TextView stationNameTv;
        TextView stationNoTv;
        TextView stationTypeTv;

        MyViewHolder(View view) {
            super(view);
            this.root = ((RelativeLayout) view.findViewById(R.id.rl_root));
            this.stationNoTv = ((TextView) view.findViewById(R.id.tv_code));
            this.stationNameTv = ((TextView) view.findViewById(R.id.tv_station_name));
            this.stationTypeTv = ((TextView) view.findViewById(R.id.tv_station_type));
        }
    }
}
