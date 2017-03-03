package com.newer.kt.ktmatch;


import android.os.Bundle;
import android.os.Message;

import com.frame.app.base.activity.BaseActivity;

/**
 * Created by leo on 17/3/3.
 */

public class MainActvity extends BaseActivity {


    @Override
    protected void initHandler(Message msg) {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {String raw = "{" +

            "'club_id':'俱乐部ID','"+

            "user_id':'当前裁判ID','" +

            "game_id':'赛事ID','" +

            "code':'气场二维码','" +

            "game_type':'0','" +

            "youku_uri':'优酷视频地址','" +

            "time':'比赛时间(格式 2016-01-01 17:00)','" +

            "side_a':{'" +

            "  users':' 用户1ID 用户2ID(2v2情况) 用户3ID(3v3情况) ','" +

            "  add_scores':'增加的积分','" +

            "  result':'结果(胜者为 1 败者 -1 平局为0)','" +

            "  goals':'进球数','" +

            "  pannas':'穿裆数','" +

            "  fouls':'犯规数','" +

            "  flagrant_fouls':'恶意犯规数','" +

            "  panna_ko':'是否穿裆KT(0 否 1 是)','" +

            "  abstained':'是否放弃比赛(0 否 1 是)','" +

            "  picture':'比赛图片'" +

            "},'" +

            "side_b':'" +

            "  同side_a" +

            "'" +

            "  }";




    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
