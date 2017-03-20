/**
  * Copyright 2017 bejson.com 
  */
package com.smj.dakejian;

import java.io.Serializable;

/**
 * Auto-generated: 2017-03-20 19:32:46
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Actions implements Serializable{

    private String name;
    private String downloadUrl;
    private int duration;
    private String strength;
    private String picture;
    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setDownloadUrl(String downloadUrl) {
         this.downloadUrl = downloadUrl;
     }
     public String getDownloadUrl() {
         return downloadUrl;
     }

    public void setDuration(int duration) {
         this.duration = duration;
     }
     public int getDuration() {
         return duration;
     }

    public void setStrength(String strength) {
         this.strength = strength;
     }
     public String getStrength() {
         return strength;
     }

    public void setPicture(String picture) {
         this.picture = picture;
     }
     public String getPicture() {
         return picture;
     }

    @Override
    public String toString() {
        return "Actions{" +
                "name='" + name + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", duration=" + duration +
                ", strength='" + strength + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}