package com.newer.kt.Refactor.ui.Fragment.Main;

import android.content.Intent;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.frame.app.base.activity.BaseActivity;
import com.frame.app.utils.ImageViewUtils;
import com.newer.kt.R;
import com.newer.kt.Refactor.ui.Avtivity.Xjss.ActivityChooseBirth;
import com.newer.kt.Refactor.ui.Avtivity.Xjss.ActivityChooseClass;
import com.newer.kt.Refactor.ui.Avtivity.Xjss.Constant;
import com.newer.kt.ktmatch.QueryBuilder;

import org.xutils.http.RequestParams;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import io.vov.vitamio.utils.Log;

import static com.newer.kt.Refactor.ui.Avtivity.LoginActivity.PRE_CURRENT_CLUB_ID;
import static com.newer.kt.Refactor.ui.Avtivity.LoginActivity.PRE_CURRENT_USER_ID;

public class Student_Info extends BaseActivity {

    private ImageView image_vs_item_back;
    public String code;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__info);
        initView();
        initOnclick();
        code = getIntent().getStringExtra("code");

        String userid = "" + PreferenceManager.getDefaultSharedPreferences(getBaseContext())
                .getLong(PRE_CURRENT_USER_ID, 1);
        String clubid = "" + PreferenceManager.getDefaultSharedPreferences(getBaseContext())
                .getLong(PRE_CURRENT_CLUB_ID, 1);
//        Toast.makeText(this,"code: "+code,Toast.LENGTH_SHORT).show();
//        Log.e(" ==== ","=================== code: "+code);
        QueryBuilder.build("school_class/get_user_info").add("user_id", code).get(new QueryBuilder.EnhancedCallback() {
            @Override
            public void onSuccessWithObject(String namelink, Object object) {
//Toast.makeText(Student_Info.this,object.toString(),Toast.LENGTH_SHORT).show();
                Map map = (Map) object;
                ((TextView) findViewById(R.id.tv_stuInfo1)).setText(map.get("nickname").toString());
                ((TextView) findViewById(R.id.tv_stuInfo2)).setText(map.get("gender").toString().toUpperCase().equals("MM") ? "女" : "男");
                ((TextView) findViewById(R.id.tv_stuInfo3)).setText(map.get("birthday").toString());
                ((TextView) findViewById(R.id.tv_stuInfo4)).setText(map.get("cls").toString());
                ((TextView) findViewById(R.id.tv_stuInfo5)).setText(map.get("phone").toString());



//                map.get("");

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onDebug(RequestParams rp) {

            }
        });
        //http://api.ktfootball.com/school_class/update_user_info?authenticity_token=82b331acbcdf6a50064b9c14b5c0fb8b&user_id=57017&club_id=89

        findViewById(R.id.rl_title7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.rl_title10).setVisibility(View.GONE);
                startActivity(new Intent(getBaseContext(), ActivityChooseBirth.class));
            }
        });
        findViewById(R.id.rl_title5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = ((TextView)findViewById(R.id.tv_stuInfo2)).getText().toString();
                if(t.equals("男")){
                    ((TextView)findViewById(R.id.tv_stuInfo2)).setText("女");
                }else{
                    ((TextView)findViewById(R.id.tv_stuInfo2)).setText("男");
                }
            }
        });



        QueryBuilder.build("offline/get_school_course_data_classes").add("club_id",clubid).get(new QueryBuilder.EnhancedCallback() {
            @Override
            public void onSuccessWithObject(String namelink, final Object object) {
                Toast.makeText(getBaseContext(),object.toString(),Toast.LENGTH_SHORT).show();
                List<Map> lt = (List<Map>) object;

                findViewById(R.id.rl_title9).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        findViewById(R.id.rl_title10).setVisibility(View.GONE);
                        startActivity(new Intent(getBaseContext(), ActivityChooseClass.class).putExtra("flag","1").putExtra("data", (Serializable) object));
                    }
                });
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onDebug(RequestParams rp) {

            }
        });
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

    //
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String date = data.getStringExtra(Constant.KEY_CHOOSE_START_TIME);
        ((TextView) findViewById(R.id.tv_stuInfo3)).setText(date);
        findViewById(R.id.rl_title10).setVisibility(View.VISIBLE);

    }
}
