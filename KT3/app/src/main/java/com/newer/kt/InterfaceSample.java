package com.newer.kt;

import android.content.Context;
import android.preference.PreferenceManager;

import com.frame.app.base.activity.BaseActivity;
import com.newer.kt.Refactor.ui.Avtivity.LoginActivity;
import com.newer.kt.Refactor.ui.Fragment.Main.ChooseLocal;
import com.newer.kt.Refactor.ui.Fragment.Main.SettingsFragment;
import com.newer.kt.ktmatch.QueryBuilder;

import org.xutils.http.RequestParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by litli on 2017/3/7.
 */

public class InterfaceSample {

       static QueryBuilder.EnhancedCallback cb =  new QueryBuilder.EnhancedCallback() {

        @Override
        public void onSuccessWithObject(String namelink,Object object) {

        }


        @Override
        public void onError(Throwable ex, boolean isOnCallback) {

        }

        @Override
        public void onDebug(RequestParams rp) {

        }
    };
    private static ArrayList unlinkedStudents = new ArrayList();

    public InterfaceSample() {

    }

    public static void test(){
//        QueryBuilder.build("wikis/list").get(cb);
//        QueryBuilder.build("school_gym_courses/combinations").get(cb);
//        QueryBuilder.build("school_gym_courses/detail").add("school_gym_course_combination_id","").get(cb);
    }


    public static void wikis_list(){
        QueryBuilder.build("wikis/list").get(new QueryBuilder.EnhancedCallback(){
            @Override
            public void onSuccessWithObject(String namelink, Object object) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onDebug(RequestParams rp) {

            }
        });
    }

    public BaseActivity ctx;

    public InterfaceSample(BaseActivity baseActivity) {
        this.ctx = baseActivity;
    }


    public void get_club_data() {

        String clubid = "" + PreferenceManager.getDefaultSharedPreferences(ctx)
                .getLong(LoginActivity.PRE_CURRENT_CLUB_ID, 1);
        QueryBuilder.build("offline/get_club_data").add(this.ctx).add("club_id", clubid).get(new QueryBuilder.EnhancedCallback("users") {
            @Override
            public void onSuccessWithObject(String namelink, Object object) {

                SettingsFragment.rt = (List<Map<String, String>>) object;
                ChooseLocal.list.clear();
                for(Map<String, String> m:SettingsFragment.rt){
                    if(m.get("school_cls").toString().equals("null")||m.get("school_grade").toString().equals("null")){
                        ChooseLocal.list.add((Map<String, String>) m);
                    }
                }
//                int i = list.size();
//                i++;
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onDebug(RequestParams rp) {

            }

        });
    }


}
