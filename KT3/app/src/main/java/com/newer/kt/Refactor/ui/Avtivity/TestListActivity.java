package com.newer.kt.Refactor.ui.Avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.frame.app.base.activity.BaseActivity;
import com.frame.app.utils.GsonTools;
import com.newer.kt.R;
import com.newer.kt.Refactor.KTApplication;
import com.newer.kt.Refactor.adapter.TestListAdapter;
import com.newer.kt.entity.AddClassData;
import com.newer.kt.entity.GradeList;
import com.newer.kt.ui.pingce.input_result.InputResultFragment;
import com.newer.kt.ui.pingce.pingce_obj.PingceObjFragment;
import com.newer.kt.ui.pingce.select_skill.SelectSkillFragment;
import com.newer.kt.ui.pingce.select_skill.SkillVideoEvent;
import com.newer.kt.view.IndexViewPager;
import com.smj.event.NextStepEvent;
import com.smj.gradlebean.Users;
import com.smj.skillbean.SkillInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by leo on 16/11/11.
 */
public class TestListActivity extends BaseActivity {

    private PingceObjFragment mPingceObjFragment;
    private SelectSkillFragment mSkillFragment;
    private InputResultFragment mInputResultFragment;
    private List<Fragment> mFragmentList;

    private FragmentAdapter mAdapter;

    @Bind(R.id.viewpager)
    IndexViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initHandler(Message msg) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_testlist);
        ButterKnife.bind(this);
        mFragmentList = new ArrayList<>();
        mPingceObjFragment = PingceObjFragment.newInstance("", "");
        mSkillFragment = SelectSkillFragment.newInstance("", "");
        mInputResultFragment = InputResultFragment.newInstance("", "");
        mFragmentList.add(mPingceObjFragment);
        mFragmentList.add(mSkillFragment);
        mFragmentList.add(mInputResultFragment);

        mAdapter = new FragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
    }

    private List<Users> mStudents;


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNextEvent(SkillVideoEvent event) {
        mInputResultFragment.setDatas(mStudents, event.skillInfo, event.path,event.clz);
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
        Log.e("tag", "收到的学生:" + mStudents);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNextEvent(NextStepEvent event) {
        int position = event.position;
        if (event.students != null) {
            mStudents = event.students;
            //设置数据
            mSkillFragment.setUsers(mStudents,event.clz);
        }
        mViewPager.setCurrentItem(position);
        Log.e("tag", "收到的学生:" + mStudents);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    //退出当前Activity
    public void doBack(View view) {
        onBackPressed();
    }

    public void doHome(View view) {
        Intent intent = new Intent(getThis(), ClubDataActivity3.class);
        startActivity(intent);
    }


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

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() != 0) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
            return;
        }
        super.onBackPressed();
    }
}
