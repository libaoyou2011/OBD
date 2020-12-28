package com.baolong.obd.my.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.baolong.obd.BuildConfig;
import com.baolong.obd.R;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.common.sharepreferemces.UserSP;
import com.baolong.obd.common.widget.DialogManager;
import com.baolong.obd.my.activity.ResetPasswordActivity;
import com.baolong.obd.my.activity.PublishActivity;
import com.baolong.obd.my.activity.UserInfoActivity;
import com.baolong.obd.my.contract.StationListContract;
import com.baolong.obd.my.event.UpdateDefaultStationEvent;
import com.hwangjr.rxbus.RxBus;
import com.baolong.obd.common.base.BaseFragment;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.my.data.entity.GetDefaultStationResponseModel;
import com.baolong.obd.my.data.entity.GetStationListAllResponseModel;
import com.baolong.obd.my.presenter.StationListPresenter;
import com.hwangjr.rxbus.annotation.Subscribe;

import java.util.List;

public class MyMainFragment extends BaseFragment
        implements StationListContract.View {

    private View mView;
    private TextView mUserInfoTv;
    private TextView mCurrentServerUrlTv;
    private TextView mNameTv;
    private TextView mStationNameTv;
    private TextView mStationSetTv;
    private TextView mChoosePhotosTv;
    private TextView mChangePasswordTv;
    private TextView mAppVersionTV;
    private RelativeLayout mSendRL;
    private Switch mSendSW;
    private TextView mExit;

    private StationListPresenter stationListPresenter;

    private void initTitle() {
        ((ImageView) this.mView.findViewById(R.id.image_title_back)).setVisibility(View.GONE);
        final TextView titleTextView = (TextView) this.mView.findViewById(R.id.tv_title);
        titleTextView.setText(getResources().getString(R.string.main_viewpager_mine));
    }

    private void initView() {
        this.mUserInfoTv = (TextView) this.mView.findViewById(R.id.tv_user_info);
        this.mNameTv = (TextView) this.mView.findViewById(R.id.tv_name);
        this.mStationNameTv = (TextView) this.mView.findViewById(R.id.tv_station_name);
        this.mStationSetTv = (TextView) this.mView.findViewById(R.id.tv_station_set);
        this.mChoosePhotosTv = (TextView) this.mView.findViewById(R.id.tv_choose_photos);
        this.mCurrentServerUrlTv = (TextView) this.mView.findViewById(R.id.tv_current_server);
        this.mChangePasswordTv = (TextView) this.mView.findViewById(R.id.tv_authentication);
        this.mAppVersionTV = (TextView) this.mView.findViewById(R.id.tv_current_version);
        this.mSendRL = (RelativeLayout) this.mView.findViewById(R.id.rl_send);
        this.mSendSW = (Switch) this.mView.findViewById(R.id.switch_send);
        this.mExit = (TextView) this.mView.findViewById(R.id.tv_logout);

        String userName = UserSP.getUserName();
        this.mNameTv.setText("用户名：" + userName);

        mStationNameTv.setVisibility(View.GONE);

        mAppVersionTV.setText("当前版本：V" + BuildConfig.VERSION_NAME);

        mSendRL.setVisibility(View.GONE);
        mSendSW.setChecked(UserSP.getSendMessage());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("当前服务器地址：");
        stringBuilder.append(UserSP.getUserServerUrl());
        this.mCurrentServerUrlTv.setText(stringBuilder.toString());

        this.stationListPresenter = new StationListPresenter(this);
    }

    private void addListener() {
        this.mUserInfoTv.setOnClickListener((view) -> {
            Intent intent = new Intent(getContext(), UserInfoActivity.class);
            startActivity(intent);
        });
        this.mChoosePhotosTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PublishActivity.class);
                startActivity(intent);
            }
        });
//        this.mStationSetTv.setOnClickListener((view) -> {
//            Intent intent = new Intent(getContext(), StationListActivity.class);
//            startActivity(intent);
//        });
        this.mChangePasswordTv.setOnClickListener((view) -> {
            Intent intent = new Intent(getContext(), ResetPasswordActivity.class);
            startActivity(intent);
        });
        this.mCurrentServerUrlTv.setOnClickListener((view) -> {
            DialogManager.showServerInputDialog(MyMainFragment.this.getActivity()
                    , "修改服务器地址"
                    , (DialogManager.OnItemClickListener) new DialogManager.OnItemClickListener() {
                        @Override
                        public void onItemClick(final android.view.View view, final String s) {
                            // do nothing
                        }
                    }
                    , "取消"
                    , (DialogManager.OnItemClickListener) new DialogManager.OnItemClickListener() {
                        @Override
                        public void onItemClick(android.view.View view, String userServerUrl) {
                            UserSP.setUserServerUrl(userServerUrl);
                            MyMainFragment.this.mCurrentServerUrlTv.setText((CharSequence) UserSP.getUserServerUrl());
                            RetrofitManager.BASE_URL = UserSP.getUserServerUrl();
                            RetrofitManager.restartRetrofitManager();
                        }
                    }
                    , "确定"
                    , UserSP.getUserServerUrl()
            ).show();
        });
        mSendSW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Todo
                    UserSP.setSendMessage(true);
                } else {
                    //Todo
                    UserSP.setSendMessage(false);
                }
            }
        });
        this.mExit.setOnClickListener((view) -> {
            DialogManager.showConfirmDialog(MyMainFragment.this.getContext()
                    , "您确定要退出吗?"
                    , new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }
                    , new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            UserSP.clearData();
                            if (MyMainFragment.this.getActivity() != null) {
                                MyMainFragment.this.getActivity().finish();
                            }
                        }
                    }
            ).show();
        });
    }


    private void getDataByPresenter() {
        //this.stationListPresenter.getDefaultStation(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, UserSP.getUserName());
    }

    @Subscribe
    public void changeDefaultStation(UpdateDefaultStationEvent updateDefaultStationEvent) {
        this.stationListPresenter.getDefaultStation(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, UserSP.getUserName());
    }

    public static MyMainFragment newInstance() {
        return new MyMainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        RxBus.get().register(this);
        this.mView = paramLayoutInflater.inflate(R.layout.fragment_my1, null);
        initTitle();
        initView();
        addListener();
        return this.mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void onResume() {
        super.onResume();
        getDataByPresenter();
    }

    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
    }

    public void refresh() {
    }

    public void setPresenter(StationListPresenter paramStationListPresenter) {
    }

    public void showLoading() {
    }

    public void hideLoading() {
    }

    public void showFail(String paramString) {
        ToastUtils.shortToast(paramString);
    }

    public void showCurrentStation(GetDefaultStationResponseModel model) {
        this.mStationNameTv.setText(model.getJzmc());
    }

    public void showChangeStationSuccess(String paramString) {
    }

    public void showStationListAll(List<GetStationListAllResponseModel> paramList) {
    }
}
