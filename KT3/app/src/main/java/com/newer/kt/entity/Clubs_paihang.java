package com.newer.kt.entity;

/**
 * Created by leo on 17/1/10.
 */

public class Clubs_paihang {

    private String response;
    private String user_id;
    private String nickname;
    private String avatar;
    private String power;
    private String win_rate;
    private String scores;
    public Clubs_paihang() {

    }

    public Clubs_paihang(String response, String user_id, String nickname, String avatar, String power, String win_rate,String scores) {
        this.response = response;
        this.user_id = user_id;
        this.nickname = nickname;
        this.avatar = avatar;
        this.power = power;
        this.win_rate = win_rate;
        this.scores=scores;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getWin_rate() {
        return win_rate;
    }

    public void setWin_rate(String win_rate) {
        this.win_rate = win_rate;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }
}
