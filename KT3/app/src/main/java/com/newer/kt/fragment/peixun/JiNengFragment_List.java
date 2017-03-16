package com.newer.kt.fragment.peixun;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.frame.app.utils.LogUtils;
import com.newer.kt.R;
import com.newer.kt.entity.OnItemListener;
import com.newer.kt.entity.jineng.JiNeng_Bean;
import com.newer.kt.entity.jineng.SkillResponse;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JiNengFragment_List extends AppCompatActivity implements OnItemListener<JiNeng_Bean> {

    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    /**
     * 数据
     */
    private SkillResponse mSkillResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_neng_fragment__list);
        ButterKnife.bind(this);
        mSkillResponse = (SkillResponse) getIntent().getSerializableExtra("data");

        if (mSkillResponse != null) {
            tvTitle.setText(mSkillResponse.getCategory());
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(new ListAdapter(mSkillResponse.getList(), this));
        }
    }

    /**
     * 跳转
     *
     * @param context
     * @param response
     */
    public static void toAcitivty(Context context, SkillResponse response) {
        Intent intent = new Intent(context, JiNengFragment_List.class);
        intent.putExtra("data", response);
        context.startActivity(intent);
    }

    @OnClick(R.id.image_back)
    public void back() {
        finish();
    }

    @Override
    public void onItemListener(JiNeng_Bean jiNeng_bean, int position) {
        LogUtils.e(jiNeng_bean + "");
    }

    /**
     * 适配器
     */
    static class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<JiNeng_Bean> datas;


        private OnItemListener<JiNeng_Bean> mListener;

        public ListAdapter(List<JiNeng_Bean> datas, OnItemListener<JiNeng_Bean> mListener) {
            this.datas = datas;
            this.mListener = mListener;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jineng_list, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            final JiNeng_Bean data = datas.get(position);
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.tvVsSaishiTitle.setText(data.getName());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onItemListener(data, position);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return datas != null ? datas.size() : 0;
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            @Bind(R.id.tv_vs_saishi_title)
            TextView tvVsSaishiTitle;
            @Bind(R.id.iv_vs_saishi_back)
            ImageView ivVsSaishiBack;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }
}
