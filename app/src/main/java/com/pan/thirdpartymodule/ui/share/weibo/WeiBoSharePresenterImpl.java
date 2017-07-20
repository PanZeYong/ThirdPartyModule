package com.pan.thirdpartymodule.ui.share.weibo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;

import com.pan.thirdpartymodule.R;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.sina.weibo.sdk.utils.Utility;

/**
 * Author : Pan
 * Date : 20/07/2017
 */

public class WeiBoSharePresenterImpl extends WeiBoSharePresenter {
    private WeiBoShareView mView;

    private Context mContext;

    public WeiBoSharePresenterImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void attachView(@Nullable WeiBoShareView view) {
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
    public void sendMessage(WbShareHandler wbShareHandler, boolean hasText, boolean hasImage) {
        sendMultiMessage(wbShareHandler, hasText, hasImage);
    }

    /**
     * 创建文本消息对象。
     *
     * @return 文本消息对象。
     */
    private TextObject getTextObj() {
        TextObject textObject = new TextObject();
        textObject.text = mView.getText();
        textObject.title = "xxxx";
        textObject.actionUrl = "http://www.baidu.com";
        return textObject;
    }

    /**
     * 创建图片消息对象。
     *
     * @return 图片消息对象。
     */
    private ImageObject getImageObj() {
        ImageObject imageObject = new ImageObject();
        imageObject.setImageObject(mView.getBitmap());
        return imageObject;
    }

    /**
     * 创建多媒体（网页）消息对象。
     *
     * @return 多媒体（网页）消息对象。
     */
    private WebpageObject getWebpageObj() {
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title = "测试title";
        mediaObject.description = "测试描述";
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_weibo);
        // 设置 Bitmap 类型的图片到视频对象里         设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        mediaObject.setThumbImage(bitmap);
        mediaObject.actionUrl = "http://news.sina.com.cn/c/2013-10-22/021928494669.shtml";
        mediaObject.defaultText = "Webpage 默认文案";
        return mediaObject;
    }

    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。
     */
    private void sendMultiMessage(WbShareHandler wbShareHandler, boolean hasText, boolean hasImage) {
        WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
        if (hasText) {
            weiboMultiMessage.textObject = getTextObj();
        }

        if (hasImage) {
            weiboMultiMessage.imageObject = getImageObj();
        }

        weiboMultiMessage.mediaObject = getWebpageObj();
        wbShareHandler.shareMessage(weiboMultiMessage, true);
    }
}
