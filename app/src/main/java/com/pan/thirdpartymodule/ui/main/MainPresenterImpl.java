package com.pan.thirdpartymodule.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MenuItem;

/**
 * Author : Pan
 * Date : 17/07/2017
 */

public class MainPresenterImpl extends MainPresenter {
    private MainView mView;

    @Override
    public void attachView(@Nullable MainView v) {
        if (null == mView) {
            this.mView = v;
        }
    }

    @Override
    public void detachView() {
        if (null != mView) {
            mView = null;
        }
    }

    @Override
    public void clickMenuItem(Context context, MenuItem item) {

    }
}
