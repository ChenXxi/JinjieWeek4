package com.example.jinjieweek4.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpBase {
    private static final String Sp_name = "user";
    //存数据
    public static void save(Context context,String key,String value){
        SharedPreferences preferences = context.getSharedPreferences(Sp_name,Context.MODE_PRIVATE);
        preferences.edit().putString(key, value).commit();
    }
    //取数据
    public static String getString(Context context,String key,String defultValue){
        return context.getSharedPreferences(Sp_name,Context.MODE_PRIVATE).getString(key,defultValue);
    }
}
