package com.newer.kt.entity.jineng;

/**
 * Created by leo on 17/3/16.
 */

public class Lesson {
//    id: 课时id, name: 名称, avatar: 封面, intro: 介绍,
//    download_images_url: 图片zip下载地址, zip_size: zip包大小
    public String id;
    public String name;
    public String avatar;
    public String intro;
    public String download_images_url;
    public String zip_size;

    public Lesson(){

    }

    public Lesson(String id, String name, String avatar, String intro, String download_images_url, String zip_size) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.intro = intro;
        this.download_images_url = download_images_url;
        this.zip_size = zip_size;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getDownload_images_url() {
        return download_images_url;
    }

    public void setDownload_images_url(String download_images_url) {
        this.download_images_url = download_images_url;
    }

    public String getZip_size() {
        return zip_size;
    }

    public void setZip_size(String zip_size) {
        this.zip_size = zip_size;
    }
}
