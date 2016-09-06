package com.qy.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.qy.R;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private MainTitleHolder titleHolder;
    private MainPagerHolder pagerHolder;
    private MainBannerHolder bannerHolder;

    private View container;
    private RelativeLayout layoutToolbar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
        setUpPagerAdapter();
        setUpToolbar();
        setData();
    }

    private void setUpViews() {
        container = findViewById(R.id.container);
        layoutToolbar = (RelativeLayout) findViewById(R.id.layoutToolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        titleHolder = new MainTitleHolder(this, container);
        pagerHolder = new MainPagerHolder(this, container, this.getSupportFragmentManager());
        bannerHolder = new MainBannerHolder(this, container);
    }

    private void setUpPagerAdapter() {
        pagerHolder.setAdapter(titleHolder.getTitleList());
        titleHolder.setupWithViewPager(pagerHolder.getPager());
        titleHolder.setUpTabLayout();
    }

    private void setUpToolbar() {
        int apiVersion = android.os.Build.VERSION.SDK_INT;
        if(apiVersion >= 21) {
            layoutToolbar.setPadding(layoutToolbar.getPaddingLeft(),
                    layoutToolbar.getPaddingTop() + getStatusBarHeight(),
                    layoutToolbar.getPaddingRight(),
                    layoutToolbar.getPaddingBottom());
        }
        toolbar.setTitle("首页");
        toolbar.setSubtitleTextColor(this.getResources().getColor(R.color.gray));
        toolbar.setSubtitle("亓颖测试专用");
        setSupportActionBar(toolbar);
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void setData() {
        //设置轮播图
        String[] photo = {"", ""};
        bannerHolder.setData(Arrays.asList(photo));
    }
}
