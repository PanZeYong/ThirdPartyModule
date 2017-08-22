package com.pan.thirdpartymodule.wxapi;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

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
import timber.log.Timber;

/**
 * Author : Pan
 * Date : 24/07/2017
 */

public class WXEntryActivity extends BaseActivity<WeChatSharePresenter>
        implements IWXAPIEventHandler, WeChatView{

    private IWXAPI mApi;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Timber.d("onNewIntent");
        setIntent(intent);
        mApi.handleIntent(intent, this);
    }

    @Override
    protected int initContentView() {
        return R.layout.activity_wechat_share;
    }

    @Override
    protected void init() {
        mPresenter.attachView(this);
        mApi = WXAPIFactory.createWXAPI(this, Constants.WECHAT_APP_KEY, false);

        mApi.handleIntent(getIntent(), this);
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
        Timber.d("Request Type : " + baseReq.getType());

        Toast.makeText(this, "Type : " + baseReq.getType(), Toast.LENGTH_LONG).show();
    }

    /**
     * 发送到微信请求的响应结果
     * @param baseResp
     */
    @Override
    public void onResp(BaseResp baseResp) {
        Timber.d("Response Type : " + baseResp.errCode);

        Toast.makeText(this, "Response Type : " + baseResp.getType(), Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(context, WXEntryActivity.class);
        context.startActivity(intent);
    }
}
