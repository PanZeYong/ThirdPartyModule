package com.pan.thirdpartymodule.ui.share.wechat;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.pan.thirdpartymodule.R;
import com.pan.thirdpartymodule.base.BaseActivity;
import com.tencent.mm.opensdk.diffdev.DiffDevOAuthFactory;
import com.tencent.mm.opensdk.diffdev.IDiffDevOAuth;
import com.tencent.mm.opensdk.diffdev.OAuthErrCode;
import com.tencent.mm.opensdk.diffdev.OAuthListener;

import butterknife.OnClick;
import timber.log.Timber;

/**
 * Author : Pan
 * Date : 28/08/2017
 */

public class WeChatScanQrCodeActivity extends BaseActivity<WeChatScanQrCodePresenter>
        implements WeChatScanQrCodeView, OAuthListener{
    private static final String TAG = WeChatScanQrCodeActivity.class.getCanonicalName();

    private IDiffDevOAuth mAuth;

    @Override
    protected int initContentView() {
        return R.layout.activity_scan_qrcode;
    }

    @Override
    protected void init() {
        mAuth = DiffDevOAuthFactory.getDiffDevOAuth();
        mPresenter.attachView(this);
    }

    @Override
    protected void registerListener() {

    }

    @Override
    protected WeChatScanQrCodePresenter bindPresenter() {
        return new WeChatScanQrCodePresenterImpl(this);
    }

    @Override
    protected void release() {
        mAuth.removeAllListeners();
        mAuth.detach();
        mPresenter.detachView();
    }

    @OnClick({R.id.start_oauth_btn, R.id.stop_oauth_btn})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.start_oauth_btn:
                mPresenter.loginWeChat(mAuth, this);
                break;

            case R.id.stop_oauth_btn:
                mPresenter.cancelWeChat(mAuth);
                break;

            default:
                break;
        }
    }

    @Override
    public void onAuthGotQrcode(String s, byte[] bytes) {
        Timber.d(TAG + " : " + "onAuthGotQrcode");
    }

    @Override
    public void onQrcodeScanned() {
        Timber.d(TAG + " : " + "onQrcodeScanned");
    }

    @Override
    public void onAuthFinish(OAuthErrCode oAuthErrCode, String s) {
        Timber.d(TAG + " : " + "onAuthFinish");
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, WeChatScanQrCodeActivity.class);
        context.startActivity(intent);
    }
}
