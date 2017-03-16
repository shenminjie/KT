package com.newer.kt.fragment.peixun;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.frame.app.utils.LogUtils;
import com.google.gson.Gson;
import com.newer.kt.R;
import com.newer.kt.Refactor.ui.Avtivity.LoginActivity;
import com.newer.kt.entity.StudySkillInfo;
import com.newer.kt.entity.jineng.JiNeng_Bean;
import com.newer.kt.ktmatch.QueryBuilder;
import com.newer.kt.ui.ShipinXiangqingActivity;
import com.newer.kt.ui.video_list.VideoListActivity;

import org.json.JSONObject;
import org.xutils.http.RequestParams;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class JiNeng_Liebiao extends AppCompatActivity implements QueryBuilder.Callback {

    private ImageView iv_back;
    private TextView tv_jineng;


    private JiNeng_Bean data;

    private StudySkillInfo mStudySkillInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jineng);
        ButterKnife.bind(this);

        data = (JiNeng_Bean) getIntent().getSerializableExtra("data");


        if (data != null) {
            long user_id = PreferenceManager.getDefaultSharedPreferences(this).getLong(LoginActivity.PRE_CURRENT_USER_ID, 0);
            QueryBuilder.build("study/app_cartoon").add("user_id", user_id).add("app_cartoon_id", data.getApp_cartoon_id()).get(this);
        }
    }

    private void initDate() {
        Intent intent = getIntent();
        if (intent != null) {
            tv_jineng.setText(mStudySkillInfo.getName());
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
        if (mStudySkillInfo != null) {
//            VideoListActivity.toAcitivty(this, mStudySkillInfo.getVideos());
            //根据初中高来跳转，默认低级
            ShipinXiangqingActivity.toActivity(this,mStudySkillInfo);
        }
    }

    /**
     * 跳转
     *
     * @param context context
     * @param data    data
     */
    public static void toActivity(Context context, JiNeng_Bean data) {
        Intent intent = new Intent(context, JiNeng_Liebiao.class);
        intent.putExtra("data", data);
        context.startActivity(intent);
    }

    @Override
    public void onSuccess(String result) {
        LogUtils.e("返回的数据:" + result);
        Gson gson = new Gson();
        StudySkillInfo studySkillInfo = gson.fromJson(result, StudySkillInfo.class);
        if (studySkillInfo != null && "success".equals(studySkillInfo.getResponse())) {
            mStudySkillInfo = studySkillInfo;
            initView();
            initDate();
            initOnclick();
        }
    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    @Override
    public void onDebug(RequestParams rp) {

    }
}
