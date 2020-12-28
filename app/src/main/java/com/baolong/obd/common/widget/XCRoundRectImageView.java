package com.baolong.obd.common.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class XCRoundRectImageView extends AppCompatImageView {
    private Paint paint = new Paint();

    public XCRoundRectImageView(Context paramContext) {
        this(paramContext, null);
    }

    public XCRoundRectImageView(Context paramContext, AttributeSet paramAttributeSet) {
        this(paramContext, paramAttributeSet, 0);
    }

    public XCRoundRectImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    @Override
    protected void onDraw(Canvas paramCanvas) {
        Object localObject = getDrawable();
        if (localObject != null) {
            int conner = 40;
            localObject = getRoundBitmap(((BitmapDrawable) localObject).getBitmap(), conner);
            Rect srcRect = new Rect(0, 0, ((Bitmap) localObject).getWidth(), ((Bitmap) localObject).getHeight());
            Rect dstRect = new Rect(0, 0, getWidth(), getHeight());
            this.paint.reset();
            paramCanvas.drawBitmap((Bitmap) localObject, srcRect, dstRect, this.paint);
            return;
        }
        super.onDraw(paramCanvas);
    }

    private Bitmap getRoundBitmap(final Bitmap bitmap,  int n) {
        final Bitmap bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap2);

        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float n2 = (float)n;
        this.paint.setAntiAlias(true);
        this.paint.setColor(Color.BLUE);

        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, n2, n2, this.paint);
        this.paint.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, this.paint);
        return bitmap2;
    }

}
