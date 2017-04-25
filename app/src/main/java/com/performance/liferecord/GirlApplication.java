package com.performance.liferecord;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Project name : Girls .
 * Package name : com.performance.liferecord .
 * Created by gaojack .
 * Date : 2017/4/25 .
 */

public class GirlApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
