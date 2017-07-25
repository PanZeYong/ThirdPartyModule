package com.pan.thirdpartymodule.ui.share.wechat;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.pan.thirdpartymodule.Constants;
import com.tencent.mm.opensdk.openapi.IWXAPI;

/**
 * Author : Pan
 * Date : 24/07/2017
 */

public class WeChatSharePresenterImpl extends WeChatSharePresenter{
    private WeChatView mView;

    private Context mContext;

    public WeChatSharePresenterImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void attachView(@Nullable WeChatView view) {
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
    public void registerWX(IWXAPI iwxapi) {
        boolean isRegister = iwxapi.registerApp(Constants.WECHAT_APP_KEY);
        Toast.makeText(mContext, "Register : " + isRegister, Toast.LENGTH_LONG).show();
    }

    @Override
    public void gotoWX() {
        WeChatShareActivity.startActivity(mContext);
    }

    @Override
    public void launchWX(IWXAPI iwxapi) {
        Toast.makeText(mContext, "Launch Result : " + iwxapi.openWXApp(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void checkTimelineSupported(IWXAPI iwxapi) {
        int wxSdkVersion = iwxapi.getWXAppSupportAPI();

        if (wxSdkVersion >= Constants.TIMELINE_SUPPORTED_VERSION) {
            Toast.makeText(mContext, "WXSdkVersion : " + Integer.toHexString(wxSdkVersion) +
                    " timeline supported", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(mContext, "WXSdkVersion : " + Integer.toHexString(wxSdkVersion) +
                    " timeline not supported", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void scanQrCodeLogin() {

    }
}
