package com.newer.kt.entity;

import java.util.List;

/**
 * Created by leo on 17/1/10.
 */

public class Clubs_paihang_Bean {

   public List<Clubs_paihang> game_rankings;

    public static class Clubs_paihang{
        public String response;
        public String user_id;
        public String nickname;
        public String avatar;
        public String power;
        public String win_rate;
        public String scores;

        @Override
        public String toString() {
            return "Clubs_paihang{" +
                    "response='" + response + '\'' +
                    ", user_id='" + user_id + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", power='" + power + '\'' +
                    ", win_rate='" + win_rate + '\'' +
                    ", scores='" + scores + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Clubs_paihang_Bean{" +
                "game_rankings=" + game_rankings +
                '}';
    }
}
