package com.baolong.obd.execution.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baolong.obd.R;
import com.baolong.obd.blackcar.event.RefreshTelemetryListByFilter;
import com.baolong.obd.blackcar.data.entity.Exhaust;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.blackcar.event.RefreshExecutionCarListByFilter;
import com.baolong.obd.common.utils.ActivityUtils;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.component.webview.ReportProductWebActivity;
import com.baolong.obd.execution.contract.ExecutionListContract;
import com.baolong.obd.monitor.activity.StationDetailActivity;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.baolong.obd.common.base.BaseFragment;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.common.view.RecyclerViewForEmpty;
import com.baolong.obd.execution.adapter.ExecListAdapter;
import com.baolong.obd.execution.event.UpdateExecListEvent;
import com.baolong.obd.execution.presenter.ExecutionListPresenter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import static com.baolong.obd.analysis.activity.AnalysisCommentActivity.Table_telemetry;
import static com.baolong.obd.execution.fragment.ExecutionMainFragment.Table_wcf;
import static com.baolong.obd.execution.fragment.ExecutionMainFragment.Table_ycf;

public class ExecListFragment extends BaseFragment
        implements View.OnClickListener, ExecutionListContract.View {
    public static final String TAG = ExecListFragment.class.getSimpleName();

    private View mRootView;
    private View mEmptyView;
    private boolean mHasNoMore;
    private String mType = null; //处罚状态 int类型 必填 (0:未处罚、 1:已处罚、 null:所有监测纪录)
    private int mPageNum = 1;
    private final int mPageSize = 10;
    private RefreshLayout mRefreshLayout;
    private RecyclerViewForEmpty mRecycler;
    private ExecListAdapter mAdapter;
    private ExecutionListPresenter mExecutionListPresenter;
    private List<FilterCategoryModel> mFilterCategoryModelList;

    @Override
    public void setArguments(Bundle paramBundle) {
        super.setArguments(paramBundle);
        this.mType = paramBundle.getString("type");
    }

    private void initTitle() {
    }

    private void initView() {
        this.mRefreshLayout = ((RefreshLayout) this.mRootView.findViewById(R.id.refresh_layout));

        this.mRecycler = ((RecyclerViewForEmpty) this.mRootView.findViewById(R.id.rv_recycler));
        this.mRecycler.setEmptyView(this.mEmptyView);
        this.mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        this.mAdapter = new ExecListAdapter(getActivity(), this.mType);
        this.mAdapter.setOnItemClickListener(new ExecListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Exhaust exhaust, String paramString) {
//                ARouter.getInstance()
//                        .build("/monitor/activity/BlackCarDetailActivity")
//                        .withString("jzbh", detail.getStationno())
//                        .withString("jlid", detail.getId())
//                        .navigation();
                if (exhaust != null) {
                    Intent intent = new Intent(getContext(), StationDetailActivity.class);
                    intent.putExtra("exhaust", exhaust);
                    intent.putExtra("optionType", "review");
                    ActivityUtils.activitySwitch((Activity) getContext(), intent, true);
                }
            }
        });

        this.mAdapter.setOnItemLongClickListener(new ExecListAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(Exhaust exhaust) {
                if (exhaust != null) {
                    Intent intent = new Intent(getContext(), ReportProductWebActivity.class);
                    intent.putExtra("title", "预览报告");
                    intent.putExtra("jlbh", exhaust.getJlbh());
                    //intent.putExtra("url", FileUtils.CombineUrl(BaseApplication.host, "/modules/overproof/previewbyjlbh",exhaust.getJlbh()));
                    ActivityUtils.activitySwitch(getActivity(), intent);
                }
                return true;
            }
        });

        this.mAdapter.setOnActionClickListener(new ExecListAdapter.OnActionClickListener() {
            @Override
            public void onActionClick(Exhaust exhaust, String type) {
                if (exhaust != null) {
                    Intent intent = new Intent(getContext(), StationDetailActivity.class);
                    intent.putExtra("exhaust", exhaust);
                    if (Table_wcf.equals(mType)) {
                        intent.putExtra("optionType", "judge");
                    } else {
                        intent.putExtra("optionType", "review");
                    }
                    ActivityUtils.activitySwitch((Activity) getContext(), intent, true);
                }
            }
        });

        this.mRecycler.setAdapter(this.mAdapter);
    }

    private void initData() {
    }

    private void addListener() {
        RxBus.get().register(this);
        this.mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //refreshLayout.finishRefresh();
                refreshLayout.finishRefresh(2000, true);
                mHasNoMore = false;
                mPageNum = 1;
                final ExecutionListPresenter tempExecutionListPresenter = mExecutionListPresenter;

                //未处罚车辆、已处罚车辆、超标车数据
                /*if (!Table_telemetry.equals(mType)) {
                    // pdjg         判断结果 (0-不合格、 1-合格、 2-无效)
                    // isornopunish 处罚状态 (0:未处罚、 1:已处罚、 null:所有监测纪录)
                    //tempProcessedListPresenter.getData("0", mType, mPageRow, mPageNum, "jcrq", "desc", mFilterCategoryModelList);

                    if (Table_wcf.equals(mType)) {
                        //超标：未审核
                        tempExecutionListPresenter.getWcfData("0", mType, mPageSize, mPageNum, "jcrq", "desc", mFilterCategoryModelList);
                    } else if (Table_ycf.equals(mType)){
                        //超标：已审核
                        tempExecutionListPresenter.getYcfData("0", mType, mPageSize, mPageNum, "jcrq", "desc", mFilterCategoryModelList);
                    } else if(mType == null){
                        //超标：所有超标车
                        tempExecutionListPresenter.getOverproofData("0", mType, mPageSize, mPageNum, "jcrq", "desc", mFilterCategoryModelList);
                    }

                } else { //遥测数据
                    tempExecutionListPresenter.getTelemetryData(mPageSize, mPageNum, "jcrq", "desc", mFilterCategoryModelList);
                }*/
            }
        });
        this.mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();
                if (!ExecListFragment.this.mHasNoMore) {
                    mPageNum++;
                    final ExecutionListPresenter tempExecutionListPresenter = mExecutionListPresenter;

                    //未处罚车辆、已处罚车辆、超标车数据
                    /*if (!Table_telemetry.equals(mType)) {
                        // pdjg         判断结果 (0-不合格、 1-合格、 2-无效)
                        // isornopunish 处罚状态 (0:未处罚、 1:已处罚、 null:所有监测纪录)
                        //tempProcessedListPresenter.getData("0", mType, mPageSize, mPageNum, "jcrq", "desc", mFilterCategoryModelList);

                        if ("0".equals(mType)) {
                            tempExecutionListPresenter.getWcfData("0", mType, mPageSize, mPageNum, "jcrq", "desc", mFilterCategoryModelList);
                        } else {
                            tempExecutionListPresenter.getYcfData("0", mType, mPageSize, mPageNum, "jcrq", "desc", mFilterCategoryModelList);
                        }

                    } else { //遥测数据
                        int isBlackCar = 0; //是否是黑烟车 (0-不是、 1-是)

                        tempExecutionListPresenter.getTelemetryData(mPageSize, mPageNum, "jcrq", "desc", mFilterCategoryModelList);
                    }*/
                }
            }
        });
    }

    private void initPresenter() {
        this.mExecutionListPresenter = new ExecutionListPresenter(this);
    }

    private void getData() {
        this.mRefreshLayout.autoRefresh();
    }

    public void onClick(View paramView) {
        //paramView.getId();
        //int i = R.id.tv_right_text;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle) {
        this.mRootView = paramLayoutInflater.inflate(R.layout.fragment_exec_list, paramViewGroup, false);
        this.mEmptyView = paramLayoutInflater.inflate(R.layout.recycler_empty, paramViewGroup, false);
        initTitle();
        initView();
        initData();
        initPresenter();
        addListener();
        getData();
        return this.mRootView;
    }

    public void onDestroyView() {
        RxBus.get().unregister(this);
        super.onDestroyView();
    }


    // 超标模块——筛选对应的事件
    @Subscribe
    public void refreshListByFilter(RefreshExecutionCarListByFilter paramRefreshExecutionCarListByFilter) {
        if (!Table_telemetry.equals(mType)) {
            this.mFilterCategoryModelList = paramRefreshExecutionCarListByFilter.getFilterCategoryModelList();
            this.mRefreshLayout.autoRefresh();
        }
    }

    // 遥测模块——筛选对应的事件
    @Subscribe
    public void refreshTelemetryListByFilter(RefreshTelemetryListByFilter paramRefreshTelemetryListByFilter) {
        if (Table_telemetry.equals(mType)) {
            this.mFilterCategoryModelList = paramRefreshTelemetryListByFilter.getFilterCategoryModelList();
            this.mRefreshLayout.autoRefresh();
        }
    }

    //超标车-处罚成功后对应的事件（刷新列表）
    @Subscribe
    public void refreshList(UpdateExecListEvent paramUpdateExecListEvent) {
        this.mRefreshLayout.autoRefresh();
    }


    // 重写 ProcessListContract.View接口  包含的抽象方法
    @Override
    public void setPresenter(ExecutionListPresenter paramExecutionListPresenter) {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showFail(String paramString) {
        ToastUtils.shortToast(paramString);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void setWcfData(List<Exhaust> exhaustList) {
        hideLoading();
        if (exhaustList == null) {
            return;
        }
        LogUtil.i(TAG, "items num:" + exhaustList.size());

        if (exhaustList.size() < this.mPageSize) {
            // 此页不超过15条数据，则是最后一页
            this.mHasNoMore = true;
        }

        if (mPageNum == 1) {
            // 更新总数
//            if ("0".equals(mType)){   // mType处罚状态 (0:未处罚、 1:已处罚、 null:所有监测纪录)
//                RxBus.get().post((Object) new UpdateExecSumEvent("exec", exhaustList.getTotal(), -1));
//            } else if ("1".equals(mType)){
//                RxBus.get().post((Object) new UpdateExecSumEvent("exec", -1, exhaustList.getTotal()));
//            }

            this.mAdapter.setData(exhaustList);

        } else {
            this.mAdapter.getData().addAll(exhaustList);
            this.mAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void setYcfooAllData(List<Exhaust> exhaustList) {
        hideLoading();
        if (exhaustList == null) {
            return;
        }
        LogUtil.i(TAG, "items num:" + exhaustList.size());

        if (exhaustList.size() < this.mPageSize) {
            // 此页不超过15条数据，则是最后一页
            this.mHasNoMore = true;
        }

        if (mPageNum == 1) {
            // 更新总数
//            if ("0".equals(mType)){   // mType处罚状态 (0:未处罚、 1:已处罚、 null:所有监测纪录)
//                RxBus.get().post((Object) new UpdateExecSumEvent("exec", exhaustList.getTotal(), -1));
//            } else if ("1".equals(mType)){
//                RxBus.get().post((Object) new UpdateExecSumEvent("exec", -1, exhaustList.getTotal()));
//            }

            this.mAdapter.setData(exhaustList);
        } else {
            this.mAdapter.getData().addAll(exhaustList);
            this.mAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void setTelemetryData(List<Exhaust> exhaustList) {
        hideLoading();
        if (exhaustList == null) {
            return;
        }
        LogUtil.i(TAG, "items num:" + exhaustList.size());

        if (exhaustList.size() < this.mPageSize) {
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

}
