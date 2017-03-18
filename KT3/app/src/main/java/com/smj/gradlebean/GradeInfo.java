/**
  * Copyright 2017 bejson.com 
  */
package com.smj.gradlebean;
import java.util.List;

/**
 * Auto-generated: 2017-03-18 9:43:12
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class GradeInfo {

    private int grade;
    private List<Classes> classes;
    public void setGrade(int grade) {
         this.grade = grade;
     }
     public int getGrade() {
         return grade;
     }

    public void setClasses(List<Classes> classes) {
         this.classes = classes;
     }
     public List<Classes> getClasses() {
         return classes;
     }

    @Override
    public String toString() {
        return "GradeInfo{" +
                "grade=" + grade +
                ", classes=" + classes +
                '}';
    }
}