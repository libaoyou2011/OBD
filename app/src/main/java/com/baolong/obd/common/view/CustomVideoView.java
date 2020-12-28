package com.baolong.obd.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class CustomVideoView extends VideoView {
    int height = 1080;
    int width = 1920;

    public CustomVideoView(Context paramContext) {
        super(paramContext);
    }

    public CustomVideoView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public CustomVideoView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    @Override
    protected void onMeasure(int paramInt1, int paramInt2) {
        super.onMeasure(paramInt1, paramInt2);
        setMeasuredDimension(getDefaultSize(this.width, paramInt1), getDefaultSize(this.height, paramInt2));
    }
}
