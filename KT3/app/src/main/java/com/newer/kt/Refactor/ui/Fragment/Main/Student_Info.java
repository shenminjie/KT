package com.newer.kt.Refactor.ui.Fragment.Main;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.frame.app.utils.ImageViewUtils;
import com.newer.kt.R;
import com.newer.kt.ktmatch.QueryBuilder;

import org.xutils.http.RequestParams;

import java.util.Map;

import static com.newer.kt.Refactor.ui.Avtivity.LoginActivity.PRE_CURRENT_CLUB_ID;
import static com.newer.kt.Refactor.ui.Avtivity.LoginActivity.PRE_CURRENT_USER_ID;

public class Student_Info extends AppCompatActivity {

    private ImageView image_vs_item_back;
    public String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__info);
        initView();
        initOnclick();
        code = getIntent().getStringExtra("code");

        String userid = "" + PreferenceManager.getDefaultSharedPreferences(getBaseContext())
                .getLong(PRE_CURRENT_USER_ID, 1);
        String clubid = "" + PreferenceManager.getDefaultSharedPreferences(getBaseContext())
                .getLong(PRE_CURRENT_CLUB_ID, 1);

        QueryBuilder.build("school_class/update_user_info").add("user_id", userid).add("club_id", clubid).post(new QueryBuilder.EnhancedCallback() {
            @Override
            public void onSuccessWithObject(String namelink, Object object) {
                Map map = (Map) object;
                ((TextView) findViewById(R.id.tv_stuInfo1)).setText(map.get("nickname").toString());
                ((TextView) findViewById(R.id.tv_stuInfo2)).setText(map.get("gender").toString().toUpperCase().equals("MM") ? "女" : "男");
                ((TextView) findViewById(R.id.tv_stuInfo3)).setText(map.get("birthday").toString());
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
}
