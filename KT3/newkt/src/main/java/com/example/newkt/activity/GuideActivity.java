package com.example.newkt.activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.newkt.R;
import com.example.newkt.adapter.GuideAdapter;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    ArrayList<View>imageViews=new ArrayList<>();
    GuideAdapter guideAdapter=null;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initData();
        initView();
    }
    private void initData() {
        ImageView guid1=new ImageView(this);
        guid1.setImageResource(R.drawable.app_start_aa);
        guid1.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView guid2=new ImageView(this);
        guid2.setImageResource(R.drawable.app_start_bb);
        guid2.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView guid3=new ImageView(this);
        guid3.setImageResource(R.drawable.app_start_cc);
        guid3.setScaleType(ImageView.ScaleType.FIT_XY);
         View guid4=View.inflate(getBaseContext(),R.layout.guide_last_imageview,null);
        imageViews.add(guid1);
        imageViews.add(guid2);
        imageViews.add(guid3);
        imageViews.add(guid4);
    }

    private void initView() {
        ViewPager viewPager= (ViewPager) findViewById(R.id.guide_ViewPager);
        viewPager.setOnPageChangeListener(this);
        guideAdapter=new GuideAdapter(imageViews);
        viewPager.setAdapter(guideAdapter);
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    if(position==imageViews.size()-1){
        SharedPreferences preferences=getSharedPreferences("args",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("isGuid",true).commit();
    }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //点击按钮跳转到工作空间
    public void guid_tiyan(View view){
        intent = new Intent(getBaseContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        intent=null;

    }

}
