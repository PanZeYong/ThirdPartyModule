package com.pan.thirdpartymodule.ui.share.weibo;

import com.pan.thirdpartymodule.base.BasePresenter;
import com.sina.weibo.sdk.share.WbShareHandler;

/**
 * Author : Pan
 * Date : 20/07/2017
 */

public abstract class WeiBoSharePresenter extends BasePresenter<WeiBoShareView>{
    public abstract void sendMessage(WbShareHandler wbShareHandler, boolean hasText, boolean hasImage);
}
