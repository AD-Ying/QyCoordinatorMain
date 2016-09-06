package com.qy.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yingqi on 16/9/5.
 */

public abstract class BaseViewHolder<T> {

    public Context context;

    public BaseViewHolder(Context context) {
        this.context = context;
    }

    public abstract void setData(T data);

    public abstract View getView(ViewGroup parent);
}
