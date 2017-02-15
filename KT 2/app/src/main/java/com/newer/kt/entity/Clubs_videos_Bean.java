package com.newer.kt.entity;

import java.util.List;

/**
 * Created by leo on 17/1/16.
 */

public class Clubs_videos_Bean {
    public List<Clubs_videos> videos;

    public static class Clubs_videos {
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

        @Override
        public String toString() {
            return "Clubs_videos{" +
                    "response='" + response + '\'' +
                    ", game_video_id='" + game_video_id + '\'' +
                    ", game_video_type='" + game_video_type + '\'' +
                    ", scores='" + scores + '\'' +
                    ", comment_count='" + comment_count + '\'' +
                    ", likes='" + likes + '\'' +
                    ", time='" + time + '\'' +
                    ", local='" + local + '\'' +
                    ", uri='" + uri + '\'' +
                    ", screenshot='" + screenshot + '\'' +
                    ", picture='" + picture + '\'' +
                    ", video_time='" + video_time + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }

        public static class Clubs_user {
            public String user_id;
            public String nickname;
            public String grade;
            public String avatar;
            public String scores;

            @Override
            public String toString() {
                return "Clubs_user{" +
                        "user_id='" + user_id + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", grade='" + grade + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", scores='" + scores + '\'' +
                        '}';
            }
        }

        public static class Clubs_panna_ko {
            public String league_id;
            public String name;

            @Override
            public String toString() {
                return "Clubs_panna_ko{" +
                        "league_id='" + league_id + '\'' +
                        ", name='" + name + '\'' +
                        '}';
            }
        }


    }

    @Override
    public String toString() {
        return "Clubs_videos_Bean{" +
                "videos=" + videos +
                '}';
    }
}
