package com.baolong.obd.querycar.activity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.blackcar.contract.BlackDetailContract;
import com.baolong.obd.blackcar.data.entity.HuanjianModel;
import com.baolong.obd.blackcar.presenter.BlackDetailPresenter;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.component.publicevent.RefreshBlackCar;

import com.baolong.obd.querycar.adapter.AutoViewListAdapter;
import com.baolong.obd.querycar.data.entity.KeyNameValueListModel;
import com.google.gson.Gson;
import com.hwangjr.rxbus.RxBus;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;


//@Route(path = Constance.ACTIVITY_URL_BlackCarDetailActivity)
public class HuanJianDetailActivity extends BaseActivity implements BlackDetailContract.View {
    private static final String TAG = HuanJianDetailActivity.class.getSimpleName();

    private HuanjianModel mHuanjianModel;

    private RecyclerView mRecycler;
    private AutoViewListAdapter mAdapter;
    KeyNameValueListModel mKeyNameValueListModel;

    private void initTitle() {
        ((ImageView) findViewById(R.id.image_title_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HuanJianDetailActivity.this.onBackPressed();
            }
        });

        TextView titleTextView = (TextView) findViewById(R.id.tv_title);
        String titleName = getResources().getString(R.string.find_huanjian_detail_title);
        titleTextView.setText(titleName);
    }

    private void initView() {
        this.mRecycler = (RecyclerView) this.findViewById(R.id.rv_recycler);
        this.mRecycler.setLayoutManager((RecyclerView.LayoutManager) new LinearLayoutManager(this));
        this.mAdapter = new AutoViewListAdapter(this, null);
        this.mRecycler.setAdapter(this.mAdapter);

        // 从assets中取数据，转化为 keyNameValueListModel对象
        final String replace = this.readAssetsTxt().replace("\n", "").replace("\t", "").replace("\r", "");
        if (!TextUtils.isEmpty(replace)) {
            try {
                mKeyNameValueListModel = (KeyNameValueListModel) new Gson().fromJson(replace, (Class) KeyNameValueListModel.class);
            } catch (Exception ex) {
                mKeyNameValueListModel = null;
                ex.printStackTrace();
            }
        }

        if (mKeyNameValueListModel != null) {
            this.mAdapter.setData(mKeyNameValueListModel.getItems());
        }

    }

    public void showHuanjian(HuanjianModel vehicleInfo) {
        // 将服务器返回的值赋值到 keyNameValueListModel对象
        if (mKeyNameValueListModel != null) {

            if (vehicleInfo != null) {
                //车辆字段转换
                convertVehicleInfo(vehicleInfo);

                //获得某个类的所有声明的字段，即包括public, protected, default，private，但是不包括父类的申明字段。
                final Field[] declaredFields = vehicleInfo.getClass().getDeclaredFields();
                for (int i = 0; i < mKeyNameValueListModel.getItems().size(); ++i) {
                    for (int j = 0; j < declaredFields.length; ++j) {
                        final String fieldName = declaredFields[j].getName();
                        if (mKeyNameValueListModel.getItems().get(i).getKey().equals(fieldName)) {
                            try {
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append("get");
                                String tempFieldName = fieldName.substring(0, 1).toUpperCase().concat(fieldName.substring(1));
                                stringBuilder.append(tempFieldName);

                                //1.获取该类的Class Type;
                                final Class<? extends HuanjianModel> class1 = vehicleInfo.getClass();
                                //2.通过调用getMethod方法 获取Method对象；
                                Method method = class1.getMethod(stringBuilder.toString(), (Class<?>[]) new Class[0]);
                                LogUtil.i(TAG, method.toString());
                                //3.通过调用 invoke方法 来执行对象的某个方法； 方法的反射操作
                                String value = (String) method.invoke(vehicleInfo, new Object[]{});
                                mKeyNameValueListModel.getItems().get(i).setValue(value);
                                //mKeyNameValueListModel.getItems().get(i).setValue((String) class1.getMethod(sb.toString(), (Class<?>[]) new Class[0]).init(getMonitoringDataDetailNewModel, new Object[0]));
                                break;
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
                this.mAdapter.setData(mKeyNameValueListModel.getItems());
            }

        }
        this.mRecycler.setAdapter(this.mAdapter);
        // 先不加载图片
        //AppImageDisplay.showImg("blzxjcpt", vehicleInfo.gettp1(), this.getContext(), R.drawable.img_monitor_pic, (ImageView) this.mRoundImageView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_huanjian_detail);
        this.mHuanjianModel = (HuanjianModel) getIntent().getSerializableExtra("huanjian");

        initTitle();
        initView();
        showHuanjian(mHuanjianModel);
    }

    public void convertVehicleInfo(HuanjianModel model) {
        if (!TextUtils.isEmpty(model.getHpys())) {
            switch (model.getHpys()) {
                case "0":
                    model.setHpys("蓝牌");
                    break;
                case "1":
                    model.setHpys("黄牌");
                    break;
                case "2":
                    model.setHpys("白牌");
                    break;
                case "3":
                    model.setHpys("黑牌");
                    break;
                case "4":
                    model.setHpys("绿牌");
                    break;
            }
        }
        if (!TextUtils.isEmpty(model.getTestType())) {
            switch (model.getTestType()) {
                case "1":
                    model.setTestType("双怠速");
                    break;
                case "2":
                    model.setTestType("稳态工况");
                    break;
                case "3":
                    model.setTestType("简易瞬态工况");
                    break;
                case "4":
                    model.setTestType("加载减速");
                    break;
                case "5":
                    model.setTestType("不透光烟度");
                    break;
                case "6":
                    model.setTestType("滤纸烟度");
                    break;
            }
        }
        if (!TextUtils.isEmpty(model.getResult())) {
            switch (model.getResult()) {
                case "0":
                    model.setResult("不合格");
                    break;
                case "1":
                    model.setResult("合格");
                    break;
                case "2":
                    model.setResult("无效");
                    break;
            }
        }
    }


    public String readAssetsTxt() {
        InputStream inputStream = null;
        byte[] arrayOfByte;
        String content = null;

        try {
            inputStream = BaseApplication.getInstance().getAssets().open("car_huanjian_data.txt");
            arrayOfByte = new byte[inputStream.available()];
            inputStream.read(arrayOfByte);
            content = new String(arrayOfByte, StandardCharsets.UTF_8);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return content;

    }


    @Override
    public void setPresenter(BlackDetailPresenter paramStationDetailPresenter) {
    }

    @Override
    public void showAddSh(String paramString) {
        RxBus.get().post(new RefreshBlackCar());
        finish();
    }

    @Override
    public void showFail(String paramString) {
        ToastUtils.shortToast(paramString);
    }

    @Override
    public void showLoading() {
    }
}
