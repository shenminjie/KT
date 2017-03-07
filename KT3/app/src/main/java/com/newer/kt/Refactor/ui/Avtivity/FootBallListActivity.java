package com.newer.kt.Refactor.ui.Avtivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.Text;
import com.frame.app.base.activity.BaseActivity;
import com.frame.app.utils.GsonTools;
import com.frame.app.utils.SharedPreferencesUtils;
import com.newer.kt.R;
import com.newer.kt.Refactor.Constants;
import com.newer.kt.Refactor.Entitiy.Combinations;
import com.newer.kt.Refactor.Entitiy.SchoolGymCourseCombinationDetail;
import com.newer.kt.Refactor.Entitiy.SchoolGymCourseCombinations;
import com.newer.kt.Refactor.KTApplication;
import com.newer.kt.Refactor.Net.CallServer;
import com.newer.kt.Refactor.Net.HttpListener;
import com.newer.kt.Refactor.Request.SchoolGymCourseCombinationDetailRequest;
import com.newer.kt.Refactor.ui.Avtivity.FootballLesson.LessonListAvtivity;
import com.newer.kt.Refactor.utils.FootBallListManager;
import com.newer.kt.Refactor.view.CircleProgressView;
import com.newer.kt.Refactor.view.SlideDelete;
import com.newer.kt.fragment.peixun.FootBallFragment;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;

import butterknife.Bind;

/**
 * Created by leo on 16/11/10.
 */
public class FootBallListActivity extends BaseActivity {
    private RelativeLayout rl_ftclass1;
    private ImageView iv_ft_back;
//    @Bind(R.id.expanded_menu)
//    ExpandableListView mExpandableListView;
//    private SchoolGymCourseCombinations bean;
//    private List<String> name;
//    private List<List<Combinations>> conent = new ArrayList<>();
//    private List<CircleProgressView> mList = new ArrayList<>();

    String type;
    private Map<String, ArrayList> map;
    public static List semester;

    @Override
    protected void initHandler(Message msg) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_football_list);
        initEvent();
        type = getIntent().getStringExtra("type");
        map = new TreeMap(FootBallFragment.map);
        String key = getIntent().getStringExtra("key");
        for(Object k:new TreeSet(map.keySet())){
            if(!k.toString().startsWith(key)){
                map.remove(k);
            }
        }
        final List items = new ArrayList(map.keySet());
        ((ListView) findViewById(R.id.lv_zqk)).setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return map.size();
            }

            @Override
            public Object getItem(int i) {
                return items.get(i);
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(final int i, View view, ViewGroup viewGroup) {

                View convertView = LayoutInflater.from(getBaseContext()).inflate(R.layout.zqk_item, null);
                ((TextView) convertView.findViewById(R.id.name)).setText(items.get(i).toString());
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        semester =  map.get(items.get(i));
                        Intent intent=new Intent(getApplicationContext(),FootBall_Class_Lesson.class);
                        startActivity(intent);

                    }
                });
                return convertView;
            }
        });
    }

    private void initEvent() {
//        rl_ftclass1 = ((RelativeLayout) findViewById(R.id.rl_ftclass1));
        iv_ft_back = ((ImageView) findViewById(R.id.iv_ft_back));
        iv_ft_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void setListener() {
//        rl_ftclass1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),FootBall_Class_Lesson.class);
//                startActivity(intent);
//
//            }
//        });

//        iv_ft_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FootBallListActivity.this.finish();
//            }
//        });

    }

    @Override
    protected void initData(Bundle savedInstanceState) {


    }
//
//    @Override
//    protected void initData(Bundle savedInstanceState) {
//        String data = KTApplication.getSchoolGymCourseCombinations();
//        bean = GsonTools.changeGsonToBean(data, SchoolGymCourseCombinations.class);
//        name = new ArrayList<>();
//        for (int x = 0; x < bean.combinations.size(); x++) {
//            Combinations combinations = bean.combinations.get(x);
//            if (name.size() == 0) {
//                name.add(combinations.semester);
//                conent.add(new ArrayList<Combinations>());
//                conent.get(0).add(combinations);
//            } else {
//                if (!name.contains(combinations.semester)) {
//                    name.add(combinations.semester);
//                    conent.add(new ArrayList<Combinations>());
//                } else {
//                    conent.get(name.indexOf(combinations.semester)).add(combinations);
//                }
//            }
//        }

//       mExpandableListView.setAdapter(new BaseExpandableListAdapter() {
//
//
//            @Override
//            public int getGroupCount() {
//                return name.size();
//            }
//
//            @Override
//            public int getChildrenCount(int groupPosition) {
//                return conent.get(groupPosition).size();
//            }
//
//            @Override
//            public Object getGroup(int groupPosition) {
//                return name.get(groupPosition);
//            }
//
//            @Override
//            public Object getChild(int groupPosition, int childPosition) {
//                return conent.get(groupPosition).get(childPosition);
//            }
//
//            @Override
//            public long getGroupId(int groupPosition) {
//                return groupPosition;
//            }
//
//            @Override
//            public long getChildId(int groupPosition, int childPosition) {
//                return childPosition;
//            }
//
//            @Override
//            public boolean hasStableIds() {
//                return true;
//            }
//
//
//            @Override
//            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//                ParentView parentView;
//                if (convertView == null) {
//                    parentView = new ParentView();
//                    convertView = getLayoutInflater().inflate(R.layout.item_foot_parent, null);
//                    parentView.mTile = (TextView) convertView.findViewById(R.id.tv_title);
//                    parentView.mBag = (ImageView) convertView.findViewById(R.id.img);
//                    convertView.setTag(parentView);
//                } else {
//                    parentView = (ParentView) convertView.getTag();
//                }
//                parentView.mTile.setText(name.get(groupPosition));
//                if (isExpanded) {
//                    parentView.mBag.setImageResource(R.mipmap.nomal_up);
//                } else {
//                    parentView.mBag.setImageResource(R.mipmap.nomal_down);
//                }
//                return convertView;
//            }
//
//            @Override
//            public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//                final ChikldView parentView;
//                if (convertView == null) {
//                    parentView = new ChikldView();
//                    convertView = getLayoutInflater().inflate(R.layout.item_foot_content, null);
//                    parentView.mClass = (TextView) convertView.findViewById(R.id.tv_class);
//                    parentView.mTime = (TextView) convertView.findViewById(R.id.tv_timeid);
//                    parentView.mQiangdu = (TextView) convertView.findViewById(R.id.tv_qiangdu);
//                    parentView.mIsDownlad = (TextView) convertView.findViewById(R.id.tv_download);
//                    parentView.mContent = (TextView) convertView.findViewById(R.id.tv_content);
//                    parentView.linear_download = (RelativeLayout) convertView.findViewById(R.id.linear_download);
//                    parentView.mProgressView = (CircleProgressView) convertView.findViewById(R.id.progress);
//                    convertView.setTag(parentView);
//                } else {
//                    parentView = (ChikldView) convertView.getTag();
//                }
//                final String data = SharedPreferencesUtils.getString(getThis(), Constants.SCHOOLGYMCOURSECOMBINATIONDETAIL, conent.get(groupPosition).get(childPosition).id, "");
//                if (!"".equals(data)) {
//                    parentView.linear_download.setVisibility(View.GONE);
//                    parentView.mIsDownlad.setVisibility(View.VISIBLE);
//                } else {
//                    parentView.linear_download.setVisibility(View.VISIBLE);
//                    parentView.mIsDownlad.setVisibility(View.GONE);
//                }
//                parentView.mClass.setText(conent.get(groupPosition).get(childPosition).name);
//                parentView.mTime.setText(conent.get(groupPosition).get(childPosition).total_minutes + "分钟");
//                parentView.mContent.setText("课程简介 : " + conent.get(groupPosition).get(childPosition).intro);
//                parentView.mProgressView.setTag(conent.get(groupPosition).get(childPosition).id);
//                parentView.linear_download.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        parentView.mProgressView.setProgress(0);
//                        getSchoolGymCourseCombinationDetail(conent.get(groupPosition).get(childPosition).id, parentView.mProgressView, groupPosition);
//                    }
//                });
//                convertView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getThis(), FootBallDetailActivity.class);
//                        intent.putExtra("info", conent.get(groupPosition).get(childPosition));
//                        startActivity(intent);
//                    }
//                });
//                return convertView;
//            }
//
//            @Override
//            public boolean isChildSelectable(int groupPosition, int childPosition) {
//                return true;
//            }
//
//            class ParentView {
//                TextView mTile;
//                ImageView mBag;
//
//            }
//
//            class ChikldView {
//                TextView mClass;
//                TextView mTime;
//                TextView mQiangdu;
//                TextView mContent;
//                TextView mIsDownlad;
//                RelativeLayout linear_download;
//                CircleProgressView mProgressView;
//            }
//
//
//        });
//
//
//
//    }
//
//
//
//    public void doBack(View view) {
//        finish();
//
//    }
//
//
//    /**
//     * 大课间
//     *
//     * @param club_id       俱乐部ID
//     * @param mProgressView
//     */
//    private void getSchoolGymCourseCombinationDetail(final String club_id, final CircleProgressView mProgressView, final int groupId) {
//        SchoolGymCourseCombinationDetailRequest request = new SchoolGymCourseCombinationDetailRequest(Constants.GET_SCHOOL_GYM_COURSE_COMBINATION_DETAIL, RequestMethod.GET);
//        request.add("gym_course_combination", club_id);
//        CallServer.getRequestInstance().add((BaseActivity) getThis(), 0, request, new HttpListener<SchoolGymCourseCombinationDetail>() {
//            @Override
//            public void onSucceed(int what, Response<SchoolGymCourseCombinationDetail> response) {
//                if (response != null && response.get() != null && response.get().response.equals("success")) {
//                    mList.add(mProgressView);
//                    SharedPreferencesUtils.saveString(getThis(), Constants.SCHOOLGYMCOURSECOMBINATIONDETAIL, club_id + "", GsonTools.createGsonString(response.get()));
//                    new FootBallListManager(getThis(), response.get(), club_id, groupId, new FootBallListManager.OnDownloadListener() {
//                        @Override
//                        public void progress(int position, String id) {
//                            for (int i = 0; i < mList.size(); i++) {
//                                if (mList.get(i).getTag().equals(id)) {
//                                    mProgressView.setProgress(position);
//                                }
//                            }
//
//
//                        }
//
//                        @Override
//                        public void complete(String id, int position) {
//                            for (int i = 0; i < mList.size(); i++) {
//                                if (mList.get(i).getTag().equals(id)) {
//                                    mList.remove(i);
//                                }
//                            }
//
//
//                        }
//                    });
//                } else {
//
//                }
//            }
//
//            @Override
//            public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
//
//            }
//        }, false, false);
//    }


}
