package com.example.myapplication;

import android.app.Application;
import android.content.Context;

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
}
