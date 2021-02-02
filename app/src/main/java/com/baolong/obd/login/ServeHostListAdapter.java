package com.baolong.obd.login;

import androidx.recyclerview.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.RadioButton;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import android.view.View;

import com.baolong.obd.R;
import com.baolong.obd.login.model.ServerHostInfoModel;

import java.util.List;

import android.content.Context;

public class ServeHostListAdapter extends RecyclerView.Adapter<ServeHostListAdapter.MyViewHolder> {
    private Context mContext;
    private List<ServerHostInfoModel> mModels;
    private OnItemClickListener mItemClickListener;

    public ServeHostListAdapter(final Context mContext, final List<ServerHostInfoModel> mModels) {
        this.mContext = mContext;
        this.mModels = mModels;
    }

    public void setmModels(final List<ServerHostInfoModel> mModels) {
        this.mModels = mModels;
        this.notifyDataSetChanged();
    }

    public List<ServerHostInfoModel> getmModels() {
        return this.mModels;
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public int getItemCount() {
        if (this.mModels == null) {
            return 0;
        }
        return this.mModels.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int n) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_server_host, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder viewHolder, final int position) {
        final ServerHostInfoModel model = this.mModels.get(position);
        viewHolder.root.setTag((Object) model);
        viewHolder.nameTV.setText(model.getCityName());
        viewHolder.chooseRB.setChecked(model.isCheck());
        if (this.mItemClickListener != null) {
            viewHolder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(model);
                }
            });
        }
        viewHolder.chooseRB.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
            public void onClick(final View view) {
                for (int i = 0; i < ServeHostListAdapter.this.mModels.size(); ++i) {
                    ((ServerHostInfoModel) ServeHostListAdapter.this.mModels.get(i)).setCheck(false);
                }
                model.setCheck(viewHolder.chooseRB.isChecked());
                ServeHostListAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(final ServerHostInfoModel p0);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout root;
        RadioButton chooseRB;
        TextView nameTV;

        MyViewHolder(final View view) {
            super(view);
            this.root = (LinearLayout) view.findViewById(R.id.ll_root);
            this.chooseRB = (RadioButton) view.findViewById(R.id.rb_choose);
            this.nameTV = (TextView) view.findViewById(R.id.item_tv_name);
        }
    }
}
