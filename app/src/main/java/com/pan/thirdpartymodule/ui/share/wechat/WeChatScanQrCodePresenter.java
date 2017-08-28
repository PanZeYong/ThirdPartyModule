package com.pan.thirdpartymodule.ui.share.wechat;

import com.pan.thirdpartymodule.base.BasePresenter;
import com.tencent.mm.opensdk.diffdev.IDiffDevOAuth;
import com.tencent.mm.opensdk.diffdev.OAuthListener;

/**
 * Author : Pan
 * Date : 28/08/2017
 */

public abstract class WeChatScanQrCodePresenter extends BasePresenter<WeChatScanQrCodeView> {
    abstract void loginWeChat(IDiffDevOAuth auth, OAuthListener listener);

    abstract void cancelWeChat(IDiffDevOAuth auth);
}
