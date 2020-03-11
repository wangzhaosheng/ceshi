package com.example.myapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Time:  19-9-29  下午8:20
 * Author:  wangzhaosheng
 * Description：
 */
public class CeShiService extends Service {

    String TAG ="CeShiService";
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "测试服务开启");
        return super.onStartCommand(intent, flags, startId);
    }
}
