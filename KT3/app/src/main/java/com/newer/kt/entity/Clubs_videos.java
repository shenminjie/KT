package com.newer.kt.entity;

/**
 * Created by leo on 17/1/16.
 */

public class Clubs_videos {
//    game_video_id: 视频ID,
//    game_video_type: 0或者1(0: 1v1,1: 2v2),
//    scores: 比分, comment_count: "评论数",
//    likes: "喜欢数", time: 视频时间,
//    local, 地点, uri: 视频uri,
//    screenshot: 视频截图,
//    picture: 比赛图片  video_time: 视频时间,
    public String response;
    public String game_video_id;
    public String game_video_type;
    public String scores;
    public String comment_count;
    public String likes;
    public String time;
    public String local;
    public String uri;
    public String screenshot;
    public String picture;
    public String video_time;
    public String city;

    public Clubs_videos(){

    }

    public Clubs_videos(String response, String game_video_id, String game_video_type, String scores, String comment_count, String likes, String time, String local, String uri, String screenshot, String picture, String video_time, String city) {
        this.response = response;
        this.game_video_id = game_video_id;
        this.game_video_type = game_video_type;
        this.scores = scores;
        this.comment_count = comment_count;
        this.likes = likes;
        this.time = time;
        this.local = local;
        this.uri = uri;
        this.screenshot = screenshot;
        this.picture = picture;
        this.video_time = video_time;
        this.city = city;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getGame_video_id() {
        return game_video_id;
    }

    public void setGame_video_id(String game_video_id) {
        this.game_video_id = game_video_id;
    }

    public String getGame_video_type() {
        return game_video_type;
    }

    public void setGame_video_type(String game_video_type) {
        this.game_video_type = game_video_type;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getVideo_time() {
        return video_time;
    }

    public void setVideo_time(String video_time) {
        this.video_time = video_time;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static class Clubs_user{
        public String user_id;
        public String nickname;
        public String grade;
        public String avatar;
        public String scores;

        public Clubs_user(){

        }
        public Clubs_user(String user_id, String nickname, String grade, String avatar, String scores) {
            this.user_id = user_id;
            this.nickname = nickname;
            this.grade = grade;
            this.avatar = avatar;
            this.scores = scores;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getScores() {
            return scores;
        }

        public void setScores(String scores) {
            this.scores = scores;
        }


    }
    public static class  Clubs_panna_ko{
        public String league_id;
        public String name;

        public Clubs_panna_ko(){

        }

        public Clubs_panna_ko(String league_id, String name) {
            this.league_id = league_id;
            this.name = name;
        }

        public String getLeague_id() {
            return league_id;
        }

        public void setLeague_id(String league_id) {
            this.league_id = league_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
