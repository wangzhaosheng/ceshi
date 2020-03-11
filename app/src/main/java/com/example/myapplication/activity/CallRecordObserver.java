
package com.example.myapplication.activity;

import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;


/**
 * Created by ubuntu on 16-5-23.
 */
class CallRecordObserver extends ContentObserver {
    private final Handler handler;


    CallRecordObserver(Handler h) {
        super(null);
        handler = h;
    }


    @Override
    public void onChange(boolean selfChange) {
//        handler.removeCallbacks(collectTask);
//        handler.postDelayed(collectTask, 1_000);
        System.out.println("广播线程id"+Thread.currentThread().getId());
        System.out.println("广播进程"+android.os.Process.myPid());
        if(Looper.getMainLooper()==Looper.myLooper()){
            System.out.println("广播是主线程");
        }else {
            System.out.println("广播不是主线程");
        }
    }

//    private Runnable collectTask = () -> TransparentActivity.actionQueryDB(AppEnv.getContext(), TransparentActivity.TRIGGER_PHONE);




}

