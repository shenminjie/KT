/**
  * Copyright 2017 bejson.com 
  */
package com.smj.gradlebean;
/**
 * Auto-generated: 2017-03-18 9:43:12
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Users {

    private int userId;
    private String nickname;
    private String gender;
    private String birthday;
    private String phone;
    private int height;
    private int weight;
    private String clzId;

    boolean isChecked;

    public String getClzId() {
        return clzId;
    }

    public void setClzId(String clzId) {
        this.clzId = clzId;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void setUserId(int userId) {
         this.userId = userId;
     }
     public int getUserId() {
         return userId;
     }

    public void setNickname(String nickname) {
         this.nickname = nickname;
     }
     public String getNickname() {
         return nickname;
     }

    public void setGender(String gender) {
         this.gender = gender;
     }
     public String getGender() {
         return gender;
     }

    public void setBirthday(String birthday) {
         this.birthday = birthday;
     }
     public String getBirthday() {
         return birthday;
     }

    public void setPhone(String phone) {
         this.phone = phone;
     }
     public String getPhone() {
         return phone;
     }

    public void setHeight(int height) {
         this.height = height;
     }
     public int getHeight() {
         return height;
     }

    public void setWeight(int weight) {
         this.weight = weight;
     }
     public int getWeight() {
         return weight;
     }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phone='" + phone + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}