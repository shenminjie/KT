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
        finish();
    }

}
