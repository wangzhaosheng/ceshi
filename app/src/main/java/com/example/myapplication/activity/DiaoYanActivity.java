package com.example.myapplication.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Looper;
import android.provider.Settings;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.AA;
import com.example.myapplication.IOUtils;
import com.example.myapplication.MyMediaRecorder;
import com.example.myapplication.R;

import jni.JniTest;

import com.example.myapplication.diaoyan.BABA;
import com.example.myapplication.diaoyan.ERZI;
import com.example.myapplication.diaoyan.TestT;
import com.example.myapplication.diaoyan.YEYE;
import com.example.myapplication.factory.SmsFactory;
import com.example.myapplication.proxy.IPrinter;
import com.example.myapplication.proxy.ISay;
import com.example.myapplication.proxy.Printer;
import com.example.myapplication.proxy.ProxyHandler;
import com.example.myapplication.service.CeShiService;
import com.example.myapplication.service.NotifyService;
import com.example.myapplication.service.ServiceUtils;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.Circle;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class DiaoYanActivity extends AppCompatActivity {


    //    public static final String CONNECTIVITY_CHANGE_ACTION = android.net.ConnectivityManager.CONNECTIVITY_ACTION;
    public static final String CONNECTIVITY_CHANGE_ACTION = "android.net.wifi.WIFI_HOTSPOT_CLIENTS_CHANGED";

    //    android.net.wifi.WIFI_HOTSPOT_CLIENTS_CHANGED
//    WifiManager.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diao_yan);
    }

    public void click37(View view) {
//        registerDateTransReceiver();

    }

    private void registerDateTransReceiver() {

        Log.i("Phone Link", "register receiver " + CONNECTIVITY_CHANGE_ACTION);

        IntentFilter filter = new IntentFilter();

        filter.addAction(CONNECTIVITY_CHANGE_ACTION);

        filter.setPriority(1000);

        registerReceiver(myReceiver, filter);

    }


    private MyReceiver myReceiver = new MyReceiver();

    public void click38(View view) {
        try {
            Intent intent;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            } else {
                intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            }
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void click39(View view) {
        startService(new Intent(this, NotifyService.class));
    }

    public void click40(View view) {

        SpinKitView spinKitView = (SpinKitView) findViewById(R.id.spin_kit);
        spinKitView.setIndeterminateDrawable(new Circle());
    }

    public void click41(View view) {

        startService(new Intent(this, CeShiService.class));
    }

    public void click43(View view) {
        boolean serviceRunning = ServiceUtils.isServiceRunning(this, "com.example.myapplication.service.NotifyService");
        Toast.makeText(this, serviceRunning ? "在运行" : "不在运行", Toast.LENGTH_LONG).show();
    }

    public void click44(View view) {
        PackageManager packageManager = getPackageManager();
        packageManager.setComponentEnabledSetting(new ComponentName(this, com.example.myapplication.service.NotifyService.class), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        packageManager.setComponentEnabledSetting(new ComponentName(this, com.example.myapplication.service.NotifyService.class), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

    public void click46(View view) {

        HashSet<Object> objects1 = new HashSet<>();
        objects1.add(1);
        objects1.add(2);
        objects1.add(3);
        HashSet<Object> objects2 = new HashSet<>();
        objects2.add(2);
        objects2.add(3);
        objects2.add(4);

        boolean b = objects1.removeAll(objects2);


        HashSet<Object> objects3 = new HashSet<>();
        objects3.add("a");
        objects3.add("b");
        objects3.add("c");

        HashSet<Object> objects4 = new HashSet<>();
        objects4.add("b");
        objects4.add("c");
        objects4.add("d");


        boolean c = objects3.removeAll(objects4);


    }

    public void click47(View view) {

        MyMediaRecorder.getInstance().start("/sdcard/aaa.amr");
    }

    public void click48(View view) {
        JniTest jniTest = new JniTest();
        jniTest.test();
//        JniTest.test();
    }

    public void click49(View view) {

        System.out.println("主进程id" + android.os.Process.myPid());


        new Thread(new Runnable() {
            @Override
            public void run() {
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    System.out.println("diaoyanActivity是主线程");
                } else {
                    System.out.println("diaoyanActivity不是主线程");
                }
                startActivity(new Intent(DiaoYanActivity.this, DemonActivity.class));
            }
        }).start();

    }

    public void click50(View view) {
        String a = "abc";
        String b = null;
        String s = a + b;
        System.out.println("ssss" + s);

    }

    public void click51(View view) {

        String[] strings = splitPhones("18811452634");
        String[] strings1 = splitPhones("18811452634;123445667778");
    }

    private String[] splitPhones(String phonesStr) {
        String[] phones = new String[0];
        if (!TextUtils.isEmpty(phonesStr)) {
            phones = phonesStr.split(";");
        }
        return phones;
    }

    public void click52(View view) {

        SmsFactory smsFactory = new SmsFactory();
        List<AA> msgId = smsFactory.getMsgId();
        System.out.println("msgId: " + msgId);

    }

    public void click55(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://www.baidu.com");
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.connect();
                    System.out.println("rrrrrr" + urlConnection);
                } catch (Exception e) {
                    System.out.println("-----" + e);
                }
                try {
                    URL url = new URL("http://www.wanglsdjflksjdf.com");
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.connect();
                    System.out.println("rrrrrr" + urlConnection);
                } catch (Exception e) {
                    System.out.println("-----" + e);
                }
            }
        }).start();

//
//        List<Integer>list;
//        for (Integer integer : list) {
//
//        }
//        for (int i = 0; i < list.size();) {
//
//        }
    }

    public void click56(View view) {

        String a = "+86862134";
        String substring = a.substring(3, a.length() - 2);
        String replace = a.replace("+86", "");
        System.out.println(a + "---" + replace);
    }

    public void click57(View view) {

        //获取PackageManager
        PackageManager packageManager = this.getPackageManager();
//获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
//用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<>();
//从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                String name = packageInfos.get(i).applicationInfo.name;
                packageNames.add(packName);
//                if(packName.contains("com.huawei.w")){
                System.out.println("pack: " + packName);
//                }
            }
        }
//        System.out.println("list: " + packageNames);
    }

    /**
     * 桶排序
     *
     * @param view
     */
    public void click60(View view) {
        int[] arr = {56, 4, 23, 1, 23, 8, 7, 45, 3, 6, 7, 8, 5, 6, 4, 4};
        bucketSort(arr, 5);

    }

    private int[] bucketSort(int[] arr, int bucketSize) {
//        if (arr == null || arr.length < 2 || bucketSize < 1) {
//            return arr;
//        }
//
//        int min = arr[0];
//        int max = arr[0];
//        for (int i : arr) {
//            if (i < min) {
//                min = i;
//            }
//            if (i > max) {
//                max = i;
//            }
//        }
//        int bucketCount = (max - min) / bucketSize + 1;
//
//
//        ArrayList<List<Integer>> list = new ArrayList<>(bucketCount);
//
//        for (int i = 0; i < bucketCount; i++) {
//            list.add(new ArrayList<Integer>());
//        }
//
//        for (int i = 0, len = arr.length; i < len; i++) {
//            int bucketIndex = (arr[i] - min) / bucketSize + 1;
//            list.get(bucketIndex).add(arr[i]);
//
//        }
//        for (List<Integer> bucket : list) {
//            if (bucket.size() > 0) {
//                if (bucketCount ==1){
//                    bucketSize--;
//                }
//
////                bucketSort(bucket.toArray(),bucketSize);
//
//            }
//        }


        return new  int[4];
    }

    public void click61(View view) {

        int []arr={1,3,5};
        int bsearch = bsearch(arr, 6);
        System.out.println(bsearch);

    }

    int bsearch ( int[] arr, int target){
        int L = 0, R = arr.length - 1;

        while (L <= R) {
            int mid = L + (R - L) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                if ((mid + 1) < arr.length && arr[mid + 1] > target) {
                    return mid;
                } else {
                    L = mid + 1;
                }
            } else {
                R = mid - 1;
            }
        }
        return -1;

    }

    public void click66(View view) {

        ProxyHandler proxyHandler=new ProxyHandler();
        ISay printer=(ISay) proxyHandler.newProxyInstance(new Printer());
        printer.woShuo();
        int aa[]=null;
        List bb =null;
    }

    public void click67(View view) {

        String resourceEntryName = getResources().getResourceEntryName(R.layout.activity_diao_yan);
    }


    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            System.out.println("PfDataTransReceiver receive action " + action);

            if (TextUtils.equals(action, CONNECTIVITY_CHANGE_ACTION)) { // 网络变化的时候会发送通知

                System.out.println("网络变化了");

                return;

            }
        }
    }


    public void click45(View view) {

        EditText viewById = findViewById(R.id.et);
        Editable text = viewById.getText();
        String s = text.toString();
        String s2 = "\u0000";
        System.out.println("s:" + s);
        System.out.println("特殊字符1：" + s2 + "===");
        System.out.println("特殊字符2只写空格：" + " " + "===");

//        HttpURLConnection httpURLConnection;
//        httpURLConnection.connect();
//        OutputStream outputStream = httpURLConnection.getOutputStream();


    }


    public void click54(View view) {

        TestT<ERZI> babaTestT = new TestT<>(new ERZI());
        BABA t = babaTestT.getT();
        List<? super BABA> babas = new ArrayList<>();
        babas.add(new BABA());
//        BABA baba = babas.get(0);
        Object object = babas.get(0);
        isOppoAddress();

    }

    private static final String mOppoAddress = "oppo_groupaddress";
    private volatile int mOppoStatus = -1;
    /**
     * 过滤需要查询的字段
     *
     * @return
     */
    /**
     * 过滤需要查询的字段
     *
     * @return
     */
    private boolean isOppoAddress() {
        Cursor cursor = null;
        try {
            ContentResolver resolver = this.getContentResolver();
            cursor = resolver.query(Telephony.Sms.CONTENT_URI, new String[]{"aaaa"}, null, null, Telephony.Sms._ID + " desc limit 0"); //
            if (null != cursor) {
                if (cursor.getColumnIndex(mOppoAddress) >= 0) {
                    mOppoStatus = 1;
                } else {
                    mOppoStatus = 0;
                }
            }
        } catch (Throwable t) {
//                ZLog.e(TAG, "isOppoAddress", t);
            System.out.println(t);
        } finally {
            IOUtils.closeQuietly(cursor);
        }
        return 0 < mOppoStatus;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(myReceiver);
    }
}
