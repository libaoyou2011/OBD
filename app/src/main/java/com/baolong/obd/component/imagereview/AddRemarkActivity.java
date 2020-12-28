package com.baolong.obd.component.imagereview;

/*import com.baolong.edsp.R;

import android.os.Bundle;
import android.content.Intent;

import com.baolong.edsp.common.utils.ToastUtils;
import com.baolong.edsp.common.network.ResponseWrapper;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.baolong.edsp.common.network.RetrofitManager;
import com.baolong.edsp.common.sharepreferemces.UserSP;

import java.util.HashMap;

import android.view.View;
import android.content.Context;
import android.widget.Toast;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.EditText;

import com.baolong.edsp.common.base.BaseActivity;

public class AddRemarkActivity extends BaseActivity implements View.OnClickListener {
    public static final String REMARK_CONTENT = "remark_content";
    private EditText et_content;
    private String id;
    private ImageView image_title_back;
    private TextView tv_title;
    private TextView tv_title_right;

    private void initView() {
        (this.image_title_back = (ImageView) this.findViewById(R.id.image_title_back)).setVisibility(View.VISIBLE);
        (this.tv_title = (TextView) this.findViewById(R.id.tv_title)).setText((CharSequence) "\u5907\u6ce8\u4fe1\u606f");
        this.tv_title_right = (TextView) this.findViewById(R.id.tv_title_right);
        this.et_content = (EditText) this.findViewById(R.id.et_content);
        this.tv_title_right.setVisibility(View.VISIBLE);
        this.tv_title_right.setText((CharSequence) "添加");
        this.tv_title_right.setTextColor(this.getResources().getColor(R.color.textColor_4d7bfe));
        this.tv_title_right.setOnClickListener((View.OnClickListener) this);
        this.image_title_back.setOnClickListener((View.OnClickListener) this);
    }

    private void submit() {
        final String trim = this.et_content.getText().toString().trim();
        if (TextUtils.isEmpty((CharSequence) trim)) {
            Toast.makeText((Context) this, (CharSequence) "请输入图片备注信息", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!TextUtils.isEmpty((CharSequence) this.id)) {
            this.showLoading((View) this.et_content);
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("token", UserSP.getUserToken());
            hashMap.put("imageId", this.id);
            hashMap.put("remarkContent", trim);
            ((HttpApi.SelectImageAndRemark) RetrofitManager.getInstance().createReq(HttpApi.SelectImageAndRemark.class))
                    .insertImageRemark(hashMap).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResponseWrapper<Object>>() {
                        public void onCompleted() {
                        }

                        public void onError(final Throwable t) {
                            AddRemarkActivity.this.hideLoading();
                            ToastUtils.shortToast(t.getMessage());
                        }

                        public void onNext(final ResponseWrapper<Object> responseWrapper) {
                            AddRemarkActivity.this.hideLoading();
                            final Intent intent = AddRemarkActivity.this.getIntent();
                            intent.putExtra("remark_content", trim);
                            AddRemarkActivity.this.setResult(-1, intent);
                            AddRemarkActivity.this.finish();
                        }
                    });
            return;
        }
        final Intent intent = this.getIntent();
        intent.putExtra("remark_content", trim);
        this.setResult(-1, intent);
        this.finish();
    }

    public void onClick(final View view) {
        final int id = view.getId();
        if (id == R.id.image_title_back) {
            this.onBackPressed();
            return;
        }
        if (id == R.id.tv_title_right) {
            this.submit();
        }
    }

    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_add_image_remark);
        this.id = this.getIntent().getStringExtra("id");
        this.initView();
    }
}*/
