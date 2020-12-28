package com.baolong.obd.monitor.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baolong.obd.R;
import com.baolong.obd.common.base.Constance;
import com.baolong.obd.common.network.RetrofitManager;
import com.baolong.obd.common.sharepreferemces.UserSP;
import com.baolong.obd.common.utils.SharedPreferencesUtils;
import com.baolong.obd.monitor.contract.SearchStationContract;
import com.baolong.obd.monitor.data.entity.GetStationListAllResponseModel;
import com.baolong.obd.monitor.data.entity.SiteInfoItemV3;
import com.baolong.obd.monitor.data.entity.StationListAllModel;
import com.baolong.obd.monitor.presenter.SearchStationPresenter;
import com.google.gson.Gson;
import com.baolong.obd.common.base.BaseActivity;
import com.nex3z.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;


@Route(path = Constance.ACTIVITY_URL_Search)
public class SearchStationActivity extends BaseActivity
        implements SearchStationContract.View {
    private final static String TAG = SearchStationActivity.class.getSimpleName();

    private AutoCompleteTextView autoCompleteTextView;
    private TextView btn_cancel;
    private TextView btn_search;
    private ImageView btn_clear;
    private LinearLayout has_data;
    private SearchStationPresenter mSearchStationPresenter;
    private ArrayAdapter<String> mSearchAdapter;
    //站点名称集合
    private ArrayList<String> mSearchSiteNameList;
    //服务器端站点集合
    //private List<GetStationListAllResponseModel> mSearchNetModelList = new ArrayList();
    //历史搜索站点集合
    private StationListAllModel mSearchHistoryModel;

    private LinearLayout no_data;
    private FlowLayout flowLayout;

    private String type; //值station
    private ArrayList<SiteInfoItemV3> mSiteList;

    private void initTitle() {
        ((ImageView) findViewById(R.id.image_title_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ((TextView) findViewById(R.id.tv_title)).setText(getResources().getString(R.string.monitor_search_station));
    }

    private void initPresenter() {
        this.mSearchStationPresenter = new SearchStationPresenter(this);
    }

    private void getData(){
        mSearchStationPresenter.getStationQuery(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, "");
    }

    public void initData() {
        this.mSearchSiteNameList = new ArrayList<>();

        if (this.mSiteList == null) {
            this.mSiteList = new ArrayList<SiteInfoItemV3>();
        }

        for (int i = 0; i < this.mSiteList.size(); i++) {
            //if (paramList.get(i).getJzmc().contains(autoCompleteTextView.getText()))
            this.mSearchSiteNameList.add(mSiteList.get(i).getDwmc());
        }
        this.mSearchAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item_line, R.id.tv_simple_item, this.mSearchSiteNameList);
        this.autoCompleteTextView.setAdapter(this.mSearchAdapter);
        this.mSearchAdapter.notifyDataSetChanged();
    }

    private void initView() {
        //自动填充文本框
        this.autoCompleteTextView = ((AutoCompleteTextView) findViewById(R.id.autoCompleteTextView));

        //不是在我们点击EditText的时候触发;
        //也不是在我们对EditText进行编辑时触发;
        //而是在我们编辑完之后点击软键盘上的回车键才会触发
        this.autoCompleteTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                Log.i(TAG, "OnEditorActionListener_onEditorAction");

                boolean bool = false;

                // 组件布局属性里设置的imeOptions属性：定义软键盘右下角为搜索按钮
                // 按下搜索按钮，这里和imeOptions对应  则去实况地图中搜索此站点
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    setResultToBack(textView.getText().toString(), "", "", "");
                    bool = true;
                }

                //返回true，保留软键盘。false，隐藏软键盘
                return bool;
            }
        });

        this.mSearchSiteNameList = new ArrayList<>();
        this.mSearchAdapter = new ArrayAdapter<>(this, R.layout.dropdown_item_line, R.id.tv_simple_item, this.mSearchSiteNameList);
        this.autoCompleteTextView.setAdapter(this.mSearchAdapter);

        //当文本更改时调用其方法
        this.autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //这个方法被调用，说明在s字符串中，从start位置开始的count个字符即将被长度为after的新文本所取代。
                //在这个方法里面改变s，会报错。
                StringBuilder sb = new StringBuilder();
                sb.append("TextWatcher_beforeTextChanged ");
                sb.append("s");
                sb.append(":");
                sb.append(s);
                sb.append(" ");
                sb.append("start");
                sb.append(":");
                sb.append(start);
                sb.append(" ");
                sb.append("count");
                sb.append(":");
                sb.append(count);
                sb.append(" ");
                sb.append("after");
                sb.append(":");
                sb.append(after);
                Log.i(TAG, sb.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //这个方法被调用，说明在s字符串中，从start位置开始的count个字符刚刚取代了长度为before的旧文本。
                // 在这个方法里面改变s，会报错。
                StringBuilder sb = new StringBuilder();
                sb.append("TextWatcher_onTextChanged ");
                sb.append("s");
                sb.append(":");
                sb.append(s);
                sb.append(" ");
                sb.append("start");
                sb.append(":");
                sb.append(start);
                sb.append(" ");
                sb.append("before");
                sb.append(":");
                sb.append(before);
                sb.append(" ");
                sb.append("count");
                sb.append(":");
                sb.append(count);
                Log.i(TAG, sb.toString());

                if (s.length() == 0) {
                    btn_search.setClickable(false);
                } else {
                    btn_search.setClickable(true);
                }

                //网络请求数据
                //mSearchStationPresenter.getStationQuery(UserSP.getUserToken(), RetrofitManager.mUseName, RetrofitManager.mAppId, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //这个方法被调用，那么说明s字符串的某个地方已经被改变。
                Log.i(TAG, "TextWatcher_afterTextChanged " + s.toString());
            }
        });

        //取消按钮
        this.btn_cancel = ((TextView) findViewById(R.id.btn_cancel));
        this.btn_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                //SearchStationActivity.this.finish();
                autoCompleteTextView.setText("");
            }
        });
        //搜索按钮
        this.btn_search = ((TextView) findViewById(R.id.btn_search));
        this.btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResultToBack(autoCompleteTextView.getText().toString(), "", "", "");
            }
        });
        this.btn_search.setClickable(false);

        //显示搜索历史数据控件
        this.flowLayout = ((FlowLayout) findViewById(R.id.search_history));

        try {
            this.mSearchHistoryModel = ((StationListAllModel) new Gson().fromJson(SharedPreferencesUtils.read(this, this.type, null), StationListAllModel.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.mSearchHistoryModel == null) {
            this.mSearchHistoryModel = new StationListAllModel();
        }

        for (SiteInfoItemV3 tempSiteInfoItemV3 : this.mSearchHistoryModel.getHistoryList()) {
            TextView textView = new TextView(this);
            textView.setText(tempSiteInfoItemV3.getDwmc());
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            textView.setPadding(20, 20, 20, 20);
            textView.setBackgroundResource(R.drawable.bg_search_key);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    setResultToBack(((TextView) view).getText().toString(), String.valueOf(tempSiteInfoItemV3.getDdwd()), String.valueOf(tempSiteInfoItemV3.getDdjd()), tempSiteInfoItemV3.getDwbh());
                }
            });
            this.flowLayout.addView(textView);
        }
        this.flowLayout.setChildSpacing(20);
        this.flowLayout.setRowSpacing(20.0F);
        this.flowLayout.setFlow(true);

        //清除历史数据
        this.btn_clear = ((ImageView) findViewById(R.id.img_clear));
        this.btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtils.write(getApplicationContext(), type, "[]");
                mSearchHistoryModel.getHistoryList().clear();
                mSearchAdapter.notifyDataSetChanged();
                flowLayout.removeAllViews();
            }
        });

        this.has_data = ((LinearLayout) findViewById(R.id.has_data));
        this.no_data = ((LinearLayout) findViewById(R.id.no_data));
        if (this.mSearchHistoryModel.getHistoryList().size() > 0) {
            this.has_data.setVisibility(View.VISIBLE);
            this.no_data.setVisibility(View.GONE);
        } else {
            this.has_data.setVisibility(View.GONE);
            this.no_data.setVisibility(View.VISIBLE);
        }

    }

    public void finish() {
        super.finish();
        closeInput();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitor_search_activity);
        Intent intent = getIntent();
        if (intent != null) {
            this.type = intent.getStringExtra("type");
            this.mSiteList = (ArrayList<SiteInfoItemV3>) intent.getSerializableExtra("data");
        }
        initTitle();
        initView();
        initPresenter();
        //getWarningData();
        initData();
    }

    /**
     * 设置setResult，返回上一级实况地图界面
     * 点击历史纪录项(4个参数非空)，键盘搜索时调用(1个参数非空)
     */
    public void setResultToBack(String text, String latStr, String longStr, String jzbhStr) {

        if ((!TextUtils.isEmpty(text)) && (!TextUtils.isEmpty(latStr))
                && (!TextUtils.isEmpty(longStr)) && (!TextUtils.isEmpty(jzbhStr))) {
            Intent intent = new Intent();
            intent.putExtra("result", text);
            intent.putExtra("lat", latStr);
            intent.putExtra("long", longStr);
            intent.putExtra("jzbh", jzbhStr);
            setResult(-1, intent);
        } else {
            boolean hasStationInSearchNet = false;
            SiteInfoItemV3 tempModel = new SiteInfoItemV3();
            //判断服务器返回的列表是否有此站点
            for (int i = 0; i < this.mSiteList.size(); i++) {
                if (((SiteInfoItemV3) this.mSiteList.get(i)).getDwmc().equals(text)) {
                    tempModel = (SiteInfoItemV3) this.mSiteList.get(i);
                    hasStationInSearchNet = true;
                    break;
                }
            }
            //如果服务器返回的列表有此站点，则判断历史搜索列表中是否有此站点，有则删除此站点再添加此站点到表头
            if (hasStationInSearchNet) {
                for (int i = 0; i < this.mSearchHistoryModel.getHistoryList().size(); i++) {
                    if (((SiteInfoItemV3) this.mSearchHistoryModel.getHistoryList().get(i)).getDwmc().equals(tempModel.getDwmc())) {
                        this.mSearchHistoryModel.getHistoryList().remove(i);
                    }
                }
                this.mSearchHistoryModel.getHistoryList().add(0, tempModel);
                SharedPreferencesUtils.write(getApplicationContext(), this.type, new Gson().toJson(this.mSearchHistoryModel));

                Intent intent = new Intent();
                //intent.putExtra("result", text);
                intent.putExtra("lat", String.valueOf(tempModel.getDdwd()));
                intent.putExtra("long", String.valueOf(tempModel.getDdjd()));
                intent.putExtra("jzbh", tempModel.getDwbh());
                setResult(-1, intent);
            }

        }
        this.finish();
    }

    // 重写几个方法
    public void setPresenter(SearchStationPresenter paramMonitorMainPresenter) {
    }

    public void showFail(String paramString) {
    }

    public void showLoading() {
    }

    public void showStationSearchList(List<GetStationListAllResponseModel> paramList) {
//        this.mSearchNetNameList = new ArrayList();
//
//        this.mSearchNetModelList = paramList;
//        if (this.mSearchNetModelList == null) {
//            this.mSearchNetModelList = new ArrayList<GetStationListAllResponseModel>();
//        }
//
//        for (int i = 0; i < this.mSearchNetModelList.size(); i++) {
//            //if (paramList.get(i).getJzmc().contains(autoCompleteTextView.getText()))
//            this.mSearchNetNameList.add(paramList.get(i).getJzmc());
//        }
//        this.mSearchAdapter = new ArrayAdapter(this, R.layout.dropdown_item_line, R.id.tv_simple_item, this.mSearchNetNameList);
//        this.autoCompleteTextView.setAdapter(this.mSearchAdapter);
//        this.mSearchAdapter.notifyDataSetChanged();
    }

}