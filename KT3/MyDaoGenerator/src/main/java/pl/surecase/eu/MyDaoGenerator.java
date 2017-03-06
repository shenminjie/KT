package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

//    public static void main(String args[]) throws Exception {
//        Schema schema = new Schema(3, "greendao");
//        Entity box = schema.addEntity("Box");
//        box.addIdProperty();
//        box.addStringProperty("name");
//        box.addIntProperty("slots");
//        box.addStringProperty("description");
//        new DaoGenerator().generateAll(schema, args[0]);
//    }

    public static void main(String args[]) throws Exception {
        //http://www.ktfootball.com/
        //Schema�������2����������һ��������DB�İ汾�ţ�ͨ�����°汾�����������ݿ⡣�ڶ����������Զ����ɴ���İ�·������·��ϵͳ�Զ�����
        Schema schema = new Schema( 13, "com.ktfootball.www.dao");
        // 1: ���ݿ�汾��
        // com.xxx.bean:�Զ����ɵ�Bean�����ŵ�/java-gen/com/xxx/bean��

//        schema.setDefaultJavaPackageDao("com.chongwuzhiwu.app.screen.dao");
        // DaoMaster.java��DaoSession.java��BeanDao.java��ŵ�/java-gen/com/xxx/dao��

        // �����������ļ���·���������Զ��壬Ҳ���Բ�����

        initBags(schema); // ��ʼ��Bean��
        initUsers(schema); // ��ʼ��Bean��
        initGames(schema); // ��ʼ��Bean��
        initSideaandb(schema); // ��ʼ��Bean��
        initVcrPath(schema); // ��ʼ��Bean��
        uploadBigClassroomCourseRecord(schema); // �ϴ���μ��Ͽμ�¼(post)
        uploadBigClassroomCourseRecordBoolean(schema); // �ϴ���μ��Ͽμ�¼(post)
        initRankingPower(schema); // ��ʼ��Bean��
        initRankingLeagueScores(schema); // ս�������а�(get)
        initRankingLeagueScores3v3(schema); // ս�������а�(get)
        initRankingLeagueScores1v1(schema); // ս�������а�(get)1v1
        uploadGymCourseRecord(schema); // �ϴ��������Ͽμ�¼(post)
        initUsetInfo(schema); // �ϴ��������Ͽμ�¼(post)
//        ��һ��������Schema���󣬵ڶ���������ϣ���Զ����ɵĴ����Ӧ����Ŀ·����
        new DaoGenerator().generateAll(schema, args[0]);// �Զ�����
    }

    private static void initUsetInfo(Schema schema) {
        Entity userBean = schema.addEntity("UserInfo");// ����
        userBean.addIdProperty().autoincrement();
        userBean.addIntProperty("grade");
        userBean.addLongProperty("classid");
        userBean.addStringProperty("name");
        userBean.addIntProperty("cls");
        userBean.addStringProperty("user_id");
        userBean.addStringProperty("nickname");
        userBean.addStringProperty("gender");
        userBean.addStringProperty("birthday");
        userBean.addStringProperty("phone");
        userBean.addStringProperty("height");
        userBean.addStringProperty("weight");
    }

    private static void uploadGymCourseRecord(Schema schema) {
        Entity userBean = schema.addEntity("UploadGymCourseRecord");// ����
        userBean.addIdProperty().autoincrement();
        userBean.addStringProperty("club_id");
        userBean.addStringProperty("user_id");
        userBean.addStringProperty("course_combination_id");
        userBean.addStringProperty("classes");
        userBean.addStringProperty("is_finished");
        userBean.addStringProperty("finished_time");
    }

    private static void uploadBigClassroomCourseRecord(Schema schema) {
//        club_id: ���ֲ�ID,
//                user_id: ����ID,
//                youku_video_url: �ſ���Ƶurl,
//                classroom_id: ��μ�ID,
//                classes: �༶ID(��,�ŷָ�),
//                is_finished: 0(δ��ɿγ�),1(��ɿγ�),
//                finished_time: ���ʱ��( �� 2016-03-01 14:00 )

        Entity userBean = schema.addEntity("UploadBigClassroomCourseRecord");// ����
        userBean.addIdProperty().autoincrement();
        userBean.addStringProperty("club_id");
        userBean.addStringProperty("user_id");
        userBean.addStringProperty("youku_video_url");
        userBean.addStringProperty("classroom_id");
        userBean.addStringProperty("classes");
        userBean.addStringProperty("is_finished");
        userBean.addStringProperty("path");
        userBean.addStringProperty("finished_time");
    }

    private static void uploadBigClassroomCourseRecordBoolean(Schema schema) {
//        club_id: ���ֲ�ID,
//                user_id: ����ID,
//                youku_video_url: �ſ���Ƶurl,
//                classroom_id: ��μ�ID,
//                classes: �༶ID(��,�ŷָ�),
//                is_finished: 0(δ��ɿγ�),1(��ɿγ�),
//                finished_time: ���ʱ��( �� 2016-03-01 14:00 )

        Entity userBean = schema.addEntity("UploadBigClassroomCourseRecordBoolean");// ����
        userBean.addIdProperty().autoincrement();
        userBean.addStringProperty("club_id");
        userBean.addStringProperty("user_id");
        userBean.addStringProperty("youku_video_url");
        userBean.addStringProperty("classroom_id");
        userBean.addStringProperty("classes");
        userBean.addStringProperty("is_finished");
        userBean.addStringProperty("path");
        userBean.addStringProperty("finished_time");
        userBean.addBooleanProperty("isSuccess");
    }

    private static void initRankingLeagueScores3v3(Schema schema) {
        Entity userBean = schema.addEntity("RankingLeagueScores3v3");// ����
        userBean.addIdProperty().autoincrement();
        userBean.addStringProperty("league_id");
        userBean.addStringProperty("name");
        userBean.addStringProperty("scores");
        userBean.addStringProperty("win_rate");
    }

    private static void initRankingLeagueScores(Schema schema) {
        Entity userBean = schema.addEntity("RankingLeagueScores");// ����
        userBean.addIdProperty().autoincrement();
        userBean.addStringProperty("league_id");
        userBean.addStringProperty("name");
        userBean.addStringProperty("scores");
        userBean.addStringProperty("win_rate");
    }

    private static void initRankingLeagueScores1v1(Schema schema) {
        Entity userBean = schema.addEntity("RankingLeagueScores1v1");// ����
        userBean.addIdProperty().autoincrement();
        userBean.addStringProperty("nickname");
        userBean.addStringProperty("age");
        userBean.addStringProperty("gender");
        userBean.addStringProperty("cls");
        userBean.addStringProperty("grade");
        userBean.addStringProperty("scores");
        userBean.addStringProperty("win_rate");
    }

    private static void initRankingPower(Schema schema) {
        Entity userBean = schema.addEntity("RankingPower");// ����
        userBean.addIdProperty().autoincrement();
        userBean.addStringProperty("user_id");
        userBean.addStringProperty("nickname");
        userBean.addStringProperty("age");
        userBean.addStringProperty("gender");
        userBean.addStringProperty("school_grade");
        userBean.addStringProperty("school_cls");
        userBean.addStringProperty("scores");
        userBean.addStringProperty("power");
        userBean.addStringProperty("win_rate");
    }

    private static void initVcrPath(Schema schema) {
        Entity userBean = schema.addEntity("VcrPath");// ����
        userBean.addIdProperty().autoincrement();
        userBean.addStringProperty("users");
        userBean.addIntProperty("add_scores");
        userBean.addIntProperty("result");
        userBean.addIntProperty("goals");
        userBean.addIntProperty("pannas");
        userBean.addIntProperty("fouls");
        userBean.addIntProperty("flagrant_fouls");
        userBean.addIntProperty("panna_ko");
        userBean.addIntProperty("abstained");
        userBean.addStringProperty("users_b");
        userBean.addIntProperty("add_scores_b");
        userBean.addIntProperty("result_b");
        userBean.addIntProperty("goals_b");
        userBean.addIntProperty("pannas_b");
        userBean.addIntProperty("fouls_b");
        userBean.addIntProperty("flagrant_fouls_b");
        userBean.addIntProperty("panna_ko_b");
        userBean.addIntProperty("abstained_b");
        userBean.addStringProperty("path");
        userBean.addStringProperty("time");
        userBean.addIntProperty("game_type");
        userBean.addBooleanProperty("isSuccess");
    }

    private static void initSideaandb(Schema schema) {
        Entity userBean = schema.addEntity("SideAandB");// ����
        userBean.addIdProperty().autoincrement();
        userBean.addStringProperty("users");
        userBean.addIntProperty("add_scores");
        userBean.addIntProperty("result");
        userBean.addIntProperty("goals");
        userBean.addIntProperty("pannas");
        userBean.addIntProperty("fouls");
        userBean.addIntProperty("flagrant_fouls");
        userBean.addIntProperty("panna_ko");
        userBean.addIntProperty("abstained");
        userBean.addStringProperty("users_b");
        userBean.addIntProperty("add_scores_b");
        userBean.addIntProperty("result_b");
        userBean.addIntProperty("goals_b");
        userBean.addIntProperty("pannas_b");
        userBean.addIntProperty("fouls_b");
        userBean.addIntProperty("flagrant_fouls_b");
        userBean.addIntProperty("panna_ko_b");
        userBean.addIntProperty("abstained_b");
        userBean.addStringProperty("path");
        userBean.addStringProperty("time");
        userBean.addIntProperty("game_type");
    }

    private static void initGames(Schema schema) {
        Entity userBean = schema.addEntity("Games");// ����
        userBean.addIdProperty().autoincrement();
        userBean.addStringProperty("game_id");
        userBean.addStringProperty("game_enter_users_count");
        userBean.addStringProperty("name");
        userBean.addStringProperty("time_start");
        userBean.addStringProperty("time_end");
        userBean.addStringProperty("avatar");
        userBean.addStringProperty("introduction");
        userBean.addStringProperty("location");
        userBean.addStringProperty("ktb");
        userBean.addStringProperty("enter_time_start");
        userBean.addStringProperty("enter_time_end");
        userBean.addStringProperty("place");
        userBean.addStringProperty("country");
        userBean.addStringProperty("city");
    }

    private static void initUsers(Schema schema) {
        Entity userBean = schema.addEntity("Users");// ����
        userBean.addIdProperty().autoincrement();
        userBean.addStringProperty("user_id");
        userBean.addStringProperty("avatar");
        userBean.addStringProperty("nickname");
        userBean.addStringProperty("email");
        userBean.addStringProperty("phone");
        userBean.addStringProperty("grade");
        userBean.addStringProperty("scores");
        userBean.addStringProperty("power");
        userBean.addStringProperty("ktb");
        userBean.addStringProperty("age");
        userBean.addStringProperty("gender");
    }

    private static void initBags(Schema schema) {
        Entity userBean = schema.addEntity("Bags");// ����
        userBean.addIdProperty().autoincrement();
        userBean.addStringProperty("name");
        userBean.addStringProperty("code");
    }

    private static void initUserBean(Schema schema) {
        //Entity��ʾһ��ʵ����Զ�Ӧ�����ݿ��еı�
        //ϵͳ�Զ����Դ���Ĳ�����Ϊ������֣������������NOTE
        Entity userBean = schema.addEntity("ScreenState");// ����
        //��ȻҲ�����Լ����ñ�����֣���������
//        userBean.setTableName("user"); // ���ԶԱ�������
//        �����ID�Զ�����������������
        userBean.addIdProperty().autoincrement();
//        userBean.addStringProperty("id").primaryKey().index();// ����������
        userBean.addIntProperty("type");
        userBean.addLongProperty("timestamp");
        userBean.addBooleanProperty("isRrecord");
        userBean.addLongProperty("currentDay");
        userBean.addLongProperty("userpresent");
    }
}
