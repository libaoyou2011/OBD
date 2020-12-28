package com.baolong.obd.monitor.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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
import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.common.base.Constance;
import com.baolong.obd.common.utils.CommonUtils;
import com.baolong.obd.common.widget.DialogManager;
import com.baolong.obd.execution.event.UpdateExecListEvent;
import com.baolong.obd.monitor.fragment.ScaleCircleNavigator;
import com.baolong.obd.querycar.fragment.CarDetailFragment;
import com.hwangjr.rxbus.RxBus;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.monitor.adapter.ViewPagerFragmentAdapter;
import com.baolong.obd.monitor.contract.StationDetailContract;
import com.baolong.obd.monitor.fragment.ImageFragment;
import com.baolong.obd.monitor.fragment.StationDetailJcFragment;
import com.baolong.obd.monitor.fragment.VideoFragment;
import com.baolong.obd.monitor.presenter.StationDetailPresenter;

import java.util.ArrayList;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 遥测数据测详情，都是显示此页
 */
@Route(path = Constance.ACTIVITY_URL_StationDetailActivity)
public class StationDetailActivity extends BaseActivity implements StationDetailContract.View {
    private Exhaust mExhaust;
    private String mOptionType;  // review 查看， judge 审核/处罚
    private String mDwbh;
    private String mJlbh;

    //底部处罚栏
    private LinearLayout mLlConfirm;
    private Button mCancel;
    private Button mConfirm;

    private ViewPager mViewPagerPic;
    private FragmentPagerAdapter pagerAdapterPic;
    private ArrayList<Fragment> fragmentsPic;

    private TabLayout mTabLayout;

    private ViewPager mViewPagerJcCar;
    private FragmentPagerAdapter pagerAdapterJcCar;
    private ArrayList<Fragment> fragmentsCarList;

    private StationDetailPresenter mStationDetailPresenter;

    private void initTitle() {
        ((ImageView) findViewById(R.id.image_title_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StationDetailActivity.this.onBackPressed();
            }
        });

        TextView titleTextView = (TextView) findViewById(R.id.tv_title);
        String titleName = getResources().getString(R.string.monitor_station_detail_title);
        if (!TextUtils.isEmpty(this.mDwbh)) {
            titleName = titleName + "-" + this.mDwbh;
        }
        titleTextView.setText(titleName);
    }

    private void initView() {
        // 1.处罚栏
        this.mLlConfirm = (LinearLayout) findViewById(R.id.ll_confirm);
        this.mConfirm = (Button) findViewById(R.id.btn_commit);
        this.mCancel = (Button) findViewById(R.id.btn_cancel);
        this.mConfirm.setText("处罚");
        this.mCancel.setText("关闭");
        if ("judge".equals(mOptionType)) {
            this.mLlConfirm.setVisibility(View.VISIBLE);
        } else if ("review".equals(mOptionType)) {
            this.mLlConfirm.setVisibility(View.GONE);
        }
        this.mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StationDetailActivity.this.onBackPressed();
            }
        });
        this.mConfirm.setOnClickListener((v) -> {
            DialogManager.showConfirmDialog((Context) StationDetailActivity.this
                    , "您确定处罚该条记录吗?"
                    , (v1 -> {
                    })
                    , (v2 -> {
                        JSONObject tempJSONObject = new JSONObject();
                        try {
                            tempJSONObject.put("cpys",StationDetailActivity.this.mExhaust.getCpys());
                            tempJSONObject.put("hphm",StationDetailActivity.this.mExhaust.getHphm());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), tempJSONObject.toString());
                        StationDetailActivity.this.mStationDetailPresenter.addPunish(requestBody);
                    })
            ).show();
        });

        //2.图片栏
        this.mViewPagerPic = (ViewPager) findViewById(R.id.vp_pic);
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
        int showPictureMinNum = Math.max(urlArray.length, 2);

        this.fragmentsPic = new ArrayList<>();
        Fragment fragment;
        //展示图片的ImageFragment
        for (int i = 0; i < showPictureMinNum; i++) {
            fragment = new ImageFragment();
            Bundle arguments = new Bundle();
            if (i < urlArray.length) {
                arguments.putString("image", urlArray[i]);
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

        this.pagerAdapterPic = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return StationDetailActivity.this.fragmentsPic.size();
            }

            @Override
            public Fragment getItem(int position) {
                return (Fragment) StationDetailActivity.this.fragmentsPic.get(position);
            }
        };
        this.mViewPagerPic.setAdapter(this.pagerAdapterPic);
        this.mViewPagerPic.setOffscreenPageLimit(2);//ViewPager缓存2*n+1页 (n为设置的值)
        //初始化 图片索引指示器
        initMagicIndicator();

        //3.数据栏
        this.mTabLayout = ((TabLayout) findViewById(R.id.tab_jc_car));
        this.mViewPagerJcCar = ((ViewPager) findViewById(R.id.vp_jc_car));
        final LinearLayout linearLayout = (LinearLayout) this.mTabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.layout_divider_vertical));
        linearLayout.setDividerPadding((int) CommonUtils.DpToPx((Context) this, 10.0f));

        // 原本：监测结果、车辆信息 原本在同一个fragment中通过判断显示
        // 改成：车辆信息用车辆查询中的 CarDetailFragment 显示
        this.fragmentsCarList = new ArrayList<>();

        StationDetailJcFragment detailJcFragment1 = new StationDetailJcFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("from", "0");
        bundle1.putString("type", "0"); //遥测数据
        bundle1.putParcelable("exhaust", mExhaust);
        detailJcFragment1.setArguments(bundle1);
        this.fragmentsCarList.add(detailJcFragment1);

        /*StationDetailJcFragment detailJcFragment2 = new StationDetailJcFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("from", "1");
        bundle2.putString("type", "1");  //车辆信息数据
        bundle1.putSerializable("exhaust", mExhaust);
        detailJcFragment2.setArguments(bundle2);
        this.fragmentsCarList.add(detailJcFragment2);*/

        CarDetailFragment carDetailFragment = new CarDetailFragment();
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

        this.pagerAdapterJcCar = new ViewPagerFragmentAdapter(getSupportFragmentManager(), this.fragmentsCarList, titleFragmentCarList);
        this.mViewPagerJcCar.setAdapter(this.pagerAdapterJcCar);
        this.mViewPagerJcCar.setCurrentItem(0);

        this.mTabLayout.setupWithViewPager(this.mViewPagerJcCar);

    }

    /**
     * 初始化MagicIndicator
     */
    private void initMagicIndicator() {
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

    private void initMagicIndicator2() {
        MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator4);
        ScaleCircleNavigator localScaleCircleNavigator = new ScaleCircleNavigator(this);
        localScaleCircleNavigator.setCircleCount(this.fragmentsPic.size());
        localScaleCircleNavigator.setNormalCircleColor(-1996488705);
        localScaleCircleNavigator.setSelectedCircleColor(-1);
        localScaleCircleNavigator.setCircleClickListener(new ScaleCircleNavigator.OnCircleClickListener() {
            public void onClick(int paramAnonymousInt) {
                StationDetailActivity.this.mViewPagerPic.setCurrentItem(paramAnonymousInt);
            }
        });
        magicIndicator.setNavigator(localScaleCircleNavigator);
        ViewPagerHelper.bind(magicIndicator, this.mViewPagerPic);
    }


    private void initData() {
        this.mStationDetailPresenter = new StationDetailPresenter(this);
//        this.mStationDetailPresenter.getWarningData(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, this.mJlbh);
//        showLoading();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitor_station_detail_activity);

        this.mDwbh = getIntent().getStringExtra("dwbh");
        this.mJlbh = getIntent().getStringExtra("jlbh");
        this.mExhaust = (Exhaust) getIntent().getParcelableExtra("exhaust");
        this.mOptionType = getIntent().getStringExtra("optionType");

        initTitle();
        initView();
        initData();
    }

    // 实现StationDetailContract.View接口 5个方法
    @Override
    public void setPresenter(StationDetailPresenter paramStationDetailPresenter) {
    }

    @Override
    public void showAddCfSuccess(String paramString) {
        ToastUtils.longToast(paramString);
        RxBus.get().post(new UpdateExecListEvent("4"));
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
