package com.empty.cup.application;

import android.app.Application;

//import com.squareup.leakcanary.LeakCanary;

/**
 * Created by empty cup on 2017/9/22.
 */

public class ExampleApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        // Normal app init code...
    }
}
