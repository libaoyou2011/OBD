package com.baolong.obd.blackcar.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baolong.obd.R;
import com.baolong.obd.blackcar.contract.ProcessListContract;
import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.blackcar.data.entity.ExhaustZC;
import com.baolong.obd.blackcar.event.UpdateBlackSumEvent;
import com.baolong.obd.common.base.Constance;
import com.baolong.obd.common.utils.LogUtil;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.baolong.obd.blackcar.adapter.BlackCarListAdapter;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.blackcar.event.RefreshBlackCarListByFilter;
import com.baolong.obd.blackcar.event.UpdateBlackListEvent;
import com.baolong.obd.blackcar.presenter.BlackCarListPresenter;
import com.baolong.obd.common.base.BaseFragment;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.common.view.RecyclerViewForEmpty;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public class BlackCarListFragment extends BaseFragment
        implements View.OnClickListener, ProcessListContract.View {
    public static final String TAG = BlackCarListFragment.class.getSimpleName();

    public static final String OptionType_Review = "0";
    public static final String OptionType_Judge = "1";

    private String mTableType;  //未审核、已审核、所有黑烟

    private int mPageNum = 1;   //默认请求第 1 页
    private int mPageRow = 10;  //默认一页 10 条
    private boolean mHasNoMore; //是否为最后一页

    private String mCount = "";
    private String mTempSize = "";
    private boolean sumLoaded = false;  //对重新请求加载了数据进行标记，用于更新条数信息

    private View mRootView;
    private RefreshLayout mRefreshLayout;
    private RecyclerViewForEmpty mRecycler;
    private View mEmptyView;

    private BlackCarListAdapter mAdapter;
    private BlackCarListPresenter mBlackCarListPresenter;
    private List<FilterCategoryModel> mFilterCategoryModelList;

    private void initTitle() {
    }

    private void initView() {
        this.mRefreshLayout = ((RefreshLayout) this.mRootView.findViewById(R.id.refresh_layout));

        this.mRecycler = ((RecyclerViewForEmpty) this.mRootView.findViewById(R.id.rv_recycler));
        this.mRecycler.setEmptyView(this.mEmptyView);
        this.mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        this.mAdapter = new BlackCarListAdapter(getActivity(), this.mTableType);
        this.mAdapter.setOnItemClickListener(new BlackCarListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ExhaustZC dataDetail, String s) {
                ARouter.getInstance()
                        .build(Constance.ACTIVITY_URL_BlackCarDetailZCActivity)
                        .withString("tableType", mTableType)
                        .withString("optionType", OptionType_Review)
                        .withParcelable("exhaust", dataDetail)
                        .navigation();
            }
        });
       /* this.mAdapter.setOnItemLongClickListener(new BlackCarListAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(Exhaust exhaust) {
                if (exhaust != null) {
                    Intent intent = new Intent(getContext(), ReportProductWebActivity.class);
                    intent.putExtra("title", "预览报告");
                    intent.putExtra("jlbh", exhaust.getJlbh());
                    ActivityUtils.activitySwitch(getActivity(), intent);
                }
                return true;
            }
        });*/
        this.mAdapter.setOnActionClickListener(new BlackCarListAdapter.OnActionClickListener() {
            public void onActionClick(ExhaustZC dataDetail, String mType) {
//                if (Table_wsh.equals(mTableType)) {
//                    ARouter.getInstance()
//                            .build(Constance.ACTIVITY_URL_BlackCarDetailActivity)
//                            .withString("tableType", mTableType)
//                            .withString("optionType", OptionType_Judge)
//                            .withParcelable("exhaust", dataDetail)
//                            .navigation();
//                } else {
                ARouter.getInstance()
                        .build(Constance.ACTIVITY_URL_BlackCarDetailZCActivity)
                        .withString("tableType", mTableType)
                        .withString("optionType", OptionType_Review)
                        .withParcelable("exhaust", dataDetail)
                        .navigation();
//                }
            }
        });

        this.mRecycler.setAdapter(this.mAdapter);
    }

    private void initData() {
    }

    private void initNoDataText() {
    }

    private void addListener() {
        this.mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //refreshLayout.finishRefresh();
                //this.mRefreshLayout.finishRefresh();
                refreshLayout.finishRefresh(2000, true);
                BlackCarListFragment.this.mHasNoMore = false;
                BlackCarListFragment.this.mPageNum = 1;
                final BlackCarListPresenter tempBlackCarListPresenter = BlackCarListFragment.this.mBlackCarListPresenter;
                tempBlackCarListPresenter.getZcListData(mTableType, mPageRow, mPageNum, "jcrq", "desc", mFilterCategoryModelList);
//                if (Table_wsh.equals(type)) {
//                    tempBlackCarListPresenter.getDshData("0", mPageRow, mPageNum, "jcrq", "desc", mFilterCategoryModelList);
//                } else if (Table_cb.equals(type)) {
//                    tempBlackCarListPresenter.getYshData("0", "1", mPageRow, mPageNum, "jcrq", "desc", mFilterCategoryModelList);
//                } else if (Table_aLL.equals(type)) {
//                    tempBlackCarListPresenter.getAllData( mPageRow, mPageNum, "jcrq", "desc", mFilterCategoryModelList);
//                }
            }
        });
        this.mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                //refreshLayout.finishLoadMore();
                refreshLayout.finishLoadMore(2000, true, mHasNoMore);
                if (!BlackCarListFragment.this.mHasNoMore) {
                    BlackCarListFragment.this.mPageNum++;
                    final BlackCarListPresenter tempBlackCarListPresenter = BlackCarListFragment.this.mBlackCarListPresenter;
                    tempBlackCarListPresenter.getZcListData(mTableType, mPageRow, mPageNum, "jcrq", "desc", mFilterCategoryModelList);

//                    if (Table_wsh.equals(type)) {
//                        tempBlackCarListPresenter.getDshData("0", mPageRow, mPageNum, "jcrq", "desc", mFilterCategoryModelList);
//                    } else if (Table_ysh.equals(type)) {
//                        tempBlackCarListPresenter.getYshData("0", "1", mPageRow, mPageNum, "jcrq", "desc", mFilterCategoryModelList);
//                    } else if (Table_aLL.equals(type)) {
//                        tempBlackCarListPresenter.getAllData( mPageRow, mPageNum, "jcrq", "desc", mFilterCategoryModelList);
//                    }
                }
            }
        });
    }

    private void initPresenter() {
        this.mBlackCarListPresenter = new BlackCarListPresenter(this);
    }

    private void getDataByPresenter() {
        this.mRefreshLayout.autoRefresh();
    }

    @Override
    public void onClick(View paramView) {
        //paramView.getId();
        //int i = R.id.tv_right_text;
    }

    @Override
    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
        this.mTableType = bundle.getString("type");
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.fragment_exec_list, viewGroup, false);
        this.mEmptyView = layoutInflater.inflate(R.layout.recycler_empty, viewGroup, false);
        RxBus.get().register(this);
        initTitle();
        initView();
        initData();
        addListener();
        initPresenter();
        getDataByPresenter();
        return this.mRootView;
    }

    public void onDestroyView() {
        RxBus.get().unregister(this);
        super.onDestroyView();
    }


    // 黑烟模块——筛选对应的事件（刷新列表）
    @Subscribe
    public void refreshListByFilter(RefreshBlackCarListByFilter refreshBlackCarListByFilter) {
        LogUtil.i(TAG, "条件筛选后 刷新黑烟车列表：" + this.mTableType);
        this.mFilterCategoryModelList = refreshBlackCarListByFilter.getFilterCategoryModelList();
        this.mRefreshLayout.autoRefresh();
    }

    //审核成功对应的事件
    @Subscribe
    public void refreshListAfterSh(UpdateBlackListEvent paramRefreshBlackCar) {
        this.mRefreshLayout.autoRefresh();
    }


    /**
     * 实现 ProcessListContract.View接口 5个方法
     */
    @Override
    public void setPresenter(BlackCarListPresenter paramBlackCarListPresenter) {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showFail(String s) {
        ToastUtils.shortToast(s);
    }

    @Override
    public void showLoading() {
    }


    @Override
    public void setData(List<Exhaust> exhaustList) {
        /*hideLoading();
        if (exhaustList == null) {
            return;
        }
        LogUtil.i(TAG, "items num:" + exhaustList.size());

        this.sumLoaded = true;
        if (exhaustList.size() < this.mPageRow) {
            // 此页不超过15条数据，则是最后一页
            this.mHasNoMore = true;
        }

        if (mPageNum == 1) {
            // 更新总数
//            if (mTableType.equals(BlackCarMainFragment.Table_wsh)){   // mType处罚状态 (0:未处罚、 1:已处罚、 null:所有监测纪录)
//                RxBus.get().post((Object) new UpdateBlackSumEvent(BlackCarMainFragment.Table_wsh, exhaustList.getTotal(), -1, -1));
//            } else if (mTableType.equals(BlackCarMainFragment.Table_ysh)){
//                RxBus.get().post((Object) new UpdateBlackSumEvent(BlackCarMainFragment.Table_ysh, -1, exhaustList.getTotal(), -1));
//            }else if (mTableType.equals(BlackCarMainFragment.Table_aLL)){
//                RxBus.get().post((Object) new UpdateBlackSumEvent(BlackCarMainFragment.Table_aLL, -1, -1, exhaustList.getTotal()));
//            }

            this.mAdapter.setData(exhaustList);

        } else {
            this.mAdapter.getData().addAll(exhaustList);
            this.mAdapter.notifyDataSetChanged();

        }*/
    }

    @Override
    public void setZcData(List<ExhaustZC> exhaustList, int total) {
        hideLoading();
        if (exhaustList == null) {
            return;
        }
        LogUtil.i(TAG, "items num:" + exhaustList.size());

        this.sumLoaded = true;
        if (exhaustList.size() < this.mPageRow) {
            // 此页不超过15条数据，则是最后一页
            this.mHasNoMore = true;
        }

        if (mPageNum == 1) {
            // 1.更新总数
            switch (mTableType) {
                case BlackCarMainFragment.Table_hg:    // mType处罚状态 (0:未处罚、 1:已处罚、 null:所有监测纪录)
                    RxBus.get().post((Object) new UpdateBlackSumEvent(mTableType, total, -1, -1));
                    break;
                case BlackCarMainFragment.Table_cb:
                    RxBus.get().post((Object) new UpdateBlackSumEvent(mTableType, -1, total, -1));
                    break;
                case BlackCarMainFragment.Table_wpd:
                    RxBus.get().post((Object) new UpdateBlackSumEvent(mTableType, -1, -1, total));
                    break;
            }
            // 2.更新列表集合
            this.mAdapter.setData(exhaustList);

        } else {
            this.mAdapter.getData().addAll(exhaustList);
            this.mAdapter.notifyDataSetChanged();

        }
    }
}
