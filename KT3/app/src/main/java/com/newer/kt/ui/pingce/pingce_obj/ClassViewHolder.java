package com.newer.kt.ui.pingce.pingce_obj;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.newer.kt.R;

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

    @Bind(R.id.btn_expand)
    ImageButton btnExpand;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

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

    @OnClick(R.id.btn_expand)
    public void onClick() {
        RecyclerView.LayoutParams param = (RecyclerView.LayoutParams)itemView.getLayoutParams();
        if (recyclerView.getVisibility() == View.VISIBLE) {
            recyclerView.setVisibility(View.GONE);
            param.height = 0;
            param.width = 0;
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            param.height = RecyclerView.LayoutParams.WRAP_CONTENT;
            param.width = RecyclerView.LayoutParams.MATCH_PARENT;
        }
        itemView.setLayoutParams(param);

    }


}
