package com.newer.kt.ui.pingce.select_skill;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.newer.kt.R;
import com.newer.kt.entity.OnItemListener;
import com.newer.kt.utils.ViewUtil;
import com.smj.skillbean.SkillInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenminjie on 17/3/18.
 */

public class SelectSkillAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<SkillInfo> mDatas;

    OnItemListener<SkillInfo> mOnItemListener;


    public SelectSkillAdapter(List<SkillInfo> mClassDatas, OnItemListener<SkillInfo> mOnItemListener) {
        this.mDatas = mClassDatas;
        this.mOnItemListener = mOnItemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_skill, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) viewHolder.itemView.getLayoutParams();
        layoutParams.height = (int) (measureView(viewHolder.itemView)[0] / 3.6);
        viewHolder.itemView.setLayoutParams(layoutParams);

        viewHolder.tvName.setText(mDatas.get(position).getName());

        //选择与否
        if (mDatas.get(position).isChecked()) {
            ViewUtil.setViewVisible(viewHolder.tvSelect, true);
            ViewUtil.setViewVisible(viewHolder.zhezhaoceng, false);
        } else {
            ViewUtil.setViewVisible(viewHolder.tvSelect, false);
            ViewUtil.setViewVisible(viewHolder.zhezhaoceng, true);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemListener != null)
                    mOnItemListener.onItemListener(mDatas.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.zhezhaoceng)
        FrameLayout zhezhaoceng;
        @Bind(R.id.tv_select)
        ImageView tvSelect;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 测量视图尺寸
     *
     * @param view 视图
     * @return arr[0]: 视图宽度, arr[1]: 视图高度
     */
    public int[] measureView(View view) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }
        int widthSpec = ViewGroup.getChildMeasureSpec(0, 0, lp.width);
        int lpHeight = lp.height;
        int heightSpec;
        if (lpHeight > 0) {
            heightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight, View.MeasureSpec.EXACTLY);
        } else {
            heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }
        view.measure(widthSpec, heightSpec);
        return new int[]{view.getMeasuredWidth(), view.getMeasuredHeight()};
    }
}
