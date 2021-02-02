package com.baolong.obd.monitor.activity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.view.RecyclerViewForEmpty;
import com.baolong.obd.monitor.adapter.SiteStatueListAdapter;
import com.baolong.obd.monitor.contract.SiteStatueListContract;
import com.baolong.obd.monitor.data.entity.SiteInfoItem;
import com.baolong.obd.monitor.data.entity.SiteInfoItemV3;
import com.baolong.obd.monitor.event.JumpToMapEvent;
import com.baolong.obd.monitor.presenter.SiteStatueListPresenter;
import com.hwangjr.rxbus.RxBus;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public class SitesStatueActivity extends BaseActivity
        implements SiteStatueListContract.View {
    private SiteStatueListAdapter mAdapter;
    private View mEmptyView;
    private boolean mHasNoMore;
    private int mPageNum = 1;
    private int mPageRow = 15;
    private RecyclerViewForEmpty mRecycler;
    private RefreshLayout mRefreshLayout;
    private SiteStatueListPresenter mSiteStatueListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning_list);

        /*
        //在需要监听的对象中调用RefWatcher的watch方法进行监听，比如我想监听一个Activity
        BaseApplication.getRefWatcher().watch(this);
        //模拟单例内存泄漏
        SingletonTest.newInstance(this).dealData();
        */

        initTitle();
        initView();
        initAdater();
        addListener();
        initPresenter();
        getData();
    }

    private void initTitle() {
        ((ImageView) findViewById(R.id.image_title_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ((TextView) findViewById(R.id.tv_title)).setText(getResources().getString(R.string.monitor_station_warn_title1));
    }

    private void initView() {
        this.mEmptyView = LayoutInflater.from(this).inflate(R.layout.recycler_empty, null, false);

        this.mRefreshLayout = ((RefreshLayout) findViewById(R.id.refresh_layout));
        this.mRecycler = ((RecyclerViewForEmpty) findViewById(R.id.rv_recycler));
        this.mRecycler.setEmptyView(this.mEmptyView);
        this.mRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initAdater() {
        this.mAdapter = new SiteStatueListAdapter(this, new SiteStatueListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SiteInfoItem siteInfoItem) {
                if (siteInfoItem != null) {
                    RxBus.get().post((Object) new JumpToMapEvent(siteInfoItem));
                    SitesStatueActivity.this.finish();
                }
            }
        });
        this.mRecycler.setAdapter(this.mAdapter);
    }

    private void addListener() {
        this.mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh(RefreshLayout refreshLayout) {
                //refreshLayout.finishRefresh();
                refreshLayout.finishRefresh(2000, true);
                mHasNoMore = false;
                mPageNum = 1;
                mSiteStatueListPresenter.getWarningData();

            }
        });
        this.mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            public void onLoadMore(RefreshLayout refreshLayout) {
                //refreshLayout.finishLoadMore();
                refreshLayout.finishLoadMore(2000, true, mHasNoMore);
                if (!SitesStatueActivity.this.mHasNoMore) {
                    mPageNum++;
                    mSiteStatueListPresenter.getWarningData();
                }
            }
        });
    }


    private void initPresenter() {
        this.mSiteStatueListPresenter = new SiteStatueListPresenter(this);
    }

    private void getData() {
        this.mSiteStatueListPresenter.getWarningData();
        showLoading();
    }

    public void setPresenter(SiteStatueListPresenter paramSiteStatueListPresenter) {
    }

    public void showWarningSites(List<SiteInfoItemV3> paramList) {
        //if (paramGetFaultstateDataListResponseModel.getList().size() < this.mPageRow) {
        this.mHasNoMore = true;
        //}

        //if (paramGetFaultstateDataListResponseModel.getPageNo() == 1) {
        this.mAdapter.setData(paramList);
        hideLoading();
        //    return;
        // }

        //this.mAdapter.getData().addAll(paramGetFaultstateDataListResponseModel.getList());
        this.mAdapter.notifyDataSetChanged();
    }

    public void showFail(String paramString) {
    }

    public void showLoading() {

    }
}
