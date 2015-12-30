package com.times.ussd.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by umesh on 14/8/15.
 */
public class PreferenceHelper {

    private static PreferenceHelper sInstance;
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    public interface PreferenceKeys {
        String MAIN_BALANCE = "main_balance";
        String INTERNET_BALANCE = "internet_balance";
    }

    private PreferenceHelper(Context ctx) {
        mPrefs = ctx.getApplicationContext().getSharedPreferences(ctx.getPackageName(), Context.MODE_PRIVATE);
        mEditor = mPrefs.edit();
    }

    public static PreferenceHelper getInstance(Context ctx) {
        if (sInstance == null)
            sInstance = new PreferenceHelper(ctx.getApplicationContext());
        return sInstance;
    }


    public void setValue(String key, String value) {
        mEditor.putString(key, value).apply();
    }

    public String getValue(String key) {
        return mPrefs.getString(key, "");
    }


}
