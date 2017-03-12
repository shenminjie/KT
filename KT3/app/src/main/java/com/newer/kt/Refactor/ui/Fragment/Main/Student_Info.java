package com.newer.kt.Refactor.ui.Fragment.Main;

import android.content.Intent;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.frame.app.base.activity.BaseActivity;
import com.frame.app.utils.ImageViewUtils;
import com.newer.kt.R;
import com.newer.kt.Refactor.Constants;
import com.newer.kt.Refactor.ui.Avtivity.Xjss.ActivityChooseBirth;
import com.newer.kt.Refactor.ui.Avtivity.Xjss.ActivityChooseClass;
import com.newer.kt.Refactor.ui.Avtivity.Xjss.Constant;
import com.newer.kt.ktmatch.QueryBuilder;

import org.w3c.dom.Text;
import org.xutils.http.RequestParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import io.vov.vitamio.utils.Log;

import static com.newer.kt.Refactor.ui.Avtivity.LoginActivity.PRE_CURRENT_CLUB_ID;
import static com.newer.kt.Refactor.ui.Avtivity.LoginActivity.PRE_CURRENT_USER_ID;

public class Student_Info extends BaseActivity {

    private ImageView image_vs_item_back;
    public String code;
    private Object lt;
    String passid = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__info);
        code = getIntent().getStringExtra("code");
        if(code==null){
            passid = getIntent().getStringExtra("passid");
        }

        initView();
        initOnclick();
        final String userid = "" + PreferenceManager.getDefaultSharedPreferences(getBaseContext())
                .getLong(PRE_CURRENT_USER_ID, 1);
        final String clubid = "" + PreferenceManager.getDefaultSharedPreferences(getBaseContext())
                .getLong(PRE_CURRENT_CLUB_ID, 1);
//        Toast.makeText(this,"code: "+code,Toast.LENGTH_SHORT).show();
//        Log.e(" ==== ","=================== code: "+code);
        QueryBuilder.build("school_class/get_user_info").add("user_id", code==null?passid:code).get(new QueryBuilder.EnhancedCallback() {
            @Override
            public void onSuccessWithObject(String namelink, Object object) {
//Toast.makeText(Student_Info.this,object.toString(),Toast.LENGTH_SHORT).show();
                Map map = (Map) object;
                ((TextView) findViewById(R.id.tv_stuInfo1)).setText(map.get("nickname").toString());
                ((TextView) findViewById(R.id.tv_stuInfo2)).setText(map.get("gender").toString().toUpperCase().equals("MM") ? "女" : "男");
                ((TextView) findViewById(R.id.tv_stuInfo3)).setText(map.get("birthday").toString());
                ((TextView) findViewById(R.id.tv_stuInfo4)).setText(map.get("cls").toString());
                ((TextView) findViewById(R.id.tv_stuInfo5)).setText(map.get("phone").toString());


//                m.get("");

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
                findViewById(R.id.rl_title13).setVisibility(View.GONE);
                startActivityForResult(new Intent(getBaseContext(), ActivityChooseBirth.class),0);
            }
        });
        findViewById(R.id.rl_title5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = ((TextView) findViewById(R.id.tv_stuInfo2)).getText().toString();
                if (t.equals("男")) {
                    ((TextView) findViewById(R.id.tv_stuInfo2)).setText("女");
                } else {
                    ((TextView) findViewById(R.id.tv_stuInfo2)).setText("男");
                }
            }
        });


        QueryBuilder.build("offline/get_school_course_data_classes").add("club_id", clubid).get(new QueryBuilder.EnhancedCallback("classes") {
            @Override
            public void onSuccessWithObject(String namelink, final Object object) {
                lt = object;
//                Toast.makeText(getBaseContext(), lt.toString(), Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onDebug(RequestParams rp) {

            }
        });
        findViewById(R.id.rl_title9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lt==null){
                    return;
                }
                findViewById(R.id.rl_title13).setVisibility(View.GONE);
                startActivityForResult(new Intent(getBaseContext(), ActivityChooseClass.class).putExtra("flag", "1").putExtra("data", (Serializable) lt),0);
            }
        });

        if(code!=null){
            findViewById(R.id.finish).setVisibility(View.VISIBLE);
            findViewById(R.id.saishi).setVisibility(View.GONE);
        }else{
            findViewById(R.id.finish).setVisibility(View.GONE);
            findViewById(R.id.saishi).setVisibility(View.VISIBLE);
        }
        findViewById(R.id.finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Map m = getStu(userid, clubid);
                QueryBuilder.build("school_class/update_user_info")
                        .add("gender", m.get("gender") + "")
                        .add("school_club_id", clubid)
                        .add("phone", m.get("phone") + "")
                        .add("birthday", m.get("birthday") + "")
                        .add("school_class_id", getIntent().getStringExtra("id"))
                        .add("club_id", clubid)
                        .add("nickname",m.get("nickname"))
                        .add("user_id", m.get("user_id").toString()).post(new QueryBuilder.EnhancedCallback("response") {
                    @Override
                    public void onSuccessWithObject(String namelink, Object object) {
                        if (object.toString().equals("success")) {
                            List list = new ArrayList();
                            list.add(m);
                            setResult(1,new Intent().putExtra("maps", (Serializable) list));
                            finish();
                        }
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
        findViewById(R.id.saishi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Map m = getStu(userid, clubid);
                QueryBuilder.build("school_class/update_user_info")

//                        .add("gender", m.get("gender") + "")
//                        .add("school_club_id", clubid)
//                        .add("phone", m.get("phone") + "")
//                        .add("birthday", m.get("birthday") + "")
//                        .add("school_class_id", getIntent().getStringExtra("id"))
//                        .add("club_id", clubid)
//                        .add("nickname",m.get("nickname"))
//                        .add("user_id", m.get("user_id").toString())
//
                        .add("nickname", m.get("nickname") + "")
                        .add("gender", m.get("gender") + "")
                        .add("school_club_id", "null")
                        .add("phone", m.get("phone") + "")
                        .add("birthday", m.get("birthday") + "")
                        .add("school_class_id", "null")
                        .add("club_id", "null")
                        .add("user_id", m.get("user_id").toString()).post(new QueryBuilder.EnhancedCallback("response;msg") {
                    @Override
                    public void onSuccessWithObject(String namelink, Object object) {
                        if (namelink.equals("response")&&object.toString().equals("success")) {
                            setResult(2,new Intent().putExtra("map", (Serializable) m));
                            finish();
                        }else{
//                            Toast.makeText("");
                        }
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

    }

    @NonNull
    private Map getStu(String userid, String clubid) {
        final Map m = new TreeMap();
        m.put("nickname", ((TextView) findViewById(R.id.tv_stuInfo1)).getText());
        m.put("gender", ((TextView) findViewById(R.id.tv_stuInfo2)).getText().equals("男")?"GG":"MM");
        String birth = ((TextView) findViewById(R.id.tv_stuInfo3)).getText().toString();
        m.put("birthday", birth.equals("无")?"":birth);
        String phone = ((TextView) findViewById(R.id.tv_stuInfo5)).getText().toString();

        m.put("phone", phone.startsWith("暂无")?"":phone);

        m.put("club_id", clubid);
        m.put("school_club_id", clubid);
        m.put("school_class_id", cls_id==null?getIntent().getStringExtra("id"):cls_id);
        m.put("user_id", userid);
        m.put("avatar", "");
        return m;
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
        if(code==null){
            findViewById(R.id.rl_title13).setVisibility(View.VISIBLE);
        }
        if(data==null){
            return;
        }
        if (data.getStringExtra("flag") == null || data.getStringExtra("flag").equals("")) {
            String date = data.getStringExtra(Constant.KEY_CHOOSE_TIME);
            ((TextView) findViewById(R.id.tv_stuInfo3)).setText(date);
            findViewById(R.id.saishi).setVisibility(View.VISIBLE);

        } else {
            String data1 = data.getStringExtra("data");
            cls_id = data.getStringExtra("cls_id");
            ((TextView) findViewById(R.id.tv_stuInfo4)).setText(data1);
            findViewById(R.id.saishi).setVisibility(View.VISIBLE);

        }

    }
    String cls_id;
}
