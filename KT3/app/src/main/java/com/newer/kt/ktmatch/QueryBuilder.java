package com.newer.kt.ktmatch;

import com.newer.kt.Refactor.Constants;
import com.newer.kt.Refactor.utils.MD5;
import com.newer.kt.ktmatch.json.JsonToMapUtils;

import org.xutils.http.RequestParams;

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
}
