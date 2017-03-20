package com.newer.kt;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;


import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.Serializable;

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
        String image = (String) getIntent().getSerializableExtra("image");
        ImageLoader.getInstance().displayImage(image, (ImageView) findViewById(R.id.iv));
        String path = getIntent().getStringExtra("video_path");
//        String path = getIntent().getStringExtra("gym_video_url");

        if (path != null) {

            path = Environment.getExternalStorageDirectory() + "/KT/" + path.substring(path.lastIndexOf("/") + 1);
            findViewById(R.id.toppic).setVisibility(View.GONE);
            findViewById(R.id.play_toolbar).setVisibility(View.GONE);

                    setHideAnimation(findViewById(R.id.iv),3000);
            setControllerVisible(false);
            startNoController(path);

            mVideoView.setBackgroundResource(R.drawable.choose_button_corner_upper);
        } else {

        }


//        mTitleTxt.setText(getIntent().getStringExtra("child_title"));
//        mOrganizationTxt.setText(getIntent().getStringExtra("organization"));
//        mRequirementTxt.setText(getIntent().getStringExtra("exercise_requirement"));

    }

    BroadcastReceiver brr = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//                String url = intent.getStringExtra("url").toString();
//                Intent in = new Intent(context, ActivityScheduleDetail.class).putExtra("video_path",url);
//                startActivity(in);
        }
    };

    private void setHideAnimation( View view, int duration ){  
    if( null == view || duration < 0 ){  
        return;  
    }

        AlphaAnimation mHideAnimation = new AlphaAnimation(1.0f, 0.0f);
    mHideAnimation.setDuration( duration );  
    mHideAnimation.setFillAfter( true );  
    view.startAnimation( mHideAnimation );  
    }  

    @OnClick(R.id.detail_close_img)
    public void OnClick(View view) {
        this.finish();
    }
}
