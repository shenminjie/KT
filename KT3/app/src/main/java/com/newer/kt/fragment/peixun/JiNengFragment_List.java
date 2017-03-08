package com.newer.kt.fragment.peixun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.newer.kt.R;
import com.newer.kt.Refactor.ui.Avtivity.Main2Activity;
import com.newer.kt.Refactor.ui.Fragment.Main.ManagerFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JiNengFragment_List extends AppCompatActivity {

    private ListView lv_jinengList;
    private BaseAdapter adapter;
    private ImageView image_vs_item_back;
    private List data;
    List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_neng_fragment__list);
        list = (List) getIntent().getSerializableExtra("list");
        int index = getIntent().getIntExtra("catidx", 0);
        if (list == null) {
            data = (List) ((Map) JinengFramgent.jineng_cat_data.get(index)).get("list");
            ((TextView) findViewById(R.id.tv_title_game)).setText((CharSequence) ((Map) JinengFramgent.jineng_cat_data.get(index)).get("category"));
            if (((TextView) findViewById(R.id.tv_title_game)).getText().equals("足球游戏")) {
                ((TextView) findViewById(R.id.tv_title_game)).setText("素质教育");
            }
        } else {
            data = list;
            ((TextView) findViewById(R.id.tv_title_game)).setText("足球知识");

        }
        initView();
        initAdapter();
        initOnclick();
    }


    private void initOnclick() {
        lv_jinengList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list == null) {
                    Intent intent = new Intent(getApplicationContext(), JiNeng_Liebiao.class);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(getApplicationContext(), Main2Activity.class).putExtra("url", ((Map) list.get(position)).get("url").toString());
                    startActivity(intent);

                }
            }
        });

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
                return data.size();
            }

            @Override
            public Object getItem(int position) {
                return data.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = View.inflate(getApplicationContext(), R.layout.item_jineng_list, null);
                if (list == null) {
                    ((TextView) convertView.findViewById(R.id.tv_vs_saishi_title)).setText((CharSequence) ((Map) getItem(position)).get("name"));
                } else {
                    ((TextView) convertView.findViewById(R.id.tv_vs_saishi_title)).setText((CharSequence) ((Map) getItem(position)).get("title"));

                }
                return convertView;
            }
        };
        lv_jinengList.setAdapter(adapter);

    }

    private void initView() {
        lv_jinengList = ((ListView) findViewById(R.id.lv_jinengList));
        image_vs_item_back = ((ImageView) findViewById(R.id.image_vs_item_back));


    }
}
