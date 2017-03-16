package com.newer.kt.entity;

import java.io.Serializable;

/**
 * Description:
 * Created by MonkeyShen on 2017/3/16.
 * Mail:shenminjie92@sina.com
 */

public class VideoInfo implements Serializable {
    private String id;
    private String download_video_url;
    private String video_size;
    private String video_level;
    private String youku_videos;
    private String speed;
    private String total_times;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDownload_video_url() {
        return download_video_url;
    }

    public void setDownload_video_url(String download_video_url) {
        this.download_video_url = download_video_url;
    }

    public String getVideo_size() {
        return video_size;
    }

    public void setVideo_size(String video_size) {
        this.video_size = video_size;
    }

    public String getVideo_level() {
        return video_level;
    }

    public void setVideo_level(String video_level) {
        this.video_level = video_level;
    }

    public String getYouku_videos() {
        return youku_videos;
    }

    public void setYouku_videos(String youku_videos) {
        this.youku_videos = youku_videos;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getTotal_times() {
        return total_times;
    }

    public void setTotal_times(String total_times) {
        this.total_times = total_times;
    }

    @Override
    public String toString() {
        return "VideoInfo{" +
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
