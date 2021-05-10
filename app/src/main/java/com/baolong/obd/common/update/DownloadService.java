package com.baolong.obd.common.update;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.provider.Settings;
import android.text.TextUtils;

import androidx.core.content.FileProvider;

import com.baolong.obd.common.utils.LogUtil;

import java.io.File;
import java.io.IOException;

public class DownloadService extends Service {
    private static final String TAG = DownloadService.class.getSimpleName();

    private String url; //下载链接
    private long mId;  //下载任务的编号
    public String DOWNLOAD_PATH = Environment.getExternalStorageDirectory() + "/download/OBD.apk";

    private BroadcastReceiver mBroadcastReceiver;

    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.i(TAG, "onStartCommand" + intent.toString());

        url = intent.getStringExtra("url");
        if (!TextUtils.isEmpty(url)) {
            download(url);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    public void download(String url) {
        if (new File(DOWNLOAD_PATH).exists()) {
            new File(DOWNLOAD_PATH).delete();
        }

        receiver();

        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        //设置通知栏标题
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);  //DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED 下载过程中显示，下载完成后一直显示
        request.setTitle("版本更新");
        request.setDescription("新版本下载中...");
        request.setMimeType("application/vnd.android.package-archive");

        // 这里的"AppUpdate.apk"要对应DOWNLOAD_PATH的"AppUpdate.apk"
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "OBD.apk");

        // 设置为可被 MediaScanner扫描到
        request.allowScanningByMediaScanner();
        // 设置为可见和可管理
        request.setVisibleInDownloadsUi(true);

        try {
            // 取得系统服务后，调用 DownloadManager对象的enqueue方法进行下载，此方法返回一个编号用于标示此下载任务：
            DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            mId = downloadManager.enqueue(request);
        } catch (Exception e) {
            LogUtil.i(TAG, "下载异常：");
            e.printStackTrace();
        }

    }


    public void receiver() {
        //自定义广播接收者
        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                LogUtil.i(TAG, "BroadcastReceiver" + intent.toString());

                long downloadID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (mId == downloadID) {
                    setPermission(DOWNLOAD_PATH);
                    Intent intent2 = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.parse("package:" + getPackageName()));
                    intent2.setAction(Intent.ACTION_VIEW);
                    intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 由于没有在Activity环境下启动Activity,设置下面的标签
                    intent2.addCategory(Intent.CATEGORY_DEFAULT);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//大于24  Android版本7.0
                        // 参数1 上下文,
                        // 参数2 Provider主机地址 和AndroidManifest.xml的provider节点下的authorities属性保持一致
                        // 参数3 共享的文件
                        Uri contentUri = FileProvider.getUriForFile(context, "com.zhihu.matisse.sample.obdfileprovider", new File(DOWNLOAD_PATH));
                        intent2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//这里不能是setFlags(),set会覆盖掉之前的flags
                        intent2.setDataAndType(contentUri, "application/vnd.android.package-archive");
                        context.startActivity(intent2);
                    } else {
                        installApk(context, mId);
                    }

                }
            }
        };

        //动态注册广播，下载完成时就能收到广播
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(mBroadcastReceiver, filter);

    }

    private void installApk(Context context, long downloadApkId) {
        DownloadManager dManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(downloadApkId);
        Cursor c = dManager.query(query);
        if (c != null) {
            if (c.moveToFirst()) {
                int columnIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS);
                if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(columnIndex)) {
                    String downloadFileUrl = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    startInstall(context, Uri.parse(downloadFileUrl));
                }
            }
            c.close();
        }
    }

    private boolean startInstall(Context context, Uri uri) {
        if (!new File(uri.getPath()).exists()) {
            System.out.println(" local file has been deleted! ");
            return false;
        }
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        startActivity(intent);
        return true;
    }

    /**
     * 提升读写权限
     *
     * @param filePath 文件路径
     */
    public static void setPermission(String filePath) {
        String command = "chmod " + "777" + " " + filePath;
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        //取消动态注册广播
        unregisterReceiver(this.mBroadcastReceiver);
        super.onDestroy();
    }

}