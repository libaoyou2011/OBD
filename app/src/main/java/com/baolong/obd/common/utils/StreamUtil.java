package com.baolong.obd.common.utils;

import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class StreamUtil {
    public static void close(Closeable... paramVarArgs) {
        if (paramVarArgs == null) {
            return;
        }
        int j = paramVarArgs.length;
        int i = 0;
        while (i < j) {
            Closeable localCloseable = paramVarArgs[i];
            if (localCloseable != null) {
                try {
                    localCloseable.close();
                } catch (IOException localIOException) {
                    localIOException.printStackTrace();
                }
            }
            i += 1;
        }
    }

    public static boolean copy(File paramFile1, File paramFile2) {
        if (!paramFile1.exists()) {
            return false;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(paramFile1);
            return copy(fileInputStream, paramFile2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean copy(File paramFile, OutputStream paramOutputStream) {
        if (!paramFile.exists()) {
            return false;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(paramFile);
            return copy(fileInputStream, paramOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean copy(InputStream paramInputStream, File paramFile) {
        if (!paramFile.exists()) {
            File localFile = paramFile.getParentFile();
            if ((!localFile.exists()) && (!localFile.mkdirs())) {
                return false;
            }
            try {
                boolean bool = paramFile.createNewFile();
                if (!bool) {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(paramFile);
            return copy(paramInputStream, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean copy(InputStream fis, OutputStream fos) {
        try {
            //创建搬运工具
            byte datas[] = new byte[1024 * 2];
            //创建长度
            int len = 0;
            //循环读取数据
            while ((len = fis.read(datas)) != -1) {
                fos.write(datas, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return true;
            }
        }
    }

    /**
     * 深度复制，实参类必须实现Serializable接口
     */
    public static Object deepCopy(Object o) throws IOException, ClassNotFoundException {
        //先序列化，写入到流里
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(o);
        //然后反序列化，从流里读取出来，即完成复制
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return oi.readObject();
    }

    public static boolean delete(String paramString) {
        boolean bool1 = TextUtils.isEmpty(paramString);
        boolean bool2 = false;
        if (bool1) {
            return false;
        }
        File file = new File(paramString);
        bool1 = bool2;
        if (file.exists()) {
            bool1 = bool2;
            if (file.delete()) {
                bool1 = true;
            }
        }
        return bool1;
    }

    public static Serializable readObject(final String path) {
        Serializable serializable = null;
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(path));
            Object object = objectInputStream.readObject();
            if (object != null) {
                serializable = (Serializable) object;
            }
            objectInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return serializable;
    }

    public static <T> T readObject1(final String path) {
        T t1 = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
            Object object = objectInputStream.readObject();
                 t1 = (T) object;
            objectInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  t1;
    }

    public static void writeObject(String path, Object o){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(path)));
            objectOutputStream.writeObject(o);

            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /* Error */
//    public static <E> java.util.List<E> readObjectForList(File paramFile) {
    // Byte code:
    //   0: new 32	java/io/FileInputStream
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 35	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   8: astore_0
    //   9: new 91	java/io/ObjectInputStream
    //   12: dup
    //   13: aload_0
    //   14: invokespecial 94	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   17: astore_3
    //   18: aconst_null
    //   19: astore 4
    //   21: aload_0
    //   22: astore_1
    //   23: aload_3
    //   24: astore_2
    //   25: aload_3
    //   26: invokevirtual 98	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   29: astore 5
    //   31: aload 5
    //   33: ifnull +17 -> 50
    //   36: aload_0
    //   37: astore_1
    //   38: aload_3
    //   39: astore_2
    //   40: aload 5
    //   42: checkcast 100	[Ljava/lang/Object;
    //   45: astore 4
    //   47: goto -26 -> 21
    //   50: aload_0
    //   51: astore_1
    //   52: aload_3
    //   53: astore_2
    //   54: aload 4
    //   56: invokestatic 106	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   59: astore 4
    //   61: aload_3
    //   62: ifnull +10 -> 72
    //   65: aload_3
    //   66: invokevirtual 107	java/io/ObjectInputStream:close	()V
    //   69: goto +3 -> 72
    //   72: aload_0
    //   73: ifnull +14 -> 87
    //   76: aload_0
    //   77: invokevirtual 108	java/io/FileInputStream:close	()V
    //   80: goto +7 -> 87
    //   83: aload_0
    //   84: invokevirtual 20	java/io/IOException:printStackTrace	()V
    //   87: aload 4
    //   89: areturn
    //   90: astore 4
    //   92: goto +54 -> 146
    //   95: astore 4
    //   97: goto +91 -> 188
    //   100: astore 4
    //   102: goto +128 -> 230
    //   105: astore_1
    //   106: aconst_null
    //   107: astore_2
    //   108: goto +163 -> 271
    //   111: astore 4
    //   113: aconst_null
    //   114: astore_3
    //   115: goto +31 -> 146
    //   118: astore 4
    //   120: aconst_null
    //   121: astore_3
    //   122: goto +66 -> 188
    //   125: astore 4
    //   127: aconst_null
    //   128: astore_3
    //   129: goto +101 -> 230
    //   132: astore_1
    //   133: aconst_null
    //   134: astore_0
    //   135: aload_0
    //   136: astore_2
    //   137: goto +134 -> 271
    //   140: astore 4
    //   142: aconst_null
    //   143: astore_3
    //   144: aload_3
    //   145: astore_0
    //   146: aload_0
    //   147: astore_1
    //   148: aload_3
    //   149: astore_2
    //   150: aload 4
    //   152: invokevirtual 109	java/lang/ClassNotFoundException:printStackTrace	()V
    //   155: aload_3
    //   156: ifnull +10 -> 166
    //   159: aload_3
    //   160: invokevirtual 107	java/io/ObjectInputStream:close	()V
    //   163: goto +3 -> 166
    //   166: aload_0
    //   167: ifnull +97 -> 264
    //   170: aload_0
    //   171: invokevirtual 108	java/io/FileInputStream:close	()V
    //   174: aconst_null
    //   175: areturn
    //   176: aload_0
    //   177: invokevirtual 20	java/io/IOException:printStackTrace	()V
    //   180: aconst_null
    //   181: areturn
    //   182: astore 4
    //   184: aconst_null
    //   185: astore_3
    //   186: aload_3
    //   187: astore_0
    //   188: aload_0
    //   189: astore_1
    //   190: aload_3
    //   191: astore_2
    //   192: aload 4
    //   194: invokevirtual 20	java/io/IOException:printStackTrace	()V
    //   197: aload_3
    //   198: ifnull +10 -> 208
    //   201: aload_3
    //   202: invokevirtual 107	java/io/ObjectInputStream:close	()V
    //   205: goto +3 -> 208
    //   208: aload_0
    //   209: ifnull +55 -> 264
    //   212: aload_0
    //   213: invokevirtual 108	java/io/FileInputStream:close	()V
    //   216: aconst_null
    //   217: areturn
    //   218: aload_0
    //   219: invokevirtual 20	java/io/IOException:printStackTrace	()V
    //   222: aconst_null
    //   223: areturn
    //   224: astore 4
    //   226: aconst_null
    //   227: astore_3
    //   228: aload_3
    //   229: astore_0
    //   230: aload_0
    //   231: astore_1
    //   232: aload_3
    //   233: astore_2
    //   234: aload 4
    //   236: invokevirtual 110	java/io/EOFException:printStackTrace	()V
    //   239: aload_3
    //   240: ifnull +10 -> 250
    //   243: aload_3
    //   244: invokevirtual 107	java/io/ObjectInputStream:close	()V
    //   247: goto +3 -> 250
    //   250: aload_0
    //   251: ifnull +13 -> 264
    //   254: aload_0
    //   255: invokevirtual 108	java/io/FileInputStream:close	()V
    //   258: aconst_null
    //   259: areturn
    //   260: aload_0
    //   261: invokevirtual 20	java/io/IOException:printStackTrace	()V
    //   264: aconst_null
    //   265: areturn
    //   266: astore_3
    //   267: aload_1
    //   268: astore_0
    //   269: aload_3
    //   270: astore_1
    //   271: aload_2
    //   272: ifnull +10 -> 282
    //   275: aload_2
    //   276: invokevirtual 107	java/io/ObjectInputStream:close	()V
    //   279: goto +3 -> 282
    //   282: aload_0
    //   283: ifnull +14 -> 297
    //   286: aload_0
    //   287: invokevirtual 108	java/io/FileInputStream:close	()V
    //   290: goto +7 -> 297
    //   293: aload_0
    //   294: invokevirtual 20	java/io/IOException:printStackTrace	()V
    //   297: aload_1
    //   298: athrow
    //   299: astore_0
    //   300: goto -217 -> 83
    //   303: astore_0
    //   304: goto -128 -> 176
    //   307: astore_0
    //   308: goto -90 -> 218
    //   311: astore_0
    //   312: goto -52 -> 260
    //   315: astore_0
    //   316: goto -23 -> 293
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	319	0	paramFile	File
    //   22	30	1	localFile	File
    //   105	1	1	localObject1	Object
    //   132	1	1	localObject2	Object
    //   147	151	1	localObject3	Object
    //   24	252	2	localObject4	Object
    //   17	227	3	localObjectInputStream	java.io.ObjectInputStream
    //   266	4	3	localObject5	Object
    //   19	69	4	localObject6	Object
    //   90	1	4	localClassNotFoundException1	ClassNotFoundException
    //   95	1	4	localIOException1	IOException
    //   100	1	4	localEOFException1	java.io.EOFException
    //   111	1	4	localClassNotFoundException2	ClassNotFoundException
    //   118	1	4	localIOException2	IOException
    //   125	1	4	localEOFException2	java.io.EOFException
    //   140	11	4	localClassNotFoundException3	ClassNotFoundException
    //   182	11	4	localIOException3	IOException
    //   224	11	4	localEOFException3	java.io.EOFException
    //   29	12	5	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   25	31	90	java/lang/ClassNotFoundException
    //   40	47	90	java/lang/ClassNotFoundException
    //   54	61	90	java/lang/ClassNotFoundException
    //   25	31	95	java/io/IOException
    //   40	47	95	java/io/IOException
    //   54	61	95	java/io/IOException
    //   25	31	100	java/io/EOFException
    //   40	47	100	java/io/EOFException
    //   54	61	100	java/io/EOFException
    //   9	18	105	finally
    //   9	18	111	java/lang/ClassNotFoundException
    //   9	18	118	java/io/IOException
    //   9	18	125	java/io/EOFException
    //   0	9	132	finally
    //   0	9	140	java/lang/ClassNotFoundException
    //   0	9	182	java/io/IOException
    //   0	9	224	java/io/EOFException
    //   25	31	266	finally
    //   40	47	266	finally
    //   54	61	266	finally
    //   150	155	266	finally
    //   192	197	266	finally
    //   234	239	266	finally
    //   65	69	299	java/io/IOException
    //   76	80	299	java/io/IOException
    //   159	163	303	java/io/IOException
    //   170	174	303	java/io/IOException
    //   201	205	307	java/io/IOException
    //   212	216	307	java/io/IOException
    //   243	247	311	java/io/IOException
    //   254	258	311	java/io/IOException
    //   275	279	315	java/io/IOException
    //   286	290	315	java/io/IOException
//    }

    /* Error */
//    public static <T> boolean writeObject(java.util.List<T> paramList, File paramFile) {
    // Byte code:
    //   0: aload_0
    //   1: invokeinterface 122 1 0
    //   6: checkcast 100	[Ljava/lang/Object;
    //   9: astore_2
    //   10: aconst_null
    //   11: astore_0
    //   12: aconst_null
    //   13: astore_3
    //   14: new 55	java/io/FileOutputStream
    //   17: dup
    //   18: aload_1
    //   19: invokevirtual 126	java/io/File:toString	()Ljava/lang/String;
    //   22: invokespecial 127	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   25: astore_1
    //   26: new 129	java/io/ObjectOutputStream
    //   29: dup
    //   30: aload_1
    //   31: invokespecial 132	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   34: astore_0
    //   35: aload_0
    //   36: aload_2
    //   37: invokevirtual 135	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   40: aload_0
    //   41: aconst_null
    //   42: invokevirtual 135	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   45: aload_0
    //   46: ifnull +15 -> 61
    //   49: aload_0
    //   50: invokevirtual 136	java/io/ObjectOutputStream:close	()V
    //   53: goto +8 -> 61
    //   56: astore_0
    //   57: aload_0
    //   58: invokevirtual 20	java/io/IOException:printStackTrace	()V
    //   61: aload_1
    //   62: ifnull +118 -> 180
    //   65: aload_1
    //   66: invokevirtual 137	java/io/FileOutputStream:close	()V
    //   69: goto +111 -> 180
    //   72: astore_0
    //   73: aload_0
    //   74: invokevirtual 20	java/io/IOException:printStackTrace	()V
    //   77: goto +103 -> 180
    //   80: astore_2
    //   81: goto +11 -> 92
    //   84: aconst_null
    //   85: astore_0
    //   86: goto +62 -> 148
    //   89: astore_2
    //   90: aconst_null
    //   91: astore_0
    //   92: goto +17 -> 109
    //   95: aconst_null
    //   96: astore_2
    //   97: aload_0
    //   98: astore_1
    //   99: aload_2
    //   100: astore_0
    //   101: goto +47 -> 148
    //   104: astore_2
    //   105: aconst_null
    //   106: astore_0
    //   107: aload_3
    //   108: astore_1
    //   109: aload_2
    //   110: invokevirtual 138	java/lang/Exception:printStackTrace	()V
    //   113: aload_0
    //   114: ifnull +15 -> 129
    //   117: aload_0
    //   118: invokevirtual 136	java/io/ObjectOutputStream:close	()V
    //   121: goto +8 -> 129
    //   124: astore_0
    //   125: aload_0
    //   126: invokevirtual 20	java/io/IOException:printStackTrace	()V
    //   129: aload_1
    //   130: ifnull +50 -> 180
    //   133: aload_1
    //   134: invokevirtual 137	java/io/FileOutputStream:close	()V
    //   137: goto +43 -> 180
    //   140: astore_0
    //   141: aload_0
    //   142: invokevirtual 20	java/io/IOException:printStackTrace	()V
    //   145: goto +35 -> 180
    //   148: aload_0
    //   149: ifnull +15 -> 164
    //   152: aload_0
    //   153: invokevirtual 136	java/io/ObjectOutputStream:close	()V
    //   156: goto +8 -> 164
    //   159: astore_0
    //   160: aload_0
    //   161: invokevirtual 20	java/io/IOException:printStackTrace	()V
    //   164: aload_1
    //   165: ifnull +15 -> 180
    //   168: aload_1
    //   169: invokevirtual 137	java/io/FileOutputStream:close	()V
    //   172: goto +8 -> 180
    //   175: astore_0
    //   176: aload_0
    //   177: invokevirtual 20	java/io/IOException:printStackTrace	()V
    //   180: iconst_1
    //   181: ireturn
    //   182: astore_1
    //   183: goto -88 -> 95
    //   186: astore_0
    //   187: goto -103 -> 84
    //   190: astore_2
    //   191: goto -105 -> 86
    //   194: astore_2
    //   195: goto -47 -> 148
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	198	0	paramList	java.util.List<T>
    //   0	198	1	paramFile	File
    //   9	28	2	arrayOfObject	Object[]
    //   80	1	2	localException1	Exception
    //   89	1	2	localException2	Exception
    //   96	4	2	localObject1	Object
    //   104	6	2	localException3	Exception
    //   190	1	2	localObject2	Object
    //   194	1	2	localObject3	Object
    //   13	95	3	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   49	53	56	java/io/IOException
    //   65	69	72	java/io/IOException
    //   35	45	80	java/lang/Exception
    //   26	35	89	java/lang/Exception
    //   14	26	104	java/lang/Exception
    //   117	121	124	java/io/IOException
    //   133	137	140	java/io/IOException
    //   152	156	159	java/io/IOException
    //   168	172	175	java/io/IOException
    //   14	26	182	finally
    //   26	35	186	finally
    //   35	45	190	finally
    //   109	113	194	finally
//    }
}
