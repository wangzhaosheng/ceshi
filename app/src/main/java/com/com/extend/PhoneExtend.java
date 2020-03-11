package com.extend;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * @Author: zff
 * @Time 12/28/18 5:53 PM  .
 * @Email: zhoufeifei@zhizhangyi.com
 * @Describe:
 */
public class PhoneExtend {

    public static final Uri CONTENT_URI = Uri.parse("content://com.uusafe.emm.android.extend");
    private static final String CALLER = "caller";
    private static final String JOB_NO = "jobNo";
    private static final String WAYBILL = "waybill";
    private static final String CUSTOMER_PHONE_NO_MD5 = "customerPhoneNoMD5";
    private static final String YUAN_TONG_METHOD = "yuan_tong_caller";

    /**
     * @param context
     * @param caller             主叫手机号
     * @param jobNo              员工工号
     * @param waybill            运单号
     * @param customerPhoneNoMD5 客户手机号MD5
     */
    public static void performBackIpc(Context context, String caller, String jobNo, String waybill, String customerPhoneNoMD5) {
        try {
            Bundle bundle = new Bundle();
            bundle.putSerializable(CALLER, caller);//"15850518962"
            bundle.putSerializable(JOB_NO, jobNo);//"00019386"
            bundle.putSerializable(WAYBILL, waybill);
            bundle.putSerializable(CUSTOMER_PHONE_NO_MD5, customerPhoneNoMD5);
            context.getContentResolver().call(CONTENT_URI, YUAN_TONG_METHOD, null, bundle);
        } catch (Exception e) {
        }
    }
}
