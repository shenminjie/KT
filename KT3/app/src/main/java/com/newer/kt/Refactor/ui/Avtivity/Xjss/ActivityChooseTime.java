package com.newer.kt.Refactor.ui.Avtivity.Xjss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.newer.kt.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bajieaichirou on 17/3/4.
 */
public class ActivityChooseTime extends Activity {
    
    @Bind(R.id.choose_time_title_txt)
    TextView mTitleTxt;
    
    @Bind(R.id.time_cancel_wheel)
    TextView mCancel;
    
    @Bind(R.id.time_ok_wheel)
    TextView mOk;
    
    @Bind(R.id.wheel_year)
    WheelPicker mYearWheel;
    
    @Bind(R.id.wheel_month)
    WheelPicker mMonthWheel;
    
    @Bind(R.id.wheel_day)
    WheelPicker mDayWheel;

    private List<String> year_list = new ArrayList<>();
    private List<String> month_list = new ArrayList<>();
    private List<String> day_list = new ArrayList<>();
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
        setContentView(R.layout.activity_choose_time);
        ButterKnife.bind(this);
        setData();
    }

    @OnClick({R.id.time_cancel_wheel, R.id.time_ok_wheel})
    public void OnClick(View view){
        intent = new Intent();
        switch (view.getId()){
            case R.id.time_cancel_wheel:
                intent.putExtra(Constant.KEY_CHOOSE_TIME, "");
                setResult(Constant.CODE_CHOOSE_TIME, intent);
                this.finish();
                break;

            case R.id.time_ok_wheel:
                intent.putExtra(Constant.KEY_CHOOSE_TIME, year_list.get(year_selected_index) + "-" +
                    month_list.get(month_selected_index) + "-" +
                    day_list.get(day_selected_index));
                setResult(Constant.CODE_CHOOSE_TIME, intent);
                this.finish();
                break;
        }
    }

    private void setData() {
        type = getIntent().getStringExtra(Constant.KEY_CHOOSE_TIME_TYPE);
        if (type.equals(Constant.KEY_CHOOSE_START_TIME)){
            mTitleTxt.setText(getResources().getString(R.string.contest_start_time_hint));
        }else{
            mTitleTxt.setText(getResources().getString(R.string.contest_end_time_hint));
        }
        Calendar c = Calendar.getInstance();
        for (int j = c.get(Calendar.YEAR),i = j; i <j+8 ; i ++){
            year_list.add("" + i);
        }
        mYearWheel.setData(year_list);
        for (int i = c.get(Calendar.MONTH); i < 13; i++){
            month_list.add("" + i);
        }
        mMonthWheel.setData(month_list);
        day = getDaysByYearMonth(Integer.valueOf(year_list.get(0)), Integer.valueOf(month_list.get(0)));
        for(int i = c.get(Calendar.DAY_OF_MONTH); i <= day; i ++){
            day_list.add("" + i);
        }
        mDayWheel.setData(day_list);
        mYearWheel.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                year_selected_index = position;
                setDayData();
            }
        });

        mMonthWheel.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                month_selected_index = position;
                setDayData();
            }
        });

        mDayWheel.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                day_selected_index = position;
            }
        });
    }

    private void setDayData(){
        day = getDaysByYearMonth(Integer.valueOf(year_list.get(year_selected_index)), Integer.valueOf(month_list.get(month_selected_index)));
        day_list.clear();
        for(int i = 1; i <= day; i ++){
            day_list.add("" + i);
        }
        mDayWheel.setData(day_list);
    }


    private int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
}
