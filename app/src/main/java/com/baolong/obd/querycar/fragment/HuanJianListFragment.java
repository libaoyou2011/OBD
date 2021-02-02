package com.baolong.obd.querycar.fragment;

import java.util.Collection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

import com.baolong.obd.blackcar.data.entity.HuanjianModel;
import com.baolong.obd.blackcar.data.entity.ResponseExhaustListModel;
import com.baolong.obd.blackcar.data.entity.ResponseListModel;
import com.baolong.obd.blackcar.data.entity.VehicleInfo;
import com.baolong.obd.common.utils.ActivityUtils;
import com.baolong.obd.common.view.RecyclerViewForEmpty;
import com.baolong.obd.querycar.activity.HuanJianDetailActivity;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.hwangjr.rxbus.RxBus;

import com.baolong.obd.R;
import com.baolong.obd.querycar.presenter.QueryCarListPresenter;
import com.baolong.obd.querycar.adapter.HuanjianListAdapter;
import com.baolong.obd.querycar.contract.QueryCarListContract;
import com.baolong.obd.common.base.BaseFragment;

/**
 * 车辆 (环检/年检) 记录
 */
public class HuanJianListFragment extends BaseFragment
        implements ViewPager.OnPageChangeListener, QueryCarListContract.View {
    private String mHphm;
    private String mCpys;

    private int mPageNum = 1;
    final private int mPageRow = 15;
    private boolean mHasNoMore;

    private RecyclerViewForEmpty mRecycler;
    private RefreshLayout mRefreshLayout;
    private android.view.View mRootView;
    private View mEmptyView;

    private HuanjianListAdapter mAdapter;
    private QueryCarListPresenter queryCarListPresenter;

    private void initView() {
        this.mRefreshLayout = (RefreshLayout) this.mRootView.findViewById(R.id.refreshLayout);

        this.mRecycler = (RecyclerViewForEmpty) this.mRootView.findViewById(R.id.rv_recycler);
        this.mRecycler.setEmptyView(this.mEmptyView);
        this.mRecycler.setLayoutManager((RecyclerView.LayoutManager) new LinearLayoutManager((Context) this.getActivity()));

        this.mAdapter = new HuanjianListAdapter((Context) this.getActivity(), new HuanjianListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(HuanjianModel huanjianModel) {
                if (huanjianModel != null){
                    Intent intent = new Intent(getContext(), HuanJianDetailActivity.class);
                    //intent.putExtra("dwbh", mDwbh);
                    //intent.putExtra("jlbh", exhaust.getJlbh());
                    intent.putExtra("huanjian", huanjianModel);
                    ActivityUtils.activitySwitch((Activity) getContext(), intent, true);
                }
            }
            /*@Override
            public void onClick(View v) {
                // do nothing
                HuanjianModel exhaust = (HuanjianModel) v.getTag();
                if (exhaust != null){
                    Intent intent = new Intent(getContext(), HuanJianDetailActivity.class);
                    //intent.putExtra("dwbh", mDwbh);
                    //intent.putExtra("jlbh", exhaust.getJlbh());
                    intent.putExtra("huanjian", exhaust);
                    ActivityUtils.activitySwitch((Activity) getContext(), intent, true);
                }
            }*/
        });
        this.mRecycler.setAdapter(this.mAdapter);
    }

    private void addListener() {
        RxBus.get().register((Object) this);
        this.mRefreshLayout.setOnRefreshListener((OnRefreshListener) new OnRefreshListener() {
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                //refreshLayout.finishRefresh();
                refreshLayout.finishRefresh(2000, true);

                mHasNoMore = false;
                mPageNum = 1;

                queryCarListPresenter.getJyListData(mPageRow, mPageNum, "testDate", "desc", mHphm, mCpys);

            }
        });
        this.mRefreshLayout.setOnLoadMoreListener((OnLoadMoreListener) new OnLoadMoreListener() {
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                //refreshLayout.finishLoadMore();
                refreshLayout.finishLoadMore(2000, true, mHasNoMore);

                if (!HuanJianListFragment.this.mHasNoMore) {
                    mPageNum++;
                    queryCarListPresenter.getJyListData(mPageRow, mPageNum, "testDate", "desc", mHphm, mCpys);

                }
            }
        });
    }

    private void initPresenter() {
        this.queryCarListPresenter = new QueryCarListPresenter(this);
    }

    private void getData() {
        this.mRefreshLayout.autoRefresh();
    }

    public HuanJianListFragment() {
        this.mHphm = "";
        this.mCpys = "";
    }

    public static HuanJianListFragment newInstance() {
        return new HuanJianListFragment();
    }

    @Override
    public void setArguments(final Bundle arguments) {
        super.setArguments(arguments);
        this.mHphm = arguments.getString("hphm");
        this.mCpys = arguments.getString("cpys");
    }

    @Nullable
    public View onCreateView(@NonNull final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final Bundle bundle) {
        RxBus.get().register((Object) this);
        this.mRootView = layoutInflater.inflate(R.layout.fragment_check_record, (ViewGroup) null);
        this.mEmptyView = layoutInflater.inflate(R.layout.recycler_empty, viewGroup, false);

        this.initView();
        this.addListener();
        this.initPresenter();
        this.getData();
        return this.mRootView;
    }

    public void onPageScrollStateChanged(final int n) {
    }

    public void onPageScrolled(final int n, final float n2, final int n3) {
    }

    public void onPageSelected(final int n) {
    }

    @Override
    public void refresh() {
    }

    public void setPresenter(final QueryCarListPresenter queryCarListPresenter) {
    }

    public void showLoading() {
    }

    public void hideLoading() {
    }

    public void showFail(final String s) {
    }

    public void showJyListData(ResponseListModel<HuanjianModel> result) {
        if (result.getRows().size() < this.mPageRow) {
            // 此页不超过15条数据，则是最后一页
            this.mHasNoMore = true;
        }
        if (result.getPageNum() == 1) {
            this.mAdapter.setData(result.getRows());
            this.hideLoading();
            return;
        }
        this.mAdapter.getData().addAll((Collection<? extends HuanjianModel>) result.getRows());
        this.mAdapter.notifyDataSetChanged();
    }


    @Override
    public void showCarInfo(VehicleInfo p0) {

    }

    @Override
    public void showYcListData(ResponseExhaustListModel p0) {

    }

    @Override
    public void showCbListData(ResponseExhaustListModel p0) {

    }

}

