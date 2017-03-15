package com.newer.kt.Refactor.ui;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.newer.kt.R;
import com.newer.kt.Refactor.Constants;
import com.newer.kt.Refactor.ui.Avtivity.LoginActivity;
import com.newer.kt.Refactor.utils.MD5;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class SchoolName extends AppCompatActivity {

    private TextView tv_quxiao;
    private TextView tv_baocun;
    private EditText et_schoolName;
    String school_Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_name);

        initView();
        initDate();
        initOnclick();
    }

    private void initOnclick() {
        tv_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent=new Intent();
                finish();
            }
        });
        tv_baocun.setOnClickListener(new View.OnClickListener() {
            final String clubid = "" + PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getLong(LoginActivity.PRE_CURRENT_CLUB_ID, 1);

            @Override
            public void onClick(View v) {
                school_Name = et_schoolName.getText().toString();
                String url = Constants.KTHOST + "club_app/upload_avatar";
                RequestParams params = new RequestParams(url);
                params.addQueryStringParameter("authenticity_token", MD5.getToken(url));
                params.addQueryStringParameter("school_name",school_Name);
                params.addQueryStringParameter("club_id",clubid);
                x.http().post(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        finish();
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
        });

    }

    private void initDate() {
        Intent intent = getIntent();
        if (intent != null) {
            String schoolValue = intent.getStringExtra("schoolName");
            et_schoolName.setText(schoolValue.toString());
        }

    }

    private void initView() {
        tv_quxiao = ((TextView) findViewById(R.id.tv_quxiao));
        tv_baocun = ((TextView) findViewById(R.id.tv_baocun));
        et_schoolName = ((EditText) findViewById(R.id.et_schoolName));
    }
}
