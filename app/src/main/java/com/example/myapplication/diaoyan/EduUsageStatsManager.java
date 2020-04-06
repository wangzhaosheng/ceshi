//package com.example.myapplication.diaoyan;
//
//import android.app.usage.UsageEvents;
//import android.app.usage.UsageEvents.Event;
//import android.app.usage.UsageStats;
//import android.app.usage.UsageStatsManager;
//import android.text.TextUtils;
//import android.util.Pair;
//
////import com.zhizhangyi.edu.mate.env.AppEnv;
////import com.zhizhangyi.edu.mate.utils.EduTimeUtils;
////import com.zhizhangyi.platform.log.ZLog;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Author: zff
// * @Time 4/17/19 10:19 AM  .
// * @Email: zhoufeifei@zhizhangyi.com
// * @Describe: 应用切换时间查询
// */
//public class EduUsageStatsManager {
//
//    private static final String TAG = "EduUsageStatsManager";
//    private static long[] queryInterval = new long[]{
//            TimeUnit.MILLISECONDS.convert(10L, TimeUnit.SECONDS),
//            TimeUnit.MILLISECONDS.convert(1L, TimeUnit.MINUTES),
//            TimeUnit.MILLISECONDS.convert(1L, TimeUnit.HOURS),
//            TimeUnit.MILLISECONDS.convert(1L, TimeUnit.DAYS),
//            TimeUnit.MILLISECONDS.convert(7L, TimeUnit.DAYS),
//            0};
//
//    private static final long dayTime = TimeUnit.DAYS.toMillis(1L);
//
//    /**
//     * 分时段查询,最长查询时段,0- System.currentTimeMillis()
//     *
//     * @return 最后一次进入记录
//     */
//    public static Event intervalQueryTopEvent() {
//        int index = 0;
//        do {
//            Pair<Long, Long> pair = getPeriod(index);
//            Event last = getLastInEvent(pair.first, pair.second);
//            if (last != null) {
//                return last;
//            }
//        } while (++index < queryInterval.length);
//        return null;
//    }
//
//
//    //获取查询时段
//    private static Pair<Long, Long> getPeriod(int index) {
//        long end = EduTimeUtils.nowTime();
//        long start = 0;
//        if (index < queryInterval.length - 1) {
//            start = end - queryInterval[index];
//        }
//        return new Pair<>(start, end);
//    }
//
//    /**
//     * @param start 查询开始时间
//     * @param end   查询结束时间
//     * @return 最后一次进入的记录 有可能为null
//     */
//    public static Event getLastInEvent(long start, long end) {
//        Event lastEvent = null;
//        UsageEvents events = queryEvent(start, end);
//        if (events != null) {
//            Event event = new Event();
//            while (events.getNextEvent(event)) {
//                if (event.getEventType() == Event.MOVE_TO_FOREGROUND) {
//                    lastEvent = event;
//                    //地址引用会发生变化
//                    event = new Event();
//                }
//            }
//        }
//        return lastEvent;
//    }
//
//    /**
//     * @param start 开始时间
//     * @param end   结束时间
//     * @return 应用切换记录, 有可能为null
//     */
//    public static UsageEvents queryEvent(long start, long end) {
//        try {
//            UsageStatsManager uSManager = (UsageStatsManager) AppEnv.getContext().getSystemService("usagestats");
//            return uSManager.queryEvents(start, end);
//        } catch (Throwable t) {
//            ZLog.e(TAG, t.toString());
//        }
//        return null;
//    }
//
//    /**
//     * 获取应用使用时长
//     *
//     * @param pkg
//     * @return
//     */
//    public static long queryAppUseTime(String pkg) {
//        long start = EduTimeUtils.getDayBegins();
//        long end = EduTimeUtils.getDayEnd();
//        long totalTimeInForeground = 0;
//        UsageStatsManager uSManager = (UsageStatsManager) AppEnv.getContext().getSystemService("usagestats");
//        List<UsageStats> queryUsageStats = uSManager.queryUsageStats(
//                UsageStatsManager.INTERVAL_DAILY, start, end);
//        for (UsageStats usageStats : queryUsageStats) {
//            String packageName = usageStats.getPackageName();
//            if (TextUtils.equals(pkg, packageName)) {
//                totalTimeInForeground = usageStats.getTotalTimeInForeground();
//            }
//        }
//        return totalTimeInForeground;
//    }
//
//}
