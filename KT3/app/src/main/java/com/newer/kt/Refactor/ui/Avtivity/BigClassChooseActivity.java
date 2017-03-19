package com.newer.kt.Refactor.ui.Avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.frame.app.base.activity.BaseActivity;
import com.frame.app.utils.LogUtils;
import com.frame.app.view.LoadingDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.monkeyshen.recordlib.VideoCaptureActivity;
import com.monkeyshen.recordlib.configuration.CaptureConfiguration;
import com.newer.kt.R;
import com.newer.kt.ktmatch.QueryBuilder;
import com.newer.kt.ui.dakejian.DakejianXuanzeAdapter;
import com.newer.kt.utils.DialogUtil;
import com.smj.gradlebean.Classes;
import com.smj.gradlebean.GradeInfo;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * Created by leo on 16/11/11.
 */
public class BigClassChooseActivity extends BaseActivity implements DakejianXuanzeAdapter.Callback {
    private ExpandableListView listView;
    private LoadingDialog mLoading;
    private DakejianXuanzeAdapter mAdapter;

    @Override
    protected void initHandler(Message msg) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_big_choose);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        listView = getViewById(R.id.layout_select_class_list);
        mDatas = new ArrayList<>();
        mAdapter = new DakejianXuanzeAdapter(mDatas, this);
        listView.setAdapter(mAdapter);

        mLoading = new LoadingDialog(this);
        DialogUtil.show(mLoading);
        getData();
    }

    private List<GradeInfo> mDatas;

    /**
     * getdata
     */
    private void getData() {
        String clubid = "" + PreferenceManager.getDefaultSharedPreferences(this)
                .getLong(LoginActivity.PRE_CURRENT_CLUB_ID, 1);
        QueryBuilder.build("offline/get_club_school_class_data").add("club_id", clubid).get(new QueryBuilder.Callback() {
            @Override
            public void onSuccess(String result) {
                DialogUtil.dismiss(mLoading);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.has("response") && "success".equals(jsonObject.getString("response"))) {
                        String dataString = jsonObject.getString("grade_list");
                        Gson gson = new Gson();
                        List<GradeInfo> gradeInfos = gson.fromJson(dataString, new TypeToken<List<GradeInfo>>() {
                        }.getType());
                        mDatas.clear();
                        mDatas.addAll(gradeInfos);
                        mAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                DialogUtil.dismiss(mLoading);
            }

            @Override
            public void onDebug(RequestParams rp) {
                DialogUtil.dismiss(mLoading);
            }
        });
    }


    @OnClick(R.id.tv_luzhi)
    public void luzhi() {
        //调用录制----
        VideoCaptureActivity.toActivity(this, CaptureConfiguration.getDefault(), System.currentTimeMillis() + "");
    }

    //退出当前Activity
    public void doBack(View view) {
        finish();
    }

    public void doHome(View view) {
        Intent intent = new Intent(getThis(), ClubDataActivity3.class);
        startActivity(intent);
    }

    @Override
    public void onCheckListener(Classes classes, boolean isCheck) {
        classes.setChecked(isCheck);
        mAdapter.notifyDataSetChanged();
    }
}
