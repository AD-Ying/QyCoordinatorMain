package com.qy.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.qy.R;

import java.util.List;


public class PagePointUtil implements OnPageChangeListener {
    private View view;
    private ViewPager pager;
    private RadioGroup radioGroup;
    private List<View> views;
    private int playTime = 1000 * 4;
    private Context context;
    private boolean autoPlay = false;
    private PagerBannerAdapter adapter;
    private Runnable runnable;
    private boolean isPlay = true;

    public PagePointUtil(Context context, View view, List<View> views) {
        this.context = context;
        this.view = view;
        this.views = views;
        pager = (ViewPager) view.findViewById(R.id.banner);
        radioGroup = (RadioGroup) view.findViewById(R.id.point);
        init();
    }

    public View getView() {
        return view;
    }

    public void setLayoutParams(RelativeLayout.LayoutParams params) {
        pager.setLayoutParams(params);
    }

    public void setAutoPlay(boolean autoPlay) {
        this.autoPlay = autoPlay;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public void startAutoPlay() {
        if (context == null)
            return;
        if(views == null || views.size() <= 1) {
            return;
        }
        if(!autoPlay) {
            return;
        }
        runnable = new Runnable() {
            @Override
            public void run() {
                if (handler == null) return;
                if (!isPlay) return;
                handler.postDelayed(this, playTime);
                nextPage();
            }
        };
        handler.postDelayed(runnable, playTime);
    }

    private Handler handler = new Handler();

    private void nextPage() {
        if (views == null) return;
        int count = views.size();
        if (count > 2) {
            int index = pager.getCurrentItem();
            index = index % (count - 2) + 1;
            pager.setCurrentItem(index, true);
        }
    }

    public void notifyDataSetChanged() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            addPointsAndListener();
        }
    }

    public void init() {
        if (pager != null && views != null) {
            adapter = new PagerBannerAdapter(this.views);
            pager.setAdapter(adapter);
        }
        addPointsAndListener();
    }


    private void addPointsAndListener() {
        if (views == null || views.size() <= 1) {
            return;
        }

        radioGroup.removeAllViews();
        for (int i = 0; i < views.size() - 2; i++) {
            RadioButton radio = new RadioButton(context);
            radio.setButtonDrawable(R.drawable.radio_default);
            radio.setPadding(10, 0, 10, 0);
            radioGroup.addView(radio);
        }

        pager.addOnPageChangeListener(this);
        pager.setCurrentItem(1);
    }

    private void updatePoints(int position) {
        if (radioGroup.getChildCount() >= 0 && position < radioGroup.getChildCount()) {
            ((RadioButton) radioGroup.getChildAt(position)).setChecked(true);
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(int position) {
        if (views == null || views.size() <= 1) {
            return;
        }
        if ( position < 1) {
            position = views.size() - 2;
            pager.setCurrentItem(position, false);
        } else if ( position > views.size() - 2) {
            position = 1;
            pager.setCurrentItem(position, false);
        }
        updatePoints(position - 1);
    }

    public int getCurrentIndex() {
        return pager.getCurrentItem();
    }

    public void stopPlay() {
        isPlay = false;
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }

    public void startPlay() {
        isPlay = true;
        if (handler != null && runnable != null) {
            handler.postDelayed(runnable, playTime);
        }
    }
}
