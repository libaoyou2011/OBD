package com.baolong.obd.querycar.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.baolong.obd.R;
import com.baolong.obd.querycar.data.entity.KeyNameValueModel;

import java.util.List;


public class AutoViewListAdapter extends RecyclerView.Adapter<AutoViewListAdapter.MyViewHolder> {
    private Context mContext;
    private List<KeyNameValueModel> mLists;
    private View.OnClickListener mOnItemClickListener;

    public AutoViewListAdapter(final Context mContext, final View.OnClickListener mOnItemClickListener) {
        this.mContext = mContext;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public List<KeyNameValueModel> getData() {
        if (this.mLists == null) {
            return new ArrayList<KeyNameValueModel>();
        }
        return this.mLists;
    }

    @Override
    public int getItemCount() {
        return ((this.mLists == null) ? 0 : this.mLists.size());
    }

    @Override
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int n) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_detail_car_info, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final @NonNull MyViewHolder holder, final int position) {
        final KeyNameValueModel keyNameValueModel = this.mLists.get(position);
        holder.name.setText((CharSequence) keyNameValueModel.getName());
        holder.value.setText((CharSequence) keyNameValueModel.getValue());
    }

    public void setData(final List<KeyNameValueModel> mLists) {
        this.mLists = mLists;
        this.notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView value;

        MyViewHolder(final View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.txt_key);
            this.value = (TextView) view.findViewById(R.id.txt_value);
        }
    }
}
