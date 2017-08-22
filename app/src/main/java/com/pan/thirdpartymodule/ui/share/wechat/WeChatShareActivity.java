package com.pan.thirdpartymodule.ui.share.wechat;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.pan.thirdpartymodule.Constants;
import com.pan.thirdpartymodule.R;
import com.pan.thirdpartymodule.base.BaseActivity;
import com.pan.thirdpartymodule.base.BasePresenter;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.OnClick;

/**
 * Author : Pan
 * Date : 25/07/2017
 */

public class WeChatShareActivity extends BaseActivity<WeChatSharePresenter> implements WeChatView{
    private IWXAPI mIWXAPI;

    @Override
    protected int initContentView() {
        return R.layout.activity_send_message_to_wechat;
    }

    @Override
    protected void init() {
        mPresenter.attachView(this);

        mIWXAPI = WXAPIFactory.createWXAPI(this, Constants.WECHAT_APP_KEY, false);
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

    @OnClick({R.id.send_text, R.id.send_img, R.id.send_appbrand, R.id.send_webpage,
    R.id.send_video, R.id.send_appdata, R.id.send_music, R.id.unregister, R.id.get_token})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.send_text:
                mPresenter.shareText(mIWXAPI);
                break;

            case R.id.send_img:
                mPresenter.shareImage(mIWXAPI);
                break;

            case R.id.send_music:
                mPresenter.shareMusic(mIWXAPI);
                break;

            case R.id.send_video:
                mPresenter.shareVideo(mIWXAPI);
                break;

            case R.id.send_webpage:
                mPresenter.shareWebPage(mIWXAPI);
                break;

            case R.id.send_appbrand:
                mPresenter.shareMiniProgram(mIWXAPI);
                break;

            case R.id.send_appdata:
                mPresenter.openCamera();
                break;


            case R.id.get_token:
                mPresenter.getToken(mIWXAPI);
                break;

            case R.id.unregister:
                mPresenter.unregister(mIWXAPI);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0x101:
                if (RESULT_OK == resultCode) {
                    mPresenter.shareAppData(mIWXAPI);
                }
                break;

            default:
                break;
        }
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, WeChatShareActivity.class);
        context.startActivity(intent);
    }
}
