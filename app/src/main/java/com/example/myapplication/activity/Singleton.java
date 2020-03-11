package com.example.myapplication.activity;

/**
 * Author: zeal zuo
 * Date: 2017/9/27.
 * Usage:
 */

public abstract class Singleton<T> {
    private volatile T mInstance;

    protected abstract T create();

    public final T get() {
        if (mInstance == null) {
            synchronized (this) {
                if (mInstance == null) {
                    mInstance = create();
                }
            }
        }
        return mInstance;
    }
}
