package com.ola.android22.mylibrary;

/**
 * Created by wangzhaosheng on 2020-04-08
 * Description
 */
public class Utils {

    public static String getDebug(){
        return BuildConfig.DEBUG == true? "是debug":"不是debug";
    }
}
