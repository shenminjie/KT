package com.newer.kt.ktmatch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.newer.kt.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bajieaichirou on 17/3/1.
 */
public class ActivityScore extends Activity {

    @Bind(R.id.left_ball_plus)
    ImageView mLeft_ball_plus_img;

    @Bind(R.id.left_ball_minus)
    ImageView mLeft_ball_minus_img;

    @Bind(R.id.left_pass_plus)
    ImageView mLeft_pass_plus_img;

    @Bind(R.id.left_pass_minus)
    ImageView mLeft_pass_minus_img;

    @Bind(R.id.left_x_plus)
    ImageView mLeft_x_plus_img;

    @Bind(R.id.left_x_minus)
    ImageView mLeft_x_minus_img;

    @Bind(R.id.right_ball_plus)
    ImageView mRight_ball_plus_img;

    @Bind(R.id.right_ball_minus)
    ImageView mRight_ball_minus_img;

    @Bind(R.id.right_pass_plus)
    ImageView mRight_pass_plus_img;

    @Bind(R.id.right_pass_minus)
    ImageView mRight_pass_minus_img;

    @Bind(R.id.right_x_plus)
    ImageView mRight_x_plus_img;

    @Bind(R.id.right_x_minus)
    ImageView mRight_x_minus_img;

    @Bind(R.id.left_ball_value)
    TextView mLeft_ball_value_txt;

    @Bind(R.id.left_pass_value)
    TextView mLeft_pass_value_txt;

    @Bind(R.id.left_x_value)
    TextView mLeft_x_value_txt;

    @Bind(R.id.right_ball_value)
    TextView mRight_ball_value_txt;

    @Bind(R.id.right_pass_value)
    TextView mRight_pass_value_txt;

    @Bind(R.id.right_x_value)
    TextView mRight_x_value_txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        ButterKnife.bind(this);
        setOnClick();
    }

    private void setOnClick(){
        mRight_pass_plus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRight_pass_value_txt.setText(plus(mRight_pass_value_txt.getText().toString()));
            }
        });

        mRight_pass_minus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRight_pass_value_txt.setText(minus(mRight_pass_value_txt.getText().toString()));
            }
        });

        mRight_x_plus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRight_x_value_txt.setText(plus(mRight_x_value_txt.getText().toString()));
            }
        });

        mRight_x_minus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRight_x_value_txt.setText(minus(mRight_x_value_txt.getText().toString()));
            }
        });
    }

    @OnClick({R.id.left_ball_plus, R.id.left_ball_minus,
        R.id.left_pass_plus, R.id.right_pass_minus,
        R.id.left_x_plus, R.id.left_x_minus,
        R.id.right_ball_plus, R.id.right_ball_minus})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.left_ball_plus:
                mLeft_ball_value_txt.setText(plus(mLeft_ball_value_txt.getText().toString()));
                break;
            case R.id.left_ball_minus:
                mLeft_ball_value_txt.setText(minus(mLeft_ball_value_txt.getText().toString()));
                break;
            case R.id.left_pass_plus:
                mLeft_pass_value_txt.setText(plus(mLeft_pass_value_txt.getText().toString()));
                break;
            case R.id.left_pass_minus:
                mLeft_pass_value_txt.setText(minus(mLeft_pass_value_txt.getText().toString()));
                break;
            case R.id.left_x_plus:
                mLeft_x_value_txt.setText(plus(mLeft_x_value_txt.getText().toString()));
                break;
            case R.id.left_x_minus:
                mLeft_x_value_txt.setText(minus(mLeft_x_value_txt.getText().toString()));
                break;
            case R.id.right_ball_plus:
                mRight_ball_value_txt.setText(plus(mRight_ball_value_txt.getText().toString()));
                break;
            case R.id.right_ball_minus:
                mRight_ball_value_txt.setText(minus(mRight_ball_value_txt.getText().toString()));
                break;
        }
    }

    private String minus(String value){
        String temp = "0";
        if (!value.isEmpty()){
            int minus_value = Integer.valueOf(value);
            if (minus_value > 0){
                minus_value --;
                temp = String.valueOf(minus_value);
            }else{
                temp = "0";
                Toast.makeText(this, "已是最小值0", Toast.LENGTH_SHORT).show();
            }
        }
        return temp;
    }

    private String plus(String value){
        String temp = "0";
        if (!value.isEmpty()){
            int minus_value = Integer.valueOf(value);
            minus_value ++;
            temp = String.valueOf(minus_value);
        }
        return temp;
    }
}
