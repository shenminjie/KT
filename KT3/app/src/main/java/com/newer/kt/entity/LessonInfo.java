package com.newer.kt.entity;

import java.io.Serializable;

/**
 * Description:
 * Created by MonkeyShen on 2017/3/16.
 * Mail:shenminjie92@sina.com
 */

public class LessonInfo implements Serializable {
    private String id;
    private String name;
    private String avatar;
    private String intro;
    private String download_images_url;
    private String zip_size;

    @Override
    public String toString() {
        return "LessonInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", intro='" + intro + '\'' +
                ", download_images_url='" + download_images_url + '\'' +
                ", zip_size='" + zip_size + '\'' +
                '}';
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
