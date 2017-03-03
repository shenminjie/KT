package com.newer.kt.Refactor.ui.Fragment.Main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.newer.kt.R;
import com.newer.kt.entity.Clubs_paihang;
import com.newer.kt.entity.Clubs_paihang_Bean;
import com.newer.kt.ktmatch.MapBuilder;
import com.newer.kt.ktmatch.Params;
import com.newer.kt.ktmatch.QueryBuilder;
import com.newer.kt.record.TakePicActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import shengchengerweima.CamScanActivity;

public class SaiShiEJ extends CamScanActivity {

    public ListView lv_vs_ej;
    private BaseAdapter adapter;
    private BaseAdapter adapter_vs;
    private View view = null;
    private ViewHolder viewHolder;

    final ArrayList<Clubs_paihang_Bean.Clubs_paihang> clubs_paihang_list = new ArrayList<Clubs_paihang_Bean.Clubs_paihang>();
    private RadioButton rb_paihang;
    private RadioButton rb_zongchangci;
    private ListView lv_vs_cc;
    private ImageView image_vs_item_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_saishi);
        initView();
        affairs();
        invent();
        getRanking();
        others();
        lv_vs_ej.setAdapter(adapter);
        lv_vs_ej.setVisibility(View.VISIBLE);
        lv_vs_cc.setVisibility(View.GONE);
        findViewById(R.id.saishi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),TakePicActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    @Override
    public void recvCode(String result) {
        super.recvCode(result);
        Params.getInstanceParam().setCode(result);
    }

    /**
     * 功能性处理
     */
    private void others() {
        image_vs_item_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaiShiEJ.this.finish();   // 返回键
            }
        });
    }

    /**
     * 获取数据
     */
    private void getRanking() {

        RequestParams rp = QueryBuilder.build("games/ranking").add("game_id",getIntent().getStringExtra("game_id")).add("type","1").get();

        x.http().get(rp, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Clubs_paihang_Bean clubs_bean = gson.fromJson(result, Clubs_paihang_Bean.class);
                clubs_paihang_list.clear();
                clubs_paihang_list.addAll(clubs_bean.game_rankings);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    /**
     * 拿控件
     */
    private void initView() {

        lv_vs_ej = ((ListView) findViewById(R.id.lv_vs_ej));
        lv_vs_cc = ((ListView) findViewById(R.id.lv_vs_cc));
        rb_paihang = ((RadioButton) findViewById(R.id.rb_paihang));
        rb_zongchangci = ((RadioButton) findViewById(R.id.rb_zongchangci));
        image_vs_item_back = ((ImageView) findViewById(R.id.image_vs_item_back));

    }


    /**
     * 点击事件
     */
    private void invent() {
//        tv_paihang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(tv_paihang.isSelected()==true){
//                    tv_paihang.setTextColor(Color.BLACK);
//                }
//                lv_vs_ej.setAdapter(adapter);
//                lv_vs_ej.setVisibility(View.VISIBLE);
//                lv_vs_cc.setVisibility(View.GONE);
//                Toast.makeText(getApplicationContext(), "sdofjowjefowneofnwoenf", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        tv_zongchangci.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                lv_vs_cc.setAdapter(adapter_vs);
//                lv_vs_ej.setVisibility(View.GONE);
//                lv_vs_cc.setVisibility(View.VISIBLE);
//                Toast.makeText(getApplicationContext(), "hahah", Toast.LENGTH_SHORT).show();
//                System.out.println("12312312312312222222222222222222222312312312312312312313123");
//            }
//        });
        rb_paihang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv_vs_ej.setAdapter(adapter);
                lv_vs_ej.setVisibility(View.VISIBLE);
                lv_vs_cc.setVisibility(View.GONE);
            }
        });


        rb_zongchangci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv_vs_cc.setAdapter(adapter);
                lv_vs_cc.setVisibility(View.VISIBLE);
                lv_vs_ej.setVisibility(View.GONE);


            }
        });
    }

    /**
     * 打气填充
     */
    private void affairs() {

        adapter = new BaseAdapter() {


            @Override
            public int getCount() {
               return clubs_paihang_list.size();
               // return 10;
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
                viewHolder = new ViewHolder();
                View view = View.inflate(getApplicationContext(), R.layout.item_vs_paihang, null);
                viewHolder.iv_touxiang = ((ImageView) findViewById(R.id.iv_touxiang));
                viewHolder.tv_vs_name = ((TextView) findViewById(R.id.tv_vs_name));
                viewHolder.tv_nianji = ((TextView) findViewById(R.id.tv_nianji));
                viewHolder.tv_changci = ((TextView) findViewById(R.id.tv_changci));
                viewHolder.tv_jifen = ((TextView) findViewById(R.id.tv_jifen));
                viewHolder.tv_zhandouli = ((TextView) findViewById(R.id.tv_zhandouli));


//                user_id: 用户ID,
//                        nickname: 昵称,
//                        avatar: 头像,
//                        scores: 积分,
//                        power: 战斗力,
//                        win_rate: 胜率
//                Clubs_paihang_Bean.Clubs_paihang clubs = clubs_paihang_list.get(position);
//                viewHolder.tv_vs_name.setText(clubs.nickname);
//                viewHolder.tv_changci.setText(clubs.win_rate);
//                viewHolder.tv_jifen.setText(clubs.scores);
//                viewHolder.tv_zhandouli.setText(clubs.power);
                return view;
            }
        };


//        adapter_vs = new BaseAdapter() {
//            @Override
//            public int getCount() {
//                return 1;
//            }
//
//            @Override
//            public Object getItem(int position) {
//                return null;
//            }
//
//            @Override
//            public long getItemId(int position) {
//                return 0;
//            }
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                convertView=View.inflate(getApplicationContext(),R.layout.item_vs_paihang,null);
//                return convertView;
//            }
//        };
    }

    public static class ViewHolder {

        private TextView tv_vs_name;
        private TextView tv_nianji;
        private ImageView iv_touxiang;
        private TextView tv_changci;
        private TextView tv_jifen;
        private TextView tv_zhandouli;

    }
}
