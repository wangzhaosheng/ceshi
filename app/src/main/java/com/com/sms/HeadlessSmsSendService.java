package com.sms;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * @Author: zff
 * @Time 2/14/19 10:31 AM  .
 * @Email: zhoufeifei@zhizhangyi.com
 * @Describe:
 */
public class HeadlessSmsSendService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
