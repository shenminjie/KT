package com.newer.kt;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bajieaichirou on 17/3/7.
 */
public class ActivityScheduleDetail extends Activity {

    @Bind(R.id.detail_title)
    TextView mTitleTxt;

    @Bind(R.id.detail_title_classfy)
    TextView mClassfyTxt;

    @Bind(R.id.detail_organization_txt)
    TextView mOrganizationTxt;

    @Bind(R.id.detail_requirement_txt)
    TextView mRequirementTxt;

    @Bind(R.id.detail_close_img)
    ImageView mClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_schedule_detail);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        //TODO
//        mTitleTxt.setText(getIntent().getStringExtra("detail_title"));
//        mClassfyTxt.setText(getIntent().getStringExtra("detail_classfy"));
//        mOrganizationTxt.setText(getIntent().getStringExtra("detail_organization"));
//        mRequirementTxt.setText(getIntent().getStringExtra("detail_requirement"));
    }

    @OnClick(R.id.detail_close_img)
    public void OnClick(View view){
        this.finish();
    }
}
