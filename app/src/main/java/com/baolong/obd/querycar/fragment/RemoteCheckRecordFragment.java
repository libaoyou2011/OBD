package com.baolong.obd.querycar.fragment;

import java.util.Collection;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import com.alibaba.android.arouter.launcher.ARouter;

import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.blackcar.data.entity.HuanjianModel;
import com.baolong.obd.blackcar.data.entity.ResponseExhaustListModel;
import com.baolong.obd.blackcar.data.entity.ResponseListModel;
import com.baolong.obd.blackcar.data.entity.VehicleInfo;
import com.baolong.obd.common.base.Constance;
import com.baolong.obd.common.view.RecyclerViewForEmpty;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.hwangjr.rxbus.RxBus;

import com.baolong.obd.R;
import com.baolong.obd.querycar.presenter.QueryCarListPresenter;
import com.baolong.obd.querycar.adapter.RemoteCheckListAdapter;
import com.baolong.obd.querycar.contract.QueryCarListContract;
import com.baolong.obd.common.base.BaseFragment;

import static com.baolong.obd.blackcar.fragment.BlackCarListFragment.OptionType_Review;
import static com.baolong.obd.blackcar.fragment.BlackCarMainFragment.Table_wpd;

/**
 * 车辆 (遥测、超标) 记录
 */
public class RemoteCheckRecordFragment extends BaseFragment
        implements ViewPager.OnPageChangeListener, QueryCarListContract.View {
    private String mHphm = null;
    private String mCpys = null;
    private String mType = null; //("yc"：遥测, "cb"：超标)

    private int mPageNum = 1;  //默认请求第 1 页;
    private int mPageRow = 15;  //默认一页 15 条
    private boolean mHasNoMore;

    private android.view.View mRootView;
    private RefreshLayout mRefreshLayout;
    private RecyclerViewForEmpty mRecycler;
    private View mEmptyView;

    private RemoteCheckListAdapter mAdapter;
    private QueryCarListPresenter mQueryCarListPresenter;

    private void initView() {
        this.mRefreshLayout = (RefreshLayout) this.mRootView.findViewById(R.id.refreshLayout);

        this.mRecycler = (RecyclerViewForEmpty) this.mRootView.findViewById(R.id.rv_recycler);
        this.mRecycler.setEmptyView(this.mEmptyView);
        this.mRecycler.setLayoutManager((RecyclerView.LayoutManager) new LinearLayoutManager((Context) this.getActivity()));

        this.mAdapter = new RemoteCheckListAdapter((Context) this.getActivity(), new RemoteCheckListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Exhaust exhaust) {
//                ARouter.getInstance()
//                        .build("/monitor/activity/BlackCarDetailActivity")
//                        .withString("jzbh", exhaust.getStationNo())
//                        .withString("jlid", exhaust.getId())
//                        .navigation();

                ARouter.getInstance()
                        .build(Constance.ACTIVITY_URL_StationDetailActivity)
                        .withString("tableType", Table_wpd)
                        .withString("optionType", OptionType_Review)
                        .withParcelable("exhaust", exhaust)
                        .navigation();
            }
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
                final QueryCarListPresenter tempQueryCarListPresenter = mQueryCarListPresenter;

                // 此车辆遥测记录 || 超标记录
                if ("yc".equals(mType)) {
                    int isBlackCar = 0; //是否是黑烟车: (0-不是、 1-是)
                    tempQueryCarListPresenter.getYcListData(isBlackCar, mPageRow, mPageNum, "jcrq", "desc", mHphm, mCpys);
                } else if ("cb".equals(mType)) {
                    int pdjg = 0; //是否是黑烟车:  判断结果 (0-不合格、 1-合格、 2-无效)
                    tempQueryCarListPresenter.getCbListData(pdjg, mPageRow, mPageNum, "jcrq", "desc", mHphm, mCpys);
                }
            }
        });
        this.mRefreshLayout.setOnLoadMoreListener((OnLoadMoreListener) new OnLoadMoreListener() {
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                //refreshLayout.finishLoadMore();
                refreshLayout.finishLoadMore(2000, true, mHasNoMore);

                if (!RemoteCheckRecordFragment.this.mHasNoMore) {
                    mPageNum++;
                    final QueryCarListPresenter tempQueryCarListPresenter = mQueryCarListPresenter;

                    // 此车辆遥测记录 || 超标记录
                    if ("yc".equals(mType)) {
                        int isBlackCar = 0; //是否是黑烟车: (0-不是、 1-是)
                        tempQueryCarListPresenter.getYcListData(isBlackCar, mPageRow, mPageNum, "jcrq", "desc", mHphm, mCpys);
                    } else if ("cb".equals(mType)) {
                        int pdjg = 0; //是否是黑烟车:  判断结果 (0-不合格、 1-合格、 2-无效)
                        tempQueryCarListPresenter.getCbListData(pdjg, mPageRow, mPageNum, "jcrq", "desc", mHphm, mCpys);
                    }
                }
            }
        });
    }

    private void initPresenter() {
        this.mQueryCarListPresenter = new QueryCarListPresenter(this);
    }

    private void getData() {
        this.mRefreshLayout.autoRefresh();
    }

    @Override
    public void setArguments(final Bundle bundle) {
        super.setArguments(bundle);
        this.mHphm = bundle.getString("hphm");
        this.mCpys = bundle.getString("cpys");
        this.mType = bundle.getString("type");
    }

    @Nullable
    public View onCreateView(@NonNull final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final Bundle bundle) {
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

    @Override
    public void setPresenter(final QueryCarListPresenter queryCarListPresenter) {
    }

    public void hideLoading() {
    }

    public void showCarInfo(final VehicleInfo getMonitoringDataDetailNewModel) {
    }

    @Override
    public void showJyListData(ResponseListModel<HuanjianModel> p0) {

    }

    public void showFail(final String s) {
    }

    public void showLoading() {
    }

    @Override
    public void showYcListData(final ResponseExhaustListModel getYcListResponseModel) {
        if (getYcListResponseModel.getRows().size() < this.mPageRow) {
            // 此页不超过15条数据，则是最后一页
            this.mHasNoMore = true;
        }

        if (getYcListResponseModel.getPageNum() == 1) {
            this.mAdapter.setData(getYcListResponseModel.getRows());
        } else {
            this.mAdapter.getData().addAll((Collection<? extends Exhaust>) getYcListResponseModel.getRows());
            this.mAdapter.notifyDataSetChanged();
        }

        hideLoading();

        //RxBus.get().post((Object) new UpdateHistoryEvent(this.mHphm));
    }

    @Override
    public void showCbListData(final ResponseExhaustListModel getYcListResponseModel) {
        if (getYcListResponseModel.getRows().size() < this.mPageRow) {
            // 此页不超过15条数据，则是最后一页
            this.mHasNoMore = true;
        }

        if (getYcListResponseModel.getPageNum() == 1) {
            this.mAdapter.setData(getYcListResponseModel.getRows());
        } else {
            this.mAdapter.getData().addAll((Collection<? extends Exhaust>) getYcListResponseModel.getRows());
            this.mAdapter.notifyDataSetChanged();
        }

        hideLoading();

        //RxBus.get().post((Object) new UpdateHistoryEvent(this.mHphm));
    }
}

