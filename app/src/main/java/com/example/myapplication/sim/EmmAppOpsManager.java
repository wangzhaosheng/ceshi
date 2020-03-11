package com.example.myapplication.sim;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v4.content.PermissionChecker;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static android.support.v4.content.PermissionChecker.PERMISSION_DENIED_APP_OP;
import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

/**
 * Created by ubuntu on 17-3-17.
 */

public class EmmAppOpsManager {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static boolean requestWindowPermission(Context context) {
        try {
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ) {
                AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
                Class<?> clazz = AppOpsManager.class;
                Field sysAlert = clazz.getField("OP_READ_SMS");
                int op = sysAlert.getInt(manager);
                Method method = clazz.getDeclaredMethod("checkOp", int.class, int.class, String.class);
                return AppOpsManager.MODE_ALLOWED == (int) method.invoke(manager, op, Binder.getCallingUid(), context.getPackageName());
//            } else {
//                return Settings.canDrawOverlays(context);
//            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static boolean requestWindowPermission2(Context context) {
        try {
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ) {
                AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
                Class<?> clazz = AppOpsManager.class;
                Field sysAlert = clazz.getField("OP_READ_SMS");
                int op = 10005;
                Method method = clazz.getDeclaredMethod("checkOp", int.class, int.class, String.class);
                return AppOpsManager.MODE_ALLOWED == (int) method.invoke(manager, op, Binder.getCallingUid(), context.getPackageName());
//            } else {
//                return Settings.canDrawOverlays(context);
//            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }



//    public int noteProxyOp(String op, String proxiedPackageName) {
//        return noteProxyOp(strOpToOp(op), proxiedPackageName);
//    }
    public static int mmsPermission(Context context) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        Class<?> clazz = AppOpsManager.class;
        Method method = clazz.getDeclaredMethod("opToSwitch", int.class);
        Method noteProxyOpNoThrow = clazz.getDeclaredMethod("noteProxyOpNoThrow", int.class,String.class);
//        Method noteProxyOp = clazz.getDeclaredMethod("noteProxyOp", String.class,String.class);
////        int invoke1 = (int) noteProxyOp.invoke(manager, Manifest.permission.READ_CALL_LOG, context.getPackageName());
//        int op =(int) method.invoke(manager, 10005);
//        Field sysAlert = clazz.getDeclaredField("sOpToString");
//        Field map = clazz.getDeclaredField("sPermToOp");
//        Field sOpPerms = clazz.getDeclaredField("sOpPerms");
////        Field RUNTIME_AND_APPOP_PERMISSIONS_OPS = clazz.getDeclaredField("RUNTIME_AND_APPOP_PERMISSIONS_OPS");
////        RUNTIME_AND_APPOP_PERMISSIONS_OPS.setAccessible(true);
//        map.setAccessible(true);
//        sysAlert.setAccessible(true);
//        sOpPerms.setAccessible(true);
////        String [] annotationsByType = sysAlert.getDeclaredAnnotationsByType(String.class);
//        String [] o = (String[]) sysAlert.get(manager);
//        String [] o2 = (String[]) sOpPerms.get(manager);
//        int [] o3= (int[]) RUNTIME_AND_APPOP_PERMISSIONS_OPS.get(manager);
//        HashMap<String, Integer> sPermToOp= (HashMap<String, Integer>) map.get(manager);

//        int invoke = (int) noteProxyOpNoThrow.invoke(manager, 10005, context.getPackageName());

//        int i = PermissionChecker.checkSelfPermission(context, o[10005]);
        return 0;
//        if (AppOpsManagerCompat.noteProxyOp(context, op+"", context.getPackageName())
//                != AppOpsManagerCompat.MODE_ALLOWED) {
//            return PERMISSION_DENIED_APP_OP;
//        }
//
//        return PERMISSION_GRANTED;
    }
}
