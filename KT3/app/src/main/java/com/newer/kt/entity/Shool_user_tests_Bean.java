package com.newer.kt.entity;

import java.util.List;

/**
 * Created by leo on 17/2/9.
 */

public class Shool_user_tests_Bean {
    public List<Shool_user_tests> rankings;

    public static  class Shool_user_tests{
        public String total_scores;
        public String skill_count;
        public String user_id;
        public String avatar;
        public String nickname;
        public String cls;
        public String grade;

        @Override
        public String toString() {
            return "Shool_user_tests{" +
                    "total_scores='" + total_scores + '\'' +
                    ", skill_count='" + skill_count + '\'' +
                    ", user_id='" + user_id + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", cls='" + cls + '\'' +
                    ", grade='" + grade + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Shool_user_tests_Bean{" +
                "rankings=" + rankings +
                '}';
    }
}
