package com.baolong.obd.analysis.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.analysis.chartUtils.CarFlowMarkerView;
import com.baolong.obd.analysis.chartUtils.CarNumMarkerView;
import com.baolong.obd.analysis.contract.CarNumContract;
import com.baolong.obd.analysis.data.entity.CarNumModel;
import com.baolong.obd.analysis.event.RefreshAnalysisByFilter;
import com.baolong.obd.analysis.presenter.CarNumPresenter;
import com.baolong.obd.blackcar.activity.BlackCarFilterActivity;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.common.utils.ToastUtils;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AnalysisCarNumActivity extends BaseActivity
        implements CarNumContract.View {
    private static final String TAG = AnalysisCarNumActivity.class.getSimpleName();
    String mTitle;
    String mType;
    List<FilterCategoryModel> mFilterCategoryModelList;

    private CombinedChart mChart;//图表
    private CombinedData mData;

    private CarNumPresenter carNumPresenter;
    private List<CarNumModel> mItems = new ArrayList<CarNumModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_car_num);

        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        mType = intent.getStringExtra("type");

        RxBus.get().register(this);

        initTitle();

        carNumPresenter = new CarNumPresenter(this);
        carNumPresenter.getData(mFilterCategoryModelList);

        mChart = (CombinedChart) findViewById(R.id.combined_chart);
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

        final android.view.View lineView = this.findViewById(R.id.v_top);
        TextView mTopBarRightTv = ((TextView) this.findViewById(R.id.tv_right_text));
        mTopBarRightTv.setVisibility(View.VISIBLE);
        mTopBarRightTv.setText("筛选");
        mTopBarRightTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View convertView = LayoutInflater.from(AnalysisCarNumActivity.this).inflate(R.layout.activity_select_filter, null);
                new BlackCarFilterActivity(AnalysisCarNumActivity.this, convertView, lineView.getBottom(), "analysis").showAsDropDown(lineView);
            }
        });
    }


    /**
     * 展示数据
     */
    private void showDataOnChart() {
        //绘制图表数据
        mData = new CombinedData();
        //设置柱状图数据
        mData.setData(getBarData());
        //设置折线图数据
        mData.setData(getLineData());
        mChart.setData(mData);

        //设置横坐标数据
        setAxisXBottom();
        //设置右侧纵坐标数据
        setAxisYRight();
        //设置左侧纵坐标数据
        setAxisYLeft();

        mChart.getDescription().setEnabled(false); // 不显示备注信息
        mChart.setDrawBorders(false); // 显示边界
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setDrawBarShadow(false);

        //设置是否可以触摸
        mChart.setTouchEnabled(true);
        mChart.setDragDecelerationFrictionCoef(0.9f);
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
        final CarNumMarkerView mv = new CarNumMarkerView(this, R.layout.chart_marker_view, mChart, mItems);
        mv.setChartView(mChart);
        mChart.setMarker(mv); // set the marker to the chart

        mChart.setExtraBottomOffset(20);//距视图窗口底部的偏移，类似与paddingbottom
        mChart.animateX(2000);

        //图例的位置
        Legend legend = mChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
    }

    /**
     * 设置横坐标数据
     */
    private void setAxisXBottom() {
        List<String> valuesX = new ArrayList<>();
        //String date[] = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
        for (int i = 0; i < mItems.size(); i++) {
            valuesX.add(mItems.get(i).getLx());
        }
        XAxis bottomAxis = mChart.getXAxis();
        bottomAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        bottomAxis.setCenterAxisLabels(true);
        bottomAxis.setValueFormatter(new IndexAxisValueFormatter(valuesX));
        /*解决左右两端柱形图只显示一半的情况 只有使用CombinedChart时会出现，如果单独使用BarChart不会有这个问题*/
        bottomAxis.setAxisMinimum(mData.getXMin() - 0.5f);
        bottomAxis.setAxisMaximum(mData.getXMax() + 0.5f);
        bottomAxis.setLabelCount(mItems.size());
        //bottomAxis.setAxisLineWidth(2f);
        bottomAxis.setAvoidFirstLastClipping(false);
    }

    /**
     * 设置右侧纵坐标数据
     */
    private void setAxisYRight() {
        YAxis right = mChart.getAxisRight();
//        right.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return value + "℃";
//            }
//        });

        right.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value == 0f) {
                    return 0 + "";
                }
                DecimalFormat df = new DecimalFormat("#.0");
                return df.format(value);
            }
        });

//        right.setValueFormatter(new DefaultAxisValueFormatter(2));

        right.setDrawGridLines(false);
        right.setTextColor(getResources().getColor(R.color.chart_red));
        //right.setAxisLineWidth(2f);
        right.setAxisMinimum(0f);//为坐标轴设置最小值
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
        left.setValueFormatter(new DefaultAxisValueFormatter(2));
        left.setDrawGridLines(false);
        //left.setAxisLineWidth(2f);
        left.setAxisMinimum(0f);//为坐标轴设置最小值

    }

    /**
     * 设置折线图绘制数据
     * 温度
     *
     * @return
     */
    public LineData getLineData() {
        List<Entry> pointList = new ArrayList<>();
        //float[] data = {2.0f, 2.2f, 3.3f, 4.5f, 6.3f, 10.2f, 20.3f, 23.4f, 23.0f, 16.5f, 12.0f, 6.2f};
        //人数
        for (int i = 0; i < mItems.size(); i++) {
            pointList.add(new Entry(i + 0.5f, Float.parseFloat(mItems.get(i).getCbl())));
            LogUtil.i(TAG, "" + i +":" + Float.parseFloat(mItems.get(i).getCbl()));
        }
        LineDataSet lineDataSet = new LineDataSet(pointList, "超标率（%）");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);
        lineDataSet.setColor(getResources().getColor(R.color.chart_red));
        lineDataSet.setCircleColor(getResources().getColor(R.color.chart_red));
        lineDataSet.setCircleRadius(5);
        lineDataSet.setLineWidth(3);
        lineDataSet.setValueTextSize(12);
        lineDataSet.setValueTextColor(getResources().getColor(R.color.chart_red));

        LineData lineData = new LineData();
        lineData.addDataSet(lineDataSet);
        lineData.setHighlightEnabled(true);
        lineData.setValueFormatter(new DefaultAxisValueFormatter(2));

        return lineData;
    }

    /**
     * 设置柱状图绘制数据
     *
     * @return
     */
    public BarData getBarData() {
        //总量金额
        List<BarEntry> amounts = new ArrayList<>();
        //float z[] = {2.0f, 4.9f, 7.0f, 23.2f, 25.6f, 76.7f, 135.6f, 162.2f, 32.6f, 20.0f, 6.4f, 3.3f};
        //平均金额
        //List<BarEntry> averages = new ArrayList<>();
        //float j[] = {2.6f, 5.9f, 9.0f, 26.4f, 28.7f, 70.7f, 175.6f, 182.2f, 48.7f, 18.8f, 6.0f, 2.3f};
        //添加数据
        for (int i = 0; i < mItems.size(); i++) {
            amounts.add(new BarEntry(i + 0.5f, Float.parseFloat(mItems.get(i).getTotal())));
            //averages.add(new BarEntry(i,j[i]));
        }
        //设置总数的柱状图
        BarDataSet amountBar = new BarDataSet(amounts, "数量（条）");
        amountBar.setAxisDependency(YAxis.AxisDependency.LEFT);
        amountBar.setColor(getResources().getColor(R.color.chart_blue));
        //设置平均的柱状图
//        BarDataSet averageBar = new BarDataSet(averages,"降水量");
//        averageBar.setAxisDependency(YAxis.AxisDependency.LEFT);
//        averageBar.setColor(Color.parseColor("#2F4554"));
        amountBar.setValueTextSize(10);
        //averageBar.setValueTextSize(10);

        BarData barData = new BarData();
        barData.addDataSet(amountBar);
        //barData.addDataSet(averageBar);
        barData.setHighlightEnabled(true);

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
        carNumPresenter.getData(mFilterCategoryModelList);
    }

    @Override
    public void setPresenter(CarNumContract.Presenter paramT) {

    }

    public void showLoading() {
    }

    public void hideLoading() {
        //((BaseActivity) this).hideLoading();
    }

    public void showFail(String paramString) {
        ToastUtils.shortToast(paramString);
    }


    public void setmData(List<CarNumModel> paramList) {
        //ToastUtils.shortToast(paramString);
        if (paramList != null && paramList.size() > 0) {
            mItems = paramList;

            showDataOnChart();

        }
    }


}

