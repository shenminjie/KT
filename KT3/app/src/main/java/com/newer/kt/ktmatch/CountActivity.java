package com.newer.kt.ktmatch;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.newer.kt.R;
import com.newer.kt.Refactor.Constants;
import com.newer.kt.Refactor.utils.MD5;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Field;

import static com.newer.kt.Refactor.ui.Avtivity.LoginActivity.PRE_CURRENT_CLUB_ID;

public class CountActivity extends ActivityScore {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),ActivityKi.class));
                submitKT(Params.getInstanceParam());
                finish();
            }
        });
        findViewById(R.id.cancel_submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        ((TextView)findViewById(R.id.left_ball_value)).setText(Params.getInstanceParam().getGoals1());
        ((TextView)findViewById(R.id.right_ball_value)).setText(Params.getInstanceParam().getGoals2());
        ((TextView)findViewById(R.id.left_pass_value)).setText(Params.getInstanceParam().getPannas1());
        ((TextView)findViewById(R.id.right_pass_value)).setText(Params.getInstanceParam().getPannas2());
//        ((TextView)findViewById(R.id.left_x_value)).setText(Params.getInstanceParam().getPannas1());
//        ((TextView)findViewById(R.id.right_x_value)).setText(Params.getInstanceParam().getPannas2());
        if(Params.getInstanceParam().getPanna_ko1().equals("1")){
            (findViewById(R.id.left_kt_txt)).setBackgroundResource(R.drawable.score_kt2x);
        }else if(Params.getInstanceParam().getPanna_ko2().equals("1")){
            (findViewById(R.id.right_kt_txt)).setBackgroundResource(R.drawable.score_kt2x);
        }
        findViewById(R.id.left_kt_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Params.getInstanceParam().getPanna_ko1().equals("0")){
                    view.setBackgroundResource(R.drawable.score_kt2x);
                    Params.getInstanceParam().setPanna_ko1("1");
                }else if(Params.getInstanceParam().getPanna_ko1().equals("1")){
                    view.setBackgroundResource(R.drawable.match_kt);
                    Params.getInstanceParam().setPanna_ko1("0");
                }
                if(Params.getInstanceParam().getPanna_ko2().equals("0")){
                    findViewById(R.id.right_kt_txt).setBackgroundResource(R.drawable.score_kt2x);
                    Params.getInstanceParam().setPanna_ko2("1");
                }else if(Params.getInstanceParam().getPanna_ko2().equals("1")){
                    findViewById(R.id.right_kt_txt).setBackgroundResource(R.drawable.match_kt);
                    Params.getInstanceParam().setPanna_ko2("0");
                }
            }
        });
        findViewById(R.id.right_kt_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Params.getInstanceParam().getPanna_ko2().equals("0")){
                    view.setBackgroundResource(R.drawable.score_kt2x);
                    Params.getInstanceParam().setPanna_ko2("1");
                }else if(Params.getInstanceParam().getPanna_ko2().equals("1")){
                    view.setBackgroundResource(R.drawable.match_kt);
                    Params.getInstanceParam().setPanna_ko2("0");
                }
                if(Params.getInstanceParam().getPanna_ko1().equals("0")){
                    findViewById(R.id.left_kt_txt).setBackgroundResource(R.drawable.score_kt2x);
                    Params.getInstanceParam().setPanna_ko1("1");
                }else if(Params.getInstanceParam().getPanna_ko1().equals("1")){
                    findViewById(R.id.left_kt_txt).setBackgroundResource(R.drawable.match_kt);
                    Params.getInstanceParam().setPanna_ko1("0");
                }
            }
        });
    }
//    提交结果(post)(用于比赛结束)
//    接口地址: http://www.ktfootball.com/apiv2/games/post_result
//    参数：
//    game_type: 0 或者 1 (0: 1v1, 1: 2v2, 2: 3v3)
//    judge_type: 0 或者1 ( 0: 自动, 1: 人工 )
//    result: 0: 平局, 1:用户1胜, -1: 用户1输
//    uid1: A方 ID (1v1的情况传user1_id, 2v2,3v3的情况下传 league1_id)
//    uid2: B方 ID (1v1的情况传user2_id, 2v2,3v3的情况下传 league2_id)
//    game_id: 赛事ID
//    code: 气场二维码
//    goals1: 进球数(用户1或战队1)
//    goals2: 进球数(用户2或战队2)
//    fouls1: 犯规数(用户1或战队1)   //buchuan
//    fouls2: 犯规数(用户1或战队1)   //buchuan
//    flagrant_fouls1: 恶意犯规数 (用户1或战队1) //buchuan
//    flagrant_fouls2: 恶意犯规数 (用户1或战队1) //buchuan
//    pannas1: 穿裆数 (用户1或战队1)
//    pannas2: 穿裆数 (用户1或战队1)
//    panna_ko1: 是否穿裆KT (0: 否 1: 是)
//    panna_ko2: 是否穿裆KT (0: 否 1: 是)
//    abstained1: 是否放弃比赛 (0: 否 1: 是)
//    abstained2: 是否放弃比赛 (0: 否 1: 是)
//    battle_id: 对战ID   //UU
//
//    参数例子:
//    { game_type: 0, judge_type: 0, result: 1, uid1: 39393, uid2: 39000, game_id: 256, code: "KT-CP-QC-140617-001", goals1: 10, goals2: 5, fouls1: 0, fouls2: 0, flagrant_fouls1: 0, flagrant_fouls2: 0, pannas1: 5, pannas2: 5, panna_ko1: 0, panna_ko2: 0, abstained1: 0, abstained2: 0, battle_id: 100}
//
//    成功返回:
//    { response: "success",battle_id: 对战ID ,user1_change_power: 用户1战斗力变化, user2_change_power: 用户2战斗力变化, user3_change_power: 用户3战斗力变化, user4_change_power: 用户4战斗力变化, user5_change_power: 用户5战斗力变化, user6_change_power: 用户6战斗力变化, user1_power: 用户1战斗力, user2_power: 用户2战斗力, user3_power: 用户3战斗力, user4_power: 用户4战斗力, user5_power: 用户5战斗力,user6_power: 用户6战斗力, user1_winrate: 用户1胜率， user2_winrate: 用户2胜率, user3_winrate: 用户3胜率， user4_winrate: 用户4胜率, user5_winrate: 用户5胜率, user6_winrate: 用户6胜率  }
//    注意: 如果是 1v1 则读取 user1 和 user2 的数据
//    失败返回
//    { response: "error", msg: "错误信息"  }

    public void submitKT(Params params){

        String url = Constants.KTHOST + "apiv2/games/post_result";
        RequestParams p = new RequestParams(url);

        String clubid = ""+ PreferenceManager.getDefaultSharedPreferences(getBaseContext())
                .getLong(PRE_CURRENT_CLUB_ID,1);
        Field[] fs = params.getClass().getDeclaredFields();
        for(Field f:fs){
            f.setAccessible(true);
            if(f.getName().contains("params")){
                continue;
            }
            if(f.getName().contains("serialVersionUID")){
                continue;
            }
            try {
                Object oval =  f.get(params);
                if(oval==null){
                    continue;
                }
                String val = oval.toString();
                p.addQueryStringParameter(f.getName(), val);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
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
                }
            }
//
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
}
