package com.newer.kt.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * Created by MonkeyShen on 2017/3/16.
 * Mail:shenminjie92@sina.com
 */

public class StudySkillInfo implements Serializable {
    private String name;
    private String response;
    private String id;
    private String intro;
    private String description;
    private String avatar;
    private String ori_price;
    private String price;
    private List<LessonInfo> lessons;
    private List<VideoInfo> videos;
    private String finished_times;
    private String finished_minutes;
    private String now_level_name;
    private String now_level_color;
    private String now_level_progress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getOri_price() {
        return ori_price;
    }

    public void setOri_price(String ori_price) {
        this.ori_price = ori_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<LessonInfo> getLessons() {
        return lessons;
    }

    public void setLessons(List<LessonInfo> lessons) {
        this.lessons = lessons;
    }

    public List<VideoInfo> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoInfo> videos) {
        this.videos = videos;
    }

    public String getFinished_times() {
        return finished_times;
    }

    public void setFinished_times(String finished_times) {
        this.finished_times = finished_times;
    }

    public String getFinished_minutes() {
        return finished_minutes;
    }

    public void setFinished_minutes(String finished_minutes) {
        this.finished_minutes = finished_minutes;
    }

    public String getNow_level_name() {
        return now_level_name;
    }

    public void setNow_level_name(String now_level_name) {
        this.now_level_name = now_level_name;
    }

    public String getNow_level_color() {
        return now_level_color;
    }

    public void setNow_level_color(String now_level_color) {
        this.now_level_color = now_level_color;
    }

    public String getNow_level_progress() {
        return now_level_progress;
    }

    public void setNow_level_progress(String now_level_progress) {
        this.now_level_progress = now_level_progress;
    }

    @Override
    public String toString() {
        return "StudySkillInfo{" +
                "response='" + response + '\'' +
                ", id='" + id + '\'' +
                ", intro='" + intro + '\'' +
                ", description='" + description + '\'' +
                ", avatar='" + avatar + '\'' +
                ", ori_price='" + ori_price + '\'' +
                ", price='" + price + '\'' +
                ", lessons=" + lessons +
                ", videos=" + videos +
                ", finished_times='" + finished_times + '\'' +
                ", finished_minutes='" + finished_minutes + '\'' +
                ", now_level_name='" + now_level_name + '\'' +
                ", now_level_color='" + now_level_color + '\'' +
                ", now_level_progress='" + now_level_progress + '\'' +
                '}';
    }
}
