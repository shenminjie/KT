package com.newer.kt.entity;

/**
 * Created by leo on 17/3/13.
 */

public class Club_Info_Bean {
    public Club_Info club_info;

    public static class Club_Info{
        public String response;
        public String name;
        public String school_student_count;
        public String higher_manager;

        @Override
        public String toString() {
            return "Club_Info{" +
                    "response='" + response + '\'' +
                    ", name='" + name + '\'' +
                    ", school_student_count='" + school_student_count + '\'' +
                    ", higher_manager='" + higher_manager + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Club_Info_Bean{" +
                "club_info=" + club_info +
                '}';
    }
}
