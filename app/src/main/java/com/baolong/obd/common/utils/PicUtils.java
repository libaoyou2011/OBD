package com.baolong.obd.common.utils;

import android.graphics.Rect;

import java.io.InputStream;

import android.graphics.BitmapFactory;

import java.io.FileInputStream;

import android.graphics.Bitmap;

import java.io.IOException;
import java.io.FileOutputStream;

import android.util.Log;

import java.io.OutputStream;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class PicUtils {
    public PicUtils() {
        super();
    }

    private static int calculateInSampleSize(final BitmapFactory.Options bitmapFactory$Options, int round, final int n) {
        final int outHeight = bitmapFactory$Options.outHeight;
        final int outWidth = bitmapFactory$Options.outWidth;
        int round2 = 1;
        if (outHeight > n || outWidth > round) {
            round2 = Math.round(outHeight / (float) n);
            round = Math.round(outWidth / (float) round);
            if (round2 < round) {
                return round;
            }
        }
        return round2;
    }

    public static File compressImage(final String s) {
        try {
            final Bitmap bitmap = getBitmap(s);
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final Bitmap.CompressFormat jpeg = Bitmap.CompressFormat.JPEG;
            int n = 100;
            bitmap.compress(jpeg, 100, (OutputStream) byteArrayOutputStream);
            final StringBuilder sb = new StringBuilder();
            sb.append("before is ");
            sb.append(byteArrayOutputStream.toByteArray().length);
            Log.i("dj", sb.toString());
            while (byteArrayOutputStream.toByteArray().length / 1024 > 20 && n > 0) {
                byteArrayOutputStream.reset();
                n -= 10;
                bitmap.compress(Bitmap.CompressFormat.JPEG, n, (OutputStream) byteArrayOutputStream);
            }
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("after is ");
            sb2.append(byteArrayOutputStream.toByteArray().length);
            Log.i("dj", sb2.toString());
            final FileOutputStream fileOutputStream = new FileOutputStream(s);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
            final File file = new File(s);
            final StringBuilder sb3 = new StringBuilder();
            sb3.append("file is ");
            sb3.append(file.length());
            Log.i("dj", sb3.toString());
            return file;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Bitmap getBitmap(final String path) throws IOException {
        final File file = new File(path);
        if (file == null) {
            return null;
        }
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        final FileInputStream fileInputStream = new FileInputStream(file);
        BitmapFactory.decodeStream((InputStream) fileInputStream, (Rect) null, options);
        fileInputStream.close();

        final int calculateInSampleSize = calculateInSampleSize(options, 1280, 1280);
        final BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inSampleSize = calculateInSampleSize;
        final FileInputStream fileInputStream2 = new FileInputStream(file);
        final Bitmap decodeStream = BitmapFactory.decodeStream((InputStream) fileInputStream2, (Rect) null, options2);
        fileInputStream2.close();
        return decodeStream;
    }
}
