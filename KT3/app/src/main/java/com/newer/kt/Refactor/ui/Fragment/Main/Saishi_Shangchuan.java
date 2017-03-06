package com.newer.kt.Refactor.ui.Fragment.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.newer.kt.R;

public class Saishi_Shangchuan extends AppCompatActivity {

    private ImageView image_vs_item_back;
    private ListView lv_daishangchuan;
    private BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saishi__shangchuan);

        initView();
        initAdapter();
        //
        initOnclick();


        initDate();

    }

    private void initAdapter() {
        adapter=new BaseAdapter() {
            @Override
            public int getCount() {
                return 2;
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
                convertView=View.inflate(getApplicationContext(),R.layout.item_shangchuanlist,null);

                return convertView;
            }
        };
        lv_daishangchuan.setAdapter(adapter);

    }


    private void initDate() {

    }

    private void initOnclick() {
        image_vs_item_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        image_vs_item_back = ((ImageView) findViewById(R.id.image_vs_item_back));
        lv_daishangchuan = ((ListView) findViewById(R.id.lv_daishangchuan));
    }
}
