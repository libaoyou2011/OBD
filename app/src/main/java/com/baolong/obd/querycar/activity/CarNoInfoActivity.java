package com.baolong.obd.querycar.activity;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import java.util.ArrayList;

import com.baolong.obd.R;

import android.os.Bundle;

import com.baolong.obd.querycar.fragment.HuanJianListFragment;
import com.baolong.obd.querycar.fragment.CarDetailFragment;
import com.baolong.obd.querycar.fragment.RemoteCheckRecordFragment;
import com.baolong.obd.common.utils.CommonUtils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;

import com.baolong.obd.querycar.adapter.ViewPagerFragmentAdapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;

import java.util.List;

import com.alibaba.android.arouter.facade.annotation.Route;

import com.baolong.obd.common.base.BaseActivity;

import static android.widget.LinearLayout.SHOW_DIVIDER_MIDDLE;

@Route(path = "/querycar/CarNoInfoActivity")
public class CarNoInfoActivity extends BaseActivity {
    private String mHphm;
    private String mCpys;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerFragmentAdapter mViewPagerFragmentAdapter;

    private void initTitle() {
        ((ImageView) this.findViewById(R.id.image_title_back)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CarNoInfoActivity.this.onBackPressed();

            }
        });
        final TextView textView = (TextView) this.findViewById(R.id.tv_title);
        StringBuilder sb = new StringBuilder();
        sb.append(this.getResources().getString(R.string.car_no_info_title));
        sb.append(":");
        sb.append(mHphm);
        textView.setText(sb.toString());
    }

    private void initView() {
        this.mTabLayout = (TabLayout) this.findViewById(R.id.tab_type);
        this.mViewPager = (ViewPager) this.findViewById(R.id.vp_main);

        final LinearLayout linearLayout = (LinearLayout) this.mTabLayout.getChildAt(0);
        linearLayout.setShowDividers(SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable((Context) this, R.drawable.layout_divider_vertical));
        linearLayout.setDividerPadding((int) CommonUtils.DpToPx((Context) this, 10.0f));
    }

    private void initData() {
        Bundle arguments = new Bundle();
        arguments.putString("hphm", this.mHphm);
        arguments.putString("cpys", this.mCpys);

        CarDetailFragment carDetailFragment = new CarDetailFragment();
        carDetailFragment.setArguments(arguments);

        HuanJianListFragment huanJianListFragment = new HuanJianListFragment();
        huanJianListFragment.setArguments(arguments);

        RemoteCheckRecordFragment remoteCheckRecordFragment = new RemoteCheckRecordFragment();
        Bundle bundle3 = new Bundle();
        bundle3.putString("hphm", this.mHphm);
        bundle3.putString("cpys", this.mCpys);
        bundle3.putString("type", "yc");
        remoteCheckRecordFragment.setArguments(bundle3);

        RemoteCheckRecordFragment cbRecordFragment = new RemoteCheckRecordFragment();
        Bundle bundle4 = new Bundle();
        bundle4.putString("hphm", this.mHphm);
        bundle4.putString("cpys", this.mCpys);
        bundle4.putString("type", "cb");
        cbRecordFragment.setArguments(bundle4);

        List<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(carDetailFragment);
        mFragmentList.add(huanJianListFragment);
        mFragmentList.add(remoteCheckRecordFragment);
        mFragmentList.add(cbRecordFragment);

        ArrayList<String> list = new ArrayList<>();
        list.add("车辆信息");
        list.add("环检信息");
        list.add("遥测记录");
        list.add("超标记录");

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        this.mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(fragmentManager, mFragmentList, list);
        this.initViewPager();
    }

    public void initViewPager() {
        this.mViewPager.setAdapter((PagerAdapter) this.mViewPagerFragmentAdapter);
        this.mViewPager.setCurrentItem(0);
        this.mViewPager.setOffscreenPageLimit(3);
        this.mTabLayout.setupWithViewPager(this.mViewPager);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_car_no_info);

        this.mHphm = getIntent().getStringExtra("hphm");
        this.mCpys = getIntent().getStringExtra("cpys");

        this.initTitle();
        this.initView();
        this.initData();
    }

}

