package com.qy.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by yingqi on 16/9/5.
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

    private List list;
    private Context context;

    public BaseAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list == null) return 0;
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        if (list == null) return null;
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final BaseViewHolder holder;
        if (view == null) {
            holder = bindViewHolder(context);
            view = holder.getView(parent);
            view.setTag(holder);
        } else {
            holder = (BaseViewHolder) view.getTag();
        }
        holder.setData(list.get(position));
        return view;
    }

    public abstract BaseViewHolder<T> bindViewHolder(Context context);
}
