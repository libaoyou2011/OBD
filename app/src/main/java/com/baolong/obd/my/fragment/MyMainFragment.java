package com.baolong.obd.my.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.baolong.obd.BuildConfig;
import com.baolong.obd.R;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.common.sharepreferemces.UserSP;
import com.baolong.obd.common.update.DialogConfirmActivity;
import com.baolong.obd.common.update.model.CheckVersionResponseModel;
import com.baolong.obd.common.widget.DialogManager;
import com.baolong.obd.my.activity.ResetPasswordActivity;
import com.baolong.obd.my.activity.PublishActivity;
import com.baolong.obd.my.activity.UserInfoActivity;
import com.baolong.obd.my.contract.StationListContract;
import com.baolong.obd.my.event.UpdateDefaultStationEvent;
import com.baolong.obd.my.event.UpdateVersionEvent;
import com.hwangjr.rxbus.RxBus;
import com.baolong.obd.common.base.BaseFragment;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.my.data.entity.GetDefaultStationResponseModel;
import com.baolong.obd.my.data.entity.GetStationListAllResponseModel;
import com.baolong.obd.my.presenter.StationListPresenter;
import com.hwangjr.rxbus.annotation.Subscribe;

import java.util.List;
import java.util.Objects;


public class MyMainFragment extends BaseFragment implements StationListContract.View {

    private boolean hasNewVersion;
    CheckVersionResponseModel mModel;

    private View mView;
    private TextView mNameTv;
    private LinearLayout mUserInfoLL;
    private TextView mUserInfoTv;
    private LinearLayout mChangePasswordLL;
    private TextView mChangePasswordTv;
    private LinearLayout mAppVersionLL;
    private TextView mAppVersionTV;
    private TextView mNewVersionTV;
    private TextView mExit;

    private StationListPresenter stationListPresenter;

    private void initTitle() {
        ((ImageView) this.mView.findViewById(R.id.image_title_back)).setVisibility(View.GONE);
        final TextView titleTextView = (TextView) this.mView.findViewById(R.id.tv_title);
        titleTextView.setText(getResources().getString(R.string.main_viewpager_mine));
    }

    private void initView() {
        this.mNameTv = (TextView) this.mView.findViewById(R.id.tv_name);
        this.mUserInfoLL = (LinearLayout) this.mView.findViewById(R.id.ll_user_info);
        this.mUserInfoTv = (TextView) this.mView.findViewById(R.id.tv_user_info);
        this.mChangePasswordLL = (LinearLayout) this.mView.findViewById(R.id.ll_authentication);
        this.mChangePasswordTv = (TextView) this.mView.findViewById(R.id.tv_authentication);
        this.mAppVersionLL = (LinearLayout) this.mView.findViewById(R.id.ll_current_version);
        this.mAppVersionTV = (TextView) this.mView.findViewById(R.id.tv_current_version);
        this.mNewVersionTV = (TextView) this.mView. findViewById(R.id.tv_new_version);
        this.mExit = (TextView) this.mView.findViewById(R.id.tv_logout);


        String userName = UserSP.getUserName();
        this.mNameTv.setText("用户名：" + userName);
        this.mAppVersionTV.setText("当前版本：V" + BuildConfig.VERSION_NAME);
        if (hasNewVersion) {
            this.mNewVersionTV.setVisibility(View.VISIBLE);
        } else {
            this.mNewVersionTV.setVisibility(View.INVISIBLE);
        }


        this.stationListPresenter = new StationListPresenter(this);
    }

    private void addListener() {
        this.mUserInfoLL.setOnClickListener((view) -> {
            Intent intent = new Intent(getContext(), UserInfoActivity.class);
            startActivity(intent);
        });


        this.mChangePasswordLL.setOnClickListener((view) -> {
            Intent intent = new Intent(getContext(), ResetPasswordActivity.class);
            startActivity(intent);
        });

        this.mAppVersionLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasNewVersion  && mModel != null) {
                    // 更新APP
                    Intent intent = new Intent();
                    intent.setClass(Objects.requireNonNull(getContext()), DialogConfirmActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //在 Service 或者 ApplicationContext中startActivity时要添加额外标志ntent.FLAG_ACTIVITY_NEW_TASK
                    intent.putExtra("isFirstCheck", false);
                    intent.putExtra("model", (Parcelable) mModel);

                    startActivity(intent);
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

    @Subscribe
    public void updateVersion(UpdateVersionEvent updateVersionEvent) {
        hasNewVersion = true;
        mModel = updateVersionEvent.getVersionInfo();

        this.mNewVersionTV.setVisibility(View.VISIBLE);
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

    @Override
    public void setPresenter(StationListPresenter paramT) {

    }

    public void showLoading() {
    }

    public void hideLoading() {
    }

    public void showFail(String paramString) {
        ToastUtils.shortToast(paramString);
    }

    public void showCurrentStation(GetDefaultStationResponseModel model) {
    }

    public void showChangeStationSuccess(String paramString) {
    }

    public void showStationListAll(List<GetStationListAllResponseModel> paramList) {
    }

}