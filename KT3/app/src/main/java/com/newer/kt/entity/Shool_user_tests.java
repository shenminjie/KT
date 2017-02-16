package com.newer.kt.entity;

/**
 * Created by leo on 17/2/9.
 */

public class Shool_user_tests {
//    response: "success",
//    rankings: [
//    {
//        total_scores: 总分,
//                skill_count: 技能数,
//            user_id: 用户id,
//            avatar: 用户头像,
//            nickname: 昵称,
//            cls: 班级,
//            grade: 年级,

    public String total_scores;
    public String skill_count;
    public String user_id;
    public String avatar;
    public String nickname;
    public String cls;
    public String grade;

    public Shool_user_tests(){

    }

    public Shool_user_tests(String total_scores, String skill_count, String user_id, String avatar, String nickname, String cls, String grade) {
        this.total_scores = total_scores;
        this.skill_count = skill_count;
        this.user_id = user_id;
        this.avatar = avatar;
        this.nickname = nickname;
        this.cls = cls;
        this.grade = grade;
    }

    public String getTotal_scores() {
        return total_scores;
    }

    public void setTotal_scores(String total_scores) {
        this.total_scores = total_scores;
    }

    public String getSkill_count() {
        return skill_count;
    }

    public void setSkill_count(String skill_count) {
        this.skill_count = skill_count;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCls() {
        return cls;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
