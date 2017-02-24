package com.umeng.um_share;

import android.app.Activity;
import android.content.Intent;
import com.newer.kt.AnalyticsHome;
import com.umeng.socialize.UMShareAPI;


/**
 * Created by admin on 2017/2/17.
 */
public class ShareActy extends AnalyticsHome{
    public void share(String... cnt){
            Intent shareintent = new Intent(getBaseContext(),ShareActivity.class);
            startActivityForResult(shareintent,2);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }
}
