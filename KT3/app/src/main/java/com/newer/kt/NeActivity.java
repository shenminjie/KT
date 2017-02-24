package com.newer.kt;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.umeng.um_share.ShareActy;

/**
 * Created by win7 on 2017/2/14.
 */



public class NeActivity extends NEVideoPlayerActivity {
    public static NeActivity neActivity;

    public static NeActivity getThisInstance() {
        return neActivity;
    }

    public String getLen(String file) {
        if (new File(file).exists()) {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(file);
            String fileLength = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            return fileLength;
        }
        return "";
    }

    long leng;
    NEVideoView mVeoView;
    String path;
    long size;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_ne);
        path = getIntent().getStringExtra("path");

        neActivity = this;
//        mVeoView.setOnBufferingUpdateListener(new NELivePlayer.OnBufferingUpdateListener() {
//            @Override
//            public void onBufferingUpdate(NELivePlayer neLivePlayer, int i) {
//
//            }
//
//        });

//        mVeoView.play();
//
        leng = Long.parseLong(getLen(path));
        Date d = new Date(leng);
        String s = new SimpleDateFormat("mm:ss").format(d);
        size = leng % 1000 > 0 ? leng / 1000 + 1 : leng / 1000;
//        ((ProgressBar) findViewById(R.id.jindu)).setMax((int) size);


//        ((ProgressBar) findViewById(R.id.jindu)).setProgress((int) (leng / 1000));

        init();
    }

    long played;
    TimerTask timerTask;

    private void daojishi(final long leng, final long size) {
        Timer t = new Timer();
        t.schedule(timerTask = new TimerTask() {
            long t = leng;
            long len = size;

            @Override
            public synchronized void run() {
                t -= 1000;
                played += 1000;
                len -= 1;
                if (len <= 0) {
                    Thread.interrupted();
                } else {
                    long duration = len;
                    final long finalLen = len;
                    if (t * 1l + 6000l <= leng) {
                        try {
                            mVeoView.setPos(played*5);
                            mVeoView.pause();

                            share();
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    new Handler(Looper.getMainLooper(), new Handler.Callback() {
                        @Override
                        public boolean handleMessage(Message message) {
                            View v = (View) (findViewById(R.id.jindu)).getParent();
                            (findViewById(R.id.jindu)).getLayoutParams().width = ((int) (size - finalLen)) * v.getWidth() / ((int) (size));
                            (findViewById(R.id.jindu)).invalidate();

                            ((TextView) findViewById(R.id.jindushow)).setText(new SimpleDateFormat("mm:ss").format(new Date(t)));

                            return false;
                        }
                    }).sendEmptyMessage(0);
                }
            }
        }, new Date(), 1000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mVeoView == null) {
            mVeoView = (NEVideoView) findViewById(R.id.video_view);
            mVeoView.set(path);
            mVeoView.setMediaController(new NEMediaController(this));
            mVeoView.play();

            daojishi(leng, size);

        } else {
//            mVeoView.stop();
        }


//        if (goon) {
//            mVeoView.seekTo(played);
//            timerTask.notify();
//        } else {
//            mVeoView.pause();
//        }
    }

    public static boolean goon;

    public void callback(boolean success) {
        if (success) {
//            mVeoView.seekTo(played);
            mVeoView.start();
//            timerTask.notify();
        } else {
            mVeoView.pause();

        }
    }
}
