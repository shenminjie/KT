package com.newer.kt.ui.pingce.input_result;

import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.newer.kt.R;
import com.newer.kt.Refactor.KTApplication;
import com.smj.gradlebean.Users;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenminjie on 17/3/18.
 */

public class InputResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Users> mDatas;

    public InputResultAdapter(List<Users> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_student_input_result_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvStudentName.setText(mDatas.get(position).getNickname());
        int genderIconResource = R.mipmap.nan;
        if (mDatas.get(position).getGender().equals("MM")) {
            genderIconResource = R.mipmap.nv;
        }
        Drawable drawable = ResourcesCompat.getDrawable(KTApplication.getContext().getResources(), genderIconResource, null);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        viewHolder.tvStudentName.setCompoundDrawables(null, null, drawable, null);
        viewHolder.edResult.setText(mDatas.get(position).getScord() );
        viewHolder.edResult.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mDatas.get(position).setScord(charSequence + "");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_student_name)
        TextView tvStudentName;
        @Bind(R.id.ed_result)
        EditText edResult;
        @Bind(R.id.tv_pingji)
        TextView tvPingji;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
