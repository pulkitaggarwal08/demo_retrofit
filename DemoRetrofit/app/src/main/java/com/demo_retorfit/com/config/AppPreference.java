package com.demo_retorfit.com.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by pulkit on 10/3/18.
 */

public class AppPreference {

    private static AppPreference appPreferences;
    protected SharedPreferences sharedPreferences;

    public final static String KEY_PHONE_NUM = "phone_num";
    public final static String KEY_USER_TOKEN = "user";
    public final static String KEY_USER_REFRESH_TOKEN = "user_refresh";

    public AppPreference(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static AppPreference init(Context context) {
        if (null == appPreferences) {
            appPreferences = new AppPreference(context);
        }
        return appPreferences;
    }

    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getSavedString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }



}
