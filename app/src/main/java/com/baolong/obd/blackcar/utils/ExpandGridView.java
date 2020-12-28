package com.baolong.obd.blackcar.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class ExpandGridView extends GridView {
    public ExpandGridView(Context paramContext) {
        super(paramContext);
    }

    public ExpandGridView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public ExpandGridView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //int型的MeasureSpec来表示一个组件的大小，这个变量里面不仅有组件的尺寸大小，还有大小的模式.
        //一个int类型我们知道有32位。而模式有三种，要表示三种状态，至少得2位二进制位。于是系统采用了最高的2位表示模式
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
