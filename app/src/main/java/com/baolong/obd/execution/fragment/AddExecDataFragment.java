package com.baolong.obd.execution.fragment;

import com.baolong.obd.R;
import com.baolong.obd.common.file.uploadFile.NewContractPhotoModel;
import com.baolong.obd.execution.data.entity.SearchMonitoringDataResponseModel;
import com.baolong.obd.execution.event.UpdateExecListEvent;
import com.baolong.obd.execution.presenter.ImageUtils;
import com.baolong.obd.my.photos.GifSizeFilter;
import com.baolong.obd.my.photos.Glide4Engine;
import com.hwangjr.rxbus.RxBus;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import android.support.annotation.NonNull;

import com.baolong.obd.execution.data.entity.GetUploadImgResponseModel;

import java.io.File;

import android.content.Intent;

import com.baolong.obd.common.utils.ToastUtils;

import android.text.TextUtils;

import com.tbruyelle.rxpermissions2.RxPermissions;

//import com.yanzhenjie.album.Album;
//import com.yanzhenjie.album.api.ImageSingleWrapper;
//import com.yanzhenjie.album.AlbumFile;
//import com.yanzhenjie.album.Action;

import com.baolong.obd.common.base.BaseActivity;

import android.text.method.KeyListener;
import android.view.LayoutInflater;

import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.common.utils.DateUtils;

import java.util.Date;

import com.baolong.obd.common.sharepreferemces.UserSP;

import java.util.Locale;

import com.baolong.obd.component.media.AppImageDisplay;

import android.view.ViewGroup;
import android.content.Context;

import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import com.baolong.obd.execution.presenter.AddExecPresenter;
import com.baolong.obd.component.imagereview.RemarkImage;

import java.util.List;

import com.baolong.obd.component.attachment.AttachmentTypeEntity;

import java.util.ArrayList;

import com.baolong.obd.execution.contract.AddExecContract;

import android.widget.Toast;

import com.baolong.obd.common.base.BaseFragment;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;

public class AddExecDataFragment extends BaseFragment
        implements ViewPager.OnPageChangeListener, AddExecContract.View {
    private static final String TAG = AddExecDataFragment.class.getSimpleName();
    private static final int REQUEST_CODE_CHOOSE = 23;

    private AttachmentTypeEntity entity;
    private List<RemarkImage> images;
    private AddExecPresenter mAddExecPresenter;
    private List<View> mAttachmentFiles;
    private TextView mAttachmentTxt;
    private EditText mBtgdEt;
    private EditText mCoEt;
    private EditText mCoTwoEt;
    private Button mCommitBtn;
    private EditText mDoPersonEt;
    private EditText mDoPlaceEt;
    private EditText mDoTimeEt;
    private boolean mEdit;
    private LinearLayout mFileContainer;
    private ImageView mFirstImage;
    private EditText mHcEt;
    private LinearLayout mImageContainer;
    private TextView mImageNum;
    private EditText mNoEt;
    private EditText mRemarkEt;
    private String mTestNo;
    private android.view.View mView;
    private EditText mYdEt;

    // 增加View 用来显示Image文件的名称
    private View createAttachmentView(final String s, final String s2) {
        final android.view.View inflate = android.view.View.inflate((Context) this.getActivity(), R.layout.item_attachment, (ViewGroup) null);
        inflate.setTag((Object) s);
        final TextView textView = (TextView) inflate.findViewById(R.id.tv_name);
        final ImageView imageView = (ImageView) inflate.findViewById(R.id.ib_action);
        textView.setText((CharSequence) s);
        // 预览图片
//        textView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Intent intent = new Intent((Context) getActivity(), (Class) GalleryActivity.class);
//                final ArrayList list = new ArrayList();
//                list.addAll(images);
//                intent.putParcelableArrayListExtra("path_list", list);
//                final String s = (String) v.getTag();
//                final int size = mAttachmentFiles.size();
//                final boolean b = false;
//                int n = 0;
//                int n2;
//                while (true) {
//                    n2 = (b ? 1 : 0);
//                    if (n >= size) {
//                        break;
//                    }
//                    if (s.equals(mAttachmentFiles.get(n).getTag())) {
//                        n2 = n;
//                        break;
//                    }
//                    ++n;
//                }
//                intent.putExtra("current_item", n2);
//                startActivity(intent);
//            }
//        });
        // 删除图片
        imageView.setOnClickListener((View.OnClickListener) new View.OnClickListener() {
            public void onClick(final android.view.View view) {
                final String s = (String) inflate.getTag();
                final int size = AddExecDataFragment.this.mAttachmentFiles.size();
                int i = 0;
                while (i < size) {
                    if (s.equals(((android.view.View) AddExecDataFragment.this.mAttachmentFiles.get(i)).getTag())) {
                        AddExecDataFragment.this.mAttachmentFiles.remove(i);
                        AddExecDataFragment.this.mFileContainer.removeViewAt(i);
                        AddExecDataFragment.this.images.remove(i);
                        if (AddExecDataFragment.this.images.size() > 0) {
                            AppImageDisplay.showImg("blzxjcpt/", AddExecDataFragment.this.images.get(AddExecDataFragment.this.images.size() - 1).getImageAddress(), AddExecDataFragment.this.getContext(), R.drawable.img_monitor_pic, AddExecDataFragment.this.mFirstImage);
                            AddExecDataFragment.this.mImageNum.setText((CharSequence) String.format(Locale.ENGLISH, "(%d)", AddExecDataFragment.this.mFileContainer.getChildCount()));
                            return;
                        }
                        AddExecDataFragment.this.mFileContainer.setVisibility(View.GONE);
                        AddExecDataFragment.this.mImageContainer.setVisibility(View.GONE);
                    } else {
                        ++i;
                    }
                }
            }
        });
        if (!this.mEdit) {
            imageView.setVisibility(View.GONE);
        }
        return inflate;
    }

    private void initView(final LayoutInflater layoutInflater) {
        this.mView = layoutInflater.inflate(R.layout.fragment_add_exec_data, (ViewGroup) null);
        this.mAttachmentTxt = (TextView) this.mView.findViewById(R.id.txt_add_attachment);
        this.mImageContainer = (LinearLayout) this.mView.findViewById(R.id.ly_image_attach);
        this.mFirstImage = (ImageView) this.mView.findViewById(R.id.iv_image);
        this.mImageNum = (TextView) this.mView.findViewById(R.id.tv_image_attach_amount);
        this.mFileContainer = (LinearLayout) this.mView.findViewById(R.id.ll_file_container);
        this.mCommitBtn = (Button) this.mView.findViewById(R.id.btn_commit);

        this.mDoPersonEt = (EditText) this.mView.findViewById(R.id.et_do_person);
        this.mDoTimeEt = (EditText) this.mView.findViewById(R.id.et_do_time);
        this.mDoPlaceEt = (EditText) this.mView.findViewById(R.id.et_do_place);
        this.mCoEt = (EditText) this.mView.findViewById(R.id.et_co);
        this.mCoTwoEt = (EditText) this.mView.findViewById(R.id.et_co_two);
        this.mHcEt = (EditText) this.mView.findViewById(R.id.et_hc);
        this.mNoEt = (EditText) this.mView.findViewById(R.id.et_no);
        this.mYdEt = (EditText) this.mView.findViewById(R.id.et_yd);
        this.mBtgdEt = (EditText) this.mView.findViewById(R.id.et_btgd);
        this.mRemarkEt = (EditText) this.mView.findViewById(R.id.et_remark);
        this.mDoPersonEt.setKeyListener((KeyListener) null);
        this.mDoTimeEt.setKeyListener((KeyListener) null);
        if (!this.mEdit) {
            this.mDoPlaceEt.setKeyListener((KeyListener) null);
            this.mCoEt.setKeyListener((KeyListener) null);
            this.mCoTwoEt.setKeyListener((KeyListener) null);
            this.mNoEt.setKeyListener((KeyListener) null);
            this.mHcEt.setKeyListener((KeyListener) null);
            this.mYdEt.setKeyListener((KeyListener) null);
            this.mBtgdEt.setKeyListener((KeyListener) null);
            this.mRemarkEt.setKeyListener((KeyListener) null);
            this.mAttachmentTxt.setVisibility(View.GONE);
            this.mCommitBtn.setVisibility(View.GONE);
        }
    }

    private void initData() {
        this.mAttachmentFiles = new ArrayList<android.view.View>();
        this.mAddExecPresenter = new AddExecPresenter(this);
        this.mDoPersonEt.setText((CharSequence) UserSP.getUserName());
        this.mDoTimeEt.setText((CharSequence) DateUtils.dateTimeToString(new Date()));
        if (!this.mEdit) {
            this.mAddExecPresenter.queryMonitoringData(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, this.mTestNo);
        }
    }

    private void addListener() {
        // 点击 添加附件
        this.mAttachmentTxt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((BaseActivity) getActivity()) != null && ((BaseActivity) getActivity()).ExistSDCard()) {
                    RxPermissions rxPermissions = new RxPermissions(getActivity());
                    rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe((Observer<Boolean>) new Observer<Boolean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Boolean aBoolean) {
                                    if (aBoolean) {
                                        Matisse.from((Fragment) AddExecDataFragment.this)
                                                .choose(MimeType.ofAll(), false)
                                                .countable(true)
                                                .capture(true)
                                                .captureStrategy(new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider", "test"))
                                                .maxSelectable(1)
                                                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.x240))
                                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                                .thumbnailScale(0.85f)
                                                //.imageEngine(new GlideEngine())  // for glide-V3
                                                .imageEngine(new Glide4Engine())    // for glide-V4
                                                .setOnSelectedListener(new OnSelectedListener() {
                                                    @Override
                                                    public void onSelected(@NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                                                        // DO SOMETHING IMMEDIATELY HERE
                                                        Log.e("onSelected", "onSelected: pathList=" + pathList);

                                                    }
                                                })
                                                .originalEnable(true)
                                                .maxOriginalSize(10)
                                                .autoHideToolbarOnSingleTap(true)
                                                .setOnCheckedListener(new OnCheckedListener() {
                                                    @Override
                                                    public void onCheck(boolean isChecked) {
                                                        // DO SOMETHING IMMEDIATELY HERE
                                                        Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                                                    }
                                                })
                                                .forResult(REQUEST_CODE_CHOOSE);
                                    } else {
                                        Toast.makeText(getActivity(), R.string.permission_request_denied, Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }
            }
        });
        // 点击 提交
        this.mCommitBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String s;
                if (TextUtils.isEmpty(mDoPlaceEt.getText())) {
                    s = "处理地点不能为空，请输入处理地点!";
                } else if (TextUtils.isEmpty(mCoEt.getText())
                        && TextUtils.isEmpty(mCoTwoEt.getText())
                        && TextUtils.isEmpty(mNoEt.getText())
                        && TextUtils.isEmpty(mYdEt.getText())
                        && TextUtils.isEmpty(mBtgdEt.getText())
                        && TextUtils.isEmpty(mHcEt.getText())) {
                    s = "CO、CO2、HC、NO、烟度、不透明度6项不能全为空，请输入至少输入任意一项!";
                } else {
                    if (!TextUtils.isEmpty(mRemarkEt.getText())) {
                        String allImageId = "";
                        String allImageTargetType = "";
                        int i = 0;
                        while (i < images.size()) {
                            final StringBuilder sb = new StringBuilder();
                            sb.append(allImageId);
                            sb.append(",");
                            sb.append(images.get(i).getImageId());
                            allImageId = sb.toString();

                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append(allImageTargetType);
                            sb2.append(",");
                            sb2.append(images.get(i).getTargetType());
                            allImageTargetType = sb2.toString();
                            ++i;
                        }
                        // 调用接口服务，提交处理
                        mAddExecPresenter.insertMonitoringData(UserSP.getUserToken()
                                , RetrofitManager.mUseName
                                , RetrofitManager.mAppId
                                , UserSP.getUserName()
                                , mTestNo
                                , mDoPersonEt.getText().toString()
                                , mDoTimeEt.getText().toString()
                                , mDoPlaceEt.getText().toString()
                                , "1"
                                , mRemarkEt.getText().toString()
                                , allImageId
                                , allImageTargetType
                                , mCoEt.getText().toString()
                                , mCoTwoEt.getText().toString()
                                , mNoEt.getText().toString()
                                , mYdEt.getText().toString()
                                , mBtgdEt.getText().toString()
                                , mHcEt.getText().toString());
                        return;
                    }
                    s = "处理说明不能为空，请输入处理说明!";
                }
                ToastUtils.longToast(s);
            }
        });
    }

    // 从图片选取界面后返回
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<String> filePath = Matisse.obtainPathResult(data);

            final File file;
            if (filePath != null && filePath.size() > 0) {
                file = new File(filePath.get(0));       // /storage/emulated/0/Images/11.jpg
                if (file.exists()) {
                    this.mAddExecPresenter.addAttachment(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, ImageUtils.bitmapToString(file.getPath()), file.getName(), file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(".") + 1), UserSP.getUserName(), file.getAbsolutePath());
                }
            }
        }
    }

    @Override
    public void setArguments(final Bundle arguments) {
        super.setArguments(arguments);
        this.mTestNo = arguments.getString("testNo");
        this.mEdit = arguments.getBoolean("edit", false);
    }

    public AddExecDataFragment() {
        this.mAttachmentFiles = new ArrayList<android.view.View>();
        this.images = new ArrayList<RemarkImage>();
        this.entity = new AttachmentTypeEntity();
    }

    public static AddExecDataFragment newInstance() {
        return new AddExecDataFragment();
    }

    @Nullable
    public View onCreateView(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final Bundle bundle) {
        RxBus.get().register((Object) this);
        this.initView(layoutInflater);
        this.initData();
        this.addListener();
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
    public void onPageScrollStateChanged(final int n) {
    }

    public void onPageScrolled(final int n, final float n2, final int n3) {
    }

    public void onPageSelected(final int n) {
    }

    /**
     * 网络数据请求回调 -- 通用
     */
    public void setPresenter(final AddExecPresenter addExecPresenter) {
    }

    public void showLoading() {
        ((BaseActivity) this.getActivity()).showLoading(this.mView);
    }

    public void hideLoading() {
        ((BaseActivity) this.getActivity()).hideLoading();
    }

    public void showFail(final String s) {
        ToastUtils.shortToast(s);
    }

    /**
     * 网络数据请求回调 -- 文字数据
     */
    public void showInsertMonitorDataSuccess(final String s) {
        this.getActivity().finish();
        RxBus.get().post((Object) new UpdateExecListEvent(s));
    }

    /**
     * 网络数据请求回调 -- 文件数据
     */
    public void fileUploadFail(final String s, final String s2) {
    }

    public void fileUploadProgress(final File file, final int n, final String s) {
    }

    public void fileUploadSuccess(final GetUploadImgResponseModel getUploadImgResponseModel) {
    }

    /**
     * 网络数据请求回调 -- 图片数据
     */
    public void imageUploadFail(final String s) {
    }

    public void imageUploadProgress(final File file, final int n) {
    }

    public void imageUploadSuccess(final GetUploadImgResponseModel getUploadImgResponseModel) {
        this.mFileContainer.setVisibility(View.VISIBLE);
        final View attachmentView = this.createAttachmentView(getUploadImgResponseModel.getFileClientName(), getUploadImgResponseModel.getFileClientPath());
        this.mFileContainer.addView(attachmentView);
        this.mAttachmentFiles.add(attachmentView);
        this.mImageContainer.setVisibility(View.VISIBLE);
        AppImageDisplay.showImg("blzxjcpt/", getUploadImgResponseModel.getFilePath(), this.getContext(), R.drawable.img_monitor_pic, this.mFirstImage);
        this.mImageNum.setText((CharSequence) String.format(Locale.ENGLISH, "(%d)", this.mFileContainer.getChildCount()));

        final RemarkImage remarkImage = new RemarkImage();
        remarkImage.setImageId(getUploadImgResponseModel.getFileId());
        remarkImage.setTargetType(getUploadImgResponseModel.getFilePath());
        final StringBuilder urlSB = new StringBuilder();
        urlSB.append(RetrofitManager.BASE_URL);
        urlSB.append("blzxjcpt/");
        urlSB.append(getUploadImgResponseModel.getFilePath());
        remarkImage.setImageAddress(urlSB.toString());
        this.images.add(remarkImage);
    }

    /**
     * 网络数据请求回调 -- 视频数据
     */
    public void videoUploadFail(final String s) {
    }

    public void videoUploadProgress(final File file, final int n) {
    }

    public void videoUploadSuccess(final List<NewContractPhotoModel> list) {
    }

    /**
     * 网络数据请求回调 -- 查询数据
     */
    public void showQueryData(final SearchMonitoringDataResponseModel searchMonitoringDataResponseModel) {
        this.mDoPersonEt.setText(searchMonitoringDataResponseModel.getProcessingPerson());
        this.mDoTimeEt.setText(searchMonitoringDataResponseModel.getProcessingDate());
        this.mDoPlaceEt.setText(searchMonitoringDataResponseModel.getDisposalSiteDetail());
        this.mCoEt.setText(searchMonitoringDataResponseModel.getCo());
        this.mCoTwoEt.setText(searchMonitoringDataResponseModel.getCo2());
        this.mHcEt.setText(searchMonitoringDataResponseModel.getHc());
        this.mNoEt.setText(searchMonitoringDataResponseModel.getNo());
        this.mYdEt.setText(searchMonitoringDataResponseModel.getYd());
        this.mBtgdEt.setText(searchMonitoringDataResponseModel.getBtgd());
        this.mRemarkEt.setText(searchMonitoringDataResponseModel.getDisposalExplain());
        String s2;
        final String s = s2 = searchMonitoringDataResponseModel.getTp();
        if (s.startsWith(",")) {
            s2 = s.substring(1);
        }
        final String[] split = s2.split(",");
        for (int i = 0; i < split.length; ++i) {
            this.mFileContainer.setVisibility(View.VISIBLE);
            final String substring = split[i].substring(split[i].lastIndexOf("/"));
            final View attachmentView = this.createAttachmentView(substring, split[i]);
            this.mFileContainer.addView(attachmentView);
            this.mAttachmentFiles.add(attachmentView);

            this.mImageContainer.setVisibility(View.VISIBLE);
            AppImageDisplay.showImg("blzxjcpt/", split[i], this.getContext(), R.drawable.img_monitor_pic, this.mFirstImage);
            this.mImageNum.setText((CharSequence) String.format(Locale.ENGLISH, "(%d)", this.mFileContainer.getChildCount()));

            final RemarkImage remarkImage = new RemarkImage();
            remarkImage.setImageId(substring);
            remarkImage.setTargetType(split[i]);
            final StringBuilder urlSB = new StringBuilder();
            urlSB.append(RetrofitManager.BASE_URL);
            urlSB.append("blzxjcpt/");
            urlSB.append(split[i]);
            remarkImage.setImageAddress(urlSB.toString());
            this.images.add(remarkImage);
        }
    }

    @Override
    public void refresh() {
    }

}

