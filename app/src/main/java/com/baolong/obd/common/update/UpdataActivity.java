package com.baolong.obd.common.update;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.baolong.obd.R;
import com.baolong.obd.common.update.model.CheckVersionResponseModel;
import com.maning.updatelibrary.InstallUtils;
import com.maning.updatelibrary.InstallUtils.InstallCallBack;

public class UpdataActivity extends Activity
        implements View.OnClickListener {
    private static final String TAG = UpdataActivity.class.getSimpleName();
    private CheckVersionResponseModel mModel;
    private String mPath;
    private TextView mTv_size;
    private TextView mTv_updata_info;
    private TextView mTv_version;

    private void initView() {
        this.mTv_version = ((TextView) findViewById(R.id.tv_version));
        this.mTv_size = ((TextView) findViewById(R.id.tv_size));
        this.mTv_updata_info = ((TextView) findViewById(R.id.tv_updata_info));
        findViewById(R.id.tv_updata_enforce).setOnClickListener(this);
    }

    private void showData() {
        this.mModel = ((CheckVersionResponseModel) getIntent().getParcelableExtra("model"));
        this.mTv_version.setText(getString(R.string.updata_info_version, new Object[]{this.mModel.getVersionName()}));
        this.mTv_size.setText(getString(R.string.updata_info_size, new Object[]{this.mModel.getVersionSize()}));
        this.mTv_updata_info.setText(this.mModel.getUpdateContent());
    }

    public void downloadApkFile() {
        InstallUtils.with(this)
                .setApkUrl(this.mModel.getDownloadUrl())
                .setCallBack(new InstallUtils.DownloadCallBack() {
                    //下载取消
                    public void cancle() {
                        Log.i(TAG, "cancle ");
                    }

                    //下载完成
                    public void onComplete(String path) {
                        Log.i(TAG, "onComplete ");
                        UpdataActivity.this.mPath = path;
                        if (VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            //Android 8.0先获取是否有安装未知来源应用的权限
                            boolean haveInstallPermission = getPackageManager().canRequestPackageInstalls();
                            if (!haveInstallPermission) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("package:");
                                sb.append(UpdataActivity.this.getApplicationContext().getPackageName());
                                Uri packageURI = Uri.parse(sb.toString());
                                //跳转到请求安装未知来源应用界面
                                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                                UpdataActivity.this.startActivityForResult(intent, 1000);
                                return;
                            }
                        }
                        InstallUtils.installAPK(UpdataActivity.this.getApplicationContext(), path, new InstallCallBack() {
                            public void onSuccess() {
                                Toast.makeText(UpdataActivity.this.getApplicationContext(), "正在安装程序", Toast.LENGTH_SHORT).show();
                            }

                            public void onFail(Exception e) {
                                Context context = UpdataActivity.this.getApplicationContext();
                                StringBuilder sb = new StringBuilder();
                                sb.append("安装失败:");
                                sb.append(e.toString());
                                Toast.makeText(context, sb.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    //下载失败
                    public void onFail(Exception e) {
                        Log.i(TAG, "onFail ");
                        e.printStackTrace();
                    }

                    //下载中
                    public void onLoading(long n, long n2) {
                        Log.i(TAG, "----------------------------------");
                        StringBuilder sb = new StringBuilder();
                        sb.append("onLoading ");
                        sb.append((float) n2 / (float) n * 100.0F);
                        Log.i(TAG, sb.toString());
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("total ");
                        sb2.append(n);
                        Log.i(TAG, sb2.toString());
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("current ");
                        sb3.append(n2);
                        Log.i(TAG, sb3.toString());
                    }

                    //下载开始
                    public void onStart() {
                        Log.i(TAG, "onStart ");
                    }
                }).startDownload();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 1000) {
            // 是从 请求安装未知来源应用界面 返回来的
            InstallUtils.installAPK(getApplicationContext(), this.mPath, new InstallUtils.InstallCallBack() {
                public void onSuccess() {
                    Toast.makeText(UpdataActivity.this.getApplicationContext(), "正在安装程序", Toast.LENGTH_SHORT).show();
                }

                public void onFail(Exception e) {
                    Context applicationContext = UpdataActivity.this.getApplicationContext();
                    StringBuilder sb = new StringBuilder();
                    sb.append("安装失败:");
                    sb.append(e.toString());
                    Toast.makeText(applicationContext, sb.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Process.killProcess(Process.myPid());
    }

    public void onClick(View paramView) {
        if (paramView.getId() == R.id.tv_updata_enforce) {
            downloadApkFile();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(49);
        window.setType(2038);
        /* 项目中用到浮窗，如下：
             窗口类型：应用程序覆盖窗口显示在所有活动窗口上方
            （{@link #FIRST_APPLICATION_WINDOW}
             和 {@link #LAST_APPLICATION_WINDOW} 之间的类型）
             但在状态栏或IME等关键系统窗口之下。
        */
        window.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view_updata);
        getWindow().setLayout(-1, -1);
        initView();
        showData();
    }
}
