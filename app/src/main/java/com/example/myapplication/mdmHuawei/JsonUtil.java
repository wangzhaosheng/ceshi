package com.example.myapplication.mdmHuawei;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Json解析工作类
 */
public class JsonUtil {

    private static final String TAG = "JsonUtil";

    private static class SingletonHolder {
        /**
         * The Gson instance does not maintain any state while invoking Json operations.
         * So, you are free to reuse the same object for multiple Json serialization and deserialization operations.
         */
        private final static Gson INSTANCE = new Gson();
    }

    public static Gson getGson() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * json string to object
     *
     * @param json     the string from which the object is to be deserialized
     * @param classOfT the class of T
     * @param <T>      the type of the desired object
     * @return an object of type T from the string. Returns {@code null} if {@code json} is {@code null}.
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return getGson().fromJson(json, classOfT);
        } catch (Exception ex) {
//            ZLog.e(TAG, json + "\n" + classOfT + "\n" + ex);
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * json string to object
     *
     * @param json    the string from which the object is to be deserialized
     * @param typeOfT The specific genericized type of src. You can obtain this type by using the
     * @param <T>     the type of the desired object
     * @return an object of type T from the string. Returns {@code null} if {@code json} is {@code null}.
     */
    public static <T> T fromJson(String json, Type typeOfT) {
        try {
            return getGson().fromJson(json, typeOfT);
        } catch (Exception ex) {
//            ZLog.e(TAG, json + "\n" + typeOfT + "\n" + ex);

            ex.printStackTrace();
        }
        return null;
    }

    /**
     * object to json string
     *
     * @param src the object for which Json representation is to be created setting for Gson
     * @return json string
     */
    public static String toJson(Object src) {
        try {
            return getGson().toJson(src);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * object to json string
     *
     * @param src       the object for which Json representation is to be created setting for Gson
     * @param typeOfSrc The specific genericized type of src. You can obtain
     * @return json string
     */
    public static String toJson(Object src, Type typeOfSrc) {
        try {
            return getGson().toJson(src, typeOfSrc);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static int optInt(String json, String key) {
        int result = 0;
        try {
            JSONObject jsonObject = new JSONObject(json);
            result = jsonObject.optInt(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static long optLong(String json, String key) {
        long result = 0;
        try {
            JSONObject jsonObject = new JSONObject(json);
            result = jsonObject.optLong(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static double optDouble(String json, String key) {
        double result = 0D;
        try {
            JSONObject jsonObject = new JSONObject(json);
            result = jsonObject.optDouble(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static boolean optBoolean(String json, String key) {
        boolean result = false;
        try {
            JSONObject jsonObject = new JSONObject(json);
            result = jsonObject.optBoolean(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static String optString(String json, String key) {
        String result = "";
        try {
            JSONObject jsonObject = new JSONObject(json);
            result = jsonObject.optString(key);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
