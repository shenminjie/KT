package com.newer.kt.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.newer.kt.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShipinXiangqingActivity extends AppCompatActivity {

    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipin_xiangqing);
        ButterKnife.bind(this);
        tvTitle.setText("hahah");
    }

    @OnClick(R.id.image_back)
    public void onFinish() {
        finish();
    }

    @OnClick(R.id.content_shipin_xiangqing)
    public void toYoukuVideo() {
        Intent intent = new Intent(this, com.newer.kt.Refactor.ui.Avtivity.Settings.PlayerActivity.class);
        startActivity(intent);
    }

}
