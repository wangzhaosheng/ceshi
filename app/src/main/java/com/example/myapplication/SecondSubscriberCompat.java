package com.example.myapplication;

import android.content.Context;
import android.text.TextUtils;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by ${zuowp291} on 2017/3/17.
 */

class SecondSubscriberCompat extends SubscriberIdCompat {
    final Object invoker;
    final Method method;


    SecondSubscriberCompat(Context c, Object obj, Method method) {
        super(c);
        invoker = obj;
        this.method = method;
    }

    @Override
    boolean getSubscribers(Set<String> res) {
        boolean update = false;
        for (int i = 0; i < 9; i++) {
            try {
                Object result = method.invoke(invoker, i);
                if (result != null) {
                    String data = result.toString();
                    if (!TextUtils.isEmpty(data)) {
                        update |= res.add(data);
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return update;
    }
}
