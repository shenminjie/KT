package com.newer.kt.fragment.peixun;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.frame.app.base.fragment.BaseFragment;
import com.frame.app.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.internal.Streams;
import com.google.gson.reflect.TypeToken;
import com.newer.kt.R;
import com.newer.kt.Refactor.Constants;
import com.newer.kt.Refactor.ui.Avtivity.Main2Activity;
import com.newer.kt.Refactor.utils.MD5;
import com.newer.kt.Refactor.view.ChildViewpager;
import com.newer.kt.entity.jineng.JiNeng_Bean;
import com.newer.kt.entity.jineng.SkillResponse;
import com.newer.kt.ktmatch.QueryBuilder;
import com.newer.kt.ktmatch.json.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

/**
 * Created by leo on 16/11/9.
 */

public class JinengFramgent extends BaseFragment {
    @Bind(R.id.tv_all_class)
    TextView mAll_class;
    @Bind(R.id.tv_all_people)
    TextView mAll_people;
    @Bind(R.id.tv_all)
    TextView mAll;
    @Bind(R.id.tv_pingjun)
    TextView mAll_pingjun;


    @Bind(R.id.tv_all_nale)
    TextView tv_all_nale;//总技能教学数
    @Bind(R.id.tv_all_class_nale)
    TextView tv_all_class_nale;//总训练
    @Bind(R.id.tv_all_people_nale)
    TextView tv_all_people_nale;//总训练时长
    @Bind(R.id.tv_pingjun_nale)
    TextView tv_pingjun_nale;//平均分
    @Bind(R.id.gv_gridView)
    GridView getGv_gridView;

    private Typeface mTf;
    private GridView gv_gridView;
    private int imageIds[];
    private String strs[];


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jinengHomeTongji();
        System.out.println("123123");
        getJiNeng();
    }

    /**
     * smj
     */
    private List<SkillResponse> mSkillResponses;

    public static List<JiNeng_Bean> jineng_cat_data;
    public static List<Map> wikis;

    private void getJiNeng() {

        QueryBuilder.build("wikis/list").get(new QueryBuilder.EnhancedCallback("wikis") {
            @Override
            public void onSuccessWithObject(String namelink, Object object) {
                wikis = (ArrayList<Map>) object;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onDebug(RequestParams rp) {

            }
        });

        QueryBuilder.build("study/get_all_app_cartoons").get(new QueryBuilder.Callback() {


            @Override
            public void onSuccess(String result) {
//                jineng_cat_data = JsonUtil.fromJsonArray((String) JsonUtil.findJsonNode("app_cartoons",result));
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (!jsonObject.has("app_cartoons")) {
                        return;
                    }
                    String data = jsonObject.getString("app_cartoons");
                    Gson gson = new Gson();
                    mSkillResponses = gson.fromJson(data, new TypeToken<List<SkillResponse>>() {
                    }.getType());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                LogUtils.e("返回的数据:" + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onDebug(RequestParams rp) {

            }
        });
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_jineng);

//        gv_gridView = ((GridView) rootView.findViewById(R.id.gv_gridView));


    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {


        ////////////////////////////////
        mTf = Typeface.createFromAsset(getActivity().getAssets(), "BEBAS.ttf");

        mAll.setTypeface(mTf);
        mAll_pingjun.setTypeface(mTf);
        mAll_class.setTypeface(mTf);
        mAll_people.setTypeface(mTf);

        tv_all_nale.setText("总技能教学数");
        tv_pingjun_nale.setText("平均分");
        tv_all_class_nale.setText("总训练");
        tv_all_people_nale.setText("总训练时长");


        imageIds = new int[]{
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
        };
        strs = new String[]{
                "控球类",
                "素质教育",
                "传球类",
                "足球知识",
        };

        initInfo();
        initBG();
        initEvent();
        initOnclick();


    }

    private void initOnclick() {
        getGv_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mSkillResponses == null) {
                    return;
                }
                if (position == 3) {
//                    Intent intent=new Intent(getActivity(), JiNengFragment_List.class);
//                    startActivity(intent);
                }
                SkillResponse skillResponse = mSkillResponses.get(position);
                JiNengFragment_List.toAcitivty(getContext(), skillResponse);
            }
        });
    }

    private void initEvent() {
        getGv_gridView.setAdapter(new BaseAdapter() {
            private TextView image_title;
            private ImageView ig_jineng_tupian;


            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = View.inflate(getActivity(), R.layout.item_jineng_tupian, null);
                ig_jineng_tupian = ((ImageView) convertView.findViewById(R.id.ig_jineng_tupian));
                image_title = ((TextView) convertView.findViewById(R.id.image_title));

                ig_jineng_tupian.setImageResource(imageIds[position]);
                image_title.setText(strs[position]);


                return convertView;
            }
        });

    }

    private void initInfo() {


    }

    private void initBG() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void initHandler(Message msg) {

    }


    public class JinengView {
        ImageView mImageView;
        TextView mTitle;
    }


    //技能首页统计
    public void jinengHomeTongji() {
        String url = Constants.KTHOST + "study/home_tongji";
        RequestParams p = new RequestParams(url);
        p.addQueryStringParameter("authenticity_token", MD5.getToken(url));
        p.addQueryStringParameter("club_id", "89");

        x.http().get(p, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                Gson gson = new Gson();
                //1. 获得 解析者
                JsonParser parser = new JsonParser();

                //2. 获得 根节点元素
                JsonElement element = parser.parse(result);

                //3. 根据 文档判断根节点属于 什么类型的 Gson节点对象
                JsonObject root = element.getAsJsonObject();

                //4. 取得 节点 下 的某个节点的 value


                //total_study_skill_count: 总学习技能数
                JsonPrimitive total_study_skill_count_json = root.getAsJsonPrimitive("total_study_skill_count");
                String total_study_skill_count = total_study_skill_count_json.getAsString();

                //total_study_count: 总训练
                JsonPrimitive total_study_count_json = root.getAsJsonPrimitive("total_study_count");
                String total_study_count = total_study_count_json.getAsString();

                //total_study_finish_time: 总训练时长
                JsonPrimitive total_study_finish_time_json = root.getAsJsonPrimitive("total_study_finish_time");
                String total_study_finish_time = total_study_finish_time_json.getAsString();
                //average_scores: 平均分
                JsonPrimitive average_scores_json = root.getAsJsonPrimitive("average_scores");
                String average_scores = average_scores_json.getAsString();

                mAll.setText(total_study_skill_count);
                mAll_pingjun.setText(average_scores);
                mAll_class.setText(total_study_count);
                mAll_people.setText(total_study_finish_time);

                System.out.println("技能首页：总学习技能数：" + total_study_skill_count + " 总训练：" + total_study_count + " 总训练时长：" + total_study_finish_time + " 平均分:" + average_scores + "");


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
}
