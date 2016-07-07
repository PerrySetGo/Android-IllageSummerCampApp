package com.perrysetgo.illageSummerCamp;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class IllageApplication extends com.activeandroid.app.Application {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    public void onCreate() {
        super.onCreate();
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        mEditor.putString(Constants.PREFERENCES_USER_KEY, "goodChoices").apply();
        mEditor.putString(Constants.PREFERENCES_PW_KEY, "l34d3r").apply();
        mEditor.putBoolean(Constants.PREFERENCES_LOGIN_STATUS, false);
    }
}
