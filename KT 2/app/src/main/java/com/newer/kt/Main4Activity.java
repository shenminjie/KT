package com.newer.kt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;

public class Main4Activity extends Activity {

    private boolean fullscreen = true;
    private MyVideoView audtoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        audtoView = ((MyVideoView) findViewById(R.id.audtoView));

        if(!LibsChecker.checkVitamioLibs(this)){return;}
        audtoView.setVideoPath(getCacheDir()+"/大课间第一套/大课间第一套.mp4");
//        vv.setVideoPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)+"/测试.mp4");
//        full_holder.setMediaController(new MediaController(BigClassDetailActivity.this));

        audtoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        audtoView.start();

        if(!fullscreen){//设置RelativeLayout的全屏模式
            RelativeLayout.LayoutParams layoutParams=
                    new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            audtoView.setLayoutParams(layoutParams);

            fullscreen = true;//改变全屏/窗口的标记
        }else{//设置RelativeLayout的窗口模式
            RelativeLayout.LayoutParams lp=new  RelativeLayout.LayoutParams(320,240);
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            audtoView.setLayoutParams(lp);
            fullscreen = false;//改变全屏/窗口的标记
        }
    }
}
