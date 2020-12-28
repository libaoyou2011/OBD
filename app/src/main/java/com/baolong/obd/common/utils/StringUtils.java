package com.baolong.obd.common.utils;


public class StringUtils
{
    private StringUtils()
    {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String buffer(String... paramVarArgs)
    {
        StringBuffer localStringBuffer = new StringBuffer();
        int j = paramVarArgs.length;
        int i = 0;
        while (i < j)
        {
            localStringBuffer.append(paramVarArgs[i]);
            i += 1;
        }
        return localStringBuffer.toString();
    }

    public static String builder(String... paramVarArgs)
    {
        StringBuilder localStringBuilder = new StringBuilder();
        int j = paramVarArgs.length;
        int i = 0;
        while (i < j)
        {
            localStringBuilder.append(paramVarArgs[i]);
            i += 1;
        }
        return localStringBuilder.toString();
    }

    public static boolean equals(CharSequence paramCharSequence1, CharSequence paramCharSequence2)
    {
        boolean bool2 = false;
        boolean bool1;
        if (paramCharSequence1 != paramCharSequence2)
        {
            bool1 = bool2;
            if (paramCharSequence1 != null)
            {
                bool1 = bool2;
                if (paramCharSequence2 != null)
                {
                    int j = paramCharSequence1.length();
                    bool1 = bool2;
                    if (j == paramCharSequence2.length())
                    {
                        if (((paramCharSequence1 instanceof String)) && ((paramCharSequence2 instanceof String))) {
                            return paramCharSequence1.equals(paramCharSequence2);
                        }
                        int i = 0;
                        while (i < j)
                        {
                            if (paramCharSequence1.charAt(i) != paramCharSequence2.charAt(i)) {
                                return false;
                            }
                            i += 1;
                        }
                    }
                }
            }
        }
        else
        {
            bool1 = true;
        }
        return bool1;
    }

    public static boolean equalsIgnoreCase(String paramString1, String paramString2)
    {
        boolean bool2 = false;
        boolean bool1;
        if (paramString1 != paramString2)
        {
            bool1 = bool2;
            if (paramString2 != null)
            {
                bool1 = bool2;
                if (paramString1.length() == paramString2.length())
                {
                    bool1 = bool2;
                    if (!paramString1.regionMatches(true, 0, paramString2, 0, paramString2.length())) {}
                }
            }
        }
        else
        {
            bool1 = true;
        }
        return bool1;
    }

    public static boolean isEmpty(CharSequence paramCharSequence)
    {
        boolean bool = false;
        if ((paramCharSequence == null) || (paramCharSequence.length() == 0) || (paramCharSequence.equals("null"))) {
            bool = true;
        }
        return bool;
    }

    public static boolean isSpace(String paramString)
    {
        boolean bool = false;
        if ((paramString == null) || (paramString.trim().length() == 0)) {
            bool = true;
        }
        return bool;
    }

    public static int length(CharSequence paramCharSequence)
    {
        if (paramCharSequence == null) {
            return 0;
        }
        return paramCharSequence.length();
    }

    public static String lowerFirstLetter(String paramString)
    {
        Object localObject = paramString;
        if (!isEmpty(paramString))
        {
            if (!Character.isUpperCase(paramString.charAt(0))) {
                return paramString;
            }
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append(String.valueOf((char)(paramString.charAt(0) + ' ')));
            ((StringBuilder)localObject).append(paramString.substring(1));
            localObject = ((StringBuilder)localObject).toString();
        }
        return (String)localObject;
    }

    public static String null2Length0(String paramString)
    {
        String str = paramString;
        if (paramString == null) {
            str = "";
        }
        return str;
    }

    public static String removeHtmlTag(String paramString)
    {
        return paramString.replace("&nbsp;", " ");
    }

    public static String reverse(String paramString)
    {
        int k = length(paramString);
        if (k <= 1) {
            return paramString;
        }
        char[] mParamString = paramString.toCharArray();
        int j = 0;
        while (j < k >> 1)
        {
            int i = mParamString[j];
            int m = k - j - 1;
            mParamString[j] = mParamString[m];
            mParamString[m] = mParamString[i];
            j += 1;
        }
        return new String(mParamString);
    }

    public static String toSBC(String paramString)
    {
        if (isEmpty(paramString)) {
            return paramString;
        }
        char[] mParamString = paramString.toCharArray();
        int j = mParamString.length;
        int i = 0;
        while (i < j)
        {
            if (mParamString[i] == ' ') {
                mParamString[i] = 12288;
            } else if (('!' <= mParamString[i]) && (mParamString[i] <= '~')) {
                mParamString[i] = ((char)(mParamString[i] + 65248));
            } else {
                mParamString[i] = mParamString[i];
            }
            i += 1;
        }
        return new String(mParamString);
    }

    public static String upperFirstLetter(String paramString)
    {
        Object localObject = paramString;
        if (!isEmpty(paramString))
        {
            if (!Character.isLowerCase(paramString.charAt(0))) {
                return paramString;
            }
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append(String.valueOf((char)(paramString.charAt(0) - ' ')));
            ((StringBuilder)localObject).append(paramString.substring(1));
            localObject = ((StringBuilder)localObject).toString();
        }
        return (String)localObject;
    }
}
