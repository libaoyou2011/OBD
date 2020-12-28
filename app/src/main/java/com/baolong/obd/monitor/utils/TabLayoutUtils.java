package com.baolong.obd.monitor.utils;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

public class TabLayoutUtils {
    public TabLayoutUtils() {
        super();
    }

    public static void setIndicator(final TabLayout tabLayout, int lestMargin, int rightMargin, int bottomMargin) {
        final Class<? extends TabLayout> class1 = tabLayout.getClass();
        try {
            final Field declaredField = class1.getDeclaredField("mTabStrip");
            declaredField.setAccessible(true);
            try {
                final LinearLayout linearLayout = (LinearLayout) declaredField.get(tabLayout);
                final int leftMargin = (int) TypedValue.applyDimension(1, (float) lestMargin, Resources.getSystem().getDisplayMetrics());
                rightMargin = (int) TypedValue.applyDimension(1, (float) rightMargin, Resources.getSystem().getDisplayMetrics());
                bottomMargin = (int) TypedValue.applyDimension(1, (float) bottomMargin, Resources.getSystem().getDisplayMetrics());
                View child;
                LinearLayout.LayoutParams layoutParams;
                for (lestMargin = 0; lestMargin < linearLayout.getChildCount(); ++lestMargin) {
                    child = linearLayout.getChildAt(lestMargin);
                    child.setPadding(0, 0, 0, 0);
                    layoutParams = new LinearLayout.LayoutParams(0, -1, 1.0f);
                    layoutParams.leftMargin = leftMargin;
                    layoutParams.rightMargin = rightMargin;
                    layoutParams.bottomMargin = bottomMargin;
                    child.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
                    child.invalidate();
                }
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        } catch (NoSuchFieldException ex2) {
            ex2.printStackTrace();
        }
    }
}