package com.newer.kt.ktmatch;

import com.newer.kt.Refactor.Constants;
import com.newer.kt.Refactor.utils.MD5;
import com.newer.kt.ktmatch.json.JsonToMapUtils;
import com.newer.kt.ktmatch.json.JsonUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.http.body.StringBody;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by admin on 2017/3/2.
 */
public class QueryBuilder {

    public Map map = new HashMap();
    public RequestParams p = new RequestParams();

    public static QueryBuilder build(String url) {
        url = Constants.KTHOST + url;
        QueryBuilder qb = new QueryBuilder();
        qb.p = new RequestParams(url);
        qb.p.addQueryStringParameter("authenticity_token", MD5.getToken(url));
        qb.map.put("authenticity_token", MD5.getToken(url));
        return qb;
    }

    public QueryBuilder add(String k, Object v) {

        p.addQueryStringParameter(k, "" + v);
        map.put(k, "" + v);

        return this;
    }

    public RequestParams get() {
        return p;
    }

    public Map getMap() {
        return map;
    }

    public String getJson() {
        return JsonToMapUtils.mapToJson(map);
    }

    ;
    public Callback callback;

    public void addCallback(Callback callback) {
        this.callback = callback;
    }

    public void get(final Callback callback) {
        if (callback == null) {
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
                callback.onError(ex, isOnCallback);
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

    public static abstract class EnhancedCallback implements Callback {
        List<String> list;


        public EnhancedCallback(String expr) {
            if (expr == null || expr.length() == 0) {
                return;
            }
            this.list = Arrays.asList(expr.split(";"));
        }

        public EnhancedCallback() {

        }


        public abstract void onSuccessWithObject(String namelink, Object object);


        public void onSuccess(String str) {
            String namelink;
            if (list == null) {
                namelink = "";
                Object rt = JsonUtil.extractJsonRightValue(str);
                onSuccessWithObject("", rt);
            }else {
                for (String astr : list) {
                    String group = null;
                    namelink = astr;
                    String expr = null;
                    if (astr.contains("[")) {
                        int i1 = astr.indexOf("[");
                        int i2 = astr.indexOf("]", i1 + 1);
                        expr = astr.substring(i1 + 1, i2);
                        String[] exproptArr = expr.split(":");
                        if (exproptArr[0].contains("group")) {
                            group = exproptArr[1];
                        }
                        namelink = astr.substring(0, i1);
                    }
//            }
                    Object rt = JsonUtil.extractJsonRightValue(JsonUtil.findJsonLink(namelink, str));
                    if(expr==null){
                        onSuccessWithObject(astr,rt);
                        return;
                    }
                    Map<String, ArrayList> cats = null;
                    if (rt instanceof List) {
                        if (group != null) {
                            List<Map> listrt = (List<Map>) rt;
                            cats = new TreeMap<String, ArrayList>();
                            for (Map map : listrt) {
                                if (!map.containsKey(group)) {
                                    throw new RuntimeException("no this '" + group + "' name for group on List Type Result");
                                }
                                String key = map.get(group).toString();
                                if (!cats.containsKey(key)) {
                                    cats.put(key, new ArrayList<Map>());
                                }
                                cats.get(key).add(map);

                            }
                        }
                    }
                    onSuccessWithObject(astr, cats);
                }

            }
        }

    }


    public void post(final Callback callback) {
        if (callback == null) {
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
                callback.onError(ex, isOnCallback);
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
