//package com.example.myapplication.diaoyan;
//
//import android.app.usage.UsageEvents;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.ApplicationInfo;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.content.pm.ResolveInfo;
//import android.support.v4.util.ArraySet;
//import android.text.TextUtils;
//import android.util.ArrayMap;
//
////import com.zhizhangyi.edu.mate.env.AppEnv;
////import com.zhizhangyi.edu.mate.receiver.ReceiverManager;
////import com.zhizhangyi.edu.mate.receiver.ReceiverSubType;
////import com.zhizhangyi.edu.mate.receiver.ReceiverType;
////import com.zhizhangyi.platform.log.ZLog;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Author: zff
// * @Time 4/15/19 4:45 PM  .
// * @Email: zhoufeifei@zhizhangyi.com
// * @Describe: 应用使用痕迹
// */
//public class AppsUseMark {
//    private static final String TAG = "AppsUseMark";
//    private static final long ignoreTime = TimeUnit.SECONDS.toMillis(4);
//    private static final long systemIgnore = TimeUnit.MINUTES.toMillis(1);
//    private static final long mergeTime = TimeUnit.MINUTES.toMillis(1);
//
//    private static String testTag = "zff_test";
//    private static volatile Map<String, AppUsageBean> mStatisticsApps;
//    private static ReceiverManager.IReceiver mReceiver;
//
//    /**
//     * 获取应用切换事件   todo   入口---------------
//     *
//     * @return
//     */
//    public static List<AppUsageBean> queryAppEvents(Rule rule, long start, long end) {
//        List<AppUsageBean> list = new ArrayList<>();
//        UsageEvents events = EduUsageStatsManager.queryEvent(start, end);
//        if (events == null) {
//            ZLog.d(TAG, "usage events is null");
//            return list;
//        }
//        list.addAll(analysisAppEvents(events, rule));
//        return list;
//    }
//
//
//    private static List<AppUsageBean> analysisAppEvents(UsageEvents events, Rule rule) {
//        List<AppUsageBean> list = new ArrayList<>();
//        if (rule == null) {
//            rule = defaultRule();
//        }
//        if (mStatisticsApps == null || mStatisticsApps.isEmpty()) {
//            register();
//            mStatisticsApps = createAuditApps(rule.auditApps());
//        }
//        UsageEvents.Event event = new UsageEvents.Event();
//        long from = -1;
//        String inPkgName = null;
//        AppUsageBean lastUsage = null;
//
//        while (events.hasNextEvent()) {
//            events.getNextEvent(event);
//            int type = event.getEventType();
//            String nowPkgName = event.getPackageName();
//            //package is null or not contains launcher
//            if (!mStatisticsApps.containsKey(nowPkgName)) {
////                ZLog.d(TAG, "nowPkgName :" + nowPkgName);
//                continue;
//            }
//            //离开事件
//            if (type == UsageEvents.Event.MOVE_TO_BACKGROUND) {
//                // package not equals
//                if (!TextUtils.equals(inPkgName, nowPkgName)) {
//                    ZLog.e(TAG, "pkgName: " + inPkgName + ", tempPagName: " + nowPkgName + " from is" + from);
//                    inPkgName = null;
//                    continue;
//                }
//                AppUsageBean temp = mStatisticsApps.get(nowPkgName);
//                if (temp == null) {
//                    ZLog.e(TAG, "pkgName: " + inPkgName + ", tempPagName: " + nowPkgName + " from is" + from);
//                    continue;
//                }
//                long to = event.getTimeStamp();
//                ZLog.d(TAG, "type: " + type + "," + nowPkgName + " " + to);
//                AppUsageBean nowUsage = new AppUsageBean(nowPkgName, from, to, temp.ver, temp.appName, temp.isSystemApp);
//                //ignore rule
//                boolean ignore = rule.ignoreRule(nowUsage);
//                if (ignore) {
//                    continue;
//                }
//                if (lastUsage == null) {
//                    lastUsage = new AppUsageBean(inPkgName, from, to, temp.ver, temp.appName, temp.isSystemApp);
//                    continue;
//                }
//                boolean merge = rule.mergeRule(nowUsage, lastUsage);
//                if (merge) {
//                    lastUsage = new AppUsageBean(inPkgName, lastUsage.from, to, temp.ver, temp.appName, temp.isSystemApp);
//                } else {
//                    list.add(lastUsage);
//                    lastUsage = new AppUsageBean(inPkgName, from, to, temp.ver, temp.appName, temp.isSystemApp);
//                }
//                //如果应用在前台,系统息屏幕会出数据错乱,出现多次后台事件,如果无进入事件忽略此次记录
////                2019-06-03 10:58:34.838 18229-18375/com.kided.cn E/zff_: 1 com.tencent.mm  2019-05-30 09:41:38:704
////                2019-06-03 10:58:34.839 18229-18375/com.kided.cn E/zff_: 2 com.tencent.mm  2019-05-30 09:41:39:911
////                2019-06-03 10:58:34.839 18229-18375/com.kided.cn E/zff_: 1 com.tencent.mm  2019-05-30 09:41:39:956
////                2019-06-03 10:58:34.839 18229-18375/com.kided.cn E/zff_: 2 com.tencent.mm  2019-05-30 09:42:05:176
////                2019-06-03 10:58:34.840 18229-18375/com.kided.cn E/zff_: 12 com.tencent.mm  2019-05-30 10:25:52:837
////                2019-06-03 10:58:34.840 18229-18375/com.kided.cn E/zff_: 12 com.tencent.mm  2019-05-30 10:25:52:865
////                2019-06-03 10:58:34.841 18229-18375/com.kided.cn E/zff_: 12 com.tencent.mm  2019-05-30 10:30:37:540
////                2019-06-03 10:58:34.841 18229-18375/com.kided.cn E/zff_: 12 com.tencent.mm  2019-05-30 11:08:05:546
////                2019-06-03 10:58:34.841 18229-18375/com.kided.cn E/zff_: 2 com.tencent.mm  2019-05-30 11:29:21:342
//                inPkgName = null;
//            }
//            //进入事件
//            if (type == UsageEvents.Event.MOVE_TO_FOREGROUND) {
//                inPkgName = nowPkgName;
//                from = event.getTimeStamp();
//                ZLog.d(TAG, "type: " + type + "," + nowPkgName + " " + from);
//            }
//        }
//        if (lastUsage != null) {
//            list.add(lastUsage);
//        }
//        return list;
//    }
//
//    private static void register() {
//        if (mReceiver == null) {
//            mReceiver = new ReceiverManager.IReceiver() {
//                @Override
//                public void onReceive(Context c, Intent i, ReceiverType type, ReceiverSubType sType) {
//                    switch (sType) {
//                        case ECoreReceiverSub_Package_Added:
//                        case ECoreReceiverSub_Package_Removed:
//                        case ECoreReceiverSub_Package_Fully_Removed:
//                        case ECoreReceiverSub_Package_Changed:
//                        case ECoreReceiverSub_Package_Replaced:
//                            mStatisticsApps.clear();
//                            break;
//                    }
//                }
//            };
//            ReceiverManager.register(ReceiverType.ECoreReceiver_Package, mReceiver);
//        }
//    }
//
//    private static Rule defaultRule;
//
//    private static Rule defaultRule() {
//        if (defaultRule == null) {
//            defaultRule = new Rule() {
//                @Override
//                public boolean mergeRule(AppUsageBean nowUsage, AppUsageBean lastUsage) {
//                    return defaultMergeRule(nowUsage, lastUsage);
//                }
//
//                @Override
//                public boolean ignoreRule(AppUsageBean nowUsage) {
//                    return defaultIgnoreRule(nowUsage);
//                }
//
//                @Override
//                public Set<String> auditApps() {
//                    return defaultAuditApps();
//                }
//            };
//        }
//        return defaultRule;
//    }
//
//    /**
//     * @param newUsage 当前应进入离开信息
//     * @return
//     */
//    public static boolean defaultIgnoreRule(AppUsageBean newUsage) {
//        long diffTime = newUsage.to - newUsage.from;
//        return (newUsage.isSystemApp && diffTime < systemIgnore) || (!newUsage.isSystemApp && diffTime < ignoreTime);
//    }
//
//    /**
//     * @param newUsage  当前应进入离开信息
//     * @param lastUsage 前一条应用进入离开信息
//     * @return
//     */
//    public static boolean defaultMergeRule(AppUsageBean newUsage, AppUsageBean lastUsage) {
//        //merge条件: 1.中间无切换应用(过滤后的应用) 2.距离上次切换时间小于1分钟
//        return (TextUtils.equals(lastUsage.pkgName, newUsage.pkgName) && newUsage.from - lastUsage.to < mergeTime);
//    }
//
//
//    /**
//     * 默认统计的应用
//     *
//     * @return
//     */
//    private static Set<String> defaultAuditApps() {
//        Set<String> set = new ArraySet<>();
//        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
//        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
//        PackageManager pm = AppEnv.getContext().getPackageManager();
//
//        List<ResolveInfo> appList = pm.queryIntentActivities(mainIntent, 0);
//        for (ResolveInfo resolveInfo : appList) {
//            String pkgName = resolveInfo.activityInfo.packageName;
//            if (TextUtils.isEmpty(pkgName) || TextUtils.equals(pkgName, AppEnv.getPackageName())) {
//                continue;
//            }
//            set.add(resolveInfo.activityInfo.packageName);
//        }
//        return set;
//    }
//
//
//    private static Map<String, AppUsageBean> createAuditApps(Set<String> auditApps) {
//        Map<String, AppUsageBean> map = new ArrayMap<>();
//        if (auditApps == null || auditApps.isEmpty()) {
//            return map;
//        }
//        PackageManager pm = AppEnv.getContext().getPackageManager();
//        for (String pkgName : auditApps) {
//            try {
//                PackageInfo packageInfo = pm.getPackageInfo(pkgName, 0);
//                boolean isSystemApp = false;
//                if (0 < (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)) {
//                    isSystemApp = true;
//                }
//                String versionName = packageInfo.versionName;
//                String appName = packageInfo.applicationInfo.loadLabel(pm).toString();
//                AppUsageBean bean = new AppUsageBean(pkgName, versionName, appName, isSystemApp);
//                map.put(pkgName, bean);
//            } catch (Throwable t) {
//                ZLog.e(TAG, t.toString());
//            }
//
//        }
//        return map;
//    }
//
//    public interface Rule {
//
//        /**
//         * 当前和上一条是否合并
//         */
//        boolean mergeRule(AppUsageBean nowUsage, AppUsageBean lastUsage);
//
//        /**
//         * 是否忽略当前
//         */
//        boolean ignoreRule(AppUsageBean nowUsage);
//
//        /**
//         * 需要审计的应用
//         */
//        Set<String> auditApps();
//
//    }
//}
