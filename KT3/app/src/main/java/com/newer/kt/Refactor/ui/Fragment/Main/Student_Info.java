package com.newer.kt.Refactor.ui.Fragment.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.frame.app.utils.ImageViewUtils;
import com.newer.kt.R;

public class Student_Info extends AppCompatActivity {

    private ImageView image_vs_item_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__info);
        initView();
        initOnclick();
    }

    private void initOnclick() {
        image_vs_item_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        image_vs_item_back = ((ImageView) findViewById(R.id.image_vs_item_back));
    }
}
