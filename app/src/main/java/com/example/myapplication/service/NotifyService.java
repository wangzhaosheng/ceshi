package com.example.myapplication.service;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RemoteViews;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Time:  19-9-25  下午6:04
 * Author:  wangzhaosheng
 * Description：  todo  测试过的系统  华为 8  华为9
 * todo 乐视只有一次广播  华为有多次
 */
public class NotifyService extends NotificationListenerService {

    String TAG = "NotifyService";

    //    短信、QQ、微信对应的包名则为：
    public static final String QQ = "com.tencent.mobileqq";
    public static final String WX = "com.tencent.mm";
    public static final String MMS = "com.android.mms";
    public static final String xiao_mi = "com.xiaomi.xmsf";// TODO: 19-9-27

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
       super.onNotificationPosted(sbn);
//        默认开启了NotificationListenerService将收到系统所有开启了推送开关的应用的推送消息，如果想要收到指定应用消息，则需过滤该应用的包名：
        String packageName = sbn.getPackageName();
        if (!packageName.contains(MMS)) {
            Log.e(TAG, "不是短信应用");
            return;
        }

        Log.e(TAG, "id: " + sbn.getId());
        Notification notification = sbn.getNotification();
        CharSequence tickerText = notification.tickerText;
        Log.e(TAG, "flag: " + notification.flags);

        if (!TextUtils.isEmpty(tickerText)) {
            Log.e(TAG, "获取到短信内容了tickerText: " + tickerText);
            //华为第二次出现结果  带着手机号+：    ‭9532759184426526‬: 【拼多多】您的验证码是592401。请于5分钟内完成验证，若非本人操作，请忽略本短信。
            //todo 小米手机的内容如下   阳光出行: 【阳光出行】您的动态验证码为665594，有效期5分钟，感谢您对阳光出行的支持!    第一个内容是阳光出行
            //todo 乐视没有冒号：  9532759184426526【拼多多】您的验证码是479411。请于5分钟内完成验证，若非本人操作，请忽略本短信。
            //todo 华为多次广播不是每次都有，也有可能一次都没有这个字段  ‭10690836511908601233‬: 【阳光出行】您的动态验证码为858849，有效期5分钟，感谢您对阳光出行的支持!
            //todo vivo跟小米一样  有冒号  薪人薪事: 【薪人薪事】您申请的验证码为510279，请在10分钟内输入。
//            return;
        }

// TODO: 19-9-27 tickerText 有值的时候  下面的值都没有
        Bundle extras = notification.extras;
        if (extras == null) {
            Log.e(TAG, "extras==null");
            return;
        }

        String title = extras.getString("android.title");//小米乐视这个字段是名称或者手机号，有名称不是手机号  todo 华为 106910372653821（验证码）华为带着验证码几个字。。。
        String text = extras.getString("android.text");//小米这个不靠谱
        String text2 = extras.getString("android.bigText");//乐视没有这个字段  小米也没有
        Log.e(TAG, "title: "+title);
        if (!TextUtils.isEmpty(text)) {
            Log.e(TAG, "text: "+text);
        }
        if (!TextUtils.isEmpty(text2)) {
            Log.e(TAG, "bigText: "+ text2);
        }

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        Log.e(TAG, "onNotificationRemoved");
    }

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
