/**
  * Copyright 2017 bejson.com 
  */
package com.smj.dakejian;
import java.io.Serializable;
import java.util.List;
/**
 * Auto-generated: 2017-03-20 19:32:46
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DakejianBasicInfo implements Serializable{

    @Override
    public String toString() {
        return "DakejianBasicInfo{" +
                "response='" + response + '\'' +
                ", name='" + name + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", duration=" + duration +
                ", strength='" + strength + '\'' +
                ", requirement='" + requirement + '\'' +
                ", skills=" + skills +
                ", actions=" + actions +
                '}';
    }

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String response;
    private String name;
    private String videoUrl;
    private int duration;
    private String strength;
    private String requirement;
    private List<Skills> skills;
    private List<Actions> actions;
    public void setResponse(String response) {
         this.response = response;
     }
     public String getResponse() {
         return response;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setVideoUrl(String videoUrl) {
         this.videoUrl = videoUrl;
     }
     public String getVideoUrl() {
         return videoUrl;
     }

    public void setDuration(int duration) {
         this.duration = duration;
     }
     public int getDuration() {
         return duration;
     }

    public void setStrength(String strength) {
         this.strength = strength;
     }
     public String getStrength() {
         return strength;
     }

    public void setRequirement(String requirement) {
         this.requirement = requirement;
     }
     public String getRequirement() {
         return requirement;
     }

    public void setSkills(List<Skills> skills) {
         this.skills = skills;
     }
     public List<Skills> getSkills() {
         return skills;
     }

    public void setActions(List<Actions> actions) {
         this.actions = actions;
     }
     public List<Actions> getActions() {
         return actions;
     }

}