package com.newer.kt.ui.pingce.pingce_obj;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.newer.kt.R;
import com.newer.kt.Refactor.KTApplication;
import com.smj.gradlebean.Classes;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenminjie on 17/3/18.
 */

public class ClassViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.tv_class_name)
    TextView tvClassName;

    @Bind(R.id.tv_student_num)
    TextView tvStudentCount;

    @Bind(R.id.cb)
    CheckBox checkBox;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.btn_expand2)
    ImageButton btnExpand;

    private StudentAdapter mAdapter;

    public ClassViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mAdapter = new StudentAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }


    /**
     * setData
     *
     * @param classes
     * @param mOnCheckListener
     */
    public void setData(Classes classes, ClassAdapter.OnCheckListener mOnCheckListener) {
        tvClassName.setText(classes.getClassName());
        tvStudentCount.setText(classes.getUsers().size() + "学生");
        LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) recyclerView.getLayoutParams();
        if (classes.isExpand()) {
            recyclerView.setVisibility(View.VISIBLE);
            param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            param.width = LinearLayout.LayoutParams.MATCH_PARENT;
            btnExpand.setImageDrawable(ResourcesCompat.getDrawable(KTApplication.getContext().getResources(), R.drawable.arrow_up, null));
        } else {
            recyclerView.setVisibility(View.GONE);
            param.height = 0;
            param.width = 0;
            btnExpand.setImageDrawable(ResourcesCompat.getDrawable(KTApplication.getContext().getResources(), R.drawable.arrow_down, null));
        }
        mAdapter.setDatas(classes.getUsers());
        mAdapter.setListener(mOnCheckListener);
        recyclerView.setLayoutParams(param);

        if (classes.isChecked()) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
    }
}
