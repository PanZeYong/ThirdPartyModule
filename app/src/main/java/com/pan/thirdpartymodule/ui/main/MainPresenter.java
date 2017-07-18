package com.pan.thirdpartymodule.ui.main;

import android.content.Context;
import android.view.MenuItem;

import com.pan.thirdpartymodule.base.BasePresenter;

/**
 * Author : Pan
 * Date : 17/07/2017
 */

public abstract class MainPresenter extends BasePresenter<MainView>{
    /**
     * NavigationView MenuItem点击事件
     * @param item item
     */
    public abstract void clickMenuItem(Context context, MenuItem item);
}
