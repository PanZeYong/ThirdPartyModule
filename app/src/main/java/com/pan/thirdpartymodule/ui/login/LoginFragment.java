package com.pan.thirdpartymodule.ui.login;

import android.view.View;

import com.pan.thirdpartymodule.R;
import com.pan.thirdpartymodule.base.BaseFragment;
import com.pan.thirdpartymodule.base.BasePresenter;

/**
 * Author : Pan
 * Date : 17/07/2017
 */

public class LoginFragment extends BaseFragment {
    @Override
    protected int initContentView() {
        return R.layout.login;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }
}
