package com.newer.kt.ui.pingce.pingce_obj;

import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.newer.kt.R;
import com.newer.kt.Refactor.KTApplication;
import com.smj.gradlebean.Users;

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

    public void setData(Users users) {
        tvStudentName.setText(users.getNickname());
        int genderIconResource = R.mipmap.nan;
        if (users.getGender().equals("MM")) {
            genderIconResource = R.mipmap.nv;
        }
        Drawable drawable = ResourcesCompat.getDrawable(KTApplication.getContext().getResources(), genderIconResource, null);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvStudentName.setCompoundDrawables(null, null, drawable, null);
        cb.setChecked(users.isChecked());
    }
}
