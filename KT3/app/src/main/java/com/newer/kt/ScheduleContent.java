package com.newer.kt;


import java.util.Map;

/**
 * Created by bajieaichirou on 17/3/7.
 */
public class ScheduleContent {
    private String schedule_value;
    private String contentTitle;
    private String contentTime;
    private String childImageUrl;
    private String childTitle;
    private String childDetail;
    private int type;//0: 教学目标或者教学器材 1:代表教学内容

    public String getSchedule_value() {
        return schedule_value;
    }

    public void setSchedule_value(String schedule_value) {
        this.schedule_value = schedule_value;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentTime() {
        return contentTime;
    }

    public void setContentTime(String contentTime) {
        this.contentTime = contentTime;
    }

    public String getChildImageUrl() {
        return childImageUrl;
    }

    public void setChildImageUrl(String childImageUrl) {
        this.childImageUrl = childImageUrl;
    }

    public String getChildTitle() {
        return childTitle;
    }

    public void setChildTitle(String childTitle) {
        this.childTitle = childTitle;
    }

    public Map data;

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    public String getChildDetail() {
        return childDetail;
    }

    public void setChildDetail(String childDetail) {
        this.childDetail = childDetail;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
