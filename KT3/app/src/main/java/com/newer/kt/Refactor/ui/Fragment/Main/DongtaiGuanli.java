package com.newer.kt.Refactor.ui.Fragment.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.newer.kt.R;

public class DongtaiGuanli extends AppCompatActivity {

    private TextView tv_zhong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dongtai_guanli);

        initView();
        initOnclick();
    }

    private void initOnclick() {
        tv_zhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Saishi_Shangchuan.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        tv_zhong = ((TextView) findViewById(R.id.tv_zhong));
    }
}
