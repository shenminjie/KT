package com.newer.kt.entity;

/**
 * Created by leo on 17/1/3.
 */

public class Clubs_game {
    public String game_id;
    public String name;
    public String user_count;
    public String status;
    public String start_date;
    public String end_date;


    public Clubs_game(){

    }

    public Clubs_game(String game_id, String name, String user_count, String status, String start_date, String end_date) {
        this.game_id = game_id;
        this.name = name;
        this.user_count = user_count;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_count() {
        return user_count;
    }

    public void setUser_count(String user_count) {
        this.user_count = user_count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
