package com.baolong.obd.common.update;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.common.update.model.CheckVersionResponseModel;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.my.event.UpdateVersionEvent;
import com.hwangjr.rxbus.RxBus;

public class DialogConfirmActivity extends Activity {
    private static final String TAG = DialogConfirmActivity.class.getSimpleName();

    private boolean isFirstCheck;
    private CheckVersionResponseModel mModel;

    private TextView versionCodeTV;
    private TextView versionContentTV;

    private TextView updateConfirm;
    private ImageView updateCancelImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_update_new_version);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        Intent intent = getIntent();
        if (intent != null) {
            this.isFirstCheck = intent.getBooleanExtra("isFirstCheck",true);
            this.mModel = ((CheckVersionResponseModel) intent.getParcelableExtra("model"));
        }

        versionCodeTV = (TextView) findViewById(R.id.tv_version_code);
        versionContentTV = (TextView) findViewById(R.id.tv_version_content);

        updateConfirm = (TextView) findViewById(R.id.tv_update_confirm);
        updateCancelImg = (ImageView) findViewById(R.id.img_update_cancel);

        if (mModel != null) {
            versionCodeTV.setText(mModel.getVersionName());
            String content = mModel.getUpdateContent();
            if (content != null) {
                // 字符串中的转义字符，通过Intent传递时，转义字符”\n”已经变成”\\n“
                // 因此要转换回来
                content = content.replace("\\n", "\n");
            }
            versionContentTV.setText(content);
        }

        updateConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mModel.getFileApp() != null) {
                    // 具体操作   // 阿里云app："https://yunpan.aliyun.com/downloads/apps/10000996%40yunpan-release.apk"
                    Intent intent = new Intent(DialogConfirmActivity.this, DownloadService.class);
                    intent.putExtra("url", mModel.getFileApp());
                    startService(intent);

                    ToastUtils.longToast("正在下载新版本...");
                }  else {
                    ToastUtils.longToast("新版本下载无地址");
                }

                onBackPressed();

            }
        });

        updateCancelImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFirstCheck) {
                    RxBus.get().post((Object) new UpdateVersionEvent(mModel));
                }

                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LogUtil.i(TAG, "onBackPressed");

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i(TAG, "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG, "onDestroy");
    }

}