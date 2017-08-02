package com.pan.thirdpartymodule.ui.share.wechat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.pan.thirdpartymodule.Constants;
import com.pan.thirdpartymodule.R;
import com.pan.thirdpartymodule.util.Utils;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMusicObject;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import butterknife.BindString;

/**
 * Author : Pan
 * Date : 24/07/2017
 */

public class WeChatSharePresenterImpl extends WeChatSharePresenter{
    private WeChatView mView;

    private Context mContext;

    @BindString(R.string.share_image_success)
    protected String SHARE_IMAGE_SUCCESS;

    @BindString(R.string.share_image_fail)
    protected String SHARE_IMAGE_FAIL;

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

    @Override
    public void sendText(IWXAPI iwxapi) {
        WXTextObject textObject = new WXTextObject();
        textObject.text = "微信分享文本示例";

        WXMediaMessage message = new WXMediaMessage();
        message.mediaObject = textObject;
        message.title = "微信分享";
        message.description = "微信分享有很多类型，文本分享是其中一种";
        message.mediaTagName = "WeChat";
        message.messageAction = "com.pan.thirdpartymodule";
        message.messageExt = "Ext";

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text");
        req.message = message;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;

        iwxapi.sendReq(req);
    }

    /**
     * 图片来源：resource、file、network
     * @param iwxapi
     */
    @Override
    public void sendImage(IWXAPI iwxapi) {
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.picture);
        WXImageObject imageObject = new WXImageObject(bitmap);

        WXMediaMessage message = new WXMediaMessage();
        message.mediaObject = imageObject;
        message.title = "微信分享";
        message.description = "微信分享有很多类型，图片分享是其中一种";

        Bitmap thumbBitmap = Utils.compressImage(bitmap);
        bitmap.recycle();
        message.thumbData = Utils.bmpToByteArray(thumbBitmap, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");
        req.message = message;
        req.scene = SendMessageToWX.Req.WXSceneSession;

        Toast.makeText(mContext, iwxapi.sendReq(req) ? SHARE_IMAGE_SUCCESS : SHARE_IMAGE_FAIL,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void sendMusic(IWXAPI iwxapi) {
        WXMusicObject musicObject = new WXMusicObject();
        musicObject.musicUrl = "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3";
//        musicObject.musicUrl = "http://www.170mv.com/kw/other.web.ra01.sycdn.kuwo.cn/resource/n3/64/1/3432690767.mp3";

        WXMediaMessage message = new WXMediaMessage();
        message.mediaObject = musicObject;
        message.title = "分享音乐";
        message.description = "微信分享音乐示例";

        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.send_music_thumb);
        Bitmap thumbBitmap = Utils.compressImage(bitmap);
        bitmap.recycle();
        message.thumbData = Utils.bmpToByteArray(thumbBitmap, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("music");
        req.message = message;
        req.scene = SendMessageToWX.Req.WXSceneFavorite;

        Toast.makeText(mContext, iwxapi.sendReq(req) ? "分享音乐成功" : "分享音乐失败",
                Toast.LENGTH_LONG).show();

    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
