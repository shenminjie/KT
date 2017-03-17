package com.newer.kt.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newer.kt.R;
import com.newer.kt.Refactor.Entitiy.BigClassRoom;
import com.newer.kt.Refactor.ui.Avtivity.BigClassDetailActivity;
import com.newer.kt.entity.OnItemListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by leo on 16/11/9.
 */

public class BigClassAdater extends RecyclerView.Adapter<BigClassAdater.ViewHodler> {
    private Context mContext;
    private List<BigClassRoom> mList = new ArrayList<BigClassRoom>();

    private OnItemListener<BigClassRoom> mItemListener;

    public void setListener(OnItemListener<BigClassRoom> mItemListener) {
        this.mItemListener = mItemListener;
    }

    public BigClassAdater(Context context, List<BigClassRoom> mList) {
        this.mList = mList;
        this.mContext = context;
    }

    @Override
    public ViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHodler(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bigclass, parent, false));
    }


    @Override
    public void onBindViewHolder(final ViewHodler holder, final int position) {
        holder.mTitle.setText(mList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemListener != null) {
                    mItemListener.onItemListener(mList.get(position), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHodler extends RecyclerView.ViewHolder {
        @Bind(R.id.image_bg)
        ImageView mBg;
        @Bind(R.id.tv_title)
        TextView mTitle;


        public ViewHodler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }
}
