package com.example.myapplication.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.AudioRecordingConfiguration;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.myapplication.MyApplication;
import com.example.myapplication.R;
//import com.uusafe.common.device.env.DevEnv;
//import com.zhizhangyi.platform.common.rtc.AlarmReceiver;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;
import java.util.logging.Logger;

public class DiaoYan2Activity extends AppCompatActivity {
    private static final String VOLUME_CHANGED_ACTION = "android.media.VOLUME_CHANGED_ACTION";

    private static final String EXTRA_VOLUME_STREAM_TYPE = "android.media.EXTRA_VOLUME_STREAM_TYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diao_yan2);


    }

    private void hideNavigationBar() {

        Toast.makeText(getApplicationContext(), "hideNavigationBar()", Toast.LENGTH_SHORT).show();

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private MyBroadCastReceiver myBroadCastReceiver;

    public void click130(View view) {
//https://blog.csdn.net/ljphhj/article/details/39481411

        myBroadCastReceiver = new MyBroadCastReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);//亮灭屏
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);//亮灭屏
        intentFilter.addAction(VOLUME_CHANGED_ACTION);//声音变化

        System.out.println("I'm coming, myBroadCastReceiver注册了!");
        registerReceiver(myBroadCastReceiver, intentFilter);
    }

    /**
     * wifi热点
     * vivo手机不知道跳转了个什么奇葩的界面,跟系统的不一样,但是也是热点设置. 但是vivo点击系统左上角返回键返回的是系统设置, 点击back回当前应用
     * 华为手机可以实现,并且华为点击返回键能回当前应用
     *
     * @param view
     */

    public void click201(View view) {
        Intent intent = new Intent();
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setAction("android.intent.action.MAIN");
        ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.Settings$TetherSettingsActivity");
        intent.setComponent(cn);
        startActivity(intent);
    }

    /**
     * 系统更新界面  vivo会崩溃
     *
     * @param view
     */
    public void click202(View view) {

        if (isHuaWeiRom()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent("android.settings.SYSTEM_UPDATE_SETTINGS");
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }).start();
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent();
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.setAction("android.intent.action.MAIN");
                    //todo vivo 系统更新页面的包名类名
                    ComponentName cn = new ComponentName("com.bbk.updater", "com.bbk.updater.ui.UpdateActivity");
                    intent.setComponent(cn);
                    startActivity(intent);
                }
            }).start();
        }


    }

    public static boolean isHuaWeiRom() {
//        return DevEnv.Model.isRom(DevEnv.Model.EROM_HUAWEI) ||
//                DevEnv.Model.isRom(DevEnv.Model.EROM_HUAWEI_EMUI_3)
//                ;
        return true;
    }


    public static boolean isVivoRom() {
//        return DevEnv.Model.isRom(DevEnv.Model.EROM_VIVO);
        return true;
    }

    public static boolean isOppoRom() {
//        return DevEnv.Model.isRom(DevEnv.Model.EROM_OPPO);
        return true;
    }

    /**
     * wifi设置页面
     *
     * @param view
     */
    public void click203(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).start();


    }

    /**
     * 跳锁屏密码页面
     *
     * @param view
     */

    public void click204(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(DevicePolicyManager.ACTION_SET_NEW_PASSWORD);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).start();
    }

    public void click205(View view) {

        queryPackage();
    }


    //查找所有  com.android.server.telecom
    private void queryPackage() {
        PackageManager manager = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Intent intent = new Intent(Intent.ACTION_CALL);
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
//        intent.setData(Uri.parse("http://www.baidu.com"));
//        List<ResolveInfo> infos = manager.queryIntentActivities(intent, PackageManager.MATCH_ALL);
        List<ResolveInfo> infos = manager.queryIntentActivities(intent, PackageManager.MATCH_ALL);
    }

    public void click224(View view) {
        // TODO: 2020-05-29 经测试  这玩意不好使  屏幕旋转广播经常不生效. 在华为畅享平板测试

        BroadcastReceiver broadcastReceive = new AABroadcastReceive();
//注册广播接收,注意：要监听这个系统广播就必须用这种方式来注册，不能再xml中注册，否则不能生效
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        registerReceiver(broadcastReceive, filter);

        int b = 2;
        int i = b ^ b;
        String m = "a";
        System.out.println("i: " + i);
        if (b == 5 & i == 4) {
            System.out.println("-------");
        }
        int a = 3;
        int c = a & b;
        System.out.println("c: " + c);
        char v = 'a';

    }

    //todo 获取手机型号
    public void click234(View view) {

        System.out.println("手机型号机型: "+Build.MODEL);
    }

    public void click225(View view) {

        //todo 1普通方法  vivo好使
//        boolean navigationBarShow = hasNavBar(this);
//        Toast.makeText(this, navigationBarShow ? "有导航栏" : "没有导航栏", 1).show();

        //todo 2小米的  经测试好使
        boolean isMiuiFullScreen = isMiuiFullScreen(this);
        Toast.makeText(this, isMiuiFullScreen ? "没导航栏" : "有导航栏", 1).show();

        //todo 3 华为的  经测试好使
//        boolean isHuaWeiHideNav = isHuaWeiHideNav(this);
//        Toast.makeText(this, isHuaWeiHideNav ? "没导航栏" : "有导航栏", 1).show();
    }

    public void click226(View view) {
        int navigationBarHeight = getNavigationBarHeight(MyApplication.getContext());

        Toast.makeText(this, "导航栏宽度px" + navigationBarHeight, 1).show();
        System.out.println("导航栏宽度px" + navigationBarHeight);
    }

    /**
     * 华为手机是否隐藏了虚拟导航栏
     *
     * @return true 表示隐藏了，false 表示未隐藏
     */
    private boolean isHuaWeiHideNav(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return Settings.System.getInt(context.getContentResolver(), "navigationbar_is_min", 0) != 0;
        } else {
            return Settings.Global.getInt(context.getContentResolver(), "navigationbar_is_min", 0) != 0;
        }
    }

    /**
     * 小米手机是否开启手势操作
     *
     * @return true 表示使用的是手势，false 表示使用的是虚拟导航栏(NavigationBar)，默认是false
     */
    private boolean isMiuiFullScreen(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "force_fsg_nav_bar", 0) != 0;
    }

    /**
     * 判断是否隐藏虚拟按键
     *
     * @return
     */
    public boolean isNavigationBarShow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            if (null == manager) {
                return false;
            }
            Display display = manager.getDefaultDisplay();
            Point size = new Point();
            Point realSize = new Point();
            display.getSize(size);
            display.getRealSize(realSize);
            return realSize.y != size.y;
        } else {
            boolean menu = ViewConfiguration.get(this).hasPermanentMenuKey();
            boolean back = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);
            if (menu || back) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * 判断是否有NavigationBar
     * todo 金光s6上测试不好使.开不开起导航栏,都获取到true . 开不开导航栏获取到的屏幕显示高度都一样
     *
     * @param activity
     * @return
     */
    public boolean checkHasNavigationBar(Context activity) {
//        WindowManager windowManager = activity.getWindowManager();
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display d = windowManager.getDefaultDisplay();

        DisplayMetrics realDisplayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            d.getRealMetrics(realDisplayMetrics);
        }

        int realHeight = realDisplayMetrics.heightPixels;
        int realWidth = realDisplayMetrics.widthPixels;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        d.getMetrics(displayMetrics);

        int displayHeight = displayMetrics.heightPixels;
        int displayWidth = displayMetrics.widthPixels;

        return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
    }


    /**
     * 检查是否存在虚拟按键栏
     * todo 这个金光s6测试好使, 小米手机测试不好使
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private static boolean hasNavBar(Context context) {
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (resourceId != 0) {
            boolean hasNav = res.getBoolean(resourceId);
            String sNavBarOverride = getNavBarOverride();
            if ("1".equals(sNavBarOverride)) {
                hasNav = false;
            } else if ("0".equals(sNavBarOverride)) {
                hasNav = true;
            }
            return hasNav;
        } else {
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }
    }


    /**
     * 判断虚拟按键栏是否重写
     */
    private static String getNavBarOverride() {
        String sNavBarOverride = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                Class c = Class.forName("android.os.SystemProperties");
                Method m = c.getDeclaredMethod("get", String.class);
                m.setAccessible(true);
                sNavBarOverride = (String) m.invoke(null, "qemu.hw.mainkeys");
            } catch (Exception e) {
            }
        }
        return sNavBarOverride;
    }


    /**
     * 获得NavigationBar的高度
     * todo 在金光s6上测试.无论有没有开启导航.都能获取到高度
     */
    public static int getNavigationBarHeight(Context activity) {

        int result = 0;
        if (hasNavBar(activity)) {
            Resources res = activity.getResources();
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = res.getDimensionPixelSize(resourceId);
            }
        }
        return result;

//        int result = 0;
//        Resources resources = activity.getResources();
//        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
//        if (resourceId > 0 && checkHasNavigationBar(activity)) {
//            result = resources.getDimensionPixelSize(resourceId);
//        }
//        return result;
    }

    public void click227(View view) {

        int a = -106;
        int i = a >> 1;
        System.out.println("+++" + i);
        int c=4;
        int d=5;
        int [] b={c,d};
    }




    public void click228(View view) {
        //https://developer.android.com/guide/topics/media/sharing-audio-input?hl=zh-cn
        AudioManager   mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        //
        //Added in API level 24
        List<AudioRecordingConfiguration> activeRecordingConfigurations = mAudioManager.getActiveRecordingConfigurations();
        System.out.println(activeRecordingConfigurations);
    }

    public void click229(View view) {

        int a =-5;
        int i = a / 2;
        Toast.makeText(this,i+"",1).show();
    }

    public void click232(View view) {

        File file = new File("/sdcard/uusafe/emm_android/logcat/2.1.0.3");
        String[] list = file.list();
        System.out.println(Arrays.toString(list));
    }

    public void click233(View view) {

    }

    public void click235(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("后台执行--------");
            }
        }).start();
        ccc();
    }
    Handler handler = new Handler();
    private void ccc() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("定时任务执行----");
                ccc();
            }
        }, 90000);
    }

    class AABroadcastReceive extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("屏幕旋转了");
        }
    }

    private class MyBroadCastReceiver extends BroadcastReceiver {
        //https://blog.csdn.net/ljphhj/article/details/39481411
        @Override
        public void onReceive(Context context, Intent intent) {
            //你自己先把 reasons == homekey 和 长按homekey 排除，剩下的做下面的处理
            String reason = intent.getStringExtra("reason");
            if (intent.getAction().equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                System.out.println("Intent.ACTION_CLOSE_SYSTEM_DIALOGS : " + intent.getStringExtra("reason"));

                if (intent.getExtras() != null && intent.getExtras().getBoolean("myReason")) {
                    myBroadCastReceiver.abortBroadcast();
                } else if (reason != null) {

                    if (reason.equalsIgnoreCase("globalactions")) {

                        //监听电源长按键的方法：
//                        Intent myIntent = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
//                        myIntent.putExtra("myReason", true);
//                        context.sendOrderedBroadcast(myIntent, null);
                        System.out.println("电源  键被长按");

                    } else if (reason.equalsIgnoreCase("homekey")) {

                        //监听Home键的方法
                        //在这里做一些你自己想要的操作,比如重新打开自己的锁屏程序界面，这样子就不会消失了
                        System.out.println("Home 键被触发");

                    } else if (reason.equalsIgnoreCase("recentapps")) {

                        //监听Home键长按的方法
                        System.out.println("Home 键被长按");
                    }
                }
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
                //灭屏
                System.out.println("灭屏");
            } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
                //亮屏屏
                System.out.println("亮屏屏");
//            }else if(intent.getAction().equals(VOLUME_CHANGED_ACTION)&& (intent.getIntExtra(EXTRA_VOLUME_STREAM_TYPE, -1) == AudioManager.STREAM_MUSIC)){
            } else if (intent.getAction().equals(VOLUME_CHANGED_ACTION)) {
                // //媒体音量改变才通知   如果不加STREAM_MUSIC  会有三次回调
                System.out.println("音量变化了");
            }
        }

    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        super.onKeyDown(keyCode, event);
//        if(keyCode == KeyEvent.KEYCODE_POWER){
//            event.startTracking();
//        }
//
//    }

//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        switch (keyCode) {
//            case KeyEvent.KEYCODE_VOLUME_UP:
//                System.out.println("音量增加");
//                return super.onKeyDown(keyCode, event);
//            case KeyEvent.KEYCODE_VOLUME_DOWN:
//                System.out.println("音量减小");
//                return super.onKeyDown(keyCode, event);
//
//            case KeyEvent.KEYCODE_POWER:
//                //电源
//                System.out.println("onKeyDown 电源");
//                break;
//            default:
//                break;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_POWER) {
            System.out.println("onKeyLongPress 电源 长按");
        }
        return super.onKeyLongPress(keyCode, event);
    }


    public void click132(View view) {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
        queryEvents();
//            }
//        }, 4000);


    }

//    Function<Integer, String> fun = (integer) -> String.valueOf(integer + "222");
//
//    Function<String, Integer> fun1 = new Function<String, Integer>() {
//        @Override
//        public Integer apply(String string) {
//            return Integer.parseInt(string);
//        }
//    };

    public void click133(View view) {

//        Function<String, String> compose = fun.compose(fun1);

    }

    public void queryEvents() {
        UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(USAGE_STATS_SERVICE);
        long time = System.currentTimeMillis();
//获取最近一小时的应用最近使用统计信息
        UsageEvents usageEvents = usageStatsManager.queryEvents(time - 1000 * 60 * 60, time);
        if (usageEvents == null || !usageEvents.hasNextEvent()) {
            try {
                startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            while (usageEvents.hasNextEvent()) {
                UsageEvents.Event event = new UsageEvents.Event();
                if (usageEvents.getNextEvent(event)) {
                    if (event.getEventType() == UsageEvents.Event.MOVE_TO_FOREGROUND) {
                        Log.e("UsageEvents", event.getPackageName() + "应用在前台" + ": 发生时间: " + new Date(event.getTimeStamp()).toString());
                    } else {
                        Log.e("UsageEvents", event.getPackageName() + "应用在后台" + ": 发生时间: " + new Date(event.getTimeStamp()).toString());
                    }
//                    event.getPackageName();//获取包名
//                    event.getClassName();//获取类名
//                    event.getTimeStamp();//获取发生此事件的时间
//                    event.getConfiguration();//获取可以的所有设备配置信息影响应用程序检索的资源
                }
            }
        }
    }


}
