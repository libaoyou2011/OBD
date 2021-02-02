package com.baolong.obd.execution.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.baolong.obd.R;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.execution.contract.AddExecContract;
import com.hwangjr.rxbus.RxBus;
import com.baolong.obd.common.base.BaseFragment;
import com.baolong.obd.common.file.uploadFile.NewContractPhotoModel;
import com.baolong.obd.common.sharepreferemces.UserSP;
import com.baolong.obd.common.utils.DateUtils;
import com.baolong.obd.execution.data.entity.GetUploadImgResponseModel;
import com.baolong.obd.execution.data.entity.SearchMonitoringDataResponseModel;
import com.baolong.obd.execution.event.UpdateExecListEvent;
import com.baolong.obd.execution.presenter.AddExecPresenter;

import java.io.File;
import java.util.Date;
import java.util.List;

public class CancelExecDataFragment extends BaseFragment
        implements ViewPager.OnPageChangeListener, AddExecContract.View {
    private String mTestNo;
    private boolean mEdit;
    private AddExecPresenter mAddExecPresenter;

    private View mView;
    private EditText mDoPersonEt;
    private EditText mDoTimeEt;
    private EditText mRemarkEt;
    private Button mCommitBtn;

    private void initView(LayoutInflater paramLayoutInflater) {
        this.mView = paramLayoutInflater.inflate(R.layout.fragment_cancel_exec_data, null);
        this.mDoPersonEt = (EditText) this.mView.findViewById(R.id.et_do_person);
        this.mDoTimeEt = (EditText) this.mView.findViewById(R.id.et_do_time);
        this.mRemarkEt = (EditText) this.mView.findViewById(R.id.et_remark);
        this.mCommitBtn = (Button) this.mView.findViewById(R.id.btn_commit);
        this.mDoPersonEt.setKeyListener(null);
        this.mDoTimeEt.setKeyListener(null);
    }

    private void initData() {
        this.mAddExecPresenter = new AddExecPresenter(this);
        this.mDoPersonEt.setText(UserSP.getUserName());
        this.mDoTimeEt.setText(DateUtils.dateTimeToString(new Date()));
    }

    private void addListener() {
        this.mCommitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mRemarkEt.getText())) {
                    ToastUtils.longToast("取消处理说明不能为空，请输入取消处理说明!");
                    return;
                }
                // 调用接口服务，提交取消处理
                mAddExecPresenter.insertMonitoringData(UserSP.getUserToken()
                        , RetrofitManager.mUseName
                        , RetrofitManager.mAppId
                        , UserSP.getUserName()
                        , mTestNo
                        , mDoPersonEt.getText().toString()
                        , mDoTimeEt.getText().toString()
                        , ""
                        , "0"
                        , mRemarkEt.getText().toString()
                        , "", "", "", "", "", "", "", "");
            }
        });
    }

    public static CancelExecDataFragment newInstance() {
        return new CancelExecDataFragment();
    }

    @Override
    public void setArguments(Bundle paramBundle) {
        super.setArguments(paramBundle);
        this.mTestNo = paramBundle.getString("testNo");
        this.mEdit = paramBundle.getBoolean("edit", false);
    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, Bundle paramBundle) {
        RxBus.get().register(this);
        initView(paramLayoutInflater);
        initData();
        addListener();
        return this.mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
    }

    /**
    * ViewPager.OnPageChangeListener
    */
    public void onPageScrollStateChanged(int paramInt) {
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {
    }

    public void onPageSelected(int paramInt) {
    }

    /**
     * 网络数据请求回调 -- 通用
     */
    public void setPresenter(AddExecPresenter paramAddExecPresenter) {
    }

    public void showLoading() {
    }

    public void hideLoading() {
    }

    public void showFail(String paramString) {
    }

    /**
     * 网络数据请求回调 -- 文字数据
     */
    public void showInsertMonitorDataSuccess(String paramString) {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
        RxBus.get().post(new UpdateExecListEvent(paramString));
    }

    /**
     * 网络数据请求回调 -- 文件数据
     */
    public void fileUploadFail(String paramString1, String paramString2) {
    }

    public void fileUploadProgress(File paramFile, int paramInt, String paramString) {
    }

    public void fileUploadSuccess(GetUploadImgResponseModel paramGetUploadImgResponseModel) {
    }

    /**
     * 网络数据请求回调 -- 图片数据
     */
    public void imageUploadFail(String paramString) {
    }

    public void imageUploadProgress(File paramFile, int paramInt) {
    }

    public void imageUploadSuccess(GetUploadImgResponseModel paramGetUploadImgResponseModel) {
    }

    /**
     * 网络数据请求回调 -- 视频数据
     */
    public void videoUploadFail(String paramString) {
    }

    public void videoUploadProgress(File paramFile, int paramInt) {
    }

    public void videoUploadSuccess(List<NewContractPhotoModel> paramList) {
    }

    /**
     * 网络数据请求回调 -- 查询数据
     */
    public void showQueryData(SearchMonitoringDataResponseModel paramSearchMonitoringDataResponseModel) {
    }

    public void refresh() {
    }
}
