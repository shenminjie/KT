package com.bajie.demo;

import android.app.Application;

import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Created by bajieaichirou on 17/2/28.
 */
public class App extends Application {

    @Override
    public void onCreate()
    {
        super.onCreate();
        AutoLayoutConifg.getInstance().useDeviceSize();
    }
}
