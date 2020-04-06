package com.example.myapplication.record;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.MyApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class MediaRecorderReceiver extends BroadcastReceiver {
    public static final int IDLE = 0;       // 无通话
    public static final int ACTIVE = 1;     // 通话中
    public static final int ON_HOLD = 2;    // 保持通话中
    public static final int DIALING = 3;    // 新去电
    public static final int RINGING = 5;    // 新来电
    public static final int DISCONNECTED = 7; // 电话挂断

    private static final String TAG = MediaRecorderReceiver.class.getSimpleName();

    private static class Holder {
        private static MediaRecorderReceiver recorder = new MediaRecorderReceiver();
    }


    public static MediaRecorderReceiver getInstance() {
        return Holder.recorder;
    }

    private MediaRecorderManager mediaRecorderManager;
    /**
     * 单线程池
     */
    private ExecutorService executors = Executors.newSingleThreadExecutor();


    private MediaRecorderReceiver() {
        mediaRecorderManager = new MediaRecorderManager();
    }

    /**
     * 目前测试   出入的路径带后缀.amr   以后项目中自己定
     */
    private String filePath;

    /**
     * callState 通话状态，详见附录
     * callHandle 电话号码
     * slotId取值为0(卡槽1) 或 1(卡槽2)
     * iccid 当前通话使用卡的iccid，可能为空字符串
     * age 通话时长，只有在callState = DISCONNECTED时有值，单位：秒
     *
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = new Bundle();
        bundle = intent.getBundleExtra("bundle");
        //见上面注释
        final int state = bundle.getInt("callState");
        //对方号码
        String number = bundle.getString("callHandle");
        Log.e(TAG, "state" + state + " number: " + number);
        executors.execute(new Runnable() {
            @Override
            public void run() {
                if (state == ACTIVE) {
                    mediaRecorderManager.record(filePath);
                } else if(state ==DISCONNECTED) {
                    mediaRecorderManager.stopRecord();
                }

            }
        });

    }


    private AtomicBoolean isRegister = new AtomicBoolean(false);

    /**
     * @param filePath 录音文件保存绝对路径
     *                 原vivo定制录音传入路径: example:/storage/emulated/0/Android/data/com.android.systemui/.f9450992/recording/20190415163349_10086
     *                 自己监听自己录音的方式路径目前
     * @return
     * @zpi 开启录音（开启后应用维持状态,监听来电startRecord，挂断stopRecord）
     */
    public synchronized boolean startRecord(final String filePath) {
        this.filePath = filePath;
        if (isRegister.get()) {
            return true;
        }
        isRegister.set(true);
        unregister();
        try {
            registerReceiver();
        } catch (Throwable e) {
            Log.e(TAG, "startRecord" + e);
            isRegister.set(false);
            return false;
        }
        return true;
    }


    public synchronized void stopRecord() {
        unregister();
        mediaRecorderManager.stopRecord();
    }


    private void registerReceiver() {
        IntentFilter f = new IntentFilter();
        f.addAction("vivo.app.action.EMM_PRECISE_CALL_STATE_CHANGE");
        MyApplication.getContext().registerReceiver(this, f);
    }

    private void unregister() {
        try {
            MyApplication.getContext().unregisterReceiver(this);
        } catch (Exception ignore) {
            Log.e(TAG, "unregister" + ignore);
        }
    }


}