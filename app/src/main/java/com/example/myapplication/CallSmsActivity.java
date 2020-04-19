package com.example.myapplication;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.BaseColumns;
import android.provider.CallLog;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.activity.FingerActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Administrator
 * 2019/5/30.
 * CallSmsActivity
 */
public class CallSmsActivity extends AppCompatActivity {

    private static final int DATABASE_VERSION = 15;

    private static final String TABLE_NAME = "data";
    private static final String COLUMN_KEY_ID = "_id";
    private static final String COLUMN_KEY_TYPE = "type";
    private static final String COLUMN_KEY_HASH = "hash";
    private static final String COLUMN_KEY_COUNT = "cnt";


    TextView textView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_sms);
        textView2 = findViewById(R.id.textView2);
//        getContentResolver.re
    }

    public void click1(View view) throws JSONException {
        ContentResolver resolver = getContentResolver();
        String[] projection = {
                CallLog.Calls.PHONE_ACCOUNT_ID,
//                "simid",
        };
        String sortOrder = BaseColumns._ID + " desc limit 10 ";
        Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, null, null, null, sortOrder);

        int count = cursor.getCount();
        int columnCount = cursor.getColumnCount();
        System.out.println("isBeforeFirst"+cursor.isBeforeFirst());
        System.out.println("isfirst"+cursor.isFirst());
        System.out.println("isLast"+cursor.isLast());
        System.out.println("isAfterLast"+cursor.isAfterLast());



        while (cursor.moveToNext()) {
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < columnCount; i++) {
                jsonObject.put(cursor.getColumnName(i), cursor.getString(i));
//                if (cursor.getColumnName(i).contains("hang_up")) {
//                    Log.e("1111111111111",cursor.getString(i));
//                }
//                stringBuilder.append(cursor.getColumnName(i)+":"+cursor.getString(i)+" ");

            }
            System.out.println(jsonObject);
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

    public void click21(View view) {
        getContentResolver().registerContentObserver(CallLog.Calls.CONTENT_URI, true, new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                ContentResolver resolver = getContentResolver();
                String[] projection = {
                        CallLog.Calls.PHONE_ACCOUNT_ID,
//                "simid",
                };
        String sortOrder = BaseColumns._ID + " desc limit 5";
                Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, null, null, null, sortOrder);

                int count = cursor.getCount();
                int columnCount = cursor.getColumnCount();
                StringBuilder stringBuilder = new StringBuilder();
                while (cursor.moveToNext()) {
                    JSONObject jsonObject = new JSONObject();
                    for (int i = 0; i < columnCount; i++) {
                        try {
                            jsonObject.put(cursor.getColumnName(i), cursor.getString(i));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                stringBuilder.append(cursor.getColumnName(i)+":"+cursor.getString(i)+" ");

                    }
                    try {
                        System.out.println(jsonObject);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    /**
     * 插入通话记录
     *
     * @param view
     */
    public void click7(View view) {
        final ContentResolver resolver = getContentResolver();


        new Thread() {
            @Override
            public void run() {
                super.run();
                int i = 0;
                while (i++ < 10) {
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("number","156456789");
                    ContentValues values = new ContentValues();
                    values.put(CallLog.Calls.CACHED_NAME, "name");
                    values.put(CallLog.Calls.NUMBER, "1234556");
                    values.put(CallLog.Calls.DATE, System.currentTimeMillis());
                    values.put(CallLog.Calls.DURATION, "123");
                    values.put(CallLog.Calls.TYPE, 2);
                    values.put(CallLog.Calls.NEW, true);
                    Uri insert = resolver.insert(CallLog.Calls.CONTENT_URI, values);
                    System.out.println(insert);

                }
            }
        }.start();


    }

    public void click2(View view) throws JSONException {
        ContentResolver resolver = getContentResolver();
        String sortOrder = BaseColumns._ID + " desc ";
        Cursor cursor = resolver.query(Uri.parse("content://telephony/siminfo"), null, null, null, sortOrder);

        int count = cursor.getCount();
        int columnCount = cursor.getColumnCount();
        while (cursor.moveToNext()) {
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < columnCount; i++) {
                jsonObject.put(cursor.getColumnName(i), cursor.getString(i));


            }
            System.out.println(jsonObject);

        }
    }

    public void click3(View v) {

//        for (int i = 1; i < 10; i++) {
//            String imsiFromSubId = getImsiFromSubId(i);
//            System.out.println(imsiFromSubId+"------");
//        }

        // TODO: 19-6-27   mms-sms     Uri.parse("content://sim/icc")
        getContentResolver().registerContentObserver(Telephony.MmsSms.CONTENT_URI, true, new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange, Uri uri) {
                System.out.println("-=-=-=-=-=-===-uri" + uri + selfChange);
                super.onChange(selfChange, uri);
                ContentResolver resolver = getContentResolver();
                String sortOrder = BaseColumns._ID + " desc limit 5";
                String selection = "(" + Telephony.Mms.MESSAGE_BOX + " =? or " + Telephony.Mms.MESSAGE_BOX + " =? )" + " and " + Telephony.Mms.MESSAGE_TYPE + " !=? " + " and " + Telephony.Mms.MESSAGE_TYPE + " !=? ";
                String[] selectionArgs = {
                        String.valueOf(Telephony.Mms.MESSAGE_BOX_INBOX),
                        String.valueOf(Telephony.Mms.MESSAGE_BOX_SENT),
                        String.valueOf(130),//接收方接收的试探彩信剔除
                        String.valueOf(134) //发送方收的的回执剔除
                };
                String[] projection = new String[]{Telephony.Mms._ID, Telephony.Mms.MESSAGE_BOX, Telephony.Mms.DATE, Telephony.Mms.SUBJECT, Telephony.Mms.MESSAGE_TYPE};
                Cursor cursor = resolver.query(Telephony.Mms.CONTENT_URI, projection, selection, selectionArgs, sortOrder);

                int count = cursor.getCount();
                int columnCount = cursor.getColumnCount();

                while (cursor.moveToNext()) {
//                    if(isInvalidMMs(cursor)){
//                        System.out.println("-=-=-=-=-=-===-");
////                continue;
//                    }
                    JSONObject jsonObject = new JSONObject();
                    for (int i = 0; i < columnCount; i++) {
                        try {
                            try {
                                jsonObject.put(cursor.getColumnName(i), cursor.getString(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } catch (SQLException exception) {
                            System.out.println("exception " + exception);
                        }

                    }
                    System.out.println(jsonObject);
                    int _id = cursor.getInt(cursor.getColumnIndex(Telephony.Mms._ID));
                    try {
                        queryAddrDatum(_id + "");
                        queryPart(_id + "");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

//                Cursor cursor2 = resolver.query(Uri.withAppendedPath(Telephony.Mms.CONTENT_URI, "part"), null, null, null, sortOrder);
//
//                int columnCount2 = cursor2.getColumnCount();
//
//                while (cursor2.moveToNext()) {
//                    JSONObject jsonObject = new JSONObject();
//                    for (int i = 0; i < columnCount2; i++) {
//                        try {
//                            jsonObject.put(cursor2.getColumnName(i), cursor2.getString(i));
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                    System.out.println(jsonObject);
//                }
            }
        });

    }


    private  String[] queryRow(String[] projectionOppo) {
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(
                    CallLog.Calls.CONTENT_URI, projectionOppo, null, null, "_id desc limit 0");
            return projectionOppo;
        } catch (Throwable t) {
            System.out.println("no  oppo address");
        } finally {
            IOUtils.closeQuietly(cursor);
        }
        return null;
    }
    public void click4(View v) throws JSONException {
        String[] projectionOppo = new String[]{
                CallLog.Calls.DATE,
                CallLog.Calls.NUMBER,
                CallLog.Calls.TYPE,
                CallLog.Calls.CACHED_NAME,
                CallLog.Calls.DURATION,
                "extention_lable",
                "record_path",
                "is_active"
        };
//      queryRow(projectionOppo);

        ContentResolver resolver = getContentResolver();

        String selection = Telephony.Sms.TYPE + " =? or " + Telephony.Sms.TYPE + " =?";
        String[] selectionArgs = {String.valueOf(Telephony.Sms.MESSAGE_TYPE_INBOX), String.valueOf(Telephony.Sms.MESSAGE_TYPE_SENT)};
        String sortOrder = BaseColumns._ID + " desc limit 10";
        String[] projection = new String[]{Telephony.Sms._ID, Telephony.Sms.TYPE, Telephony.Sms.ADDRESS, Telephony.Sms.DATE, Telephony.Sms.BODY};
        Cursor cursor = resolver.query(Telephony.Sms.CONTENT_URI, null, null, null, sortOrder);

        int count = cursor.getCount();
        int columnCount = cursor.getColumnCount();

        while (cursor.moveToNext()) {
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < columnCount; i++) {
                jsonObject.put(cursor.getColumnName(i), cursor.getString(i));

            }
            System.out.println(jsonObject);

            //测试代码,用完删除
            System.out.println("联系人名称"+ cursor.getString(cursor.getColumnIndex(Telephony.Sms.PERSON)));
        }
    }
    public void click23(View v) throws JSONException {
//        getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, new ContentObserver(new Handler()) {
        getContentResolver().registerContentObserver(Telephony.Sms.CONTENT_URI, true, new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {

                super.onChange(selfChange);
                String sortOrder = BaseColumns._ID + " desc limit 3";
                String[] projection = new String[]{Telephony.Sms._ID, Telephony.Sms.TYPE, Telephony.Sms.ADDRESS, Telephony.Sms.DATE, Telephony.Sms.BODY};
                String selection = Telephony.Sms.TYPE + " =? or " + Telephony.Sms.TYPE + " =?";
                String[] selectionArgs = {String.valueOf(Telephony.Sms.MESSAGE_TYPE_INBOX), String.valueOf(Telephony.Sms.MESSAGE_TYPE_SENT)};
                Cursor cursor = getContentResolver().query(Telephony.Sms.CONTENT_URI, null, null, null, sortOrder);

                int count = cursor.getCount();
                int columnCount = cursor.getColumnCount();

                while (cursor.moveToNext()) {
                    JSONObject jsonObject = new JSONObject();
                    for (int i = 0; i < columnCount; i++) {
                        try {
                            jsonObject.put(cursor.getColumnName(i), cursor.getString(i));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    System.out.println(jsonObject);
                }
//                try {
//                    click24(new View(CallSmsActivity.this));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }


            }
        });

    }

    public void click24(View v) throws JSONException {

        ContentResolver resolver = getContentResolver();

        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/"), new String[]{"* from threads order by _id desc limit 3--"}, null, null, null);
        int count = cursor.getCount();
        int columnCount = cursor.getColumnCount();

        while (cursor.moveToNext()) {
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < columnCount; i++) {
                jsonObject.put(cursor.getColumnName(i), cursor.getString(i));

            }
            System.out.println(jsonObject);
        }
    }

    //插入短信
    public void click19(View view) {
        final ContentResolver resolver = getContentResolver();


        new Thread() {
            @Override
            public void run() {
                super.run();
                int i = 0;
                while (i++ < 80) {
//            ContentValues contentValues = new ContentValues();
//            contentValues.put("number","156456789");
                    ContentValues values = new ContentValues();
                    values.put(Telephony.Sms.ADDRESS, "1234556");
                    values.put(Telephony.Sms.BODY, "短信内容");
                    values.put(Telephony.Sms.DATE, System.currentTimeMillis());
                    values.put(Telephony.Sms.TYPE, Telephony.Sms.MESSAGE_TYPE_SENT);
                    Uri insert = resolver.insert(Telephony.Sms.CONTENT_URI, values);
                    System.out.println(insert);

                }
            }
        }.start();
    }
    public void click5(View v) throws JSONException {
        ContentResolver resolver = getContentResolver();
        String sortOrder = BaseColumns._ID + " desc ";
        String pro=" _id not in (0,1)" ;
        String selection = "(" + Telephony.Mms.MESSAGE_BOX + " =? or " + Telephony.Mms.MESSAGE_BOX + " =? )" + " and " + Telephony.Mms.MESSAGE_TYPE + " !=? " + " and " + Telephony.Mms.MESSAGE_TYPE + " !=? ";
        String[] selectionArgs = {
                String.valueOf(Telephony.Mms.MESSAGE_BOX_INBOX),
                String.valueOf(Telephony.Mms.MESSAGE_BOX_SENT),
                String.valueOf(130),//接收方接收的试探彩信剔除
                String.valueOf(134) //发送方收的的回执剔除
        };
        Cursor cursor = resolver.query(Telephony.Mms.CONTENT_URI, null, selection, selectionArgs, sortOrder);

        int count = cursor.getCount();
        int columnCount = cursor.getColumnCount();

        while (cursor.moveToNext()) {
            if (isInvalidMMs(cursor)) {
                System.out.println("-=-=-=-=-=-===-");
//                continue;
            }
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < columnCount; i++) {
                try {
                    jsonObject.put(cursor.getColumnName(i), cursor.getString(i));
                } catch (SQLException exception) {
                    System.out.println("exception " + exception);
                }

            }
            long time = cursor.getLong(cursor.getColumnIndex(Telephony.Mms.DATE)) * 1000;
            System.out.println("-------------time: " + time);//"date":"1565157377"
            System.out.println(jsonObject);
            int _id = cursor.getInt(cursor.getColumnIndex(Telephony.Mms._ID));

//            queryAddrDatum(_id + "");
//            queryPart(_id + "");
            String theme = cursor.getString(cursor.getColumnIndex(Telephony.Mms.SUBJECT));
            System.out.println("theme: " + theme);
        }
        System.out.println(System.currentTimeMillis());
    }

    /**
     * 在小米 手机（两条彩信数据）一些重要的字段都获取不到  可以认为这条彩信数据可以抛弃
     *
     * @return
     */
    public boolean isInvalidMMs(Cursor cursor) {
        try {
            int threadId = cursor.getInt(cursor.getColumnIndex(Telephony.Mms.THREAD_ID));
            String contentType = cursor.getString(cursor.getColumnIndex(Telephony.Mms.CONTENT_TYPE));
            int messageSize = cursor.getInt(cursor.getColumnIndex(Telephony.Mms.MESSAGE_SIZE));
            String transactionId = cursor.getString(cursor.getColumnIndex(Telephony.Mms.TRANSACTION_ID));
            if (threadId == 0 && TextUtils.isEmpty(contentType) && messageSize == 0 && TextUtils.isEmpty(transactionId)) {
                return true;
            }
        } catch (Throwable t) {
            return true;
        }
        return false;
    }

    public void click6(View v) throws JSONException {
        ContentResolver resolver = getContentResolver();
        String sortOrder = BaseColumns._ID + " desc ";
        Cursor cursor = resolver.query(Uri.withAppendedPath(Telephony.Mms.CONTENT_URI, "part"), null, null, null, sortOrder);

        int count = cursor.getCount();
        int columnCount = cursor.getColumnCount();

        while (cursor.moveToNext()) {
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < columnCount; i++) {
                jsonObject.put(cursor.getColumnName(i), cursor.getString(i));

            }
            System.out.println(jsonObject);
        }

    }

    /**
     * addr 表  获取联系人
     *
     * @param mid addr 表 msg_id   part 表中的 mid
     * @return 电话号码
     */
    private List<String> queryAddrDatum(String mid) throws JSONException {
        List<String> phoneList = new ArrayList<>();
        Cursor cursor = null;
        boolean isIgnoreNext = false;
        try {
            Uri uri = Uri.withAppendedPath(Telephony.Mms.CONTENT_URI, mid + "/addr/");
            String[] projection = new String[]{Telephony.Mms.Addr.ADDRESS, Telephony.Mms.Addr.TYPE};
            String selection = Telephony.Mms.Addr.MSG_ID + " =?  and " + Telephony.Mms.Addr.ADDRESS + " !=? ";
//            String[] selectionArgs = {mid, IGNORE_TYPE1};
            ContentResolver resolver = getContentResolver();
            cursor = resolver.query(uri, null, null, null, null);


            int count = cursor.getCount();
            int columnCount = cursor.getColumnCount();
            while (cursor.moveToNext()) {
                JSONObject jsonObject = new JSONObject();
                for (int i = 0; i < columnCount; i++) {
                    jsonObject.put(cursor.getColumnName(i), cursor.getString(i));

                }
                System.out.println("addr:  " + jsonObject);
            }


//            while (cursor != null && cursor.moveToNext()) {
//                String phoneNumber = cursor.getString(cursor.getColumnIndex(Telephony.Mms.Addr.ADDRESS));
//                if (!TextUtils.isEmpty(phoneNumber)) {
//                    String type = cursor.getString(cursor.getColumnIndex(Telephony.Mms.Addr.TYPE));
//                    if (IGNORE_TYPE2.equals(type)) {
//                        isIgnoreNext = true;
//                        phoneList.add(phoneNumber);
//                    } else {
//                        if (isIgnoreNext) {
//                            //忽略此次记录
//                            isIgnoreNext = false;
//                        } else {
//                            phoneList.add(phoneNumber);
//                        }
//                    }
//                }
//            }
        } finally {
//            IOUtils.closeQuietly(cursor);
        }
        return phoneList;
    }

    private static final Class<?>[] sClsTypeInt = new Class[]{Integer.TYPE};
    private static final Class<?>[] sClsTypeLong = new Class[]{Long.TYPE};

    public static int getSubId(int i) {
        try {
            Class<?> cls = Class.forName("android.telephony.SubscriptionManager"); // >= 5.1
            return ((int[]) cls.getMethod("getSubId", sClsTypeInt).invoke(cls, new Object[]{Integer.valueOf(i)}))[0];
        } catch (Throwable ignored) {
        }
        try {
            Class<?> cls = Class.forName("android.telephony.SubscriptionManager"); // 5.0
            return ((int[]) cls.getMethod("getSubId", sClsTypeLong).invoke(cls, new Object[]{Long.valueOf(i)}))[0];
        } catch (Throwable t) {
            return -1;
        }
    }

    public String getImsiFromSubId(int subId) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            Method m = telephonyManager.getClass().getDeclaredMethod("getSubscriberId", int.class);
            if (m == null) {
                return null;
            }
            Object obj = m.invoke(telephonyManager, subId);
            if (obj == null) {
                return null;
            }
            String imsi = obj.toString();
            return imsi;

        } catch (Exception ignore) {
            return null;
        }
    }


    /**
     * @param mid only id
     * @return pair  first data , second theme
     */
    private Pair<String, String> queryPart(String mid) throws JSONException {
        Cursor cursor = null;
        String data = null;
        String theme = null;
        int id = 0;

        try {
            Uri uri = Uri.withAppendedPath(Telephony.Mms.CONTENT_URI, "part");
            String[] projection = new String[]{Telephony.Mms.Part._DATA, Telephony.Mms.Part.TEXT, Telephony.Mms.Part._ID};
            String selection = Telephony.Mms.Part.MSG_ID + " =?  and " + Telephony.Mms.Part.SEQ + " !=? ";
            String[] selectionArgs = {mid, "-1"};
            String sortOrder = BaseColumns._ID + " desc limit 6";
            ContentResolver resolver = getContentResolver();
            cursor = resolver.query(uri, projection, selection, selectionArgs, sortOrder);
            int columnCount = cursor.getColumnCount();
            assert cursor != null;
            while (cursor.moveToNext()) {

                if (TextUtils.isEmpty(data)) {
                    data = cursor.getString(cursor.getColumnIndex(Telephony.Mms.Part._DATA));
                }
                if (TextUtils.isEmpty(theme)) {
                    theme = cursor.getString(cursor.getColumnIndex(Telephony.Mms.Part.TEXT));
                }
                JSONObject jsonObject = new JSONObject();
                try {
                    for (int i = 0; i < columnCount; i++) {
                        jsonObject.put(cursor.getColumnName(i), cursor.getString(i));

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("part表" + jsonObject);

            }
        } finally {
            cursor.close();
        }

        return new Pair<>(data, theme);
    }

    /**
     * 获取imei  imsi
     *
     * @param view
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void click8(View view) {
        Set<String> imsis = SimCardSubscriber.getSubscribers(this);
        System.out.println("imsis=========" + imsis);
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        System.out.println("imei=========" + imei);
    }

    public void click15(View view) {

        delete();
    }

    public void click16(View view) {

                query();
    }

    public void click18(View view) {
        commit();
    }

    private void delete() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        try {
            db.beginTransaction();
            String[] whereArgs = {String.valueOf(1)};
//            db.delete(TABLE_NAME, " type = 2","limit 2");
//            db.execSQL("set rowcount 3 delete from data where type = 2");
            db.execSQL("delete from data where _id in (select _id from data where type = 2 Limit 2)");
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    private void query() {

            Cursor cursor = null;
            try {
                SQLiteDatabase db = mDbHelper.getReadableDatabase();
                String selection = COLUMN_KEY_HASH + " =? ";
                cursor = db.query(TABLE_NAME, null, null, null, null, null, "_id desc");
                int columnCount = cursor.getColumnCount();
                while (cursor.moveToNext()) {
                    JSONObject jsonObject = new JSONObject();
                    boolean last = cursor.isLast();
                    for (int i = 0; i < columnCount; i++) {
                        try {
                            jsonObject.put(cursor.getColumnName(i), cursor.getString(i));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                    System.out.println("sqllite:  " + jsonObject);
                }
            } finally {
                IOUtils.closeQuietly(cursor);
            }

    }

    /**
     * 提交这次操作,并保存到数据库中
     */
    public void commit() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        try {
            db.beginTransaction();
            ContentValues values = new ContentValues(2);
            values.put(COLUMN_KEY_TYPE, 2);
            values.put(COLUMN_KEY_HASH, 100);
            db.insert(TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    private final DatabaseHelper mDbHelper = new DatabaseHelper();

    public void click17(View view) {

        ContentResolver resolver = getContentResolver();
        String sortOrder = BaseColumns._ID + " desc limit 50";
        String pro=" _id not in (0,1)" ;
        String selection = "(" + Telephony.Mms.MESSAGE_BOX + " =? or " + Telephony.Mms.MESSAGE_BOX + " =? )" + " and " + Telephony.Mms.MESSAGE_TYPE + " !=? " + " and " + Telephony.Mms.MESSAGE_TYPE + " !=? ";
        String[] selectionArgs = {
                String.valueOf(Telephony.Mms.MESSAGE_BOX_INBOX),
                String.valueOf(Telephony.Mms.MESSAGE_BOX_SENT),
                String.valueOf(130),//接收方接收的试探彩信剔除
                String.valueOf(134) //发送方收的的回执剔除
        };
        Cursor cursor = resolver.query(Telephony.Mms.CONTENT_URI, null, selection, selectionArgs, sortOrder);

        int count = cursor.getCount();
        int columnCount = cursor.getColumnCount();

        while (cursor.moveToNext()) {
            if (isInvalidMMs(cursor)) {
                System.out.println("-=-=-=-=-=-===-");
//                continue;
            }
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < columnCount; i++) {
                try {
                    jsonObject.put(cursor.getColumnName(i), cursor.getString(i));
                } catch (Exception exception) {
                    System.out.println("exception " + exception);
                }

            }
            long time = cursor.getLong(cursor.getColumnIndex(Telephony.Mms.DATE)) * 1000;
            System.out.println("-------------time: " + time);
            System.out.println(jsonObject);
            int _id = cursor.getInt(cursor.getColumnIndex(Telephony.Mms._ID));

            try {
                queryAddrDatum(_id + "");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void click20(View view) {
//        //申请默认短信应用
        Intent intent = new Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
        intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME,getPackageName());
        startActivity(intent);

    }
    public void click22(View view) {
        EditText viewById = findViewById(R.id.et_count);
        String maxCount = viewById.getText().toString().trim();
        com.communication.SmsDb.insertSms(Integer.parseInt(maxCount),this);
    }

    public void click29(View view) throws JSONException {


        ContentResolver resolver = getContentResolver();

        String sortOrder = BaseColumns._ID + " desc ";
//        ContactsContract.Contacts.CONTENT_URI   ContactsContract.Contacts.CONTENT_LOOKUP_URI
//        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
//        Uri uri = Uri.parse("content://com.android.contacts/data");
        LinkedList<String> systemDBCachedId = new LinkedList();
        systemDBCachedId.add("20");
        systemDBCachedId.add("21");
        systemDBCachedId.add("23");

        String selection = BaseColumns._ID + " not in (" + TextUtils.join(",", systemDBCachedId) + ")";
        Uri uri = ContactsContract.Contacts.CONTENT_URI ;
        Cursor cursor = resolver.query(uri, null, null , null, sortOrder);

        int count = cursor.getCount();
        int columnCount = cursor.getColumnCount();

        while (cursor.moveToNext()) {
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < columnCount; i++) {
                try {
                    jsonObject.put(cursor.getColumnName(i), cursor.getString(i));
                } catch (Exception exception) {
                    System.out.println("exception " + exception);
                }
            }
            System.out.println(jsonObject);

        }

    }

    public void click34(View view) throws JSONException {


        getContentResolver().registerContentObserver( ContactsContract.Contacts.CONTENT_URI, true, new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                try {
                    click29(new View(CallSmsActivity.this));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
            }
        });




    }

    public void click30(View view) {

        ContentResolver resolver = getContentResolver();

        String sortOrder = BaseColumns._ID + " desc ";
//        ContactsContract.Contacts.CONTENT_URI   ContactsContract.Contacts.CONTENT_LOOKUP_URI
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
//        Uri uri = Uri.parse("content://com.android.contacts/data");
        Cursor cursor = resolver.query(uri, null, null, null, sortOrder);

        int count = cursor.getCount();
        int columnCount = cursor.getColumnCount();

        while (cursor.moveToNext()) {
            JSONObject jsonObject = new JSONObject();new JSONObject();
            for (int i = 0; i < columnCount; i++) {
                try {
                    jsonObject.put(cursor.getColumnName(i), cursor.getString(i));
                } catch (Exception exception) {
                    System.out.println("exception " + exception);
                }
            }
            System.out.println(jsonObject);

        }

    }
    //ContactsContract.Groups.CONTENT_URI 获取群组用
    public void click31(View view) {
        Socket j =null;


        ContentResolver resolver = getContentResolver();

        String sortOrder = BaseColumns._ID + " desc ";
//        ContactsContract.Contacts.CONTENT_URI   ContactsContract.Contacts.CONTENT_LOOKUP_URI
//        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri uri = Uri.parse("content://com.android.contacts/data");
        Cursor cursor = resolver.query(uri, null, null, null, sortOrder);

        int count = cursor.getCount();
        int columnCount = cursor.getColumnCount();

        while (cursor.moveToNext()) {
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < columnCount; i++) {
                try {
                    jsonObject.put(cursor.getColumnName(i), cursor.getString(i));
                } catch (Exception exception) {
                    System.out.println("exception " + exception);
                }
            }
            System.out.println(jsonObject);

        }
    }

    //ContactsContract.Groups.CONTENT_URI 获取群组用
    public void click32(View view) {

        getContentResolver().registerContentObserver(Uri.parse("content://com.android.contacts/data"), true, new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                click31(new View(CallSmsActivity.this));

            }

            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
            }
        });

    }
    //ContactsContract.Groups.CONTENT_URI 获取群组用
    public void click33(View view) {

//        getContentResolver().registerContentObserver(ContactsContract.Groups.CONTENT_URI, true, new ContentObserver(new Handler()) {
//            @Override
//            public void onChange(boolean selfChange) {
//                super.onChange(selfChange);
                ContentResolver resolver = getContentResolver();

                String sortOrder = BaseColumns._ID + " desc ";
//        ContactsContract.Contacts.CONTENT_URI   ContactsContract.Contacts.CONTENT_LOOKUP_URI
//        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
                Uri uri = ContactsContract.Groups.CONTENT_URI;
                Cursor cursor = resolver.query(uri, null, null, null, sortOrder);

                int count = cursor.getCount();
                int columnCount = cursor.getColumnCount();

                while (cursor.moveToNext()) {
                    JSONObject jsonObject = new JSONObject();
                    for (int i = 0; i < columnCount; i++) {
                        try {
                            jsonObject.put(cursor.getColumnName(i), cursor.getString(i));
                        } catch (Exception exception) {
                            System.out.println("exception " + exception);
                        }
                    }
                    System.out.println(jsonObject);

                }

//            }
//        });

    }

    public void click58(View view) throws JSONException {

        ContentResolver resolver = getContentResolver();
        String[] projection = {
                CallLog.Calls.PHONE_ACCOUNT_ID,
//                "simid",
        };
        String sortOrder = BaseColumns._ID + " desc limit 50 ";
        Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, sortOrder);

        int count = cursor.getCount();
        int columnCount = cursor.getColumnCount();



        while (cursor.moveToNext()) {
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < columnCount; i++) {
                jsonObject.put(cursor.getColumnName(i), cursor.getString(i));

            }
            System.out.println(jsonObject);

        }
    }

    public  int deleteContacts(List<Long> contacts) throws OperationApplicationException, RemoteException {
        ContentResolver contentResolver = getContentResolver();
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        int size = 0;
        //delete contact
        for (int i = 0; i < contacts.size(); i++) {
            long id = contacts.get(i);
            ops.add(ContentProviderOperation.newDelete(ContactsContract.RawContacts.CONTENT_URI)
                    .withSelection(ContactsContract.RawContacts._ID + "=" + id, null)
                    .build());
                    //从此results无法解析出_id.
                    ContentProviderResult[] results = contentResolver.applyBatch(ContactsContract.AUTHORITY, ops);
                    size += results.length;
            }
        return size;
    }

    public void click59(View view) throws OperationApplicationException, RemoteException {
        List<Long> contacts= new ArrayList<>();
        for (long i =1160;i<=1196;i++){
            contacts.add(i);
        }
        deleteContacts(contacts);
    }



    public void click134(View view) {
        ContentResolver resolver = getContentResolver();
        String[] projection = {
                //time
                MediaStore.Images.Media.DATE_ADDED,//精确到秒
                //路径
                MediaStore.Images.Media.DATA,
                //name
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.MIME_TYPE,
                //"source": 1, //来源（自带相机/微信或QQ/设备截屏）   1：系统相册   注：暂定全部是系统相册，等待客户端调研能否细分
                //source 问题比较复杂  MediaStore.Images.Media.BUCKET_DISPLAY_NAME  字段
                //
                //size 字节为单位
                MediaStore.Images.Media.SIZE
        };
        String sortOrder = BaseColumns._ID + " desc limit 10 ";
        Cursor cursor = resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, sortOrder);

        int count = cursor.getCount();
        int columnCount = cursor.getColumnCount();
        System.out.println("isBeforeFirst"+cursor.isBeforeFirst());
        System.out.println("isfirst"+cursor.isFirst());
        System.out.println("isLast"+cursor.isLast());
        System.out.println("isAfterLast"+cursor.isAfterLast());



        while (cursor.moveToNext()) {
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < columnCount; i++) {
                try {
                    jsonObject.put(cursor.getColumnName(i), cursor.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            System.out.println(jsonObject);
//            int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID);
//            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID));


            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            File file = new File(path);
//            System.out.println(id2);
//            String columnName = cursor.getColumnName(0);
            System.out.println("name"+file.getName());
        }
    }




    public void click136(View view) {


        getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);


            }

            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
                System.out.println("-------"+ uri);
                click134(null);
            }
        });
    }


    public void click135(View view) {
        ContentResolver resolver = getContentResolver();
        String[] projection = {



                //路径
                MediaStore.Video.Media.DATA,
                ////精确到秒  time
                MediaStore.Video.Media.DATE_ADDED,
                //name
                MediaStore.Video.Media.DISPLAY_NAME,

                //"source": 1, //来源（自带相机/微信或QQ/设备截屏）   1：系统相册   注：暂定全部是系统相册，等待客户端调研能否细分
                //source 问题比较复杂  MediaStore.Images.Media.BUCKET_DISPLAY_NAME  字段
                //size 字节为单位
                MediaStore.Video.Media.SIZE
        };
        String sortOrder = BaseColumns._ID + " desc limit 10 ";
        Cursor cursor = resolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);

        int count = cursor.getCount();
        int columnCount = cursor.getColumnCount();
        System.out.println("isBeforeFirst"+cursor.isBeforeFirst());
        System.out.println("isfirst"+cursor.isFirst());
        System.out.println("isLast"+cursor.isLast());
        System.out.println("isAfterLast"+cursor.isAfterLast());



        while (cursor.moveToNext()) {
            JSONObject jsonObject = new JSONObject();
            for (int i = 0; i < columnCount; i++) {
                try {
                    jsonObject.put(cursor.getColumnName(i), cursor.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            System.out.println(jsonObject);


        }
    }

    public void click137(View view) {

        getContentResolver().registerContentObserver(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true, new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);


            }

            @Override
            public void onChange(boolean selfChange, Uri uri) {
                super.onChange(selfChange, uri);
                System.out.println("-------"+ uri);
                click135(null);
            }
        });
    }

    private void getPictures(List<FingerActivity.VideoInfo> list) {
        String[] IMAGES = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media.MIME_TYPE,
                MediaStore.Images.Media.SIZE};
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGES, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String path = cursor.getString(0);
                String bucketName = cursor.getString(1);
                String mimeType = cursor.getString(2);
                long size = cursor.getLong(3);
                Log.i("-->file", path + "," + bucketName + "," + mimeType + "," + size);

                FingerActivity.VideoInfo videoInfo = new FingerActivity.VideoInfo();
                videoInfo.setDisplayName(bucketName);
                videoInfo.setPath(path);
                videoInfo.setLastModifyTime(new File(path).lastModified());
                videoInfo.setSize(size);
                list.add(videoInfo);
            }
            cursor.close();
        }

        Log.i("分隔", "\n\n\n\n\n\n \n\n\n\n\n");
    }

    private void getVideos(List<FingerActivity.VideoInfo> list) {
        String[] IMAGES = {
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Video.Media.MIME_TYPE,
                MediaStore.Video.Media.SIZE};
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, IMAGES, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String path = cursor.getString(0);
                String bucketName = cursor.getString(1);
                String mimeType = cursor.getString(2);
                long size = cursor.getLong(3);
                Log.i("-->file", path + "," + bucketName + "," + mimeType + "," + size);

                FingerActivity.VideoInfo videoInfo = new FingerActivity.VideoInfo();
                videoInfo.setDisplayName(bucketName);
                videoInfo.setPath(path);
                videoInfo.setLastModifyTime(new File(path).lastModified());
                videoInfo.setSize(size);
                list.add(videoInfo);
            }
            cursor.close();
        }
    }

    public void click138(View view) {
        String callName = getCallName("13810407417");
        System.out.println("姓名"+callName);
    }

    /**
     * 获取联系人
     *
     * @param phoneNumber
     * @return
     */
    String getCallName(String phoneNumber) {
        Cursor cursor = null;
        String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME};
        try {
            Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
            ContentResolver resolver = this.getContentResolver();
            cursor = resolver.query(uri, projection, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    return cursor.getString(0);
                }
            }
        } catch (Exception e) {
        } finally {
            IOUtils.closeQuietly(cursor);
        }
        return null;
    }


    private class DatabaseHelper extends SQLiteOpenHelper {
        private static final String sDbName = "test.db";

        DatabaseHelper() {
            super(CallSmsActivity.this, sDbName, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            onUpgrade(db, 0, DATABASE_VERSION);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            System.out.println("onUpgrade: " + oldVersion + "newVersion " + newVersion);
            switch (oldVersion) {
                case 0:
                    createDB(db);
                case 2:
                    db.delete(TABLE_NAME, null, null);
                case 3:
                    //communication  refactor hash update

                case 4:

//                    db.execSQL("DROP TABLE "+TABLE_NAME);

                default:
                    break;
            }

        }


        private void createDB(SQLiteDatabase db) {
            String sqlFormat = "CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY, %s INTEGER, %s INTEGER, %s INTEGER)";
            String createSql = String.format(sqlFormat,
                    TABLE_NAME,
                    COLUMN_KEY_ID,
                    COLUMN_KEY_TYPE,
                    COLUMN_KEY_HASH,
                    COLUMN_KEY_COUNT);
            db.execSQL(createSql);
        }
    }
}
