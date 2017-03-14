package com.newer.kt.Refactor.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.newer.kt.R;

public class SchoolName extends AppCompatActivity {

    private TextView tv_quxiao;
    private TextView tv_baocun;
    private EditText et_schoolName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_name);

        initView();
        initDate();
        initOnclick();
    }

    private void initOnclick() {
        tv_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent=new Intent();
                finish();
            }
        });

    }

    private void initDate() {
        Intent intent=getIntent();
        if(intent!=null){
            String schoolValue=intent.getStringExtra("schoolName");
            et_schoolName.setText(schoolValue.toString());
        }

    }

    private void initView() {
        tv_quxiao = ((TextView) findViewById(R.id.tv_quxiao));
        tv_baocun = ((TextView) findViewById(R.id.tv_baocun));
        et_schoolName = ((EditText) findViewById(R.id.et_schoolName));
    }
}
