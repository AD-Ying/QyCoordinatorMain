package com.qy.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by yingqi on 16/4/9.
 */
public class RecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerHolder> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_FOOTER = 2;
    private View mHeaderView;
    private View mFooterView;

    public List<T> datas;

    public Context context;

    public RecyclerAdapter(Context context, List<T> datas) {
        this.datas = datas;
        this.context = context;
    }

    public void setHeaderView(View headerView) {
        if (mHeaderView != null) {
        }
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public void setFooterView(View mFooterView) {
        this.mFooterView = mFooterView;
        if (datas != null)
            notifyItemInserted(datas.size());
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            if (mHeaderView == null) return TYPE_NORMAL;
            return TYPE_HEADER;
        }
        if (mFooterView == null) return TYPE_NORMAL;
        if (position == (mHeaderView == null ? datas.size() : datas.size() + 1)) return TYPE_FOOTER;
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        int count = mHeaderView == null ? datas.size() : datas.size() + 1;
        return mFooterView == null ? count : count + 1;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new RecyclerHolder(mHeaderView);
        if (mFooterView != null && viewType == TYPE_FOOTER) return new RecyclerHolder(mFooterView);
        return getViewHolder(parent, viewType);
    }

    public RecyclerHolder getViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerHolder viewHolder, int position) {
        if (datas == null || datas.size() == 0) {
            return;
        }
        if (getItemViewType(position) == TYPE_HEADER) return;
        if (getItemViewType(position) == TYPE_FOOTER) return;
        if (viewHolder == null) return;
        if (getPosition(position) < datas.size()) {
            viewHolder.setDatas(datas.get(getPosition(position)));
            viewHolder.setDatas(datas.get(getPosition(position)), getPosition(position));
        }
    }

    //得到 list position 在data中的position
    public int getPosition(int position) {
        return mHeaderView == null ? position : position - 1;
    }

    public void notifyItem(T data, int position) {
        if (datas == null || datas.size() < position + 1) return;
        datas.set(position, data);
        //根据data中的position 刷新 list指定位置
        notifyItemChanged(mHeaderView == null ? position : position + 1);
    }

    public void removeItem(int position) {
        if (datas == null || datas.size() < position + 1) return;
        datas.remove(position);
        notifyItemRemoved(mHeaderView == null ? position : position + 1);
    }

    public View getHeaderView() {
        return mHeaderView;
    }
}
