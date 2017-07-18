package com.pan.thirdpartymodule.ui.login;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import java.text.SimpleDateFormat;

import timber.log.Timber;

/**
 * Author : Pan
 * Date : 18/07/2017
 */

public class LoginPresenterImpl extends LoginPresenter {
    private LoginView mView;

    private Context mContext;

    public LoginPresenterImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void attachView(@Nullable LoginView view) {
        if (null == mView) {
            this.mView = view;
        }
    }

    @Override
    public void detachView() {
        if (null != mView) {
            mView = null;
        }
    }

    @Override
    public void loginByWeiBo(SsoHandler ssoHandler) {
        // 如果安装微博客户端则通过客户端授权；如果没有则通过 Web 授权
//        ssoHandler.authorize(new SelfWbAuthListener());

        // 只通过微博客户端进行授权
//        ssoHandler.authorizeClientSso(new SelfWbAuthListener());

        // 通过 SDK 自带的 WebView 打开 H5 页面进行授权
        ssoHandler.authorizeWeb(new SelfWbAuthListener());
    }

    private class SelfWbAuthListener implements WbAuthListener {

        @Override
        public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
            Timber.d("onSuccess : " + (mView == null));
            mView.showMessage("uid = " + oauth2AccessToken.getUid() + "\n" +
                    "access_token = " + oauth2AccessToken.getToken() + "\n" +
                    "expires_in = " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
                    new java.util.Date(oauth2AccessToken.getExpiresTime())) + "\n" +
                    "refresh_token = " + oauth2AccessToken.getRefreshToken() + "\n" +
                    "phone_num = " + oauth2AccessToken.getPhoneNum());
        }

        @Override
        public void cancel() {
            Timber.d("cancel");
            mView.showMessage("cancel");
        }

        @Override
        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
            Timber.d("onFailure : " + wbConnectErrorMessage.getErrorMessage());
            mView.showMessage(wbConnectErrorMessage.getErrorCode());
        }
    }
}
