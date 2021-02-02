package com.baolong.obd.analysis.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.analysis.data.entity.AnalysisLayoutModel;

import java.util.ArrayList;
import java.util.List;

public class QueryHistoryListAdapter extends RecyclerView.Adapter<QueryHistoryListAdapter.MyViewHolder> {
    private final Context mContext;
    private List<AnalysisLayoutModel.ProductsBean.ItemsBean> mLists;
    private OnHistoryLongClickListener mOnLongClickListener;
    private OnHistoryClickListener mOnHistoryClickListener;

    public QueryHistoryListAdapter(final Context mContext) {
        this.mContext = mContext;
    }

    public QueryHistoryListAdapter(final Context mContext, final OnHistoryClickListener mOnHistoryClickListener) {
        this.mContext = mContext;
        this.mOnHistoryClickListener = mOnHistoryClickListener;
    }

    public QueryHistoryListAdapter(final Context mContext, final OnHistoryLongClickListener mOnLongClickListener, final OnHistoryClickListener mOnHistoryClickListener) {
        this.mContext = mContext;
        this.mOnLongClickListener = mOnLongClickListener;
        this.mOnHistoryClickListener = mOnHistoryClickListener;
    }

    public void setData(final List<AnalysisLayoutModel.ProductsBean.ItemsBean> mLists) {
        this.mLists = mLists;
        this.notifyDataSetChanged();
    }

    public List<AnalysisLayoutModel.ProductsBean.ItemsBean> getData() {
        if (this.mLists == null) {
            return new ArrayList<AnalysisLayoutModel.ProductsBean.ItemsBean>();
        }
        return this.mLists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_analysis, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final AnalysisLayoutModel.ProductsBean.ItemsBean itemsBean = this.mLists.get(position);
        holder.root.setTag((Object) itemsBean);
        try {
            int resId = mContext.getResources().getIdentifier(itemsBean.getIcon(), "mipmap", mContext.getPackageName());
            holder.imageView.setImageResource(resId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.textView.setText((CharSequence) itemsBean.getTitle());

        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnHistoryClickListener != null) {
                    mOnHistoryClickListener.onItemClick(itemsBean);
                }
            }
        });

        holder.root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnLongClickListener != null) {
                    mOnLongClickListener.onLongClick(itemsBean);
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
        void onItemClick(final AnalysisLayoutModel.ProductsBean.ItemsBean p0);
    }

    public interface OnHistoryLongClickListener {
        void onLongClick(final AnalysisLayoutModel.ProductsBean.ItemsBean p0);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout root;
        ImageView imageView;
        TextView textView;

        MyViewHolder(final View view) {
            super(view);
            this.root = (ConstraintLayout) view.findViewById(R.id.root);
            this.imageView = (ImageView) view.findViewById(R.id.image) ;
            this.textView = (TextView) view.findViewById(R.id.text);
        }
    }
}
