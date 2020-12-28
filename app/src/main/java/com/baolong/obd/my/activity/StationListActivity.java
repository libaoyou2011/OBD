package com.baolong.obd.my.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.common.widget.DialogManager;
import com.baolong.obd.my.contract.StationListContract;
import com.hwangjr.rxbus.RxBus;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.common.sharepreferemces.UserSP;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.my.adapter.StationListAdapter;
import com.baolong.obd.my.data.entity.GetDefaultStationResponseModel;
import com.baolong.obd.my.data.entity.GetStationListAllResponseModel;
import com.baolong.obd.my.event.UpdateDefaultStationEvent;
import com.baolong.obd.my.presenter.StationListPresenter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public class StationListActivity extends BaseActivity
        implements StationListContract.View {
    private boolean mHasNoMore;

    private RecyclerView mRecycler;
    private RefreshLayout mRefreshLayout;
    private StationListAdapter stationListAdapter;
    private StationListPresenter stationListPresenter;

    private void initTitle() {
        ((ImageView) findViewById(R.id.image_title_back)).setOnClickListener((view) -> {
            this.onBackPressed();
        });
        ((TextView) findViewById(R.id.tv_title)).setText(getResources().getString(R.string.my_station_list_title));
    }

    private void initView() {
        this.mRefreshLayout = ((RefreshLayout) findViewById(R.id.refresh_layout));
        this.mRecycler = ((RecyclerView) findViewById(R.id.rv_recycler));
        this.mRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private void addListener() {
        this.mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            //上拉刷新
            public void onRefresh(RefreshLayout refreshLayout) {
                //refreshLayout.finishRefresh();
                refreshLayout.finishRefresh(2000,true);

                mHasNoMore = false;

                if (!mHasNoMore){
                    StationListActivity.this.stationListPresenter.getStationListAll(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId);
                }
            }
        });
    }

    private void initData() {
        this.stationListAdapter = new StationListAdapter(this, (GetStationListAllResponseModel model) -> {
            StringBuilder sb = new StringBuilder();
            sb.append("您确定要将当前站点修改为:'");
            sb.append(model.getJzmc());
            sb.append("'吗?");
            DialogManager.showConfirmDialog(StationListActivity.this, sb.toString()
                    , new View.OnClickListener() {
                        @Override
                        public void onClick(android.view.View v) {
                            // do nothing
                        }
                    }
                    , new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            stationListPresenter.setDefaultStation(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, UserSP.getUserName(), model.getJzbh(), model.getJzmc());
                        }
                    }
            ).show();
        });
        this.mRecycler.setAdapter(this.stationListAdapter);
        this.stationListPresenter = new StationListPresenter(this);
        this.stationListPresenter.getStationListAll(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_station_list);
        initTitle();
        initView();
        addListener();
        initData();
    }

    public void setPresenter(StationListPresenter paramStationListPresenter) {
    }

    public void showLoading() {
        showLoading(this.mRecycler);
    }

    public void showFail(String paramString) {
        ToastUtils.shortToast(paramString);
    }

    public void showStationListAll(List<GetStationListAllResponseModel> paramList) {
        this.stationListAdapter.getData().clear();
        this.stationListAdapter.setData(paramList);
    }

    public void showChangeStationSuccess(String paramString) {
        RxBus.get().post(new UpdateDefaultStationEvent(paramString));
        finish();
    }

    public void showCurrentStation(GetDefaultStationResponseModel paramGetDefaultStationResponseModel) {
    }
}
