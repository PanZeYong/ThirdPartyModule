package com.pan.thirdpartymodule.ui.main;

import android.support.v4.app.Fragment;

import com.pan.thirdpartymodule.base.BaseView;

/**
 * Author : Pan
 * Date : 17/07/2017
 */

public interface MainView extends BaseView{
    void showFragment(int containerId, Fragment fragment);

    void setTitle(String title);
}
