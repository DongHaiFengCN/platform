package com.yh.ydd.common.mvp;

import android.app.Application;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

/**
 * 基础初始化的application
 */
public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        //初始化内存泄漏监听
        initLeakCanary();



    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        Log.e("DOAING", "APPLICATION关闭了");
    }

    private void initLeakCanary() {

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

}
