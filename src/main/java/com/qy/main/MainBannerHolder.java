package com.qy.main;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qy.R;
import com.qy.widget.PagePointUtil;

import java.util.ArrayList;
import java.util.List;

public class MainBannerHolder implements View.OnClickListener{

    private PagePointUtil pagePointUtil;
    private long start = 0;
    private Context context;
    private View view;
    private View.OnClickListener listener;

    public MainBannerHolder(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    public void setOnclickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    /**
     * 设置轮播图
     * @param bannerModels
     */
    public void setData(List<String> bannerModels) {
        if (start == 0 || System.currentTimeMillis() - start > 1000 * 60 * 30) {
            start = System.currentTimeMillis();
            if (pagePointUtil != null) {
                pagePointUtil.stopPlay();
                pagePointUtil = null;
            }
            List<View> views = new ArrayList<>();
            if (bannerModels.size() == 1) {
                View view = LayoutInflater.from(context).inflate(R.layout.common_page_item, null);
                SimpleDraweeView ivBanner = (SimpleDraweeView) view.findViewById(R.id.ivPager);
                ivBanner.setImageURI(Uri.parse(bannerModels.get(0)));
                ivBanner.setTag(bannerModels.get(0));
                ivBanner.setOnClickListener(this);
                views.add(view);
            } else {
                for (int i = 0; i < bannerModels.size() + 2; i++) {
                    View view = LayoutInflater.from(context).inflate(R.layout.common_page_item, null);
                    SimpleDraweeView ivBanner = (SimpleDraweeView) view.findViewById(R.id.ivPager);
                    if (i != 0 && i != bannerModels.size() + 1) {
                        ivBanner.setImageURI(Uri.parse(bannerModels.get(i - 1)));
                        ivBanner.setTag(bannerModels.get(i - 1));
                        ivBanner.setOnClickListener(this);
                    }
                    views.add(view);
                }
            }

            pagePointUtil = new PagePointUtil(context, view, views);
            pagePointUtil.setAutoPlay(true);
            pagePointUtil.startAutoPlay();
        }
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }
}
