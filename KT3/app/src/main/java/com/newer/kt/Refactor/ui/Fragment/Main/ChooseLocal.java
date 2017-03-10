package com.newer.kt.Refactor.ui.Fragment.Main;

import android.media.Image;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.frame.app.base.activity.BaseActivity;
import com.newer.kt.InterfaceSample;
import com.newer.kt.R;
import com.newer.kt.Refactor.ui.Avtivity.LoginActivity;
import com.newer.kt.ktmatch.QueryBuilder;
import com.sina.weibo.sdk.api.share.Base;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class ChooseLocal extends BaseActivity {

    private ListView lv_chooseStu;
    private BaseAdapter adapter;
    private ImageView image_vs_item_back;
    private Button btn_tijiao;
    private ArrayList<Map<String, String>> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_local);
        list = SettingsFragment.unlinkedStudents;
        initView();
        initOnclick();
        initAdapter();
        if(list==null||list.size()==0){
            new InterfaceSample(this).get_club_data();
        }
    }

    @Override
    public void onDataLoad(String namelink, Object object) {
        super.onDataLoad(namelink, object);
        list = (ArrayList<Map<String, String>>) object;

        for(Map<String, String> m:list){
            if(m.get("school_cls").toString().equals("null")||m.get("school_grade").toString().equals("null")){
                list.add((Map<String, String>) m);
            }
        }
        lv_chooseStu.setAdapter(adapter);
        adapter.notifyDataSetChanged();

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

    private void initOnclick() {
        image_vs_item_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void initAdapter() {
        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = View.inflate(getApplicationContext(), R.layout.item_choose_stu, null);
                final Map<String, String> m = (Map<String, String>) getItem(position);
//                ((TextView) convertView.findViewById(R.id)).setText(m.get("avatar"));

                ((TextView) convertView.findViewById(R.id.name)).setText(m.get("nickname"));
                ((TextView) convertView.findViewById(R.id.gender)).setText(m.get("gender").equals("GG")?"男":"女");
                boolean b = !map.contains(m);
                View view = ((View) convertView.findViewById(R.id.xuanzhong));
                if (!b) {
                    ((ImageView)view).setImageResource(R.drawable.xuanzhonqg);
                } else {
                    ((ImageView)view).setImageResource(R.drawable.weixuanzhong);
                }

                ((ImageView) convertView.findViewById(R.id.xuanzhong)).setOnClickListener(new View.OnClickListener() {
                    boolean b;

                    @Override
                    public void onClick(View view) {
                        if (!map.contains(m)) {

                            ((ImageView)view).setImageResource(R.drawable.xuanzhonqg);
                            map.add(m);
                        } else {
                            ((ImageView)view).setImageResource(R.drawable.weixuanzhong);
                            map.remove(m);
                        }
                        notifyDataSetChanged();

                    }
                });
//                ((TextView)findViewById(R.id.)).setText(m.get("").toString());
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((View) view.findViewById(R.id.xuanzhong)).performClick();
                    }
                });
                return convertView;
            }
        };
        lv_chooseStu.setAdapter(adapter);
    }

    List<Map<String, String>> map = new ArrayList<Map<String, String>>();

    private void initView() {
        lv_chooseStu = ((ListView) findViewById(R.id.lv_chooseStu));
        image_vs_item_back = ((ImageView) findViewById(R.id.image_vs_item_back));
        btn_tijiao = ((Button) findViewById(R.id.btn_tijiao));
        image_vs_item_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(map.size()==0){
                    finish();
                    return;
                }
                String clubid = ""+ PreferenceManager.getDefaultSharedPreferences(ChooseLocal.this)
                        .getLong(LoginActivity.PRE_CURRENT_CLUB_ID,1);
                String userid = ""+ PreferenceManager.getDefaultSharedPreferences(ChooseLocal.this)
                        .getLong(LoginActivity.PRE_CURRENT_USER_ID,1);

                for(final Map<String,String> m : map){

                    QueryBuilder.build("school_class/update_user_info").add("school_club_id",m.get("school_club_id").toString()).add("phone",m.get("phone").toString()).add("birthday",m.get("birthday").toString()).add("",m.get("").toString()).add("avatar",m.get("avator").toString()).add("school_class_id",m.get("school_class_id").toString()).add("club_id",m.get("club_id").toString()).add("user_id",m.get("user_id").toString()).get(new QueryBuilder.EnhancedCallback("response") {
                        @Override
                        public void onSuccessWithObject(String namelink, Object object) {
                            if(object.toString().equals("success")){
                                map.remove(m);
                                if(map.size()==0){
                                    finish();
                                }
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
            }
        });
    }
}
