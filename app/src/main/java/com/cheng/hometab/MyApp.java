package com.cheng.hometab;

import android.app.Application;

public class MyApp extends Application{

    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
