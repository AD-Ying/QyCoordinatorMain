package com.qy.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.qy.R;

public class MyViewPager extends ViewPager {

    private boolean isScroll;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.viewPager);
        isScroll = array.getBoolean(R.styleable.viewPager_isScroll, true);
        array.recycle();
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        if (isScroll) {
            super.setCurrentItem(item, smoothScroll);
        } else {
            super.setCurrentItem(item, false);
        }
    }

    @Override
    public void setCurrentItem(int item) {
        if (isScroll) {
            super.setCurrentItem(item);
        } else {
            super.setCurrentItem(item, false);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!isScroll) {
            return false;
        }
        if (getParent() != null)
            getParent().requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!isScroll) {
            return false;
        }
        if (getParent() != null)
            getParent().requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(ev);
    }
}
