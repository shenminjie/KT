package com.newer.kt.fragment.peixun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.newer.kt.R;
import com.newer.kt.ui.ShipinXiangqingActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class JiNeng_Liebiao extends AppCompatActivity {

    private ImageView iv_back;
    String jinengName;
    private TextView tv_jineng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jineng);
        ButterKnife.bind(this);
        initView();
        initDate();
        initOnclick();
    }
    //  ((TextView)findViewById(R.id.tv_title_stuInfo)).setText(getIntent().getStringExtra("title"));

    private void initDate() {
        Intent intent = getIntent();
        if (intent != null) {
            jinengName = intent.getStringExtra("jinengname");
            tv_jineng.setText(jinengName);
        }
    }

    private void initOnclick() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        iv_back = ((ImageView) findViewById(R.id.iv_back));
        tv_jineng = ((TextView) findViewById(R.id.tv_jineng));
    }

    @OnClick(R.id.layout_real_man)
    public void toRealManShow() {
        Intent intent = new Intent(this, ShipinXiangqingActivity.class);
        startActivity(intent);
    }

}
