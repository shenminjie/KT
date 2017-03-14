package com.newer.kt.zqk;

import android.graphics.Color;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.frame.app.base.activity.BaseActivity;
import com.newer.kt.R;
import com.sina.weibo.sdk.api.share.Base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ZQKVIdeoActivity extends BaseActivity {
    BaseExpandableListAdapter adapter;
    List<String> titles = new ArrayList<String>();
    Map<String,ArrayList<Map>> maps = new TreeMap<String,ArrayList<Map>>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zqkvideo);
        titles = (List<String>) getIntent().getSerializableExtra("titles");
        maps= (Map<String, ArrayList<Map>>) getIntent().getSerializableExtra("content");
        adapter = new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {
                return titles.size();
            }

            @Override
            public int getChildrenCount(int i) {
                return maps.get(getGroup(i).toString()).size();
            }

            @Override
            public Object getGroup(int i) {
                return titles.get(i);
            }

            @Override
            public Object getChild(int i, int i1) {
                return maps.get(getGroup(i).toString()).get(i1);
            }

            @Override
            public long getGroupId(int i) {
                return 0;
            }

            @Override
            public long getChildId(int i, int i1) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
                if(view==null){
                    view  = LayoutInflater.from(getBaseContext()).inflate(R.layout.adapter_schedule_list_group,null);
                    view.setBackgroundColor(Color.parseColor("#EDEDED"));
                }
                ((TextView)view.findViewById(R.id.schedule_group_item_txt)).setText(getGroup(i).toString());
                return view;
            }

            @Override
            public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
                if(view==null){
                    view  = LayoutInflater.from(getBaseContext()).inflate(R.layout.adapter_schedule_list_group_child,null);
                }
                ((TextView)view.findViewById(R.id.schedule_group_item_txt)).setText(((Map)getChild(i,i1)).get("child_title").toString());
                view.findViewById(R.id.schedule_group_item_img).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                return view;
            }

            @Override
            public boolean isChildSelectable(int i, int i1) {
                return false;
            }
        };
        ((ExpandableListView)findViewById(R.id.expandable)).setAdapter(adapter);
        for(int i = 0;i<adapter.getGroupCount();i++){
            ((ExpandableListView)findViewById(R.id.expandable)).expandGroup(i);
        }
        ((ExpandableListView)findViewById(R.id.expandable)).setGroupIndicator(null);
//        ((ExpandableListView)findViewById(R.id.expandable));

        ((ScrollView)findViewById(R.id.scroll)).getChildAt(0).scrollTo(0,0);
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

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
