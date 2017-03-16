package com.newer.kt.entity.jineng;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * Created by MonkeyShen on 2017/3/16.
 * Mail:shenminjie92@sina.com
 */

public class SkillResponse implements Serializable {
    private String category;
    private List<JiNeng_Bean> list;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<JiNeng_Bean> getList() {
        return list;
    }

    public void setList(List<JiNeng_Bean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "SkillResponse{" +
                "category='" + category + '\'' +
                ", list=" + list +
                '}';
    }
}
