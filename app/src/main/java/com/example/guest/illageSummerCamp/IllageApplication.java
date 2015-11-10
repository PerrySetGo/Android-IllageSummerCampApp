package com.example.guest.illageSummerCamp;

import android.app.Application;

import com.parse.Parse;

public class IllageApplication extends com.activeandroid.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
       Parse.enableLocalDatastore(this);
       Parse.initialize(this, "eZbkoTB1Ob1vT5lc2DXZC9TfYur3xhbZ6yd41ehz", "kqbP6upN7aNoTCrVzhMCsXQCSuKM2wgd9bfpur08");
    }
}
