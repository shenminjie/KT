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
 * Created by bajieaichirou on 17/3/3.
 */
public class ActivityChooseClub extends Activity {

    @Bind(R.id.cancle_wheel)
    TextView mCancelTxt;

    @Bind(R.id.ok_wheel)
    TextView mFinishTxt;

    @Bind(R.id.wheel)
    WheelPicker mClubWheel;

    private List<String> list = new ArrayList<>();
    private Intent intent;
    private int wheel_select_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_choose_club);
        ButterKnife.bind(this);
        setData();
    }

    private void setData() {
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++ ){
            list.add(i + "维度俱乐部");
        }
        mClubWheel.setData(list);

        mClubWheel.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                wheel_select_index = position;
            }
        });
    }

    @OnClick({R.id.cancle_wheel, R.id.ok_wheel})
    public void OnClick(View view){
        intent = new Intent();
        switch (view.getId()){
            case R.id.cancle_wheel:
                intent.putExtra(Constant.KEY_CHOOSE_CLUB_NAME, "");
                setResult(Constant.CODE_CHOOSE_CLUB, intent);
                this.finish();
                break;

            case R.id.ok_wheel:
                intent.putExtra(Constant.KEY_CHOOSE_CLUB_NAME, list.get(wheel_select_index));
                setResult(Constant.CODE_CHOOSE_CLUB, intent);
                this.finish();
                break;
        }
    }
}
