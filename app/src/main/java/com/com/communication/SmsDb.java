package com.communication;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.RequiresApi;
import android.telephony.SmsManager;

import com.com.android.utils.ToastUtil;

import java.util.ArrayList;

/**
 * @Author: zff
 * @Time 12/28/18 5:27 PM  .
 * @Email: zhoufeifei@zhizhangyi.com
 * @Describe:
 */
public class SmsDb {

    private static final String SMS_URI_ALL = "content://sms/";
    private static final Uri uri = Uri.parse(SMS_URI_ALL);


    private static final String SENT_SMS_ACTION = "SENT_SMS_ACTION";
    private static int sendSmsCount = 0;

    /**
     * 发送短信
     *
     * @param phoneNum
     * @param smsContent
     */
    public static void sendSMS(String phoneNum, String smsContent, Context context) {
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> contents = smsManager.divideMessage(smsContent + sendSmsCount++);
        Intent intent = new Intent(SENT_SMS_ACTION);
        intent.putExtra("com.android.phone.extra.slot", sendSmsCount % 2);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        ArrayList<PendingIntent> sentIntents = new ArrayList<>();
        sentIntents.add(pendingIntent);
        smsManager.sendMultipartTextMessage(phoneNum, null, contents, sentIntents, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public static void sendSMS(String phoneNum, Context context) {
        String smsContent = "测试发送短信" + sendSmsCount++;
        Intent intent = new Intent(SENT_SMS_ACTION);

        int subId = 1;
        if (sendSmsCount % 2 == 0) {
            subId = 2;
        }
        SmsManager smsManager = SmsManager.getSmsManagerForSubscriptionId(subId);
        PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        smsManager.sendTextMessage(phoneNum, null, smsContent, sentIntent, null);
    }

    @SuppressLint("IntentReset")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public static void sendSMS(Context context) {
        Intent intent = new Intent(SENT_SMS_ACTION);
        long time = System.currentTimeMillis();
        String smsContent = "测试发送短信 " + time;
        intent.putExtra(Telephony.Sms.DATE, time);
        SmsManager smsManager = SmsManager.getDefault();
        PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        IntentFilter sendFilter = new IntentFilter(SENT_SMS_ACTION);
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final Intent intentFinal = intent;
                Bundle bundle = intent.getExtras();
                Object[] pdus = (Object[]) bundle.get("pdus");
            }
        };
        context.registerReceiver(receiver, sendFilter);
        smsManager.sendTextMessage("15101090032", null, smsContent, sentIntent, null);
    }

    /**
     * insert sms to inbox
     *
     * @param maxCount
     */
    public static void insertSms(final int maxCount, final Context context) {
        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                ContentResolver resolver = context.getContentResolver();
                for (int i = 0; i < maxCount; i++) {
                    ContentValues values = new ContentValues();
//                    values.put("address", "136" + i);//sender name
//                    values.put("body", " test sms " + i + " time: " + System.currentTimeMillis());

                    values.put("address", "1456789" );//sender name
                    values.put("body", " test sms ");


                    Uri uri = resolver.insert(Uri.parse("content://sms/inbox"), values);
                    System.out.println("insert uri "+uri);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                ToastUtil.showToast(context, "短信插入完成： " + maxCount);
            }
        };
        asyncTask.execute();
    }
}
