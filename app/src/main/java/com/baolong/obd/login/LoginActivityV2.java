package com.baolong.obd.login;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baolong.obd.R;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.common.network.RsaUtil;
import com.baolong.obd.common.sharepreferemces.ServerHostSP;
import com.baolong.obd.common.sharepreferemces.UserSP;
import com.baolong.obd.common.utils.CommonUtils;
import com.baolong.obd.common.utils.FileUtils;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.common.utils.StreamUtil;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.common.view.recyclerview.itemdecoration.RecycleViewDivider;
import com.baolong.obd.login.contract.ILoginContract;
import com.baolong.obd.login.model.ResponseLoginModel;
import com.baolong.obd.login.model.ServerHostInfoModel;
import com.baolong.obd.login.presenter.LoginPresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;
import static com.baolong.obd.common.utils.CommonUtils.isUrl;

public class LoginActivityV2 extends BaseActivity
        implements OnClickListener, ILoginContract.View {
    private static final String TAG = LoginActivityV2.class.getSimpleName();
    private EditText mChooseHostEt;
    private EditText mUserNameEt;
    private EditText mPwsEt;
    private Button mBt_login;
    private LoginPresenter mLoginPresenter;

    private RecyclerView serverRecyclerView;
    private ServeHostListAdapter serveHostListAdapter;

    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(1024, 1024);
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        initView();
        initData();
        initPresenter();
    }

    protected void initView() {
        this.mChooseHostEt = (EditText) this.findViewById(R.id.tv_login_server);
        this.mChooseHostEt.setOnClickListener(this);
        this.mUserNameEt = ((EditText) findViewById(R.id.et_login_phone));
        this.mPwsEt = ((EditText) findViewById(R.id.et_login_pwd));
        this.mBt_login = ((Button) findViewById(R.id.bt_login));
        this.mBt_login.setOnClickListener(this);

        String userName;
        if (TextUtils.isEmpty(UserSP.getUserName())) {
            userName = "";
        } else {
            userName = UserSP.getUserName();
        }
        //显示用户名
        this.mUserNameEt.setText(userName);
        // 光标再最后一个文字后
        this.mUserNameEt.setSelection(userName.length());
    }

    protected void initData() {
        /*try {
            final String versionName = BaseApplication.getIns().getPackageManager().getPackageInfo(BaseApplication.getIns().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException ex) {
            ex.printStackTrace();
        }
        if (FileUtils.checkFileExists(ServerHostSP.HOST_CACHE_PATH)) {
            try {
                final ServerHostInfoModel serverHostInfoModel = (ServerHostInfoModel) StreamUtil.readObject(ServerHostSP.HOST_CACHE_PATH);
                if (serverHostInfoModel != null) {
//                    this.mChooseHostEt.setText(serverHostInfoModel.getCityName());
                }
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }*/

        if (!TextUtils.isEmpty(UserSP.getStationLocalHost())) {
            this.mChooseHostEt.setText(UserSP.getStationLocalHost());
        }

    }

    private void initPresenter() {
        this.mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        if (id == R.id.tv_login_server) {
            //this.showServerHostDialog(this.getHostUrlData());

        } else if (id == R.id.bt_login) {
            final String host = this.mChooseHostEt.getText().toString().trim();

            String tips;
            if (TextUtils.isEmpty(host)) {
                //tips = "请选择主服务器地址!";
                tips = "请输入主服务器地址!";
            } else if (!isUrl(host)) {
                tips = "输入主服务器地址格式不正确!";
            } else if (TextUtils.isEmpty(this.mUserNameEt.getText().toString().trim())) {
                tips = "请输入账号信息!";
            } else if (TextUtils.isEmpty(this.mPwsEt.getText().toString().trim())) {
                tips = "请输入密码!";
            } else {

                final String base_URL;
                if (!host.endsWith("/")) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append(host);
                    sb.append("/");
                    base_URL = sb.toString();
                } else {
                    base_URL = host;
                }
                LogUtil.i(TAG, base_URL);

                //首次登录，重设BaseApplication里的host
                BaseApplication.host = base_URL;

                // 保存在 SharedPreferences
                UserSP.setStationLocalHost(base_URL);
                //保存在 缓存目录
                //StreamUtil.writeObject(ServerHostSP.HOST_CACHE_PATH, base_URL);
                //Retrofit重启，使用UserSP中base_URL
                RetrofitManager.restartRetrofitManager();

                this.doLogin(this.mUserNameEt.getText().toString().trim(), this.mPwsEt.getText().toString().trim());

                return;
            }
            ToastUtils.shortToast(tips);

        }

    }

    private List<ServerHostInfoModel> getHostUrlData() {
        Object obj = new ArrayList<ServerHostInfoModel>();
        Serializable serial = (Serializable) (obj);
        try {
            obj = serial;
            final String textFromFile = FileUtils.readTextFromAssets(this, "city_host.txt");
            final List<ServerHostInfoModel> list = (List) new Gson().fromJson(textFromFile, new TypeToken<List<ServerHostInfoModel>>() {
            }.getType());

            if (!FileUtils.checkFileExists(ServerHostSP.HOST_CACHE_PATH)) {
                return (List<ServerHostInfoModel>) list;
            } else {
                Serializable serverHostInfoModelOf = StreamUtil.readObject(ServerHostSP.HOST_CACHE_PATH);
                ServerHostInfoModel serverHostInfoModel = (ServerHostInfoModel) serverHostInfoModelOf;
                for (int i = 0; i < list.size(); ++i) {
                    list.get(i).setCheck(false);
                    if (list.get(i).getCityName().equals(serverHostInfoModel.getCityName())) {
                        list.get(i).setCheck(true);
                    }
                }
                return (List<ServerHostInfoModel>) list;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return (List<ServerHostInfoModel>) obj;
        }
    }

    private void showServerHostDialog(final List<ServerHostInfoModel> list) {
        //ToastUtils.longToast(new Gson().toJson(list));
        final Dialog dialog = new Dialog((Context) this, R.style.ActionSheetDialogStyle);
        View inflate = LayoutInflater.from(this).inflate(R.layout.activity_server_host_list, (ViewGroup) null);
        dialog.setContentView(inflate);

        final Window window = dialog.getWindow();
        window.setGravity(80);
        window.getDecorView().setPadding(0, 0, 0, 0);

        final WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = (int) CommonUtils.DpToPx((Context) this, 400.0f);
        window.setAttributes(attributes);
        dialog.show();

        this.serverRecyclerView = (RecyclerView) inflate.findViewById(R.id.rv_place);
        this.serverRecyclerView.setLayoutManager((RecyclerView.LayoutManager) new LinearLayoutManager((Context) this));
        RecycleViewDivider recycleViewDivider = new RecycleViewDivider((Context) this, RecycleViewDivider.VERTICAL);
        recycleViewDivider.setDrawable(this.getResources().getDrawable(R.drawable.list_row_line));
        this.serverRecyclerView.addItemDecoration((RecyclerView.ItemDecoration) recycleViewDivider);

        // Adapter适配器
        this.serveHostListAdapter = new ServeHostListAdapter((Context) this, list);
        //点击事件
        this.serveHostListAdapter.setOnItemClickListener((ServeHostListAdapter.OnItemClickListener) new ServeHostListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final ServerHostInfoModel serverHostInfoModel) {
                for (int i = 0; i < LoginActivityV2.this.serveHostListAdapter.getmModels().size(); ++i) {
                    LoginActivityV2.this.serveHostListAdapter.getmModels().get(i).setCheck(false);
                }
                serverHostInfoModel.setCheck(true);
                LoginActivityV2.this.serveHostListAdapter.notifyDataSetChanged();
            }
        });
        // 取消按钮
        ((TextView) inflate.findViewById(R.id.tv_cancel)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        // 确认按钮
        ((TextView) inflate.findViewById(R.id.tv_confirm)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final List<ServerHostInfoModel> getmModels = serveHostListAdapter.getmModels();
                ServerHostInfoModel serverHostInfoModel = null;
                for (int i = 0; i < getmModels.size(); ++i) {
                    if (getmModels.get(i).isCheck()) {
                        serverHostInfoModel = getmModels.get(i);
                    }
                }
                if (serverHostInfoModel == null) {
                    ToastUtils.shortToast("您还没有选择主服务器地址，请选择！");
                    return;
                }
                LoginActivityV2.this.mChooseHostEt.setText((CharSequence) serverHostInfoModel.getCityName());
                //重设 host ip
                String base_URL;
                if (!serverHostInfoModel.getHostUrl().endsWith("/")) {
                    final StringBuilder sb = new StringBuilder();
                    sb.append(serverHostInfoModel.getHostUrl());
                    sb.append("/");
                    base_URL = sb.toString();
                } else {
                    base_URL = serverHostInfoModel.getHostUrl();
                }
                LogUtil.i(TAG, base_URL);
                // 保存在 SharedPreferences
                UserSP.setStationLocalHost(RetrofitManager.BASE_URL = base_URL);
                UserSP.setAddressCenterLat(serverHostInfoModel.getAddressCenterLat());
                UserSP.setAddressCenterLng(serverHostInfoModel.getAddressCenterLng());
                UserSP.setStationLocalHost(serverHostInfoModel.getHostUrl());
                RetrofitManager.restartRetrofitManager();
                //保存在 缓存目录
                StreamUtil.writeObject(ServerHostSP.HOST_CACHE_PATH, serverHostInfoModel);
                //首次登录，重设BaseApplication里的host
                BaseApplication.host = serverHostInfoModel.getHostUrl();
                dialog.dismiss();
            }
        });
        this.serverRecyclerView.setAdapter((RecyclerView.Adapter) this.serveHostListAdapter);
    }

    private void doLogin(String username, String password) {
        showLoading(this.mBt_login);

        // 清除之前的Cookie
        UserSP.setCookie("");
        BaseApplication.cookie = "";

        // 方法一：JSONObject.toString()
        JSONObject tempJSONObject = new JSONObject();
        try {
            //测试加密
            LogUtil.i(TAG, "password 原始:" + password);
            String temp = RsaUtil.encryptByPubKey(password);
            LogUtil.i(TAG, "password 加密:" + temp);
            LogUtil.i(TAG, "password 解密:" + RsaUtil.decryptByPriKey(temp));
            LogUtil.i(TAG, "password 浏览器加密后解密:" + RsaUtil.decryptByPriKey("Xr76IJlXvZrYNRjwV84FaBpi17Ivb+bwCx7j7UqbLoq1C4tUxoIGqGPteGiuJiB50qv2vtYp2QAQGdpM3Yx/Dw=="));

            this.username = username;
            this.password = RsaUtil.encryptByPubKey(password);
            tempJSONObject.put("username", this.username);
            tempJSONObject.put("password", this.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), tempJSONObject.toString());

        // 方法二：Gson 将Object转json
        // Gson gson = new Gson();
        // String obj = gson.toJson(Object obj);
        // RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj);

        this.mLoginPresenter.login2(requestBody);
        //this.mLoginPresenter.login(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, userName, userPws);

        // 清除账户信息
        //UserSP.clearData();
    }

    @Override
    public void loginSuccess(ResponseLoginModel responseLoginModel) {
        hideLoading();
        UserSP.setUserName(this.username);
        UserSP.setUserPwd(this.password);


//        StringBuilder sb = new StringBuilder();
//        sb.append("username=");
//        sb.append(this.username);
//        sb.append(";");
//        sb.append("password=");
//        sb.append(this.password);
//        sb.append(";");
//        sb.append("rememberMe=true;");
//        sb.append("Admin-Token=");
//        sb.append(responseLoginModel.getToken());
//        String cookie = sb.toString();
//        LogUtil.i(TAG, cookie);
        String cookie = "Bearer " + responseLoginModel.getToken();


        UserSP.setCookie(cookie);
        // 首次登录时，cookie在BaseApplication通过SharedPreferences初始化为空，
        // 为了保证cookie在BaseApplication中有值，在此赋值
        BaseApplication.cookie = cookie;

        ARouter.getInstance().build("/main/MainActivity").navigation();
        finish();
    }

    @Override
    public void loginFail(String s) {
        hideLoading();
        ToastUtils.shortToast(s);
    }

}