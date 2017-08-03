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

    public abstract void shareText(IWXAPI iwxapi);

    public abstract void shareImage(IWXAPI iwxapi);

    public abstract void shareMusic(IWXAPI iwxapi);

    public abstract void shareVideo(IWXAPI iwxapi);

    public abstract void shareWebPage(IWXAPI iwxapi);

    public abstract void shareMiniProgram(IWXAPI iwxapi);

    public abstract void shareAppData(IWXAPI iwxapi);

    public abstract void getToken(IWXAPI iwxapi);

    public abstract void unregister(IWXAPI iwxapi);

    public abstract void openCamera();
}
