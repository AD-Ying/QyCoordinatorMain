package com.qy.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qy.R;
import com.qy.main.controller.PagerFragmentController;

public class PagerFragment extends Fragment {

    private PagerFragmentController controller;
    private PagerFragmentHolder holder;
    private String title;

    public static PagerFragment getInstance(String title) {
        PagerFragment fragment = new PagerFragment();
        fragment.title = title;
        return fragment;
    }

    public String getTitle() {
        return title;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        controller = new PagerFragmentController(this);
        holder = new PagerFragmentHolder(this, view);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 处理网络请求结果 或者其他异步处理结果等
     * @param what
     * @param obj
     */
    public void handleCallBack(int what, Object... obj) {

    }
}
