package com.example.myapplication;

import android.content.Context;

import java.util.Set;

/**
 * Created by ${zuowp291} on 2017/3/17.
 */

abstract class SubscriberIdCompat {
    final Context context;

    SubscriberIdCompat(Context c) {
        context = c;
    }


    abstract boolean getSubscribers(Set<String> sub);
}
