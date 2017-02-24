package com.newer.kt.Refactor.Entitiy;

/**
 * Created by Administrator on 2017/1/22.
 */
public class Action {

    private String name;//名称,
    private String download_url;//下载地址
    private Integer duration;//时长
    private String strength;// 强度
    private String picture;// 图片

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Action{" +
                "name='" + name + '\'' +
                ", download_url='" + download_url + '\'' +
                ", duration=" + duration +
                ", strength='" + strength + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
