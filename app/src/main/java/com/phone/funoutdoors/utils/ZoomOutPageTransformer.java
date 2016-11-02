package com.phone.funoutdoors.utils;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Lenovo-SXX on 2016/10/26.
 */
public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    @SuppressLint("NewApi")
    public void transformPage(View view, float position) {
        if (position >= -1 && position < 0) {
            view.setPivotX(0);//设置要旋转的Y轴的位置
            view.setPivotY(0);
            view.setScaleX(1 - Math.abs(position));//开始设置属性动画值
            view.setTranslationX(view.getWidth() * Math.abs(position));
        }
        if (position > 0 && position <= 1) {
            view.setPivotY(view.getWidth());
            view.setScaleX(1 - position);
        }
    }
}
