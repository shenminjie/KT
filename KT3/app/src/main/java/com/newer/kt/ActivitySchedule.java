package com.newer.kt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.newer.kt.ktmatch.QueryBuilder;
import com.newer.kt.ktmatch.json.JsonUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bajieaichirou on 17/3/7.
 */
public class ActivitySchedule extends Activity {

    @Bind(R.id.schedule_time_txt)
    TextView mTimeTxt;

    @Bind(R.id.schedule_strength_txt)
    TextView mStrengthTxt;

    @Bind(R.id.schedule_practice_txt)
    TextView mPracticeTxt;

    @Bind(R.id.schedule_video_lesson_txt)
    TextView mVideoLessonTxt;

    @Bind(R.id.schedule_teacher_icon)
    ImageView mCreatorIcon;

    @Bind(R.id.schedule_creator_description)
    TextView mCreatorDescriptionTxt;

    @Bind(R.id.schedule_list_view)
    ExpandableListView mScheduleListView;

    private String schedule_time, schedule_strength,
            schedule_practice, creator_description;

    private List<ScheduleInfo> contentList;
    private List<Map> list = new ArrayList<Map>();
    private List<String> list_title;
    private ScheduleContent scheduleContent;
    private ScheduleAdapter mAdapter;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        ButterKnife.bind(this);
        QueryBuilder.build("school_gym_courses/detail").add("school_gym_course_combination_id", getIntent().getStringExtra("school_gym_course_combination_id")).get(new QueryBuilder.Callback() {


            @Override
            public void onSuccess(String result) {
                data = result;
                initData();
            }

            //{"name":"第一课","semester":"幼儿园中班","create_user_nickname":"","create_user_intro":null,"create_user_avatar":"","duration":null,"requirement":"","video_url":"","strength":"","teaching_goal":"模仿各种玩球的动作 ，对创造性玩球产生兴趣。\r\n体验KT足球，体验足球比赛的兴趣","teaching_equipment":"足球：若干\r\nKT足球气场：1个","courses":[{"sort_number":0,"name":"热身\u0026准备活动 ","list":[{"name":"徒手操","gym_video_url":"http://public.ktfootball.com/download/body/徒手操.mp4","duration":null,"strength":"","image":"http://public.ktfootball.com/uploads/school_gym_course/image/229/__0186.jpg","organization":"学生站列一排或两排对齐进行，每个动作做4个八拍。","exercise_requirement":"动作整齐、统一","intro":"1.头部运动\r\n2.扩胸运动\r\n3.扭腰运动\r\n4.手、脚抖动运动","gif_url":"http://public.ktfootball.com/download/body/徒手操.gif"}]},{"sort_number":1,"name":"足球游戏","list":[{"name":"请你像我这样做","gym_video_url":"http://public.ktfootball.com/download/game/6请你像我这样做.gif","duration":null,"strength":"","image":"http://public.ktfootball.com/uploads/school_gym_course/image/230/_______0119.jpg","organization":"全体幼儿站成一个圆圈，足球人手一个。\r\n先由教师启发幼儿说出各种玩球的动作。\r\n然后由任意一名幼儿开始，说“请你像我这样做”。说完幼儿做一个动作，全体幼儿模仿，并说“我就照你那样做”。\r\n接着第二名幼儿领做，游戏依次进行。","exercise_requirement":"全体幼儿要模仿领头人的动作，谁模仿错误，则为失败。","intro":"","gif_url":"http://public.ktfootball.com/download/game/请你像我这样做.gif"}]},{"sort_number":3,"name":"足球比赛","list":[{"name":"KT足球比赛2V2 （15min）","gym_video_url":"http://114.215.170.230/gym_course_video/脚底踩球真人视频.MOV.zip","duration":null,"strength":"","image":"","organization":"KT足球气场 两人一队；\r\n猜拳决定球权，并进行比赛；\r\n每局3分钟。\r\n如若没有KT足球气场，可用标志桶围出8m*10m的区域，在两端放置小球门替代。","exercise_requirement":"在气场中央开球，每个队防守一个球门，同时要试图攻入对方的球门得分。\r\n交换控球权出现在防守者抢到球、球出界或射门得分后。","intro":"在气场中央开球，每个队防守一个球门，同时要试图攻入对方的球门得分。交换控球权出现在防守者抢到球、球出界或射门得分后。","gif_url":""}]}],"times":""}
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onDebug(RequestParams rp) {

            }
        });
    }


    private void initData() {
        String portrait = JsonUtil.findJsonLink("create_user_avatar", data).toString();
        if (portrait != null && !portrait.equals("")) {
            ImageLoader.getInstance().displayImage(portrait, mCreatorIcon);
        }
        String create_user_nickname = JsonUtil.findJsonLink("create_user_nickname", data).toString();

        String create_user_intro = JsonUtil.findJsonLink("create_user_intro", data).toString();
        if (create_user_nickname != null && !create_user_nickname.equals("")) {
            mCreatorDescriptionTxt.setText(create_user_nickname + "  " + create_user_intro);
        }
        String duration = JsonUtil.findJsonLink("duration", data).toString();
        if (!duration.equals("")) {
            mTimeTxt.setText(duration);
        }
        String strength = JsonUtil.findJsonLink("strength", data).toString();
        if (!strength.equals("")) {
            mStrengthTxt.setText(strength);
        }
        String requirement = JsonUtil.findJsonLink("requirement", data).toString();
        if (!requirement.equals("")) {
            mPracticeTxt.setText(requirement);
        }

//        schedule_time = getIntent().getStringExtra("schedule_time");
//        schedule_strength = getIntent().getStringExtra("schedule_strength");
//        schedule_practice = getIntent().getStringExtra("schedule_practice");
//        creator_description = getIntent().getStringExtra("creator_description");
//        mTimeTxt.setText(schedule_time);
//        mStrengthTxt.setText(schedule_strength);
//        mPracticeTxt.setText(schedule_practice);
//        mCreatorDescriptionTxt.setText(creator_description);
                contentList = new ArrayList<ScheduleInfo>();
        list_title = new ArrayList<String>();
        list_title.add("教学目标");
        list_title.add("教学器材");
        list_title.add("教学内容");

        List<ScheduleContent> listTarget = new ArrayList<>();
        scheduleContent = new ScheduleContent();
        scheduleContent.setType(0);
        scheduleContent.setSchedule_value(JsonUtil.findJsonLink("teaching_goal", data).toString());
        listTarget.add(scheduleContent);

        List<ScheduleContent> listEquipment = new ArrayList<>();
        scheduleContent = new ScheduleContent();
        scheduleContent.setType(0);
        scheduleContent.setSchedule_value(JsonUtil.findJsonLink("teaching_equipment", data).toString());
        listEquipment.add(scheduleContent);
        List rt =
                (List) JsonUtil.extractJsonRightValue(JsonUtil.findJsonLink("courses", data).toString());
        Map map;
        for (int i = 0; i < rt.size(); i++) {

            map = ((Map) rt.get(i));
            map.put("content_title", (i + 1) + ". " + map.get("name").toString());
            map.put("content_time", "");
            map.put("child_title", "");
            list.add(map);
            List ls = (List) map.get("list");
            for (int j = 0; j < ls.size(); j++) {
                Map m = (Map) ls.get(j);
                m.put("content_title", "");
                m.put("child_title", m.get("name"));
                list.add(m);

            }

        }
        int size = list.size();
        List<ScheduleContent> listContent = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            scheduleContent = new ScheduleContent();
            scheduleContent.setType(1);
            scheduleContent.setData(list.get(i));
            String temp = list.get(i).get("content_title").toString();
            if (temp.isEmpty()) {
                scheduleContent.setContentTitle("");

                scheduleContent.setChildTitle(list.get(i).get("child_title").toString());
            } else {

                scheduleContent.setContentTitle(temp);
                scheduleContent.setContentTime(list.get(i).get("content_time").toString());
                scheduleContent.setChildTitle("");
            }
            listContent.add(scheduleContent);
        }

        for (int i = 0; i < list_title.size(); i++) {
            ScheduleInfo scheduleInfo = new ScheduleInfo();
            if (i == 0) {
                scheduleInfo.setScheduleList(listTarget);
            } else if (i == 1) {
                scheduleInfo.setScheduleList(listEquipment);
            } else if (i == 2) {
                scheduleInfo.setScheduleList(listContent);
            }

            contentList.add(scheduleInfo);
            mScheduleListView.setGroupIndicator(null);

        }

        mAdapter = new ScheduleAdapter(this, list_title, contentList);
        mScheduleListView.setAdapter(mAdapter);
    }

}
