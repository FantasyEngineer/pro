package com.hjg.toolspro;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.hjg.tool.T;

/**
 * @author houjiguo
 * @data 2018/11/26 15:17
 * @description this is description
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        T.init(this);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
