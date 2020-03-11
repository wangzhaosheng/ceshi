package com.example.myapplication;

import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import java.io.IOException;

public class MyMediaRecorder {


	private static final String TAG = MyMediaRecorder.class.getSimpleName();
	private MediaRecorder mediaRecorder;

	private static MyMediaRecorder myMediaRecorder;

	private boolean bStarted = false;

	private String filePath;

	private MyMediaRecorder(){
		mediaRecorder = new MediaRecorder();
	}

	public synchronized static MyMediaRecorder getInstance() {
		if (myMediaRecorder == null) {
			myMediaRecorder = new MyMediaRecorder();
		}
		return myMediaRecorder;
	}

	public synchronized boolean start(final String filePath) {
		Log.e(TAG,"[start] filepath:" + filePath + "  bStarted:" + bStarted);
		this.filePath = filePath;
		if (!bStarted) {
//			mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_RECOGNITION);
//			mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// Microphone
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);// 设置输出格式
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);



//			mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			mediaRecorder.setOnErrorListener(new OnErrorListener() {

				@Override
				public void onError(MediaRecorder mr, int what, int extra) {
					Log.e(TAG,"[MediaRecorder.OnErrorListener] what:" + what + " extra:" + extra);
					stop();
					start(filePath);
				}
			});
			mediaRecorder.setOutputFile(filePath);
			try {
				mediaRecorder.prepare();
                // TODO: 2019/4/26 应该在准备后面紧接着start   不然会报错

			} catch (IllegalStateException e) {
				Log.e(TAG,"[mediaRecorder.prepare] IllegalStateException" + e.getLocalizedMessage());
				mediaRecorder.reset();
				return false;
			} catch (Exception e) {
				Log.e(TAG,"[mediaRecorder.prepare] IOException" + e.getLocalizedMessage());
				e.printStackTrace();
				mediaRecorder.reset();
				return false;
			}
			try {
				mediaRecorder.start();
			} catch (Exception e) {
				Log.e(TAG,"[mediaRecorder.start]" + e.getLocalizedMessage());
				e.printStackTrace();
				mediaRecorder.reset();
				return false;
			}
			bStarted = true;
		}
		return true;
	}

	public synchronized boolean isStarted() {
		return bStarted;
	}

	public synchronized void stop() {
		Log.e(TAG,"[stop]   bStarted:" + bStarted);
		try {
			if (bStarted) {
				bStarted = false;
				mediaRecorder.stop();
				mediaRecorder.reset();
//				en();
			}
		} catch (Exception e) {
			mediaRecorder.reset();
			e.printStackTrace();
		}
	}

//	private void en() {
//		File file = new File(filePath);
//		try {
//			SecurityAesUtil.encryptFile(file, new File(filePath + "mm"), NativeManager.b() + RcdServiceManager.pw);
//			logger.debug("[en] en success");
//			boolean b = file.delete();
//			Log.e(TAG,"[en] delete " + file.getName() + " result:" + b);
//		} catch (Exception e) {
//			e.printStackTrace();
//			boolean b = file.delete();
//			logger.error("[en]" + e.getLocalizedMessage() + " delete success:" + b);
//		}
//	}

	public synchronized void release() {
		Log.e(TAG,"[release]");
		mediaRecorder.release();
	}

}