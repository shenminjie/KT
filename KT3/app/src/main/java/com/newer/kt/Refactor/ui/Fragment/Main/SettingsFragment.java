package com.newer.kt.Refactor.ui.Fragment.Main;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.frame.app.base.fragment.BaseFragment;
import com.frame.app.utils.PhoneUtils;
import com.newer.kt.ActivityClass;
import com.newer.kt.R;
import com.newer.kt.Refactor.Constants;
import com.newer.kt.Refactor.db.BagsDaoHelper;
import com.newer.kt.Refactor.ui.Avtivity.ListActivity;
import com.newer.kt.Refactor.ui.Avtivity.LoginActivity;
import com.newer.kt.Refactor.ui.Avtivity.Settings.AboutKTActivity;
import com.newer.kt.Refactor.ui.Avtivity.Settings.CampusStatisticsActivity;
import com.newer.kt.Refactor.ui.Avtivity.Settings.HelpActivity;
import com.newer.kt.Refactor.ui.Avtivity.Settings.MineSchoolInfoActivity;
import com.newer.kt.Refactor.ui.Avtivity.Settings.SchoolEquipmentActivity;
import com.newer.kt.Refactor.ui.Avtivity.Settings.SchoolStatisticsActivity;
import com.newer.kt.ktmatch.MathChooseActivity;
import com.newer.kt.ktmatch.QueryBuilder;

import org.xutils.http.RequestParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by jy on 16/9/14.
 */
public class SettingsFragment extends BaseFragment {

    private RelativeLayout layout_settings_item_1;
    private RelativeLayout layout_settings_item_2;
    private RelativeLayout layout_settings_item_3;
    public static List<Map<String, String>> rt;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.layout_settings);
        layout_settings_item_1 = ((RelativeLayout) rootView.findViewById(R.id.layout_settings_item_1));
        layout_settings_item_2 = ((RelativeLayout) rootView.findViewById(R.id.layout_settings_item_2));
        layout_settings_item_3 = ((RelativeLayout) rootView.findViewById(R.id.layout_settings_item_3));


        String clubid = "" + PreferenceManager.getDefaultSharedPreferences(getThis())
                .getLong(LoginActivity.PRE_CURRENT_CLUB_ID, 1);
        QueryBuilder.build("offline/get_club_data").add("club_id", clubid).get(new QueryBuilder.EnhancedCallback("users") {
            @Override
            public void onSuccessWithObject(String namelink, Object object) {

                rt = (List<Map<String, String>>) object;

                for(Map<String, String> m:rt){
                    if(m.get("school_cls").toString().equals("null")||m.get("school_grade").toString().equals("null")){
                    unlinkedStudents.add((Map<String, String>) m);
                    }
                }
                int i = unlinkedStudents.size();
                i++;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onDebug(RequestParams rp) {

            }

        });
    }


    public static ArrayList<Map<String,String>> unlinkedStudents = new ArrayList<Map<String, String>>();

    @Override
    protected void setListener() {

        //班级管理
        layout_settings_item_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),ActivityClass.class);
                startActivity(intent);

            }
        });
        //学员管理
        layout_settings_item_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Stu_Manager.class);

                startActivity(intent);

            }
        });

        //动态管理
        layout_settings_item_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DongtaiGuanli.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void initHandler(Message msg) {

    }

    /**
     * 点击进入学生or气场or赛事列表页面
     */
    private void doStartActivity() {
        Intent intent = new Intent(getThis(), ListActivity.class);
        intent.putExtra(Constants.EXTRA_LIST_CODE, 2);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.TO_CAMPUSSTATISTICSACTIVITY && resultCode == Constants.CAMPUSSTATISTICS_BACK) {
            getThis().finish();
        }
    }
}
