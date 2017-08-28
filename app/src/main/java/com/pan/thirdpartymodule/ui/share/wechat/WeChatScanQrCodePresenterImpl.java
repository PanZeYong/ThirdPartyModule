package com.pan.thirdpartymodule.ui.share.wechat;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.pan.thirdpartymodule.Constants;
import com.tencent.mm.opensdk.diffdev.IDiffDevOAuth;
import com.tencent.mm.opensdk.diffdev.OAuthListener;

/**
 * Author : Pan
 * Date : 28/08/2017
 */

public class WeChatScanQrCodePresenterImpl extends WeChatScanQrCodePresenter {
    private WeChatScanQrCodeView mView;

    private Context mContext;

    public WeChatScanQrCodePresenterImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void attachView(@Nullable WeChatScanQrCodeView view) {
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
    void loginWeChat(IDiffDevOAuth auth, OAuthListener listener) {
        auth.stopAuth();

        boolean authRet = auth.auth(Constants.WECHAT_APP_KEY, "snsapi_login",
                genNonceStr(), genTimestamp(), "", listener);

        Toast.makeText(mContext, "Auth : " + authRet, Toast.LENGTH_LONG).show();
    }

    @Override
    void cancelWeChat(IDiffDevOAuth auth) {
        auth.stopAuth();
    }

    private String genNonceStr() {
        //Random r = new Random(System.currentTimeMillis());
        //return MD5.getMessageDigest((APP_ID + r.nextInt(10000) + System.currentTimeMillis()).getBytes());
        return "noncestr";
    }

    private String genTimestamp() {
        //return System.currentTimeMillis() + "";
        return "timestamp";
    }
}
