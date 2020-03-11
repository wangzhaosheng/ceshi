package com.communication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author: zff
 * @Time 18-10-12 下午5:57   .
 * @Email: zhoufeifei@zhizhangyi.com
 * @Describe:
 */
public class CustomPhoneStateListener extends PhoneStateListener {

    private static final String TAG_1 = "CustomPhoneStateListener";
    private final TelephonyManager telephonyManager;
    private AtomicBoolean isRegister = new AtomicBoolean(false);
    private AtomicBoolean isIdle = new AtomicBoolean(false);

    public CustomPhoneStateListener(Context context) {
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onServiceStateChanged(ServiceState serviceState) {
        super.onServiceStateChanged(serviceState);
        Log.e(TAG_1, "CustomPhoneStateListener onServiceStateChanged: " + serviceState);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        Log.e(TAG_1, "CustomPhoneStateListener state: "
                + state + " incomingNumber: " + incomingNumber);
        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:      // 电话挂断
                isIdle.set(true);
                break;
            case TelephonyManager.CALL_STATE_RINGING:   // 电话响铃
                Log.e(TAG_1, "CustomPhoneStateListener onCallStateChanged endCall");
//                HangUpTelephonyUtil.endCall(mContext);
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:   // 来电接通 或者 去电，去电接通  但是没法区分
                break;
        }
    }

    public void registerPhoneStateListener() {
        if (!isRegister.get()) {
            telephonyManager.listen(this, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

    public void unregisterPhoenStateListener() {
        if (isRegister.get()) {
            telephonyManager.listen(this, PhoneStateListener.LISTEN_NONE);
        }
    }

    public boolean isIdle() {
        return isIdle.get();
    }

}