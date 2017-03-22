package com.newer.kt.ktmatch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.helper.ItemTouchHelper;
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
import com.newer.kt.ktmatch.json.JsonReplacer;
import com.smj.LocalDataManager;
import com.smj.saishi.SaishiItemRequest;
import com.smj.saishi.SaishiRequest;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.newer.kt.Refactor.ui.Avtivity.LoginActivity.PRE_CURRENT_CLUB_ID;
import static com.newer.kt.Refactor.ui.Avtivity.LoginActivity.PRE_CURRENT_USER_ID;

public class CountActivity extends ActivityScore {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ActivityKi.class));
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


        ((TextView) findViewById(R.id.left_ball_value)).setText(Params.getInstanceParam().getGoals1());
        ((TextView) findViewById(R.id.right_ball_value)).setText(Params.getInstanceParam().getGoals2());
        ((TextView) findViewById(R.id.left_pass_value)).setText(Params.getInstanceParam().getPannas1());
        ((TextView) findViewById(R.id.right_pass_value)).setText(Params.getInstanceParam().getPannas2());
//        ((TextView)findViewById(R.id.left_x_value)).setText(Params.getInstanceParam().getPannas1());
//        ((TextView)findViewById(R.id.right_x_value)).setText(Params.getInstanceParam().getPannas2());
        if (Params.getInstanceParam().getPanna_ko1().equals("1")) {
            (findViewById(R.id.left_kt_txt)).setBackgroundResource(R.drawable.score_kt2x);
        } else if (Params.getInstanceParam().getPanna_ko2().equals("1")) {
            (findViewById(R.id.right_kt_txt)).setBackgroundResource(R.drawable.score_kt2x);
        }
        findViewById(R.id.left_kt_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Params.getInstanceParam().getPanna_ko1().equals("0")) {
                    view.setBackgroundResource(R.drawable.score_kt2x);
                    Params.getInstanceParam().setPanna_ko1("1");
                } else if (Params.getInstanceParam().getPanna_ko1().equals("1")) {
                    view.setBackgroundResource(R.drawable.match_kt);
                    Params.getInstanceParam().setPanna_ko1("0");
                }
                if (Params.getInstanceParam().getPanna_ko2().equals("0")) {
                    findViewById(R.id.right_kt_txt).setBackgroundResource(R.drawable.score_kt2x);
                    Params.getInstanceParam().setPanna_ko2("1");
                } else if (Params.getInstanceParam().getPanna_ko2().equals("1")) {
                    findViewById(R.id.right_kt_txt).setBackgroundResource(R.drawable.match_kt);
                    Params.getInstanceParam().setPanna_ko2("0");
                }
            }
        });
        findViewById(R.id.right_kt_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Params.getInstanceParam().getPanna_ko2().equals("0")) {
                    view.setBackgroundResource(R.drawable.score_kt2x);
                    Params.getInstanceParam().setPanna_ko2("1");
                } else if (Params.getInstanceParam().getPanna_ko2().equals("1")) {
                    view.setBackgroundResource(R.drawable.match_kt);
                    Params.getInstanceParam().setPanna_ko2("0");
                }
                if (Params.getInstanceParam().getPanna_ko1().equals("0")) {
                    findViewById(R.id.left_kt_txt).setBackgroundResource(R.drawable.score_kt2x);
                    Params.getInstanceParam().setPanna_ko1("1");
                } else if (Params.getInstanceParam().getPanna_ko1().equals("1")) {
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

    public void submitKT(Params params) {
        int leftScore = (Integer.parseInt(Params.getInstanceParam().getGoals1()) + Integer.parseInt(Params.getInstanceParam().getPannas1()));
        int rightScore = (Integer.parseInt(Params.getInstanceParam().getGoals2()) + Integer.parseInt(Params.getInstanceParam().getPannas2()));
        int result = 0;
        if (leftScore > rightScore) {
            result = 1;
            Params.getInstanceParam().setSide_a__result(1 + "");
            Params.getInstanceParam().setSide_b__result(-1 + "");
        } else if (leftScore < rightScore) {
            result = -1;
            Params.getInstanceParam().setSide_b__result(1 + "");
            Params.getInstanceParam().setSide_a__result(-1 + "");
        } else {
            Params.getInstanceParam().setSide_b__result(0 + "");
            Params.getInstanceParam().setSide_a__result(0 + "");
        }


        String url = Constants.KTHOST + "games/post_result";
        QueryBuilder qb = QueryBuilder.build(url);

        String clubid = "" + PreferenceManager.getDefaultSharedPreferences(getBaseContext())
                .getLong(PRE_CURRENT_CLUB_ID, 1);
        Params.getInstanceParam().setClub_id(clubid);

        String userid = "" + PreferenceManager.getDefaultSharedPreferences(getBaseContext())
                .getLong(PRE_CURRENT_USER_ID, 1);
        Params.getInstanceParam().setUser_id(userid);

        Params.getInstanceParam().setTime(new SimpleDateFormat("yyyy-MM-dd HH:ss").format(new Date()));
        Field[] fs = params.getClass().getDeclaredFields();
        for (Field f : fs) {
            f.setAccessible(true);
            if (f.getName().contains("params")) {
                continue;
            }
            if (f.getName().contains("serialVersionUID")) {
                continue;
            }
            try {
                Object oval = f.get(params);
                if (oval == null) {
                    continue;
                }
                String val = oval.toString();
                qb.add(f.getName(), val);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        String qbjson;
        SharedPreferences.Editor ed = (SharedPreferences.Editor) PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit();
        ed.putString("game_id:" + qb.getMap().get("game_id").toString(), qbjson = qb.getJson());
        ed.commit();
//        String raw = "{" +
//
//                "'club_id':'俱乐部ID','"+
//
//                "user_id':'当前裁判ID','" +
//
//                "game_id':'赛事ID','" +
//
//                "code':'气场二维码','" +
//
//                "game_type':'0','" +
//
//                "youku_uri':'优酷视频地址','" +
//
//                "time':'比赛时间(格式 2016-01-01 17:00)','" +
//
//                "side_a':{'" +
//
//                "  users':[*],'" +
//
//                "  add_scores':'增加的积分','" +
//
//                "  result':'结果(胜者为 1 败者 -1 平局为0)','" +
//
//                "  goals':'进球数','" +
//
//                "  pannas':'穿裆数','" +
//
//                "  fouls':'犯规数','" +
//
//                "  flagrant_fouls':'恶意犯规数','" +
//
//                "  panna_ko':'是否穿裆KT(0 否 1 是)','" +
//
//                "  abstained':'是否放弃比赛(0 否 1 是)','" +
//
//                "  picture':'比赛图片'" +
//
//                "},'" +
//
//                "side_b':{'" +
//
//                "  users':[*],'" +
//
//                "  add_scores':'增加的积分','" +
//
//                "  result':'结果(胜者为 1 败者 -1 平局为0)','" +
//
//                "  goals':'进球数','" +
//
//                "  pannas':'穿裆数','" +
//
//                "  fouls':'犯规数','" +
//
//                "  flagrant_fouls':'恶意犯规数','" +
//
//                "  panna_ko':'是否穿裆KT(0 否 1 是)','" +
//
//                "  abstained':'是否放弃比赛(0 否 1 是)','" +
//
//                "  picture':'比赛图片'" +
//
//                "}" +
//                "}" ;
        String users = "";
        String user = Params.getInstanceParam().getSide_a_users_1();
        if (user != null && !user.equals("")) {
            Params.getInstanceParam().getSide_a__users().add(user);
        }
        user = Params.getInstanceParam().getSide_a_users_2();
        if (user != null && !user.equals("")) {
            Params.getInstanceParam().getSide_a__users().add(user);
        }
        user = Params.getInstanceParam().getSide_a_users_3();
        if (user != null && !user.equals("")) {
            Params.getInstanceParam().getSide_a__users().add(user);
        }

        users = "";
        user = Params.getInstanceParam().getSide_b_users_1();
        if (user != null && !user.equals("")) {
            Params.getInstanceParam().getSide_b__users().add(user);
        }
        user = Params.getInstanceParam().getSide_b_users_2();
        if (user != null && !user.equals("")) {
            Params.getInstanceParam().getSide_b__users().add(user);
        }
        user = Params.getInstanceParam().getSide_b_users_3();
        if (user != null && !user.equals("")) {
            Params.getInstanceParam().getSide_b__users().add(user);
        }
        users = "[" + users.substring(1) + "]";

//        String side_a =
//                "side_a':{'" +
//
//                        "  users':" + Params.getInstanceParam().getSide_a__users() +
//                        ",'" +
//
//                        "  add_scores':'" + Params.getInstanceParam().getSide_a__add_scores() +
//                        "','" +
//
//                        "  result':'" + Params.getInstanceParam().getSide_a__result() +
//                        "','" +
//
//                        "  goals':'" + Params.getInstanceParam().getGoals1() +
//                        "','" +
//
//                        "  pannas':'" + Params.getInstanceParam().getPannas1() +
//                        "','" +
//
//                        "  fouls':'" + "" +
//                        "','" +
//
//                        "  flagrant_fouls':'" + Params.getInstanceParam().getSide_a__flagrant_fouls() +
//                        "','" +
//
//                        "  panna_ko':'" + Params.getInstanceParam().getPanna_ko1() +
//                        "','" +
//
//                        "  abstained':'" + Params.getInstanceParam().getSide_a__abstained() +
//                        "','" +
//
//                        "  picture':''" +
//
//                        "}";
//
//
//        String side_b =
//                "side_b':{'" +
//
//                        "  users':" + Params.getInstanceParam().getSide_b__users() +
//                        ",'" +
//
//                        "  add_scores':'" + Params.getInstanceParam().getSide_b__add_scores() +
//                        "','" +
//
//                        "  result':'" + Params.getInstanceParam().getSide_b__result() +
//                        "','" +
//
//                        "  goals':'" + Params.getInstanceParam().getGoals2() +
//                        "','" +
//
//                        "  pannas':'" + Params.getInstanceParam().getPannas2() +
//                        "','" +
//
//                        "  fouls':'" + "" +
//                        "','" +
//
//                        "  flagrant_fouls':'" + Params.getInstanceParam().getSide_b__flagrant_fouls()+
//        "','" +
//
//                "  panna_ko':'" + Params.getInstanceParam().getPanna_ko1() +
//                "','" +
//
//                "  abstained':'" + Params.getInstanceParam().getSide_b__abstained()+
//        "','" +
//
//                "  picture':''" +
//
//                "}";


//                String replacement = new JsonReplacer(qb.getMap()).fromJsonObject(raw);
        SaishiItemRequest item_side_a = new SaishiItemRequest();
        item_side_a.setAbstained(Params.getInstanceParam().getSide_a__abstained());
        item_side_a.setAdd_scores(Params.getInstanceParam().getSide_a__add_scores());
        item_side_a.setFlagrant_fouls(Params.getInstanceParam().getSide_a__flagrant_fouls());
        item_side_a.setFouls("");
        item_side_a.setGoals(Params.getInstanceParam().getGoals1());
        item_side_a.setPanna_ko(Params.getInstanceParam().getPanna_ko1());
        item_side_a.setPannas(Params.getInstanceParam().getPannas1());
        item_side_a.setPicture("");
        item_side_a.setResult(Params.getInstanceParam().getSide_a__result());
        item_side_a.setUsers(Params.getInstanceParam().getSide_a__users());

        SaishiItemRequest item_side_b = new SaishiItemRequest();
        item_side_b.setAbstained(Params.getInstanceParam().getSide_b__abstained());
        item_side_b.setAdd_scores(Params.getInstanceParam().getSide_b__add_scores());
        item_side_b.setFlagrant_fouls(Params.getInstanceParam().getSide_b__flagrant_fouls());
        item_side_b.setFouls("");
        item_side_b.setGoals(Params.getInstanceParam().getGoals2());
        item_side_b.setPanna_ko(Params.getInstanceParam().getPanna_ko2());
        item_side_b.setPannas(Params.getInstanceParam().getPannas2());
        item_side_b.setPicture("");
        item_side_b.setResult(Params.getInstanceParam().getSide_b__result());
        item_side_b.setUsers(Params.getInstanceParam().getSide_b__users());

        SaishiRequest srequest = new SaishiRequest();
        srequest.setClub_id(clubid);
        srequest.setCode(Params.getInstanceParam().getCode());
        srequest.setGame_id(qb.getMap().get("game_id").toString());
        srequest.setGame_type(Params.getInstanceParam().getGame_type());
        srequest.setSide_a(item_side_a);
        srequest.setSide_b(item_side_b);
        srequest.setTime(new SimpleDateFormat("yyyy-MM-dd HH:ss").format(new Date()));
        srequest.setUser_id(userid);
        LocalDataManager.saveSaishiInfo(srequest,Params.getInstanceParam().getVideoPath(),"比赛 "+srequest.getTime());
//        x.http().post(qb.get(), new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
////                        { response: "success", user_id: 用户ID, match_id: 用户快速参赛的号码 }
////                showDialogToast(result);
//
//
//                Gson gson = new Gson();
//                //1. 获得 解析者
//                JsonParser parser = new JsonParser();
//
//                //2. 获得 根节点元素
//                JsonElement element = parser.parse(result);
//
//                //3. 根据 文档判断根节点属于 什么类型的 Gson节点对象
//                JsonObject root = element.getAsJsonObject();
//
//                //4. 取得 节点 下 的某个节点的 value
//                JsonPrimitive flagjson = root.getAsJsonPrimitive("response");
//                String flag = flagjson.getAsString();
//
//                if("success".equals(flag)){
//
//                    JsonPrimitive matchidj = root.getAsJsonPrimitive("match_id");
//                    String matchid = matchidj.getAsString();
//                }
//            }
////
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//
//            }
//
//            @Override
//            public void onFinished() {
//
//
//            }
//        });
    }

}
