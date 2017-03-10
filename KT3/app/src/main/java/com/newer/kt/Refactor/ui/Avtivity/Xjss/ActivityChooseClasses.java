package com.newer.kt.Refactor.ui.Avtivity.Xjss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.newer.kt.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bajieaichirou on 17/3/4.
 */
public class ActivityChooseClasses extends Activity {
    
    @Bind(R.id.choose_time_title_txt)
    TextView mTitleTxt;
    
    @Bind(R.id.time_cancel_wheel)
    TextView mCancel;
    
    @Bind(R.id.time_ok_wheel)
    TextView mOk;
    
    @Bind(R.id.wheel_year)
    WheelPicker mYearWheel;

    private List<String> year_list = new ArrayList<>();
    private int day;
    private int year_selected_index = 0;
    private int month_selected_index = 0;
    private int day_selected_index = 0;
    private String type;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_choose_grade);
        ButterKnife.bind(this);
        setData();
        getWindow().getDecorView().setBackgroundResource(R.color.transparent);
    }

    @OnClick({R.id.time_cancel_wheel, R.id.time_ok_wheel})
    public void OnClick(View view){
        intent = new Intent();
        switch (view.getId()){
            case R.id.time_cancel_wheel:

                setResult(0, intent);
                this.finish();
                break;

            case R.id.time_ok_wheel:
                intent.putExtra("rt", year_list.get(year_selected_index));

                intent.putExtra("grade", year_selected_index+1);
                setResult(0, intent);
                this.finish();
                break;
        }
    }

    private void setData() {
        String[] str = new String[]{"幼儿园小班","幼儿园中班","幼儿园大班","小学一年级","小学二年级","小学三年级","小学四年级","小学五年级","小学六年级","初一","初二"};
        for (int i = 0; i <str.length ; i ++){
            year_list.add(str[i]);
        }
        mYearWheel.setData(year_list);
        mYearWheel.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                year_selected_index = position;


            }
        });

    }

}
