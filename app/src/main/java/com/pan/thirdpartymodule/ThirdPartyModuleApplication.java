package com.pan.thirdpartymodule;

import android.app.Application;
import android.content.Context;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;

/**
 * Author : Pan
 * Date : 17/07/2017
 */

public class ThirdPartyModuleApplication extends Application {
    private RefWatcher mRefWatcher;

    private static final boolean DEBUG = false;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mRefWatcher = LeakCanary.install(this);

        Timber.plant(new Timber.DebugTree());

        WbSdk.install(this, new AuthInfo(this, Constants.WEI_BO_APP_KEY,
                Constants.REDIRECT_URL, Constants.SCOPE));
    }

    public static RefWatcher getRefWatcher(Context context) {
        ThirdPartyModuleApplication application =
                (ThirdPartyModuleApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }
}
