package com.qy.widget;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.qy.R;

public class ItemView extends FrameLayout {

    private int firstLayout;
    private int secondLayout;

    private View fistView;
    private View secondView;

    private AnimatorSet mRightOutSet; // 右出动画
    private AnimatorSet mLeftInSet; // 左入动画

    private boolean mIsShowBack = false;

    private boolean isstop = false;

    private Context context;

    public ItemView(Context context) {
        super(context);
        initAttrs(context, null);
        setUpViews();
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        setUpViews();
    }

    public ItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(context, attrs);
        setUpViews();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.list_rotate_anim);
        firstLayout = array.getResourceId(R.styleable.list_rotate_anim_layout_fist, R.layout.common_layout);
        secondLayout = array.getResourceId(R.styleable.list_rotate_anim_layout_second, R.layout.common_layout);
        array.recycle();
    }

    private void setUpViews() {
        if (firstLayout > 0) {
            fistView = LayoutInflater.from(context).inflate(firstLayout, this, false);
            secondView = LayoutInflater.from(context).inflate(secondLayout, this, false);
            secondView.setVisibility(View.GONE);
            addView(secondView);
            addView(fistView);
        }
    }

    public void setFirstLayout(int firstLayout) {
        this.firstLayout = firstLayout;
    }

    public void setSecondLayout(int secondLayout) {
        this.secondLayout = secondLayout;
    }
    float startX = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float endX = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                endX = event.getX();
                if(!isstop) {
                    if (endX - startX > 100) {
                        flipCard();
                    } else if (startX - endX > 100) {
                        flipCard();
                    }
                }
                break;
        }
        return true;
    }

    // 设置动画
    private void setAnimators(Context context) {
        mRightOutSet = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.anim_out);
        mLeftInSet = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.anim_in);

        mRightOutSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                setClickable(false);
                isstop = true;
            }
        });
        mLeftInSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (mIsShowBack) {
                    fistView.setVisibility(View.GONE);
                    secondView.setVisibility(View.VISIBLE);
                } else {
                    secondView.setVisibility(View.GONE);
                    fistView.setVisibility(View.VISIBLE);
                }
                setClickable(true);
                isstop = false;
            }
        });
    }

    public void flipCard() {
        if (mRightOutSet == null) {
            setAnimators(context);
        }
        fistView.setVisibility(View.VISIBLE);
        secondView.setVisibility(View.VISIBLE);
        // 正面朝上
        if (!mIsShowBack) {
            mRightOutSet.setTarget(fistView);
            mLeftInSet.setTarget(secondView);
            mRightOutSet.start();
            mLeftInSet.start();
            mIsShowBack = true;
        } else { // 背面朝上
            mRightOutSet.setTarget(secondView);
            mLeftInSet.setTarget(fistView);
            mRightOutSet.start();
            mLeftInSet.start();
            mIsShowBack = false;
        }
    }
}
