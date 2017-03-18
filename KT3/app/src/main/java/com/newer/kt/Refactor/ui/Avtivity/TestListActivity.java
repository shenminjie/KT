package com.newer.kt.Refactor.ui.Avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ExpandableListView;

import com.frame.app.base.activity.BaseActivity;
import com.frame.app.utils.GsonTools;
import com.newer.kt.R;
import com.newer.kt.Refactor.KTApplication;
import com.newer.kt.Refactor.adapter.TestListAdapter;
import com.newer.kt.entity.AddClassData;
import com.newer.kt.entity.GradeList;
import com.newer.kt.ui.pingce.pingce_obj.PingceObjFragment;
import com.newer.kt.ui.pingce.select_skill.SelectSkillFragment;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by leo on 16/11/11.
 */
public class TestListActivity extends BaseActivity {

    private PingceObjFragment mPingceObjFragment;

    @Override
    protected void initHandler(Message msg) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_testlist);
//        mPingceObjFragment = PingceObjFragment.newInstance("", "");
        SelectSkillFragment skillFragment = SelectSkillFragment.newInstance("", "");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, skillFragment).commit();
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    //退出当前Activity
    public void doBack(View view) {
        finish();
    }

    public void doHome(View view) {
        Intent intent = new Intent(getThis(), ClubDataActivity3.class);
        startActivity(intent);
    }
}
