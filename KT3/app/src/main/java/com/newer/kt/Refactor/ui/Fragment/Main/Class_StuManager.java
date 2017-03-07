package com.newer.kt.Refactor.ui.Fragment.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.newer.kt.R;

public class Class_StuManager extends AppCompatActivity {

    private ListView lv_class_stuManager;
    private BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_stu_manager);
        initView();
        initOnclick();
        initDate();
        initAdapter();
    }


    private void initAdapter() {
        adapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView=View.inflate(getApplicationContext(),R.layout.item_class_stumanager,null);
                return convertView;
            }
        };
        lv_class_stuManager.setAdapter(adapter);
    }

    private void initDate() {
    }

    private void initOnclick() {
    }

    private void initView() {
        lv_class_stuManager = ((ListView) findViewById(R.id.lv_class_stuManager));
    }
}
