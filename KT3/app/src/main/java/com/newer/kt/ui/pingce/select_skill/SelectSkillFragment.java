package com.newer.kt.ui.pingce.select_skill;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frame.app.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.newer.kt.R;
import com.newer.kt.entity.OnItemListener;
import com.newer.kt.ktmatch.QueryBuilder;
import com.smj.gradlebean.GradeInfo;
import com.smj.skillbean.FootballSkillInfo;
import com.smj.skillbean.SkillInfo;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 */
public class SelectSkillFragment extends Fragment implements OnItemListener<SkillInfo> {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    SelectSkillAdapter mAdapter;


    public SelectSkillFragment() {
    }

    public static SelectSkillFragment newInstance(String param1, String param2) {
        SelectSkillFragment fragment = new SelectSkillFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_skill, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDatas = new ArrayList<>();
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mAdapter = new SelectSkillAdapter(mDatas, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(20, 15, 20, 15);
            }
        });

        initData();
    }

    private void initData() {
        QueryBuilder.build("shool_user_tests/get_football_skills").get(new QueryBuilder.Callback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.has("response") && "success".equals(jsonObject.getString("response"))) {
                        String dataString = jsonObject.getString("football_skills");
                        Gson gson = new Gson();
                        List<FootballSkillInfo> infos = gson.fromJson(dataString, new TypeToken<List<FootballSkillInfo>>() {
                        }.getType());
                        setData(infos);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onDebug(RequestParams rp) {

            }
        });
    }

    /**
     * setdata
     *
     * @param infos
     */
    private void setData(List<FootballSkillInfo> infos) {
        for (FootballSkillInfo skillInfo : infos) {
            for (SkillInfo skillInfo1 : skillInfo.getList()) {
                skillInfo1.setChecked(false);
                mDatas.add(skillInfo1);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    private List<SkillInfo> mDatas;

    private SkillInfo mSelectInfo;

    @Override
    public void onItemListener(SkillInfo skillInfo, int position) {
        if (mSelectInfo == skillInfo) {
            return;
        }
        int beforePosition = -1;
        for (int i = 0; i < mDatas.size(); i++) {
            if (mDatas.get(i).isChecked()) {
                mDatas.get(i).setChecked(false);
                beforePosition = i;
            }
        }
        if (beforePosition != -1) {
            mAdapter.notifyItemChanged(beforePosition);
        }
        skillInfo.setChecked(true);
        mAdapter.notifyItemChanged(position);
        mSelectInfo = skillInfo;
    }
}
