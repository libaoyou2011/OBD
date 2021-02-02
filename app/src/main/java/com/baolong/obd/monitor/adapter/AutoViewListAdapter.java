package com.baolong.obd.monitor.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.monitor.data.entity.KeyNameValueModel;

import java.util.ArrayList;
import java.util.List;

public class AutoViewListAdapter extends RecyclerView.Adapter<AutoViewListAdapter.ViewHolder> {
    private Context mContext;
    private View.OnClickListener mOnItemClickListener;
    private List<KeyNameValueModel> mLists;

    public AutoViewListAdapter(Context paramContext, View.OnClickListener paramOnClickListener) {
        this.mContext = paramContext;
        this.mOnItemClickListener = paramOnClickListener;
    }

    public void setData(List<KeyNameValueModel> paramList) {
        this.mLists = paramList;
        notifyDataSetChanged();
    }

    public List<KeyNameValueModel> getData() {
        if (this.mLists == null) {
            return new ArrayList();
        }
        return this.mLists;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup paramViewGroup, int paramInt) {
        View view = LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.item_station_detail_jc, paramViewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder paramViewHolder, int paramInt) {
        KeyNameValueModel localKeyNameValueModel = (KeyNameValueModel) this.mLists.get(paramInt);
        paramViewHolder.name.setText(localKeyNameValueModel.getName());
        paramViewHolder.value.setText(localKeyNameValueModel.getValue());
    }

    @Override
    public int getItemCount() {
        if (this.mLists == null) {
            return 0;
        }
        return this.mLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView value;

        ViewHolder(View paramView) {
            super(paramView);
            this.name = ((TextView) paramView.findViewById(R.id.item_txt_key));
            this.value = ((TextView) paramView.findViewById(R.id.item_txt_value));
        }
    }
}
