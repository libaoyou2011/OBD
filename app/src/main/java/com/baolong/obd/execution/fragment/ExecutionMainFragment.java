package com.baolong.obd.execution.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.blackcar.activity.BlackCarFilterActivity;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.execution.data.entity.GetStatisticCountModel;
import com.baolong.obd.execution.event.UpdateExeSumEvent;
import com.baolong.obd.execution.event.UpdateExecListEvent;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.base.BaseFragment;
import com.baolong.obd.common.utils.CommonUtils;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.execution.adapter.ViewPagerFragmentAdapter;
import com.baolong.obd.execution.presenter.ExecutionMainPresenter;
import com.baolong.obd.execution.contract.ExecutionMainContract;
//import com.sunfusheng.glideimageview.progress.CircleProgressView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExecutionMainFragment extends BaseFragment
        implements View.OnClickListener, ExecutionMainContract.View {
    private static final String TAG = "ExecutionMainFragment";

    public static final String Table_online = "1"; //车辆在线
    public static final String Table_outline = "0"; //车辆离线

    private View mView;

    private TextView mWaittingProcess;
    private TextView mAlreadyProcessedTxt;
    //private CircleProgressView mCircleProgressView;
    private ProgressBar mCircleProgressView;
    //private ExecutionMainPresenter mExecutionMainPresenter;
    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private ViewPagerFragmentAdapter mViewPagerFragmentAdapter;
    private List<Fragment> mFragmentList;

    private ExecutionMainPresenter mExecutionMainPresenter;


    public static ExecutionMainFragment newInstance() {
        return new ExecutionMainFragment();
    }

    private void initTitle() {
        ((ImageView) this.mView.findViewById(R.id.image_title_back)).setVisibility(View.GONE);

        final TextView textView = (TextView) this.mView.findViewById(R.id.tv_title);
        textView.setText((CharSequence) this.getResources().getString(R.string.execution_main_title));

        final android.view.View lineView = this.mView.findViewById(R.id.view_ex_top);
        TextView tempTopBarRightTv = ((TextView) this.mView.findViewById(R.id.tv_right_text));
        tempTopBarRightTv.setVisibility(View.INVISIBLE);
        tempTopBarRightTv.setText("筛选");
        tempTopBarRightTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View convertView = LayoutInflater.from(ExecutionMainFragment.this.getContext()).inflate(R.layout.activity_select_filter, null);
                new BlackCarFilterActivity(ExecutionMainFragment.this.getContext(), convertView, lineView.getBottom(), "execution").showAsDropDown(lineView);
            }
        });

    }

    private void initView() {
        this.mWaittingProcess = (TextView) this.mView.findViewById(R.id.txt_waiting_process);
        this.mAlreadyProcessedTxt = (TextView) this.mView.findViewById(R.id.txt_already_processed);
//        this.mCircleProgressView = (ProgressBar)this.mView.findViewById(R.id.pb_exec);
//        this.mCircleProgressView.setVisibility(View.GONE);

        this.mTablayout = (TabLayout) this.mView.findViewById(R.id.tab_type);
        this.mViewPager = (ViewPager) this.mView.findViewById(R.id.vp_main);
        final LinearLayout linearLayout = (LinearLayout) this.mTablayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable((Context) Objects.requireNonNull(this.getActivity()), R.drawable.layout_divider_vertical));
        linearLayout.setDividerPadding((int) CommonUtils.DpToPx((Context) this.getActivity(), 10.0f));

        this.mFragmentList = new ArrayList<Fragment>();
        final ExecListFragment TodoExecListFragment = new ExecListFragment();
        final Bundle arguments = new Bundle();
        arguments.putString("type", Table_online);
        TodoExecListFragment.setArguments(arguments);
        this.mFragmentList.add(TodoExecListFragment);

        final ExecListFragment hadExecListFragment = new ExecListFragment();
        final Bundle arguments2 = new Bundle();
        arguments2.putString("type", Table_outline);
        hadExecListFragment.setArguments(arguments2);
        this.mFragmentList.add(hadExecListFragment);

        final ArrayList<String> list = new ArrayList<String>();
        list.add("在线车辆");
        list.add("离线车辆");

        FragmentManager fragmentManager = this.getChildFragmentManager();
        this.mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(fragmentManager, this.mFragmentList, list);
        this.initViewPager();
    }

    public void initViewPager() {
        this.mViewPager.setAdapter(this.mViewPagerFragmentAdapter);
        this.mViewPager.setCurrentItem(0);
        this.mViewPager.setOffscreenPageLimit(3);
        //TabLayout 绑定 ViewPager
        this.mTablayout.setupWithViewPager(this.mViewPager);
    }


    private void initPresenter() {
        this.mExecutionMainPresenter = new ExecutionMainPresenter(this);
    }

    private void getDataByPresenter() {
        if (mExecutionMainPresenter != null) {
            this.mExecutionMainPresenter.getData();
        }
    }

    @Override
    public void onClick(View paramView) {
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, Bundle paramBundle) {
        RxBus.get().register(this);
        this.mView = paramLayoutInflater.inflate(R.layout.fragment_execution, null);
        initTitle();
        initView();
        initPresenter();
        // 获取统计总数，改成由列表数据返回时带回总数
        // getDataByPresenter();
        return this.mView;
    }

    @Override
    public void onViewCreated(View paramView, @Nullable Bundle paramBundle) {
        super.onViewCreated(paramView, paramBundle);
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

    //超标车-处罚后更新总数
    @Subscribe
    public void refreshList(UpdateExecListEvent paramUpdateExecListEvent) {
        LogUtil.i(TAG, "条件筛选后 刷新黑烟车总数：do nothing");
        //this.mExecutionMainPresenter.getData();
    }

    //列表数据请求成功后，将数量更新到统计中
    @Subscribe
    public void refreshSum(UpdateExeSumEvent updateExeSumEvent) {
        LogUtil.i(TAG, "refreshSum 刷新超标车统计数量");

        if (updateExeSumEvent != null) {
            if (updateExeSumEvent.getWcfSum() >= 0) {
                this.mWaittingProcess.setText(new StringBuilder().append(updateExeSumEvent.getWcfSum()));
            }
            if (updateExeSumEvent.getYcfSum() >= 0) {
                this.mAlreadyProcessedTxt.setText(new StringBuilder().append(updateExeSumEvent.getYcfSum()));
            }
        }
    }

    /**
     * 实现 ExecutionMainContract.View接口 5个方法
     */
    public void setPresenter(ExecutionMainPresenter paramExecutionMainPresenter) {
    }

    public void hideLoading() {
        ((BaseActivity) Objects.requireNonNull(getActivity())).hideLoading();
    }

    public void setData(GetStatisticCountModel countModel) {
        if (countModel != null) {
            this.mWaittingProcess.setText(new StringBuilder().append(countModel.getWcfnumber()));
            this.mAlreadyProcessedTxt.setText(new StringBuilder().append(countModel.getYcfnumber()));
            //占比
            //this.mCircleProgressView.setProgress(Integer.parseInt(paramGetStatisticalExecutionResponseModel.getImplementationrate(), 0));
        }
    }

    public void showFail(String paramString) {
        ToastUtils.shortToast(paramString);
    }

    public void showLoading() {
        ((BaseActivity) Objects.requireNonNull(getActivity())).showLoading(this.mView);
    }
}

