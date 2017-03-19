package com.smj;

import com.newer.kt.Refactor.KTApplication;
import com.smj.gradlebean.Classes;
import com.smj.gradlebean.Users;
import com.smj.skillbean.SkillInfo;
import com.smj.upload.UpLoadInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chenminjie on 17/3/18.
 */

public class PingceLocalData implements Serializable, UpLoadInfo {
    private Classes clz;
    private List<Users> student;
    private SkillInfo skillInfo;
    private String videoPath;
    private long createtime;

    public String getVideoName() {
        String videoName = clz.getClassName() + skillInfo.getName() + "评测";
        return videoName;
    }

    public Classes getClz() {
        return clz;
    }

    public void setClz(Classes clz) {
        this.clz = clz;
    }

    public List<Users> getStudent() {
        return student;
    }

    public void setStudent(List<Users> student) {
        this.student = student;
    }

    public SkillInfo getSkillInfo() {
        return skillInfo;
    }

    public void setSkillInfo(SkillInfo skillInfo) {
        this.skillInfo = skillInfo;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "PingceLocalData{" +
                "clz=" + clz +
                ", student=" + student +
                ", skillInfo=" + skillInfo +
                ", videoPath='" + videoPath + '\'' +
                ", createtime=" + createtime +
                '}';
    }

    @Override
    public String getUpLoadName() {
        return getVideoName();
    }

    @Override
    public String getId() {
        return "pingcedata_" + createtime;
    }

    @Override
    public String getVideoPath() {
        return videoPath;
    }
}
