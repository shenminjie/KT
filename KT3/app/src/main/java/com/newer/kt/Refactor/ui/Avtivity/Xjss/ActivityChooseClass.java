package com.newer.kt.Refactor.ui.Avtivity.Xjss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.aigestudio.wheelpicker.WheelPicker;
import com.newer.kt.R;

import org.apache.commons.collections.ArrayStack;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bajieaichirou on 17/3/4.
 */
public class ActivityChooseClass extends Activity {

    private static Map mapgetCls = new TreeMap();
    public static String get(String id){

return (String) mapgetCls.get(id)==null?id:(String) mapgetCls.get(id);
    }
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
    
//    @Bind(R.id.wheel_day)
//    WheelPicker mDayWheel;

    private static List<String> year_list = new ArrayList<>();
    private static List<String> month_list = new ArrayList<>();
    private List<String> day_list = new ArrayList<>();
    private int day;
    private static int year_selected_index = 0;
    private static int month_selected_index = 0;
    private int day_selected_index = 0;
    private String type;
    private Intent intent;


    public static List<Map<String,Object>> maps = new ArrayList<Map<String,Object>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_choose_class);
        ButterKnife.bind(this);
        maps = (List<Map<String, Object>>) getIntent().getSerializableExtra("data");
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
                intent.putExtra("data", year_list.get(year_selected_index) + "-" +
                    month_list.get(month_selected_index));
                intent.putExtra("flag","1");
                intent.putExtra("cls_id",map.get(month_list.get(month_selected_index)));

                setResult(Constant.CODE_CHOOSE_TIME, intent);
                this.finish();
                break;
        }
    }
    static String[] grade = new String[]{"小班","中班","大班","一年级","二年级","三年级","四年级","五年级","六年级","初一","初二"};

    private void setData() {
        mTitleTxt.setText("班级");

//        initData();
        mYearWheel.setData(year_list);
        mMonthWheel.setData(month_list);
//        day = getDaysByYearMonth(Integer.valueOf(year_list.get(0)), Integer.valueOf(month_list.get(0)));
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

            }
        });

//        mDayWheel.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(WheelPicker picker, Object data, int position) {
//                day_selected_index = position;
//            }
//        });
    }

    public static  void initData() {
        for (int i = 0; i < maps.size(); i ++){
            int gradeix = Integer.parseInt(maps.get(i).get("grade").toString())-1;
            String gradestr = grade[gradeix];
            year_list.add(gradestr);
        }
        for(int j = 0;j<maps.size();j++){

        for (int i = 0; i < ((List)maps.get(j).get("list")).size(); i++){
            String cls = ((Map)((List)maps.get(j).get("list")).get(i)).get("cls").toString();
            cls = cls.endsWith("班")?cls:cls+"班";
            map.put(cls,((Map)((List)maps.get(j).get("list")).get(i)).get("id").toString());
            mapgetCls.put(((Map)((List)maps.get(j).get("list")).get(i)).get("id").toString(),cls);
            month_list.add(cls);
        }
        }
    }

    static Map<String,String> map = new TreeMap<String,String>();
    private void setDayData(){
//        day = getDaysByYearMonth(Integer.valueOf(year_list.get(year_selected_index)), Integer.valueOf(month_list.get(month_selected_index)));
//        day_list.clear();
//        for(int i = 1; i <= day; i ++){
//            day_list.add("" + i);
//        }
//        mDayWheel.setData(day_list);
        month_list.clear();
        map.clear();
        for (int i = 0; i < ((List)maps.get(year_selected_index).get("list")).size();i++){
            String cls = ((Map)((List)maps.get(0).get("list")).get(i)).get("cls").toString();
            cls = cls.endsWith("班")?cls:cls+"班";
            map.put(cls,((Map)((List)maps.get(0).get("list")).get(i)).get("id").toString());
            month_list.add(cls);
        }
        mMonthWheel.setData(month_list);
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
