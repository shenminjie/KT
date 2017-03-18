package com.smj.event;

import com.newer.kt.entity.Student;
import com.smj.gradlebean.Users;

import java.util.List;

/**
 * Description:
 * Created by MonkeyShen on 2017/3/18.
 * Mail:shenminjie92@sina.com
 */

public class NextStepEvent {
    public final int position;

    public final List<Users> students;

    public NextStepEvent(int position, List<Users> students) {
        this.position = position;
        this.students = students;
    }
}
