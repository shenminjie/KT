package com.smj.saishi;

import java.io.Serializable;

/**
 * Description:
 * Created by MonkeyShen on 2017/3/22.
 * Mail:shenminjie92@sina.com
 */

public class SaishiRequest implements Serializable {
    private String club_id;
    private String user_id;
    private String game_id;
    private String code;
    private String game_type;
    private String time;
    private SaishiItemRequest side_a;
    private SaishiItemRequest side_b;


    public String getClub_id() {
        return club_id;
    }

    public void setClub_id(String club_id) {
        this.club_id = club_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGame_type() {
        return game_type;
    }

    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public SaishiItemRequest getSide_a() {
        return side_a;
    }

    public void setSide_a(SaishiItemRequest side_a) {
        this.side_a = side_a;
    }

    public SaishiItemRequest getSide_b() {
        return side_b;
    }

    public void setSide_b(SaishiItemRequest side_b) {
        this.side_b = side_b;
    }

    @Override
    public String toString() {
        return "SaishiRequest{" +
                "club_id='" + club_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", game_id='" + game_id + '\'' +
                ", code='" + code + '\'' +
                ", game_type='" + game_type + '\'' +
                ", time='" + time + '\'' +
                ", side_a=" + side_a +
                ", side_b=" + side_b +
                '}';
    }
}
