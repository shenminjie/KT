package com.newer.kt.Refactor.ui.Fragment.Main;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.frame.app.base.fragment.BaseFragment;
import com.frame.app.utils.PhoneUtils;
import com.google.gson.Gson;
import com.newer.kt.ActivityClass;
import com.newer.kt.InterfaceSample;
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
import com.newer.kt.Refactor.ui.Avtivity.Xjss.Constant;
import com.newer.kt.Refactor.utils.MD5;
import com.newer.kt.entity.Club_Info;
import com.newer.kt.ktmatch.MathChooseActivity;
import com.newer.kt.ktmatch.Params;
import com.newer.kt.ktmatch.QueryBuilder;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

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
    private LinearLayout linear_info;
    private TextView tv_shcool;
    public Club_Info clubInfo;
    private TextView tv_time;
    private ImageView image_head;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.layout_settings);
        layout_settings_item_1 = ((RelativeLayout) rootView.findViewById(R.id.layout_settings_item_1));
        layout_settings_item_2 = ((RelativeLayout) rootView.findViewById(R.id.layout_settings_item_2));
        layout_settings_item_3 = ((RelativeLayout) rootView.findViewById(R.id.layout_settings_item_3));
        linear_info = ((LinearLayout) rootView.findViewById(R.id.linear_info));
        tv_shcool = ((TextView) rootView.findViewById(R.id.tv_shcool));
        tv_time = ((TextView) rootView.findViewById(R.id.tv_time));
        image_head = ((ImageView) rootView.findViewById(R.id.image_head));


        new InterfaceSample(getBaseActivity()).get_club_data();
    }


    public static ArrayList<Map<String, String>> unlinkedStudents = new ArrayList<Map<String, String>>();

    @Override
    protected void setListener() {

        //班级管理
        layout_settings_item_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityClass.class);
                startActivity(intent);

            }
        });
        //学员管理
        layout_settings_item_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Stu_Manager.class);

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
        //我校资料
        linear_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), School_Info.class);
                startActivity(intent);
            }
        });


    }
    @Override
    public void onResume() {
        initData(null);
        super.onResume();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
final String end_date_xiaoyuan = "" + PreferenceManager.getDefaultSharedPreferences(getContext()).getString(LoginActivity.PRE_CURRENT_END_DATE_XIAOYUAN,"");

        tv_time.setText("有效日期: "+end_date_xiaoyuan);
        
        String url = Constants.KTHOST + "games/club_tongji";
        RequestParams param = new RequestParams(url);
        param.addQueryStringParameter("authenticity_token", MD5.getToken(url));

        final String clubid = "" + PreferenceManager.getDefaultSharedPreferences(getContext()).getLong(LoginActivity.PRE_CURRENT_CLUB_ID,0);

        final String clubname = "" + PreferenceManager.getDefaultSharedPreferences(getContext()).getString(LoginActivity.PRE_CURRENT_CLUB_NAME,"");
        tv_shcool.setText(clubname);
        param.addQueryStringParameter("club_id", clubid);
//        response: "success",
//                name: 校园名称,
//                school_student_count: 学生数,
//                avatar: 俱乐部头像,

//                higher_manager: 上级管理员

        x.http().get(param, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                 clubInfo=gson.fromJson(result,Club_Info.class);
                System.out.println(clubInfo.getResponse()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                Log.v("clubInfo",clubInfo.getResponse());


               ImageLoader.getInstance().displayImage(clubInfo.avatar,image_head);
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
