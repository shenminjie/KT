package com.bajie.demo.view;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by BaJie on 2015/11/27.
 */
public class SwipeRefresh implements SwipeRefreshLayout.OnRefreshListener {
        CustomRecyclerView mCustomRecyclerView;

    public SwipeRefresh(CustomRecyclerView customRecyclerView) {
            this.mCustomRecyclerView = customRecyclerView;
    }

    @Override
    public void onRefresh() {
            if (!mCustomRecyclerView.isRefresh()) {
                mCustomRecyclerView.setIsRefresh(true);
                mCustomRecyclerView.refresh();
            }
    }
}