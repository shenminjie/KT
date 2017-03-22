package com.newer.kt.ui.upload;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alipay.share.sdk.openapi.channel.APMessage;
import com.newer.kt.R;
import com.newer.kt.entity.OnItemListener;
import com.smj.LocalDataInfo;
import com.smj.PingceLocalData;
import com.smj.upload.UpLoadInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenminjie on 17/3/19.
 */

public class UpLoadAdapter<T extends LocalDataInfo> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<T> mDatas;

    private OnItemListener<T> mListener;

    public UpLoadAdapter(List<T> mDatas, Callback mCallBack, OnItemListener<T> mListener) {
        this.mDatas = mDatas;
        this.mCallBack = mCallBack;
        this.mListener = mListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.size() == 0) {
            return 1;
        }
        return 2;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_upload_layout, parent, false);
            RecyclerView.ViewHolder viewHolder = new ViewHolder(view);
            viewHolder.setIsRecyclable(false);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_empty_upload, parent, false);
            return new RecyclerView.ViewHolder(view) {
            };
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.tvName.setText(mDatas.get(position).getUpLoadName());
            String name = null;
            if (mDatas.get(position).getType() == LocalDataInfo.TYPE_DAKEJIAN) {
                name = "大课间";
            } else if (mDatas.get(position).getType() == LocalDataInfo.TYPE_PINGCE) {
                name = "评测";
            } else {
                name = "赛事";
            }
            viewHolder.tvType.setText(name);
            mCallBack.bindViewHolder(viewHolder, position);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemListener(mDatas.get(position), position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mDatas.size() == 0) {
            return 1;
        }
        return mDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_type)
        TextView tvType;
        @Bind(R.id.progreebar)
        ProgressBar progreebar;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private Callback mCallBack;


    /**
     * callback
     */
    public interface Callback {
        void bindViewHolder(ViewHolder viewHolder, int position);
    }
}
