package com.smj;

import com.smj.dakejian.DakejianBasicInfo;
import com.smj.gradlebean.Classes;
import com.smj.upload.UpLoadInfo;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * Created by MonkeyShen on 2017/3/20.
 * Mail:shenminjie92@sina.com
 */

public class DakejianLocalData implements Serializable ,UpLoadInfo{
    private List<Classes> classes;
    private long createtime;
    private int is_finished;
    private String videoPath;
    private DakejianBasicInfo dakejianBasicInfo;



    @Override
    public String getUpLoadName() {
        return dakejianBasicInfo.getName()+"";
    }

    @Override
    public String getId() {
        return "dakejian_"+createtime;
    }

    @Override
    public String getVideoPath() {
        return videoPath;
    }

    @Override
    public int getType() {
        return TYPE_DAKEJIAN;
    }


    public List<Classes> getClasses() {
        return classes;
    }

    public void setClasses(List<Classes> classes) {
        this.classes = classes;
    }

    public long getCreatetime() {
        return createtime;
    }

    public void setCreatetime(long createtime) {
        this.createtime = createtime;
    }

    public int getIs_finished() {
        return is_finished;
    }

    public void setIs_finished(int is_finished) {
        this.is_finished = is_finished;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public DakejianBasicInfo getDakejianBasicInfo() {
        return dakejianBasicInfo;
    }

    public void setDakejianBasicInfo(DakejianBasicInfo dakejianBasicInfo) {
        this.dakejianBasicInfo = dakejianBasicInfo;
    }
}
