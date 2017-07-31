package com.pan.thirdpartymodule.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.pan.thirdpartymodule.R;

import java.io.ByteArrayOutputStream;

/**
 * Author : Pan
 * Date : 27/07/2017
 */

public class Utils {
    private static final int RESOURCE = 1;
    private static final int PATH = 2;
    private static final int NETWORK = 3;

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 参考链接：http://wuzheng.site/2016/11/29/Android%E5%BE%AE%E5%8D%9A%E3%80%81%E5%BE%AE%E4%BF%A1%
     * E5%88%86%E4%BA%ABSDK%E5%9B%BE%E7%89%87%E5%A4%A7%E5%B0%8F%E9%99%90%E5%88%B6/
     *
     * @param  context
     * @return
     */
    public static Bitmap compressImage(Context context) {
        // 32KB, api doc:http:sinaweibosdk.github.io/weibo_android_sdk/doc/com/sina/weibo/sdk/api/BaseMediaObject.html#setThumbImage(Bitmap)
        int MAX_SIZE_THUMBNAIL_BYTE = 1 << 15;

        // 2MB, api doc: http://sinaweibosdk.github.io/weibo_android_sdk/doc/com/sina/weibo/sdk/api/ImageObject.html#imageData
        int MAX_SIZE_LARGE_BYTE = 1 << 21;

        Bitmap originalImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.picture);
        Bitmap thumbnailImg = originalImg;

        if (thumbnailImg.getByteCount() > MAX_SIZE_THUMBNAIL_BYTE) {
            double scale = Math.sqrt(1.0 * thumbnailImg.getByteCount() / MAX_SIZE_THUMBNAIL_BYTE);
            int scaledW = (int) (thumbnailImg.getWidth() / scale);
            int scaledH = (int) (thumbnailImg.getHeight() / scale);

            thumbnailImg = Bitmap.createScaledBitmap(originalImg, scaledW, scaledH, true);
        }

        // large pic
//        Bitmap largeImg = originalImg;
//        if (largeImg.getByteCount() > MAX_SIZE_LARGE_BYTE) {
//            double scale = Math.sqrt(1.0 * largeImg.getByteCount() / MAX_SIZE_LARGE_BYTE);
//            int scaledW = (int) (largeImg.getWidth() / scale);
//            int scaledH = (int) (largeImg.getHeight() / scale);
//
//            largeImg = Bitmap.createScaledBitmap(originalImg, scaledW, scaledH, true);
//        }

        return thumbnailImg;
    }
}
