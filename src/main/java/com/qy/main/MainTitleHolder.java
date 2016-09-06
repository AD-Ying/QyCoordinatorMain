package com.qy.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qy.R;

import java.util.Arrays;
import java.util.List;

public class MainTitleHolder {

    private TabLayout tabLayout;
    private Context context;

    public MainTitleHolder(Context context, View view) {
        this.context = context;
        setUpViews(view);
    }

    private void setUpViews(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
    }

    public void setUpTabLayout() {
        if (tabLayout.getTabCount() > 4) {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

    public List<String> getTitleList() {
        return Arrays.asList(getTitles().split(","));
    }

    public void setupWithViewPager(ViewPager pager) {
        tabLayout.setupWithViewPager(pager);
    }

    private String getTitles() {
        SharedPreferences preferences = context.getSharedPreferences("title", Context.MODE_PRIVATE);
        //是否用户相关,如果用户相关,key添加用户ID
        return preferences.getString("title" + "", context.getResources().getString(R.string.titles));
    }
}
