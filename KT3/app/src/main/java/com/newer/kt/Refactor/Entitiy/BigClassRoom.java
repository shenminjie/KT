package com.newer.kt.Refactor.Entitiy;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/1/20.
 */
public class BigClassRoom implements Serializable{

    private String shool_big_classroom_id;//大课间id,
    private String name;//大课间名称,
    private String duration;//时长,
    private String strength;// 强度
    private String video_url;// 强度
    private String requirement;// 强度
    private List<Skill> skills;// 强度
    private List<Action> actions;// 强度

    public String getShool_big_classroom_id() {
        return shool_big_classroom_id;
    }

    public void setShool_big_classroom_id(String shool_big_classroom_id) {
        this.shool_big_classroom_id = shool_big_classroom_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}

