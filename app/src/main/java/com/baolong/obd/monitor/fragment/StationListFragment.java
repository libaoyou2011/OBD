package com.baolong.obd.monitor.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baolong.obd.R;
import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.common.utils.ActivityUtils;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.common.view.RecyclerViewForEmpty;
import com.baolong.obd.monitor.activity.StationDetailActivity;
import com.baolong.obd.monitor.contract.StationListContract;
import com.hwangjr.rxbus.RxBus;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.base.BaseFragment;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.monitor.adapter.StationListAdapter;
import com.baolong.obd.monitor.presenter.StationListPresenter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public class StationListFragment extends BaseFragment
        implements View.OnClickListener, StationListContract.View {
    public static final String TAG = StationListFragment.class.getSimpleName();

    private String mDwbh;  // 点位编号
    private String mType = "1"; //1:合格, 0:不合格

    private int mPageNum = 1;  //默认请求第 1 页
    private final int mPageRow = 10;  //默认一页 10 条
    private final String mOrderByColumn = "jcrq";  //排序列名：检测日期
    private final String mOrder = "desc";  //排序方式：降序
    private boolean mHasNoMore;

    private View mRootView;
    private RefreshLayout mRefreshLayout;
    private RecyclerViewForEmpty mRecyclerViewForEmpty;
    private View mEmptyView;

    private StationListAdapter mAdapter;
    private StationListPresenter mStationListPresenter;

    private void addListener() {
        RxBus.get().register(this);
        this.mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                //refreshLayout.finishRefresh();
                refreshLayout.finishRefresh(2000, true);

                mHasNoMore = false;
                mPageNum = 1;

                mStationListPresenter.getTodayData(mDwbh, mType, mPageRow, mPageNum, "jcrq", "desc");

            }
        });
        this.mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            public void onLoadMore(RefreshLayout refreshLayout) {
                //refreshLayout.finishLoadMore();
                refreshLayout.finishLoadMore(2000, true, mHasNoMore);

                if (!StationListFragment.this.mHasNoMore) {
                    StationListFragment.this.mPageNum++;
                    mStationListPresenter.getTodayData(mDwbh, mType, mPageRow, mPageNum, "jcrq", "desc");
                }
            }
        });
    }

    private void initTitle() {
    }

    private void initView() {
        this.mRefreshLayout = ((RefreshLayout) this.mRootView.findViewById(R.id.refresh_layout));
        this.mRecyclerViewForEmpty = ((RecyclerViewForEmpty) this.mRootView.findViewById(R.id.rv_recycler));
        this.mRecyclerViewForEmpty.setEmptyView(this.mEmptyView);
        this.mRecyclerViewForEmpty.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initData() {
        this.mAdapter = new StationListAdapter(getContext(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exhaust exhaust = (Exhaust) v.getTag();
                if (exhaust != null) {
                    Intent intent = new Intent(getContext(), StationDetailActivity.class);
                    //intent.putExtra("dwbh", mDwbh);
                    //intent.putExtra("jlbh", exhaust.getJlbh());
                    intent.putExtra("exhaust", exhaust);
                    intent.putExtra("optionType", "review");
                    ActivityUtils.activitySwitch((Activity) getContext(), intent, true);
                }
            }
        });
        this.mRecyclerViewForEmpty.setAdapter(this.mAdapter);
    }

    private void initPresenter() {
        this.mStationListPresenter = new StationListPresenter(this);
    }

    private void getData() {
        this.mRefreshLayout.autoRefresh();
    }

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
        this.mRootView = paramLayoutInflater.inflate(R.layout.fragment_station_list, paramViewGroup, false);
        this.mEmptyView = paramLayoutInflater.inflate(R.layout.recycler_empty, paramViewGroup, false);
        this.mDwbh = getActivity().getIntent().getStringExtra("dwbh");
        this.mType = getArguments().getString("type");
        initTitle();
        initView();
        initData();
        addListener();
        initPresenter();
        getData();
        return this.mRootView;
    }

    @Override
    public void onClick(View paramView) {
        // paramView.getId();
        // int i = R.id.tv_right_text;
    }

    @Override
    public void onDestroyView() {
        RxBus.get().unregister(this);
        this.mStationListPresenter.dropView();
        super.onDestroyView();
    }

    @Override
    public void setPresenter(StationListPresenter paramStationListPresenter) {

    }

    @Override
    public void hideLoading() {
        ((BaseActivity) getActivity()).hideLoading();
    }


    @Override
    public void showTodayData(List<Exhaust> exhaustList) {
        hideLoading();
        if (exhaustList == null) {
            return;
        }
        LogUtil.i(TAG, "items num:" + exhaustList.size());


        if (exhaustList.size() < this.mPageRow) {
            // 此页不超过15条数据，则是最后一页
            this.mHasNoMore = true;
        }

        if (mPageNum == 1) {
            this.mAdapter.setData(exhaustList);
        } else {
            this.mAdapter.getData().addAll(exhaustList);
            this.mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showFail(String paramString) {
        ToastUtils.shortToast(paramString);
    }

    @Override
    public void showLoading() {
        ((BaseActivity) getActivity()).showLoading(this.mRootView);
    }
}
