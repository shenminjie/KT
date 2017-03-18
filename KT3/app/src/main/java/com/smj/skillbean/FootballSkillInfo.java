/**
  * Copyright 2017 bejson.com 
  */
package com.smj.skillbean;
import java.io.Serializable;
import java.util.List;

/**
 * Auto-generated: 2017-03-18 14:23:13
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class FootballSkillInfo implements Serializable{

    private String category;
    private List<SkillInfo> list;
    public void setCategory(String category) {
         this.category = category;
     }
     public String getCategory() {
         return category;
     }

    public void setList(List<SkillInfo> list) {
         this.list = list;
     }
     public List<SkillInfo> getList() {
         return list;
     }

}