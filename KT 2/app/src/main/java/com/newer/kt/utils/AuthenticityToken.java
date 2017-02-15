package com.newer.kt.utils;


import static org.xutils.common.util.MD5.md5;

/**
 * Created by asus on 2016/12/21.
 */

public class AuthenticityToken {

    public static String getAuthenticityToken(String url){
        String api_key = "ktfootballapistrftoken";
        return md5(url + api_key);
    }





}
