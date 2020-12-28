package com.baolong.obd.blackcar.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baolong.obd.R;
import com.baolong.obd.blackcar.contract.BlackDetailContract;
import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.blackcar.event.UpdateBlackListEvent;
import com.baolong.obd.blackcar.fragment.BlackCarDetailJcFragment;
import com.baolong.obd.blackcar.presenter.BlackDetailPresenter;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.base.Constance;
import com.baolong.obd.common.utils.CommonUtils;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.common.widget.DialogManager;
import com.baolong.obd.monitor.adapter.ViewPagerFragmentAdapter;
import com.baolong.obd.monitor.fragment.ImageFragment;
import com.baolong.obd.monitor.fragment.VideoFragment;
import com.baolong.obd.querycar.fragment.CarDetailFragment;
import com.hwangjr.rxbus.RxBus;
import com.jaredrummler.materialspinner.MaterialSpinner;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.baolong.obd.blackcar.fragment.BlackCarListFragment.OptionType_Judge;
import static com.baolong.obd.blackcar.fragment.BlackCarListFragment.OptionType_Review;

@Route(path = Constance.ACTIVITY_URL_BlackCarDetailActivity)
public class BlackCarDetailActivity extends BaseActivity implements BlackDetailContract.View {
    private static final String TAG = "BlackCarDetailActivity";
    private Exhaust mExhaust;
    private String mTableType = "";
    private String mOptionType = "";
    private String strJlid;
    private String strJzbh;


    String[] sfmhyArray = {"是", "否"};
    String[] shztArra = {"未审核", "审核通过", "审核未通过"};
    String sfmhy = "1";  //是否冒黑烟 0 否 1 是
    String shzt = "0";        //审核状态 0 未审核 1 审核通过 2 审核未通过
    String remake;      //审核过程备注
    private LinearLayout mLlConfirm;
    private MaterialSpinner mSfmhySpinner; //是否冒黑烟
    private MaterialSpinner mShztSpinner;//审核状态
    private Button mConfirm;


    private ArrayList<Fragment> fragmentsPic;
    private ArrayList<Fragment> fragmentsCarList;

    private TabLayout mTab;
    private ViewPager mViewPagerPic;
    private ViewPager mViewPagerJcCar;
    private FragmentPagerAdapter pageAdapterPic;
    private FragmentPagerAdapter pagerAdapterJcCar;

    private BlackDetailPresenter mBlackDetailPresenter;

/*    private void initMagicIndicator3() {
        MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator4);
        ScaleCircleNavigator scaleCircleNavigator = new ScaleCircleNavigator(this);
        scaleCircleNavigator.setCircleCount(this.fragmentsPic.size());
        scaleCircleNavigator.setNormalCircleColor(Color.LTGRAY);
        scaleCircleNavigator.setSelectedCircleColor(Color.DKGRAY);
        scaleCircleNavigator.setCircleClickListener(new ScaleCircleNavigator.OnCircleClickListener() {
            public void onClick(int index) {
                BlackCarDetailActivity.this.mViewPagerPic.setCurrentItem(index);
            }
        });
        magicIndicator.setNavigator(scaleCircleNavigator);
        ViewPagerHelper.bind(magicIndicator, this.mViewPagerPic);
    }*/

    /**
     * 初始化MagicIndicator
     */
    private void initMagicIndicator1() {
        MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator4);
        CircleNavigator circleNavigator = new CircleNavigator(this);
        circleNavigator.setCircleCount(this.fragmentsPic.size());
        circleNavigator.setCircleColor(Color.WHITE);
        circleNavigator.setCircleClickListener(new CircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                mViewPagerPic.setCurrentItem(index);
            }
        });
        magicIndicator.setNavigator(circleNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPagerPic);
    }

    private void initTitle() {
        ((ImageView) findViewById(R.id.image_title_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlackCarDetailActivity.this.onBackPressed();
            }
        });

        TextView titleTextView = (TextView) findViewById(R.id.tv_title);
        String titleName = getResources().getString(R.string.monitor_station_detail_title);
        titleTextView.setText(titleName);

//        if (TextUtils.isEmpty(this.strJzbh)) {
//            titleTextView.setText(titleName);
//        } else {
//            StringBuilder sb = new StringBuilder();
//            sb.append(titleName);
//            sb.append("-");
//            sb.append(this.mExhaust.getJlbh());
//            titleTextView.setText(sb.toString());
//        }
    }

    private void initView() {
        this.mViewPagerPic = (ViewPager) findViewById(R.id.vp_pic);
        this.mLlConfirm = (LinearLayout) findViewById(R.id.ll_confirm);
        this.mConfirm = (Button) findViewById(R.id.btn_commit);
        this.mSfmhySpinner = (MaterialSpinner) findViewById(R.id.spinner_sfmhy);
        this.mShztSpinner = (MaterialSpinner) findViewById(R.id.spinner_shzt);
        mSfmhySpinner.setItems(sfmhyArray);
        mShztSpinner.setItems(shztArra);
        mSfmhySpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                switch (position) {
                    case 0:
                        sfmhy = "1";
                        break;
                    case 1:
                        sfmhy = "0";
                        break;
                }
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
        mShztSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                switch (position) {
                    case 0:
                        shzt = "0";
                        break;
                    case 1:
                        shzt = "1";
                        break;
                    case 2:
                        shzt = "2";
                        break;
                }
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });


        //将所有的图片拼接到一起
        StringBuilder urlSB = new StringBuilder();
        if (!TextUtils.isEmpty(mExhaust.getTp1())) {
            urlSB.append(mExhaust.getTp1());
        }
        if (!TextUtils.isEmpty(mExhaust.getTp2())) {
            if (!TextUtils.isEmpty(urlSB.toString())) {
                urlSB.append(";");
            }
            urlSB.append(mExhaust.getTp2());
        }
        if (!TextUtils.isEmpty(mExhaust.getTp3())) {
            if (!TextUtils.isEmpty(urlSB.toString())) {
                urlSB.append(";");
            }
            urlSB.append(mExhaust.getTp3());
        }
        if (!TextUtils.isEmpty(mExhaust.getTp4())) {
            if (!TextUtils.isEmpty(urlSB.toString())) {
                urlSB.append(";");
            }
            urlSB.append(mExhaust.getTp4());
        }
        if (!TextUtils.isEmpty(mExhaust.getTp5())) {
            if (!TextUtils.isEmpty(urlSB.toString())) {
                urlSB.append(";");
            }
            urlSB.append(mExhaust.getTp5());
        }
        // 有效图片集合
        String[] urlArray = urlSB.toString().split(";");
        // 图片至少显示 2张，如果小于 2张时用默认图片代替
        int showPictureMinNum = 2;
        if (urlArray.length > 2) {
            showPictureMinNum = urlArray.length;
        }

        this.fragmentsPic = new ArrayList<>();
        Fragment fragment;
        //展示图片的ImageFragment
        for (int i = 0; i < showPictureMinNum; i++) {
            fragment = new ImageFragment();
            Bundle arguments = new Bundle();
            if (i < urlArray.length) {
                arguments.putString("image", urlArray[0]);
            } else {
                arguments.putString("image", null);
            }
            arguments.putInt("index", i);
            arguments.putBoolean("isVideo", false);
            ((ImageFragment) fragment).setArguments(arguments);

            this.fragmentsPic.add(fragment);
        }
        //展示视频的VideoFragment
        fragment = new VideoFragment();
        Bundle arguments2 = new Bundle();
        arguments2.putString("image", mExhaust.getSp1());
        arguments2.putInt("index", showPictureMinNum);
        arguments2.putBoolean("isVideo", true);
        ((VideoFragment) fragment).setArguments(arguments2);
        this.fragmentsPic.add(fragment);

        this.pageAdapterPic = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return BlackCarDetailActivity.this.fragmentsPic.size();
            }

            @Override
            public Fragment getItem(int position) {
                return (Fragment) BlackCarDetailActivity.this.fragmentsPic.get(position);
            }
        };
        this.mViewPagerPic.setAdapter(this.pageAdapterPic);
        this.mViewPagerPic.setOffscreenPageLimit(2);

        //初始化 图片索引指示器
        initMagicIndicator1();

        this.mTab = ((TabLayout) findViewById(R.id.tab_jc_car));
        this.mViewPagerJcCar = ((ViewPager) findViewById(R.id.vp_jc_car));
        final LinearLayout linearLayout = (LinearLayout) this.mTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.layout_divider_vertical));
        linearLayout.setDividerPadding((int) CommonUtils.DpToPx((Context) this, 10.0f));

        // 原本：监测结果、车辆信息 原本在同一个fragment中通过判断显示
        // 改成：车辆信息用车辆查询中的 CarDetailFragment 显示
        this.fragmentsCarList = new ArrayList<>();
        BlackCarDetailJcFragment detailJcFragment1 = new BlackCarDetailJcFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("from", this.mTableType);
        bundle1.putString("type", "0");   //黑烟数据
        bundle1.putParcelable("exhaust", mExhaust);
        detailJcFragment1.setArguments(bundle1);
        this.fragmentsCarList.add(detailJcFragment1);

        /*BlackCarDetailJcFragment detailJcFragment2 = new BlackCarDetailJcFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("from", this.mTableType);
        bundle2.putString("type", "1");  //车辆信息数据
        bundle2.putSerializable("exhaust", mExhaust);
        detailJcFragment2.setArguments(bundle2);
        this.fragmentsCarList.add(detailJcFragment2);*/

        CarDetailFragment carDetailFragment = new CarDetailFragment();  //车辆信息数据
        Bundle arguments = new Bundle();
        if (mExhaust != null) {
            arguments.putString("hphm", mExhaust.getHphm());
            arguments.putString("cpys", mExhaust.getCpys());
        }
        carDetailFragment.setArguments(arguments);
        this.fragmentsCarList.add(carDetailFragment);

        ArrayList<String> titleFragmentCarList = new ArrayList<>();
        titleFragmentCarList.add("监测结果");
        titleFragmentCarList.add("车辆信息");

        FragmentManager fragmentManager = getSupportFragmentManager();
        this.pagerAdapterJcCar = new ViewPagerFragmentAdapter(fragmentManager, this.fragmentsCarList, titleFragmentCarList);
        this.mViewPagerJcCar.setAdapter(this.pagerAdapterJcCar);
        this.mViewPagerJcCar.setCurrentItem(0);
        this.mTab.setupWithViewPager(this.mViewPagerJcCar);

        this.mConfirm.setOnClickListener((v) -> {
            DialogManager.showConfirmDialog((Context) BlackCarDetailActivity.this
                    , "您确定要将该记录审核为黑烟车吗?"
                    , (v1 -> {
                    })
                    , (v2 -> {
                        JSONObject tempJSONObject = new JSONObject();
                        try {
                            tempJSONObject.put("jlbh", BlackCarDetailActivity.this.mExhaust.getJlbh());
                            tempJSONObject.put("sfmhy", sfmhy);  //是否冒黑烟 0 否 1 是
                            tempJSONObject.put("shzt", shzt);   //审核状态 0 未审核 1 审核通过 2 未通过
                            tempJSONObject.put("shgcbz", remake);   //审核过程备注
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), tempJSONObject.toString());
                        BlackCarDetailActivity.this.mBlackDetailPresenter.addSh(requestBody);

                    })
            ).show();
        });
    }

    private void initData() {
        this.mBlackDetailPresenter = new BlackDetailPresenter(this);
//        this.mBlackDetailPresenter.getWarningData(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, this.strJlid);
        showLoading();

        if (OptionType_Review.equals(mOptionType)) {
            this.mLlConfirm.setVisibility(View.GONE);
        } else if (OptionType_Judge.equals(mOptionType)) {
            this.mLlConfirm.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blackcar_station_detail_activity);

        this.mTableType = getIntent().getStringExtra("tableType");
        this.mOptionType = getIntent().getStringExtra("optionType");
        this.mExhaust = (Exhaust) getIntent().getParcelableExtra("exhaust");
        initTitle();
        initView();
        initData();
    }

    // 实现StationDetailContract.View接口 5个方法
//    @Override
//    public void showWarmingSuccess(GetMonitoringDataDetailNewModel getMonitoringDataDetailNewModel) {
//        RxBus.get().post(new UpdateJcEvent(getMonitoringDataDetailNewModel));
//    }

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
