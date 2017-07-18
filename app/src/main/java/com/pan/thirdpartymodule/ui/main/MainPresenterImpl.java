package com.pan.thirdpartymodule.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import com.pan.thirdpartymodule.R;
import com.pan.thirdpartymodule.ui.login.LoginFragment;
import com.pan.thirdpartymodule.ui.share.ShareFragment;

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
        switch (item.getItemId()) {
            case R.id.login:
                mView.showFragment(R.id.container, LoginFragment.newInstance());
                mView.setTitle(context.getString(R.string.third_party_login));
                break;

            case R.id.share:
                mView.showFragment(R.id.container, ShareFragment.newInstance());
                mView.setTitle(context.getString(R.string.share));
                break;

            default:
                break;
        }
    }
}
