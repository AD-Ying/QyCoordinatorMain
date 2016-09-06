package com.qy.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qy.R;
import com.qy.base.RecyclerAdapter;
import com.qy.base.RecyclerHolder;
import com.qy.main.PagerListItemHolder;

import java.util.List;

/**
 * Created by yingqi on 16/9/5.
 */

public class PagerListItemAdapter extends RecyclerAdapter<String> {

    public PagerListItemAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public RecyclerHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main_list, parent, false);
        return new PagerListItemHolder(context, view);
    }
}
