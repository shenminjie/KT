package com.newer.kt.fragment.peixun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.newer.kt.R;

public class JiNengFragment_List extends AppCompatActivity {

    private ListView lv_jinengList;
    private BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_neng_fragment__list);


        initView();
        initAdapter();
        initOnclick();


    }

    private void initOnclick() {
        lv_jinengList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(getApplicationContext(),JiNeng_Liebiao.class);
                startActivity(intent);
            }
        });
    }

    private void initAdapter() {
        adapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
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
                convertView=View.inflate(getApplicationContext(),R.layout.item_jineng_list,null);
                return convertView;
            }
        };
        lv_jinengList.setAdapter(adapter);

    }

    private void initView() {
        lv_jinengList = ((ListView) findViewById(R.id.lv_jinengList));


    }
}
