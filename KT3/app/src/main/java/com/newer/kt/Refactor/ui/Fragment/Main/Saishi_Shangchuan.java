package com.newer.kt.Refactor.ui.Fragment.Main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.frame.app.utils.LogUtils;
import com.newer.kt.R;
import com.newer.kt.Refactor.ui.Avtivity.LoginActivity;
import com.newer.kt.ktmatch.QueryBuilder;
import com.newer.kt.ktmatch.json.JsonUtil;
import com.newer.kt.ui.upload.UpLoadFragment;
import com.newer.kt.utils.DialogUtil;

import net.sf.json.util.JSONUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Saishi_Shangchuan extends AppCompatActivity {


    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;



    UpLoadFragment cepingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saishi_shangchuan);
        ButterKnife.bind(this);

        tvTitle.setText("上传视频");
        cepingFragment = UpLoadFragment.newInstance("", "");

        getSupportFragmentManager().beginTransaction().
                replace(R.id.fragment_container, cepingFragment).commitAllowingStateLoss();
        initData();

    }

    private void initData() {
        long user_id = PreferenceManager.getDefaultSharedPreferences(this).getLong(LoginActivity.PRE_CURRENT_USER_ID, 0);
        QueryBuilder.build("users/get_role").add("user_id", user_id).get(new QueryBuilder.Callback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.has("youku_token")) {
                        String youkuToken = jsonObject.getString("youku_token");
                        cepingFragment.setToken(youkuToken);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onDebug(RequestParams rp) {

            }
        });
    }


    @OnClick({R.id.image_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (cepingFragment.isUploading()) {
            DialogUtil.showAlert(this, "正在上传", "退出后上传会中断，是否取消上传", "退出", "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    finish();
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            return;
        }
        finish();
    }

}
