/**
 * Copyright 2017 bejson.com
 */
package com.smj.gradlebean;

import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2017-03-18 9:43:12
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Classes implements Serializable{

    private int id;
    private String cls;
    private List<Users> users;

    //年级
    private int grade;

    private boolean isExpand;


    boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public String getCls() {
        return cls;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public List<Users> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "Classes{" +
                "id=" + id +
                ", cls='" + cls + '\'' +
                ", users=" + users +
                '}';
    }

    /**
     * 获取班别
     *
     * @return
     */
    public String getClassName() {
        if (grade == 1) {
            return "小班" + cls + "班";
        } else if (grade == 2) {
            return "中班" + cls + "班";
        } else if (grade == 3) {
            return "大班" + cls + "班";
        } else if (grade == 4) {
            return "一年级" + cls + "班";
        } else if (grade == 5) {
            return "二年级" + cls + "班";
        } else if (grade == 6) {
            return "三年级" + cls + "班";
        } else if (grade == 7) {
            return "四年级" + cls + "班";
        } else if (grade == 8) {
            return "五年级" + cls + "班";
        } else if (grade == 9) {
            return "六年级" + cls + "班";
        } else if (grade == 10) {
            return "初一" + cls + "班";
        } else if (grade == 11) {
            return "初二" + cls + "班";
        }
        return "";
    }
}