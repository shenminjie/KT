package com.newer.kt.record;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.coremedia.iso.boxes.Container;
import com.frame.app.base.activity.BaseActivity;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import com.googlecode.mp4parser.authoring.container.mp4.MovieCreator;
import com.googlecode.mp4parser.authoring.tracks.AppendTrack;
import com.newer.kt.R;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;


public class TakePicActivity extends BaseActivity {

    List<String> video = new ArrayList<String>();
    private MovieRecorderView mRecorderView;
    private ImageView mShootBtn;
    private boolean isFinish = true;
    private ProgressBar mProgressbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_video);
        mRecorderView = (MovieRecorderView) findViewById(R.id.movieRecorderView);
        mShootBtn = (ImageView) findViewById(R.id.shoot_button);
        mProgressbar = (ProgressBar) findViewById(R.id.progressBar);
        mRecorderView.setHandler(handler);
//        mShootBtn.setOnTouchListener(new OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // TODO Auto-generated method stub
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    start();
//                } else if (event.getAction() == MotionEvent.ACTION_UP) {
//
//                    success();
//                }<code></code>
//                return true;
//            }
//        });reco


        mShootBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String path;

                if (!showstart) {
                    showstart = true;

                    if (showstart) {
                        path = start();
                        video.add(path);
                        mShootBtn.setImageResource(R.drawable.stoprecord);
                        findViewById(R.id.submit).setVisibility(View.GONE);
                    } else {


                    }
                }else{
                    mShootBtn.setImageResource(R.drawable.startrecord);
                    getWindow().getDecorView().invalidate();
                    stop();
//                    mergeVideo();
                    findViewById(R.id.submit).setVisibility(View.VISIBLE);
                }
            }
        });
//        findViewById(R.id.restart).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showstart = false;
//                ((TextView) findViewById(R.id.text)).setText("0'/5''");
//                mShootBtn.setImageResource(R.drawable.startrecord);
//                findViewById(R.id.ctrol).setVisibility(View.GONE);
//            }
//        });
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        mRecorderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showfinish) {
//                    mRecorderView.play();
                }
            }
        });
    }
    private String  mergeVideo() {
        long begin = System.currentTimeMillis();
        File merged = null;
        List<Movie> movies = new ArrayList<>();
        try {
            for (int i = 0; i < video.size(); i++) {
                Movie movie = null;
                    movie = MovieCreator.build(video.get(i));
                movies.add(movie);
            }
            List<Track> videoTracks = new ArrayList<>();
            List<Track> audioTracks = new ArrayList<>();
            for (Movie movie : movies) {
                for (Track track : movie.getTracks()) {
                    if ("vide".equals(track.getHandler())) {
                        videoTracks.add(track);
                    }
                    if ("soun".equals(track.getHandler())) {
                        audioTracks.add(track);
                    }
                }
            }
            Movie result = new Movie();
            if (videoTracks.size() > 0) {
                result.addTrack(new AppendTrack(videoTracks.toArray(new Track[videoTracks.size()])));
            }
            if (audioTracks.size() > 0) {
                result.addTrack(new AppendTrack(audioTracks.toArray(new Track[audioTracks.size()])));
            }

            Container container = new DefaultMp4Builder().build(result);
            FileChannel fc = new FileOutputStream(merged = mRecorderView
                    .createRecordDir(true)).getChannel();
            container.writeContainer(fc);
            fc.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        Log.e("test", "merge use time:" + (end - begin));
        return merged.getAbsolutePath();
    }
    boolean showfinish;
    boolean showstart;

    private void submit() {
        Toast.makeText(getBaseContext(),"video "+mergeVideo(),Toast.LENGTH_LONG).show();
        finish();
//        new PicUploader() {
//
//            @Override
//            public void onSuccess(String imageUrl, String path) {
//                CenterRepo.getInsatnce().getRepo().put("videoUrl", imageUrl);
//            }
//
//            @Override
//            public void onFinish(List<String> imageUrls) {
//                startActivity(new Intent(TakePicActivity.this, CertiModelThirdPageActivity.class));
//
//            }
//
//            @Override
//            public void onFailure(int errorCode, String msg, int requestCode) {
//
//            }
//        }.upload(new String[]{Constants.VERIFICATION_PATH}, new String[]{s});


    }


    public String start() {
        showfinish = false;
        mRecorderView.onDestroy();
        mShootBtn.setImageResource(R.drawable.stoprecord);
        String path = mRecorderView.record(new MovieRecorderView.OnRecordFinishListener() {

            @Override
            public void onRecordFinish() {
                handler.sendEmptyMessage(1);
            }
        });
        return path;
    }

    public void restart() {
        mShootBtn.setImageResource(R.drawable.startrecord);
        findViewById(R.id.ctrol).setVisibility(View.GONE);
        stop();
        start();
    }

    public void stop() {
        mRecorderView.stop();
        showstart = false;
        mShootBtn.setImageResource(R.drawable.startrecord);
    }


    public void success() {
        if (mRecorderView.getTimeCount() > 1)
            handler.sendEmptyMessage(1);
        else {
            if (mRecorderView.getVecordFile() != null)
                mRecorderView.getVecordFile().delete();
            mRecorderView.stop();
            Toast.makeText(TakePicActivity.this, "视频录制时间太短", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        isFinish = true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        isFinish = false;
        mRecorderView.stop();

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initHandler(Message msg) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void finish() {
        finishActivity();
        super.finish();

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
//                findViewById(R.id.ctrol).setVisibility(View.VISIBLE);
//                mShootBtn.setImageResource(R.drawable.stoprecord);;
                showfinish = true;

            } else if (msg.what == 0) {
//                ((TextView) findViewById(R.id.text)).setText(msg.arg1 + "'/5''");

            }

        }
    };

    private void finishActivity() {
//        if (isFinish) {
            mRecorderView.stop();

//        }
//        Intent intent = new Intent();
//        intent.putExtra("result", mRecorderView.getVecordFile().toString());
//        setResult(0, intent);
//        finish();
    }

    /**
     * 录制完成回调
     *
     * @author liuyinjun
     * @date 2015-2-9
     */
    public interface OnShootCompletionListener {
        public void OnShootSuccess(String path, int second);

        public void OnShootFailure();
    }
}

