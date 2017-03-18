package com.newer.kt.fragment.peixun;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.frame.app.base.fragment.BaseFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.newer.kt.R;
import com.newer.kt.Refactor.Constants;
import com.newer.kt.Refactor.Entitiy.TestIndexData;
import com.newer.kt.Refactor.Entitiy.TestPaiMingBean;
import com.newer.kt.Refactor.ui.Avtivity.LoginActivity;
import com.newer.kt.Refactor.ui.Avtivity.TestListActivity;
import com.newer.kt.Refactor.ui.Fragment.Main.ManagerFragment;
import com.newer.kt.Refactor.utils.MD5;
import com.newer.kt.Refactor.view.LoadMoreRecyclerView;
//import com.newer.kt.adapter.TestAdater;
import com.newer.kt.entity.Shool_user_tests_Bean;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by leo on 16/11/9.
 */

public class TestFragment extends BaseFragment {
    // @Bind(R.id.tv_all_people)
    //  TextView mAll_people;
//    @Bind(R.id.tv_all)
//    TextView mAll;
//    @Bind(R.id.tv_pingjun)
//    TextView mAll_pingjun;
//    // @Bind(R.id.tv_all_nale)
//    TextView tv_all_nale;
//    LoadMoreRecyclerView mRecyclerView;
//    @Bind(R.id.lv_test_list)

//    private Typeface mTf;
//
//    ImageView mImageView;

//    SkillInfo<String> mList = new ArrayList<>();
    //SkillInfo<TestPaiMingBean> mList1 = new ArrayList<TestPaiMingBean>();
    // @Bind(R.id.appbar)
    //AppBarLayout appbar;
    //private ListView lv_test_list;


    private BaseAdapter adapter;
    ViewHolder viewHolder;
    private ListView lv_test_list;
    //  private Shool_user_tests_Bean shool_user_tests_bean;
    final ArrayList<Shool_user_tests_Bean.Shool_user_tests> shool_user_tests_list = new ArrayList<Shool_user_tests_Bean.Shool_user_tests>();
    private ImageView image_top;
    private TextView tv_cont;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDa();
        initData2();


    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_test);
        initDa();
        initAdapter();
        initView1();


    }

    private void initAdapter() {
        adapter = new BaseAdapter() {


            private ImageView image_top;
            private TextView tv_cont;

            @Override
            public int getCount() {
                return shool_user_tests_list.size();
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
                convertView = rootView.inflate(getActivity(), R.layout.item_test, null);
                viewHolder = new ViewHolder();
                viewHolder.image_head_test = ((ImageView) convertView.findViewById(R.id.image_head_test));
                viewHolder.tv_name_test = ((TextView) convertView.findViewById(R.id.tv_name_test));
                viewHolder.tv_gradle_test = ((TextView) convertView.findViewById(R.id.tv_gradle_test));
                viewHolder.tv_dongzuo_test = ((TextView) convertView.findViewById(R.id.tv_dongzuo_test));
                viewHolder.tv_all_cont_test = ((TextView) convertView.findViewById(R.id.tv_all_cont_test));

                Shool_user_tests_Bean.Shool_user_tests shool_bean = shool_user_tests_list.get(position);
                viewHolder.tv_name_test.setText(shool_bean.nickname + "");
                viewHolder.tv_gradle_test.setText(shool_bean.cls + "");
                viewHolder.tv_dongzuo_test.setText(shool_bean.skill_count);
                viewHolder.tv_all_cont_test.setText(shool_bean.total_scores);
                tv_cont = ((TextView) convertView.findViewById(R.id.tv_cont));
                image_top = ((ImageView) convertView.findViewById(R.id.image_top));
                tv_cont.setText(position + 1 + "");
                if (tv_cont.getText().equals("1")) {
                    image_top.setVisibility(View.VISIBLE);
                    tv_cont.setVisibility(View.GONE);
                }
                if (tv_cont.getText().equals("2")) {
                    int a=R.drawable.top_2;
                    image_top.setImageResource(a);
                    image_top.setVisibility(View.VISIBLE);
                    tv_cont.setVisibility(View.GONE);
                }
                if (tv_cont.getText().equals("3")) {
                    int b=R.drawable.top_3;
                    image_top.setImageResource(b);
                    image_top.setVisibility(View.VISIBLE);
                    tv_cont.setVisibility(View.GONE);
                }



                Glide.with(getActivity()).load(shool_bean.avatar).into(viewHolder.image_head_test);

                return convertView;
            }
        };
    }

    private void initView1() {
        lv_test_list = ((ListView) rootView.findViewById(R.id.lv_test_list));


        initDa();

        lv_test_list.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }


    private void initDa() {


    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
       /* mTf = Typeface.createFromAsset(getActivity().getAssets(), "BEBAS.ttf");
        mAll.setTypeface(mTf);
        mAll_pingjun.setTypeface(mTf);
        mAll_people.setTypeface(mTf);*/

//
//        mRecyclerView.setLoadMoreListener(this);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mList.add("dasdasdas");
//        mList.add("dasdasdas");
//        mList.add("dasdasdas");
//        mList.add("dasdasdas");
//        mList.add("dasdasdas");
//        mList.add("dasdasdas");
//        mList.add("dasdasdas");
//        mList.add("dasdasdas");
//        mList.add("dasdasdas");
//        mRecyclerView.setAdapter(new TestAdater(mList));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            appbar.setOutlineProvider(null);
//        }
    }

    //加载测评页面排名数据
    private void initData2() {
        String url = Constants.KTHOST + "shool_user_tests/school_football_skill_test_ranking";
        RequestParams p = new RequestParams(url);
        p.addQueryStringParameter("authenticity_token", MD5.getToken(url));


        final String clubid = "" + PreferenceManager.getDefaultSharedPreferences(getContext()).getLong(LoginActivity.PRE_CURRENT_CLUB_ID, 1);

        p.addQueryStringParameter("club_id", clubid);
        x.http().get(p, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
//                TestPaiMingBean testPaiMingBean = new Gson().fromJson(result.toString(),new TypeToken<TestPaiMingBean>(){}.getType());
//                System.out.println("----123"+testPaiMingBean.toString());
                 /*mAll_people.setText(testIndexData.getUsers_count());
                 mAll_pingjun.setText(testIndexData.getAverage_scores());*/
                Gson gson = new Gson();
                Shool_user_tests_Bean shool_user_tests_Bean = gson.fromJson(result, Shool_user_tests_Bean.class);
                shool_user_tests_list.clear();
                shool_user_tests_list.addAll(shool_user_tests_Bean.rankings);
                adapter.notifyDataSetChanged();
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

    @OnClick(R.id.tv_start)
    public void start() {
        Intent intent = new Intent(getThis(), TestListActivity.class);
        startActivity(intent);

    }


    public static class ViewHolder {
        private ImageView image_head_test;
        private TextView tv_name_test;
        private TextView tv_gradle_test;
        private TextView tv_dongzuo_test;
        private TextView tv_all_cont_test;
    }
}
