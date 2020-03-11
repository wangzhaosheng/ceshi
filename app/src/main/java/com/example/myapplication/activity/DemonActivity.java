package com.example.myapplication.activity;

import android.os.Looper;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapplication.R;

public class DemonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_);
        System.out.println("demon进程id" + android.os.Process.myPid());
        if(Looper.getMainLooper()==Looper.myLooper()){
            System.out.println("DemonActivity是主线程");
        }else {
            System.out.println("DemonActivity不是主线程");
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("注册广播线程id"+Thread.currentThread().getId());
                CallRecordObserver  mCallRecordReceiver = new CallRecordObserver(null);
                getContentResolver().registerContentObserver(CallLog.Calls.CONTENT_URI, true, mCallRecordReceiver);
            }
        }).start();
    }
}
