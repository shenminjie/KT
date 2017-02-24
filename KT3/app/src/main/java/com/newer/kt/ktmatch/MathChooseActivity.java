package com.newer.kt.ktmatch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.newer.kt.R;
import com.newer.kt.Refactor.Constants;
import com.newer.kt.Refactor.utils.MD5;
import com.newer.kt.record.TakePicActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import shengchengerweima.CamScanActivity;

import static com.newer.kt.Refactor.ui.Avtivity.LoginActivity.PRE_CURRENT_CLUB_ID;


public class MathChooseActivity extends CamScanActivity {

    int index = R.id.leftid;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_choose);
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inSampleSize = 2;
        Bitmap bp = BitmapFactory.decodeResource(getResources(),R.drawable.team_battle_0,op);
        findViewById(R.id.action_match_choose).setBackground(new BitmapDrawable(bp));
        findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.menu).setVisibility(View.GONE);
            }
        });
        findViewById(R.id.saomiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invokeCap();



            }
        });
        findViewById(R.id.leftportrait).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                index = R.id.leftid;
                genMatch_Id(index);
                return false;
            }
        });
        findViewById(R.id.rightportrait).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                index = R.id.rightid;
                genMatch_Id(index);
                return false;
            }
        });
        findViewById(R.id.leftportrait).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.menu).setVisibility(View.VISIBLE);
                index = R.id.leftid;
            }
        });
        findViewById(R.id.rightportrait).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.menu).setVisibility(View.VISIBLE);
                index = R.id.rightid;
            }
        });
        findViewById(R.id.ready).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TakePicActivity.invoke(getBaseContext());
                finish();
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    int preparedCount = 2;

    @Override
    public void recvCode(String result) {
        super.recvCode(result);
        ((TextView)findViewById(index)).setText(result);

        checkPrepared(index);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        findViewById(R.id.menu).setVisibility(View.GONE);
    }

    public void genMatch_Id(final int index){

                    String url = Constants.KTHOST + "users/f_register";
                    RequestParams p = new RequestParams(url);

                    String clubid = ""+PreferenceManager.getDefaultSharedPreferences(MathChooseActivity.this)
                            .getLong(PRE_CURRENT_CLUB_ID,-1);
                    p.addQueryStringParameter("club_id", ""+clubid);
        p.addQueryStringParameter("authenticity_token", MD5.getToken(url));
                    x.http().post(p, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
//                        { response: "success", user_id: 用户ID, match_id: 用户快速参赛的号码 }
//                showDialogToast(result);


                            Gson gson = new Gson();
                            //1. 获得 解析者
                            JsonParser parser = new JsonParser();

                            //2. 获得 根节点元素
                            JsonElement element = parser.parse(result);

                            //3. 根据 文档判断根节点属于 什么类型的 Gson节点对象
                            JsonObject root = element.getAsJsonObject();

                            //4. 取得 节点 下 的某个节点的 value
                            JsonPrimitive flagjson = root.getAsJsonPrimitive("response");
                            String flag = flagjson.getAsString();

                            if("success".equals(flag)){

                                JsonPrimitive matchidj = root.getAsJsonPrimitive("match_id");
                                String matchid = matchidj.getAsString();
                                ((TextView)findViewById(index)).setText(matchid);
                                checkPrepared(index);
                            }
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
    public void checkPrepared(int index){
        switch(index){
            case R.id.leftid:
                findViewById(R.id._1v1_left).setVisibility(View.VISIBLE);
                break;
            case R.id.rightid:
                findViewById(R.id._1v1_right).setVisibility(View.VISIBLE);
                break;
        }
        preparedCount--;
        if(preparedCount<=0){
            findViewById(R.id.ready).setVisibility(View.VISIBLE);
        }
    }


}
