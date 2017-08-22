package com.pan.thirdpartymodule.ui.share;

import android.content.Context;
import android.support.annotation.Nullable;

import com.pan.thirdpartymodule.ui.share.weibo.WeiBoShareActivity;
import com.pan.thirdpartymodule.wxapi.WXEntryActivity;

/**
 * Author : Pan
 * Date : 20/07/2017
 */

public class SharePresenterImpl extends SharePresenter {
    private ShareView mView;

    private Context mContext;

    public SharePresenterImpl(Context context) {
       this.mContext = context;
    }

    @Override
    public void startWeiBoShareActivity() {
        WeiBoShareActivity.startActivity(mContext);
    }

    @Override
    public void startWeChatShareActivity() {
        WXEntryActivity.startActivity(mContext);
    }

    @Override
    public void attachView(@Nullable ShareView view) {
        if (null == view) {
            this.mView = view;
        }
    }

    @Override
    public void detachView() {
        if (null != mView) {
            mView = null;
        }
    }
}
