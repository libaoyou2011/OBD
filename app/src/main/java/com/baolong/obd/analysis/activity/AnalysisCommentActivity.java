package com.baolong.obd.analysis.activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.baolong.obd.R;
import com.baolong.obd.blackcar.activity.BlackCarFilterActivity;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.execution.fragment.ExecListFragment;
import com.baolong.obd.querycar.fragment.QueryCarMainFragment;

/**
 *  遥测数据、车辆查询
 */
@Route(path = "/analysis/activity/AnalysisCommentActivity")
public class AnalysisCommentActivity extends BaseActivity {

    public static final String Table_telemetry = "telemetry";  //遥测

    private String mTitle;
    private String mType;

    private void initTitle() {
        if ("telemetryData".equals(mType)) {
            ((ImageView) this.findViewById(R.id.image_title_back)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AnalysisCommentActivity.this.onBackPressed();

                }
            });

            final TextView titleTextView = (TextView) this.findViewById(R.id.tv_title);
            titleTextView.setText(mTitle);

            final android.view.View lineView = this.findViewById(R.id.v_top);
            TextView mTopBarRightTv = ((TextView) this.findViewById(R.id.tv_right_text));
            mTopBarRightTv.setVisibility(View.VISIBLE);
            mTopBarRightTv.setText("筛选");
            mTopBarRightTv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    View convertView = LayoutInflater.from(AnalysisCommentActivity.this).inflate(R.layout.activity_select_filter, null);
                    new BlackCarFilterActivity(AnalysisCommentActivity.this, convertView, lineView.getBottom(), "telemetry").showAsDropDown(lineView);
                }
            });
        } else if("findCar".equals(mType)){
            ((ImageView) this.findViewById(R.id.image_title_back)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AnalysisCommentActivity.this.onBackPressed();

                }
            });
            ((TextView) this.findViewById(R.id.tv_title)).setText(mTitle);
        }

    }

    private void initView() {
        if ("telemetryData".equals(mType)) {
            // 遥测数据界面
            ExecListFragment execListFragment = new ExecListFragment();
            Bundle arguments2 = new Bundle();
            arguments2.putString("type", Table_telemetry);
            execListFragment.setArguments(arguments2);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container,  execListFragment)
                    .commit();

        } else if ("findCar".equals(mType)) {
            //车辆查询界面
            QueryCarMainFragment execListFragment = new QueryCarMainFragment();
            Bundle arguments2 = new Bundle();
            arguments2.putString("type", Table_telemetry);
            execListFragment.setArguments(arguments2);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container,  execListFragment)
                    .commit();
        }

    }

    public AnalysisCommentActivity() {
        super();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_telemetry);

        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        mType = intent.getStringExtra("type");

        this.initTitle();
        this.initView();

    }

}

