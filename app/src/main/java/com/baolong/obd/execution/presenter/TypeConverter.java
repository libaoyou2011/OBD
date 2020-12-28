//package com.baolong.edsp.execution.presenter;
//
//import android.graphics.Matrix;
//import android.media.ExifInterface;
//import android.graphics.BitmapFactory;
//import android.graphics.BitmapFactory.Options;
//import java.io.OutputStream;
//import android.graphics.Bitmap.CompressFormat;
//import java.io.ByteArrayOutputStream;
//import android.graphics.Bitmap;
//import Decoder.BASE64Encoder;
//import android.util.Base64;
//import java.io.IOException;
//import java.io.FileInputStream;
//import java.io.File;
//
//public class TypeConverter
//{
//    public TypeConverter() {
//        super();
//    }
//
//    public static String GetImageStr(String s) {
//        Label_0050: {
//            FileInputStream fileInputStream = null;
//            try {
//                s = (String)new File(s);
//                fileInputStream = new FileInputStream((File)s);
//                s = (String)(Object)new byte[(int)((File)s).length()];
//                final FileInputStream fileInputStream2 = fileInputStream;
//                final String s2 = s;
//                fileInputStream2.read((byte[])(Object)s2);
//                final FileInputStream fileInputStream3 = fileInputStream;
//                fileInputStream3.close();
//                break Label_0050;
//            }
//            catch (IOException ex) {
//                s = (String)(Object)new byte[0];
//            }
//            while (true) {
//                try {
//                    final FileInputStream fileInputStream2 = fileInputStream;
//                    final String s2 = s;
//                    fileInputStream2.read((byte[])(Object)s2);
//                    final FileInputStream fileInputStream3 = fileInputStream;
//                    fileInputStream3.close();
//                    return Base64.encodeToString((byte[])(Object)s, 11);
//                    final IOException ex;
//                    ex.printStackTrace();
//                    return Base64.encodeToString((byte[])(Object)s, 11);
//                }
//                catch (IOException ex) {
//                    continue;
//                }
//                break;
//            }
//        }
//    }
//
//    public static String GetImageStrTwo(String s) {
//        final String s2 = null;
//        Label_0038: {
//            FileInputStream fileInputStream = null;
//            try {
//                fileInputStream = new FileInputStream(s);
//                s = (String)(Object)new byte[fileInputStream.available()];
//                final FileInputStream fileInputStream2 = fileInputStream;
//                final String s3 = s;
//                fileInputStream2.read((byte[])(Object)s3);
//                final FileInputStream fileInputStream3 = fileInputStream;
//                fileInputStream3.close();
//                break Label_0038;
//            }
//            catch (IOException ex) {
//                s = s2;
//            }
//            while (true) {
//                try {
//                    final FileInputStream fileInputStream2 = fileInputStream;
//                    final String s3 = s;
//                    fileInputStream2.read((byte[])(Object)s3);
//                    final FileInputStream fileInputStream3 = fileInputStream;
//                    fileInputStream3.close();
//                    return new BASE64Encoder().encode((byte[])(Object)s);
//                    final IOException ex;
//                    ex.printStackTrace();
//                    return new BASE64Encoder().encode((byte[])(Object)s);
//                }
//                catch (IOException ex) {
//                    continue;
//                }
//                break;
//            }
//        }
//    }
//
//    public static String bitmapToBase64(Bitmap o) {
//        ByteArrayOutputStream byteArrayOutputStream = null;
//        Label_0121: {
//            if (o != null) {
//                Label_0097: {
//                    ByteArrayOutputStream byteArrayOutputStream2;
//                    try {
//                        byteArrayOutputStream2 = (byteArrayOutputStream = new ByteArrayOutputStream());
//                        try {
//                            final Object o2 = o;
//                            final Bitmap.CompressFormat bitmap$CompressFormat = Bitmap.CompressFormat.JPEG;
//                            final int n = 100;
//                            final ByteArrayOutputStream byteArrayOutputStream3 = byteArrayOutputStream2;
//                            ((Bitmap)o2).compress(bitmap$CompressFormat, n, (OutputStream)byteArrayOutputStream3);
//                            byteArrayOutputStream = byteArrayOutputStream2;
//                            final ByteArrayOutputStream byteArrayOutputStream4 = byteArrayOutputStream2;
//                            byteArrayOutputStream4.flush();
//                            byteArrayOutputStream = byteArrayOutputStream2;
//                            final ByteArrayOutputStream byteArrayOutputStream5 = byteArrayOutputStream2;
//                            byteArrayOutputStream5.close();
//                            byteArrayOutputStream = byteArrayOutputStream2;
//                            final ByteArrayOutputStream byteArrayOutputStream6 = byteArrayOutputStream2;
//                            final byte[] array = byteArrayOutputStream6.toByteArray();
//                            final int n2 = 0;
//                            Base64.encodeToString(array, n2);
//                            final ByteArrayOutputStream byteArrayOutputStream7 = byteArrayOutputStream = byteArrayOutputStream2;
//                        }
//                        catch (IOException ex) {}
//                    }
//                    catch (IOException ex) {
//                        byteArrayOutputStream2 = null;
//                    }
//                    finally {
//                        byteArrayOutputStream = null;
//                        break Label_0097;
//                    }
//                    try {
//                        final Object o2 = o;
//                        final Bitmap.CompressFormat bitmap$CompressFormat = Bitmap.CompressFormat.JPEG;
//                        final int n = 100;
//                        final ByteArrayOutputStream byteArrayOutputStream3 = byteArrayOutputStream2;
//                        ((Bitmap)o2).compress(bitmap$CompressFormat, n, (OutputStream)byteArrayOutputStream3);
//                        byteArrayOutputStream = byteArrayOutputStream2;
//                        final ByteArrayOutputStream byteArrayOutputStream4 = byteArrayOutputStream2;
//                        byteArrayOutputStream4.flush();
//                        byteArrayOutputStream = byteArrayOutputStream2;
//                        final ByteArrayOutputStream byteArrayOutputStream5 = byteArrayOutputStream2;
//                        byteArrayOutputStream5.close();
//                        byteArrayOutputStream = byteArrayOutputStream2;
//                        final ByteArrayOutputStream byteArrayOutputStream6 = byteArrayOutputStream2;
//                        final byte[] array = byteArrayOutputStream6.toByteArray();
//                        final int n2 = 0;
//                        Base64.encodeToString(array, n2);
//                        byteArrayOutputStream = byteArrayOutputStream2;
//                        break Label_0121;
//                        Label_0094: {
//                            return null;
//                        }
//                        byteArrayOutputStream = byteArrayOutputStream2;
//                        final IOException ex;
//                        ex.printStackTrace();
//                        // iftrue(Label_0094:, byteArrayOutputStream2 == null)
//                        try {
//                            byteArrayOutputStream2.flush();
//                            byteArrayOutputStream2.close();
//                        }
//                        catch (IOException ex2) {
//                            ex2.printStackTrace();
//                        }
//                        return null;
//                    }
//                    finally {}
//                }
//                if (byteArrayOutputStream != null) {
//                    try {
//                        byteArrayOutputStream.flush();
//                        byteArrayOutputStream.close();
//                    }
//                    catch (IOException ex3) {
//                        ex3.printStackTrace();
//                    }
//                }
//            }
//            else {
//                o = null;
//            }
//        }
//        if (byteArrayOutputStream != null) {
//            try {
//                byteArrayOutputStream.flush();
//                byteArrayOutputStream.close();
//                return (String)o;
//            }
//            catch (IOException ex4) {
//                ex4.printStackTrace();
//            }
//        }
//        return (String)o;
//    }
//
//    private static int calculateInSampleSize(final BitmapFactory.Options bitmapFactory$Options, int round, final int n) {
//        final int outHeight = bitmapFactory$Options.outHeight;
//        final int outWidth = bitmapFactory$Options.outWidth;
//        int round2 = 1;
//        if (outHeight > n || outWidth > round) {
//            round2 = Math.round(outHeight / (float)n);
//            round = Math.round(outWidth / (float)round);
//            if (round2 < round) {
//                return round;
//            }
//        }
//        return round2;
//    }
//
//    public static byte[] getSmallBitmapToBaos(String byteArray) {
//        final BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(byteArray, options);
//        options.inSampleSize = calculateInSampleSize(options, 480, 800);
//        options.inJustDecodeBounds = false;
//        final Bitmap decodeFile = BitmapFactory.decodeFile(byteArray, options);
//        if (decodeFile == null) {
//            return null;
//        }
//        final Bitmap rotateBitmap = rotateBitmap(decodeFile, readPictureDegree(byteArray));
//        ByteArrayOutputStream byteArrayOutputStream2;
//        try {
//            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            try {
//                rotateBitmap.compress(Bitmap.CompressFormat.JPEG, 30, (OutputStream)byteArrayOutputStream);
//                byteArray = (String)(Object)byteArrayOutputStream.toByteArray();
//                if (byteArrayOutputStream != null) {
//                    try {
//                        byteArrayOutputStream.close();
//                        return (byte[])(Object)byteArray;
//                    }
//                    catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//                return (byte[])(Object)byteArray;
//            }
//            finally {}
//        }
//        finally {
//            byteArrayOutputStream2 = null;
//        }
//        if (byteArrayOutputStream2 != null) {
//            try {
//                byteArrayOutputStream2.close();
//            }
//            catch (IOException ex2) {
//                ex2.printStackTrace();
//            }
//        }
//    }
//
//    private static int readPictureDegree(final String s) {
//        try {
//            switch (new ExifInterface(s).getAttributeInt("Orientation", 1)) {
//                case 8: {
//                    return 270;
//                }
//                case 6: {
//                    return 90;
//                }
//                case 3: {
//                    return 180;
//                }
//            }
//        }
//        catch (IOException ex) {
//            ex.printStackTrace();
//            return 0;
//        }
//        return 0;
//    }
//
//    private static Bitmap rotateBitmap(final Bitmap bitmap, final int n) {
//        if (bitmap == null) {
//            return null;
//        }
//        final int width = bitmap.getWidth();
//        final int height = bitmap.getHeight();
//        final Matrix matrix = new Matrix();
//        matrix.postRotate((float)n);
//        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
//    }
//}
