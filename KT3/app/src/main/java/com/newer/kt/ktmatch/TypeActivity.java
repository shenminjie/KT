package com.newer.kt.ktmatch;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.os.Bundle;
import android.view.View;

import com.frame.app.base.activity.BaseActivity;
import com.newer.kt.R;

public class TypeActivity extends BaseActivity {
    public static void invoke(Context ctx) {
        Intent intent = new Intent(ctx, TypeActivity.class);
        ctx.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kt_type);
        Params.getInstanceParam().setJudge_type(1+"");
        findViewById(R.id.choose1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),MathChooseActivity.class).putExtra("num",1));
                Params.getInstanceParam().setGame_type(0+"");
            }
        });
        findViewById(R.id.choose2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),MathChooseActivity.class).putExtra("num",2));
                Params.getInstanceParam().setGame_type(1+"");
            }
        });
        findViewById(R.id.choose3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),MathChooseActivity.class).putExtra("num",3));
                Params.getInstanceParam().setGame_type(2+"");
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
    protected void initData(Bundle savedInstanceState) {

    }
}
