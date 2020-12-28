package com.baolong.obd.my.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.my.contract.UserInfoContract;
import com.baolong.obd.my.data.entity.UserInfoModel;
import com.baolong.obd.my.presenter.UserInfoPresenter;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class UserInfoActivity extends BaseActivity
        implements UserInfoContract.View, RadioGroup.OnCheckedChangeListener {

    private EditText userNameEt;
    private EditText userPhoneEt;
    private EditText userEmailEt;
    private RadioGroup userGenderRg;
    private RadioButton userMaleRb;
    private RadioButton userFemaleRb;
    private Button saveBt;

    private String sex;

    private UserInfoPresenter userInfoPresenter;

    private void initTitle() {
        ((ImageView) findViewById(R.id.image_title_back)).setOnClickListener((view) -> {
            this.onBackPressed();
        });
        ((TextView) findViewById(R.id.tv_title)).setText(getResources().getString(R.string.user_info_title));
    }

    private void initView() {
        userNameEt = (EditText) this.findViewById(R.id.et_user_name);
        userPhoneEt = (EditText) this.findViewById(R.id.et_user_phone);
        userEmailEt = (EditText) this.findViewById(R.id.et_user_email);
        userGenderRg = (RadioGroup) this.findViewById(R.id.radioGroup_gender);
        userMaleRb = (RadioButton) this.findViewById(R.id.radioButton_male);
        userFemaleRb = (RadioButton) this.findViewById(R.id.radioButton_female);
        saveBt = (Button) this.findViewById(R.id.bt_save);
    }

    private void addListener() {
        userGenderRg.setOnCheckedChangeListener(this);

        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                putDataByPresenter();
            }
        });
    }

    private void initPresenter() {
        userInfoPresenter = new UserInfoPresenter(this);
    }

    private void getDataByPresenter() {
        userInfoPresenter.getUserInfo();
    }

    private void putDataByPresenter() {
        JSONObject tempJSONObject = new JSONObject();
        try {
            tempJSONObject.put("nickName", userNameEt.getText().toString());
            tempJSONObject.put("phonenumber", userPhoneEt.getText().toString());
            tempJSONObject.put("email", userEmailEt.getText().toString());
            tempJSONObject.put("sex", sex);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), tempJSONObject.toString());
        userInfoPresenter.saveUserInfo(requestBody);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initTitle();
        initView();
        addListener();
        initPresenter();
        getDataByPresenter();
    }

    /**
     * Lint是一个静态检查器，它围绕Android项目的正确性、安全性、性能、可用性以及可访问性进行分析。
     * 它检查的对象包括XML资源、位图、ProGuard配置文件、源文件甚至编译后的字节码。
     * Lint包含了API版本检查、性能检查以及其他诸多特性。
     * 可以使用@SuppressLint标注忽略指定的警告
     */
    //@SuppressLint("NonConstantResourceId")
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //升级Android studio4.1后用switch语句判断id直接给出了警告
        //警告内容：Resource IDs will be non-final in Android Gradle Plugin version 5.0, avoid using them in switch case statements
        //解决：谷歌官方给出的解决办法是把switch换成if else
        //优点：根据谷歌官方的说法，这样的写法变更在UI中几乎没有性能损失。
//        switch (checkedId) {
//            case R.id.radioButton_male:
//                //当用户点击男性按钮时执行的代码
//                gender = "0";
//                break;
//            case R.id.radioButton_female:
//                //当用户点击女性按钮时执行的代码
//                gender = "1";
//                break;
//        }
        if (checkedId == R.id.radioButton_male) {
            //当用户点击男性按钮时执行的代码
            sex = "0";
        } else if (checkedId == R.id.radioButton_female) {
            //当用户点击女性按钮时执行的代码
            sex = "1";
        }
    }

    @Override
    public void setPresenter(UserInfoContract.Presenter paramT) {

    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
        ((BaseActivity) this).hideLoading();
    }

    @Override
    public void showFail(String paramString) {
        ToastUtils.shortToast(paramString);
    }

    @Override
    public void showUserInfo(UserInfoModel paramUser) {
        if (paramUser != null) {
            userNameEt.setText(paramUser.getNickName());
            userPhoneEt.setText(paramUser.getPhonenumber());
            userEmailEt.setText(paramUser.getEmail());

            sex = paramUser.getSex();

            if ("0".equals(sex)) {
                userMaleRb.setChecked(true);
            } else if ("1".equals(sex)) {
                userFemaleRb.setChecked(true);
            }
        }
    }

    @Override
    public void showSaveUserInfo(String paramString) {
        ToastUtils.shortToast(paramString);
    }

}

