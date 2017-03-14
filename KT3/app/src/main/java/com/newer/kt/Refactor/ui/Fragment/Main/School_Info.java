package com.newer.kt.Refactor.ui.Fragment.Main;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.newer.kt.R;
import com.newer.kt.Refactor.Constants;
import com.newer.kt.Refactor.ui.Avtivity.LoginActivity;
import com.newer.kt.Refactor.ui.SchoolName;
import com.newer.kt.Refactor.utils.MD5;
import com.newer.kt.entity.Club_Info;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class School_Info extends AppCompatActivity {

    private ImageView image_vs_item_back;
    private Club_Info club_info;
    private TextView tv_stuInfo1;
    private TextView tv_stuInfo2;
    private TextView tv_stuInfo3;
    private ImageView iv_stuInfo1;
    private RelativeLayout rl_title3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school__info);

        initView();
        initDate();
        initOnclick();


    }

    private void initOnclick() {
        image_vs_item_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rl_title3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), SchoolName.class);
                intent.putExtra("schoolName",club_info.getName().toString());
                startActivity(intent);

            }
        });
    }

    private void initDate() {
        String url = Constants.KTHOST + "club_app/club_info";
        RequestParams p = new RequestParams(url);
        p.addQueryStringParameter("authenticity_token", MD5.getToken(url));

        final String clubid = "" + PreferenceManager.getDefaultSharedPreferences(this).getLong(LoginActivity.PRE_CURRENT_CLUB_ID, 1);

        p.addQueryStringParameter("club_id", clubid);
//        response: "success",
//                name: 校园名称,
//                school_student_count: 学生数,
//                avatar: 俱乐部头像,
//                higher_manager: 上级管理员
        x.http().get(p, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                club_info = gson.fromJson(result, Club_Info.class);
                tv_stuInfo1.setText(club_info.getName().toString());
                tv_stuInfo2.setText(club_info.getSchool_student_count().toString());
                Glide.with(getApplicationContext()).load(club_info.getAvatar().toString()).into(iv_stuInfo1);

                if (club_info.higher_manager.toString() == null) {

                } else {
                    tv_stuInfo3.setText(club_info.getHigher_manager().toString());

                }
                //    ImageLoader.getInstance().displayImage(club_info.avatar,iv_stuInfo1);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    private void initView() {
        image_vs_item_back = ((ImageView) findViewById(R.id.image_vs_item_back));
        tv_stuInfo1 = ((TextView) findViewById(R.id.tv_stuInfo1));
        tv_stuInfo2 = ((TextView) findViewById(R.id.tv_stuInfo2));
        tv_stuInfo3 = ((TextView) findViewById(R.id.tv_stuInfo3));
        iv_stuInfo1 = ((ImageView) findViewById(R.id.iv_stuInfo1));
        rl_title3 = ((RelativeLayout) findViewById(R.id.rl_title3));
    }
}
