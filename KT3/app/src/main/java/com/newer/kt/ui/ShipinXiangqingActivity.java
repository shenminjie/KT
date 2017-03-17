package com.newer.kt.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.frame.app.utils.LogUtils;
import com.newer.kt.R;
import com.newer.kt.entity.StudySkillInfo;
import com.newer.kt.fragment.peixun.JiNeng_Liebiao;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShipinXiangqingActivity extends AppCompatActivity {

    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_name)
    TextView tvName;

    private StudySkillInfo mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipin_xiangqing);
        ButterKnife.bind(this);
        mData = (StudySkillInfo) getIntent().getSerializableExtra("data");

        if (mData != null) {
            tvTitle.setText(mData.getName() + "视频详情");
            tvName.setText(mData.getName() + "真人视频");
            LogUtils.e("");
        }
    }

    @OnClick(R.id.image_back)
    public void onFinish() {
        finish();
    }

    @OnClick(R.id.content_shipin_xiangqing)
    public void toYoukuVideo() {
        if (mData != null && mData.getVideos() != null && mData.getVideos().size() != 0) {
            Intent intent = new Intent(this, com.newer.kt.Refactor.ui.Avtivity.Settings.PlayerActivity.class);
            intent.putExtra("vid", getId());
            startActivity(intent);
        }
    }

    /**
     * 跳转
     *
     * @param context
     * @param mStudySkillInfo
     */
    public static void toActivity(Context context, StudySkillInfo mStudySkillInfo) {
        Intent intent = new Intent(context, ShipinXiangqingActivity.class);
        intent.putExtra("data", mStudySkillInfo);
        context.startActivity(intent);
    }

    public String getId() {
        String videoUrl = mData.getYouku_videos().get(0);
        int start = "http://player.youku.com/embed/".length();
        int end = videoUrl.indexOf("==");
        return videoUrl.substring(start,end);
    }
}
