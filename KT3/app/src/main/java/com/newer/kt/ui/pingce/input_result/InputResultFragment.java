package com.newer.kt.ui.pingce.input_result;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newer.kt.R;
import com.newer.kt.utils.DialogUtil;
import com.smj.LocalDataInfo;
import com.smj.LocalDataManager;
import com.smj.PingceLocalData;
import com.smj.gradlebean.Classes;
import com.smj.gradlebean.Users;
import com.smj.skillbean.SkillInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.smj.LocalDataInfo.TYPE_PINGCE;

/**
 */
public class InputResultFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private String mParam1;
    private String mParam2;

    private List<Users> mDatas;
    private SkillInfo mSkillInfo;
    private String mPath;
    private Classes mClz;
    private InputResultAdapter mAdapter;


    public void setDatas(List<Users> datas, SkillInfo skillInfo, String path, Classes clz) {
        mDatas.clear();
        mDatas.addAll(datas);
        mAdapter.notifyDataSetChanged();

        mSkillInfo = skillInfo;
        mPath = path;
        mClz=clz;
    }


    public InputResultFragment() {
    }

    public static InputResultFragment newInstance(String param1, String param2) {
        InputResultFragment fragment = new InputResultFragment();
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
        View view = inflater.inflate(R.layout.fragment_input_result, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDatas = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new InputResultAdapter(mDatas);
        mRecyclerView.setAdapter(mAdapter);
    }

    @OnClick(R.id.btn_save)
    public void onSave(){
        for(Users users:mDatas){
            if(TextUtils.isEmpty(users.getScord())){
                DialogUtil.showAlert(getContext(), "温馨提示", "请输入分数", "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                return;
            }
        }
        //保存到本地
        try{
            LocalDataInfo pingceLocalData=new LocalDataInfo();
            pingceLocalData.setPingceClz(mClz);
            pingceLocalData.setCreatetime(System.currentTimeMillis());
            pingceLocalData.setPingceSkillInfo(mSkillInfo);
            pingceLocalData.setPingceStudent(mDatas);
            pingceLocalData.setVideoPath(mPath);
            pingceLocalData.setType(TYPE_PINGCE);
            LocalDataManager.saveUpLoadInfo(pingceLocalData);

        }catch (Exception e){
            e.printStackTrace();
        }

        //保存成功，结束
        DialogUtil.showAlert(getContext(), "温馨提示", "保存成功，请到动态管理上传视频", "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                getActivity().finish();
                Log.e("tag--缓存数据",LocalDataManager.getCacheDatas()+"");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
