package com.newer.kt.ui.video_list;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newer.kt.R;
import com.newer.kt.Refactor.ui.Avtivity.PlayerActivity;
import com.newer.kt.entity.OnItemListener;
import com.smj.LocalDataInfo;
import com.smj.LocalDataManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.vov.vitamio.utils.Log;

public class VideoListActivity extends AppCompatActivity implements OnItemListener<LocalDataInfo> {

    @Bind(R.id.image_back)
    ImageView mImageBack;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<LocalDataInfo> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list2);
        ButterKnife.bind(this);
        mDatas = LocalDataManager.getCacheDatas();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new ListAdapter(mDatas, this));

        mTvTitle.setText("已上传视频");
    }

    @OnClick(R.id.image_back)
    public void onBack() {
        finish();
    }

    public static void toAcitivty(Context context) {
        Intent intent = new Intent(context, VideoListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onItemListener(LocalDataInfo localDataInfo, int position) {
        Log.e("tag", "localInfo:" + localDataInfo);
        if (!TextUtils.isEmpty(localDataInfo.getVideoPath())) {
            //local load video
            final Intent videoIntent = new Intent(Intent.ACTION_VIEW);
            videoIntent.setDataAndType(Uri.parse(localDataInfo.getVideoPath()), "video/*");
            try {
                startActivity(videoIntent);
            } catch (ActivityNotFoundException e) {
                // NOP
            }
        }
    }


    /**
     * 适配器
     */
    static class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        private List<LocalDataInfo> datas;


        private OnItemListener<LocalDataInfo> mListener;

        public ListAdapter(List<LocalDataInfo> datas, OnItemListener<LocalDataInfo> mListener) {
            this.datas = datas;
            this.mListener = mListener;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 1) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_upload, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_videos_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public int getItemViewType(int position) {
            if (datas.size() == 0) {
                return 1;
            }
            return 2;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof ViewHolder) {
                ViewHolder viewHolder = (ViewHolder) holder;
                viewHolder.tvName.setText(datas.get(position).getUpLoadName() + getTime(datas.get(position).getCreatetime()));
                viewHolder.tvType.setText(datas.get(position).getType() == LocalDataInfo.TYPE_DAKEJIAN ? "大课间" : "评测");
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onItemListener(datas.get(position), position);
                    }
                });

            }
        }

        /**
         * get date
         *
         * @param createTime
         * @return
         */
        private String getTime(long createTime) {
            return new SimpleDateFormat("MMdd_HHmm").format(new Date(createTime));
        }

        @Override
        public int getItemCount() {
            if (datas.size() == 0) {
                return 1;
            }
            return datas.size();
        }


        static class ViewHolder extends RecyclerView.ViewHolder {
            @Bind(R.id.tv_name)
            TextView tvName;
            @Bind(R.id.tv_type)
            TextView tvType;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
