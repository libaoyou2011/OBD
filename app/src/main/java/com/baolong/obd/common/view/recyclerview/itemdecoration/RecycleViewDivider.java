package com.baolong.obd.common.view.recyclerview.itemdecoration;

import androidx.annotation.NonNull;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Canvas;
import android.content.res.TypedArray;
import android.util.Log;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.Rect;

public class RecycleViewDivider extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS;
    public static final int HORIZONTAL = 0;
    private static final String TAG = "DividerItem";
    public static final int VERTICAL = 1;
    private final Rect mBounds;
    private Drawable mDivider;
    private int mOrientation;

    static {
        ATTRS = new int[]{16843284};
    }

    public RecycleViewDivider(final Context context, final int orientation) {
        this.mBounds = new Rect();
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(RecycleViewDivider.ATTRS);
        this.mDivider = obtainStyledAttributes.getDrawable(0);
        if (this.mDivider == null) {
            Log.w("DividerItem", "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        obtainStyledAttributes.recycle();
        this.setOrientation(orientation);
    }

    private void drawHorizontal(final Canvas canvas, final RecyclerView recyclerView) {
        canvas.save();
        final boolean clipToPadding = recyclerView.getClipToPadding();
        int i = 0;
        int paddingTop;
        int height;
        if (clipToPadding) {
            paddingTop = recyclerView.getPaddingTop();
            height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
            canvas.clipRect(recyclerView.getPaddingLeft(), paddingTop, recyclerView.getWidth() - recyclerView.getPaddingRight(), height);
        } else {
            height = recyclerView.getHeight();
            paddingTop = 0;
        }
        while (i < recyclerView.getChildCount()) {
            final View child = recyclerView.getChildAt(i);
            recyclerView.getLayoutManager().getDecoratedBoundsWithMargins(child, this.mBounds);
            final int n = Math.round(child.getTranslationX()) + this.mBounds.right;
            this.mDivider.setBounds(n - this.mDivider.getIntrinsicWidth(), paddingTop, n, height);
            this.mDivider.draw(canvas);
            ++i;
        }
        canvas.restore();
    }

    private void drawVertical(final Canvas canvas, final RecyclerView recyclerView) {
        canvas.save();
        final boolean clipToPadding = recyclerView.getClipToPadding();
        int i = 0;
        int paddingLeft;
        int width;
        if (clipToPadding) {
            paddingLeft = recyclerView.getPaddingLeft();
            width = recyclerView.getWidth() - recyclerView.getPaddingRight();
            canvas.clipRect(paddingLeft, recyclerView.getPaddingTop(), width, recyclerView.getHeight() - recyclerView.getPaddingBottom());
        } else {
            width = recyclerView.getWidth();
            paddingLeft = 0;
        }
        while (i < recyclerView.getChildCount() - 1) {
            final View child = recyclerView.getChildAt(i);
            recyclerView.getDecoratedBoundsWithMargins(child, this.mBounds);
            final int n = Math.round(child.getTranslationY()) + this.mBounds.bottom;
            this.mDivider.setBounds(paddingLeft, n - this.mDivider.getIntrinsicHeight(), width, n);
            this.mDivider.draw(canvas);
            ++i;
        }
        canvas.restore();
    }

    public void getItemOffsets(final Rect rect, final View view, final RecyclerView recyclerView, final RecyclerView.State recyclerView$State) {
        if (this.mDivider == null) {
            rect.set(0, 0, 0, 0);
            return;
        }
        if (this.mOrientation == 1) {
            rect.set(0, 0, 0, this.mDivider.getIntrinsicHeight());
            return;
        }
        rect.set(0, 0, this.mDivider.getIntrinsicWidth(), 0);
    }

    public void onDraw(final Canvas canvas, final RecyclerView recyclerView, final RecyclerView.State recyclerView$State) {
        if (recyclerView.getLayoutManager() != null) {
            if (this.mDivider == null) {
                return;
            }
            if (this.mOrientation == 1) {
                this.drawVertical(canvas, recyclerView);
                return;
            }
            this.drawHorizontal(canvas, recyclerView);
        }
    }

    public void setDrawable(@NonNull final Drawable mDivider) {
        if (mDivider == null) {
            throw new IllegalArgumentException("Drawable cannot be null.");
        }
        this.mDivider = mDivider;
    }

    public void setOrientation(final int mOrientation) {
        if (mOrientation != 0 && mOrientation != 1) {
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        this.mOrientation = mOrientation;
    }
}
