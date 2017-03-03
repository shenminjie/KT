package com.bajie.demo.view;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by BaJie on 2015/11/26.
 */
public class RecyclerViewOnScroll extends RecyclerView.OnScrollListener {
    CustomRecyclerView mCustomRecyclerView;

    public RecyclerViewOnScroll(CustomRecyclerView customRecyclerView) {
        this.mCustomRecyclerView = customRecyclerView;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int lastVisibleItem = 0;
        int firstVisibleItem = 0;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int totalItemCount = layoutManager.getItemCount();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = ((LinearLayoutManager) layoutManager);
            lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            firstVisibleItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
        } else if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = ((GridLayoutManager) layoutManager);
            //通过LayoutManager找到当前显示的最后的item的position
            lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
            firstVisibleItem = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = ((StaggeredGridLayoutManager) layoutManager);
            //因为StaggeredGridLayoutManager的特殊性可能导致最后显示的item存在多个，所以这里取到的是一个数组
            //得到这个数组后再取到数组中position值最大的那个就是最后显示的position值了
            int[] lastPositions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
            staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
            lastVisibleItem = findMax(lastPositions);
            firstVisibleItem = staggeredGridLayoutManager.findFirstVisibleItemPositions(lastPositions)[0];
        }
        if (firstVisibleItem == 0) {
            if (!mCustomRecyclerView.isLoadMore()) {
                mCustomRecyclerView.setPullRefreshEnable(true);
            }
        } else {
            mCustomRecyclerView.setPullRefreshEnable(false);
        }


        /**
         * Either horizontal or vertical
         */
        if (!mCustomRecyclerView.isRefresh() && mCustomRecyclerView.isHasMore() && (lastVisibleItem >= totalItemCount - 1)
                && !mCustomRecyclerView.isLoadMore() && (dx > 0 || dy > 0)) {
            mCustomRecyclerView.setIsLoadMore(true);
            mCustomRecyclerView.loadMore();
        }

    }

    //找到数组中的最大值
    private int findMax(int[] lastPositions) {

        int max = lastPositions[0];
        for (int value : lastPositions) {
            //       int max    = Math.max(lastPositions,value);
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
