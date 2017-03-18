package com.newer.kt.ui.pingce.pingce_obj;

import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.frame.app.view.LoadingDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.newer.kt.R;
import com.newer.kt.Refactor.ui.Avtivity.LoginActivity;
import com.newer.kt.entity.OnItemListener;
import com.newer.kt.ktmatch.QueryBuilder;
import com.newer.kt.utils.DialogUtil;
import com.smj.event.NextStepEvent;
import com.smj.gradlebean.Classes;
import com.smj.gradlebean.GradeInfo;
import com.smj.gradlebean.Users;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * pingce 选择对象
 */
public class PingceObjFragment extends Fragment implements OnItemListener<Classes>, ClassAdapter.OnCheckListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private ClassAdapter mAdapter;

    /**
     * datas
     */
    private List<Classes> mClassDatas;

    private LoadingDialog mLoading;

    public PingceObjFragment() {
    }

    public static PingceObjFragment newInstance(String param1, String param2) {
        PingceObjFragment fragment = new PingceObjFragment();
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
        View view = inflater.inflate(R.layout.fragment_pingce_obj, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLoading = new LoadingDialog(getContext());
        mClassDatas = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ClassAdapter(mClassDatas, this, this);
        mRecyclerView.setAdapter(mAdapter);

        DialogUtil.show(mLoading);

        String clubid = "" + PreferenceManager.getDefaultSharedPreferences(getContext())
                .getLong(LoginActivity.PRE_CURRENT_CLUB_ID, 1);
        QueryBuilder.build("offline/get_club_school_class_data").add("club_id", clubid).get(new QueryBuilder.Callback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.has("response") && "success".equals(jsonObject.getString("response"))) {
                        String dataString = jsonObject.getString("grade_list");
                        Gson gson = new Gson();
                        List<GradeInfo> gradeInfos = gson.fromJson(dataString, new TypeToken<List<GradeInfo>>() {
                        }.getType());
                        initData(gradeInfos);
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
     * initData
     *
     * @param gradeInfos
     */
    private void initData(List<GradeInfo> gradeInfos) {
        for (GradeInfo gradeInfo : gradeInfos) {
            for (Classes classes : gradeInfo.getClasses()) {
                classes.setGrade(gradeInfo.getGrade());
                for (Users users : classes.getUsers()) {
                    users.setClzId(gradeInfo.getGrade() + "" + classes.getCls());
                }
                mClassDatas.add(classes);
            }
        }
        mAdapter.notifyDataSetChanged();

        DialogUtil.dismiss(mLoading);
    }

    @Override
    public void onItemListener(Classes classes, int position) {
        classes.setExpand(!classes.isExpand());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCheckListener(boolean isCheck, Classes classes, int position) {
        classes.setChecked(!classes.isChecked());
        for (Users users : classes.getUsers()) {
            users.setChecked(classes.isChecked());
        }
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void onStudentCheck(boolean isCheck, Users users, RecyclerView.Adapter adapter, int position) {
        users.setChecked(!users.isChecked());
        adapter.notifyItemChanged(position);
    }

    @OnClick(R.id.btn_next)
    public void onClick() {
        //遍历数据
        Map<String, Users> map = new HashMap<>();
        List<Users> usersList = new ArrayList<>();
        Classes clz=null;
        //先遍历，是否是同一个clz
        for (Classes classes : mClassDatas) {
            for (Users users : classes.getUsers()) {
                if (users.isChecked()) {
                    Log.e("tag", users.getClzId() + "");
                    if (!map.containsKey(users.getClzId())) {
                        map.put(users.getClzId(), users);
                        clz=classes;
                    }
                    usersList.add(users);
                    //大于1，不用操作，只能一个班级
                    if (map.size() > 1) {
                        DialogUtil.showAlert(getContext(), "提示", "最多只能选择一个班级", "确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        return;
                    }
                }
            }
        }
        if (usersList.size() > 8) {
            DialogUtil.showAlert(getContext(), "提示", "最多选择8个学生", "确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            return;
        }
        if (usersList.size() == 0) {
            DialogUtil.showAlert(getContext(), "提示", "至少选择一个学生", "确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            return;
        }
        EventBus.getDefault().post(new NextStepEvent(1, usersList, clz));
    }
}
