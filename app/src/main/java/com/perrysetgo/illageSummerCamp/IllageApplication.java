package com.perrysetgo.illageSummerCamp;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class IllageApplication extends com.activeandroid.app.Application {
    SharedPreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mSharedPreferences.edit().putString(Constants.PREFERENCES_ADMIN_USER_KEY, "goodChoices").apply();
        mSharedPreferences.edit().putString(Constants.PREFERENCES_ADMIN_PW_KEY, "l34d3r").apply();
        mSharedPreferences.edit().putBoolean(Constants.PREFERENCES_USER_LOGIN_STATUS, false).apply();
    }
}
