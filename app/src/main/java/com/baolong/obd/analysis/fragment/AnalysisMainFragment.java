package com.baolong.obd.analysis.fragment;

//import java.util.LayoutInflaterist;

import android.content.Intent;

import com.baolong.obd.R;
import com.baolong.obd.analysis.activity.AnalysisCommentActivity;
import com.baolong.obd.analysis.data.entity.AnalysisLayoutModel;
import com.baolong.obd.common.base.BaseApplication;
import com.baolong.obd.common.utils.ActivityUtils;
import com.baolong.obd.common.utils.FileUtils;
import com.baolong.obd.analysis.adapter.QueryHistoryListAdapter;
//import com.baolong.edsp.analysis.data.entity.GetMonitoringDataDetailNewModel;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.component.webview.ProductWebActivity;
import com.google.gson.Gson;
import com.hwangjr.rxbus.RxBus;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

//import com.baolong.edsp.analysis.event.UpdateHistoryEvent;
//import com.baolong.edsp.analysis.data.entity.GetVehicleQueryListResponseModel;

import android.support.v7.widget.GridLayoutManager;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import android.widget.ImageView;

import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

//import com.baolong.edsp.analysis.presenter.QueryCarMainPresenter;
//import com.baolong.edsp.analysis.contract.QueryCarMainContract;

import com.baolong.obd.common.base.BaseFragment;

public class AnalysisMainFragment extends BaseFragment
        implements View.OnClickListener {
//    , QueryCarMainContract.View
    private static final String TAG = AnalysisMainFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private android.view.View mView;
//    private QueryCarMainPresenter queryCarMainPresenter;
    private QueryHistoryListAdapter queryHistoryListAdapter;

    AnalysisLayoutModel mAnalysisLayoutModel;

    private void initTitle() {
        ((ImageView) this.mView.findViewById(R.id.image_title_back)).setVisibility(View.GONE);
        ((TextView) this.mView.findViewById(R.id.tv_title)).setText(R.string.analysis_title);
    }

    private void initView(final LayoutInflater layoutInflater) {
        this.mRecyclerView = (RecyclerView) this.mView.findViewById(R.id.rv_recycler);
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        this.mRecyclerView.addItemDecoration(new SpaceItemDecoration(4), -1);

        this.queryHistoryListAdapter = new QueryHistoryListAdapter((Context) this.getActivity(),
                new QueryHistoryListAdapter.OnHistoryClickListener() {
                    @Override
                    public void onItemClick(AnalysisLayoutModel.ProductsBean.ItemsBean itemsBean) {
                        if (!TextUtils.isEmpty(itemsBean.getActivity())) {
                            Intent intent = new Intent(getContext(), AnalysisCommentActivity.class);
                            intent.putExtra("title", itemsBean.getDesc());
                            intent.putExtra("type", itemsBean.getCode());
                            ActivityUtils.activitySwitch(getActivity(), intent);

                        } else if (!TextUtils.isEmpty(itemsBean.getWebUrl())) {
                            Intent intent = new Intent(getContext(), ProductWebActivity.class);
                            intent.putExtra("title", itemsBean.getTitle());
                            intent.putExtra("url", FileUtils.CombineUrl(BaseApplication.host, itemsBean.getWebUrl()));

                            LogUtil.i(TAG, FileUtils.CombineUrl(BaseApplication.host, itemsBean.getWebUrl()));
                            ActivityUtils.activitySwitch(getActivity(), intent);
                        }
                    }
                });
        this.mRecyclerView.setAdapter((RecyclerView.Adapter) this.queryHistoryListAdapter);

        //加载布局文件
        try {
            final String textFromFile = FileUtils.readTextFromAssets(getActivity(), "layout_analysis.json");
            mAnalysisLayoutModel = (AnalysisLayoutModel) new Gson().fromJson(textFromFile, AnalysisLayoutModel.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (mAnalysisLayoutModel != null && mAnalysisLayoutModel.getProducts() != null && mAnalysisLayoutModel.getProducts().size() > 0) {
            this.queryHistoryListAdapter.setData(mAnalysisLayoutModel.getProducts().get(0).getItems());
        }

    }

    private void addListener() {

    }

//    private void initPresenter() {
//        this.queryCarMainPresenter = new QueryCarMainPresenter(this);
//    }

//    private void getDataByPresenter() {
//        this.queryCarMainPresenter.getVehicleQueryList(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, UserSP.getUserName());
//    }

    public void onClick(final View view) {
    }

//    @Subscribe
//    public void insertSearchCarNo(final UpdateHistoryEvent updateHistoryEvent) {
//        this.queryCarMainPresenter.insertVehicleQuery(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, UserSP.getUserName(), updateHistoryEvent.getId(), "0");
//    }

    public static AnalysisMainFragment newInstance() {
        return new AnalysisMainFragment();
    }

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final Bundle savedInstanceState) {
        RxBus.get().register((Object) this);
        this.mView = layoutInflater.inflate(R.layout.fragment_analysis, (ViewGroup) null);
        this.initTitle();
        this.initView(layoutInflater);
        this.addListener();
//        this.initPresenter();

        return this.mView;
    }

    @Override
    public void onViewCreated(final android.view.View view, @Nullable final Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        //暂时不加载数据
        //this.getDataByPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister((Object) this);
    }

//    @Override
//    public void setPresenter(final QueryCarMainPresenter queryCarMainPresenter) {
//    }

//    public void showLoading() {
//        ((BaseActivity) this.getActivity()).showLoading(this.mView);
//    }
//
//    public void hideLoading() {
//        ((BaseActivity) this.getActivity()).hideLoading();
//    }
//
//    public void showFail(final String s) {
//        ToastUtils.longToast(s);
//    }

    @Override
    public void refresh() {
    }

    public void refreshHistoryList() {
//        this.getDataByPresenter();
    }

//    public void showCarInfo(final GetMonitoringDataDetailNewModel getMonitoringDataDetailNewModel) {
//        if (getMonitoringDataDetailNewModel != null && !TextUtils.isEmpty((CharSequence) getMonitoringDataDetailNewModel.gethphm())) {
//            final Intent intent = new Intent((Context) this.getActivity(), (Class) CarNoInfoActivity.class);
//            intent.putExtra("hphm", getMonitoringDataDetailNewModel.gethphm());
//            this.getActivity().startActivity(intent);
//            return;
//        }
//        ToastUtils.longToast("没有查询到您输入的车牌号码相关信息，请确认后重新输入!");
//    }

//    public void showQueryHistoryList(final List<GetVehicleQueryListResponseModel> data) {
//        this.queryHistoryListAdapter.getData().clear();
//        this.queryHistoryListAdapter.setData(data);
//    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //不是第一个的格子都设一个左边和底部的间距
            outRect.left = space;
            outRect.bottom = space;
            //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
            if (parent.getChildLayoutPosition(view) % 3 == 0) {
                outRect.left = 0;
            }
        }

    }
}
