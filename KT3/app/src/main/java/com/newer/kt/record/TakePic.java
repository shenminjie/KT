package com.newer.kt.record;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.xygame.sg.R;
import com.xygame.sg.activity.base.CommonActivity;
import com.xygame.sg.activity.personal.CertiModelThirdPageActivity;
import com.xygame.sg.utils.Constants;
import com.xygame.sg.utils.MovieRecorderView;
import com.xygame.sg.utils.PicUploader;

import java.util.List;

import base.action.CenterRepo;

public class TakePic extends Activity {


    private MovieRecorderView mRecorderView;
    private ImageView mShootBtn;
    private boolean isFinish = true;
    private ProgressBar mProgressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

                if (!showstart) {
                    showstart = true;

                    if (showstart) {
                        start();

                    } else {


                    }
                }
            }
        });
        findViewById(R.id.restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showstart = false;((TextView) findViewById(R.id.text)).setText("0'/5''");
                mShootBtn.setImageResource(R.drawable.startrecord);
                findViewById(R.id.ctrol).setVisibility(View.GONE);
            }
        });
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit(mRecorderView.getVecordFile().toString());
            }
        });
        mRecorderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showfinish) {
                    mRecorderView.play();
                }
            }
        });
    }

    boolean showfinish;
    boolean showstart;

    private void submit(String s) {
        new PicUploader() {

            @Override
            public void onSuccess(String imageUrl, String path) {
                CenterRepo.getInsatnce().getRepo().put("videoUrl", imageUrl);
            }

            @Override
            public void onFinish(List<String> imageUrls) {
                startActivity(new Intent(TakePic.this, CertiModelThirdPageActivity.class));

            }

            @Override
            public void onFailure(int errorCode, String msg, int requestCode) {

            }
        }.upload(new String[]{Constants.VERIFICATION_PATH}, new String[]{s});


    }


    public void start() {
        showfinish = false;
        mRecorderView.onDestroy();
        mRecorderView.record(new MovieRecorderView.OnRecordFinishListener() {

            @Override
            public void onRecordFinish() {
                handler.sendEmptyMessage(1);
            }
        });
    }

    public void restart() {
        mShootBtn.setImageResource(R.drawable.startrecord);
        findViewById(R.id.ctrol).setVisibility(View.GONE);
        stop();
        start();
    }

    public void stop() {
        if (mRecorderView.getVecordFile() != null)
            mRecorderView.getVecordFile().delete();
        mRecorderView.stop();
        showstart = false;
        mShootBtn.setImageResource(R.drawable.stoprecord);
    }

    public void success() {
        if (mRecorderView.getTimeCount() > 1)
            handler.sendEmptyMessage(1);
        else {
            if (mRecorderView.getVecordFile() != null)
                mRecorderView.getVecordFile().delete();
            mRecorderView.stop();
            Toast.makeText(TakePic.this, "视频录制时间太短", Toast.LENGTH_SHORT).show();
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
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                findViewById(R.id.ctrol).setVisibility(View.VISIBLE);
                mShootBtn.setImageResource(R.drawable.stoprecord);;
                showfinish = true;
            } else if (msg.what == 0) {
                ((TextView) findViewById(R.id.text)).setText(msg.arg1 + "'/5''");

            }

        }
    };

    private void finishActivity() {
        if (isFinish) {
            mRecorderView.stop();
        }
        Intent intent = new Intent();
        intent.putExtra("result", mRecorderView.getVecordFile().toString());
        setResult(0, intent);
        finish();
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

