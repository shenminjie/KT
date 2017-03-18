package com.newer.kt.ui.pingce.select_skill;

import com.smj.gradlebean.Classes;
import com.smj.skillbean.SkillInfo;

/**
 * Created by chenminjie on 17/3/18.
 */

public class SkillVideoEvent {
    public final SkillInfo skillInfo;
    public final String path;
    public final Classes clz;

    public SkillVideoEvent(SkillInfo skillInfo, String path, Classes clz) {
        this.skillInfo = skillInfo;
        this.path = path;
        this.clz = clz;
    }
}
