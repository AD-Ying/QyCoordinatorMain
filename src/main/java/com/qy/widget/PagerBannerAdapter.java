package com.qy.widget;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class PagerBannerAdapter extends PagerAdapter {

    public List<View> mListViews;

    public PagerBannerAdapter(List<View> tvs) {
        this.mListViews = tvs;
    }
    @Override
    public int getCount() {
        return mListViews.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mListViews.get(position));
        return mListViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mListViews.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
