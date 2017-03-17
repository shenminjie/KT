package com.newer.kt.fragment.peixun;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.frame.app.base.fragment.BaseFragment;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.newer.kt.R;
import com.newer.kt.Refactor.Constants;
import com.newer.kt.Refactor.Entitiy.BigClassIndexData;
import com.newer.kt.Refactor.Entitiy.BigClassRoom;
import com.newer.kt.Refactor.ui.Avtivity.BigClassDetailActivity;
import com.newer.kt.Refactor.utils.MD5;
import com.newer.kt.adapter.BigClassAdater;
import com.newer.kt.entity.OnItemListener;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by leo on 16/11/9.
 */

public class BigClassFragment extends BaseFragment implements OnItemListener<BigClassRoom> {
    @Bind(R.id.tv_all_class)
    TextView mAll_class;
    @Bind(R.id.tv_all_people)
    TextView mAll_people;
    @Bind(R.id.tv_all)
    TextView mAll;
    @Bind(R.id.tv_pingjun)
    TextView mAll_pingjun;

    //    school_big_classroom_count: 总大课间数,
//                class_count: 总班级,
//                users_count: 总人数 
    @Bind(R.id.tv_all_nale)
    TextView tv_all_nale;//总大课间数
    @Bind(R.id.tv_all_class_nale)
    TextView tv_all_class_nale;//不显示
    @Bind(R.id.tv_all_people_nale)
    TextView tv_all_people_nale;//总人数
    @Bind(R.id.tv_pingjun_nale)
    TextView tv_pingjun_nale;//总班级

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private Typeface mTf;
    String user;
    String password;
    public static final String EXTRA_CLUB_ID = "club_id";
    public static final String EXTRA_CLUB_NAME = "club_name";
    public static final String PRE_CURRENT_USER_ID = "current_user_id";//提交比分时数据
    public static final String PRE_CURRENT_CLUB_ID = "current_club_id";
    public static final String PRE_CURRENT_CLUB_NAME = "current_club_name";
    public static final String PRE_CURRENT_TAYPE = "current_type";
    private BigClassIndexData indexData;

    //大课间列表
    private List<BigClassRoom> mList = new ArrayList<BigClassRoom>();
    private BigClassAdater bigClassAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_big_class);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTf = Typeface.createFromAsset(getActivity().getAssets(), "BEBAS.ttf");

        //mAll.setText(indexData.getSchool_big_classroom_count());
        // mAll_people.setText(indexData.getUsers_count());
        // mAll_class.setText(indexData.getClass_count());

        mAll.setTypeface(mTf);
        mAll_pingjun.setTypeface(mTf);
        mAll_class.setTypeface(mTf);
        mAll_people.setTypeface(mTf);

        tv_all_nale.setText("总大课间");
        tv_pingjun_nale.setText("总班级");
        tv_all_class_nale.setText("");
        tv_all_people_nale.setText("总人数");


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bigClassAdapter = new BigClassAdater(getActivity(), mList);
        bigClassAdapter.setListener(this);
        mRecyclerView.setAdapter(bigClassAdapter);


        getSchoolBigClassRooms();


    }

    //大课间列表  http://api.ktfootball.com/school_big_class_rooms/list
    public void getSchoolBigClassRooms() {


        String url = Constants.KTHOST + "school_big_class_rooms/list";
        RequestParams p = new RequestParams(url);
//        p.addQueryStringParameter("authenticity_token", "K9MpaPMdj0jij2m149sL1a7TcYrWXmg5GLrAJDCNBx8");
        p.addQueryStringParameter("authenticity_token", MD5.getToken(url));
        x.http().get(p, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
//                showDialogToast(result);


                Gson gson = new Gson();
                //1. 获得 解析者
                JsonParser parser = new JsonParser();

                //2. 获得 根节点元素
                JsonElement element = parser.parse(result);

                //3. 根据 文档判断根节点属于 什么类型的 Gson节点对象
                JsonObject root = element.getAsJsonObject();

                //4. 取得 节点 下 的某个节点的 value
                JsonPrimitive flagjson = root.getAsJsonPrimitive("response");
                String flag = flagjson.getAsString();

                if ("success".equals(flag)) {
                    JsonArray list = root.getAsJsonArray("list");
                    List<BigClassRoom> data = gson.fromJson(list, new TypeToken<List<BigClassRoom>>() {
                    }.getType());
                    mList.clear();
                    mList.addAll(data);
                    bigClassAdapter.notifyDataSetChanged();
                }
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

    public void initData() {
        String url = Constants.KTHOST + "school_big_class_rooms/home_tongji";
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
                JsonPrimitive flagjson = root.getAsJsonPrimitive("response");
                String flag = flagjson.getAsString();

                if ("success".equals(flag)) {
                    JsonPrimitive school_big_classroom_count_json = root.getAsJsonPrimitive("school_big_classroom_count");
                    String school_big_classroom_count = school_big_classroom_count_json.getAsString();
                    JsonPrimitive class_count_json = root.getAsJsonPrimitive("class_count");
                    String class_count = class_count_json.getAsString();
                    JsonPrimitive users_count_json = root.getAsJsonPrimitive("users_count");
                    String users_count = users_count_json.getAsString();

                    System.out.println("首页数据获取成功：大课间数：" + school_big_classroom_count + " 总班级：" + class_count + " 总人数：" + users_count);

                    mAll.setText(school_big_classroom_count);
                    mAll_people.setText(users_count);
                    mAll_pingjun.setText(class_count);
                    mAll_class.setText("");
                }


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
    public void onDestroy() {
        super.onDestroy();
        indexData = null;
    }

    @Override
    public void onItemListener(BigClassRoom bigClassRoom, int position) {
        Intent intent = new Intent(getContext(), BigClassDetailActivity.class);
        intent.putExtra("shool_big_classroom_id", bigClassRoom.getShool_big_classroom_id());
        intent.putExtra("data", bigClassRoom);
        startActivity(intent);
    }
}