package com.newer.kt.entity.jineng;

import java.util.List;

/**
 * Created by leo on 17/3/16.
 */

public class JiNeng_Bean {
//    response: "success",
//    id: 课程ID, name: 课程名称, intro: 介绍, description: 说明, avatar: 封面,ori_price: 原价, price: 现价,
    public List<Lesson>lessons;
    public List<Video>videos;

    public String response;
    public String id;
    public String name;
    public String intro;
    public String description;
    public String avatar;
    public String ori_price;
    public String price;

    public JiNeng_Bean(){

    }

    public JiNeng_Bean(List<Lesson> lessons, List<Video> videos, String response, String id, String name, String intro, String description, String avatar, String ori_price, String price) {
        this.lessons = lessons;
        this.videos = videos;
        this.response = response;
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.description = description;
        this.avatar = avatar;
        this.ori_price = ori_price;
        this.price = price;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public static class Lesson{
        public String id;
        public String name;
        public String avatar;
        public String intro;
        public String download_images_url;
        public String zip_size;

        @Override
        public String toString() {
            return "Lesson{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", intro='" + intro + '\'' +
                    ", download_images_url='" + download_images_url + '\'' +
                    ", zip_size='" + zip_size + '\'' +
                    '}';
        }
    }

    public static class Video{
        public String id;
        public String download_video_url;
        public String video_size;
        public String video_level;
        public String youku_videos;
        public String speed;
        public String total_times;

        @Override
        public String toString() {
            return "Video{" +
                    "id='" + id + '\'' +
                    ", download_video_url='" + download_video_url + '\'' +
                    ", video_size='" + video_size + '\'' +
                    ", video_level='" + video_level + '\'' +
                    ", youku_videos='" + youku_videos + '\'' +
                    ", speed='" + speed + '\'' +
                    ", total_times='" + total_times + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "JiNeng_Bean{" +
                "lessons=" + lessons +
                ", videos=" + videos +
                ", response='" + response + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                ", description='" + description + '\'' +
                ", avatar='" + avatar + '\'' +
                ", ori_price='" + ori_price + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
