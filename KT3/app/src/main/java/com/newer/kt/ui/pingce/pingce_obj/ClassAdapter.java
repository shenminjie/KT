package com.newer.kt.ui.pingce.pingce_obj;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.newer.kt.R;
import com.newer.kt.entity.OnItemListener;
import com.smj.gradlebean.Classes;
import com.smj.gradlebean.Users;

import java.util.List;

/**
 * Created by chenminjie on 17/3/18.
 */

public class ClassAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Classes> mClassDatas;

    OnItemListener<Classes> mOnItemListener;

    OnCheckListener mOnCheckListener;

    public ClassAdapter(List<Classes> mClassDatas, OnItemListener<Classes> mOnItemListener, OnCheckListener mOnCheckListener) {
        this.mClassDatas = mClassDatas;
        this.mOnItemListener = mOnItemListener;
        this.mOnCheckListener = mOnCheckListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_layout, parent, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ClassViewHolder viewHolder = (ClassViewHolder) holder;
        viewHolder.checkBox.setOnCheckedChangeListener(null);
        viewHolder.setData(mClassDatas.get(position), mOnCheckListener);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemListener != null)
                    mOnItemListener.onItemListener(mClassDatas.get(position), position);
            }
        });
        viewHolder.btnExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemListener != null)
                    mOnItemListener.onItemListener(mClassDatas.get(position), position);
            }
        });
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mOnCheckListener != null)
                    mOnCheckListener.onCheckListener(b, mClassDatas.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mClassDatas.size();
    }

    /**
     * cb
     */
    public interface OnCheckListener {

        /**
         * cb
         *  @param isCheck
         * @param classes
         * @param position
         */
        void onCheckListener(boolean isCheck, Classes classes, int position);

        /**
         * check
         *  @param isCheck
         * @param users
         * @param position
         */
        void onStudentCheck(boolean isCheck, Users users, RecyclerView.Adapter adapter, int position);
    }
}
