package com.baolong.obd.blackcar.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.blackcar.contract.BlackCarMainContract;
import com.baolong.obd.blackcar.data.entity.BlackCountModel;
import com.baolong.obd.blackcar.event.UpdateBlackSumEvent;
import com.baolong.obd.common.utils.LogUtil;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.baolong.obd.blackcar.activity.BlackCarFilterActivity;
import com.baolong.obd.blackcar.adapter.ViewPagerFragmentAdapter;
import com.baolong.obd.blackcar.event.RefreshBlackCarListByFilter;
import com.baolong.obd.blackcar.presenter.BlackCarMainPresenter;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.base.BaseFragment;
import com.baolong.obd.common.utils.CommonUtils;
import com.baolong.obd.common.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlackCarMainFragment extends BaseFragment
        implements View.OnClickListener, BlackCarMainContract.View {
    private static final String TAG = "BlackCarMainFragment";

    public static final String Table_hg = "1";
    public static final String Table_cb = "0";
    public static final String Table_wpd = "2";

    private View mRootView;
    View mLineView;
    private BlackCarFilterActivity mBlackCarFilterActivity;

    private TextView mNoDoTv;
    private TextView mAlreadyDoTv;
    private TextView mSumTv;

    private TabLayout mTabLayout;
    private ViewPager vpMain;
    private ViewPagerFragmentAdapter mViewPagerFragmentAdapter;
    private BlackCarMainPresenter mBlackCarMainPresenter;

    public static BlackCarMainFragment newInstance() {
        return new BlackCarMainFragment();
    }

    private void initTitle() {
        ((ImageView) this.mRootView.findViewById(R.id.image_title_back)).setVisibility(View.GONE);
        // title
        TextView titleTextView = (TextView) this.mRootView.findViewById(R.id.tv_title);
        titleTextView.setText(getResources().getString(R.string.black_car_title));
        //分割线
        mLineView = this.mRootView.findViewById(R.id.v_top);

        TextView mTopRightTv = ((TextView) this.mRootView.findViewById(R.id.tv_right_text));
        mTopRightTv.setVisibility(View.INVISIBLE);
        mTopRightTv.setText("筛选");
        mTopRightTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mBlackCarFilterActivity == null) {
                    LogUtil.i(TAG, "创建 PopupWindow");
                    View convertView = LayoutInflater.from(BlackCarMainFragment.this.getContext()).inflate(R.layout.activity_select_filter, null);
                    mBlackCarFilterActivity = new BlackCarFilterActivity(BlackCarMainFragment.this.getContext(), convertView, mLineView.getBottom(), "black");
                }
                mBlackCarFilterActivity.showAsDropDown(mLineView);
            }
        });
    }

    private void initView() {
        this.mSumTv = ((TextView) this.mRootView.findViewById(R.id.tv_sum));
        this.mAlreadyDoTv = ((TextView) this.mRootView.findViewById(R.id.tv_already_do));
        this.mNoDoTv = ((TextView) this.mRootView.findViewById(R.id.tv_no_do));
        this.mTabLayout = ((TabLayout) this.mRootView.findViewById(R.id.tab_type));
        this.vpMain = ((ViewPager) this.mRootView.findViewById(R.id.vp_main));

        final LinearLayout linearLayout = (LinearLayout) this.mTabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.layout_divider_vertical));
        linearLayout.setDividerPadding((int) CommonUtils.DpToPx(getActivity(), 10.0F));

        List<Fragment> fragmentList = new ArrayList<Fragment>();

        //未审核
        final BlackCarListFragment execListFragment = new BlackCarListFragment();
        final Bundle arguments = new Bundle();
        arguments.putString("type", Table_hg);
        execListFragment.setArguments(arguments);
        fragmentList.add(execListFragment);
        //已审核
        final BlackCarListFragment execListFragment2 = new BlackCarListFragment();
        final Bundle arguments2 = new Bundle();
        arguments2.putString("type", Table_cb);
        execListFragment2.setArguments(arguments2);
        fragmentList.add(execListFragment2);
        //所有数据
        final BlackCarListFragment execListFragment3 = new BlackCarListFragment();
        final Bundle arguments3 = new Bundle();
        arguments3.putString("type", Table_wpd);
        execListFragment3.setArguments(arguments3);
        fragmentList.add(execListFragment3);

        final ArrayList<String> list = new ArrayList<String>();
        list.add("合格");
        list.add("超标");
        list.add("未判定");

        FragmentManager fragmentManager = this.getChildFragmentManager();
        this.mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(fragmentManager, fragmentList, list);
        initViewPager();
    }

    public void initViewPager() {
        this.vpMain.setAdapter(this.mViewPagerFragmentAdapter);
        this.vpMain.setCurrentItem(0);
        this.vpMain.setOffscreenPageLimit(3);
        //TabLayout 绑定 ViewPager
        this.mTabLayout.setupWithViewPager(this.vpMain);
    }

    public void initPresenter() {
        this.mBlackCarMainPresenter = new BlackCarMainPresenter(this);
    }

    private void getDataByPresenter() {
        this.mBlackCarMainPresenter.getData();
    }


    @Override
    public void onClick(View paramView) {
    }

    @Override
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        RxBus.get().register(this);
        this.mRootView = paramLayoutInflater.inflate(R.layout.fragment_black_car, null);
        initTitle();
        initView();
        initPresenter();
        // 获取统计总数，改成由列表数据返回时带回总数
        // getDataByPresenter();
        return this.mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
    }

    @Override
    public void onViewCreated(View paramView, @Nullable Bundle paramBundle) {
        super.onViewCreated(paramView, paramBundle);
    }

    @Subscribe
    public void refreshList(RefreshBlackCarListByFilter paramRefreshBlackCarListByFilter) {
       LogUtil.i(TAG, "条件筛选后 刷新黑烟车总数：do nothing");
       /* File localFile = new File(FileUtils.getCacheDir(getContext()), "objectByArray.txt");
        if (localFile == null) {
            return;
        }
        //StreamUtil.writeObject(paramRefreshBlackCarListByFilter.getFilterCategoryModelList(), localFile);
        ListSaveUtils.writeList2Storage(paramRefreshBlackCarListByFilter.getFilterCategoryModelList(), localFile);
        */
    }

    //列表数据请求成功后，将数量更新到统计中
    @Subscribe
    public void refreshSum(UpdateBlackSumEvent updateBlackSumEvent) {
        LogUtil.i(TAG, "refreshSum 刷新黑烟车统计数量");
        if (updateBlackSumEvent != null) {
            if (updateBlackSumEvent.getWshSum() >= 0) {
                this.mNoDoTv.setText(new StringBuilder().append(updateBlackSumEvent.getWshSum()));
            }
            if (updateBlackSumEvent.getYshSum() >= 0) {
                this.mAlreadyDoTv.setText(new StringBuilder().append(updateBlackSumEvent.getYshSum()));
            }
            if (updateBlackSumEvent.getAllSum() >= 0) {
                this.mSumTv.setText(new StringBuilder().append(updateBlackSumEvent.getAllSum()));
            }
        }
    }

//    @Subscribe
//    public void refreshListAfterSh(RefreshBlackCar paramRefreshBlackCar) {
//        LogUtil.i(TAG, "refreshListAfterSh 刷新黑烟车总数" );
//        updateSum();
//    }

//    public void updateSum() {
//        LogUtil.i(TAG, "获取黑烟车总数" );
//        this.mBlackCarMainPresenter.getData();
//    }

    @Override
    public void setPresenter(BlackCarMainPresenter paramBlackCarMainPresenter) {
    }

    @Override
    public void showFail(String paramString) {
        ToastUtils.shortToast(paramString);
    }

    @Override
    public void showLoading() {
        ((BaseActivity) Objects.requireNonNull(getActivity())).showLoading(this.mRootView);
    }

    public void hideLoading() {
        ((BaseActivity) Objects.requireNonNull(getActivity())).hideLoading();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setData(BlackCountModel blackCountModel) {
        if (blackCountModel != null) {
            this.mAlreadyDoTv.setText(blackCountModel.getYshnumber() + "");
            this.mNoDoTv.setText(blackCountModel.getWshnumber() + "");
            this.mSumTv.setText((blackCountModel.getYshnumber() + blackCountModel.getWshnumber()) + "");
        }
    }

}
