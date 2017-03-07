package com.newer.kt;

import android.app.DownloadManager;

import com.alipay.share.sdk.openapi.channel.APMessage;
import com.newer.kt.ktmatch.ArrayBuilder;
import com.newer.kt.ktmatch.QueryBuilder;
import com.newer.kt.ktmatch.json.JsonUtil;

import org.xutils.http.RequestParams;

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
    public static void test(){
//        QueryBuilder.build("wikis/list").get(cb);
        QueryBuilder.build("school_gym_courses/combinations").get(cb);
        QueryBuilder.build("school_gym_courses/detail").add("school_gym_course_combination_id","").get(cb);
    }


    public static void wikis_list(QueryBuilder.Callback callBack){
        QueryBuilder.build("wikis/list").get(callBack);
    }
}
