package com.newer.kt.ktmatch;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.coremedia.iso.boxes.Container;
import com.googlecode.mp4parser.authoring.Movie;
import com.googlecode.mp4parser.authoring.Track;
import com.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import com.googlecode.mp4parser.authoring.container.mp4.MovieCreator;
import com.googlecode.mp4parser.authoring.tracks.AppendTrack;
import com.newer.kt.R;
import com.newer.kt.record.MovieRecorderView;
import com.newer.kt.record.TakePicActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class MatchActivity extends TakePicActivity {
    List<String> video = new ArrayList<String>();
    private MovieRecorderView mRecorderView;
    private ImageView mShootBtn;
    private boolean isFinish = true;
    private ProgressBar mProgressbar;

//　三个头像 //继续 停止 // 停止1次。。。2次 //  //统计界面 新  ／／选择三个类型  ／／下拉动画 ／／选择
    public static void invoke(Context ctx) {
        Intent intent = new Intent(ctx, MatchActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match);
        mRecorderView = (MovieRecorderView) findViewById(R.id.movieRecorderView);
        mRecorderView.setMAXVEDIOTIME(180);
        mShootBtn = (ImageView) findViewById(R.id.shoot_button);
        mProgressbar = (ProgressBar) findViewById(R.id.progressBar);
        mRecorderView.setHandler(handler);

        findViewById(R.id.left_ball).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.left_ball_number);
                String str = tv.getText().toString();
                int i = Integer.parseInt(str);
                tv.setText((2+i)+"");
                TextView tv2 = (TextView) findViewById(R.id.left_total_number);
                String str2 = tv2.getText().toString();
                int i2 = Integer.parseInt(str2);
                tv2.setText((2+i2)+"");
                playSound(R.raw.getpoint);

            }
        });
        findViewById(R.id.left_pass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.left_pass_number);
                String str = tv.getText().toString();
                int i = Integer.parseInt(str);
                tv.setText((++i)+"");
                TextView tv2 = (TextView) findViewById(R.id.left_total_number);
                String str2 = tv2.getText().toString();
                int i2 = Integer.parseInt(str2);
                tv2.setText((++i2)+"");
                playSound(R.raw.getpoint);
            }
        });
        findViewById(R.id.right_ball).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.right_ball_number);
                String str = tv.getText().toString();
                int i = Integer.parseInt(str);
                tv.setText((2+i)+"");
                TextView tv2 = (TextView) findViewById(R.id.right_total_number);
                String str2 = tv2.getText().toString();
                int i2 = Integer.parseInt(str2);
                tv2.setText((2+i2)+"");
                playSound(R.raw.getpoint);
            }
        });
        findViewById(R.id.right_pass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.right_pass_number);
                String str = tv.getText().toString();
                int i = Integer.parseInt(str);
                tv.setText((++i)+"");
                TextView tv2 = (TextView) findViewById(R.id.right_total_number);
                String str2 = tv2.getText().toString();
                int i2 = Integer.parseInt(str2);
                tv2.setText((++i2)+"");
                playSound(R.raw.getpoint);
            }
        });
        findViewById(R.id.matchkt1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.ktsure).setVisibility(View.VISIBLE);
                fromleft = true;

            }
        });
        findViewById(R.id.matchkt2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.ktsure).setVisibility(View.VISIBLE);
                fromleft = false;

            }
        });

        findViewById(R.id.ktqueren).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.ktsure).setVisibility(View.GONE);
                if(fromleft){
                    Params.getInstanceParam().setPanna_ko1("1");
                }else {
                    Params.getInstanceParam().setPanna_ko2("1");
                }

                showKTOverVisible();
                finish();

            }
        });
        findViewById(R.id.ktquxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.ktsure).setVisibility(View.GONE);
            }
        });
        findViewById(R.id.left_x).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.left_x_number);
                String str = tv.getText().toString();
                int i = Integer.parseInt(str);
                tv.setText((++i)+"");
                findViewById(R.id.zhongzhi).setVisibility(View.VISIBLE);
                fromleft = true;


            }
        });
        findViewById(R.id.right_x).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.right_x_number);
                String str = tv.getText().toString();
                int i = Integer.parseInt(str);
                tv.setText((++i)+"");
                findViewById(R.id.zhongzhi).setVisibility(View.VISIBLE);
                fromleft = false;
            }
        });
//        mShootBtn.setOnTouchListener(new OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // TODO Auto-generated method stub
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    start();
//                } else if (event.getA ction() == MotionEvent.ACTION_UP) {
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

                        path = start();
                        Params.getInstanceParam().setYouku_uri(path);
                        video.add(path);

//                        mShootBtn.setImageResource(R.drawable.stoprecord);
                        findViewById(R.id.match).setVisibility(View.VISIBLE);
                        mShootBtn.setVisibility(View.GONE);
                        final long[] time = {180000};
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {




                                if(time[0]<=0){
                                    replaceEndLayout();
                                    initEndLtView();
                                    camstop();
                                    cancel();
                                    return;
                                }else{
                                    new Handler(Looper.getMainLooper(), new Handler.Callback() {
                                        @Override
                                        public boolean handleMessage(Message message) {
                                            showTimeViewInVisible(time[0]);
                                            return false;
                                        }
                                    }).sendEmptyMessage(0);
                                    time[0] -=1000;
                                }
                            }
                        },0,1000);
                        findViewById(R.id.submit).setVisibility(View.GONE);

            }
        });
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
        loadSound();
        findViewById(R.id.ktjixu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                findViewById(R.id.zhongzhi).setVisibility(View.GONE);
            }
        });
        findViewById(R.id.ktzhongzhi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.juti_zhongzhi).setVisibility(View.VISIBLE);
                findViewById(R.id.zhongzhi).setVisibility(View.GONE);

            }
        });
        findViewById(R.id.ktzhongzhi).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                findViewById(R.id.juti_zhongzhi).setVisibility(View.VISIBLE);
                findViewById(R.id.zhongzhi).setVisibility(View.GONE);

            }
        });

        findViewById(R.id.zuofang_qiquan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Params.getInstanceParam().setAbstained1("1");
                ;
//                showKTOverVisible();
                finish();
            }
        });
        findViewById(R.id.youfang_qiquan).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                Params.getInstanceParam().setAbstained2("1");
//                showKTOverVisible();
                finish();
            }
        });
    }

    int fromLeft = -1;
    private SoundPool soundPool;
    public void playSound(int rawres) {
        soundPool.play(soundPoolMap.get(rawres),1, 1, 0, 0, 1);
    }
    Map<Integer,Integer> soundPoolMap = new HashMap();
    public void loadSound(){
        soundPool= new SoundPool(10,AudioManager.STREAM_SYSTEM,5);
        soundPoolMap.put(R.raw.getpoint, soundPool.load(this, R.raw.getpoint, 1));
    }

    public void showKTOverVisible() {
        startActivity(new Intent(getBaseContext(),CountActivity.class));
        Params.getInstanceParam().setGoals1(((TextView)findViewById(R.id.left_ball_number)).getText().toString());
        Params.getInstanceParam().setGoals2(((TextView)findViewById(R.id.right_ball_number)).getText().toString());
        Params.getInstanceParam().setPannas1(((TextView)findViewById(R.id.left_pass_number)).getText().toString());
        Params.getInstanceParam().setPannas2(((TextView)findViewById(R.id.right_pass_number)).getText().toString());

    }
    boolean fromleft = false;
    public void initEndLtView() {

    }
    public void replaceEndLayout() {

    }
    public void showTimeViewInVisible(long time) {
        ((TextView)findViewById(R.id.time)).setText(new SimpleDateFormat("mm:ss").format(time));
    }

        private String mergeVideo() {
        if(video.size()==0){
            return "";
        }
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        Log.e("test", "merge use time:" + (end - begin));
        return merged.getAbsolutePath();
    }

    boolean showfinish;
    boolean showstart;

    private void submit() {
        Toast.makeText(getBaseContext(), "video " + mergeVideo(), Toast.LENGTH_LONG).show();
        finish();
//        new PicUploader() {
//
//            @Override
//            public void onSuccess(String imageUrl, String path) {
//                CenterRepo.getInsatnce().getRepo().put("videoUrl", imageUrl);
//            }
//
//            @Override
//            public void onFinish(SkillInfo<String> imageUrls) {
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


    public void camstop() {
        if(isDestroyed()){
            return;
        }
        mRecorderView.stop();
        showstart = false;
        mShootBtn.setImageResource(R.drawable.startrecord);

        showKTOverVisible();
    }


    public void success() {
        if (mRecorderView.getTimeCount() > 1)
            handler.sendEmptyMessage(1);
        else {
            if (mRecorderView.getVecordFile() != null)
                mRecorderView.getVecordFile().delete();
            mRecorderView.stop();
            Toast.makeText(MatchActivity.this, "视频录制时间太短", Toast.LENGTH_SHORT).show();
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
        stop();

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

