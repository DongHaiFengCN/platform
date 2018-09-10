package com.yh.ydd.platform.entrance.application;

import android.os.Debug;

import com.yh.ydd.common.mvp.BaseApplication;

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Debug.startMethodTracing("MyApp");
    }
}
