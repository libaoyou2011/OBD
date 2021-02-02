package com.baolong.obd.querycar.fragment;

import java.io.InputStream;
import java.io.IOException;
import java.lang.reflect.Field;

import com.baolong.obd.blackcar.data.entity.HuanjianModel;
import com.baolong.obd.blackcar.data.entity.ResponseExhaustListModel;
import com.baolong.obd.blackcar.data.entity.ResponseListModel;
import com.baolong.obd.blackcar.data.entity.VehicleInfo;
import com.baolong.obd.common.utils.LogUtil;
import com.hwangjr.rxbus.RxBus;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import android.text.TextUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

import com.baolong.obd.R;
import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.querycar.data.entity.KeyNameValueListModel;
import com.baolong.obd.querycar.presenter.QueryCarListPresenter;
import com.baolong.obd.common.widget.XCRoundRectImageView;
import com.baolong.obd.querycar.adapter.AutoViewListAdapter;
import com.baolong.obd.querycar.contract.QueryCarListContract;
import com.baolong.obd.common.base.BaseFragment;

/**
 * 车辆基本信息
 */
public class CarDetailFragment extends BaseFragment
        implements ViewPager.OnPageChangeListener, QueryCarListContract.View {
    private static final String TAG = CarDetailFragment.class.getSimpleName();
    private String mHphm;
    private String mCpys;
    KeyNameValueListModel mKeyNameValueListModel;

    private View mView;
    private RecyclerView mRecycler;
    private XCRoundRectImageView mRoundImageView;
    private AutoViewListAdapter mAdapter;
    private QueryCarListPresenter queryCarListPresenter;

    public CarDetailFragment() {
        this.mHphm = "";
        this.mCpys = "";
    }

    public static CarDetailFragment newInstance() {
        return new CarDetailFragment();
    }

    private void initView() {
        this.mRoundImageView = (XCRoundRectImageView) this.mView.findViewById(R.id.img_station_pic);
        this.mRoundImageView.setVisibility(View.GONE);

        this.mRecycler = (RecyclerView) this.mView.findViewById(R.id.rv_recycler);
        this.mRecycler.setLayoutManager((RecyclerView.LayoutManager) new LinearLayoutManager(this.getContext()));
        this.mAdapter = new AutoViewListAdapter(this.getContext(), null);
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

    private void initPresenter() {
        this.queryCarListPresenter = new QueryCarListPresenter(CarDetailFragment.this);
    }

    private void initDate() {
        //this.queryCarListPresenter.getVehicleBasicInfo(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, this.mHphm, "0");
        this.queryCarListPresenter.getVehicleBasicInfo(this.mHphm, this.mCpys);
    }

    @Override
    public void setArguments(final Bundle arguments) {
        super.setArguments(arguments);
        this.mHphm = arguments.getString("hphm");
        this.mCpys = arguments.getString("cpys");
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final Bundle bundle) {
        RxBus.get().register((Object) this);
        this.mView = layoutInflater.inflate(R.layout.fragment_car_detail, (ViewGroup) null);
        this.initView();
        this.initPresenter();
        this.initDate();
        return this.mView;
    }

    public String readAssetsTxt() {
        InputStream inputStream = null;
        byte[] arrayOfByte;
        String content = null;

        try {
            inputStream = BaseApplication.getInstance().getAssets().open("car_mock_data.txt");
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

    public void onPageScrollStateChanged(final int n) {
    }

    public void onPageScrolled(final int n, final float n2, final int n3) {
    }

    public void onPageSelected(final int n) {
    }

    public VehicleInfo convertVehicleInfo(VehicleInfo vehicleInfo) {
        if (!TextUtils.isEmpty(vehicleInfo.getHpys())) {
            switch (vehicleInfo.getHpys()) {
                case "0":
                    vehicleInfo.setHpys("蓝牌");
                    break;
                case "1":
                    vehicleInfo.setHpys("黄牌");
                    break;
                case "2":
                    vehicleInfo.setHpys("白牌");
                    break;
                case "3":
                    vehicleInfo.setHpys("黑牌");
                    break;
                case "4":
                    vehicleInfo.setHpys("绿牌");
                    break;
            }
        }
        if (!TextUtils.isEmpty(vehicleInfo.getRlzl())) {
            switch (vehicleInfo.getRlzl()) {
                case "A":
                    vehicleInfo.setRlzl("汽油");
                    break;
                case "B":
                    vehicleInfo.setRlzl("柴油");
                    break;
                case "Z":
                    vehicleInfo.setRlzl("其他");
                    break;
            }
        }
        if (!TextUtils.isEmpty(vehicleInfo.getSyxz())) {
            switch (vehicleInfo.getSyxz()) {
                case "A":
                    vehicleInfo.setSyxz("非营运");
                    break;
                case "B":
                    vehicleInfo.setSyxz("公路客运");
                    break;
                case "C":
                    vehicleInfo.setSyxz("公交客运");
                    break;
                case "D":
                    vehicleInfo.setSyxz("出租客运");
                    break;
                case "E":
                    vehicleInfo.setSyxz("旅游客运");
                    break;
                case "F":
                    vehicleInfo.setSyxz("货运");
                    break;
                case "G":
                    vehicleInfo.setSyxz("租赁");
                    break;
                case "H":
                    vehicleInfo.setSyxz("警用");
                    break;
                case "I":
                    vehicleInfo.setSyxz("消防");
                    break;
                case "J":
                    vehicleInfo.setSyxz("救护");
                    break;
                case "K":
                    vehicleInfo.setSyxz("工程抢险");
                    break;
                case "L":
                    vehicleInfo.setSyxz("营转非");
                    break;
                case "M":
                    vehicleInfo.setSyxz("出租转非");
                    break;
                case "N":
                    vehicleInfo.setSyxz("教练");
                    break;
                case "O":
                    vehicleInfo.setSyxz("幼儿校车");
                    break;
                case "P":
                    vehicleInfo.setSyxz("小学生校车");
                    break;
                case "Q":
                    vehicleInfo.setSyxz("其他校车");
                    break;
                case "R":
                    vehicleInfo.setSyxz("危化品运输");
                    break;
                case "Z":
                    vehicleInfo.setSyxz("其他");
                    break;
            }
        }
        if (!TextUtils.isEmpty(vehicleInfo.getPfbzjd())) {
            switch (vehicleInfo.getPfbzjd()) {
                case "0":
                    vehicleInfo.setPfbzjd("国〇");
                    break;
                case "1":
                    vehicleInfo.setPfbzjd("国Ⅰ");
                    break;
                case "2":
                    vehicleInfo.setPfbzjd("国Ⅱ");
                    break;
                case "3":
                    vehicleInfo.setPfbzjd("国Ⅲ");
                    break;
                case "4":
                    vehicleInfo.setPfbzjd("国Ⅳ");
                    break;
                case "5":
                    vehicleInfo.setPfbzjd("国Ⅴ");
                    break;
                case "6":
                    vehicleInfo.setPfbzjd("国Ⅵ");
                    break;
            }
        }
        return vehicleInfo;
    }


    public void setPresenter(final QueryCarListPresenter queryCarListPresenter) {
    }

    public void hideLoading() {
    }

    @Override
    public void refresh() {
    }

    @Override
    public void showCarInfo(VehicleInfo vehicleInfo) {
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
                                final Class<? extends VehicleInfo> class1 = vehicleInfo.getClass();
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
    public void showJyListData(ResponseListModel<HuanjianModel> p0) {

    }

    @Override
    public void showYcListData(ResponseExhaustListModel p0) {

    }

    @Override
    public void showCbListData(ResponseExhaustListModel p0) {

    }

    public void showLoading() {
    }

    public void showFail(final String s) {
    }

}

