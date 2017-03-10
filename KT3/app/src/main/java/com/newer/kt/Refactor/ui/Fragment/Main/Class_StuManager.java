package com.newer.kt.Refactor.ui.Fragment.Main;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.frame.app.base.activity.BaseActivity;
import com.newer.kt.R;
import com.newer.kt.entity.Student;

import java.util.List;
import java.util.Map;

import shengchengerweima.CamScanActivity;

public class Class_StuManager extends CamScanActivity implements View.OnClickListener {

    private ListView lv_class_stuManager;
    private BaseAdapter adapter;
    private ImageView image_vs_item_back;
    public List list;
    private ImageView iv_zengjia;
    private PopupWindow mPopWindow;
    private LinearLayout ll_pop;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_stu_manager);
        initView();
        list = (List) getIntent().getSerializableExtra("users");

        initDate();
        initAdapter();
        initOnclick();

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
        lv_class_stuManager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(), Student_Info.class);
                startActivity(intent);
            }
        });

        image_vs_item_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





        iv_zengjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });

    }
    private void showPopupWindow() {
        View contentView = LayoutInflater.from(Class_StuManager.this).inflate(R.layout.popuplayout1, null);
        mPopWindow = new PopupWindow(contentView);
        mPopWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);




        //mPopWindow.setOutsideTouchable(true);
        TextView pop_saomiao = (TextView) contentView.findViewById(R.id.pop_saomiao);
        TextView pop_addbendi = (TextView) contentView.findViewById(R.id.pop_addbendi);
        TextView pop_quxiao = (TextView) contentView.findViewById(R.id.pop_quxiao);
        ll_pop = ((LinearLayout) contentView.findViewById(R.id.ll_pop));
        pop_saomiao.setOnClickListener(this);
        pop_addbendi.setOnClickListener(this);
        pop_quxiao.setOnClickListener(this);
        WindowManager wm = this.getWindowManager();

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        int length=width-400;
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.showAsDropDown(ll_pop,length/2,height/2);


    }
    private void initAdapter() {
        adapter=new BaseAdapter() {
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
                Map m = (Map)getItem(position);

                convertView=View.inflate(getApplicationContext(),R.layout.item_class_stumanager,null);
                ((TextView)convertView.findViewById(R.id.tv_vs_name)).setText(m.get("nickname").toString());
                ((ImageView)convertView.findViewById(R.id.tv_no)).setImageResource(m.get("gender").toString().equals("nv")?R.mipmap.nv:R.mipmap.nan);

                ((TextView)convertView.findViewById(R.id.tv_shengao)).setText(m.get("height").toString());
                ((TextView)convertView.findViewById(R.id.tv_tizhong)).setText(m.get("weight").toString());

                return convertView;
            }
        };
        lv_class_stuManager.setAdapter(adapter);
    }

    private void initDate() {
    }


    private void initView() {
        lv_class_stuManager = ((ListView) findViewById(R.id.lv_class_stuManager));
        image_vs_item_back = ((ImageView) findViewById(R.id.image_vs_item_back));
        iv_zengjia = ((ImageView) findViewById(R.id.iv_zengjia));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.pop_saomiao: {
                invokeCap();
                mPopWindow.dismiss();
            }
            break;
            case R.id.pop_addbendi: {
                Intent intent = new Intent(getApplicationContext(), ChooseLocal.class);
                startActivity(intent);
                mPopWindow.dismiss();
            }
            break;
            case R.id.pop_quxiao: {
                Intent intent = new Intent(getApplicationContext(), AddClass.class);
                startActivity(intent);
                mPopWindow.dismiss();
            }
            break;


        }
    }

    @Override
    public void recvCode(String result) {
        super.recvCode(result);
        if(result!=null){
            startActivity(new Intent(getBaseContext(),Student_Info.class).putExtra("code",result));

        }
    }
}
