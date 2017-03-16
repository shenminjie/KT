package com.newer.kt.ui.video_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newer.kt.R;
import com.newer.kt.Refactor.KTApplication;
import com.newer.kt.engine.GlideHelper;
import com.newer.kt.entity.OnItemListener;
import com.newer.kt.entity.VideoInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class VideoListActivity extends AppCompatActivity {

    @Bind(R.id.image_back)
    ImageView mImageBack;
    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list2);
        ButterKnife.bind(this);
    }

    public static void toAcitivty(Context context, List<VideoInfo> infos) {
    }


    /**
     * 适配器
     */
    static class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<VideoInfo> datas;


        private OnItemListener<VideoInfo> mListener;

        public ListAdapter(List<VideoInfo> datas, OnItemListener<VideoInfo> mListener) {
            this.datas = datas;
            this.mListener = mListener;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_videos_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ViewHolder viewHolder= (ViewHolder) holder;
//            GlideHelper.display(KTApplication.getContext(),viewHolder.iv,datas.get(position).get);
        }

        @Override
        public int getItemCount() {
            return datas != null ? datas.size() : 0;
        }


        static class ViewHolder extends RecyclerView.ViewHolder{
            @Bind(R.id.iv)
            ImageView iv;
            @Bind(R.id.iv_vs_saishi_back)
            ImageView ivVsSaishiBack;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
