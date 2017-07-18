package com.pan.thirdpartymodule;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;

/**
 * Author : Pan
 * Date : 17/07/2017
 */

public class ThirdPartyModuleApplication extends Application {
    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mRefWatcher = LeakCanary.install(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static RefWatcher getRefWatcher(Context context) {
        ThirdPartyModuleApplication application =
                (ThirdPartyModuleApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }
}
