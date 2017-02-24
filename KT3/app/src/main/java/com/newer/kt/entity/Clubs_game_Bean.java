package com.newer.kt.entity;

import java.util.List;

/**
 * Created by leo on 17/1/3.
 */

public class Clubs_game_Bean {
    public List<Clubs_game> games;

    public static class Clubs_game{
        public String game_id;
        public String name;
        public String user_count;
        public String status;
        public String start_date;
        public String end_date;

        @Override
        public String toString() {
            return "Clubs_game{" +
                    "game_id='" + game_id + '\'' +
                    ", name='" + name + '\'' +
                    ", user_count='" + user_count + '\'' +
                    ", status='" + status + '\'' +
                    ", start_date='" + start_date + '\'' +
                    ", end_date='" + end_date + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Clubs_game_Bean{" +
                "games=" + games +
                '}';
    }
}
