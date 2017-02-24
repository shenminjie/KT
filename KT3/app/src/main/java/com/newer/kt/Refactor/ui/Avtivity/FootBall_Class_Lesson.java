package com.newer.kt.Refactor.ui.Avtivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.newer.kt.R;

public class FootBall_Class_Lesson extends AppCompatActivity {
    private ImageView iv_ft_back1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot_ball__class__lesson);


        initView();
        initEvent();

    }

    private void initEvent() {
        iv_ft_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FootBall_Class_Lesson.this.finish();
            }
        });
    }

    private void initView() {
        iv_ft_back1 = ((ImageView) findViewById(R.id.iv_ft_back1));
    }
}
