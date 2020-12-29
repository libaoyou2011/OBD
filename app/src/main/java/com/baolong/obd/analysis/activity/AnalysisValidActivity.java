package com.baolong.obd.analysis.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.analysis.chartUtils.MyMarkerView;
import com.baolong.obd.analysis.contract.DataContract;
import com.baolong.obd.analysis.data.entity.DataModel;
import com.baolong.obd.analysis.event.RefreshAnalysisByFilter;
import com.baolong.obd.analysis.presenter.DataPresenter;
import com.baolong.obd.blackcar.activity.BlackCarFilterActivity;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.utils.ToastUtils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class AnalysisValidActivity extends BaseActivity
        implements DataContract.View {
    private static final String TAG = AnalysisValidActivity.class.getSimpleName();
    String mTitle;
    String mType;
    List<FilterCategoryModel> mFilterCategoryModelList;

    private BarChart mChart;//图表

    private DataPresenter mPresenter;
    private List<DataModel> mItems = new ArrayList<DataModel>();
    private ArrayList<String> mXValues = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_valid);

        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        mType = intent.getStringExtra("type");

        RxBus.get().register(this);

        initTitle();

        mPresenter = new DataPresenter(this);
        mPresenter.getData(mFilterCategoryModelList);

        mChart = (BarChart) findViewById(R.id.bar_chart);
        //showDataOnChart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    private void initTitle() {
        ((ImageView) this.findViewById(R.id.image_title_back)).setVisibility(View.GONE);

        final TextView titleTextView = (TextView) this.findViewById(R.id.tv_title);
        titleTextView.setText(mTitle);

        final View lineView = this.findViewById(R.id.v_top);
        TextView mTopBarRightTv = ((TextView) this.findViewById(R.id.tv_right_text));
        mTopBarRightTv.setVisibility(View.VISIBLE);
        mTopBarRightTv.setText("筛选");
        mTopBarRightTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View convertView = LayoutInflater.from(AnalysisValidActivity.this).inflate(R.layout.activity_select_filter, null);
                new BlackCarFilterActivity(AnalysisValidActivity.this, convertView, lineView.getBottom(), "analysis").showAsDropDown(lineView);
            }
        });
    }


    /**
     * 展示数据
     */
    private void showDataOnChart() {
        //绘制图表数据
        //设置柱状图数据
        mChart.setData(getBarData());

        //禁用右侧y轴
        mChart.getAxisRight().setEnabled(false);
        //设置横坐标数据
        setAxisXBottom();
        //设置左侧纵坐标数据
        setAxisYLeft();

        mChart.getDescription().setEnabled(false); //设置描述文本不显示
        mChart.setDrawBorders(false); // 显示边界
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);

        //设置是否可以触摸
        mChart.setTouchEnabled(true);
        mChart.setDragDecelerationFrictionCoef(1.0f);
        //设置是否可以拖拽
        mChart.setDragEnabled(true);
        //设置是否可以缩放
        mChart.setScaleEnabled(false);
        mChart.setHighlightPerDragEnabled(true);
        mChart.setPinchZoom(true);//设置按比例放缩柱状图

        mChart.setHighlightPerTapEnabled(true);
        //mChart.setHighlightFullBarEnabled(false);
        //mChart.setHighlightPerDragEnabled(false);

        //点击交叉点弹出Y轴对比数据的关键代码调用，调用的是重写MarkerView的MarkerViews类。
        final MyMarkerView mv = new MyMarkerView(this, R.layout.chart_marker_view, mChart, mXValues);
        mv.setChartView(mChart);
        mChart.setMarker(mv);  // set the marker to the chart

        mChart.setExtraBottomOffset(50);//距视图窗口底部的偏移，类似与paddingbottom
        mChart.setFitBars(true);//使两侧的柱图完全显示
        mChart.animateX(2000);

        //图例的位置
        Legend legend = mChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);//图例水平居中
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);//图例在图表上方
//        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);//图例的方向为水平
//        legend.setDrawInside(false);//绘制在chart的外侧
//        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);//图例中的文字方向
//        legend.setForm(Legend.LegendForm.SQUARE);//图例窗体的形状
//        legend.setFormSize(0f);//图例窗体的大小
        legend.setTextSize(12f);//图例文字的大小
    }

    /**
     * 设置横坐标数据
     */
    private void setAxisXBottom() {
        List<String> valuesX = new ArrayList<>();
        for (int i = 0; i < mItems.size(); i++) {
            valuesX.add(mItems.get(i).getDataname());
        }
        XAxis bottomAxis = mChart.getXAxis();
        bottomAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        bottomAxis.setCenterAxisLabels(true);
        bottomAxis.setValueFormatter(new IndexAxisValueFormatter(valuesX));
        bottomAxis.setLabelCount(mItems.size());
        //bottomAxis.setLabelRotationAngle(45);
        //bottomAxis.setAxisLineWidth(2f);
        bottomAxis.setAvoidFirstLastClipping(false);
    }

    /**
     * 设置左侧纵坐标数据
     */
    private void setAxisYLeft() {
        YAxis left = mChart.getAxisLeft();
//        left.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return value + "ml";
//            }
//        });
        left.setValueFormatter(new DefaultAxisValueFormatter(0));
        left.setDrawGridLines(false);
        left.setAxisMinimum(0f);//为坐标轴设置最小值

    }

    /**
     * 设置柱状图绘制数据
     *
     * @return
     */
    public BarData getBarData() {
        //总量金额
        List<BarEntry> amounts = new ArrayList<>();
        for (int i = 0; i < mItems.size(); i++) {
            amounts.add(new BarEntry(i + 0.5f, (float) mItems.get(i).getProportion() * 100));
        }
        //设置总数的柱状图
        BarDataSet barDataSet = new BarDataSet(amounts, "占比（%）");
        barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        //barDataSet.setColor(getResources().getColor(R.color.chart_blue));//单一颜色
        barDataSet.setColors(new int[]{ R.color.chart_blue,  R.color.chart_green, R.color.chart_gray}, AnalysisValidActivity.this);
        barDataSet.setValueTextSize(10);

        BarData barData = new BarData();
        barData.addDataSet(barDataSet);
        barData.setHighlightEnabled(true);
        barData.setValueFormatter(new DefaultAxisValueFormatter(4));
//        barData.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                return StringUtils.double2String(value, 4);
//            }
//        });

        //设置柱状图显示的大小
        float groupSpace = 0.06f;
        float barSpace = 0.02f;
        float barWidth = 0.5f;
        barData.setBarWidth(barWidth); //柱状图宽度
        //barData.groupBars(0, groupSpace, barSpace); // (起始点、柱状图组间距、柱状图之间间距)
        return barData;
    }

    // 数据分析——筛选对应的事件
    @Subscribe
    public void refreshByFilter(RefreshAnalysisByFilter paramRefreshAnalysisByFilter) {
        this.mFilterCategoryModelList = paramRefreshAnalysisByFilter.getFilterCategoryModelList();
        mPresenter.getData(mFilterCategoryModelList);
    }

    @Override
    public void setPresenter(DataContract.Presenter paramT) {

    }

    public void showLoading() {
    }

    public void hideLoading() {
        //((BaseActivity) this).hideLoading();
    }

    public void showFail(String paramString) {
        ToastUtils.shortToast(paramString);
    }


    public void setData(List<DataModel> paramList) {
        if (paramList != null && paramList.size() > 0) {
            //mItems = paramList;

            mItems.clear();
            for (DataModel dataModel : paramList)  {
                if ("记录总数".equals(dataModel.getDataname()) || "有效数据".equals(dataModel.getDataname()) || "无效数据".equals(dataModel.getDataname())) {
                    mItems.add(dataModel);
                }
            }

            for (int i = 0; i < mItems.size(); i++) {
                mXValues.add(mItems.get(i).getDataname());
            }

            showDataOnChart();
        }
    }


}

