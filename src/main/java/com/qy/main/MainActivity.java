package com.qy.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpViews();
        setUpPagerAdapter();
        setUpToolbar();
        setUpDrawerLayout();
        setData();
    }

    private void setUpViews() {
        container = findViewById(R.id.container);
        layoutToolbar = (RelativeLayout) findViewById(R.id.layoutToolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

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

    private void setUpDrawerLayout() {
        ActionBarDrawerToggle mDrawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                if (!drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout.openDrawer(GravityCompat.END);
                }
                break;
        }
        return false;
    }
}
