package com.newer.kt.entity;

/**
 * Created by leo on 17/1/3.
 */

public class Clubs_groups_Bean {

    private String response;
    private String games_count;
    private String users_count;
    private String male_users_count;
    private String female_users_count;
    private String battles_count;

    public Clubs_groups_Bean() {

    }

    public Clubs_groups_Bean(String response, String games_count, String users_count, String male_users_count, String female_users_count, String battles_count) {
        this.response = response;
        this.games_count = games_count;
        this.users_count = users_count;
        this.male_users_count = male_users_count;
        this.female_users_count = female_users_count;
        this.battles_count = battles_count;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getGames_count() {
        return games_count;
    }

    public void setGames_count(String games_count) {
        this.games_count = games_count;
    }

    public String getUsers_count() {
        return users_count;
    }

    public void setUsers_count(String users_count) {
        this.users_count = users_count;
    }

    public String getMale_users_count() {
        return male_users_count;
    }

    public void setMale_users_count(String male_users_count) {
        this.male_users_count = male_users_count;
    }

    public String getFemale_users_count() {
        return female_users_count;
    }

    public void setFemale_users_count(String female_users_count) {
        this.female_users_count = female_users_count;
    }

    public String getBattles_count() {
        return battles_count;
    }

    public void setBattles_count(String battles_count) {
        this.battles_count = battles_count;
    }
}