package com.example.myapplication;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.CallLog;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Administrator
 * 2019/4/29.
 * SecondActivity
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sec_activity);
        initWebView();
//        getCursor(SecondActivity.this);
//        File file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "MIUI/sound_recorder/call_rec");
//        boolean file1 = file.isDirectory();
//        String[] list = file.list();
//        File[] files = file.listFiles();
//        long theLastModifyFile =0;  // TODO: 2019/4/29 最后修改的录音
//        if(files.length<1){
//            theLastModifyFile=files[0].lastModified();
//        }
//        for (File file2 : files) {
//            long l = file2.lastModified();
//            if(l>theLastModifyFile){
//                theLastModifyFile=l;
//            }
//        }
//        System.out.println("===================音频文件名字"+ Arrays.toString(list));




//        this.getContentResolver().registerContentObserver(
//                CallLog.Calls.CONTENT_URI, true, new ContentObserver(new Handler()) {
//
//                    @Override
//                    public boolean deliverSelfNotifications() {
//                        return true;
//                    }
//
//                    @Override
//                    public void onChange(boolean selfChange) {
//                        super.onChange(selfChange);
//                        //当通话记录改变时，执行该方法
//                        System.out.println("===================通话记录改变");
//                        getCursor(SecondActivity.this);
//                        File file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "MIUI/sound_recorder/call_rec");
//                        boolean file1 = file.isDirectory();
//                        String[] list = file.list();
//                        File[] files = file.listFiles();
//                        long theLastModifyFile =0;  // TODO: 2019/4/29 最后修改的录音
//                        if(files.length<1){
//                            theLastModifyFile=files[0].lastModified();
//                        }
//                        for (File file2 : files) {
//                            long l = file2.lastModified();
//                            if(l>theLastModifyFile){
//                                theLastModifyFile=l;
//                            }
//                        }
//                        System.out.println("===================音频文件名字"+ Arrays.toString(list));
//
//                    }
//                });

    }

    private void initWebView() {

        WebView webView = findViewById(R.id.webview);
//        webView.setWebViewClient(new HostsWebClient());
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
//        webView.getSettings().setPluginsEnabled(true);
        webView.getSettings().setSupportMultipleWindows(false);
        webView.getSettings().setSupportZoom(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.loadUrl("https://www.baidu.com/");
    }


    public void getCursor(Context context) {
        ContentResolver cr = context.getContentResolver();
        Cursor allCursor = cr.query(CallLog.Calls.CONTENT_URI,
                new String[] { CallLog.Calls._ID, CallLog.Calls.NUMBER,
                        CallLog.Calls.DATE }, null, null,
                CallLog.Calls.DEFAULT_SORT_ORDER);
        Map<String, Integer> numberMap = new HashMap<String, Integer>();
        if (allCursor != null && allCursor.getCount() > 0) {
            while (allCursor.moveToNext()) {
                String number = allCursor.getString(1);
                if (!numberMap.containsKey(number)) {
                    numberMap.put(allCursor.getString(1),
                            allCursor.getInt(0));
                    System.out.println("==============查出来联系人"+numberMap);
                    break;
                }
            }
        }
//
//        if (allCursor != null) {
//            allCursor.close();
//            allCursor = null;
//        }
//
//        StringBuilder sb = new StringBuilder();
//        int length = numberMap.size();
//        Integer[] _ids = numberMap.values().toArray(new Integer[length]);
//        for (int i = 0; i < length; i++) {
//            sb.append(CallLog.Calls._ID + "=" + _ids[i]);
//            sb.append(" or ");
//        }
//        if (sb.length() >= 4) {
//            sb.delete(sb.length() - 4, sb.length());
//        }
//        Cursor filterCursor = cr.query(CallLog.Calls.CONTENT_URI,
//                new String[] { CallLog.Calls._ID, CallLog.Calls.NUMBER,
//                        CallLog.Calls.DATE, CallLog.Calls.DURATION,
//                        CallLog.Calls.TYPE, CallLog.Calls.CACHED_NAME },
//                sb.toString(), null, CallLog.Calls.DEFAULT_SORT_ORDER);
//        return filterCursor;
    }
}
