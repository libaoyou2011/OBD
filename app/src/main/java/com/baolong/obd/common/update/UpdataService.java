package com.baolong.obd.common.update;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.sharepreferemces.UserSP;
import com.baolong.obd.common.update.contract.ICheckVersionContract;
import com.baolong.obd.common.update.model.CheckVersionResponseModel;
import com.baolong.obd.common.update.presenter.CheckVersionPresenter;
import com.baolong.obd.common.utils.ToastUtils;

import java.io.File;

public class UpdataService extends Service
        implements ICheckVersionContract.View {
    private static final int RC = 256;
    private DownloadManager dm;
    private String downloadUrl = "http://dakaapp.troila.com/download/daka.apk?v=3.0";
    private long enqueue;
    private CheckVersionPresenter mCheckVersionPresenter;
    private BroadcastReceiver receiver;

    public static void install() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "myApp.apk");
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //新起一个栈装入启动的activity
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(BaseApplication.getInstance(), "com.a520wcf.chapter11.fileprovider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile((File) file);
        }
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        BaseApplication.getInstance().startActivity(intent);
    }

    private void showDialog(CheckVersionResponseModel checkVersionResponseModel) {
        Intent intent = new Intent();
        intent.setClass(this, (Class) UpdataActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("model", (Parcelable) checkVersionResponseModel);
        startActivity(intent);
    }

    private void startDownload(final String s) {
        this.dm = (DownloadManager)this.getSystemService(Context.DOWNLOAD_SERVICE);
        final DownloadManager.Request downloadManagerRequest = new DownloadManager.Request(Uri.parse(s));
        downloadManagerRequest.setMimeType("application/vnd.android.package-archive");
        downloadManagerRequest.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "myApp.apk");
        downloadManagerRequest.setNotificationVisibility(1);
        downloadManagerRequest.setTitle((CharSequence)"下载新版本");
        this.enqueue = this.dm.enqueue(downloadManagerRequest);
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "启动更新服务", Toast.LENGTH_SHORT).show();

        //自定义广播接收者
        this.receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                UpdataService.install();
                UpdataService.this.stopSelf();
            }
        };
        //动态注册广播，下载完成时就能收到广播
        registerReceiver(this.receiver, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));

        this.mCheckVersionPresenter = new CheckVersionPresenter(this);
        this.mCheckVersionPresenter.getData(UserSP.getUserToken());
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        //取消动态注册广播
        unregisterReceiver(this.receiver);
        super.onDestroy();
    }

    public void setPresenter(CheckVersionPresenter paramCheckVersionPresenter) {
    }

    public void showData(CheckVersionResponseModel checkVersionResponseModel) {
        String packageName = this.getPackageName();
        try {
            int i = this.getPackageManager().getPackageInfo(packageName, 0).versionCode;
            if (Integer.parseInt(checkVersionResponseModel.getVersionCode()) > i) {
                showDialog(checkVersionResponseModel);
                return;
            }
            ToastUtils.shortToast("不需要更新");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
