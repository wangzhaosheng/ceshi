package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

//import com.demo.test.AppEnv;
//import com.demo.test.R;
//import com.demo.test.biometric.BiometricPromptManager;
//import com.demo.test.os.IOUtils;
//import com.demo.test.utils.FileUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FingerActivity extends Activity {

    public static final String TAG = "FingerActivity";

    private TextView mTextView;
    private Button mButton, mButton2;
//    private BiometricPromptManager mManager;

    public static final String NOTE_PREFIX = "_(reqId=%s)";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_finger);
//        mTextView = findViewById(R.id.text_view);
//        mButton = findViewById(R.id.button);
//        mButton2 = findViewById(R.id.button2);



        new AsyncTask<Void, Void, Void>() {

            List<VideoInfo> allVideoList = new ArrayList<VideoInfo>();

            @Override
            protected Void doInBackground(Void... voids) {
                long time = System.currentTimeMillis();
//                getVideoFile(allVideoList, Environment.getExternalStorageDirectory());// 获得视频文件
                getPictures(allVideoList);
                Log.e(TAG, "-----------doInBackground:" + (System.currentTimeMillis() - time));
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
//                Log.e(TAG, "-----------onPostExecute:" + allVideoList.size());
//                for (VideoInfo info : allVideoList) {
//                    Log.e(TAG, "----------" + info.toString());
//                    File f = new File(info.path);
//                    File dest = new File(Environment.getExternalStorageDirectory(), f.getName());
//                    FileUtils.delFile(dest);
//                    try {
//                        FileUtils.copyFile(f, dest);
//                        Log.e(TAG, "------------存在-------------" + dest.exists());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }.execute();


    }


    private void getPictures(List<VideoInfo> list) {
        String[] IMAGES = {
                MediaStore.Images.Media.DATA,
                //name
                MediaStore.Images.Media.DISPLAY_NAME,
//                MediaStore.Images.Media.MIME_TYPE,
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

                VideoInfo videoInfo = new VideoInfo();
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


    private void getVideos(List<VideoInfo> list) {
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

                VideoInfo videoInfo = new VideoInfo();
                videoInfo.setDisplayName(bucketName);
                videoInfo.setPath(path);
                videoInfo.setLastModifyTime(new File(path).lastModified());
                videoInfo.setSize(size);
                list.add(videoInfo);
            }
            cursor.close();
        }
    }


    private void getVideoFile(final List<VideoInfo> list, File rootFile) {// 获得视频文件

        rootFile.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    getVideoFile(list, file);
                } else if (file.isFile()) {
                    // sdCard找到视频名称
                    String name = file.getName();
                    int i = name.indexOf('.');
                    if (i != -1) {
                        name = name.substring(i);
                        if (file.getParentFile().getName().equals(".thumbnails")) {
                            return false;
                        }
                        if (name.equalsIgnoreCase(".jpg")) {
//                            || name.equalsIgnoreCase(".3gp")
//                                    || name.equalsIgnoreCase(".wmv")
//                                    || name.equalsIgnoreCase(".ts")
//                                    || name.equalsIgnoreCase(".rmvb")
//                                    || name.equalsIgnoreCase(".mov")
//                                    || name.equalsIgnoreCase(".m4v")
//                                    || name.equalsIgnoreCase(".avi")
//                                    || name.equalsIgnoreCase(".m3u8")
//                                    || name.equalsIgnoreCase(".3gpp")
//                                    || name.equalsIgnoreCase(".3gpp2")
//                                    || name.equalsIgnoreCase(".mkv")
//                                    || name.equalsIgnoreCase(".flv")
//                                    || name.equalsIgnoreCase(".divx")
//                                    || name.equalsIgnoreCase(".f4v")
//                                    || name.equalsIgnoreCase(".rm")
//                                    || name.equalsIgnoreCase(".asf")
//                                    || name.equalsIgnoreCase(".ram")
//                                    || name.equalsIgnoreCase(".mpg")
//                                    || name.equalsIgnoreCase(".v8")
//                                    || name.equalsIgnoreCase(".swf")
//                                    || name.equalsIgnoreCase(".m2v")
//                                    || name.equalsIgnoreCase(".asx")
//                                    || name.equalsIgnoreCase(".ra")
//                                    || name.equalsIgnoreCase(".ndivx")
//                                    || name.equalsIgnoreCase(".xvid"

                            VideoInfo vi = new VideoInfo();
                            vi.setDisplayName(file.getName());
                            vi.setPath(file.getAbsolutePath());
                            vi.setLastModifyTime(file.lastModified());
                            vi.setSize(file.length());
                            list.add(vi);
                            return true;
                        }
                    }
                }
                return false;
            }
        });
    }


    public static class VideoInfo {
        String displayName;
        String path;
        long lastModifyTime;
        long size;

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public void setLastModifyTime(long lastModifyTime) {
            this.lastModifyTime = lastModifyTime;
        }

        public void setSize(long size) {
            this.size = size;
        }

        @Override
        public String toString() {
            return "VideoInfo{" +
                    "displayName='" + displayName + '\'' +
                    ", path='" + path + '\'' +
                    ", lastModifyTime=" + lastModifyTime +
                    ", size=" + size / 1024 +
                    '}';
        }
    }


    @SuppressLint("StaticFieldLeak")
    private AsyncTask<Void, Void, Void> JsonTask = new AsyncTask<Void, Void, Void>() {

        private HashMap<String, ArrayList<String>> map = new HashMap<>();

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                InputStream is = getAssets().open("sms.json");
                int lenght = is.available();
                byte[] buffer = new byte[lenght];
                is.read(buffer);
                String result = new String(buffer, "utf8");


                JSONObject jsonObject = new JSONObject(result);
                JSONArray array = jsonObject.optJSONArray("column");

                JSONObject o;
                for (int i = 0; i < array.length(); i++) {
                    o = array.optJSONObject(i);
                    String call_phone = o.optString("call_phone");
                    String msgId = o.optString("msgId");
                    ArrayList<String> phones = map.get(msgId);
                    if (phones == null) {
                        phones = new ArrayList<>();
                        map.put(msgId, phones);
                    }
                    phones.add(call_phone);
                }

            } catch (Throwable e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            for (ArrayList<String> ls : map.values()) {
                if (ls.size() > 1) {
                    Log.e(TAG, ls.toString());
                }
            }
        }
    };


    public static String getLauncherPackageName(Context context) {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        final ResolveInfo res = context.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (res.activityInfo == null) {
            return null;
        }
        if (res.activityInfo.packageName.equals("android")) {
            // 有多个桌面程序存在，且未指定默认项时；
            return null;
        } else {
            return res.activityInfo.packageName;
        }
    }

    public int createDeviceDynamicPwd() {
        return (int) ((Math.random() * 9 + 1) * 100);
    }

    private String buildSendImgJson() {
        JSONObject o = new JSONObject();
        try {
            JSONObject o1 = new JSONObject();
            o1.put("event", 10016);
            o1.put("reqid", createDeviceDynamicPwd() + "");
            o1.put("recid", "wxid_222222222222");
            o1.put("momentType", 1);
            o1.put("message", "朋友圈文本信息");
            o1.put("umwxid", "wxid_11111111111");
            JSONArray jsonObject = new JSONArray();
            for (int i = 0; i < 3; i++) {
                JSONObject o2 = new JSONObject();
                o2.put("md5", "asdfdsf");
                o2.put("url", "www.baidu.com");
                jsonObject.put(o2);
            }
            o1.put("fileList", jsonObject);
            o.put("content", o1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return o.toString();
    }

    private Object getField(Object o, String fName, Object defaultValue) {
        try {
            Field f = o.getClass().getDeclaredField(fName);
            f.setAccessible(true);
            return f.get(o);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                Log.e(TAG, "createConfirmDeviceCredentialIntent success");
            } else {
                Log.e(TAG, "createConfirmDeviceCredentialIntent failed");
            }
        }
    }
}
