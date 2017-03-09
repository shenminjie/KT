package com.newer.kt.Refactor.ui.Fragment.Main;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.Text;
import com.newer.kt.R;
import com.newer.kt.Refactor.ui.Avtivity.Xjss.ActivityChooseGrade;
import com.newer.kt.Refactor.ui.Avtivity.Xjss.ActivityChooseTime;
import com.newer.kt.Refactor.ui.Avtivity.Xjss.Constant;
import com.newer.kt.ktmatch.MathChooseActivity;
import com.newer.kt.ktmatch.QueryBuilder;

import org.xutils.http.RequestParams;

import static com.newer.kt.Refactor.ui.Avtivity.LoginActivity.PRE_CURRENT_CLUB_ID;

public class AddClass extends AppCompatActivity {
    int grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class2);
        findViewById(R.id.rl_title2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), ActivityChooseGrade.class);
                startActivityForResult(intent, 0);
            }
        });
        findViewById(R.id.saishi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(grade==0){
                    Toast.makeText(getBaseContext(), "请选择年级", Toast.LENGTH_SHORT).show();
                    return;
                }

                String cls = ((TextView)findViewById(R.id.tv_stuInfo2)).getText().toString();
                if(cls.equals("")){
                    Toast.makeText(getBaseContext(), "请选择班级", Toast.LENGTH_SHORT).show();
                    return;
                }

                String clubid = "" + PreferenceManager.getDefaultSharedPreferences(getBaseContext())
                        .getLong(PRE_CURRENT_CLUB_ID, 1);
                QueryBuilder.build("school_class/create_school_class").add("club_id", clubid).add("grade", grade).add("cls", cls).post(new QueryBuilder.EnhancedCallback("response") {
                    @Override
                    public void onSuccessWithObject(String namelink, Object object) {
                        finish();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onDebug(RequestParams rp) {

                    }
                });
            }
        });
        findViewById(R.id.image_vs_item_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String g = data.getStringExtra("rt");
        ((TextView)findViewById(R.id.tv_classChoose)).setText(g);
        grade = data.getIntExtra("grade",-1);

    }
}
