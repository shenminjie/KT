package com.bajie.demo.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.bajie.demo.R;

/**
 * Created by BaJie on 2015/11/26.
 */
public class CustomRecyclerView extends LinearLayout {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayout mFootView;

    private PullListener mPullListener;
    private boolean hasMore = true;
    private boolean isRefresh = false;
    private boolean isLoadMore = false;

    public CustomRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public CustomRecyclerView(Context context) {
        super(context);
    }

    private void initView(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_recyclerview, null);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        mFootView = (LinearLayout)view.findViewById(R.id.loading_layout);
        mFootView.setVisibility(View.GONE);

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_dark, android.R.color.holo_blue_dark, android.R.color.holo_orange_dark);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefresh(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new RecyclerViewOnScroll(this));
        mRecyclerView.setOnTouchListener(
                new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (isRefresh) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
        );
        this.addView(view);
    }

    /**
     * LinearLayoutManager
     */
    public void setLinearLayout() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    /**
     * GridLayoutManager
     */

    public void setGridLayout(int spanCount) {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, spanCount);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }


    /**
     * StaggeredGridLayoutManager
     */

    public void setStaggeredGridLayout(int spanCount) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(spanCount, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
    }


    public void setPullRefreshEnable(boolean enable) {
        mSwipeRefreshLayout.setEnabled(enable);
    }

    public boolean getPullRefreshEnable() {
        return mSwipeRefreshLayout.isEnabled();
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return mRecyclerView.getLayoutManager();
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }


    public void loadMore() {
        if (mPullListener != null && hasMore) {
            mFootView.setVisibility(View.VISIBLE);
            mPullListener.onLoadMore();

        }
    }


    public void setPullLoadMoreCompleted() {
        isRefresh = false;
        mSwipeRefreshLayout.setRefreshing(false);

        isLoadMore = false;
        mFootView.setVisibility(View.GONE);

    }

    public void setRefreshing(final boolean isRefreshing) {
        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(isRefreshing);
            }
        });

    }


    public void setOnPullLoadMoreListener(PullListener listener) {
        mPullListener = listener;
    }

    public void refresh() {
        mRecyclerView.setVisibility(View.VISIBLE);
        if (mPullListener != null) {
            mPullListener.onRefresh();
        }
    }

    public void scrollToTop() {
        mRecyclerView.scrollToPosition(0);
    }


    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter != null) {
            mRecyclerView.setAdapter(adapter);
        }
    }

    public boolean isLoadMore() {
        return isLoadMore;
    }

    public void setIsLoadMore(boolean isLoadMore) {
        this.isLoadMore = isLoadMore;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setIsRefresh(boolean isRefresh) {
        this.isRefresh = isRefresh;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public interface PullListener {
        public void onRefresh();

        public void onLoadMore();
    }
}
