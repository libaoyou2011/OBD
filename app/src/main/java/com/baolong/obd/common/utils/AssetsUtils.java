package com.baolong.obd.common.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AssetsUtils {
    private static final String TAG = AssetsUtils.class.getSimpleName();

    /**
     * 打开Assets文件夹下的文件内容
     *
     * @param context
     * @param file    相对于Assets目录的路径，可以直接的文件名
     * @return
     */
    public static String OpenAssetsFile(Context context, String file) {
        return OpenAssetsFile(context, file, "UTF-8");
    }

    /**
     * 打开Assets文件夹下的文件内容
     *
     * @param context
     * @param file     相对于Assets目录的路径，可以直接的文件名
     * @param encoding
     * @return
     */
    public static String OpenAssetsFile(Context context, String file, String encoding) {

        InputStream in = null;

        try {
            in = context.getAssets().open(file);
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            String txt = new String(buffer, encoding);
            return txt;
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception ex) {
                    Log.e(TAG, "无法关闭文件：" + ex.getMessage(), ex);
                }
            }
        }

        return "";
    }

    /**
     * 读出Assest中的文件，String数组
     *
     * @param context
     * @param file
     * @param encoding
     * @return
     */
    public static List<String> OpenAssetsFileToLines(Context context, String file, String encoding) {

        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;

        List<String> lines = new ArrayList<String>();

        try {
            inputStream = context.getAssets().open(file);
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line.trim());
                }
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        } finally {
            try {
                if (inputStreamReader != null)
                    inputStreamReader.close();
                if (inputStream != null)
                    inputStream.close();
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }

        return lines;
    }
}
