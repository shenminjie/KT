package com.newer.kt;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.frame.app.base.activity.BaseActivity;
import com.newer.kt.Refactor.ui.Fragment.Main.Class_StuManager;
import com.newer.kt.ktmatch.MathChooseActivity;
import com.newer.kt.ktmatch.QueryBuilder;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import org.xutils.http.RequestParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.newer.kt.Refactor.ui.Avtivity.LoginActivity.PRE_CURRENT_CLUB_ID;

/**
 * Created by bajieaichirou on 17/3/5.
 */
public class ActivityClass extends BaseActivity {

    ExpandableListView mListView;

    private List<String> list_grade;
    private ListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        mListView = (ExpandableListView) findViewById(R.id.list_view);
        ButterKnife.bind(this);
        initData(null);


        mListView.setGroupIndicator(null);

        mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()
        {
            @SuppressLint("NewApi")
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id)
            {
                if (list_grade.get(groupPosition).isEmpty()) {
                    return true;
                }
                return false;
            }
        });

        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
            {

//                Toast.makeText(ActivityClass.this, "group = " + list_grade.get(groupPosition) +
//                        "  child = " + list.get(groupPosition).getChildList().get(childPosition).get("grade") +
//                                list.get(groupPosition).getChildList().get(childPosition).get("num")
//                        , Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }

    @Override
    protected void initHandler(Message msg) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {

    }
    List list;
    @Override
    protected void initData(Bundle savedInstanceState) {

        String clubid = ""+ PreferenceManager.getDefaultSharedPreferences(getBaseContext())
                .getLong(PRE_CURRENT_CLUB_ID,1);
        QueryBuilder.build("offline/get_club_school_class_data").add("club_id",clubid).get(new QueryBuilder.EnhancedCallback("grade_list") {
            @Override
            public void onSuccessWithObject(String namelink, Object object) {
                list = (List) object;
                setData();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onDebug(RequestParams rp) {

            }
        });
    }
String[] grade = new String[]{"小班","中班","大班","一年级","二年级","三年级","四年级","五年级","六年级","初一","初二"};
    public void setData() {
        list_grade = new ArrayList<String>();
//        List rt = new ArrayList();
for(Object map :list){
    int i = Integer.parseInt(((Map)map).get("grade").toString())-1;
    String g = grade[i];
    list_grade.add(g);
//    List<Map> clses = (List<Map>) ((Map)map).get("classes");
////    List users = (List) ((Map)map).get("users");
//    for(Map mm:clses){
//        Map<String,Object> m = new TreeMap<String,Object>();
//        List urs = (List) mm.get("users");
//        m.put("grade", mm.get("cls") + "班");
//        m.put("num", urs.size() + "人");
//        m.put("users",urs);
//        rt.add(m);
//
    }
//}

            mAdapter = new ListAdapter(this, list, list_grade);
            mListView.setAdapter(mAdapter);
    }
    public class ListAdapter extends BaseExpandableListAdapter {

        private Context context;
        private List list;
        private List<String> grade_list;

        public ListAdapter(Context context, List list, List<String> gradeList) {
            this.context = context;
            this.list = list;
            this.grade_list = gradeList;
        }


        @Override
        public int getGroupCount() {
            return grade_list.size();
        }


        @Override
        public int getChildrenCount(int groupPosition) {
            return ((Map)list.get(groupPosition)).size();
        }


        @Override
        public Object getGroup(int groupPosition) {
            return grade_list.get(groupPosition);
        }


        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return ((List)((Map)list.get(groupPosition)).get("classes")).get(childPosition);
        }


        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }


        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }


        @Override
        public boolean hasStableIds() {
            return true;
        }


        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupHolder groupHolder = null;
            if (convertView == null) {
                convertView = new LayoutInflater(context){
                    @Override
                    protected View onCreateView(String name, AttributeSet attrs) throws ClassNotFoundException {
                        View view = null;
                        if (name.toLowerCase().contains("framelayout"))
                        {
                            view = new AutoFrameLayout(context, attrs);
                        }

                        if (name.toLowerCase().contains("linearlayout"))
                        {
                            view = new AutoLinearLayout(context, attrs);
                        }

                        if (name.toLowerCase().contains("relativelayout"))
                        {
                            view = new AutoRelativeLayout(context, attrs);
                        }

                        if (view != null) return view;
                        if(!name.toLowerCase().contains("layout")){
                            view = createView(name,"android.widget.",attrs);
                            return view;
                        }
                        return super.onCreateView(name,attrs);
                    }


                    @Override
                    public LayoutInflater cloneInContext(Context context) {
                        return null;
                    }
                }.inflate(R.layout.adapter_list_group_item, null);
                groupHolder = new GroupHolder();
                groupHolder.mGroupTitleTxt = (TextView)convertView.findViewById(R.id.group_item_txt);
                groupHolder.img = (ImageView)convertView.findViewById(R.id.group_item_img);
                convertView.setTag(groupHolder);
            } else {
                groupHolder = (GroupHolder)convertView.getTag();
            }

            if (!isExpanded) {
                groupHolder.img.setBackgroundResource(R.drawable.arrow_down);
            } else {
                groupHolder.img.setBackgroundResource(R.drawable.arrow_up);
            }
            groupHolder.mGroupTitleTxt.setText(grade_list.get(groupPosition));
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ItemHolder itemHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.adapter_list_item, null);
                itemHolder = new ItemHolder();
                itemHolder.mItemClass = (TextView)convertView.findViewById(R.id.item_class_txt);
                itemHolder.mItemNum = (TextView)convertView.findViewById(R.id.item_num_txt);
                convertView.setTag(itemHolder);
            } else {
                itemHolder = (ItemHolder)convertView.getTag();
            }
            final Map m = (Map) getChild(groupPosition,childPosition);
            itemHolder.mItemClass.setText(m.get("cls").toString()+"班");
            itemHolder.mItemNum.setText(((List)m.get("users")).size()+"人");
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(view.getContext(), Class_StuManager.class).putExtra("users", (Serializable) m.get("users")));
                }
            });
            return convertView;
        }


        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }

    class GroupHolder {
        public TextView mGroupTitleTxt;

        public ImageView img;
    }

    class ItemHolder {
        public TextView mItemClass;

        public TextView mItemNum;
    }

}
