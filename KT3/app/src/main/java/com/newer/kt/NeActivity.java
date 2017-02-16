package com.newer.kt;

import android.app.Activity;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.netease.neliveplayer.NELivePlayer;
import com.netease.neliveplayer.NEMediaPlayer;

import java.io.File;
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

    public String getLen(String file) {
        if (new File(file).exists()) {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(file);
            String fileLength = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            return fileLength;
        }
        return "";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_ne);
        final String path = getIntent().getStringExtra("path");
        final NEVideoView mVeoView = (NEVideoView) findViewById(R.id.video_view);


//        mVeoView.setOnBufferingUpdateListener(new NELivePlayer.OnBufferingUpdateListener() {
//            @Override
//            public void onBufferingUpdate(NELivePlayer neLivePlayer, int i) {
//
//            }
//
//        });

//        mVeoView.play();
//
        final long leng = Long.parseLong(getLen(path));
        Date d = new Date(leng);
        String s = new SimpleDateFormat("mm:ss").format(d);
        final long size = leng % 1000 > 0 ? leng / 1000 + 1 : leng / 1000;
//        ((ProgressBar) findViewById(R.id.jindu)).setMax((int) size);


//        ((ProgressBar) findViewById(R.id.jindu)).setProgress((int) (leng / 1000));


        daojishi(leng, size);


        mVeoView.set(path);
        mVeoView.play();
    }

    private void daojishi(final long leng, final long size) {
        Timer t = new Timer();
        TimerTask timerTask;
        t.schedule(timerTask = new TimerTask() {
            long t = leng;
            long len = size;

            @Override
            public void run() {
                t -= 1000;
                len -= 1;
                if (len <= 0) {
                    Thread.interrupted();
                } else {
                    long duration = len;
                    final long finalLen = len;
//                    if(t*2<=leng){
//                        try {
//                            wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
                    new Handler(Looper.getMainLooper(), new Handler.Callback() {
                        @Override
                        public boolean handleMessage(Message message) {
                            View v = (View) ( findViewById(R.id.jindu)).getParent();
                            ( findViewById(R.id.jindu)).getLayoutParams().width = ((int) (size - finalLen))*v.getWidth()/((int) (size));
                            ( findViewById(R.id.jindu)).invalidate();

                            ((TextView) findViewById(R.id.jindushow)).setText(new SimpleDateFormat("mm:ss").format(new Date(t)));

                            return false;
                        }
                    }).sendEmptyMessage(0);
                }
            }
        }, new Date(), 1000);
    }


}
