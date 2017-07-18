package com.pan.thirdpartymodule.ui.login;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.pan.thirdpartymodule.R;
import com.pan.thirdpartymodule.base.BaseFragment;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author : Pan
 * Date : 17/07/2017
 */

public class LoginFragment extends BaseFragment<LoginPresenter> implements LoginView{
    private final static String TAG = LoginFragment.class.getCanonicalName();
    @BindView(R.id.tv_message)
    TextView mMessage;

    private SsoHandler mSsoHandler;

    @Override
    protected int initContentView() {
        return R.layout.login;
    }

    @Override
    protected void init(View view) {
        this.mSsoHandler = new SsoHandler(getActivity());
        mPresenter.attachView(this);
    }

    @Override
    protected LoginPresenter bindPresenter() {
        return new LoginPresenterImpl(mContext);
    }

    @Override
    protected String getClassTag() {
        return TAG;
    }

    @Override
    protected void release() {
        mPresenter.detachView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != mSsoHandler) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    @OnClick(R.id.tv_weibo_login)
    public void loginByWeiBo() {
        mPresenter.loginByWeiBo(mSsoHandler);
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void showMessage(String message) {
        mMessage.setText(message);
    }
}
