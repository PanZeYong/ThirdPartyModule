package com.pan.thirdpartymodule.ui.share.wechat;

import com.pan.thirdpartymodule.base.BasePresenter;
import com.tencent.mm.opensdk.openapi.IWXAPI;

/**
 * Author : Pan
 * Date : 24/07/2017
 */

public abstract class WeChatSharePresenter extends BasePresenter<WeChatView>{
    public abstract void registerWX(IWXAPI iwxapi);

    public abstract void gotoWX();

    public abstract void launchWX(IWXAPI iwxapi);

    public abstract void checkTimelineSupported(IWXAPI iwxapi);

    public abstract void scanQrCodeLogin();

    public abstract void sendText(IWXAPI iwxapi);

    public abstract void sendImage(IWXAPI iwxapi);

    public abstract void sendMusic(IWXAPI iwxapi);
}
