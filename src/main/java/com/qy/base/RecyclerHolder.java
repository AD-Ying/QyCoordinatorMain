package com.qy.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yingqi on 16/4/10.
 */
public class RecyclerHolder<T> extends RecyclerView.ViewHolder {

    public Context context;

    public RecyclerHolder(View itemView) {
        super(itemView);
    }

    public RecyclerHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
    }

    public void setDatas(@NonNull T data) {
    }

    public void setDatas(@NonNull T data, int position) {
    }
}
