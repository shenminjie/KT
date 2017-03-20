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
public class Skills implements Serializable{

    @Override
    public String toString() {
        return "Skills{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    private int id;
    private String name;
    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

}