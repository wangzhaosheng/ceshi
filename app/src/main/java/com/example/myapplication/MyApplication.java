package com.example.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * Created by wangzhaosheng on 2020-01-02
 * Description
 */
public class MyApplication extends Application {
    static Context context;

    public static Context getContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }

    private void initDebug() {
        debug = getApplicationInfo() != null && (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }
    public static boolean isDebug() {
        return debug;
    }

    private static boolean debug = false;
}
