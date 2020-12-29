package com.baolong.obd.analysis.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.analysis.chartUtils.CarFlowMarkerView;
import com.baolong.obd.analysis.contract.CarFlowContract;
import com.baolong.obd.analysis.data.entity.CarFlowModel;
import com.baolong.obd.analysis.event.RefreshAnalysisByFilter;
import com.baolong.obd.analysis.presenter.CarFlowPresenter;
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
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AnalysisCarFlowActivity extends BaseActivity
        implements CarFlowContract.View {
    private static final String TAG = AnalysisCarFlowActivity.class.getSimpleName();
    String mTitle;
    String mType;
    List<FilterCategoryModel> mFilterCategoryModelList;


    private CarFlowPresenter carNumPresenter;
    private CarFlowModel carFlowModel;

    private CombinedChart mChart;//图表
    private CombinedData mCombinedData;
    String xAxisData[] = {"01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00"
            , "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_car_flow);

        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        mType = intent.getStringExtra("type");

        RxBus.get().register(this);

        initTitle();
        mChart = (CombinedChart) findViewById(R.id.combined_chart);
        //showDataOnChart();

        carNumPresenter = new CarFlowPresenter(this);
        carNumPresenter.getData(mFilterCategoryModelList);

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
                View convertView = LayoutInflater.from(AnalysisCarFlowActivity.this).inflate(R.layout.activity_select_filter, null);
                new BlackCarFilterActivity(AnalysisCarFlowActivity.this, convertView, lineView.getBottom(), "analysis").showAsDropDown(lineView);
            }
        });
    }


    /**
     * 展示数据
     */
    private void showDataOnChart() {
        //绘制图表数据
        mCombinedData = new CombinedData();
        //设置折线图数据
        mCombinedData.setData(getLineData());
        //设置柱状图数据
        mCombinedData.setData(getBarData());
        mChart.setData(mCombinedData);

        mChart.getAxisRight().setEnabled(false); //禁用右侧y轴
        setAxisXBottom(); //设置横坐标数据
        setAxisYLeft(); //设置左侧纵坐标数据

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
        final CarFlowMarkerView mv = new CarFlowMarkerView(this, R.layout.chart_marker_view, mChart, xAxisData, carFlowModel);
        mv.setChartView(mChart);
        mChart.setMarker(mv); // set the marker to the chart

        mChart.setExtraBottomOffset(20);//距视图窗口底部的偏移，类似与paddingbottom
        mChart.animateX(2000);

        //图例的位置
        Legend legend = mChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        //legend.setForm(Legend.LegendForm.DEFAULT);
        legend.setWordWrapEnabled(true);

    }

    /**
     * 设置横坐标数据
     */
    private void setAxisXBottom() {
//        String date[] = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
//        List<String> valuesX = new ArrayList<>();
//        for (int i = 0; i < carFlowModel.size(); i++) {
//            valuesX.add(carFlowModel.get(i).getLx());
//        }

        XAxis bottomAxis = mChart.getXAxis();
        bottomAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        bottomAxis.setCenterAxisLabels(true);
        bottomAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisData));
        /*解决左右两端柱形图只显示一半的情况 只有使用CombinedChart时会出现，如果单独使用BarChart不会有这个问题*/
        bottomAxis.setAxisMinimum(mCombinedData.getXMin() - 0.5f);
        bottomAxis.setAxisMaximum(mCombinedData.getXMax() + 0.5f);
        bottomAxis.setLabelCount(xAxisData.length);
        bottomAxis.setLabelRotationAngle(300);
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
        left.setValueFormatter(new DefaultAxisValueFormatter(0));
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
        //String date[] = {"车流量", "大型客车", "大型货车", "中型客车", "小型轿车", "小型货车", "面包车"};

        final List<Integer> numList = carFlowModel.getNum();
        int tempSize = 0;
        if (numList != null) {
            tempSize = Math.min(numList.size(), 24);
        }

        for (int i = 0; i < tempSize; i++) {
            pointList.add(new Entry(i + 0.5f, (float) numList.get(i)));
            LogUtil.i(TAG, "" + i +":" + (float) numList.get(i));
        }

        LineDataSet lineDataSet = new LineDataSet(pointList, "车流量");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setColor(getResources().getColor(R.color.chart_red));
        lineDataSet.setCircleColor(getResources().getColor(R.color.chart_red));
        lineDataSet.setValueTextColor(getResources().getColor(R.color.chart_red));
        lineDataSet.setCircleRadius(5);
        lineDataSet.setLineWidth(3);
        lineDataSet.setValueTextSize(12);

        LineData lineData = new LineData();
        lineData.addDataSet(lineDataSet);
        lineData.setDrawValues(false); // 启用/禁用 绘制所有 DataSets 数据对象包含的数据的值文本。
        lineData.setValueTextColor(Color.GREEN);//设置 DataSets 数据对象包含的数据的值文本的颜色。
        lineData.setValueTextSize(12f); //设置 DataSets 数据对象包含的数据的值文本的大小（单位是dp）。
        lineData.setValueFormatter(new DefaultAxisValueFormatter(0));
        lineData.setHighlightEnabled(true);//设置为true，允许通过点击高亮突出 ChartData 对象和其 DataSets 。

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

        final List<Integer> dakecheList = carFlowModel.getDakeche();
        final List<Integer> dahuocheList = carFlowModel.getDahuoche();
        final List<Integer> midkecheList = carFlowModel.getMidkeche();
        final List<Integer> smalljiaocheList = carFlowModel.getJiaoche();
        final List<Integer> smallhuocheList = carFlowModel.getSmallhuoche();
        final List<Integer> miaobaocheList = carFlowModel.getMiaobaoche();


        int tempSize = 0;
        if (smalljiaocheList != null) {
            tempSize = Math.min(smalljiaocheList.size(), 24);
        }

        for (int i = 0; i < tempSize; i++) {
            amounts.add(new BarEntry(i + 0.5f, new float[]{
                    (float) dakecheList.get(i), (float) dahuocheList.get(i),
                    (float) midkecheList.get(i), (float) smalljiaocheList.get(i),
                    (float) smallhuocheList.get(i), (float) miaobaocheList.get(i)
            }));
            LogUtil.i(TAG, "" + i +":" + (float) smalljiaocheList.get(i));
        }

        //设置总数的柱状图
        BarDataSet set1 = new BarDataSet(amounts, "车流类型");
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColors(new int[]{R.color.chart_blue_dark, R.color.chart_ju, R.color.chart_purple,
                R.color.chart_green, R.color.analysis_divide, R.color.chart_blue}, AnalysisCarFlowActivity.this);
        set1.setStackLabels(new String[]{"大型客车","大型货车","中型客车", "小型轿车", "小型货车","面包车"});
        set1.setDrawValues(false);
        //设置平均的柱状图
//        BarDataSet averageBar = new BarDataSet(averages,"降水量");
//        averageBar.setAxisDependency(YAxis.AxisDependency.LEFT);
//        averageBar.setColor(Color.parseColor("#2F4554"));
        set1.setValueTextSize(10);
        //averageBar.setValueTextSize(10);
        //barDataSet.setHighlightEnabled(true);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);


        BarData barData = new BarData(dataSets);

        //设置柱状图显示的大小
        float groupSpace = 0.06f;
        float barSpace = 0.02f;
        float barWidth = 0.5f;
        barData.setBarWidth(barWidth); //柱状图宽度
        //barData.groupBars(0, groupSpace, barSpace); // (起始点、柱状图组间距、柱状图之间间距)
        return barData;
    }

    private int[] getColors() {
        int stacksize = 6;
        //有尽可能多的颜色每项堆栈值
        int[] colors = new int[stacksize];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = ColorTemplate.MATERIAL_COLORS[i];
        }
        return colors;
    }

    // 数据分析——筛选对应的事件
    @Subscribe
    public void refreshByFilter(RefreshAnalysisByFilter paramRefreshAnalysisByFilter) {
        this.mFilterCategoryModelList = paramRefreshAnalysisByFilter.getFilterCategoryModelList();
        carNumPresenter.getData(mFilterCategoryModelList);
    }

    @Override
    public void setPresenter(CarFlowContract.Presenter paramT) {

    }

    public void showLoading() {
    }

    public void hideLoading() {
        //((BaseActivity) this).hideLoading();
    }

    public void showFail(String paramString) {
        ToastUtils.shortToast(paramString);
    }


    public void setmCombinedData(List<CarFlowModel> paramList) {
        //ToastUtils.shortToast(paramString);
        if (paramList != null && paramList.size() > 0) {
            carFlowModel = paramList.get(0);

            showDataOnChart();
        }
    }


}

