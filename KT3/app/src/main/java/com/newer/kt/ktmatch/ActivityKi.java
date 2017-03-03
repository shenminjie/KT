package com.newer.kt.ktmatch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.newer.kt.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by bajieaichirou on 17/3/1.
 */
public class ActivityKi extends Activity {
    public static String title = "";


    @Bind(R.id.ki_left_score_txt)
    TextView mLeftScoreTxt;

    @Bind(R.id.ki_right_score_txt)
    TextView mRightScoreTxt;

    @Bind(R.id.ki_circle_kt_img)
    ImageView mCircleKt;

    @Bind(R.id.ki_left_kt_img)
    ImageView mLeftKtImg;

    @Bind(R.id.ki_right_kt_img)
    ImageView mRightKtImg;

    @Bind(R.id.ki_left_winner_img)
    ImageView mLeftWinnerImg;

    @Bind(R.id.ki_right_winner_img)
    ImageView mRightWinnerImg;

    @Bind(R.id.ki_title_txt)
    TextView mGameTitleTxt;

    @Bind(R.id.ki_time_txt)
    TextView mGameTimeTxt;

    @Bind(R.id.ki_address_txt)
    TextView mGameAddressTxt;

    private String leftScore, rightScore, gameTitle, gameTime, gameAddress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ki);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        leftScore = (Integer.parseInt(Params.getInstanceParam().getGoals1())+Integer.parseInt(Params.getInstanceParam().getPannas1()))+"";
        rightScore = (Integer.parseInt(Params.getInstanceParam().getGoals2())+Integer.parseInt(Params.getInstanceParam().getPannas2()))+"";
        gameTitle = title;
        gameTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        gameAddress = "";
        mLeftScoreTxt.setText(leftScore);
        mRightScoreTxt.setText(rightScore);
        mGameTitleTxt.setText(gameTitle);
        mGameTimeTxt.setText(gameTime);
        mGameAddressTxt.setText(gameAddress);
        int left_score_temp = Integer.parseInt(leftScore);
        int right_score_temp = Integer.parseInt(rightScore);
        if (left_score_temp > right_score_temp){
            mLeftWinnerImg.setVisibility(View.VISIBLE);

            mRightWinnerImg.setVisibility(View.GONE);
        }else if (left_score_temp < right_score_temp){
            mLeftWinnerImg.setVisibility(View.GONE);
            mRightWinnerImg.setVisibility(View.VISIBLE);
        }else{
            mLeftWinnerImg.setVisibility(View.GONE);
            mRightWinnerImg.setVisibility(View.GONE);
        }
        if(Params.getInstanceParam().getPanna_ko1().equals("1")){

            mLeftKtImg.setVisibility(View.VISIBLE);
            mRightKtImg.setVisibility(View.GONE);

        }else if(Params.getInstanceParam().getPanna_ko2().equals("1")){

            mLeftKtImg.setVisibility(View.GONE);
            mRightKtImg.setVisibility(View.VISIBLE);

        }else{

            mLeftKtImg.setVisibility(View.GONE);
            mRightKtImg.setVisibility(View.GONE);

    }
    }

    @OnClick(R.id.ki_circle_kt_img)
    public void OnClick(View view){
        this.finish();
    }

}
