/**
  * Copyright 2017 bejson.com 
  */
package com.smj.skillbean;

import java.io.Serializable;

/**
 * Auto-generated: 2017-03-18 14:23:13
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class SkillInfo implements Serializable{

    private int schoolFootballSkillId;
    private String name;
    private int level;

    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public void setSchoolFootballSkillId(int schoolFootballSkillId) {
         this.schoolFootballSkillId = schoolFootballSkillId;
     }
     public int getSchoolFootballSkillId() {
         return schoolFootballSkillId;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setLevel(int level) {
         this.level = level;
     }
     public int getLevel() {
         return level;
     }

    @Override
    public String toString() {
        return "SkillInfo{" +
                "schoolFootballSkillId=" + schoolFootballSkillId +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", isChecked=" + isChecked +
                '}';
    }
}