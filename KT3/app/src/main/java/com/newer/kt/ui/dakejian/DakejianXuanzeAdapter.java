package com.newer.kt.ui.dakejian;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.newer.kt.R;
import com.newer.kt.Refactor.KTApplication;
import com.smj.gradlebean.Classes;
import com.smj.gradlebean.GradeInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenminjie on 17/3/19.
 */

public class DakejianXuanzeAdapter extends BaseExpandableListAdapter {

    private List<GradeInfo> mDatas;

    public DakejianXuanzeAdapter(List<GradeInfo> mDatas, Callback mCallBack) {
        this.mDatas = mDatas;
        this.mCallBack = mCallBack;
    }

    @Override
    public int getGroupCount() {
        return mDatas.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mDatas.get(i).getClasses().size();
    }

    @Override
    public Object getGroup(int i) {
        return mDatas.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mDatas.get(i).getClasses().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_select_record_class, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        GradeInfo gradeInfo = mDatas.get(i);
        viewHolder.tvClzname.setText(KTApplication.getNianJiName(gradeInfo.getGrade()));
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ItemViewHolder itemViewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_select_record_class_expand, viewGroup, false);
            itemViewHolder = new ItemViewHolder(view);
            view.setTag(itemViewHolder);
        } else {
            itemViewHolder = (ItemViewHolder) view.getTag();
        }
        final Classes classes = mDatas.get(i).getClasses().get(i1);
        itemViewHolder.tvClz.setText(classes.getCls() + "班");
        itemViewHolder.tvStudentCount.setText(classes.getUsers().size() + "学生");
        itemViewHolder.cb.setOnCheckedChangeListener(null);
        itemViewHolder.cb.setChecked(classes.isChecked());
        itemViewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mCallBack.onCheckListener(classes, b);
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


    static class ViewHolder {
        @Bind(R.id.tv_clzname)
        TextView tvClzname;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ItemViewHolder {
        @Bind(R.id.tv_clz)
        TextView tvClz;
        @Bind(R.id.tv_student_count)
        TextView tvStudentCount;
        @Bind(R.id.cb)
        CheckBox cb;

        ItemViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private Callback mCallBack;

    public interface Callback {
        void onCheckListener(Classes classes, boolean isCheck);
    }
}
