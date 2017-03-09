package com.newer.kt.Refactor.ui.Fragment.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.newer.kt.R;

public class ChooseLocal extends AppCompatActivity {

    private ListView lv_chooseStu;
    private BaseAdapter adapter;
    private ImageView image_vs_item_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_local);
        initView();
        initAdapter();
        initOnclick();
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
                convertView=View.inflate(getApplicationContext(),R.layout.item_choose_stu,null);
                return convertView;
            }
        };
        lv_chooseStu.setAdapter(adapter);
    }

    private void initView() {
        lv_chooseStu = ((ListView) findViewById(R.id.lv_chooseStu));
        image_vs_item_back = ((ImageView) findViewById(R.id.image_vs_item_back));
    }
}
