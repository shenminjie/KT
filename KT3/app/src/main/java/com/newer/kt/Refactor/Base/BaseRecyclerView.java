package com.newer.kt.Refactor.Base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.frame.app.base.Adapter.BaseRecyclerViewAdapter;
import com.frame.app.base.activity.BaseToolBarActivity2;
import com.newer.kt.R;
import com.newer.kt.Refactor.utils.CommonUtil;

/**
 * Created by jy on 16/6/14.
 */
public abstract class BaseRecyclerView extends BaseToolBarActivity2 {

    public RecyclerView mRecyclerView;
    public SwipeRefreshLayout refresh;

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        addContentView(R.layout.layout_recyclerview_nopull);
        mRecyclerView = (RecyclerView) findViewById(R.id.layout_recyclerview_rv);
        refresh = (SwipeRefreshLayout) findViewById(R.id.layout_recyclerview_sr);
        refresh.setProgressViewOffset(false, 0, CommonUtil.dip2px(getThis(), 24));
    }

    protected void setLayoutManager(RecyclerView.LayoutManager layout){
        mRecyclerView.setLayoutManager(layout);
    }
    /**
     * 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
     */
    protected void setHasFixedSize(boolean isFixed) {
        mRecyclerView.setHasFixedSize(isFixed);
    }

    protected void addItemDecoration(RecyclerView.ItemDecoration mItemDecoration) {
        mRecyclerView.addItemDecoration(mItemDecoration);
    }

    protected void setItemAnimator(RecyclerView.ItemAnimator mItemAnimator){
        mRecyclerView.setItemAnimator(mItemAnimator);
    }

    protected void setAdapter(BaseRecyclerViewAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    protected void setAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    protected RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
    protected SwipeRefreshLayout getSwipeRefreshLayout() {
        return refresh;
    }

    protected void setRefreshing(boolean isRefreshing){
        refresh.setRefreshing(isRefreshing);
    }
    protected void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener){
        refresh.setOnRefreshListener(listener);
    }

    protected void addOnScrollListener(RecyclerView.OnScrollListener listener){
        mRecyclerView.addOnScrollListener(listener);
    }
}
