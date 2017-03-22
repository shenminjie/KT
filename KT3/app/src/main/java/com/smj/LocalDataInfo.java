package com.smj;

import com.smj.dakejian.DakejianBasicInfo;
import com.smj.gradlebean.Classes;
import com.smj.gradlebean.Users;
import com.smj.skillbean.SkillInfo;
import com.smj.upload.UpLoadInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * Created by MonkeyShen on 2017/3/20.
 * Mail:shenminjie92@sina.com
 */

public class LocalDataInfo implements Serializable {


    /**
     * 大课间
     */
    private List<Classes> bisaiClasses;
    private int bisaiIs_finished;
    private DakejianBasicInfo bisaiBasicInfo;



    /**
     * 大课间
     */
    private List<Classes> dakejianClasses;
    private int dakejianIs_finished;
    private DakejianBasicInfo dakejianBasicInfo;

    /**
     * 评测
     */
    private Classes pingceClz;
    private List<Users> pingceStudent;
    private SkillInfo pingceSkillInfo;

    private String videoPath;
    private long createtime;

    /**
     * 1- 评测 2大课间
     */
    private int type;

    public static final int TYPE_PINGCE = 1;
    public static final int TYPE_DAKEJIAN = 2;

    public List<Classes> getDakejianClasses() {
        return dakejianClasses;
    }

    public void setDakejianClasses(List<Classes> dakejianClasses) {
        this.dakejianClasses = dakejianClasses;
    }

    public int getDakejianIs_finished() {
        return dakejianIs_finished;
    }

    public void setDakejianIs_finished(int dakejianIs_finished) {
        this.dakejianIs_finished = dakejianIs_finished;
    }

    public DakejianBasicInfo getDakejianBasicInfo() {
        return dakejianBasicInfo;
    }

    public void setDakejianBasicInfo(DakejianBasicInfo dakejianBasicInfo) {
        this.dakejianBasicInfo = dakejianBasicInfo;
    }

    public Classes getPingceClz() {
        return pingceClz;
    }

    public void setPingceClz(Classes pingceClz) {
        this.pingceClz = pingceClz;
    }

    public List<Users> getPingceStudent() {
        return pingceStudent;
    }

    public void setPingceStudent(List<Users> pingceStudent) {
        this.pingceStudent = pingceStudent;
    }

    public SkillInfo getPingceSkillInfo() {
        return pingceSkillInfo;
    }

    public void setPingceSkillInfo(SkillInfo pingceSkillInfo) {
        this.pingceSkillInfo = pingceSkillInfo;
    }

    public String getVideoPath() {
        return videoPath;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUpLoadName() {
        return getVideoName();
    }

    /**
     * 获取上传名字
     *
     * @return
     */
    public String getVideoName() {
        if (type == 1) {
            return pingceClz.getClassName() + pingceSkillInfo.getName() + "评测";
        } else if (type == 2) {
            return dakejianBasicInfo.getName();
        }
        return "";
    }

    /**
     * 获取id
     *
     * @return
     */
    public String getId() {
        if (type == 1) {
            return "pingce_" + createtime;
        } else if (type == 2) {
            return "dakejian_" + createtime;
        }
        return "";
    }


    @Override
    public String toString() {
        return "LocalDataInfo{" +
                "dakejianClasses=" + dakejianClasses +
                ", dakejianIs_finished=" + dakejianIs_finished +
                ", dakejianBasicInfo=" + dakejianBasicInfo +
                ", pingceClz=" + pingceClz +
                ", pingceStudent=" + pingceStudent +
                ", pingceSkillInfo=" + pingceSkillInfo +
                ", videoPath='" + videoPath + '\'' +
                ", createtime=" + createtime +
                ", type=" + type +
                '}';
    }
}
