package com.pan.thirdpartymodule.wxapi;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.pan.thirdpartymodule.Constants;
import com.pan.thirdpartymodule.R;
import com.pan.thirdpartymodule.base.BaseActivity;
import com.pan.thirdpartymodule.ui.share.wechat.WeChatSharePresenter;
import com.pan.thirdpartymodule.ui.share.wechat.WeChatSharePresenterImpl;
import com.pan.thirdpartymodule.ui.share.wechat.WeChatView;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.OnClick;

/**
 * Author : Pan
 * Date : 24/07/2017
 */

public class WxEntryActivity extends BaseActivity<WeChatSharePresenter>
        implements IWXAPIEventHandler, WeChatView{

    private IWXAPI mApi;

    @Override
    protected int initContentView() {
        return R.layout.activity_wechat_share;
    }

    @Override
    protected void init() {
        mPresenter.attachView(this);
        mApi = WXAPIFactory.createWXAPI(this, Constants.WECHAT_APP_KEY, false);
    }

    @Override
    protected void registerListener() {

    }

    @Override
    protected WeChatSharePresenter bindPresenter() {
        return new WeChatSharePresenterImpl(this);
    }

    @Override
    protected void release() {
        mPresenter.detachView();
    }

    /**
     * 微信发送的请求
     * @param baseReq
     */
    @Override
    public void onReq(BaseReq baseReq) {

    }

    /**
     * 发送到微信请求的响应结果
     * @param baseResp
     */
    @Override
    public void onResp(BaseResp baseResp) {

    }

    @OnClick({R.id.btn_register, R.id.btn_goto, R.id.btn_launch, R.id.btn_check_timeline_supported,
            R.id.btn_scan_qr_code_login})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                mPresenter.registerWX(mApi);
                break;

            case R.id.btn_goto:
                mPresenter.gotoWX();
                break;

            case R.id.btn_launch:
                mPresenter.launchWX(mApi);
                break;

            case R.id.btn_check_timeline_supported:
                mPresenter.checkTimelineSupported(mApi);
                break;

            case R.id.btn_scan_qr_code_login:
                mPresenter.scanQrCodeLogin();
                break;

            default:
                break;
        }
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, WxEntryActivity.class);
        context.startActivity(intent);
    }
}
