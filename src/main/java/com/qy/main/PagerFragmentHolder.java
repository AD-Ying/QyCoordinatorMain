package com.qy.main;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qy.R;
import com.qy.main.adapter.PagerListItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerFragmentHolder {

    private PagerFragment fragment;
    private RecyclerView listMain;
    private PagerListItemAdapter adapter;
    private List<String> strings;

    public PagerFragmentHolder(PagerFragment fragment, View view) {
        this.fragment = fragment;
        setUpViews(view);
        setUpRecyclerView();
        setData();
    }
    private void setUpViews(View view) {
        listMain = (RecyclerView) view.findViewById(R.id.listMain);
    }

    private void setUpRecyclerView() {
        strings = new ArrayList<>();
        adapter = new PagerListItemAdapter(fragment.getContext(), strings);
        listMain.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
        listMain.setAdapter(adapter);
    }

    public void setData() {
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        adapter.notifyDataSetChanged();
    }
}
