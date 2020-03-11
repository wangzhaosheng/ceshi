package com.example.myapplication;

/**
 * Created by han on 18-3-28.
 */

public interface RecordStreamListener {
    public void onRecording(byte[] audiodata, int i, int length);

    void finishRecord();

}
