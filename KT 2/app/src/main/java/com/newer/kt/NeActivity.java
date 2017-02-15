package com.newer.kt;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.netease.neliveplayer.NELivePlayer;
import com.netease.neliveplayer.NEMediaPlayer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import io.vov.vitamio.MediaPlayer;

import static com.netease.neliveplayer.NEDownTactics.NELP_LOG_SILENT;

/**
 * Created by win7 on 2017/2/14.
 */

public class NeActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_ne);
        findViewById(R.id.jindushow);

        final NEVideoView mVeoView = (NEVideoView) findViewById(R.id.video_view);
        mVeoView.set(getIntent().getStringExtra("path"));
//        mVeoView.setOnBufferingUpdateListener(new NELivePlayer.OnBufferingUpdateListener() {
//            @Override
//            public void onBufferingUpdate(NELivePlayer neLivePlayer, int i) {
//
//            }
//
//        });
        mVeoView.setOnPreparedListener(new NELivePlayer.OnPreparedListener() {

            @Override
            public void onPrepared(final NELivePlayer neLivePlayer) {
                ((ProgressBar) findViewById(R.id.jindu)).setProgress((int) (neLivePlayer.getDuration() / 1000));

                mVeoView.play();
                new Timer().schedule(new TimerTask() {
                    long len = neLivePlayer.getDuration();

                    @Override
                    public void run() {
                        len -= 1000;
                        if (len <= 0) {
                            Thread.interrupted();
                        } else {
                            long duration = len;
                            new Handler(Looper.getMainLooper(), new Handler.Callback() {
                                @Override
                                public boolean handleMessage(Message message) {
                                    ((ProgressBar) findViewById(R.id.jindu)).setProgress((int) len);
                                    int m = (int) (len/60);
                                    int sec = 0;
                                    if(m<60){
                                        sec = (int) (len%60);
                                    }

                                    ((TextView) findViewById(R.id.jindushow)).setText(new SimpleDateFormat("mm;ss").format(len*1000));

                                    return false;
                                }
                            }).sendEmptyMessage(0);
                        }
                    }
                }, new Date(), 1000);

            }
        });

    }


}
