package com.newer.kt.Refactor.ui.Avtivity;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import com.frame.app.base.activity.BaseActivity;
import com.newer.kt.R;
import com.newer.kt.fragment.peixun.FootBallFragment;

import java.util.ArrayList;
import java.util.Map;

public class FootBall_Class_Lesson extends BaseActivity {
    private ImageView iv_ft_back1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot_ball__class__lesson);


        initView();
        initEvent();
        final ArrayList<String> list = new ArrayList<String>(FootBallListActivity.semester);
        ((GridView)findViewById(R.id.gv_zqk)).setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return FootBallListActivity.semester.size();
            }

            @Override
            public Object getItem(int i) {
                return list.get(i);
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(final int i, View view, ViewGroup viewGroup) {
                View convertView = LayoutInflater.from(getBaseContext()).inflate(R.layout.zqk_gv_item, null);
                ((TextView) convertView.findViewById(R.id.name)).setText(((Map)getItem(i)).get("name").toString());
                //times" -> "10,10,15,5"
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       String id = ((Map)FootBallListActivity.semester.get(i)).get("school_gym_course_combination_id").toString();
                        Intent intent=new Intent(getApplicationContext(),Main2Activity.class).putExtra("school_gym_course_combination_id",id);
                        startActivity(intent);

                    }
                });
                return convertView;
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

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    private void initEvent() {
        iv_ft_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FootBall_Class_Lesson.this.finish();
            }
        });
    }

    private void initView() {
        iv_ft_back1 = ((ImageView) findViewById(R.id.iv_ft_back1));
    }
}
