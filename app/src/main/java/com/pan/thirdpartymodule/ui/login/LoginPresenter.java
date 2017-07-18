package com.pan.thirdpartymodule.ui.login;

import com.pan.thirdpartymodule.base.BasePresenter;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

/**
 * Author : Pan
 * Date : 18/07/2017
 */

public abstract class LoginPresenter extends BasePresenter<LoginView>{
    public abstract void loginByWeiBo(SsoHandler ssoHandler);
}
