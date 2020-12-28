package com.baolong.obd.blackcar.activity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.blackcar.event.RefreshTelemetryListByFilter;
import com.baolong.obd.blackcar.adapter.LeftProfessionAdapter;
import com.baolong.obd.blackcar.adapter.RightProfessionAdapter;
import com.baolong.obd.blackcar.contract.FilterActivityContract;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.blackcar.event.RefreshBlackCarListByFilter;
import com.baolong.obd.blackcar.event.RefreshExecutionCarListByFilter;
import com.baolong.obd.blackcar.presenter.FilterListPresenter;
import com.baolong.obd.common.utils.FileUtils;
import com.baolong.obd.common.utils.ListSaveUtils;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.common.utils.ToastUtils;
import com.baolong.obd.common.view.listview.PinnedHeaderListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hwangjr.rxbus.RxBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BlackCarFilterActivity extends PopupWindow implements FilterActivityContract.View {
    private static final String TAG = BlackCarFilterActivity.class.getSimpleName();
    private List<FilterCategoryModel> filterCategoryModelList = new ArrayList<>();
    private boolean isChange = false;
    private boolean isScroll = true;
    private final Context mContext;
    private ListView mLeft;
    private LeftProfessionAdapter mLeftProfessionAdapter;
    private PinnedHeaderListView mRight;
    private RightProfessionAdapter mRightProfessionAdapter;
    private final View mRootView;
    private FilterListPresenter stationListPresenter;

    private final String mModuleType;

    private String filterFileName;  //过滤文件存储名
    private static final String BlackFilterFileName = "blackFilter.txt";
    private static final String ExecutionFilterFileName = "executionFilter.txt";
    private static final String TelemetryFilterFileName = "telemetryFilter.txt";

    public BlackCarFilterActivity(Context context, View view, int height, String mModuleType) {
        super(-1, -1);
        this.setContentView(view);
        this.mModuleType = mModuleType;

        if ("black".equals(this.mModuleType)) {
            filterFileName = BlackFilterFileName;
        } else if ("execution".equals(this.mModuleType)) {
            filterFileName = ExecutionFilterFileName;
        } else if ("telemetry".equals(this.mModuleType)) {
            filterFileName = TelemetryFilterFileName;
        }
        LogUtil.i(TAG, filterFileName);
        this.mRootView = view;
        this.mContext = context;
        this.initAnimation();
        this.initView();
        this.initAdapter();
        this.initPresenter();
        this.getData();
    }

    @Override
    public void showAsDropDown(final View anchor) {
        LogUtil.i(TAG, "Android：SDK = " + Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Rect rect = new Rect();
            // 以屏幕 左上角 为参考系的
            anchor.getGlobalVisibleRect(rect);
            //屏幕高度减去 anchor 的 bottom
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            LogUtil.i(TAG, "PopupWindow：height = " + String.valueOf(h));
            // 重新设置PopupWindow高度
            setHeight(h);
        }
        super.showAsDropDown(anchor);
    }

    private void initAnimation() {
        setAnimationStyle(R.style.style_pop_animation);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_pop_transparent));
    }

    private void initView() {
        this.mLeft = ((ListView) this.mRootView.findViewById(R.id.left));
        this.mRight = ((PinnedHeaderListView) this.mRootView.findViewById(R.id.right));
        TextView clearAllTv = ((TextView) this.mRootView.findViewById(R.id.tv_clear_all));
        TextView confirmTv = ((TextView) this.mRootView.findViewById(R.id.tv_confirm));

        clearAllTv.setOnClickListener((v) -> {
            for (int i = 0; i < this.filterCategoryModelList.size(); ++i) {
                this.filterCategoryModelList.get(i).setSelectedCurrentCategory(false);
                this.filterCategoryModelList.get(i).setInputValue("");
                if (this.filterCategoryModelList.get(i).isCanInputTime()) {
                    this.filterCategoryModelList.get(i).setBeginTime("");
                    this.filterCategoryModelList.get(i).setEndTime("");
                }
                for (int j = 0; j < this.filterCategoryModelList.get(i).getSubList().size(); j++) {
                    ((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(j)).setSelected(false);
                }
            }
            this.filterCategoryModelList.get(0).setSelectedCurrentCategory(true);
            this.mRightProfessionAdapter.setDataList(this.filterCategoryModelList);
            this.mLeftProfessionAdapter.setDataList(this.filterCategoryModelList);

            //清除过滤文件
            File file = new File(FileUtils.getCacheDir(BlackCarFilterActivity.this.mContext), filterFileName);
            LogUtil.i(TAG, "清除过滤文件: " + file.getPath());
            ListSaveUtils.writeList2Storage(this.filterCategoryModelList, file);

            this.isChange = true;
        });

        confirmTv.setOnClickListener((view) -> {
            String tips = "";   // Toast提示内容
            boolean canShowToast = false; // 判断是否弹出提示，初始化为1
            for (int i = 0; i < this.filterCategoryModelList.size(); i++) {
                // 可输入值
                if (this.filterCategoryModelList.get(i).isCanInput() && !canShowToast) {
                    for (int k = 0; k < this.filterCategoryModelList.get(i).getSubList().size(); k++) {
                        if (((FilterCategoryModel.FilterSubCategoryModel) this.filterCategoryModelList.get(i).getSubList().get(k)).isSelected()
                                && TextUtils.isEmpty((CharSequence) this.filterCategoryModelList.get(i).getInputValue())) {
                            tips = this.filterCategoryModelList.get(i).getCategoryName() + "筛选值不能为空";
                            canShowToast = true;
                            break;
                        }
                    }
                } else if (canShowToast) {
                    break;
                }
            }
            // 选定大于小于等于，未选择值则弹出提示
            if (canShowToast) {
                ToastUtils.shortToast(tips);
                return;
            }
            // 筛选改变，则保存
            if (this.isChange) {
                // 保存过滤文件
                File file = new File(FileUtils.getCacheDir(BlackCarFilterActivity.this.mContext), filterFileName);
                if (file == null) {
                    return;
                }
                LogUtil.i(TAG, "保存过滤文件: " + file.getPath());
                ListSaveUtils.writeList2Storage(this.filterCategoryModelList, file);
            }
            // 刷新过滤列表
            if ("black".equals(this.mModuleType)) {
                // 更新黑烟车列表
                LogUtil.i(TAG, "发送Post，更新黑烟车模块列表: ");
                RxBus.get().post((Object) new RefreshBlackCarListByFilter(this.filterCategoryModelList));
            } else if ("execution".equals(this.mModuleType)) {
                // 更新超标车列表
                LogUtil.i(TAG, "发送Post，更新超标车模块列表: ");
                RxBus.get().post((Object) new RefreshExecutionCarListByFilter(this.filterCategoryModelList));
            } else if ("telemetry".equals(this.mModuleType)) {
                // 更新遥测列表
                LogUtil.i(TAG, "发送Post，更新遥测模块列表: ");
                RxBus.get().post((Object) new RefreshTelemetryListByFilter(this.filterCategoryModelList));
            }
            this.dismiss();
        });

    }

    private void initAdapter() {
        this.mRightProfessionAdapter = new RightProfessionAdapter(this.mContext, this.filterCategoryModelList);
        this.mRightProfessionAdapter.setSubItemChange(new RightProfessionAdapter.SubItemChange() {
            public void onItemChange() {
                BlackCarFilterActivity.this.isChange = true;
            }
        });
        this.mRight.setAdapter(this.mRightProfessionAdapter);

        this.mLeftProfessionAdapter = new LeftProfessionAdapter(this.mContext, this.filterCategoryModelList);
        this.mLeft.setAdapter(this.mLeftProfessionAdapter);

//        this.mLeft.setOnItemClickListener(new -..
//        Lambda.BlackCarFilterActivity.lbpWp02AVvdIbJ7nyMOT3V7sfEw(this));
        // 1.正常示例
//        this.mLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //Do something
//            }
//        });
        // 2.Lambda示例
//        this.mLeft.setOnItemClickListener(((parent, view, position, id) -> {
//            //Do something
//            //注意：匿名类的 this 关键字指向匿名类，而lambda表达式的 this 关键字指向包围lambda表达式的类;
//        }));
        // 3.本地修改后
        // 左侧一级菜单对应的点击事件
        this.mLeft.setOnItemClickListener(((parent, view, position, id) -> {
            int selection = 0;
            BlackCarFilterActivity.this.isScroll = false;
            int i = 0;
            while (true) {
                final int size = BlackCarFilterActivity.this.filterCategoryModelList.size();
                boolean currentCategory = true;
                if (i >= size) {
                    break;
                }
                final FilterCategoryModel filterCategoryModel = BlackCarFilterActivity.this.filterCategoryModelList.get(i);
                if (i != position) {
                    currentCategory = false;
                }
                filterCategoryModel.setSelectedCurrentCategory(currentCategory);
                i++;
            }
            BlackCarFilterActivity.this.mLeftProfessionAdapter.notifyDataSetChanged();

            for (int j = 0; j < position; j++) {
                selection += BlackCarFilterActivity.this.mRightProfessionAdapter.getCountForSection(j) + 1;
            }
            BlackCarFilterActivity.this.mRight.setSelection(selection);
        }));

        this.mRight.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int x = 0;  // 左侧选定项
            private int y = 0;  // 右侧第一条可见Item
            private int z = 0;

            // 在滑动屏幕的过程中，onScroll方法会一直调用
            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //LogUtil.i(TAG, "onScroll:" + "firstVisibleItem:" + firstVisibleItem + " visibleItemCount:" + visibleItemCount + " totalItemCount:" + totalItemCount);
                if (BlackCarFilterActivity.this.isScroll) {
                    for (int i = 0; i < BlackCarFilterActivity.this.filterCategoryModelList.size(); i++) {
                        if (i == BlackCarFilterActivity.this.mRightProfessionAdapter.getSectionForPosition(BlackCarFilterActivity.this.mRight.getFirstVisiblePosition())) {
                            ((FilterCategoryModel) BlackCarFilterActivity.this.filterCategoryModelList.get(i)).setSelectedCurrentCategory(true);
                            this.y = i;
                        } else {
                            ((FilterCategoryModel) BlackCarFilterActivity.this.filterCategoryModelList.get(i)).setSelectedCurrentCategory(false);
                        }
                    }
                    if (this.x != this.y) {
                        LogUtil.i(TAG, "onScroll:" + "x:" + x + " y:" + y);

                        BlackCarFilterActivity.this.mLeftProfessionAdapter.notifyDataSetChanged();
                        this.x = this.y;
                        if (this.x == BlackCarFilterActivity.this.mLeft.getFirstVisiblePosition()) {
                            //LogUtil.i(TAG, "onScroll:" + "x = mLeft.getFirstVisiblePosition = " + x);
                            BlackCarFilterActivity.this.mLeft.setSelection(this.z);
                        }
                        if (this.x == BlackCarFilterActivity.this.mLeft.getLastVisiblePosition()) {
                            //LogUtil.i(TAG, "onScroll:" + "x = mLeft.getLastVisiblePosition = " + x);
                            BlackCarFilterActivity.this.mLeft.setSelection(BlackCarFilterActivity.this.mLeft.getAdapter().getCount() - 1);
                        }
                        if (firstVisibleItem + visibleItemCount == totalItemCount - 1) {
                            //LogUtil.i(TAG, "onScroll:" + "firstVisibleItem + visibleItemCount == totalItemCount - 1");
                            BlackCarFilterActivity.this.mLeft.setSelection(BlackCarFilterActivity.this.mLeft.getAdapter().getCount() - 1);
                        }
                    }
                } else {
                    BlackCarFilterActivity.this.isScroll = true;
                }
            }

            // 滑动状态改变时调用
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                // scrollState有三种状态：
                // SCROLL_STATE_TOUCH_SCROLL：开始滚动的时候调用，调用一次
                // SCROLL_STATE_IDLE：滚动事件结束的时候调用，调用一次
                // SCROLL_STATE_FLING：当手指离开屏幕，并且产生惯性滑动的时候调用，可能会调用<=1次
                if (scrollState != SCROLL_STATE_IDLE) {
                    return;
                }
                //右侧列表最后一个显示时
                if (BlackCarFilterActivity.this.mRight.getLastVisiblePosition() == BlackCarFilterActivity.this.mRight.getCount() - 1) {
                    BlackCarFilterActivity.this.mLeft.setSelection(BlackCarFilterActivity.this.mLeft.getAdapter().getCount() - 1);
                    for (int i = 0; i < BlackCarFilterActivity.this.filterCategoryModelList.size(); i++) {
                        boolean bool;
                        if (i == BlackCarFilterActivity.this.mLeft.getAdapter().getCount() - 1) {
                            bool = true;
                        } else {
                            bool = false;
                        }
                        FilterCategoryModel tempFilterCategoryModel = (FilterCategoryModel) BlackCarFilterActivity.this.filterCategoryModelList.get(i);
                        tempFilterCategoryModel.setSelectedCurrentCategory(bool);
                    }
                }
                //右侧列表第一个显示时
                if (BlackCarFilterActivity.this.mRight.getFirstVisiblePosition() == 0) {
                    BlackCarFilterActivity.this.mLeft.setSelection(0);
                }
                BlackCarFilterActivity.this.mLeftProfessionAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initPresenter() {
        this.stationListPresenter = new FilterListPresenter(this);
    }

    private void getData() {
        if ("black".equals(this.mModuleType)) {
            // 网络请求
            //this.stationListPresenter.getBlackFilterListAll();

            // 加载本地
            try {
                String textFromFile = FileUtils.readTextFromAssets(mContext, "filter_black_car.txt");
                List<FilterCategoryModel> list =  new Gson().fromJson(textFromFile, new TypeToken<List<FilterCategoryModel>>() {
                }.getType());
                showBlackFilterListAll(list);
            } catch (Exception ex) {
                Log.e(TAG, "黑烟车过滤：加载本地模板失败");
                //ex.printStackTrace();
            }

        } else if ("execution".equals(this.mModuleType)) {
            // 网络请求
            //this.stationListPresenter.getExecutionFilterListAll();

            // 加载本地
            try {
                final String textFromFile = FileUtils.readTextFromAssets(mContext, "filter_exhaust.txt");
                final List<FilterCategoryModel> list = new Gson().fromJson(textFromFile, new TypeToken<List<FilterCategoryModel>>() {
                }.getType());
                showExecutionFilterListAll(list);
            } catch (Exception ex) {
                Log.e(TAG, "处罚管理过滤：加载本地模板失败");
                //ex.printStackTrace();
            }
        } else if ("telemetry".equals(this.mModuleType)) {
            // 网络请求
            this.stationListPresenter.getExecutionFilterListAll();

        }


    }

    //以下是重写方法
    public void setPresenter(FilterListPresenter paramFilterListPresenter) {
    }

    public void hideLoading() {
    }

    public void showFail(String paramString) {
    }

    public void showLoading() {
    }

    public void showBlackFilterListAll(final List<FilterCategoryModel> filterCategoryModelList) {
        this.filterCategoryModelList = filterCategoryModelList;
        this.filterCategoryModelList.get(0).setSelectedCurrentCategory(true);

        // 从本地本文件中，重置FilterCategoryModel.inputValue值、FilterSubCategoryModel.isSelect值
        final File file = new File(FileUtils.getCacheDir(this.mContext), filterFileName);
        if (file.exists()) {
            LogUtil.i(TAG, "加载已保存的过滤文件: " + file.getPath());
            //final List<Object> objectForList = StreamUtil.readObjectForList(file);
            final List<Object> objectForList = ListSaveUtils.readListFromStorage(file);
            if (objectForList != null) {
                for (int i = 0; i < objectForList.size(); i++) {    //1.循环本地保存
                    for (int j = 0; j < filterCategoryModelList.size(); j++) {   //2.循环网络加载
                        if (((FilterCategoryModel) objectForList.get(i)).getCategoryCode().equals(filterCategoryModelList.get(j).getCategoryCode())) {
                            filterCategoryModelList.get(j).setInputValue(((FilterCategoryModel) objectForList.get(i)).getInputValue());
                            filterCategoryModelList.get(j).setBeginTime(((FilterCategoryModel) objectForList.get(i)).getBeginTime());
                            filterCategoryModelList.get(j).setEndTime(((FilterCategoryModel) objectForList.get(i)).getEndTime());

                            for (int k = 0; k < ((FilterCategoryModel) objectForList.get(i)).getSubList().size(); k++) {
                                for (int l = 0; l < filterCategoryModelList.get(j).getSubList().size(); l++) {
                                    if (((FilterCategoryModel.FilterSubCategoryModel) ((FilterCategoryModel) objectForList.get(i)).getSubList().get(k)).getSubCategoryCode().equals(((FilterCategoryModel.FilterSubCategoryModel) filterCategoryModelList.get(j).getSubList().get(l)).getSubCategoryCode())) {
                                        ((FilterCategoryModel.FilterSubCategoryModel) filterCategoryModelList.get(j).getSubList().get(l)).setSelected(((FilterCategoryModel.FilterSubCategoryModel) ((FilterCategoryModel) objectForList.get(i)).getSubList().get(k)).isSelected());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        //更新左右两个Adapter
        this.mRightProfessionAdapter.setDataList(this.filterCategoryModelList);
        this.mLeftProfessionAdapter.setDataList(this.filterCategoryModelList);
    }

    public void showExecutionFilterListAll(final List<FilterCategoryModel> filterCategoryModelList) {
        this.filterCategoryModelList = filterCategoryModelList;
        this.filterCategoryModelList.get(0).setSelectedCurrentCategory(true);
        // 从本地本文件中，重置FilterCategoryModel.inputValue值、FilterSubCategoryModel.isSelect值
        final File file = new File(FileUtils.getCacheDir(this.mContext), filterFileName);
        if (file.exists()) {
            LogUtil.i(TAG, "加载已保存的过滤文件: " + file.getPath());
            //final List<Object> objectForList = StreamUtil.readObjectForList(file);
            final List<Object> objectForList = ListSaveUtils.readListFromStorage(file);
            if (objectForList != null) {
                for (int i = 0; i < objectForList.size(); i++) {
                    for (int j = 0; j < filterCategoryModelList.size(); j++) {
                        if (((FilterCategoryModel) objectForList.get(i)).getCategoryCode().equals(filterCategoryModelList.get(j).getCategoryCode())) {
                            filterCategoryModelList.get(j).setInputValue(((FilterCategoryModel) objectForList.get(i)).getInputValue());
                            for (int k = 0; k < ((FilterCategoryModel) objectForList.get(i)).getSubList().size(); k++) {
                                for (int l = 0; l < filterCategoryModelList.get(j).getSubList().size(); l++) {
                                    if (((FilterCategoryModel.FilterSubCategoryModel) ((FilterCategoryModel) objectForList.get(i)).getSubList().get(k)).getSubCategoryCode().equals(((FilterCategoryModel.FilterSubCategoryModel) filterCategoryModelList.get(j).getSubList().get(l)).getSubCategoryCode())) {
                                        ((FilterCategoryModel.FilterSubCategoryModel) filterCategoryModelList.get(j).getSubList().get(l)).setSelected(((FilterCategoryModel.FilterSubCategoryModel) ((FilterCategoryModel) objectForList.get(i)).getSubList().get(k)).isSelected());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        //更新左右两个Adapter
        this.mRightProfessionAdapter.setDataList(this.filterCategoryModelList);
        this.mLeftProfessionAdapter.setDataList(this.filterCategoryModelList);
    }
}
