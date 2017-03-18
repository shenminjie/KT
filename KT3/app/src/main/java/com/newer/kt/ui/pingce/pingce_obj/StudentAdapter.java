package com.newer.kt.ui.pingce.pingce_obj;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.newer.kt.R;
import com.newer.kt.entity.Student;
import com.smj.gradlebean.Users;

import java.util.List;


/**
 * Created by chenminjie on 17/3/18.
 */

public class StudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Users> users;
    ClassAdapter.OnCheckListener mOnCheckListener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_layout, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        StudentViewHolder viewHolder = (StudentViewHolder) holder;
        viewHolder.cb.setOnCheckedChangeListener(null);
        viewHolder.setData(users.get(position));
        viewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mOnCheckListener.onStudentCheck(b, users.get(position), StudentAdapter.this,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setDatas(List<Users> users) {
        this.users = users;
    }

    public void setListener(ClassAdapter.OnCheckListener mOnCheckListener) {
        this.mOnCheckListener = mOnCheckListener;
    }
}
