package com.communication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.CallLog;
import android.support.annotation.RequiresApi;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;


import java.util.List;

/**
 * @Author: zff
 * @Time 1/21/19 5:40 PM  .
 * @Email: zhoufeifei@zhizhangyi.com
 * @Describe:
 */
public class PhoneAction {

    private static int count = 0;
    private static String TAG = "PhoneAction";
    private static final String[] dualSimTypes = {
            "Subscription",
            "com.android.phone.extra.slot",
            "phone",
            "com.android.phone.DialingMode",
            "simId",
            "simnum",
            "phone_type",
            "simSlot"
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    private static PhoneAccountHandle getPhoneAccountHandle(Context context, int slotId) {
        TelecomManager telecomManager = (TelecomManager) context.getSystemService(Context.TELECOM_SERVICE);
        //PhoneAccountHandle api>5.1
        List<PhoneAccountHandle> handles = telecomManager.getCallCapablePhoneAccounts();
        SubscriptionManager sm = SubscriptionManager.from(context);
        for (PhoneAccountHandle handle : handles) {
            SubscriptionInfo info = sm.getActiveSubscriptionInfoForSimSlotIndex(slotId);
            if (info != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (TextUtils.equals(info.getIccId(), handle.getId())) {
                        return handle;
                    }
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    if (TextUtils.equals(info.getSubscriptionId() + "", handle.getId())) {
                        return handle;
                    }
                }
            }
        }
        return null;
    }

    private static String pkgName;

    public static void goCallPhone(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(callPhoneIntent("13691060723"), PackageManager.MATCH_DEFAULT_ONLY);
        if (list != null && !list.isEmpty()) {
            for (ResolveInfo resolveInfo : list) {
                if (0 < (resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)) {
                    pkgName = resolveInfo.activityInfo.packageName;
                    String className = resolveInfo.activityInfo.name;
                    ComponentName name = new ComponentName(pkgName, className);
                    Intent intent = callPhoneIntent("13691060723");
                    intent.setComponent(name);
                    context.startActivity(intent);
                }
            }
        }
//        context.startActivity(callPhoneIntent(""));
    }


    public static Intent callPhoneIntent(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
//        Uri data = Uri.parse("tel:" + phoneNum);
//        intent.setData(data);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void callPhoneHandle(Context context) {
        try {
            PhoneAccountHandle phoneAccountHandle = getPhoneAccountHandle(context, count++ % 2);
            Intent intent = new Intent(Intent.ACTION_CALL/*, Uri.parse("tel:" + "13691060723")*/);
//            intent.putExtra(TelecomManager.EXTRA_PHONE_ACCOUNT_HANDLE, phoneAccountHandle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
