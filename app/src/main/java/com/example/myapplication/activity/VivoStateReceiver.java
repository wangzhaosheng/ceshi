package com.example.myapplication.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: zff
 * @Time 18-10-29 下午7:33   .
 * @Email: zhoufeifei@zhizhangyi.com
 * @Describe:
 */
public class VivoStateReceiver extends BroadcastReceiver {
    private static final String TAG = "VivoStateReceiver";
    public static final int PHONE_STATE_NOME = -1;       // 未初始化
    public static final int PHONE_STATE_IDLE = 1;       //  无通话
    public static final int PHONE_STATE_RINGING = 2;       // 有通话状态


    public static final int IDLE = 0;       // 无通话
    public static final int ACTIVE = 1;     // 通话中
    public static final int ON_HOLD = 2;    // 保持通话中
    public static final int DIALING = 3;    // 新去电
    public static final int RINGING = 5;    // 新来电
    public static final int DISCONNECTED = 7; // 电话挂断

    final AtomicLong mPhoneState = new AtomicLong(PHONE_STATE_NOME);
    final AtomicLong mId = new AtomicLong(PHONE_STATE_NOME);
    private AtomicBoolean isRegister = new AtomicBoolean(false);
    private AtomicLong mTryTime = new AtomicLong(System.currentTimeMillis());
    private static final long WAIT_MAX_TIME = TimeUnit.SECONDS.toMillis(5);

    private static Singleton<VivoStateReceiver> singleton = new Singleton<VivoStateReceiver>() {
        @Override
        protected VivoStateReceiver create() {
            return new VivoStateReceiver();
        }
    };

    public static VivoStateReceiver getSingleton() {
        return singleton.get();
    }

    private VivoStateReceiver() {
        super();
    }

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
        Bundle bundle = intent.getBundleExtra("bundle");
        if (null != bundle) {
            int state = bundle.getInt("callState", 0);
            Log.d(TAG, "state: " + state);
            mTryTime.set(System.currentTimeMillis());
            mPhoneState.set((IDLE == state || DISCONNECTED == state) ? PHONE_STATE_IDLE : PHONE_STATE_RINGING);
            if (mId.get() == PHONE_STATE_NOME) {
                return;
            }
            String phone = bundle.getString("callHandle");
            if (TextUtils.isEmpty(phone)) {
                Log.d(TAG, "result phone is null");
                return;
            }
            System.out.println("================"+ state);
            switch (state) {

                case ACTIVE:
                    break;
                case DISCONNECTED:
                    break;
                default:
                    break;
            }

        }
    }


    public void register(Context context) {
//        if (isRegister.get()) {
//            return;
//        }
//        isRegister.set(true);
//        unregister(context);
        try {
            IntentFilter f = new IntentFilter();
            f.addAction("android.intent.action.PRECISE_CALL_STATE_CHANGE");
            f.addAction("android.intent.action.PRECISE_CALL_STATE_CHANGE_SEC");
           context.registerReceiver(this, f);
        } catch (Throwable e) {
            Log.d(TAG, String.valueOf(e));
//            isRegister.set(false);
        }
    }

    public void unregister(Context context) {
        try {
            context.unregisterReceiver(this);
        } catch (Exception ignore) {
        }
    }



    public void startCallPhone() {
        mPhoneState.set(PHONE_STATE_RINGING);
    }

    public void setId(long id) {
        mId.set(id);
    }


}
