package com.newer.kt.ktmatch;

/**
 * Created by admin on 2017/3/1.
 */

public class Params {
    static Params params = new Params();

    public static Params getInstanceParam() {
        return params;
    }

    String game_type;
    String judge_type;
    String result;
    String uid1;
    String uid2;
    String game_id;
    String code;
    String goals1 = "";
    String goals2 = "";
    String fouls1;

    String flagrant_fouls1;
    String flagrant_fouls2;
    String pannas1;
    String pannas2;
    String panna_ko1 = "0";
    String panna_ko2 = "0";
    String abstained1 = "0";
    String abstained2 = "0";
    String battle_id;

    public String getGame_type() {
        return game_type;
    }

    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }

    public String getJudge_type() {
        return judge_type;
    }

    public void setJudge_type(String judge_type) {
        this.judge_type = judge_type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUid1() {
        return uid1;
    }

    public void setUid1(String uid1) {
        this.uid1 = uid1;
    }

    public String getUid2() {
        return uid2;
    }

    public void setUid2(String uid2) {
        this.uid2 = uid2;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGoals1() {
        return goals1;
    }
    public String Goals1Plus() {
        return goals1 = ((Integer.parseInt(goals1)+1)+"");
    }
    public String Goals2Plus() {
        return goals2 = ((Integer.parseInt(goals2)+1)+"");
    }
    public String Goals1Minus() {
        return goals1 = ((Integer.parseInt(goals1)-1)+"");
    }
    public String Goals2Minus() {
        return goals2 = ((Integer.parseInt(goals2)-1)+"");
    }

    public String pannas1Plus() {
        return pannas1 = ((Integer.parseInt(pannas1)+1)+"");
    }
    public String pannas2Plus() {
        return pannas2 = ((Integer.parseInt(pannas2)+1)+"");
    }
    public String pannas1Minus() {
        return pannas1 = ((Integer.parseInt(pannas1)+1)+"");
    }
    public String pannas2Minus() {
        return pannas2 = ((Integer.parseInt(pannas2)+1)+"");
    }

    public void setGoals1(String goals1) {
        this.goals1 = goals1;
    }

    public String getGoals2() {
        return goals2;
    }

    public void setGoals2(String goals2) {
        this.goals2 = goals2;
    }

    public String getFouls1() {
        return fouls1;
    }

    public void setFouls1(String fouls1) {
        this.fouls1 = fouls1;
    }

    public String getFlagrant_fouls1() {
        return flagrant_fouls1;
    }

    public void setFlagrant_fouls1(String flagrant_fouls1) {
        this.flagrant_fouls1 = flagrant_fouls1;
    }

    public String getFlagrant_fouls2() {
        return flagrant_fouls2;
    }

    public void setFlagrant_fouls2(String flagrant_fouls2) {
        this.flagrant_fouls2 = flagrant_fouls2;
    }

    public String getPannas1() {
        return pannas1;
    }

    public void setPannas1(String pannas1) {
        this.pannas1 = pannas1;
    }

    public String getPannas2() {
        return pannas2;
    }

    public void setPannas2(String pannas2) {
        this.pannas2 = pannas2;
    }

    public String getPanna_ko1() {
        return panna_ko1;
    }

    public void setPanna_ko1(String panna_ko1) {
        this.panna_ko1 = panna_ko1;
    }

    public String getPanna_ko2() {
        return panna_ko2;
    }

    public void setPanna_ko2(String panna_ko2) {
        this.panna_ko2 = panna_ko2;
    }

    public String getAbstained1() {
        return abstained1;
    }

    public void setAbstained1(String abstained1) {
        this.abstained1 = abstained1;
    }

    public String getAbstained2() {
        return abstained2;
    }

    public void setAbstained2(String abstained2) {
        this.abstained2 = abstained2;
    }

    public String getBattle_id() {
        return battle_id;
    }

    public void setBattle_id(String battle_id) {
        this.battle_id = battle_id;
    }
}
