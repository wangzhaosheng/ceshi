package com.com.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;


import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * @Author: zff
 * @Time 2/14/19 10:29 AM  .
 * @Email: zhoufeifei@zhizhangyi.com
 * @Describe:
 */
public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = "SmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
//        //短信的信息封装在Intent中
//        Bundle bundle = intent.getExtras();
//        Object[] objects = (Object[]) bundle.get("pdus");
//        SmsMessage sms;
//        int currentApiVversion = Build.VERSION.SDK_INT;
//        //拿到广播中的所有短信(有可能短信太长在发送时被拆分成多条进行发送)
//        for (Object obj : objects) {
//            if (currentApiVversion >= Build.VERSION_CODES.LOLLIPOP)
//                sms = SmsMessage.createFromPdu((byte[]) obj, Telephony.SmsDb.Intents.SMS_RECEIVED_ACTION);
//            else
//                sms = SmsMessage.createFromPdu((byte[]) obj);
//            Log.i(this.getClass().getName(), sms.getOriginatingAddress());
//            Log.i(this.getClass().getName(), sms.getMessageBody());
//
//            //广播拦截
//            //来自1243的短信将会被拦截掉
//            if (sms.getOriginatingAddress().equals("13141319501")) {
//                abortBroadcast();
//            }
//        }
//        ZLog.d(TAG, "aaaa");

//        Object[] pduses = (Object[]) intent.getExtras().get("pdus");
//        for (Object puds : pduses) { //获取短信
//            byte[] pdusmessage = (byte[]) puds;
//            SmsMessage sms = SmsMessage.createFromPdu(pdusmessage);
//            String mobile = sms.getOriginatingAddress();
//            String content = sms.getMessageBody();
//            Date date = new Date(sms.getTimestampMillis());
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String time = format.format(date); //根据号码删除短信
//            ZLog.d(TAG, "content:" + content + " time: " + time + " mobile " + mobile);
//            int res = context.getContentResolver().delete(Uri.parse("content://sms"), Telephony.SmsDb.ADDRESS + " like '" + mobile + "'", null);
//            ZLog.d(TAG, "res: " + res);
//            abortBroadcast();
//        }
    }
}
