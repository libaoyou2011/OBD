package com.baolong.obd.querycar.fragment;

import java.util.List;

import android.content.Intent;

import com.baolong.obd.R;
import com.baolong.obd.blackcar.data.entity.VehicleInfo;
import com.baolong.obd.common.utils.CommonUtils;
import com.baolong.obd.common.utils.VehicleEnums;
import com.baolong.obd.querycar.activity.CarNoInfoActivity;
import com.baolong.obd.querycar.adapter.QueryHistoryListAdapter;
import com.hwangjr.rxbus.RxBus;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.baolong.obd.querycar.event.UpdateHistoryEvent;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.querycar.data.entity.GetVehicleQueryListResponseModel;

import com.baolong.obd.common.utils.ToastUtils;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import android.content.Context;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.common.sharepreferemces.UserSP;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.EditText;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Spinner;
import android.widget.TextView;

import com.baolong.obd.querycar.presenter.QueryCarMainPresenter;
import com.baolong.obd.querycar.contract.QueryCarMainContract;

import com.baolong.obd.common.base.BaseFragment;

public class QueryCarMainFragment extends BaseFragment
        implements View.OnClickListener, QueryCarMainContract.View {
    private static final String TAG = QueryCarMainFragment.class.getSimpleName();
    private android.view.View mView;
    private Spinner mCPYSSN;
    private String[] cpysArray = new String[]{"蓝牌", "黄牌", "白牌", "黑牌", "绿牌"};
    private ArrayAdapter<String> cpysAdapter;
    private EditText searchCarNoEt;
    private ImageView searchImg;

    private RecyclerView mRecyclerView;
    private QueryHistoryListAdapter queryHistoryListAdapter;
    private TextView clearAllTv;

    private QueryCarMainPresenter queryCarMainPresenter;

    private void initView(final LayoutInflater layoutInflater) {
        mCPYSSN = (Spinner) this.mView.findViewById(R.id.spinner_cpys);
        cpysAdapter = new ArrayAdapter<>(QueryCarMainFragment.this.getActivity(), android.R.layout.simple_spinner_item, cpysArray);
        cpysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCPYSSN.setAdapter(cpysAdapter);

        this.searchCarNoEt = (EditText) this.mView.findViewById(R.id.et_search_station);
        this.searchImg = (ImageView) this.mView.findViewById(R.id.img_search);
        this.mRecyclerView = (RecyclerView) this.mView.findViewById(R.id.rv_recycler);
        this.clearAllTv = (TextView) this.mView.findViewById(R.id.tv_clear_all);

        this.mRecyclerView.setVisibility(View.GONE);
        this.clearAllTv.setVisibility(View.GONE);
/*
        this.mRecyclerView.setLayoutManager((RecyclerView.LayoutManager) new LinearLayoutManager((Context) this.getActivity()));
        this.queryHistoryListAdapter = new QueryHistoryListAdapter((Context) this.getActivity(),
                new QueryHistoryListAdapter.OnHistoryLongClickListener() {
                    @Override
                    public void onLongClick(GetVehicleQueryListResponseModel model) {
                        DialogManager.showConfirmDialog(QueryCarMainFragment.this.getContext()
                                , "您确定要删除此条记录吗?"
                                , new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // do nothing
                                    }
                                }
                                , new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // 删除此车辆查询纪录
                                        queryCarMainPresenter.deleteVehicleQueryInfo(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, UserSP.getUserName(), model.getId());

                                    }
                                }
                        ).show();

                    }
                },
                new QueryHistoryListAdapter.OnHistoryClickListener() {
                    @Override
                    public void onItemClick(GetVehicleQueryListResponseModel model) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append("您是希望重新查询车牌号码:");
                        sb.append(model.getHphm());
                        sb.append("吗?");
                        DialogManager.showConfirmDialog((Context) getContext()
                                , sb.toString()
                                , new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        // do something
                                    }
                                }
                                , new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        // 查询此车辆的基本信息
                                        queryCarMainPresenter.getVehicleBasicInfo(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, model.getHphm(), "0");
                                    }
                                }
                        ).show();
                    }
                });
        this.mRecyclerView.setAdapter((RecyclerView.Adapter) this.queryHistoryListAdapter);*/
    }

    private void addListener() {
        mCPYSSN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * @param parent parent是你当前所操作的Spinner
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //LogUtil.i("点击------",dwlxArray[position]);
            }

            /**
             * 没有数据的时候执行
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.searchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(searchCarNoEt.getText().toString().trim())) {
                    ToastUtils.longToast("请输入要查询的车牌号码！");
                    return;
                }

                if (!CommonUtils.isCarnumberNO(searchCarNoEt.getText().toString().trim())) {
                    ToastUtils.longToast("输入车牌号码的格式不正确！");
                    return;
                }

                // 查询车辆
                //queryCarMainPresenter.getVehicleBasicInfo(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, searchCarNoEt.getText().toString(), "0");


                // 直接跳转到车辆详情页面
                final Intent intent = new Intent((Context) getActivity(), CarNoInfoActivity.class);
                intent.putExtra("hphm", searchCarNoEt.getText().toString().trim());
                /*String cpys = "0";
                switch (mCPYSSN.getSelectedItem().toString()) {
                    case "蓝牌":
                        cpys = "0";
                        break;
                    case "黄牌":
                        cpys = "1";
                        break;
                    case "白牌":
                        cpys = "2";
                        break;
                    case "黑牌":
                        cpys = "3";
                        break;
                    case "绿牌":
                        cpys = "4";
                        break;
                    default:
                        cpys = "0";
                }*/
                intent.putExtra("cpys", VehicleEnums.cpys2Bs(mCPYSSN.getSelectedItem().toString()));
                startActivity(intent);
            }
        });

        this.searchCarNoEt.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        if (source.equals(" ") || source.toString().contentEquals("\n")) {
                            return "";
                        } else {
                            return null;
                        }
                    }
                }
        });

        // 在我们编辑完之后点击软键盘上的回车键才会触发
        this.searchCarNoEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId != 3) {
                    return false;
                }
                if (TextUtils.isEmpty(searchCarNoEt.getText().toString().trim())) {
                    ToastUtils.longToast("请输入要查询的车牌号码！");
                    return true;
                }
                if (!CommonUtils.isCarnumberNO(searchCarNoEt.getText().toString().trim())) {
                    ToastUtils.longToast("输入车牌号码的格式不正确！");
                    return true;
                }
                // 查询车辆
                //queryCarMainPresenter.getVehicleBasicInfo(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, searchCarNoEt.getText().toString(), "0");
                // 直接查询
                final Intent intent = new Intent((Context) getActivity(), CarNoInfoActivity.class);
                intent.putExtra("hphm", searchCarNoEt.getText().toString().trim());
                intent.putExtra("cpys", VehicleEnums.cpys2Bs(mCPYSSN.getSelectedItem().toString()));
                startActivity(intent);
                return true;
            }
        });
        /*this.clearAllTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogManager.showConfirmDialog(getContext()
                        , "您确定要删除所有的查询历史吗?"
                        , new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }
                        , new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                queryCarMainPresenter.deleteVehicleQueryInfo(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, UserSP.getUserName(), UserSP.getUserName());
                            }
                        });
            }
        });*/
    }

    private void initPresenter() {
        this.queryCarMainPresenter = new QueryCarMainPresenter(this);
    }

    private void getDataByPresenter() {
        this.queryCarMainPresenter.getVehicleQueryList(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, UserSP.getUserName());
    }

    public void onClick(final View view) {
    }

    @Subscribe
    public void insertSearchCarNo(final UpdateHistoryEvent updateHistoryEvent) {
        this.queryCarMainPresenter.insertVehicleQuery(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, UserSP.getUserName(), updateHistoryEvent.getId(), "0");
    }

    public static QueryCarMainFragment newInstance() {
        return new QueryCarMainFragment();
    }

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final Bundle savedInstanceState) {
        RxBus.get().register((Object) this);
        this.mView = layoutInflater.inflate(R.layout.fragment_query_car, (ViewGroup) null);

        this.initView(layoutInflater);
        this.addListener();
        this.initPresenter();
        return this.mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        //暂时不加载数据
        //this.getDataByPresenter();
    }

    @Override
    public void onViewCreated(final android.view.View view, @Nullable final Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister((Object) this);
    }


    // implement 实现几个接口
    @Override
    public void setPresenter(final QueryCarMainPresenter queryCarMainPresenter) {
    }

    @Override
    public void showLoading() {
        ((BaseActivity) this.getActivity()).showLoading(this.mView);
    }

    @Override
    public void hideLoading() {
        ((BaseActivity) this.getActivity()).hideLoading();
    }

    @Override
    public void showFail(final String s) {
        ToastUtils.longToast(s);
    }

    @Override
    public void refresh() {
    }

    @Override
    public void refreshHistoryList() {
        this.getDataByPresenter();
    }

    @Override
    public void showCarInfo(final VehicleInfo getMonitoringDataDetailNewModel) {
//        if (getMonitoringDataDetailNewModel != null && !TextUtils.isEmpty((CharSequence) getMonitoringDataDetailNewModel.gethphm())) {
//            final Intent intent = new Intent((Context) this.getActivity(), (Class) CarNoInfoActivity.class);
//            intent.putExtra("hphm", getMonitoringDataDetailNewModel.gethphm());
//            this.getActivity().startActivity(intent);
//            return;
//        }
//        ToastUtils.longToast("没有查询到您输入的车牌号码相关信息，请确认后重新输入!");
    }

    @Override
    public void showQueryHistoryList(final List<GetVehicleQueryListResponseModel> data) {
        this.queryHistoryListAdapter.getData().clear();
        this.queryHistoryListAdapter.setData(data);
    }
}
