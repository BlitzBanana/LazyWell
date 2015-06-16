package com.lazywell.android.puydufou.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by victor on 16/06/2015.
 */
public class BitmapUtils {
    public static byte[] getBytes(Bitmap bitmap) {
        if(bitmap == null)
            return new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public static Bitmap getImage(byte[] image) {
        if(image == null || image.length == 0)
            return null;
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
