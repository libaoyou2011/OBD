package com.baolong.obd.monitor.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.common.utils.LogUtil;
import com.google.gson.Gson;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.base.BaseFragment;
import com.baolong.obd.R;
import com.baolong.obd.monitor.adapter.AutoViewListAdapter;
import com.baolong.obd.monitor.data.entity.GetMonitoringDataDetailNewModel;
import com.baolong.obd.monitor.data.entity.KeyNameValueListModel;
import com.baolong.obd.monitor.data.entity.KeyNameValueModel;
import com.baolong.obd.monitor.event.UpdateJcEvent;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class StationDetailJcFragment extends BaseFragment {
    private static final String TAG = StationDetailJcFragment.class.getSimpleName();

    private String mFromType = "";
    private String mType = "0";  // "0"：遥测数据  "1"：车辆信息数据
    private Exhaust mExhaust;

    private View mView;
    private ImageView mPassImg;
    private RecyclerView mRecycler;
    private AutoViewListAdapter mAdapter;

    public static StationDetailJcFragment newInstance() {
        return new StationDetailJcFragment();
    }

    @Subscribe
    public void Function(UpdateJcEvent paramUpdateJcEvent) {
        GetMonitoringDataDetailNewModel localGetMonitoringDataDetailNewModel = paramUpdateJcEvent.getModel();
        String assetsText = readAssetsTxt().replace("\n", "").replace("\t", "").replace("\r", "");
        KeyNameValueListModel keyNameValueListModel = null;
        try {
            if (TextUtils.isEmpty(assetsText)) {
                return;
            } else {
                keyNameValueListModel = (KeyNameValueListModel) new Gson().fromJson(assetsText, KeyNameValueListModel.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            keyNameValueListModel = null;
        }
        int i;
        if (keyNameValueListModel != null) {
            Field[] arrayOfField = localGetMonitoringDataDetailNewModel.getClass().getDeclaredFields();
            i = 0;
            while (i < keyNameValueListModel.getItems().size()) {
                int j = 0;
                while (j < arrayOfField.length) {
                    String fieldName = arrayOfField[j].getName();
                    if (((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).getKey().equals(fieldName)) {
                        try {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("get");
                            stringBuilder.append(fieldName);
                            //1.获取该类的Class Type;
                            Class localClass = localGetMonitoringDataDetailNewModel.getClass();
                            //2.通过调用getMethod方法 获取Method对象；
                            Method method = localClass.getMethod(stringBuilder.toString(), new Class[0]);
                            /*LogUtil.i(TAG, method.toString());*/
                            //3.通过调用 invoke方法 来执行对象的某个方法； 方法的反射操作
                            String fieldValue = (String) method.invoke(localGetMonitoringDataDetailNewModel, new Object[0]);
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
        this.mRecycler.setAdapter(this.mAdapter);
        if (this.mType.equals("0")) {
            this.mPassImg.setVisibility(View.VISIBLE);
            if (localGetMonitoringDataDetailNewModel.getpdjg().equals("合格")) {
                this.mPassImg.setImageResource(R.drawable.ic_hg);
            } else {
                this.mPassImg.setImageResource(R.drawable.ic_bhg);
            }
        } else {
            this.mPassImg.setVisibility(View.GONE);
        }
    }

    public void initData() {
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

        //将数据值填充到模板中
        if (keyNameValueListModel != null) {
            if ("0".equals(mType) && mExhaust != null) {
                // 遥测数据
                for (int i = 0; i < keyNameValueListModel.getItems().size(); i++) {
                    switch (i) {
                        case 0:
                            String dwbh = mExhaust.getSiteInfo().getDwbh();
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(dwbh);
                            break;
                        case 1:
                            String jcsj = mExhaust.getJcrq();
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(jcsj);
                            break;
                        case 2:
                            int cdxh = mExhaust.getCdxh();
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(cdxh + "");
                            break;
                        case 3:
                            double cdpd = mExhaust.getCdpd();
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(cdpd + "");
                            break;
                        case 4:
                            String hphm = mExhaust.getHphm();
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(hphm);
                            break;
                        case 5:
                            String cpys = "";   //0-蓝牌 1-黄牌 2-白牌 3-黑牌
                            if ("0".equals(mExhaust.getCpys())) {
                                cpys = "蓝牌";
                            } else if ("1".equals(mExhaust.getCpys())) {
                                cpys = "黄牌";
                            } else if ("2".equals(mExhaust.getCpys())) {
                                cpys = "白牌";
                            } else if ("3".equals(mExhaust.getCpys())) {
                                cpys = "黑牌";
                            }
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(cpys);
                            break;
                        case 6:
                            String rlzl = "";
                            if ("A".equalsIgnoreCase(mExhaust.getRlzl())) {
                                rlzl = "汽油";
                            } else if ("B".equalsIgnoreCase(mExhaust.getRlzl())) {
                                rlzl = "柴油";
                            } else if ("Z".equalsIgnoreCase(mExhaust.getRlzl())) {
                                rlzl = "其他";
                            }
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(rlzl);
                            break;
                        case 7:
                            double clsd = mExhaust.getClsd();
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(clsd + "");
                            break;
                        case 8:
                            double cljsd = mExhaust.getCljsd();
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(cljsd + "");
                            break;
                        case 9:
                            double vsp = mExhaust.getVsp();
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(vsp + "");
                            break;
                        case 10:
                            double co2scz = mExhaust.getCo2scz();
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(co2scz + "");
                            break;
                        case 11:
                            double dqy = mExhaust.getDqy();
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(dqy + "");
                            break;
                        case 12:
                            double fs = mExhaust.getFs();
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(fs + "");
                            break;
                        case 13:
                            String fx = mExhaust.getFx();
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(fx);
                            break;
                        case 14:
                            double sd = mExhaust.getSd();
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(sd + "");
                            break;
                        case 15:
                            double hjwd = mExhaust.getHjwd();
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(hjwd + "");
                            break;
                        case 16:
                            String pdjg = ""; //0：不合格  1:合格   2：无效
                            if ("0".equals(mExhaust.getPdjg())) {
                                pdjg = "不合格";
                            } else if ("1".equals(mExhaust.getPdjg())) {
                                pdjg = "合格";
                            } else if ("2".equals(mExhaust.getPdjg())) {
                                pdjg = "无效";
                            }
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(pdjg);
                            break;
                        case 17:
                            double sbzxd = mExhaust.getSbzxd();
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(sbzxd + "");
                            break;
                        case 18:
                            double coxz = mExhaust.getCoxz();
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(coxz + "");
                            break;
                        case 19:
                            double cojg = mExhaust.getCojg();
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(cojg + "");
                            break;
                        case 20:
                            double noxz = mExhaust.getNoxz();
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(noxz + "");
                            break;
                        case 21:
                            double nojg = mExhaust.getNojg();
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(nojg + "");
                            break;
                    }
                }
            } else if ("1".equals(mType)) {
                // 车辆信息数据
                // Todo
            }
            this.mAdapter.setData(keyNameValueListModel.getItems());
        }
        this.mRecycler.setAdapter(this.mAdapter);

        if (this.mType.equals("0")) { // 是遥测数据  非车辆信息数据
            this.mPassImg.setVisibility(View.VISIBLE);
            if ("1".equals(mExhaust.getPdjg())) { //判定结果：0-不合格 1-合格 2-无效
                this.mPassImg.setImageResource(R.drawable.ic_hg);
            } else {
                this.mPassImg.setImageResource(R.drawable.ic_bhg);
            }
        } else {
            this.mPassImg.setVisibility(View.GONE);
        }
    }

    @Override
    public void setArguments(Bundle paramBundle) {
        super.setArguments(paramBundle);
        this.mFromType = paramBundle.getString("from");
        this.mType = paramBundle.getString("type");
        this.mExhaust = (Exhaust) paramBundle.getParcelable("exhaust");

        LogUtil.i(TAG, "exhaust:" + new Gson().toJson(this.mExhaust));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, Bundle paramBundle) {
        RxBus.get().register(this);
        this.mView = paramLayoutInflater.inflate(R.layout.fragment_station_detail_jc, null);
        this.mRecycler = ((RecyclerView) this.mView.findViewById(R.id.rv_recycler));
        this.mPassImg = ((ImageView) this.mView.findViewById(R.id.img_hg_detail));

        this.mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mAdapter = new AutoViewListAdapter(getContext(), null);
//        KeyNameValueListModel keyNameValueListModel = null;
//        try {
//            String assetsText = readAssetsTxt().replace("\n", "").replace("\t", "").replace("\r", "");
//            keyNameValueListModel = (KeyNameValueListModel) new Gson().fromJson(assetsText, KeyNameValueListModel.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (keyNameValueListModel != null) {
//            this.mAdapter.showWarmingSuccess(keyNameValueListModel.getItems());
//        }
        this.mRecycler.setAdapter(this.mAdapter);

        initData();

        return this.mView;
    }

    @Override
    public void onDestroyView() {
        RxBus.get().unregister(this);
        super.onDestroyView();
    }

    public String readAssetsTxt() {
        String fileName = null;
        InputStream inputStream = null;
        String content = null;

        if (this.mType.equals("0")) {
            fileName = "jc_mock_data.txt";
        } else {
            fileName = "car_mock_data.txt";
        }
        try {
            inputStream = BaseApplication.getInstance().getAssets().open(fileName);
            byte[] arrayOfByte = new byte[inputStream.available()];
            inputStream.read(arrayOfByte);
            content = new String(arrayOfByte, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    public void refresh() {
    }

}
