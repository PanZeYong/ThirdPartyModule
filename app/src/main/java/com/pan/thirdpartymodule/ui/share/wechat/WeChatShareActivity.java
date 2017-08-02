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

    @OnClick({R.id.send_text, R.id.send_img, R.id.send_emoji, R.id.send_appbrand, R.id.send_webpage,
    R.id.send_video, R.id.send_appdata, R.id.send_music})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.send_text:
                mPresenter.sendText(mIWXAPI);
                break;

            case R.id.send_img:
                mPresenter.sendImage(mIWXAPI);
                break;

            case R.id.send_music:
                mPresenter.sendMusic(mIWXAPI);
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
