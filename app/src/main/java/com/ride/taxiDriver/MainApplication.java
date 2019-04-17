package com.ride.taxiDriver;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.ride.taxiDriver.analytics.MyApplication;
import com.bugfender.sdk.Bugfender;

/**
 * Created by samirgoel3@gmail.com on 6/5/2017.
 */

public class MainApplication extends MyApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Bugfender.init(this, "kCSJzZm4iEPQvHnaUeCSHhHAPpXjalFe", io.fabric.sdk.android.BuildConfig.DEBUG);
        Bugfender.enableLogcatLogging();
        Bugfender.enableUIEventLogging(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(base);
        super.attachBaseContext(base);
    }
}
