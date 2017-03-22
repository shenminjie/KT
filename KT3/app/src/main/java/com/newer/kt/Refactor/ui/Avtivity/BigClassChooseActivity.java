package com.newer.kt.Refactor.ui.Avtivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.frame.app.base.activity.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.monkeyshen.recordlib.VideoCaptureActivity;
import com.monkeyshen.recordlib.VideoCaptureLitmitActivity;
import com.monkeyshen.recordlib.configuration.CaptureConfiguration;
import com.newer.kt.R;
import com.newer.kt.ktmatch.QueryBuilder;
import com.newer.kt.ui.dakejian.DakejianXuanzeAdapter;
import com.newer.kt.utils.DialogUtil;
import com.smj.LocalDataInfo;
import com.smj.LocalDataManager;
import com.smj.dakejian.DakejianBasicInfo;
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
    private DakejianXuanzeAdapter mAdapter;

    private ProgressDialog mLoading;

    private List<GradeInfo> mDatas;
    private List<Classes> mSelectClasses;
    private DakejianBasicInfo mDakejianBasicInfo;

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

        mDakejianBasicInfo = (DakejianBasicInfo) getIntent().getSerializableExtra("data");

        listView = getViewById(R.id.layout_select_class_list);
        mDatas = new ArrayList<>();
        mAdapter = new DakejianXuanzeAdapter(mDatas, this);
        listView.setAdapter(mAdapter);

        mLoading = new ProgressDialog(this);
        mLoading.show();
        getData();
    }


    /**
     * getdata
     */
    private void getData() {
        String clubid = "" + PreferenceManager.getDefaultSharedPreferences(this)
                .getLong(LoginActivity.PRE_CURRENT_CLUB_ID, 1);
        QueryBuilder.build("offline/get_club_school_class_data").add("club_id", clubid).get(new QueryBuilder.Callback() {
            @Override
            public void onSuccess(String result) {
                mLoading.dismiss();
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
                mLoading.dismiss();
            }

            @Override
            public void onDebug(RequestParams rp) {
                mLoading.dismiss();
            }
        });
    }


    @OnClick(R.id.tv_luzhi)
    public void luzhi() {
        if (mSelectClasses == null) {
            mSelectClasses = new ArrayList<>();
        }
        mSelectClasses.clear();
        for (GradeInfo gradeInfo : mDatas) {
            for (Classes classes : gradeInfo.getClasses()) {
                if (classes.isChecked()) {
                    mSelectClasses.add(classes);
                }
            }
        }
        if (mSelectClasses.size() == 0) {
            DialogUtil.showAlert(this, "注意", "请选择一个班级", "确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            return;
        }
        Log.e("选中的班级有这些:", mSelectClasses + "");
        Log.e("大课间对象:", mDakejianBasicInfo + "");
        VideoCaptureActivity.toActivity(this, CaptureConfiguration.getDefault(), System.currentTimeMillis() + "");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String filename = "";
        if (resultCode == Activity.RESULT_OK) {
            filename = data.getStringExtra(VideoCaptureLitmitActivity.EXTRA_OUTPUT_FILENAME);
            LocalDataInfo dakejianLocalData = new LocalDataInfo();
            dakejianLocalData.setDakejianClasses(mSelectClasses);
            dakejianLocalData.setCreatetime(System.currentTimeMillis());
            dakejianLocalData.setVideoPath(filename);
            dakejianLocalData.setDakejianBasicInfo(mDakejianBasicInfo);
            dakejianLocalData.setDakejianIs_finished(0);
            dakejianLocalData.setType(LocalDataInfo.TYPE_DAKEJIAN);

            LocalDataManager.saveUnUpLoadInfo(dakejianLocalData);
            DialogUtil.showAlert(this, "温馨提示", "录制成功，请到动态管理进行上传视频", "确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    finish();
                }
            });
        } else if (resultCode == Activity.RESULT_CANCELED) {
            filename = null;
        } else if (resultCode == VideoCaptureLitmitActivity.RESULT_ERROR) {
            filename = null;
        }
        Log.e("录制视频返回的对象,", filename + "");

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
