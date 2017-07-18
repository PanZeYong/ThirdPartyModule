package com.pan.thirdpartymodule.ui.share;

import android.view.View;

import com.pan.thirdpartymodule.R;
import com.pan.thirdpartymodule.base.BaseFragment;
import com.pan.thirdpartymodule.base.BasePresenter;

/**
 * Author : Pan
 * Date : 18/07/2017
 */

public class ShareFragment extends BaseFragment {
    @Override
    protected int initContentView() {
        return R.layout.share;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected String getClassTag() {
        return ShareFragment.class.getCanonicalName();
    }

    @Override
    protected void release() {

    }

    public static ShareFragment newInstance() {
        return new ShareFragment();
    }
}
