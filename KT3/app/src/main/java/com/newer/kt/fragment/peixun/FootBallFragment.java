package com.newer.kt.fragment.peixun;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.frame.app.base.fragment.BaseFragment;
import com.newer.kt.R;
import com.newer.kt.Refactor.ui.Avtivity.FootBallListActivity;
import com.newer.kt.ktmatch.QueryBuilder;

import org.xutils.http.RequestParams;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by leo on 16/11/9.
 */

public class FootBallFragment extends BaseFragment {
    @Bind(R.id.tv_all_class)
    TextView mAll_class;
    @Bind(R.id.tv_all_people)
    TextView mAll_people;
    @Bind(R.id.tv_all)
    TextView mAll;
    @Bind(R.id.tv_pingjun)
    TextView mAll_pingjun;
    @Bind(R.id.tv_all_nale)
    TextView tv_all_nale;
    private Typeface mTf;
    public static Map map;
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_footbal);
        getRootView().findViewById(R.id.image_view1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),FootBallListActivity.class).putExtra("key","幼儿园"));

            }
        }
    );
        QueryBuilder.build("school_gym_courses/combinations").get(new QueryBuilder.EnhancedCallback("list[group:semester]") {
            @Override
            public void onSuccessWithObject(String namelink, Object object) {
                map = (Map) object;
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
    protected void setListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTf = Typeface.createFromAsset(getActivity().getAssets(), "BEBAS.ttf");
        mAll.setTypeface(mTf);
        mAll_pingjun.setTypeface(mTf);
        mAll_class.setTypeface(mTf);
        mAll_people.setTypeface(mTf);
        tv_all_nale.setText("总足球课数");
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void initHandler(Message msg) {

    }

    @OnClick(R.id.image_view)
    public void goDetail(){
        startActivity(new Intent(getContext(),FootBallListActivity.class).putExtra("key","小学"));

    }
}
