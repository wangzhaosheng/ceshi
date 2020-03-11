package com.example.myapplication.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class HotsspotReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("android.net.wifi.WIFI_AP_STATE_CHANGED")) {//便携式热点的状态为：10---正在关闭；11---已关闭；12---正在开启；13---已开启
            int state = intent.getIntExtra("wifi_state", 0);
            if (state == 10) {
                System.out.println("热点状态：正在关闭");
            } else if (state == 11) {
                System.out.println("热点状态：已关闭");
//                hotState = false;
            } else if (state == 12) {
                System.out.println("热点状态：正在开启");
            } else if (state == 13) {
//                hotState = true;
                System.out.println("热点状态：已开启");
            }
        }
    }
}