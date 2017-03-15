package com.newer.kt;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bajieaichirou on 17/3/7.
 */
public class ActivityScheduleDetail extends NEVideoPlayerActivity {

    @Bind(R.id.detail_title)
    TextView mTitleTxt;

    @Bind(R.id.detail_title_classfy)
    TextView mClassfyTxt;

    @Bind(R.id.detail_organization_txt)
    TextView mOrganizationTxt;

    @Bind(R.id.detail_requirement_txt)
    TextView mRequirementTxt;

    @Bind(R.id.detail_close_img)
    ImageView mClose;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_schedule_detail);
        ButterKnife.bind(this);
        String path = getIntent().getStringExtra("video_path");
        if (path != null) {

            path = Environment.getExternalStorageDirectory() + "/KT/" + path.substring(path.lastIndexOf("/") + 1);
            findViewById(R.id.toppic).setVisibility(View.GONE);
            setControllerVisible(false);
            start(path);
mVideoView.setBackgroundResource(R.drawable.choose_button_corner_upper);
        } else {

        }
    }


    @OnClick(R.id.detail_close_img)
    public void OnClick(View view) {
        this.finish();
    }
}
