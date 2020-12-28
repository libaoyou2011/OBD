package com.baolong.obd.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class RecyclerViewForEmpty extends RecyclerView {
    //成员变量
    private View emptyView;

    //成员变量
    private RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            if (RecyclerViewForEmpty.this.getAdapter() != null && RecyclerViewForEmpty.this.getAdapter().getItemCount() == 0) {
                if (RecyclerViewForEmpty.this.emptyView != null) {
                    RecyclerViewForEmpty.this.emptyView.setVisibility(View.VISIBLE);
                    RecyclerViewForEmpty.this.setVisibility(View.GONE);
                }

            } else {
                if (RecyclerViewForEmpty.this.emptyView != null) {
                    RecyclerViewForEmpty.this.emptyView.setVisibility(View.GONE);
                    RecyclerViewForEmpty.this.setVisibility(VISIBLE);
                }

            }
        }

        @Override
        public void onItemRangeChanged(int paramAnonymousInt1, int paramAnonymousInt2) {
            onChanged();
        }

        @Override
        public void onItemRangeChanged(int paramAnonymousInt1, int paramAnonymousInt2, Object paramAnonymousObject) {
            onChanged();
        }

        @Override
        public void onItemRangeInserted(int paramAnonymousInt1, int paramAnonymousInt2) {
            onChanged();
        }

        @Override
        public void onItemRangeMoved(int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {
            onChanged();
        }

        @Override
        public void onItemRangeRemoved(int paramAnonymousInt1, int paramAnonymousInt2) {
            onChanged();
        }
    };

    //构造方法
    public RecyclerViewForEmpty(Context paramContext) {
        super(paramContext);
    }

    //构造方法
    public RecyclerViewForEmpty(Context paramContext, @Nullable AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    //成员方法
    public void setAdapter(RecyclerView.Adapter paramAdapter) {
        super.setAdapter(paramAdapter);
        paramAdapter.registerAdapterDataObserver(this.observer);
        this.observer.onChanged();
    }

    //成员方法
    public void setEmptyView(View paramView) {
        this.emptyView = paramView;
        ((ViewGroup) getRootView()).addView(paramView);
    }
}
