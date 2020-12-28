package com.baolong.obd.monitor.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

import net.lucode.hackware.magicindicator.NavigatorHelper;
import net.lucode.hackware.magicindicator.abs.IPagerNavigator;
import net.lucode.hackware.magicindicator.buildins.ArgbEvaluatorHolder;
import net.lucode.hackware.magicindicator.buildins.UIUtil;

public class ScaleCircleNavigator extends View
        implements IPagerNavigator, NavigatorHelper.OnNavigatorScrollListener {
    private OnCircleClickListener mCircleClickListener;
    private int mCircleCount;
    private List<PointF> mCirclePoints = new ArrayList();
    private SparseArray<Float> mCircleRadiusArray = new SparseArray();
    private int mCircleSpacing;
    private float mDownX;
    private float mDownY;
    private boolean mFollowTouch = true;
    private int mMaxRadius;
    private int mMinRadius;
    private NavigatorHelper mNavigatorHelper = new NavigatorHelper();
    private int mNormalCircleColor = -3355444;
    private Paint mPaint = new Paint(1);
    private int mSelectedCircleColor = -7829368;
    private Interpolator mStartInterpolator = new LinearInterpolator();
    private int mTouchSlop;
    private boolean mTouchable;

    public ScaleCircleNavigator(Context paramContext) {
        super(paramContext);
        init(paramContext);
    }

    private void init(Context paramContext) {
        this.mTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
        this.mMinRadius = UIUtil.dip2px(paramContext, 3.0D);
        this.mMaxRadius = UIUtil.dip2px(paramContext, 5.0D);
        this.mCircleSpacing = UIUtil.dip2px(paramContext, 8.0D);
        this.mNavigatorHelper.setNavigatorScrollListener(this);
        this.mNavigatorHelper.setSkimOver(true);
    }

    private int measureHeight(int paramInt) {
        int i = View.MeasureSpec.getMode(paramInt);
        paramInt = View.MeasureSpec.getSize(paramInt);
        switch (i) {
            default:
                return 0;
            case 0:
            case -2147483648:
                paramInt = this.mMaxRadius * 2 + getPaddingTop() + getPaddingBottom();
        }
        return paramInt;
    }

    private int measureWidth(int paramInt) {
        int i = View.MeasureSpec.getMode(paramInt);
        paramInt = View.MeasureSpec.getSize(paramInt);
        switch (i) {
            default:
                return 0;
            case 0:
            case -2147483648:
                paramInt = (this.mCircleCount - 1) * this.mMinRadius * 2 + this.mMaxRadius * 2 + (this.mCircleCount - 1) * this.mCircleSpacing + getPaddingLeft() + getPaddingRight();
        }
        return paramInt;
    }

    private void prepareCirclePoints() {
        this.mCirclePoints.clear();
        if (this.mCircleCount > 0) {
            int k = Math.round(getHeight() / 2.0F);
            int m = this.mMinRadius;
            int n = this.mCircleSpacing;
            int j = this.mMaxRadius + getPaddingLeft();
            int i = 0;
            while (i < this.mCircleCount) {
                PointF localPointF = new PointF(j, k);
                this.mCirclePoints.add(localPointF);
                j += m * 2 + n;
                i += 1;
            }
        }
    }

    public void notifyDataSetChanged() {
        prepareCirclePoints();
        invalidate();
    }

    public void onAttachToMagicIndicator() {
    }

    public void onDeselected(int paramInt1, int paramInt2) {
        if (!this.mFollowTouch) {
            this.mCircleRadiusArray.put(paramInt1, Float.valueOf(this.mMinRadius));
            invalidate();
        }
    }

    public void onDetachFromMagicIndicator() {
    }

    protected void onDraw(Canvas paramCanvas) {
        int j = this.mCirclePoints.size();
        int i = 0;
        while (i < j) {
            PointF localPointF = (PointF) this.mCirclePoints.get(i);
            float f = ((Float) this.mCircleRadiusArray.get(i, Float.valueOf(this.mMinRadius))).floatValue();
            this.mPaint.setColor(ArgbEvaluatorHolder.eval((f - this.mMinRadius) / (this.mMaxRadius - this.mMinRadius), this.mNormalCircleColor, this.mSelectedCircleColor));
            paramCanvas.drawCircle(localPointF.x, getHeight() / 2.0F, f, this.mPaint);
            i += 1;
        }
    }

    public void onEnter(int paramInt1, int paramInt2, float paramFloat, boolean paramBoolean) {
        if (this.mFollowTouch) {
            float f1 = this.mMinRadius;
            float f2 = this.mMaxRadius - this.mMinRadius;
            paramFloat = this.mStartInterpolator.getInterpolation(paramFloat);
            this.mCircleRadiusArray.put(paramInt1, Float.valueOf(f1 + paramFloat * f2));
            invalidate();
        }
    }

    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        prepareCirclePoints();
    }

    public void onLeave(int paramInt1, int paramInt2, float paramFloat, boolean paramBoolean) {
        if (this.mFollowTouch) {
            float f1 = this.mMaxRadius;
            float f2 = this.mMinRadius - this.mMaxRadius;
            paramFloat = this.mStartInterpolator.getInterpolation(paramFloat);
            this.mCircleRadiusArray.put(paramInt1, Float.valueOf(f1 + paramFloat * f2));
            invalidate();
        }
    }

    protected void onMeasure(int paramInt1, int paramInt2) {
        setMeasuredDimension(measureWidth(paramInt1), measureHeight(paramInt2));
    }

    public void onPageScrollStateChanged(int paramInt) {
        this.mNavigatorHelper.onPageScrollStateChanged(paramInt);
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
        this.mNavigatorHelper.onPageScrolled(paramInt1, paramFloat, paramInt2);
    }

    public void onPageSelected(int paramInt) {
        this.mNavigatorHelper.onPageSelected(paramInt);
    }

    public void onSelected(int paramInt1, int paramInt2) {
        if (!this.mFollowTouch) {
            this.mCircleRadiusArray.put(paramInt1, Float.valueOf(this.mMaxRadius));
            invalidate();
        }
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent) {
        float f4 = paramMotionEvent.getX();
        float f1 = paramMotionEvent.getY();
        switch (paramMotionEvent.getAction()) {
            default:
                break;
            case 1:
                if ((this.mCircleClickListener != null) && (Math.abs(f4 - this.mDownX) <= this.mTouchSlop) && (Math.abs(f1 - this.mDownY) <= this.mTouchSlop)) {
                    int j = 0;
                    f1 = Float.MAX_VALUE;
                    int i = 0;
                    while (i < this.mCirclePoints.size()) {
                        float f3 = Math.abs(((PointF) this.mCirclePoints.get(i)).x - f4);
                        float f2 = f1;
                        if (f3 < f1) {
                            f2 = f3;
                            j = i;
                        }
                        i += 1;
                        f1 = f2;
                    }
                    this.mCircleClickListener.onClick(j);
                }
                break;
            case 0:
                if (this.mTouchable) {
                    this.mDownX = f4;
                    this.mDownY = f1;
                    return true;
                }
                break;
        }
        return super.onTouchEvent(paramMotionEvent);
    }

    public void setCircleClickListener(OnCircleClickListener paramOnCircleClickListener) {
        if (!this.mTouchable) {
            this.mTouchable = true;
        }
        this.mCircleClickListener = paramOnCircleClickListener;
    }

    public void setCircleCount(int paramInt) {
        this.mCircleCount = paramInt;
        this.mNavigatorHelper.setTotalCount(this.mCircleCount);
    }

    public void setCircleSpacing(int paramInt) {
        this.mCircleSpacing = paramInt;
        prepareCirclePoints();
        invalidate();
    }

    public void setFollowTouch(boolean paramBoolean) {
        this.mFollowTouch = paramBoolean;
    }

    public void setMaxRadius(int paramInt) {
        this.mMaxRadius = paramInt;
        prepareCirclePoints();
        invalidate();
    }

    public void setMinRadius(int paramInt) {
        this.mMinRadius = paramInt;
        prepareCirclePoints();
        invalidate();
    }

    public void setNormalCircleColor(int paramInt) {
        this.mNormalCircleColor = paramInt;
        invalidate();
    }

    public void setSelectedCircleColor(int paramInt) {
        this.mSelectedCircleColor = paramInt;
        invalidate();
    }

    public void setSkimOver(boolean paramBoolean) {
        this.mNavigatorHelper.setSkimOver(paramBoolean);
    }

    public void setStartInterpolator(Interpolator paramInterpolator) {
        this.mStartInterpolator = paramInterpolator;
        if (this.mStartInterpolator == null) {
            this.mStartInterpolator = new LinearInterpolator();
        }
    }

    public void setTouchable(boolean paramBoolean) {
        this.mTouchable = paramBoolean;
    }

    public static abstract interface OnCircleClickListener {
        public abstract void onClick(int paramInt);
    }
}
