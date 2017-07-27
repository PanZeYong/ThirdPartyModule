package com.pan.thirdpartymodule.util;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Author : Pan
 * Date : 27/07/2017
 */

public class Utils {
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
}
