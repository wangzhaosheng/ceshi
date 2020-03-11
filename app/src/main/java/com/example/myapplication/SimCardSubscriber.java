package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.SparseArray;


import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by ${zuowp291} on 2017/3/17.
 */

public class SimCardSubscriber {
    private static final String TAG = "SimCardSubscriber";
    private static volatile SimCardSubscriber subscriber;
    final Set<String> simCardIds;
    final Context context;
    final CompatGenerator generator;

    SimCardSubscriber(Context c) {
        simCardIds = new TreeSet<>();
        context = c;
        generator = new CompatGenerator(c);
    }

    public static Set<String> getSubscribers(Context c) {
        if (subscriber == null) {
            synchronized (SimCardSubscriber.class) {
                if (subscriber == null) {
                    subscriber = new SimCardSubscriber(c.getApplicationContext());
                }
            }
        }
        return subscriber.getSubscribers();
    }

    private synchronized Set<String> getSubscribers() {
        simCardIds.clear();
        generator.reset();
        while (generator.hasNext()) {
            SecondSubscriberCompat compat = generator.next();
            if (compat != null  ) {
                boolean subscribers = compat.getSubscribers(simCardIds);
//                break;
            }
        }
        return simCardIds;
    }


    @SuppressWarnings("ConstantConditions")
    private static class CompatGenerator implements Iterator<SecondSubscriberCompat> {
        private int currentSim = 0;
        private final Context context;
        private final SparseArray<SecondSubscriberCompat> cache = new SparseArray<>(6);

        CompatGenerator(Context context) {
            this.context = context;
        }

        @Override
        public boolean hasNext() {
            return currentSim < 6;
        }

        @Override
        public SecondSubscriberCompat next() {
            if (cache.indexOfKey(currentSim) >= 0) {
                return cache.get(currentSim++);
            }
            SecondSubscriberCompat compat = null;
            switch (currentSim) {
                case 0:
                    try {
                        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                        Method m = telephonyManager.getClass().getDeclaredMethod("getSubscriberId", int.class);
                        if (m != null) {
                            compat = new SecondSubscriberCompat(context, telephonyManager, m);
                        }
                    } catch (Exception ignored) {
                    }
                    break;
                case 1:
                    try {
                        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                        Method m = telephonyManager.getClass().getDeclaredMethod("getSubscriberId", long.class);
                        if (m != null) {
                            compat = new SecondSubscriberCompat(context, telephonyManager, m);
                        }
                    } catch (Exception ignored) {
                    }
                    break;
                case 2:
                    try {
                        Class<?> telephonyClass = Class.forName("android.telephony.MSimTelephonyManager");
                        Method getDefault = telephonyClass.getMethod("getDefault");
                        Object receiver = getDefault.invoke(null);
                        Method m = telephonyClass.getMethod("getSubscriberId", int.class);
                        if (m != null) {
                            compat = new SecondSubscriberCompat(context, receiver, m);
                        }
                    } catch (Exception ignored) {
                    }
                    break;
                case 3:
                    try {
                        Class<?> telephonyClass = Class.forName("android.telephony.MSimTelephonyManager");
                        Method getDefault = telephonyClass.getMethod("getDefault");
                        Object receiver = getDefault.invoke(null);
                        Method m = telephonyClass.getMethod("getSubscriberId", long.class);
                        if (m != null) {
                            compat = new SecondSubscriberCompat(context, receiver, m);
                        }
                    } catch (Exception ignored) {
                    }
                    break;
                //change
                case 4:
                    try {
                        @SuppressLint("WrongConstant")
                        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone2");
                        Method m = telephonyManager.getClass().getMethod("getSubscriberId");
                        if (telephonyManager != null && m != null) {
                            compat = new SecondSubscriberCompat(context, telephonyManager, m);
                        }
                    } catch (Exception ignored) {
                    }
                    break;
                case 5:
                    try {
                        Object object = context.getSystemService(Context.TELEPHONY_SERVICE);
                        Method m = object.getClass().getDeclaredMethod("getSubscriberIdGemini", int.class);
                        if (m != null) {
                            compat = new SecondSubscriberCompat(context, object, m);
                        }
                    } catch (Exception ignored) {
                    }
                    break;
                default:
                    break;
            }
            cache.put(currentSim++, compat);
            return compat;
        }

        //do nothing
        @Override
        public void remove() {
        }

        public void reset() {
            currentSim = 0;
        }
    }
}
