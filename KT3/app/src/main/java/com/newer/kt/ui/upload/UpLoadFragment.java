package com.newer.kt.ui.upload;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frame.app.utils.LogUtils;
import com.newer.kt.R;
import com.newer.kt.Refactor.utils.Toast;
import com.newer.kt.ktmatch.QueryBuilder;
import com.smj.LocalDataInfo;
import com.smj.LocalDataManager;
import com.smj.PingceLocalData;
import com.smj.gradlebean.Users;
import com.smj.upload.UpLoadInfo;
import com.smj.upload.UpLoadManager;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.vov.vitamio.utils.Log;

/**
 * client_id： 5570b4b24049e570
 * client_secret： c40bcc367334ef63e42ef4562b460e7f
 */

public class UpLoadFragment extends Fragment implements UpLoadAdapter.Callback, UploadListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.btn)
    TextView mBtn;

    private String mParam1;
    private String mParam2;
    private String mToken;


    UpLoadAdapter<LocalDataInfo> mAdapter;
    List<LocalDataInfo> mDatas;
    HashMap<String, UpLoadAdapter.ViewHolder> mViewMap;

    public UpLoadFragment() {
    }

    public static UpLoadFragment newInstance(String param1, String param2) {
        UpLoadFragment fragment = new UpLoadFragment();
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
        View view = inflater.inflate(R.layout.fragment_up_load, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void setToken(String token) {
        this.mToken = token;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDatas = LocalDataManager.getCacheDatas();
        Log.e("tag", mDatas + "");
        mAdapter = new UpLoadAdapter<>(mDatas, this);
        mViewMap = new HashMap<>();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void upLoad() {

    }

    @OnClick(R.id.btn)
    public void onClick() {
        if (true) {
            List<LocalDataInfo> mUploadList = new ArrayList<>();
            LocalDataManager.saveUpLoadList(mUploadList);
        }
        if (mDatas.size() == 0) {
            return;
        }
        if (TextUtils.isEmpty(mToken)) {
            return;
        }
        if (UpLoadManager.getInstance().getStatus() == UpLoadManager.STATUS_UPLOADING) {
            Toast.show(getContext(), "正在上传");
            return;
        }
        //data
        List<LocalDataInfo> upLoadInfos = new ArrayList<>();
        for (LocalDataInfo localData : mDatas) {
            upLoadInfos.add(localData);
        }
        UpLoadManager.getInstance().start(upLoadInfos, mToken, this);
    }

    @Override
    public void bindViewHolder(UpLoadAdapter.ViewHolder viewHolder, int position) {
        String id = mDatas.get(position).getId();
        if (mViewMap.containsKey(id)) {
            mViewMap.remove(id);
        }
        mViewMap.put(id, viewHolder);
    }

    @Override
    public void onProgressUpdate(int i, LocalDataInfo info) {
        Log.e("smj", i + "  " + info);
        mViewMap.get(info.getId()).progreebar.setProgress(i);
    }

    @Override
    public void onStart(LocalDataInfo info) {
        mViewMap.get(info.getId()).tvName.setText(info.getUpLoadName() + "(开始上传)");
    }

    @Override
    public void onSuccess(JSONObject var1, LocalDataInfo info) {
        //pingce的
        if (info instanceof LocalDataInfo) {
            commit(var1, info);
        }
    }


    @Override
    public void onFailure(JSONObject jsonObject, LocalDataInfo info) {
        mViewMap.get(info.getId()).tvName.setText(info.getUpLoadName() + "(上传失败)");
    }

    @Override
    public void onFinished(LocalDataInfo info) {
        mViewMap.get(info.getId()).tvName.setText(info.getUpLoadName() + "(上传完成)");
    }


    /**
     * 上传后提交
     *
     * @param var1
     * @param info
     */
    private void commit(JSONObject var1, LocalDataInfo info) {
        //上传数据
        try {
            String videoId = var1.getString("video_id");
            final LocalDataInfo data =  info;
            List<Users> students = data.getPingceStudent();
            StringBuilder builder = new StringBuilder();
            for (Users users : students) {
                builder.append(users.getUser_id() + ",");
            }
            QueryBuilder.build("shool_user_tests/save_user_skill_test_record_video_url")
                    .add("user_skill_test_record_id", builder.toString())
                    .add("video_url", videoId)
                    .post(new QueryBuilder.Callback() {
                        @Override
                        public void onSuccess(String result) {
                            LogUtils.e("result--smj:" + result + "");
                            remove(data);
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {

                        }

                        @Override
                        public void onDebug(RequestParams rp) {

                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void remove(LocalDataInfo info) {
        mDatas.remove(info);
        LocalDataManager.saveUpLoadList(mDatas);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        //cancle upload queue
        if (UpLoadManager.getInstance() != null) {
            UpLoadManager.getInstance().cancle();
        }
        super.onDestroy();
    }

    /**
     * is upload?
     *
     * @return
     */
    public boolean isUploading() {
        if (UpLoadManager.getInstance().getStatus() == UpLoadManager.STATUS_UPLOADING
                && mDatas.size() != 0) {
            return true;
        }
        return false;
    }
}
