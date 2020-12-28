package com.baolong.obd.monitor.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.utils.CommonUtils;
import com.baolong.obd.monitor.adapter.ViewPagerFragmentAdapter;
import com.baolong.obd.monitor.fragment.StationListFragment;
//import com.baolong.edsp.monitor.utils.TabLayoutUtils;

import java.util.ArrayList;
import java.util.List;

public class StationListActivity extends BaseActivity {
    private String mDwbh = "";
    private TabLayout mTab;
    private ViewPager vpMain;
    List<String> mTitleList;
    private List<Fragment> mFragmentList;

    private ViewPagerFragmentAdapter mViewPagerFragmentAdapter;

    private void initTitle() {
        ((ImageView) findViewById(R.id.image_title_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ((TextView) findViewById(R.id.tv_title)).setText(getResources().getString(R.string.monitor_station_list_title));
    }

    private void initView() {
        this.mTab = ((TabLayout) findViewById(R.id.tb_title_monitor_list));
//        this.mTab.post(new -..Lambda.StationListActivity.xaMyItYfKgHrlU8VFoO8N - WKxZg(this));
//        this.mTab.post(() -> {
//            TabLayoutUtils.setIndicator(this.mTab
//                    , (int) this.getResources().getDimension(R.dimen.x20)
//                    , (int) this.getResources().getDimension(R.dimen.x20)
//                    , 0);
//        });
        final LinearLayout linearLayout = (LinearLayout) this.mTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.layout_divider_vertical));
        linearLayout.setDividerPadding((int) CommonUtils.DpToPx((Context) this, 10.0f));

        this.vpMain = ((ViewPager) findViewById(R.id.vp_monitor_list));

        this.mTitleList = new ArrayList<>();
        this.mTitleList.add("合格");
        this.mTitleList.add("不合格");

        this.mFragmentList = new ArrayList<>();
        StationListFragment stationListFragmentOne = new StationListFragment();
        Bundle bundleOne = new Bundle();
        bundleOne.putString("dwbh", this.mDwbh);
        bundleOne.putString("type", "1");  // 1:表示合格
        stationListFragmentOne.setArguments(bundleOne);

        StationListFragment stationListFragmentTwo = new StationListFragment();
        Bundle bundleTwo = new Bundle();
        bundleTwo.putString("dwbh", this.mDwbh);
        bundleTwo.putString("type", "0"); // 0:表示不合格
        stationListFragmentTwo.setArguments(bundleTwo);

        this.mFragmentList.add(stationListFragmentOne);
        this.mFragmentList.add(stationListFragmentTwo);

        FragmentManager fragmentManager = getSupportFragmentManager();
        this.mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(fragmentManager, this.mFragmentList, mTitleList);
        initViewPager();
    }

    public void initViewPager() {
        // 给ViewPager设置适配器
        this.vpMain.setAdapter(this.mViewPagerFragmentAdapter);
        this.vpMain.setCurrentItem(0);

        // 将TabLayout和ViewPager关联起来
        // TabLayout不需要Adper，ViewPager的Adaper中重写getPageTitle()
        this.mTab.setupWithViewPager(this.vpMain);

        // 给Tabs设置适配器
        //this.mTab.setTabsFromPagerAdapter(this.mViewPagerFragmentAdapter);

    }

    private void addListener() {

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitor_station_list_activity);
        this.mDwbh = getIntent().getStringExtra("dwbh");

        initTitle();
        initView();
        addListener();
    }

    public void finish() {
        super.finish();
    }
}
