package com.newer.kt.ui.upload;


import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newer.kt.R;
import com.newer.kt.entity.OnItemListener;
import com.newer.kt.utils.DialogUtil;
import com.smj.LocalDataInfo;
import com.smj.LocalDataManager;
import com.smj.upload.UpLoadBySmjService;
import com.smj.upload.UpLoadManager;

import org.json.JSONObject;

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

public class UpLoadFragment extends Fragment implements UpLoadAdapter.Callback, UploadListener, OnItemListener<LocalDataInfo> {
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDatas = LocalDataManager.getUnUploadCacheDatas();
        mAdapter = new UpLoadAdapter<>(mDatas, this, this);
        mViewMap = new HashMap<>();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        Intent intent = new Intent(getContext(), UpLoadBySmjService.class);
        getActivity().startService(intent);
        getActivity().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }

    /**
     * bingder
     */
    private UpLoadBySmjService.UpLoadBinder mBinder;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBinder = (UpLoadBySmjService.UpLoadBinder) iBinder;
            mBinder.setListener(UpLoadFragment.this);

            //instance show upload data
            if (mBinder.getUpLoadStatus() == UpLoadManager.STATUS_UPLOADING) {
                mRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        LocalDataInfo info = UpLoadManager.getInstance().getCurrentUpLoadingData();
                        if (info == null) {
                            return;
                        }
                        mViewMap.get(info.getId()).tvName.setText(info.getUpLoadName() + "(正在上传)");
                        mViewMap.get(info.getId()).progreebar.setProgress(UpLoadManager.getInstance().getUpLoadingProgress());
                    }
                });

            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @OnClick(R.id.btn)
    public void onClick() {
        if (mBinder == null) {
            return;
        }
        if (mBinder.getUpLoadStatus() == UpLoadManager.STATUS_UPLOADING) {
            DialogUtil.showAlert(getContext(), "温馨提示", "正在上传", "确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            return;
        }
        if (mDatas.size() == 0) {
            return;
        }
        List<LocalDataInfo> upLoadInfos = new ArrayList<>();
        for (LocalDataInfo localData : mDatas) {
            upLoadInfos.add(localData);
        }
        mBinder.startUpLoad(upLoadInfos);
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
        mViewMap.get(info.getId()).tvName.setText(info.getUpLoadName() + "(正在上传)");
        mViewMap.get(info.getId()).progreebar.setProgress(i);
    }

    @Override
    public void onStart(LocalDataInfo info) {
        mViewMap.get(info.getId()).tvName.setText(info.getUpLoadName() + "(开始上传)");
    }

    @Override
    public void onSuccess(JSONObject var1, LocalDataInfo info) {
        mViewMap.get(info.getId()).tvName.setText(info.getUpLoadName() + "(上传成功)");
        mViewMap.get(info.getId()).progreebar.setProgress(100);
        Log.e("tag------onSuccess", "var1:" + var1);
        Log.e("tag-----onSuccess", "info:" + var1);
    }


    @Override
    public void onFailure(JSONObject jsonObject, LocalDataInfo info) {
        mViewMap.get(info.getId()).tvName.setText(info.getUpLoadName() + "(上传失败)");
        mViewMap.get(info.getId()).progreebar.setProgress(0);
    }

    @Override
    public void onFinished(LocalDataInfo info) {

    }

    @Override
    public void commitSuccess(LocalDataInfo info) {
        mDatas.remove(info);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroy() {
        if (mBinder != null) {
            mBinder.setListener(null);
        }
        getActivity().unbindService(mConnection);
        super.onDestroy();
    }


    @Override
    public void onItemListener(LocalDataInfo localDataInfo, int position) {
        if (TextUtils.isEmpty(localDataInfo.getVideoPath())) {
            return;
        }
        final Intent videoIntent = new Intent(Intent.ACTION_VIEW);
        videoIntent.setDataAndType(Uri.parse(localDataInfo.getVideoPath()), "video/*");
        try {
            startActivity(videoIntent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}
