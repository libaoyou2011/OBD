package com.baolong.obd.blackcar.activity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baolong.obd.R;
import com.baolong.obd.blackcar.adapter.BlcakCarDetailListAdapter;
import com.baolong.obd.blackcar.contract.BlackDetailContract;
import com.baolong.obd.blackcar.data.entity.ExhaustZC;
import com.baolong.obd.blackcar.event.UpdateBlackListEvent;
import com.baolong.obd.blackcar.presenter.BlackDetailPresenter;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.base.Constance;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.monitor.data.entity.KeyNameValueListModel;
import com.baolong.obd.monitor.data.entity.KeyNameValueModel;
import com.google.gson.Gson;
import com.hwangjr.rxbus.RxBus;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;


@Route(path = Constance.ACTIVITY_URL_BlackCarDetailZCActivity)
public class BlackCarDetailZCActivity extends BaseActivity implements BlackDetailContract.View {
    private static final String TAG = "BlackCarDetailActivity";
    private ExhaustZC mExhaust;
    private String mTableType = "";
    private String mOptionType = "";
    private String strJlid;
    private String strJzbh;


    private RecyclerView mRecycler;
    private BlcakCarDetailListAdapter mAdapter;

    private BlackDetailPresenter mBlackDetailPresenter;


    private void initTitle() {
        ((ImageView) findViewById(R.id.image_title_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlackCarDetailZCActivity.this.onBackPressed();
            }
        });

        TextView titleTextView = (TextView) findViewById(R.id.tv_title);
        String titleName = getResources().getString(R.string.monitor_station_detail_title);
        titleTextView.setText(titleName);
        /*if (TextUtils.isEmpty(this.strJzbh)) {
            titleTextView.setText(titleName);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(titleName);
            sb.append("-");
            sb.append(this.mExhaust.getJlbh());
            titleTextView.setText(sb.toString());
        }*/

    }

    private void initView() {
        this.mRecycler = ((RecyclerView) this.findViewById(R.id.rv_recycler));
        this.mRecycler.setLayoutManager(new LinearLayoutManager(this));
        this.mAdapter = new BlcakCarDetailListAdapter(this, null);
        this.mRecycler.setAdapter(this.mAdapter);
    }

    private void initData() {
        this.mBlackDetailPresenter = new BlackDetailPresenter(this);
        //showLoading();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blackcar_zc_detail_activity);

        this.mTableType = getIntent().getStringExtra("tableType");
        this.mOptionType = getIntent().getStringExtra("optionType");
        this.mExhaust = (ExhaustZC) getIntent().getParcelableExtra("exhaust");

        initTitle();
        initView();
        //initData();
        showDetailInfo();
    }

    // 显示车辆尾气数据
    public void showDetailInfo() {
        //加载模板
        KeyNameValueListModel keyNameValueListModel = null;
        String assetsText = readAssetsTxt().replace("\n", "").replace("\t", "").replace("\r", "");
        if (TextUtils.isEmpty(assetsText)) {
            return;
        }
        try {
            keyNameValueListModel = (KeyNameValueListModel) new Gson().fromJson(assetsText, KeyNameValueListModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 具体数据值
        if (mExhaust != null) {
            if ("0".equals(mExhaust.getPdjg())) {
                mExhaust.setPdjg("超标");
            } else if ("1".equals(mExhaust.getPdjg())) {
                mExhaust.setPdjg("合格");
            } else if ("2".equals(mExhaust.getPdjg())) {
                mExhaust.setPdjg("未判定");
            }

        }
        /*BlackCarDetailModel blackCarDetailModel = new BlackCarDetailModel();
        blackCarDetailModel.setJlbh(mExhaust.getJlbh());
        blackCarDetailModel.setDwbh(mExhaust.getSiteInfo().getDwbh());
        blackCarDetailModel.setJcrq(mExhaust.getJcrq());
        blackCarDetailModel.setCdxh(mExhaust.getCdxh() + "");
        blackCarDetailModel.setCdpd(mExhaust.getCdpd() + "");
        blackCarDetailModel.setHphm(mExhaust.getHphm());
        blackCarDetailModel.setBtgdxz(mExhaust.getBtgdxz() + "");
        blackCarDetailModel.setBtgdjg(mExhaust.getBtgdjg() + "");
        blackCarDetailModel.setHdxz(mExhaust.getHdxz() + "");
        blackCarDetailModel.setLgmhd(mExhaust.getLgmhd() + "");
        blackCarDetailModel.setCo2scz(mExhaust.getCo2scz() + "");
        blackCarDetailModel.setSbzxd(mExhaust.getSbzxd());*/

        //将数据值填充到模板中
        if (keyNameValueListModel != null) {
            Field[] fieldArray = mExhaust.getClass().getDeclaredFields();
            int i = 0;
            while (i < keyNameValueListModel.getItems().size()) { //循环模板
                int j = 0;
                while (j < fieldArray.length) { //循环数据字段
                    String fieldName = fieldArray[j].getName();
                    if (((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).getKey().equals(fieldName)) {
                        try {
                            /* 方式一： 方法反射*/
                            /* 方式二： 成员变量反射  更优：dwbh为空时，方法一显示null，方法二显示空*/
                            //1.获取该类的Class Type;
                            Class<?> localClass2 = mExhaust.getClass();
                            //2.通过调用 getDeclaredField 方法, 获得某个属性对象；
                            Field field = localClass2.getDeclaredField(fieldName);
                            field.setAccessible(true);
                            //3.通过调用 get 方法,  获得obj中对应的属性值
                            String fieldValue2 = String.valueOf(field.get(mExhaust));
                            if ("null".equals(fieldValue2)) {
                                fieldValue2 = "--";
                            }
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

        /*if ("0".equals(this.mType)) {
            this.mPassImg.setVisibility(View.VISIBLE);
            if ("1".equals(mExhaust.getPdjg())) {
                this.mPassImg.setImageResource(R.drawable.ic_hg);
            } else {
                this.mPassImg.setImageResource(R.drawable.ic_bhg);
            }
        } else {
            this.mPassImg.setVisibility(View.GONE);
        }*/
    }

    public String readAssetsTxt() {
        String content = null;
        String fileName = null;
        InputStream inputStream = null;
        try {
            fileName = "black_car_data_obd.txt";

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

    @Override
    public void setPresenter(BlackDetailPresenter paramStationDetailPresenter) {
    }

    @Override
    public void showAddSh(String paramString) {
        ToastUtils.longToast(paramString);
        RxBus.get().post(new UpdateBlackListEvent("4"));
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
