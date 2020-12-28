package com.baolong.obd.common.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;

import com.baolong.obd.R;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

public class CommonUtils {

    public static float DpToPx(final Context context, final float n) {
        return context.getResources().getDisplayMetrics().density * n;
    }

    public static int dp2px(final Context context, final float n) {
        return (int) (context.getResources().getDisplayMetrics().density * n + 0.5f);
    }

    public static String getAddress(String s, final String s2, final String s3) {
        String s4;
        if (!TextUtils.isEmpty((CharSequence) s) && !TextUtils.isEmpty((CharSequence) s2) && !TextUtils.isEmpty((CharSequence) s3)) {
            if (s.equals(s2)) {
                final StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(s2);
                sb.append("市");
                s = sb.toString();
            } else {
                final StringBuilder sb2 = new StringBuilder();
                sb2.append("");
                sb2.append(s);
                sb2.append("省");
                sb2.append(s2);
                sb2.append("市");
                s = sb2.toString();
            }
            s4 = s;
            if (!s2.equals(s3)) {
                final StringBuilder sb3 = new StringBuilder();
                sb3.append(s);
                sb3.append(s3);
                sb3.append("区");
                return sb3.toString();
            }
        } else {
            s4 = "";
        }
        return s4;
    }

    public static String getCityId(final String s) {
        if (!TextUtils.isEmpty((CharSequence) s) && s.contains("_")) {
            return s.split("_")[0];
        }
        return "";
    }

    public static String getCityName(final String s) {
        if (!TextUtils.isEmpty((CharSequence) s) && s.contains("_")) {
            return s.split("_")[1];
        }
        return "";
    }

    public static double getDoubleOneDecimal(final double n) {
        return new BigDecimal(n).setScale(1, 4).doubleValue();
    }

    public static String getLastTenWord(final String s) {
        if (TextUtils.isEmpty((CharSequence) s)) {
            return "";
        }
        if (s.length() < 10) {
            return s;
        }
        return s.substring(s.length() - 10);
    }

    public static String getNumberFromString(String trim) {
        if (TextUtils.isEmpty((CharSequence) trim)) {
            return "0";
        }
        if (TextUtils.isEmpty((CharSequence) (trim = Pattern.compile("[^0-9]").matcher(trim).replaceAll("").trim()))) {
            trim = "0";
        }
        return trim;
    }

    public static int getRandomColor(final int n) {
        int[] arrayOfInt = new int[4];
        arrayOfInt[0] = R.color.textColor_eab358;
        arrayOfInt[1] = R.color.textColor_51c9c0;
        arrayOfInt[2] = R.color.textColor_ffbf17;
        arrayOfInt[3] = R.color.textColor_ff585c;
        double n2 = Math.random();
        int n3;
        while (true) {
            n3 = (int) (n2 * 4.0);
            if (n != arrayOfInt[n3]) {
                break;
            }
            n2 = Math.random();
        }
        return arrayOfInt[n3];
    }

    public static String listToString(final List<String> list, final String s) {
        if (list == null) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); ++i) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(s);
            }
        }
        return sb.toString();
    }

    public static String readAssetsTxt(final Context context, final String s) {
        try {
            final AssetManager assets = context.getAssets();
            final StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append(".txt");
            final InputStream open = assets.open(sb.toString());
            final byte[] array = new byte[open.available()];
            open.read(array);
            open.close();
            return new String(array, "utf-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return "读取错误，请检查文件名";
        }
    }

    /* 正则表达式，判断是否为Url格式
     * http://www.baidu.com
     * http://210.30.48.14:8080
     * http://210.30.48.14
     */
    public static boolean isUrl(String url) {
        String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(url).matches();
    }

    public static boolean isCarnumberNO(String carnumber) {
        /*
        1.常规车牌号：仅允许以汉字开头，后面可录入六个字符，由大写英文字母和阿拉伯数字组成。如：粤B12345；
        2.武警车牌：允许前两位为大写英文字母，后面可录入五个或六个字符，由大写英文字母和阿拉伯数字组成，其中第三位可录汉字也可录大写英文字母及阿拉伯数字，第三位也可空，如：WJ警00081、WJ京1234J、WJ1234X。
        3.最后一个为汉字的车牌：允许以汉字开头，后面可录入六个字符，前五位字符，由大写英文字母和阿拉伯数字组成，而最后一个字符为汉字，汉字包括“挂”、“学”、“警”、“军”、“港”、“澳”。如：粤Z1234港。
        4.新军车牌：以两位为大写英文字母开头，后面以5位阿拉伯数字组成。如：BA12345。
        */
        //String carnumRegex = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[警京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{0,1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";


        /*
        1、传统车牌
        2、新能源车牌
        */
        String carnumRegex = "^(([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Z](([0-9]{5}[DF])|([DF]([A-HJ-NP-Z0-9])[0-9]{4})))|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Z][A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳使领]))$";

        if (TextUtils.isEmpty(carnumber)) {
            return false;
        } else {
            return carnumber.matches(carnumRegex);
        }

    }

}
