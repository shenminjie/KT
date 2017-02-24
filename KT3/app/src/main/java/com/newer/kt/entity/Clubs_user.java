package com.newer.kt.entity;

/**
 * Created by leo on 17/1/16.
 */

public class Clubs_user {
    //user_id: 用户ID, nickname: 昵称, grade: "等级", avatar: "头像URL", scores: "积分"
    public String user_id;
    public String nickname;
    public String grade;
    public String avatar;
    public String scores;

    public Clubs_user(){

    }

    public Clubs_user(String user_id, String nickname, String grade, String avatar, String scores) {
        this.user_id = user_id;
        this.nickname = nickname;
        this.grade = grade;
        this.avatar = avatar;
        this.scores = scores;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }
}
