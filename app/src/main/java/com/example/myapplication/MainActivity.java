package com.example.myapplication;

import android.Manifest;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.media.MediaRecorder;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.BaseColumns;
import android.provider.CallLog;
import android.provider.Settings;
import android.provider.Telephony;
import android.service.notification.StatusBarNotification;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.TimeUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ApnActivity;
import com.example.myapplication.activity.DiaoYan2Activity;
import com.example.myapplication.activity.DiaoYanActivity;
import com.example.myapplication.activity.UtilActivity;
import com.example.myapplication.mdmHuawei.HuaweiMdmActivity;
import com.example.myapplication.sim.EmmAppOpsManager;
import com.example.myapplication.wifi.HotsspotReceiver;
import com.example.myapplication.wifi.WifiHotUtil;
//import com.facebook.stetho.Stetho;
//import com.facebook.stetho.common.Util;
import com.google.gson.Gson;
//import com.ola.android22.mylibrary.Utils;
import com.tdtech.devicemanager.LocationPolicy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity {
    /**
     * 音频录制
     */
    private MediaRecorder recorder;
    /**
     * 初始化录音机，并给录音文件重命名
     *
     * @param incomingNumber 通话号码
     */

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    TextView viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = findViewById(R.id.textView);
/**
 * 在activity 或者 service中加入如下代码，以实现来电状态监听
 */
//        TelephonyManager telMgr = (TelephonyManager) this.getSystemService(
//                Context.TELEPHONY_SERVICE);
//        telMgr.listen(new MyListener(), PhoneStateListener.LISTEN_CALL_STATE);

//        File a = new File(Environment.getExternalStorageDirectory(), "a");

//        ArrayList<AA> objects1 = new ArrayList<>(objects);
//        for (AA o : objects1) {
//            o.a=4;
//        }
//        System.out.println(objects1.toString());
//        System.out.println(objects.toString());

        startService(new Intent(this, MyVpnService.class));

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_AP_STATE_CHANGED");
        registerReceiver(new HotsspotReceiver(), intentFilter);


    }


    /**
     * 获取录音文件的名称
     *
     * @return 获取录音文件的名称
     */
    private String getFileName() {
        Date date = new Date(System.currentTimeMillis());
        String currentFile = System.currentTimeMillis() + ".mp3";  // TODO: 2019/4/26   .mp3
        return currentFile;
    }

    Handler handler;
    WifiHotUtil wifiHotUtil;

    public void click99(View v) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        String address = BluetoothAdapter.getDefaultAdapter().getAddress();
        System.out.println("==========" + address);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
//        startActivity(new Intent(this,SecondActivity.class));

//        int call = ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.READ_CALL_LOG);
//        int sms = ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.READ_SMS);
////        int permissionGranted = PackageManager.PERMISSION_GRANTED;
//
//        boolean b = EmmAppOpsManager.requestWindowPermission(this.getApplicationContext());
////        boolean b2 = EmmAppOpsManager.requestWindowPermission2(this.getApplicationContext());
//        int a = EmmAppOpsManager.mmsPermission(this.getApplicationContext());
////        System.out.println(b);
//
//        int i = PermissionChecker.checkSelfPermission(this.getApplicationContext(), Manifest.permission.READ_CALL_LOG);
//        int i2 = PermissionChecker.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
//        int i1 = PermissionChecker.checkSelfPermission(this.getApplicationContext(), Manifest.permission.READ_SMS);
////        int jk = PermissionChecker.checkSelfPermission(this.getApplicationContext(), "Unknown(10005)");


        int permissionGranted = PERMISSION_GRANTED;
//        Toast.makeText(this, "电话:　" + i + "  短信: " + i1, Toast.LENGTH_LONG).show();
//        Stetho stetho;
//        String a = "AA";
//        byte[] bytes = a.getBytes();
//        System.out.println(bytes);

//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_CALL);
////        intent.setData(Uri.parse("tel:**61*" + "18201186417" + "%23")); // 无应答时进行呼叫转移
////            intent.setData(Uri.parse("tel:**920" )); // 取消无应答时进行呼叫转移
//            intent.setData(Uri.parse("tel:*92" + "18201186417" )); // 无应答时进行呼叫转移
//        startActivity(intent);


//        int i = "com.uusafe.emm.android".hashCode();
//        String format = String.format(".%x", i);
//        System.out.println(format);

        int a = 1;

        int b[] = new int[3];
//        int [3] c={2,4,3};


    }


    public void click100(View view) {
//
////        Uri phoneUri = Uri.parse("tel:%23%2361%23");  移动联通
//        Uri phoneUri = Uri.parse("tel:**920" );//电信
//        Intent dialIntent = new Intent(Intent.ACTION_CALL,
//                phoneUri);
//        dialIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(dialIntent);

        Date date = new Date();
        DateFormat format = new SimpleDateFormat("HH:mm");
        String format1 = format.format(date);
        System.out.println(format1);

        String a = "16:12";
        Date dt1 = null;//将字符串转换为date类型
        try {
            dt1 = format.parse(format1);
            Date dt2 = format.parse(a);
            boolean b = dt1.getTime() > dt2.getTime();
            if (b) { //比较时间大小,如果dt1大于dt2

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }


    public void click200(View view) {
//        Toast.makeText(this, Constants.AAA,1).show();
        File file = new File("/sdcard/aaa");
        File[] list = file.listFiles((dir, name) -> !name.startsWith(".") && name.endsWith(".amr"));
//        String[] list = file.list();
        System.out.println(list);
        for (File s : list) {
            System.out.println(s);
        }

//
//        int i = searchFirstLarger(list, new AA(14));
//        System.out.println("========="+i);
//          Toast.makeText(this, Utils.getDebug(),1).show();
    }


    int searchFirstLarger(ArrayList<AA> list, AA key) {
        //没有大于这个值的  返回-1
        if (list.isEmpty() || list.get(list.size() - 1).a <= key.a) {
            return -1;
        }


        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid).a > key.a) right = mid - 1;
            else if (list.get(mid).a <= key.a) left = mid + 1;
        }
        return left;
    }


    public static final String DEFAULT_TIME_ZONE = "GMT+8";

    public static long formatToMillis(String dateStr, SimpleDateFormat format, String tzId) {
        try {
            String tz = TextUtils.isEmpty(tzId) ? DEFAULT_TIME_ZONE : tzId;
            format.setTimeZone(TimeZone.getTimeZone(tz));
            return format.parse(dateStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static Long getTodayZeroPointTimestamps() {
        Long currentTimestamps = System.currentTimeMillis();
        Long oneDayTimestamps = Long.valueOf(60 * 60 * 24 * 1000);
        return currentTimestamps - (currentTimestamps + 60 * 60 * 8 * 1000) % oneDayTimestamps;
    }


    public void click2(View v) {
        startActivity(new Intent(this, CallSmsActivity.class));
    }


    TreeMap<AA, String> rf = new TreeMap<AA, String>(new Comparator() {

        @Override
        public int compare(Object o1, Object o2) {
            // TODO Auto-generated method stub
            AA a = (AA) o1;
            AA b = (AA) o2;
            return a.a > b.a ? 1 : -1;
        }
    });


    public void click12(View view) {
        startActivity(new Intent(this, UtilActivity.class));
    }

    public void click36(View view) {
        startActivity(new Intent(this, DiaoYanActivity.class));
    }
    public void click129(View view) {
        startActivity(new Intent(this, DiaoYan2Activity.class));
    }

    public void click64(View view) {

        startActivity(new Intent(this, HuaweiMdmActivity.class));
    }

    public void click70(View view) {

        startActivity(new Intent(Settings.ACTION_SETTINGS));
    }

    public void click77(View view) {
        startActivity(new Intent(this, ApnActivity.class));

    }

    public void click93(View view) throws JSONException {

        ContentResolver resolver = getContentResolver();
        String[] projection = {
                CallLog.Calls.PHONE_ACCOUNT_ID,
//                "simid",
        };
        String sortOrder = BaseColumns._ID + " desc limit 10 ";
        Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, null, null, null, sortOrder);

        int count = cursor.getCount();
        int columnCount = cursor.getColumnCount();


        while (cursor.moveToNext()) {
            JSONObject jsonObject = new JSONObject();
            String record_path = cursor.getString(cursor.getColumnIndex("record_path"));
            for (int i = 0; i < columnCount; i++) {
                jsonObject.put(cursor.getColumnName(i), cursor.getString(i));
//                if (cursor.getColumnName(i).contains("hang_up")) {
//                    Log.e("1111111111111",cursor.getString(i));
//                }
//                stringBuilder.append(cursor.getColumnName(i)+":"+cursor.getString(i)+" ");

            }
            System.out.println(jsonObject);
            Log.e("yuanganggang", jsonObject.toString());
//            System.out.println(jsonObject.get("_id"));
//            textView2.setText(stringBuilder);


            //todo 整体获取通话时间 date字段 并且格式化
//            long date = cursor.getLong(cursor.getColumnIndex("date"));
//            Date date1 = new Date(date);
//            System.out.println(date1.getMinutes() + "------" + date1.getSeconds());


            //todo 整体华为rom才能用----------
//            System.out.println("响铃时间：" + cursor.getInt(cursor.getColumnIndex("ring_times")));
//            System.out.println("时长duration："+ cursor.getInt(cursor.getColumnIndex("duration")));
            //todo 整体华为rom才能用-----------


            //todo 整体vivo获取imsi-------------------
//            int subid = cursor.getInt(cursor.getColumnIndex( "simid"));
//            System.out.println("subid"+subid);
//            String imsiFromSubId = getImsiFromSubId(subid);
//            System.out.println("imsiFromSubId"+imsiFromSubId);
            //todo 整体vivo-------------------


            //todo 整体华为获取imsi-------------------
//            String slotId = cursor.getString(cursor.getColumnIndex( CallLog.Calls.PHONE_ACCOUNT_ID));
//            System.out.println("slotId"+slotId);
//            if(slotId==null){
//                continue;
//            }
//            int subId = getSubId(Integer.parseInt(slotId));
//            System.out.println("subid--------"+subId);
//            String imsiFromSubId = getImsiFromSubId(subId);
//            System.out.println("imsiFromSubId"+imsiFromSubId);
            //todo 整体华为-------------------

//            break;


        }
    }
}
