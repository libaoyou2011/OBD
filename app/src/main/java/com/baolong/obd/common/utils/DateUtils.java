package com.baolong.obd.common.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_Default = "yyyy-MM-dd";

    private static final long year = 32140800000L;
    private static final long month = 2678400000L;
    private static final long day = 86400000L;
    private static final long hour = 3600000L;
    private static final long minute = 60000L;
    public static final SimpleDateFormat simpleDateFormatWithSplit = new SimpleDateFormat("yyyy-MM-dd", Locale.PRC);
    public static final SimpleDateFormat simpleDateFormatWithSplitYM = new SimpleDateFormat("yyyy-MM", Locale.PRC);

    public static Date checkDate(Date paramDate1, Date paramDate2) {
        Calendar localCalendar1 = Calendar.getInstance();
        localCalendar1.setTime(paramDate1);
        int i = localCalendar1.get(2);
        Calendar localCalendar2 = Calendar.getInstance();
        localCalendar2.setTime(paramDate2);
        Date localDate = paramDate2;
        if (localCalendar2.get(2) != i) {
            if (paramDate2.getTime() > paramDate1.getTime()) {
                localCalendar2.add(2, 0);
                localCalendar2.set(5, 1);
                return localCalendar2.getTime();
            }
            localCalendar1.add(2, 0);
            localCalendar1.set(5, 0);
            localDate = localCalendar1.getTime();
        }
        return localDate;
    }

    public static String date2Str(Date paramDate) {
        return date2Str(paramDate, "yyyy-MM-dd HH:mm:ss");
    }

    public static String date2Str(Date paramDate, String paramString) {
        if (paramDate == null) {
            return null;
        }
        String str;
        if (paramString != null) {
            str = paramString;
            if (paramString.length() != 0) {
            }
        } else {
            str = "yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(str).format(paramDate);
    }

    public static Date dateAddDay(Date paramDate, int paramInt) {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(paramDate);
        localCalendar.add(5, paramInt);
        return localCalendar.getTime();
    }

    public static Date dateAddMonth(Date paramDate, int paramInt) {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(paramDate);
        localCalendar.add(2, paramInt);
        return localCalendar.getTime();
    }

    public static String dateTimeToString(Date paramDate) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(paramDate);
    }

    public static String dateTimeToString(Date paramDate, String paramString) {
        return new SimpleDateFormat(paramString).format(paramDate);
    }

    public static String dateTimeToStringWithHM(Date paramDate) {
        return new SimpleDateFormat("HH:mm").format(paramDate);
    }

    public static String dateToChinaString(Date paramDate) {
        return new SimpleDateFormat("MM月dd日").format(paramDate);
    }

    public static String dateToString(Date paramDate) {
        return new SimpleDateFormat("yyyy.MM.dd").format(paramDate);
    }

    public static String dateToString(Date paramDate, String paramString) {
        return new SimpleDateFormat(paramString).format(paramDate);
    }

    public static String dateToString2(Date paramDate) {
        return new SimpleDateFormat("yyyy-MM-dd").format(paramDate);
    }

    public static String dateToStringTime(Date paramDate) {
        return new SimpleDateFormat("HH:mm:SS").format(paramDate);
    }

    public static Date getCurrentDateTime(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Object localObject = Calendar.getInstance();
        ((Calendar) localObject).set(11, paramInt1);
        ((Calendar) localObject).set(13, paramInt3);
        ((Calendar) localObject).set(12, paramInt2);
        ((Calendar) localObject).set(14, paramInt4);
        localObject = new Date(((Calendar) localObject).getTimeInMillis());
        try {
            return localSimpleDateFormat.parse(localSimpleDateFormat.format((Date) localObject));
        } catch (ParseException localParseException) {
            localParseException.printStackTrace();
        }
        return null;
    }

    public static String getDateToString(long paramLong, String paramString) {
        Date localDate = new Date(paramLong);
        return new SimpleDateFormat(paramString).format(localDate);
    }

    public static int getDayForWeek(String paramString) {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setFirstDayOfWeek(1);
        try {
            localCalendar.setTime(localSimpleDateFormat.parse(paramString));
            return localCalendar.get(7);
        } catch (ParseException parseException) {

        }
        return 0;
    }

    public static String getMonthFirstDay(Date paramDate) {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(paramDate);
        localCalendar.add(2, 0);
        localCalendar.set(5, 1);
        return localSimpleDateFormat.format(localCalendar.getTime());
    }

    public static String getMonthLastDay(Date paramDate) {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(paramDate);
        localCalendar.add(2, 1);
        localCalendar.set(5, 0);
        return localSimpleDateFormat.format(localCalendar.getTime());
    }

//    public static String getTimeFormatText(Date paramDate) {
//        if (paramDate == null) {
//            return null;
//        }
//        long l = new Date().getTime() - paramDate.getTime();
//        StringBuilder localStringBuilder;
//        if (l > 32140800000L) {
//            l /= 32140800000L;
//            localStringBuilder = new StringBuilder();
//            localStringBuilder.append(l);
//            paramDate = "年前";
//        }
//        for (; ; ) {
//            localStringBuilder.append(paramDate);
//            return localStringBuilder.toString();
//            if (l > 2678400000L) {
//                l /= 2678400000L;
//                localStringBuilder = new StringBuilder();
//                localStringBuilder.append(l);
//                paramDate = "个月前";
//            } else if (l > 86400000L) {
//                l /= 86400000L;
//                localStringBuilder = new StringBuilder();
//                localStringBuilder.append(l);
//                paramDate = "天前";
//            } else if (l > 3600000L) {
//                l /= 3600000L;
//                localStringBuilder = new StringBuilder();
//                localStringBuilder.append(l);
//                paramDate = "个小时前";
//            } else {
//                if (l <= 60000L) {
//                    break;
//                }
//                l /= 60000L;
//                localStringBuilder = new StringBuilder();
//                localStringBuilder.append(l);
//                paramDate = "分钟前";
//            }
//        }
//        return "刚刚";
//    }

    public static ArrayList<String> getTimeInterval(Date paramDate) {
        ArrayList localArrayList = new ArrayList();
        Calendar localCalendar = Calendar.getInstance();
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        localCalendar.setTime(paramDate);
        localCalendar.setFirstDayOfWeek(1);
        int i = localCalendar.get(7);
        localCalendar.add(5, localCalendar.getFirstDayOfWeek() - i);
        i = 0;
        while (i < 7) {
            localArrayList.add(localSimpleDateFormat.format(localCalendar.getTime()));
            localCalendar.add(5, 1);
            i += 1;
        }
        return localArrayList;
    }

    public static int getWeek(Date paramDate) {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(paramDate);
        int i = localCalendar.get(4);
        localCalendar.get(7);
        return i;
    }

    public static String getWeekOfDate(Date paramDate) {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(paramDate);
        int j = localCalendar.get(7) - 1;
        int i = j;
        if (j < 0) {
            i = 0;
        }
        return new String[]{"星期天", "星期一", "星期二", "星期三", "想起四", "想起五", "想起六"}[i];
    }

//    public static boolean isFormatDate(String paramString) {
//        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        new Date();
//        if (paramString != null) {
//        }
//        try {
//            localSimpleDateFormat.parse(paramString);
//            return true;
//        } catch (ParseException paramString) {
//            for (; ; ) {
//            }
//        }
//        return false;
//    }

    public static Date str2Date(String paramString) {
        return str2Date(paramString, null);
    }

    public static Date str2Date(String paramString1, String paramString2) {
        if (!TextUtils.isEmpty(paramString1) ){
            String format;
            if (!TextUtils.isEmpty(paramString2)){
                format = paramString2;
            } else {
                format = FORMAT;
            }

            try {
                return new SimpleDateFormat(format).parse(paramString1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Date stringToDate(String paramString) {
        return stringToDate(paramString, "yyyy.MM.dd");
    }

    public static Date stringToDate(String paramString1, String paramString2) {
        Date localDate = new Date();
        if (paramString1.matches("\\d{13}")) {
            localDate.setTime(Long.parseLong(paramString1));
            return localDate;
        }
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString2);
        try {
            localDate = localSimpleDateFormat.parse(paramString1);
            return localDate;
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        return localDate;
    }

    public static Date stringToDateNoTips(String paramString1, String paramString2) {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString2);
        Date localDate = new Date();
        if (paramString1.matches("\\d{13}")) {
            localDate.setTime(Long.parseLong(paramString1));
            return localDate;
        }
        try {
            localDate = localSimpleDateFormat.parse(paramString1);
            return localDate;
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        return localDate;
    }
}
