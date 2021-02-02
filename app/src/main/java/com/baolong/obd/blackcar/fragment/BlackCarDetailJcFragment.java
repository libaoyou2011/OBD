package com.baolong.obd.blackcar.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baolong.obd.R;
import com.baolong.obd.blackcar.adapter.BlcakCarDetailListAdapter;
import com.baolong.obd.blackcar.contract.VehicleInfoContract;
import com.baolong.obd.blackcar.data.entity.BlackCarDetailModel;
import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.blackcar.data.entity.VehicleInfo;
import com.baolong.obd.blackcar.presenter.VehicleInfoPresenter;
import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.base.BaseFragment;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.monitor.data.entity.KeyNameValueListModel;
import com.baolong.obd.monitor.data.entity.KeyNameValueModel;
import com.google.gson.Gson;
import com.hwangjr.rxbus.RxBus;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static com.baolong.obd.blackcar.fragment.BlackCarMainFragment.Table_hg;
import static com.baolong.obd.blackcar.fragment.BlackCarMainFragment.Table_wpd;
import static com.baolong.obd.blackcar.fragment.BlackCarMainFragment.Table_cb;

public class BlackCarDetailJcFragment extends BaseFragment implements VehicleInfoContract.View {
    private static final String TAG = BlackCarDetailJcFragment.class.getSimpleName();

    private String mFromType = "";
    private String mType = "0";  // 0：检测数据； 1：车辆信息
    private Exhaust mExhaust;

    private View mView;
    private ImageView mPassImg;
    private RecyclerView mRecycler;
    private BlcakCarDetailListAdapter mAdapter;

    private VehicleInfoPresenter mVehicleInfoPresenter;
    VehicleInfo mVehicleInfo = new VehicleInfo();


    /*public static BlackCarDetailJcFragment newInstance() {
        return new BlackCarDetailJcFragment();
    }

    @Override
    public void setArguments(Bundle paramBundle) {
        super.setArguments(paramBundle);
        this.mFromType = paramBundle.getString("from");
        this.mType = paramBundle.getString("type");
        this.mExhaust = (Exhaust) paramBundle.getSerializable("exhaust");

        LogUtil.i(TAG, this.mExhaust.getJlbh());
        LogUtil.i(TAG, new Gson().toJson(this.mExhaust));

    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, Bundle paramBundle) {
        RxBus.get().register(this);

        //从Activity获取传递的数据
        this.mFromType = getArguments().getString("from");
        this.mType = getArguments().getString("type");
        this.mExhaust = (Exhaust) getArguments().getParcelable("exhaust");
        LogUtil.i(TAG, this.mExhaust.getJlbh());
        LogUtil.i(TAG, new Gson().toJson(this.mExhaust));

        this.mView = paramLayoutInflater.inflate(R.layout.fragment_station_detail_jc, null);
        this.mRecycler = ((RecyclerView) this.mView.findViewById(R.id.rv_recycler));
        this.mPassImg = ((ImageView) this.mView.findViewById(R.id.img_hg_detail));

        this.mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mAdapter = new BlcakCarDetailListAdapter(getContext(), null);
        this.mRecycler.setAdapter(this.mAdapter);

        if ("0".equals(this.mType)) {
            // 显示黑烟数据
            showGatherData();
        } else {
            // 显示车辆信息，保证车辆为空也显示字段
            showVehicleData();

            // 显示车辆信息
            initPresent();
            getVehicleInfoData();
        }

        return this.mView;
    }

    @Override
    public void onDestroyView() {
        RxBus.get().unregister(this);
        super.onDestroyView();
    }

    public void initPresent() {
        this.mVehicleInfoPresenter = new VehicleInfoPresenter(this);
    }

    public void getVehicleInfoData() {
        if (mExhaust != null) {
            //真实调用
            this.mVehicleInfoPresenter.getVehicleInfo(mExhaust.getHphm(), mExhaust.getCpys());

            // 测试接口 服务器中有此车的基本信息
            //this.mVehicleInfoPresenter.getVehicleInfo("皖AF983F", "0");

            // 模拟
            //mVehicleInfo.setHphm(mExhaust.getHphm());
            //mVehicleInfo.setHpzl(mExhaust.getHpzl());
            showVehicleData();

        }

    }

    // 显示车辆尾气数据
    public void showGatherData() {
        //加载模板
        KeyNameValueListModel keyNameValueListModel = null;
        String assetsText = readAssetsTxt().replace("\n", "").replace("\t", "").replace("\r", "");
        try {
            if (TextUtils.isEmpty(assetsText)) {
                return;
            } else {
                keyNameValueListModel = (KeyNameValueListModel) new Gson().fromJson(assetsText, KeyNameValueListModel.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 具体数据值
        BlackCarDetailModel blackCarDetailModel = new BlackCarDetailModel();
        blackCarDetailModel.setJlbh(mExhaust.getJlbh());
        blackCarDetailModel.setDwbh(mExhaust.getSiteInfo().getDwbh());
        blackCarDetailModel.setJcrq(mExhaust.getJcrq());
        blackCarDetailModel.setCdxh(mExhaust.getCdxh() + "");
        blackCarDetailModel.setCdpd(mExhaust.getCdpd() + "");
        blackCarDetailModel.setHphm(mExhaust.getHphm());
        if (!TextUtils.isEmpty(mExhaust.getCpys())) {
            //0-蓝牌 1-黄牌 2-白牌 3-黑牌  4=绿牌 (-6-)
            if ("0".equals(mExhaust.getCpys())) {
                blackCarDetailModel.setCpys("蓝牌");
            } else if ("1".equals(mExhaust.getCpys())) {
                blackCarDetailModel.setCpys("黄牌");
            } else if ("2".equals(mExhaust.getCpys())) {
                blackCarDetailModel.setCpys("白牌");
            } else if ("3".equals(mExhaust.getCpys())) {
                blackCarDetailModel.setCpys("黑牌");
            } else if ("4".equals(mExhaust.getCpys())) {
                blackCarDetailModel.setCpys("绿牌");
            } else {
                blackCarDetailModel.setCpys("——牌");
            }
        }
        blackCarDetailModel.setBtgdxz(mExhaust.getBtgdxz() + "");
        blackCarDetailModel.setBtgdjg(mExhaust.getBtgdjg() + "");
        blackCarDetailModel.setHdxz(mExhaust.getHdxz() + "");
        blackCarDetailModel.setLgmhd(mExhaust.getLgmhd() + "");
        // 是否是黑烟车 (0:不是黑烟车; 1：是黑烟车)
//V3报错
//        if (mExhaust.getIsBlackCar() == 0){
//            blackCarDetailModel.setIsBlackCar("否");
//        } else if (mExhaust.getIsBlackCar() == 1){
//            blackCarDetailModel.setIsBlackCar("是");
//        } else {
//            blackCarDetailModel.setIsBlackCar("");
//        }
        blackCarDetailModel.setCo2scz(mExhaust.getCo2scz() + "");
        blackCarDetailModel.setSbzxd(mExhaust.getSbzxd());


        //将数据值填充到模板中
        if (keyNameValueListModel != null) {
//            LogUtil.i(TAG, "开始：" + DateUtils.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            Field[] fieldArray = blackCarDetailModel.getClass().getDeclaredFields();
            int i = 0;
            while (i < keyNameValueListModel.getItems().size()) { //循环模板
                int j = 0;
                while (j < fieldArray.length) { //循环数据字段
                    String fieldName = fieldArray[j].getName();
                    if (((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).getKey().equals(fieldName)) {
                        try {
                             /* 方式一： 方法反射*/
//                            StringBuilder stringBuilder = new StringBuilder();
//                            stringBuilder.append("get");
//                            String tempFieldName = fieldName.substring(0, 1).toUpperCase().concat(fieldName.substring(1));
//                            stringBuilder.append(tempFieldName);
//                            //1.获取该类的Class Type;
//                            Class<?> localClass = blackCarDetailModel.getClass();
//                            //2.通过调用getMethod方法 获取Method对象；
//                            Method method = localClass.getMethod(stringBuilder.toString());
//                            /*LogUtil.i(TAG, method.toString());*/
//                            //3.通过调用 invoke方法 来执行对象的某个方法； 方法的反射操作
//                            String fieldValue = "" +  method.invoke(blackCarDetailModel);
//                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(fieldValue);

                            /* 方式二： 成员变量反射
                            更优：dwbh为空时，方法一显示null，方法二显示空*/
                            //1.获取该类的Class Type;
                            Class<?> localClass2 =blackCarDetailModel.getClass();
                            //2.通过调用 getDeclaredField 方法, 获得某个属性对象；
                            Field field = localClass2.getDeclaredField(fieldName);
                            field.setAccessible(true);
                            //3.通过调用 get 方法,  获得obj中对应的属性值
                            String fieldValue2 = (String) field.get(blackCarDetailModel);
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(fieldValue2);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    j += 1;
                }
                i += 1;
            }
//            LogUtil.i(TAG, "结束：" + DateUtils.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            this.mAdapter.setData(keyNameValueListModel.getItems());
        }

        if ("0".equals(this.mType)) {
            this.mPassImg.setVisibility(View.VISIBLE);
            if ("1".equals(mExhaust.getPdjg())) {
                this.mPassImg.setImageResource(R.drawable.ic_hg);
            } else {
                this.mPassImg.setImageResource(R.drawable.ic_bhg);
            }
        } else {
            this.mPassImg.setVisibility(View.GONE);
        }
    }

    //显示车辆信息库数据
    public void showVehicleData() {
        //加载模板
        KeyNameValueListModel keyNameValueListModel = null;
        String assetsText = readAssetsTxt().replace("\n", "").replace("\t", "").replace("\r", "");
        try {
            if (TextUtils.isEmpty(assetsText)) {
                return;
            } else {
                keyNameValueListModel = (KeyNameValueListModel) new Gson().fromJson(assetsText, KeyNameValueListModel.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 具体数据值
        // Todo nothing


        //将数据值填充到模板中
        if (keyNameValueListModel != null) {
            Field[] fieldArray = mVehicleInfo.getClass().getDeclaredFields();
            int i = 0;
            while (i < keyNameValueListModel.getItems().size()) { //循环模板
                int j = 0;
                while (j < fieldArray.length) { //循环数据字段
                    String fieldName = fieldArray[j].getName();
                    if (((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).getKey().equals(fieldName)) {
                        try {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("get");
                            String tempFieldName = fieldName.substring(0, 1).toUpperCase().concat(fieldName.substring(1));
                            stringBuilder.append(tempFieldName);
                            //1.获取该类的Class Type;
                            Class localClass = mVehicleInfo.getClass();
                            //2.通过调用getMethod方法 获取Method对象；
                            Method method = localClass.getMethod(stringBuilder.toString(), new Class[0]);
                            /*LogUtil.i(TAG, method.toString());*/
                            //3.通过调用 invoke方法 来执行对象的某个方法； 方法的反射操作
                            String fieldValue = (String) method.invoke(mVehicleInfo, new Object[0]);
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(fieldValue);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    j += 1;
                }
                i += 1;
            }
            this.mAdapter.setData(keyNameValueListModel.getItems());
        }

        if ("0".equals(this.mType)) {
            this.mPassImg.setVisibility(View.VISIBLE);
            if ("1".equals(mExhaust.getPdjg())) {
                this.mPassImg.setImageResource(R.drawable.ic_hg);
            } else {
                this.mPassImg.setImageResource(R.drawable.ic_bhg);
            }
        } else {
            this.mPassImg.setVisibility(View.GONE);
        }
    }

    public String readAssetsTxt() {
        String fileName = null;
        InputStream inputStream = null;
        String content = null;
        try {
            if (this.mType.equals("0")) {
                // 黑烟数据
                if (Table_hg.equals(this.mFromType)) {
                    fileName = "black_car_data_wsh.txt";
                } else if (Table_cb.equals(this.mFromType)) {
                    fileName = "black_car_data_ysh.txt";
                } else if (Table_wpd.equals(this.mFromType)) {
                    fileName = "black_car_data_wsh.txt";
                }
            } else {
                // 车辆基本信息
                fileName = "car_mock_data.txt";
            }

            inputStream = BaseApplication.getInstance().getAssets().open(fileName);
            byte[] arrayOfByte = new byte[inputStream.available()];
            inputStream.read(arrayOfByte);
            content = new String(arrayOfByte, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
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

    public void refresh() {
    }

    @Override
    public void setPresenter(VehicleInfoContract.Presenter paramT) {

    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showFail(String s) {
        ToastUtils.shortToast(s);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void showVehicleInfo(VehicleInfo paramVehicleInfo) {
        if (paramVehicleInfo != null) {
            this.mVehicleInfo = paramVehicleInfo;
            showVehicleData();
        }
    }
}
