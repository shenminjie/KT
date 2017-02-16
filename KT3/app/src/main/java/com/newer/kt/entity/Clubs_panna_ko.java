package com.newer.kt.entity;

/**
 * Created by leo on 17/1/16.
 */

public class Clubs_panna_ko {
   // panna_ko: { user_id: 用户ID, nickname: 昵称 } 或 { league_id: 战队ID, name: 战队名称 },
    public String league_id;
    public String name;

    public Clubs_panna_ko(){

    }

    public Clubs_panna_ko(String league_id, String name) {
        this.league_id = league_id;
        this.name = name;
    }

    public String getLeague_id() {
        return league_id;
    }

    public void setLeague_id(String league_id) {
        this.league_id = league_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
