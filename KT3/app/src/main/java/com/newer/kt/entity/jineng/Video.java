package com.newer.kt.entity.jineng;

/**
 * Created by leo on 17/3/16.
 */

public class Video {
//    id: 视频id, download_video_url: 视频下载地址,
//    video_size: 视频大小, video_level: v.level,
//    youku_videos: 优酷视频列表, speed: 速度, total_times: 总次数

    public String id;
    public String download_video_url;
    public String video_size;
    public String video_level;
    public String youku_videos;
    public String speed;
    public String total_times;

    public Video(){

    }

    public Video(String id, String download_video_url, String video_size, String video_level, String youku_videos, String speed, String total_times) {
        this.id = id;
        this.download_video_url = download_video_url;
        this.video_size = video_size;
        this.video_level = video_level;
        this.youku_videos = youku_videos;
        this.speed = speed;
        this.total_times = total_times;
    }

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
}
