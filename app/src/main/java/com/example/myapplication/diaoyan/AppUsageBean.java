//package com.example.myapplication.diaoyan;
//
//import android.support.annotation.Keep;
//import android.support.annotation.NonNull;
//
//import com.zhizhangyi.platform.log.ZLog;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
///**
// * @Author: zff
// * @Time 4/16/19 5:36 PM  .
// * @Email: zhoufeifei@zhizhangyi.com
// * @Describe:
// */
//@Keep
//public class AppUsageBean {
//    private static final String TAG = "AppsUseMark";
//    public final String pkgName;
//    public final long from;
//    public final long to;
//    public final String ver;
//    public final String appName;
//    public final boolean isSystemApp;
//
//    AppUsageBean(String pkgName, long from, long to, String ver, String appName, boolean isSystemApp) {
//        this.pkgName = pkgName;
//        this.from = from;
//        this.to = to;
//        this.ver = ver;
//        this.appName = appName;
//        this.isSystemApp = isSystemApp;
//    }
//
//    AppUsageBean(String pkgName, String ver, String appName, boolean isSystemApp) {
//        this.pkgName = pkgName;
//        this.ver = ver;
//        this.appName = appName;
//        this.isSystemApp = isSystemApp;
//        from = 0;
//        to = 0;
//    }
//
//    @NonNull
//    @Override
//    public String toString() {
//        JSONObject json = new JSONObject();
//        try {
//            json.put("type", "app");
//            json.put("pkg", pkgName);
//            json.put("ver", ver);
//            json.put("from", from / 1000);
//            json.put("to", to / 1000);
//            //test
////            json.put("name", appName);
////            json.put("apartTime", (to - from) / 1000);
//            return json.toString();
//        } catch (JSONException e) {
//            ZLog.e(TAG, e.toString());
//            return "";
//        }
//    }
//}
