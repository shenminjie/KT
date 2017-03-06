package com.newer.kt.ktmatch;

import com.newer.kt.Refactor.Constants;
import com.newer.kt.Refactor.utils.MD5;
import com.newer.kt.ktmatch.json.JsonToMapUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2017/3/2.
 */
public class QueryBuilder {
    public Map map = new HashMap();
    public RequestParams p = new RequestParams();
    public static QueryBuilder build(String url){
        url = Constants.KTHOST+url;
        QueryBuilder qb = new QueryBuilder();
        qb.p = new RequestParams(url);
        qb.p.addQueryStringParameter("authenticity_token", MD5.getToken(url));
        qb.map.put("authenticity_token",MD5.getToken(url));
        return qb;
    }
    public QueryBuilder add(String k, Object v){

        p.addQueryStringParameter(k, ""+v);
        map.put(k, ""+v);

        return this;
    }

    public RequestParams get(){
        return p;
    }
    public Map getMap(){
        return map;
    }
    public String getJson(){
        return JsonToMapUtils.mapToJson(map);
    };
    public Callback callback;

    public void addCallback(Callback callback) {
        this.callback = callback;
    }

    public void get(final Callback callback) {
        if(callback==null){
            throw new RuntimeException("addCallback must be invoked before to");
        }
        RequestParams rp = get();
        callback.onDebug(rp);
        x.http().get(rp, new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onError(ex,isOnCallback);
            }

            @Override
            public void onCancelled(org.xutils.common.Callback.CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    public static interface Callback {

        public void onSuccess(String result);

        public void onError(Throwable ex, boolean isOnCallback);

        void onDebug(RequestParams rp);
    }
    public void post(final Callback callback) {
        if(callback==null){
            throw new RuntimeException("addCallback must be invoked before to");
        }
        RequestParams rp = get();
        callback.onDebug(rp);
        x.http().post(get(), new org.xutils.common.Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onError(ex,isOnCallback);
            }

            @Override
            public void onCancelled(org.xutils.common.Callback.CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
