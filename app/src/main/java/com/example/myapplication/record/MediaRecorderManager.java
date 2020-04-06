package com.example.myapplication.record;

import android.media.MediaRecorder;
import android.util.Log;

/**
 * Created by wangzhaosheng on 2020-03-11
 * Description 录音类
 */
public class MediaRecorderManager {

    private static final String TAG = MediaRecorderReceiver.class.getSimpleName();
    private MediaRecorder mediaRecorder;


    private boolean bStarted = false;

    public MediaRecorderManager() {

    }

    public synchronized boolean record(final String filePath) {
        Log.e(TAG, "[start] filepath:" + filePath + "  bStarted:" + bStarted);
        if (!bStarted) {
            try {
                //每次重新创建对象   stop的时候release了, 必须重新创建对象
                mediaRecorder = new MediaRecorder();
                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// Microphone
                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);// 设置输出格式
                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                mediaRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {

                    @Override
                    public void onError(MediaRecorder mr, int what, int extra) {
                        Log.e(TAG, "[MediaRecorder.OnErrorListener] what:" + what + " extra:" + extra);
                    }
                });
                mediaRecorder.setOutputFile(filePath);
                mediaRecorder.prepare();
                mediaRecorder.start();
            } catch (Exception e) {
                Log.e(TAG, "Exception" + e.getLocalizedMessage());
                e.printStackTrace();
                return false;
            }
            bStarted = true;
        }
        return true;
    }

    public synchronized void stopRecord() {
        Log.e(TAG, "stopRecord" + bStarted);
        try {
            if (bStarted) {
                bStarted = false;
                mediaRecorder.stop();
                mediaRecorder.reset();
                mediaRecorder.release();
            }
        } catch (Exception e) {
            Log.e(TAG, "stopRecord exception" + e);
        }
    }


}
