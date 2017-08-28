package com.pan.thirdpartymodule.ui.share.wechat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.pan.thirdpartymodule.Constants;
import com.pan.thirdpartymodule.R;
import com.pan.thirdpartymodule.util.Utils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.modelmsg.WXMusicObject;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import java.io.File;

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
        WeChatScanQrCodeActivity.startActivity(mContext);
    }

    @Override
    public void shareText(IWXAPI iwxapi) {
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
    public void shareImage(IWXAPI iwxapi) {
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
    public void shareMusic(IWXAPI iwxapi) {
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

    @Override
    public void shareVideo(IWXAPI iwxapi) {
        WXVideoObject videoObject = new WXVideoObject();
        videoObject.videoUrl = "http://v.youku.com/v_show/id_XMjkzOTAyMjg5Mg==.html?spm=a2hww.20023042.m_226600.5~5!2~5~5~5~5~A";

        WXMediaMessage message = new WXMediaMessage();
        message.mediaObject = videoObject;
        message.title = "分享视频";
        message.description = "微信分享视频";

        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.send_music_thumb);
        Bitmap thumbBitmap = Utils.compressImage(bitmap);
        bitmap.recycle();
        message.thumbData = Utils.bmpToByteArray(thumbBitmap, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("video");
        req.message = message;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;

        Toast.makeText(mContext, iwxapi.sendReq(req) ? "分享视频成功" : "分享视频失败",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void shareWebPage(IWXAPI iwxapi) {
        WXWebpageObject  webPageObject = new WXWebpageObject();
        webPageObject.webpageUrl = "http://www.qq.com";

        WXMediaMessage message = new WXMediaMessage();
        message.mediaObject = webPageObject;
        message.title = "分享网页";
        message.description = "微信分享网页";

        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.send_music_thumb);
        Bitmap thumbBitmap = Utils.compressImage(bitmap);
        bitmap.recycle();
        message.thumbData = Utils.bmpToByteArray(thumbBitmap, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = message;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;

        Toast.makeText(mContext, iwxapi.sendReq(req) ? "分享网页成功" : "分享网页失败",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void shareMiniProgram(IWXAPI iwxapi) {
        WXMiniProgramObject miniProgramObject = new WXMiniProgramObject();
        miniProgramObject.webpageUrl = "http://www.qq.com";
        miniProgramObject.userName = "gh_d43f693ca31f";
        miniProgramObject.path = "pages/play/index?cid=fvue88y1fsnk4w2&ptag=vicyao&seek=3219";

        WXMediaMessage message = new WXMediaMessage();
        message.mediaObject = miniProgramObject;
        message.title = "分享小程序";
        message.description = "微信分享小程序";

        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.send_music_thumb);
        Bitmap thumbBitmap = Utils.compressImage(bitmap);
        bitmap.recycle();
        message.thumbData = Utils.bmpToByteArray(thumbBitmap, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = message;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;

        Toast.makeText(mContext, iwxapi.sendReq(req) ? "分享小程序成功" : "分享小程序失败",
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void shareAppData(IWXAPI iwxapi) {
        WXAppExtendObject appExtendObject = new WXAppExtendObject();
        appExtendObject.filePath = Utils.getPath();
        appExtendObject.extInfo = "发送照片";


        WXMediaMessage message = new WXMediaMessage();
        message.mediaObject = appExtendObject;
        message.title = "APP 数据分享";
        message.description = "微信分享 APP 数据";

        Bitmap bitmap = BitmapFactory.decodeFile(Utils.getPath());
        Bitmap thumbBitmap = Utils.compressImage(bitmap);
        bitmap.recycle();
        message.thumbData = Utils.bmpToByteArray(thumbBitmap, true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.message = message;
        req.transaction = buildTransaction("appdata");
        req.scene = SendMessageToWX.Req.WXSceneTimeline;

        Toast.makeText(mContext, iwxapi.sendReq(req) ? "分享 APP 数据成功" : "分享 APP 数据失败",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void getToken(IWXAPI iwxapi) {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "none";
        Toast.makeText(mContext, iwxapi.sendReq(req) ? "获取 Token 成功" : "获取 Token 失败",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void unregister(IWXAPI iwxapi) {
        iwxapi.unregisterApp();
    }

    @Override
    public void openCamera() {
        String dir = Utils.getSdPath() + "/share/";

        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }

        Utils.takePhoto((Activity) mContext, dir, "app_data", 0x101);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
