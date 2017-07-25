package com.pan.thirdpartymodule.ui.share.wechat;

import android.content.Context;
import android.content.Intent;

import com.pan.thirdpartymodule.R;
import com.pan.thirdpartymodule.base.BaseActivity;
import com.pan.thirdpartymodule.base.BasePresenter;

/**
 * Author : Pan
 * Date : 25/07/2017
 */

public class WeChatShareActivity extends BaseActivity {
    @Override
    protected int initContentView() {
        return R.layout.activity_send_message_to_wechat;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void registerListener() {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected void release() {

    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, WeChatShareActivity.class);
        context.startActivity(intent);
    }
}
