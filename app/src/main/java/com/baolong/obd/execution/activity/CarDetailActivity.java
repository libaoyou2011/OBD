package com.baolong.obd.execution.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baolong.obd.R;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.base.Constance;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.common.widget.DialogManager;
import com.baolong.obd.execution.data.entity.OBDCar;
import com.baolong.obd.execution.data.entity.SpinnerItemMode;
import com.baolong.obd.execution.view.FormItemArrayView;
import com.baolong.obd.execution.view.FormItemEditView;
import com.baolong.obd.execution.view.FormItemTimeView;
import com.baolong.obd.execution.view.IFormItem;
import com.baolong.obd.monitor.data.entity.KeyNameValueListModel;
import com.baolong.obd.monitor.data.entity.KeyNameValueModel;
import com.google.gson.Gson;
import com.hwangjr.rxbus.RxBus;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

@Route(path = Constance.ACTIVITY_URL_CarDetailActivity)
public class CarDetailActivity extends BaseActivity  {
    private static final String TAG = CarDetailActivity.class.getSimpleName();
    private OBDCar mExhaust;
    private boolean isOnlyDisplay = true; //true: 预览   false：新增/修改

    // private RecyclerView mRecycler;
    // private CarDetailListAdapter mAdapter;
    LinearLayout form1LL;
    LinearLayout form2LL;
    LinearLayout form3LL;

    KeyNameValueListModel keyNameValueListModel;
    //缓存可修改的 item
    ArrayList<IFormItem> canEditItems;

    // 请求数据
    //private CarsMainPresenter mBlackDetailPresenter;


    private void initTitle() {
        // left
        ((ImageView) findViewById(R.id.image_title_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarDetailActivity.this.onBackPressed();
            }
        });

        // title
        TextView titleTextView = (TextView) findViewById(R.id.tv_title);
        String titleName = getResources().getString(R.string.cars_detail_title);
        titleTextView.setText(titleName);

        // right
        TextView mTopRightTv = ((TextView) this.findViewById(R.id.tv_right_text));
        mTopRightTv.setVisibility(View.INVISIBLE);
//        if (isOnlyDisplay) {
//            mTopRightTv.setText(R.string.cars_detail_right_edit);
//        } else {
//            mTopRightTv.setText(R.string.cars_detail_right_submit);
//        }
//        mTopRightTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isOnlyDisplay) {
//                    isOnlyDisplay = false;
//                    mTopRightTv.setText(R.string.cars_detail_right_submit);
//                    for (IFormItem canEditItem : canEditItems) {
//                        canEditItem.setItemEnable(true);
//                    }
//                } else {
//                    showConfirmSubmit();
//                }
//            }
//        });

    }

    private void showConfirmSubmit() {
        DialogManager.showConfirmDialog(CarDetailActivity.this
                , "您确定提交修改信息?"
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }
                , new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        submit();
                    }
                }
        ).show();
    }


    private void submit() {
        List<KeyNameValueModel> items = keyNameValueListModel.getItems();
        if (items != null) {
            // 方法一：JSONObject.toString()
            JSONObject tempJSONObject = new JSONObject();
            for (KeyNameValueModel item : items) {
                try {
                    tempJSONObject.put(item.getKey(), item.getValue());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), tempJSONObject.toString());
            //mBlackDetailPresenter.editCarInfo(requestBody);
        }
    }

    private void initView() {
        form1LL = (LinearLayout) this.findViewById(R.id.ll_form1_container);
        form2LL = (LinearLayout) this.findViewById(R.id.ll_form2_container);
        form3LL = (LinearLayout) this.findViewById(R.id.ll_form3_container);

//        this.mRecycler = ((RecyclerView) this.findViewById(R.id.rv_recycler));
//        this.mRecycler.setLayoutManager(new LinearLayoutManager(this));
//        this.mAdapter = new CarDetailListAdapter(this, null);
//        this.mRecycler.setAdapter(this.mAdapter);
    }

    private void initPresenter() {
        //this.mBlackDetailPresenter = new CarsMainPresenter(this);
        //showLoading();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_detail_activity);

        this.isOnlyDisplay = getIntent().getBooleanExtra("optionType", true);
        this.mExhaust = (OBDCar) getIntent().getParcelableExtra("exhaust");

        if (mExhaust == null) {
            this.mExhaust = new OBDCar();
        }
        canEditItems = new ArrayList<>();

        initTitle();
        initView();
        showDetailInfo();
        //initPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // 显示车辆尾气数据
    public void showDetailInfo() {
        //加载模板
        String assetsText;
        try {
            assetsText = readAssetsTxt("obd_car_detail.txt").replace("\n", "").replace("\t", "").replace("\r", "");
            if (TextUtils.isEmpty(assetsText)) {
                return;
            }
            keyNameValueListModel = (KeyNameValueListModel) new Gson().fromJson(assetsText, KeyNameValueListModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
                                fieldValue2 = "";
                            }
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(i)).setValue(fieldValue2.toString());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    j += 1;
                }
                i += 1;
            }

            int titleEnableColor = getResources().getColor(R.color.form_item_title_edit);
            // 1.终端信息
            for (int k = 0; k < 2; k++) {
                FormItemEditView formItemEditView = new FormItemEditView(this);
                formItemEditView.setItem_key(keyNameValueListModel.getItems().get(k).getKey(), k);
                formItemEditView.setItem_title(keyNameValueListModel.getItems().get(k).getName());
                formItemEditView.setItem_content(keyNameValueListModel.getItems().get(k).getValue());
                formItemEditView.setEnableTitleColor(titleEnableColor);
                formItemEditView.setItemEnable(false);
                formItemEditView.setItemClickListener(new FormItemEditView.OnItemClickListener() {
                    @Override
                    public void onItemClick(String key, String value, int position) {
                        LogUtil.i(TAG, key + "1:" + value);
                        ((KeyNameValueModel) keyNameValueListModel.getItems().get(position)).setValue(value);
                        LogUtil.i(TAG, key + "2:" + ((KeyNameValueModel) keyNameValueListModel.getItems().get(position)).getValue());

                    }
                });
                form1LL.addView(formItemEditView);
            }
            // 2.车辆信息
            for (int k = 2; k < 15; k++) {
                if (k == 6) {
                    //日期选择
                    FormItemTimeView timeView = new FormItemTimeView(this);
                    timeView.setItem_key(keyNameValueListModel.getItems().get(k).getKey(), k);
                    timeView.setItem_title(keyNameValueListModel.getItems().get(k).getName());
                    timeView.setItem_content(keyNameValueListModel.getItems().get(k).getValue());
                    timeView.setEnableTitleColor(titleEnableColor);
                    timeView.setItemEnable(false);
                    timeView.setItemClickListener(new FormItemTimeView.OnItemClickListener() {
                        @Override
                        public void onItemClick(String key, String value, int position) {
                            LogUtil.i(TAG, key + "1:" + value);
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(position)).setValue(value);
                            LogUtil.i(TAG, key + "2:" + ((KeyNameValueModel) keyNameValueListModel.getItems().get(position)).getValue());
                        }
                    });
                    form2LL.addView(timeView);
                    canEditItems.add((IFormItem) timeView);
                } else if (k == 8) {
                    // 下拉选择
                    List<SpinnerItemMode> options1Items = new ArrayList<>();
                    options1Items.clear();
                    options1Items.add(new SpinnerItemMode("0", "国〇"));
                    options1Items.add(new SpinnerItemMode("1", "国Ⅰ"));
                    options1Items.add(new SpinnerItemMode("2", "国Ⅱ"));
                    options1Items.add(new SpinnerItemMode("3", "国Ⅲ"));
                    options1Items.add(new SpinnerItemMode("4", "国Ⅳ"));
                    options1Items.add(new SpinnerItemMode("5", "国Ⅴ"));
                    options1Items.add(new SpinnerItemMode("6", "国Ⅵ"));

                    String tempContent = keyNameValueListModel.getItems().get(k).getValue();
                    for (SpinnerItemMode itemMode : options1Items) {
                        if (itemMode.GetID().equals(tempContent)) {
                            tempContent = itemMode.GetValue();
                        }
                    }

                    FormItemArrayView timeView = new FormItemArrayView(this);
                    timeView.setItem_key(keyNameValueListModel.getItems().get(k).getKey(), k);
                    timeView.setItem_list(options1Items);
                    timeView.setItem_title(keyNameValueListModel.getItems().get(k).getName());
                    timeView.setItem_content(tempContent);
                    timeView.setEnableTitleColor(titleEnableColor);
                    timeView.setItemEnable(false);
                    timeView.setItemClickListener(new FormItemArrayView.OnItemClickListener() {
                        @Override
                        public void onItemClick(String key, String value, int position) {
                            LogUtil.i(TAG, key + "1:" + value);
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(position)).setValue(value);
                            LogUtil.i(TAG, key + "2:" + ((KeyNameValueModel) keyNameValueListModel.getItems().get(position)).getValue());
                        }
                    });

                    form2LL.addView(timeView);
                    canEditItems.add((IFormItem) timeView);
                } else if (k == 9) {
                    // 下拉选择
                    List<SpinnerItemMode> options1Items = new ArrayList<>();
                    options1Items.clear();
                    options1Items.add(new SpinnerItemMode("A", "汽油"));
                    options1Items.add(new SpinnerItemMode("B", "柴油"));
                    options1Items.add(new SpinnerItemMode("C", "电"));
                    options1Items.add(new SpinnerItemMode("Z", "其他"));
                    // 文本显示原数据
                    String tempContent = keyNameValueListModel.getItems().get(k).getValue();
                    for (SpinnerItemMode itemMode : options1Items) {
                        if (itemMode.GetID().equals(tempContent)) {
                            tempContent = itemMode.GetValue();
                        }
                    }
                    // 文本点击弹出的选择弹窗
                    FormItemArrayView timeView = new FormItemArrayView(this);
                    timeView.setItem_key(keyNameValueListModel.getItems().get(k).getKey(), k);
                    timeView.setItem_list(options1Items);
                    timeView.setItem_title(keyNameValueListModel.getItems().get(k).getName());
                    timeView.setItem_content(tempContent);
                    timeView.setEnableTitleColor(titleEnableColor);
                    timeView.setItemEnable(false);
                    timeView.setItemClickListener(new FormItemArrayView.OnItemClickListener() {
                        @Override
                        public void onItemClick(String key, String value, int position) {
                            LogUtil.i(TAG, key + "1:" + value);
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(position)).setValue(value);
                            LogUtil.i(TAG, key + "2:" + ((KeyNameValueModel) keyNameValueListModel.getItems().get(position)).getValue());
                        }
                    });
                    form2LL.addView(timeView);
                    canEditItems.add((IFormItem) timeView);
                } else {
                    FormItemEditView formItemEditView = new FormItemEditView(this);
                    formItemEditView.setItem_key(keyNameValueListModel.getItems().get(k).getKey(), k);
                    formItemEditView.setItem_title(keyNameValueListModel.getItems().get(k).getName());
                    formItemEditView.setItem_content(keyNameValueListModel.getItems().get(k).getValue());
                    formItemEditView.setEnableTitleColor(titleEnableColor);
                    formItemEditView.setItemEnable(false);
                    formItemEditView.setItemClickListener(new FormItemEditView.OnItemClickListener() {
                        @Override
                        public void onItemClick(String key, String value, int position) {
                            LogUtil.i(TAG, key + "1:" + value);
                            ((KeyNameValueModel) keyNameValueListModel.getItems().get(position)).setValue(value);
                            LogUtil.i(TAG, key + "2:" + ((KeyNameValueModel) keyNameValueListModel.getItems().get(position)).getValue());
                        }
                    });
                    form2LL.addView(formItemEditView);
                    if (k < 13) {
                        canEditItems.add((IFormItem) formItemEditView);
                    }
                }
            }
            // 3.企业信息
            for (int k = 15; k < keyNameValueListModel.getItems().size(); k++) {
                FormItemEditView formItemEditView = new FormItemEditView(this);
                formItemEditView.setItem_key(keyNameValueListModel.getItems().get(k).getKey(), k);
                formItemEditView.setItem_title(keyNameValueListModel.getItems().get(k).getName());
                formItemEditView.setItem_content(keyNameValueListModel.getItems().get(k).getValue());
                formItemEditView.setEnableTitleColor(titleEnableColor);
                formItemEditView.setItemEnable(false);
                formItemEditView.setItemClickListener(new FormItemEditView.OnItemClickListener() {
                    @Override
                    public void onItemClick(String key, String value, int position) {
                        LogUtil.i(TAG, key + "1:" + value);
                        ((KeyNameValueModel) keyNameValueListModel.getItems().get(position)).setValue(value);
                        LogUtil.i(TAG, key + "2:" + ((KeyNameValueModel) keyNameValueListModel.getItems().get(position)).getValue());
                    }
                });
                form3LL.addView(formItemEditView);
            }

        }

    }

    public String readAssetsTxt(String fileName) {
        String content = null;
        InputStream inputStream = null;
        try {
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


}
