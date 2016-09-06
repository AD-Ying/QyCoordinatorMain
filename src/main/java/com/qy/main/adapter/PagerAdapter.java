package com.qy.main.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.qy.main.PagerFragment;

import java.util.List;

/**
 * Created by yingqi on 16/9/5.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    private List<String> titles;

    public PagerAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public PagerFragment getItem(int position) {
        if (titles == null || titles.size() == 0) return null;
        return PagerFragment.getInstance(titles.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles == null || titles.size() == 0) return "";
        return titles.get(position);
    }

    @Override
    public int getCount() {
        if (titles == null || titles.size() == 0) return 0;
        return titles.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }


    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
