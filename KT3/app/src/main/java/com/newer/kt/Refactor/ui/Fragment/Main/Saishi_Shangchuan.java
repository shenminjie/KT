package com.newer.kt.Refactor.ui.Fragment.Main;

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

    @Bind(R.id.viewpager)
    ViewPager mViewPager;


    String[] mTitles = new String[]{"赛事", "大课间", "测评"};
    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_saishi)
    TextView tvSaishi;
    @Bind(R.id.tv_dakejian)
    TextView tvDakejian;
    @Bind(R.id.tv_ceping)
    TextView tvCeping;

    private List<Fragment> mFragmentList;

    UpLoadFragment saishiFragment;
    UpLoadFragment dashijianFragment;
    UpLoadFragment cepingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saishi_shangchuan);
        ButterKnife.bind(this);
        mFragmentList = new ArrayList<>();

        tvTitle.setText("上传视频");

        saishiFragment = UpLoadFragment.newInstance("", "");
        dashijianFragment = UpLoadFragment.newInstance("", "");
        cepingFragment = UpLoadFragment.newInstance("", "");
        mFragmentList.add(saishiFragment);
        mFragmentList.add(dashijianFragment);
        mFragmentList.add(cepingFragment);

        mAdapter = new FragmentAdapter(getSupportFragmentManager());


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
                        initFragment(youkuToken);
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

    /**
     * init
     *
     * @param token
     */
    private void initFragment(String token) {
        saishiFragment.setToken(token);
        dashijianFragment.setToken(token);
        cepingFragment.setToken(token);

        mViewPager.setAdapter(mAdapter);
    }

    @OnClick({R.id.image_back, R.id.tv_saishi, R.id.tv_dakejian, R.id.tv_ceping})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                finish();
                break;
            case R.id.tv_saishi:
                break;
            case R.id.tv_dakejian:
                break;
            case R.id.tv_ceping:
                break;
        }
    }

    private FragmentAdapter mAdapter;

    /**
     * adapter
     */
    class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
}
