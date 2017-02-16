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
        //Schema对象接受2个参数，第一个参数是DB的版本号，通过更新版本号来更新数据库。第二个参数是自动生成代码的包路径。包路径系统自动生成
        Schema schema = new Schema( 13, "com.ktfootball.www.dao");
        // 1: 数据库版本号
        // com.xxx.bean:自动生成的Bean对象会放到/java-gen/com/xxx/bean中

//        schema.setDefaultJavaPackageDao("com.chongwuzhiwu.app.screen.dao");
        // DaoMaster.java、DaoSession.java、BeanDao.java会放到/java-gen/com/xxx/dao中

        // 上面这两个文件夹路径都可以自定义，也可以不设置

        initBags(schema); // 初始化Bean了
        initUsers(schema); // 初始化Bean了
        initGames(schema); // 初始化Bean了
        initSideaandb(schema); // 初始化Bean了
        initVcrPath(schema); // 初始化Bean了
        uploadBigClassroomCourseRecord(schema); // 上传大课间上课记录(post)
        uploadBigClassroomCourseRecordBoolean(schema); // 上传大课间上课记录(post)
        initRankingPower(schema); // 初始化Bean了
        initRankingLeagueScores(schema); // 战斗力排行榜(get)
        initRankingLeagueScores3v3(schema); // 战斗力排行榜(get)
        initRankingLeagueScores1v1(schema); // 战斗力排行榜(get)1v1
        uploadGymCourseRecord(schema); // 上传体育课上课记录(post)
        initUsetInfo(schema); // 上传体育课上课记录(post)
//        第一个参数是Schema对象，第二个参数是希望自动生成的代码对应的项目路径。
        new DaoGenerator().generateAll(schema, args[0]);// 自动创建
    }

    private static void initUsetInfo(Schema schema) {
        Entity userBean = schema.addEntity("UserInfo");// 表名
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
        Entity userBean = schema.addEntity("UploadGymCourseRecord");// 表名
        userBean.addIdProperty().autoincrement();
        userBean.addStringProperty("club_id");
        userBean.addStringProperty("user_id");
        userBean.addStringProperty("course_combination_id");
        userBean.addStringProperty("classes");
        userBean.addStringProperty("is_finished");
        userBean.addStringProperty("finished_time");
    }

    private static void uploadBigClassroomCourseRecord(Schema schema) {
//        club_id: 俱乐部ID,
//                user_id: 裁判ID,
//                youku_video_url: 优酷视频url,
//                classroom_id: 大课间ID,
//                classes: 班级ID(用,号分隔),
//                is_finished: 0(未完成课程),1(完成课程),
//                finished_time: 完成时间( 如 2016-03-01 14:00 )

        Entity userBean = schema.addEntity("UploadBigClassroomCourseRecord");// 表名
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
//        club_id: 俱乐部ID,
//                user_id: 裁判ID,
//                youku_video_url: 优酷视频url,
//                classroom_id: 大课间ID,
//                classes: 班级ID(用,号分隔),
//                is_finished: 0(未完成课程),1(完成课程),
//                finished_time: 完成时间( 如 2016-03-01 14:00 )

        Entity userBean = schema.addEntity("UploadBigClassroomCourseRecordBoolean");// 表名
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
        Entity userBean = schema.addEntity("RankingLeagueScores3v3");// 表名
        userBean.addIdProperty().autoincrement();
        userBean.addStringProperty("league_id");
        userBean.addStringProperty("name");
        userBean.addStringProperty("scores");
        userBean.addStringProperty("win_rate");
    }

    private static void initRankingLeagueScores(Schema schema) {
        Entity userBean = schema.addEntity("RankingLeagueScores");// 表名
        userBean.addIdProperty().autoincrement();
        userBean.addStringProperty("league_id");
        userBean.addStringProperty("name");
        userBean.addStringProperty("scores");
        userBean.addStringProperty("win_rate");
    }

    private static void initRankingLeagueScores1v1(Schema schema) {
        Entity userBean = schema.addEntity("RankingLeagueScores1v1");// 表名
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
        Entity userBean = schema.addEntity("RankingPower");// 表名
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
        Entity userBean = schema.addEntity("VcrPath");// 表名
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
        Entity userBean = schema.addEntity("SideAandB");// 表名
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
        Entity userBean = schema.addEntity("Games");// 表名
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
        Entity userBean = schema.addEntity("Users");// 表名
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
        Entity userBean = schema.addEntity("Bags");// 表名
        userBean.addIdProperty().autoincrement();
        userBean.addStringProperty("name");
        userBean.addStringProperty("code");
    }

    private static void initUserBean(Schema schema) {
        //Entity表示一个实体可以对应成数据库中的表
        //系统自动会以传入的参数作为表的名字，这里表名就是NOTE
        Entity userBean = schema.addEntity("ScreenState");// 表名
        //当然也可以自己设置表的名字，像这样：
//        userBean.setTableName("user"); // 可以对表重命名
//        如果想ID自动增长可以像这样：
        userBean.addIdProperty().autoincrement();
//        userBean.addStringProperty("id").primaryKey().index();// 主键，索引
        userBean.addIntProperty("type");
        userBean.addLongProperty("timestamp");
        userBean.addBooleanProperty("isRrecord");
        userBean.addLongProperty("currentDay");
        userBean.addLongProperty("userpresent");
    }
}
