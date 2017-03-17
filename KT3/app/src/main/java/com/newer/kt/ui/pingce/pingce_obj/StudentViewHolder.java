package com.newer.kt.ui.pingce.pingce_obj;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.newer.kt.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenminjie on 17/3/18.
 */

public class StudentViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.tv_student_name)
    TextView tvStudentName;
    @Bind(R.id.cb)
    CheckBox cb;

    public StudentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
