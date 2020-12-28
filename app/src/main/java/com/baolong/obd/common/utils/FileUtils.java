package com.baolong.obd.common.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import okhttp3.ResponseBody;

public class FileUtils {
    private static final String TAG = FileUtils.class.getSimpleName();
    private static final String[][] MIME_MapTable;

    static {
        String[] arrayOfString1 = {".3gp", "video/3gpp"};
        String[] arrayOfString2 = {".apk", "application/vnd.android.package-archive"};
        String[] arrayOfString3 = {".asf", "video/x-ms-asf"};
        String[] arrayOfString4 = {".avi", "video/x-msvideo"};
        String[] arrayOfString5 = {".bin", "application/octet-stream"};
        String[] arrayOfString6 = {".bmp", "image/bmp"};
        String[] arrayOfString7 = {".c", "text/plain"};
        String[] arrayOfString8 = {".class", "application/octet-stream"};
        String[] arrayOfString9 = {".conf", "text/plain"};
        String[] arrayOfString10 = {".cpp", "text/plain"};
        String[] arrayOfString11 = {".doc", "application/msword"};
        String[] arrayOfString12 = {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"};
        String[] arrayOfString13 = {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"};
        String[] arrayOfString14 = {".exe", "application/octet-stream"};
        String[] arrayOfString15 = {".gif", "image/gif"};
        String[] arrayOfString16 = {".gtar", "application/x-gtar"};
        String[] arrayOfString17 = {".gz", "application/x-gzip"};
        String[] arrayOfString18 = {".h", "text/plain"};
        String[] arrayOfString19 = {".htm", "text/html"};
        String[] arrayOfString20 = {".html", "text/html"};
        String[] arrayOfString21 = {".jar", "application/java-archive"};
        String[] arrayOfString22 = {".java", "text/plain"};
        String[] arrayOfString23 = {".jpeg", "image/jpeg"};
        String[] arrayOfString24 = {".jpg", "image/jpeg"};
        String[] arrayOfString25 = {".js", "application/x-javascript"};
        String[] arrayOfString26 = {".log", "text/plain"};
        String[] arrayOfString27 = {".m3u", "audio/x-mpegurl"};
        String[] arrayOfString28 = {".m4a", "audio/mp4a-latm"};
        String[] arrayOfString29 = {".m4b", "audio/mp4a-latm"};
        String[] arrayOfString30 = {".m4p", "audio/mp4a-latm"};
        String[] arrayOfString31 = {".m4u", "video/vnd.mpegurl"};
        String[] arrayOfString32 = {".m4v", "video/x-m4v"};
        String[] arrayOfString33 = {".mov", "video/quicktime"};
        String[] arrayOfString34 = {".mp2", "audio/x-mpeg"};
        String[] arrayOfString35 = {".mp3", "audio/x-mpeg"};
        String[] arrayOfString36 = {".mp4", "video/mp4"};
        String[] arrayOfString37 = {".mpc", "application/vnd.mpohun.certificate"};
        String[] arrayOfString38 = {".mpe", "video/mpeg"};
        String[] arrayOfString39 = {".mpeg", "video/mpeg"};
        String[] arrayOfString40 = {".mpg", "video/mpeg"};
        String[] arrayOfString41 = {".mpg4", "video/mp4"};
        String[] arrayOfString42 = {".mpga", "audio/mpeg"};
        String[] arrayOfString43 = {".ogg", "audio/ogg"};
        String[] arrayOfString44 = {".pdf", "application/pdf"};
        String[] arrayOfString45 = {".png", "image/png"};
        String[] arrayOfString46 = {".pps", "application/vnd.ms-powerpoint"};
        String[] arrayOfString47 = {".ppt", "application/vnd.ms-powerpoint"};
        String[] arrayOfString48 = {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"};
        String[] arrayOfString49 = {".prop", "text/plain"};
        String[] arrayOfString50 = {".rc", "text/plain"};
        String[] arrayOfString51 = {".rmvb", "audio/x-pn-realaudio"};
        String[] arrayOfString52 = {".rtf", "application/rtf"};
        String[] arrayOfString53 = {".sh", "text/plain"};
        String[] arrayOfString54 = {".tar", "application/x-tar"};
        String[] arrayOfString55 = {".tgz", "application/x-compressed"};
        String[] arrayOfString56 = {".txt", "text/plain"};
        String[] arrayOfString57 = {".wav", "audio/x-wav"};
        String[] arrayOfString58 = {".wma", "audio/x-ms-wma"};
        String[] arrayOfString59 = {".wmv", "audio/x-ms-wmv"};
        String[] arrayOfString60 = {".wps", "application/vnd.ms-works"};
        String[] arrayOfString61 = {".xml", "text/plain"};
        String[] arrayOfString62 = {".z", "application/x-compress"};
        String[] arrayOfString63 = {".zip", "application/x-zip-compressed"};
        String[] arrayOfString64 = {"", "*/*"};
        MIME_MapTable = new String[][]{arrayOfString1, arrayOfString2, arrayOfString3, arrayOfString4, arrayOfString5, arrayOfString6, arrayOfString7, arrayOfString8, arrayOfString9, arrayOfString10, arrayOfString11, arrayOfString12, {".xls", "application/vnd.ms-excel"}, arrayOfString13, arrayOfString14, arrayOfString15, arrayOfString16, arrayOfString17, arrayOfString18, arrayOfString19, arrayOfString20, arrayOfString21, arrayOfString22, arrayOfString23, arrayOfString24, arrayOfString25, arrayOfString26, arrayOfString27, arrayOfString28, arrayOfString29, arrayOfString30, arrayOfString31, arrayOfString32, arrayOfString33, arrayOfString34, arrayOfString35, arrayOfString36, arrayOfString37, arrayOfString38, arrayOfString39, arrayOfString40, arrayOfString41, arrayOfString42, {".msg", "application/vnd.ms-outlook"}, arrayOfString43, arrayOfString44, arrayOfString45, arrayOfString46, arrayOfString47, arrayOfString48, arrayOfString49, arrayOfString50, arrayOfString51, arrayOfString52, arrayOfString53, arrayOfString54, arrayOfString55, arrayOfString56, arrayOfString57, arrayOfString58, arrayOfString59, arrayOfString60, arrayOfString61, arrayOfString62, arrayOfString63, arrayOfString64};
    }

    /**
     * 判断文件夹是否存在
     */
    public static boolean checkFileExists(String pathName) {
        if (!TextUtils.isEmpty(pathName)) {
            return new File(pathName).exists();
        } else {
            return false;
        }
    }

    public static File createFile(String path, String fileName) {
        File file = new File(path, fileName);
        try {
            //文件已存在 并且 删除失败
            if ((file.exists()) && (!file.delete())) {
                return null;
            }
            //重新创建文件 --> 创建失败
            if (!file.createNewFile()) {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 获取App专属缓存目录：外置存储 or 内置存储
     *
     * @param context
     * @return
     */
    public static String getCacheDir(Context context) {
        File file;
        //SD卡存在 或者 SD卡不可被移除
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                && !Environment.isExternalStorageRemovable()) {
            //外部存储可用，使用外置存储
            file = context.getExternalCacheDir();
            LogUtil.i(TAG, "外置存储_CacheDir: " + file.getPath());
            //小米   外置存储_CacheDir: /storage/emulated/0/Android/data/com.baolong.edsp/cache
            //模拟器 外置存储_CacheDir: /storage/emulated/0/Android/data/com.baolong.edsp/cache
            // 外置存储_CacheDir: /storage/sdcard1/data/user/0/com.baolong.edsp/cache
        } else {
            //外部存储不可用, 使用内置存储
            file = context.getCacheDir();
            LogUtil.i(TAG, "内置存储_CacheDir: " + file.getPath());
        }
        return file.getPath();
    }

    /**
     * 获取App专属文件目录：外置存储 or 内置存储
     *
     * @param context
     * @return
     */
    public static String getFileDir(Context context) {
        File file;
        //SD卡存在 或者 SD卡不可被移除
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                && !Environment.isExternalStorageRemovable()) {
            //外部存储可用，使用外置存储
            file = context.getExternalFilesDir("tempPhotos");
            LogUtil.i(TAG, "外置存储_FileDir: " + file.getPath());
            //小米    (/storage/emulated/0/)Android/data/com.baolong.edsp/files/tempPhotos
            //模拟器    /storage/emulated/0/Android/data/com.baolong.edsp/files/tempPhotos
        } else {
            //外部存储不可用, 使用内置存储
            file = context.getFilesDir();
            LogUtil.i(TAG, "内置存储_FileDir: " + file.getPath());
        }
        return file.getPath();
    }

    /**
     * 获取sd 根目录
     *
     * @param context
     * @param suffixPath //后缀路径
     * @return
     */
    public static String getSdRootDir(Context context, String suffixPath) {
        File sdCard = Environment.getExternalStorageDirectory();

        if (TextUtils.isEmpty(suffixPath)) {
            return sdCard.getPath();
        } else {
            String folderPath = CombinePath(sdCard.getPath(), suffixPath);
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            return folder.getPath();
        }

    }

    public static String getMimeType(File paramFile) {
        String[][] arrayOfString = MIME_MapTable;
        int j = arrayOfString.length;
        int i = 0;
        while (i < j) {
            String[] arrayOfString1 = arrayOfString[i];
            if (paramFile.getName().endsWith(arrayOfString1[0])) {
                return arrayOfString1[1];
            }
            i += 1;
        }
        return null;
    }

    public static String readTextFromAssets(final Context context, final String AssetFileName) throws Exception {
        InputStream inputStream = null;
        final StringBuilder sb = new StringBuilder();
        try {
            inputStream = context.getAssets().open(AssetFileName);
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    /**
     * 将一个InputStream里面的数据写入到SD卡中
     */
    public static File write2SDFromInput(String path, String fileName, InputStream inputStream) {
        File file = null;
        OutputStream outputStream = null;
        try {
            file = createFile(path, fileName);
            if (file != null) {
                outputStream = new FileOutputStream(file);
                //4K
                byte[] buffer = new byte[4 * 1024];
                while (true) {
                    int tempLength = inputStream.read(buffer, 0, buffer.length);
                    if (tempLength == -1) {
                        break;
                    }
                    outputStream.write(buffer, 0, tempLength);
                }
                outputStream.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }


    /*
     * 下载文件保存到本地
     */
    public static File saveFile(String path, String fileName, ResponseBody body) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        File file = null;
        try {
            if (path == null) {
                return null;
            }
            file = createFile(path, fileName);

            if (file == null || !file.exists()) {
                file.createNewFile();
            }

            long fileSize = body.contentLength();
            long fileSizeDownloaded = 0;
            byte[] fileReader = new byte[4096];

            inputStream = body.byteStream();
            outputStream = new FileOutputStream(file);

            while (true) {
                int read = inputStream.read(fileReader);
                if (read == -1) {
                    break;
                }
                outputStream.write(fileReader, 0, read);
                fileSizeDownloaded += read;
            }
            outputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return file;
    }


    /**
     * 将多个目录层次拼接成为一个完整的路径。主要目的是为了处理每个目录名字开头或结尾的 File.separatorChar
     *
     * @param path
     * @return
     */
    public static String CombinePath(String... path) {

        String full = "";

        for (int i = 0; i < path.length; i++) {
            if (full == "") {
                full = path[i];
            } else if (full.endsWith(String.valueOf(File.separatorChar)) && path[i].startsWith(String.valueOf(File.separatorChar))) {
                full += path[i].substring(1);
            } else if (full.endsWith(String.valueOf(File.separatorChar)) || path[i].startsWith(String.valueOf(File.separatorChar))) {
                full += path[i];
            } else {
                if (!path[i].trim().equals("")) {
                    full += (File.separatorChar + path[i]);
                }
            }
        }

        return full;
    }

    /**
     * 将多个层次的URL拼接成一个，重点是处理多段之间的 / 连接符，避免连续两个挨着的。
     *
     * @param url
     * @return
     */
    public static String CombineUrl(String... url) {

        String full = "";

        for (int i = 0; i < url.length; i++) {
            if (full == "") {
                full = url[i];
            } else if (full.endsWith("/") && url[i].startsWith("/")) {
                full += url[i].substring(1);
            } else if (full.endsWith("/") || url[i].startsWith("/")) {
                full += url[i];
            } else {
                full += ("/" + url[i]);
            }
        }

        return full;
    }
}
