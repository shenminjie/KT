package com.newer.kt.entity;

/**
 * Created by leo on 17/3/13.
 */

public class Club_Info {
//    成功返回：
//    {
//        response: "success",
//                name: 校园名称,
//            school_student_count: 学生数,
//            avatar: 俱乐部头像,
//            higher_manager: 上级管理员
//    }

    public String response;
    public String name;
    public String avatar;
    public String school_student_count;
    public String higher_manager;

    public Club_Info(){

    }



    public Club_Info(String response, String name, String avatar, String school_student_count, String higher_manager) {
        this.response = response;
        this.name = name;
        this.avatar = avatar;
        this.school_student_count = school_student_count;
        this.higher_manager = higher_manager;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool_student_count() {
        return school_student_count;
    }

    public void setSchool_student_count(String school_student_count) {
        this.school_student_count = school_student_count;
    }

    public String getHigher_manager() {
        return higher_manager;
    }

    public void setHigher_manager(String higher_manager) {
        this.higher_manager = higher_manager;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Club_Info{" +
                "response='" + response + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", school_student_count='" + school_student_count + '\'' +
                ", higher_manager='" + higher_manager + '\'' +
                '}';
    }
}
