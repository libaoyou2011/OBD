package com.baolong.obd.execution.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baolong.obd.R;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.utils.CommonUtils;
import com.baolong.obd.execution.adapter.ViewPagerFragmentAdapter;
import com.baolong.obd.execution.fragment.AddExecDataFragment;
import com.baolong.obd.execution.fragment.CancelExecDataFragment;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/execution/AddExecDataActivity")
public class AddExecDataActivity extends BaseActivity
        implements ViewPager.OnPageChangeListener {
    private boolean b1Edit;


    private static final String TAG = AddExecDataActivity.class.getSimpleName();
    private boolean bEdit;
    private List<Fragment> mFragmentList;
    private FragmentManager mFragmentManager;
    private String mTestNo;
    private TabLayout typeTablayout;
    private ViewPager vpMain;
    private ViewPagerFragmentAdapter mViewPagerFragmentAdapter;

    private void initTitle() {
        ((ImageView) findViewById(R.id.image_title_back)).setOnClickListener(v -> {
            this.onBackPressed();
        });
        TextView textView = (TextView) this.findViewById(R.id.tv_title);
        textView.setText((CharSequence) this.getResources().getString(R.string.execution_add_title));
    }

    private void initView() {
        this.mTestNo = this.getIntent().getStringExtra("testNo");
        this.bEdit = this.getIntent().getBooleanExtra("edit", false);

        this.typeTablayout = (TabLayout) this.findViewById(R.id.tab_type);
        final LinearLayout linearLayout = (LinearLayout) this.typeTablayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this, R.drawable.layout_divider_vertical));
        linearLayout.setDividerPadding((int) CommonUtils.DpToPx(this, 10.0f));
        this.vpMain = (ViewPager) this.findViewById(R.id.vp_main);
    }

    private void initData() {
        this.mFragmentList = new ArrayList<Fragment>();

        final AddExecDataFragment addExecDataFragment = new AddExecDataFragment();
        final Bundle arguments = new Bundle();
        arguments.putString("testNo", this.mTestNo);
        arguments.putBoolean("edit", this.bEdit);
        addExecDataFragment.setArguments(arguments);
        this.mFragmentList.add(addExecDataFragment);

        final CancelExecDataFragment cancelExecDataFragment = new CancelExecDataFragment();
        final Bundle arguments2 = new Bundle();
        arguments2.putString("testNo", this.mTestNo);
        arguments2.putBoolean("edit", this.bEdit);
        cancelExecDataFragment.setArguments(arguments2);

        final ArrayList<String> list = new ArrayList<String>();
        list.add("处理");
        if (this.bEdit) {
            this.mFragmentList.add(cancelExecDataFragment);
            list.add("取消处理");
        } else {
            this.typeTablayout.setVisibility(View.GONE);
        }

        this.mFragmentManager = this.getSupportFragmentManager();
        this.mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(this.mFragmentManager, this.mFragmentList, list);
        this.initViewPager();
    }

    public void initViewPager() {
        this.vpMain.addOnPageChangeListener(this);
        this.vpMain.setAdapter(this.mViewPagerFragmentAdapter);
        this.vpMain.setCurrentItem(0);
        this.typeTablayout.setupWithViewPager(this.vpMain);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exec_data);
        initTitle();
        initView();
        initData();
    }

    // 适用含有一层Fragment，返回到对应的Fragment.onActivityResult()
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        LogUtil.i(TAG, "onActivityResult: " + String.valueOf(Matisse.obtainOriginalState(data)));
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    // 适用含有多层Fragment，返回到对应的Fragment.onActivityResult()
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FragmentManager fragmentManager = getSupportFragmentManager();
        for (int indext = 0; indext < fragmentManager.getFragments().size(); indext++) {
            Fragment fragment = fragmentManager.getFragments().get(indext); //找到第一层Fragment
            if (fragment == null) {
                Log.w(TAG, "Activity result no fragment exists for index: 0x"
                        + Integer.toHexString(requestCode));
            } else {
                handleResult(fragment, requestCode, resultCode, data);
            }
        }
    }

    /**
     * 递归调用，对所有的子Fragment生效
     *
     * @param fragment
     * @param requestCode
     * @param resultCode
     * @param data
     */
    private void handleResult(Fragment fragment, int requestCode, int resultCode, Intent data) {
        //调用每个Fragment的onActivityResult
        fragment.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult.handleResult()");
        //找到第二层Fragment
        List<Fragment> childFragment = fragment.getChildFragmentManager().getFragments();
        if (childFragment != null)
            for (Fragment f : childFragment)
                if (f != null) {
                    handleResult(f, requestCode, resultCode, data);
                }
        if (childFragment == null)
            Log.i(TAG, "onActivityResult.handleResult()");
    }


    public void onPageScrollStateChanged(int paramInt) {
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
    }

    public void onPageSelected(int paramInt) {
    }
}
