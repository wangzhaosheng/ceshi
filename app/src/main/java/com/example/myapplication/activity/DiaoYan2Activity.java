package com.example.myapplication.activity;

import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.media.AudioManager;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.example.myapplication.R;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class DiaoYan2Activity extends AppCompatActivity {
    private static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";

    private static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diao_yan2);
    }

    private MyBroadCastReceiver myBroadCastReceiver;

    public void click130(View view) {
//https://blog.csdn.net/ljphhj/article/details/39481411

        myBroadCastReceiver = new MyBroadCastReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);//亮灭屏
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);//亮灭屏
        intentFilter.addAction(VOLUME_CHANGED_ACTION);//声音变化

        System.out.println("I'm coming, myBroadCastReceiver注册了!");
        registerReceiver(myBroadCastReceiver, intentFilter);
    }



    private class MyBroadCastReceiver extends BroadcastReceiver {
        //https://blog.csdn.net/ljphhj/article/details/39481411
        @Override
        public void onReceive(Context context, Intent intent) {
            //你自己先把 reasons == homekey 和 长按homekey 排除，剩下的做下面的处理
            String reason = intent.getStringExtra("reason");
            if (intent.getAction().equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                System.out.println("Intent.ACTION_CLOSE_SYSTEM_DIALOGS : " + intent.getStringExtra("reason"));

                if (intent.getExtras() != null && intent.getExtras().getBoolean("myReason")) {
                    myBroadCastReceiver.abortBroadcast();
                } else if (reason != null) {

                    if (reason.equalsIgnoreCase("globalactions")) {

                        //监听电源长按键的方法：
//                        Intent myIntent = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
//                        myIntent.putExtra("myReason", true);
//                        context.sendOrderedBroadcast(myIntent, null);
                        System.out.println("电源  键被长按");

                    } else if (reason.equalsIgnoreCase("homekey")) {

                        //监听Home键的方法
                        //在这里做一些你自己想要的操作,比如重新打开自己的锁屏程序界面，这样子就不会消失了
                        System.out.println("Home 键被触发");

                    } else if (reason.equalsIgnoreCase("recentapps")) {

                        //监听Home键长按的方法
                        System.out.println("Home 键被长按");
                    }
                }
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                //灭屏
                System.out.println("灭屏");
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                //亮屏屏
                System.out.println("亮屏屏");
//            }else if(intent.getAction().equals(VOLUME_CHANGED_ACTION)&& (intent.getIntExtra(EXTRA_VOLUME_STREAM_TYPE, -1) == AudioManager.STREAM_MUSIC)){
            } else if (intent.getAction().equals(VOLUME_CHANGED_ACTION)) {
                // //媒体音量改变才通知   如果不加STREAM_MUSIC  会有三次回调
                System.out.println("音量变化了");
            }
        }

    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        super.onKeyDown(keyCode, event);
//        if(keyCode == KeyEvent.KEYCODE_POWER){
//            event.startTracking();
//        }
//
//    }

//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        switch (keyCode) {
//            case KeyEvent.KEYCODE_VOLUME_UP:
//                System.out.println("音量增加");
//                return super.onKeyDown(keyCode, event);
//            case KeyEvent.KEYCODE_VOLUME_DOWN:
//                System.out.println("音量减小");
//                return super.onKeyDown(keyCode, event);
//
//            case KeyEvent.KEYCODE_POWER:
//                //电源
//                System.out.println("onKeyDown 电源");
//                break;
//            default:
//                break;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_POWER) {
            System.out.println("onKeyLongPress 电源 长按");
        }
        return super.onKeyLongPress(keyCode, event);
    }


    public void click132(View view) {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
                queryEvents();
//            }
//        }, 4000);


    }

    Function<Integer, String> fun = (integer) -> String.valueOf(integer+"222");

    Function<String, Integer> fun1 = new Function<String, Integer>() {
        @Override
        public Integer apply(String string) {
            return Integer.parseInt(string);
        }
    };
    public void click133(View view) {

        Function<String, String> compose = fun.compose(fun1);

    }

    public void queryEvents() {
        UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(USAGE_STATS_SERVICE);
        long time = System.currentTimeMillis();
//获取最近一小时的应用最近使用统计信息
        UsageEvents usageEvents = usageStatsManager.queryEvents(time - 1000 * 60 *60, time);
        if (usageEvents == null || !usageEvents.hasNextEvent()) {
            try {
                startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            while (usageEvents.hasNextEvent()) {
                UsageEvents.Event event = new UsageEvents.Event();
                if (usageEvents.getNextEvent(event)) {
                    if (event.getEventType() == UsageEvents.Event.MOVE_TO_FOREGROUND) {
                        Log.e("UsageEvents", event.getPackageName()+"应用在前台"+": 发生时间: " + new Date(event.getTimeStamp()).toString());
                    }else {
                        Log.e("UsageEvents", event.getPackageName()+"应用在后台"+": 发生时间: " + new Date(event.getTimeStamp()).toString());
                    }
//                    event.getPackageName();//获取包名
//                    event.getClassName();//获取类名
//                    event.getTimeStamp();//获取发生此事件的时间
//                    event.getConfiguration();//获取可以的所有设备配置信息影响应用程序检索的资源
                }
            }
        }
    }


}