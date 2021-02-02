package com.baolong.obd.common.view.recyclerview.itemdecoration;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.graphics.Rect;

public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {
    private int pos;
    private int space;

    public RecyclerViewItemDecoration(final int space) {
        this.space = 0;
        this.space = space;
    }

    public void getItemOffsets(final Rect rect, final View view, final RecyclerView recyclerView, final RecyclerView.State recyclerViewState) {
        rect.top = this.space;
        this.pos = recyclerView.getChildAdapterPosition(view);
        if (this.pos % 2 == 0) {
            rect.left = this.space;
            rect.right = this.space / 2;
        }
        if (this.pos % 2 == 1) {
            rect.left = this.space / 2;
            rect.right = this.space;
        }
    }
}

