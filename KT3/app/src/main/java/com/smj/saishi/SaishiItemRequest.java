package com.smj.saishi;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * Created by MonkeyShen on 2017/3/22.
 * Mail:shenminjie92@sina.com
 */

public class SaishiItemRequest implements Serializable{
    private List<String> users;
    private String add_scores;
    private String result;
    private String goals;
    private String pannas;
    private String fouls;
    private String flagrant_fouls;
    private String panna_ko;
    private String abstained;
    private String picture;

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public String getAdd_scores() {
        return add_scores;
    }

    public void setAdd_scores(String add_scores) {
        this.add_scores = add_scores;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getPannas() {
        return pannas;
    }

    public void setPannas(String pannas) {
        this.pannas = pannas;
    }

    public String getFouls() {
        return fouls;
    }

    public void setFouls(String fouls) {
        this.fouls = fouls;
    }

    public String getFlagrant_fouls() {
        return flagrant_fouls;
    }

    public void setFlagrant_fouls(String flagrant_fouls) {
        this.flagrant_fouls = flagrant_fouls;
    }

    public String getPanna_ko() {
        return panna_ko;
    }

    public void setPanna_ko(String panna_ko) {
        this.panna_ko = panna_ko;
    }

    public String getAbstained() {
        return abstained;
    }

    public void setAbstained(String abstained) {
        this.abstained = abstained;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "SaishiItemRequest{" +
                "users=" + users +
                ", add_scores='" + add_scores + '\'' +
                ", result='" + result + '\'' +
                ", goals='" + goals + '\'' +
                ", pannas='" + pannas + '\'' +
                ", fouls='" + fouls + '\'' +
                ", flagrant_fouls='" + flagrant_fouls + '\'' +
                ", panna_ko='" + panna_ko + '\'' +
                ", abstained='" + abstained + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
