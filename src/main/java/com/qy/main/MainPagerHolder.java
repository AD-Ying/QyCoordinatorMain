package com.qy.main;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qy.R;
import com.qy.main.adapter.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainPagerHolder {

    private ViewPager pager;
    private Context context;
    private PagerAdapter adapter;
    private List<String> titles;
    private FragmentManager manager;

    public MainPagerHolder(Context context, View view, FragmentManager manager) {
        this.context = context;
        this.manager = manager;
        setUpViews(view);
        setUpViewPager();
    }

    private void setUpViews(View view) {
        pager = (ViewPager) view.findViewById(R.id.viewpager);
    }

    private void setUpViewPager() {
        titles = new ArrayList<>();
        adapter = new PagerAdapter(manager, titles);
        pager.setAdapter(adapter);
    }

    public void setAdapter(List<String> titles) {
        if (titles == null || titles.size() == 0) {
            return;
        }
        this.titles.addAll(titles);
        adapter.notifyDataSetChanged();
    }

    public ViewPager getPager() {
        return pager;
    }
}
