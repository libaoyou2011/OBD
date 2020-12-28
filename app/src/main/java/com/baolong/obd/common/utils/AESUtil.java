package com.baolong.obd.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.NoSuchPaddingException;

import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;

import java.security.InvalidKeyException;

import javax.crypto.IllegalBlockSizeException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.util.Base64;

public class AESUtil {
    private static final String ALGORITHM = "AES";
    private static final String CHARSET_NAME = "utf-8";
    private static final String CIPHER = "AES/ECB/PKCS7Padding";
    private static final String DEFAULT_V = "0";
    private static final String HEX = "0123456789ABCDEF";
    private static final int KEY_LENGTH = 16;
    private static final String PASSWORD = "abcdefgabcdefhzx";

    public static byte[] Base64ToByte(final String s) {
        return Base64.decode(s, 8);
    }

    public static String StringToBase64(final byte[] array) throws UnsupportedEncodingException {
        return new String(Base64.encode(array, 8), "utf-8").replaceAll("\n", "");
    }

    public static String decrypt(final String s) throws IOException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return new String(decrypt(toMakekey("abcdefgabcdefhzx", 16, "0").getBytes(), Base64ToByte(s)), "utf-8");
    }

    private static byte[] decrypt(final byte[] array, final byte[] array2) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        final SecretKeySpec secretKeySpec = new SecretKeySpec(array, "AES");
        final Cipher instance = Cipher.getInstance("AES/ECB/PKCS7Padding");
        instance.init(2, secretKeySpec);
        return instance.doFinal(array2);
    }

    public static String encrypt(final String s) throws UnsupportedEncodingException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return StringToBase64(encrypt(toMakekey("abcdefgabcdefhzx", 16, "0").getBytes(), s.getBytes("utf-8")));
    }

    private static byte[] encrypt(final byte[] array, final byte[] array2) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        final SecretKeySpec secretKeySpec = new SecretKeySpec(array, "AES");
        final Cipher instance = Cipher.getInstance("AES/ECB/PKCS7Padding");
        instance.init(1, secretKeySpec);
        return instance.doFinal(array2);
    }

    private static String toMakekey(String string, final int n, final String s) {
        int n2 = string.length();
        String s2 = string;
        if (n2 < n) {
            while (true) {
                s2 = string;
                if (n2 >= n) {
                    break;
                }
                final StringBuffer sb = new StringBuffer();
                sb.append(string);
                sb.append(s);
                string = sb.toString();
                n2 = string.length();
            }
        }
        return s2;
    }
}

