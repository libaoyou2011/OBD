package com.baolong.obd.analysis.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.analysis.chartUtils.CLJRMarkerView;
import com.baolong.obd.analysis.contract.CQJRContract;
import com.baolong.obd.analysis.contract.JCJLContract;
import com.baolong.obd.analysis.data.entity.CQJRModel;
import com.baolong.obd.analysis.data.entity.JCJLModel;
import com.baolong.obd.analysis.event.RefreshAnalysisByFilter;
import com.baolong.obd.analysis.presenter.CQJRPresenter;
import com.baolong.obd.analysis.presenter.JCJLPresenter;
import com.baolong.obd.blackcar.activity.BlackCarFilterActivity;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.utils.ToastUtils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
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
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * OBD：监测记录统计
 */
public class AnalysisJCJLActivity extends BaseActivity implements JCJLContract.View {
    private static final String TAG = AnalysisJCJLActivity.class.getSimpleName();

    String mTitle;
    String mType = "clzs";  //type=clzs、zxsl、lxsl、bjsl
    List<FilterCategoryModel> mFilterCategoryModelList;

    private BarChart mChart;//图表

    private JCJLPresenter mPresenter;
    private List<JCJLModel> mItems = new ArrayList<>();
    private ArrayList<String> mXValues = new ArrayList<>();  //X轴坐标


    private void initTitle() {
        ((ImageView) this.findViewById(R.id.image_title_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnalysisJCJLActivity.this.onBackPressed();

            }
        });

        final TextView titleTextView = (TextView) this.findViewById(R.id.tv_title);
        titleTextView.setText(mTitle);

        final View lineView = this.findViewById(R.id.v_top);
        TextView mTopBarRightTv = ((TextView) this.findViewById(R.id.tv_right_text));
        mTopBarRightTv.setVisibility(View.GONE);
//        mTopBarRightTv.setText("筛选");
//        mTopBarRightTv.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                View convertView = LayoutInflater.from(AnalysisJCJLActivity.this).inflate(R.layout.activity_select_filter, null);
//                new BlackCarFilterActivity(AnalysisJCJLActivity.this, convertView, lineView.getBottom(), "analysis").showAsDropDown(lineView);
//            }
//        });
    }


    /**
     * 展示数据
     */
    private void showDataOnChart() {
        //1.坐标设置
        setAxisXBottom(); //设置横坐标数据
        setAxisYLeft(); //设置左侧纵坐标数据
        mChart.getAxisRight().setEnabled(false);//禁用右侧y轴

        //2.设置柱状图数据
        mChart.setData(getBarData());

        //1. chart 基本设置
        //设置 chart 的描述文字
        Description description = mChart.getDescription();
        description.setEnabled(false);  //设置描述文本不显示
        description.setText("图表描述");
        description.setTextColor(Color.parseColor("#ff00ff00"));   //设置颜色时要ARGB完整的八位（如 0xff00ff00）
        description.setTextSize(10f);    //最小6f，最大16f。
        description.setTypeface(Typeface.DEFAULT_BOLD); //设置描述文字的 Typeface。
        description.setPosition(0, 0);  //自定义描述文字在屏幕上的位置（单位是像素）。
        mChart.setDescription(description);

        //设置当 chart 为空时显示的描述文字。
        mChart.setNoDataText("No chart data available.");

        //设置 chart 网格背景
        mChart.setDrawGridBackground(false); //如果启用，chart 绘图区后面的背景矩形将绘制。
        mChart.setGridBackgroundColor(Color.parseColor("#0000ff"));

        //设置 chart边框
        mChart.setDrawBorders(false); // 启用/禁用绘制图表边框（chart周围的线）
        mChart.setBorderColor(Color.parseColor("#ff00ff"));
        mChart.setBorderWidth(2f);

        //1.2其他设置
        mChart.setDrawBarShadow(false); // 柱图每列的底层背景
        mChart.setFitBars(true);//使两侧的柱图完全显示
        mChart.setExtraBottomOffset(0);//距视图窗口底部的偏移，类似与paddingbottom

        //1.3 修改视窗
        mChart.setVisibleXRangeMinimum(4); //设定x轴最小可见区域范围的大小。如果设置为17，则不可能进一步放大视图（在x轴超过17的值）。
        //mChart.setVisibleXRangeMaximum(3); //设定x轴最大可见区域范围的大小。 如果设定为3，则在x轴超过3的值被视为不可见（不滑动 chart 的话）


        //2. 设置 chart 交互
        //启用/ 禁止 手势交互
        mChart.setTouchEnabled(true); //启用/禁用与图表的所有可能的触摸交互。默认true

        mChart.setDragEnabled(true); //启用/禁用拖动（平移）图表。
        mChart.setDragDecelerationFrictionCoef(0.9f);

        mChart.setScaleEnabled(true); //启用/禁用缩放图表上的两个轴。
        mChart.setScaleXEnabled(true); //启用/禁用缩放在x轴上。
        mChart.setScaleYEnabled(true); //启用/禁用缩放在y轴。

        mChart.setPinchZoom(true); //如果设置为true，捏缩放功能。 如果false，x轴和y轴可分别放大。

        mChart.setDoubleTapToZoomEnabled(false); //如果设置为true，捏缩放功能。如果false，x轴和y轴可分别放大。
        mChart.setHighlightPerDragEnabled(true); //设置为true，允许每个图表表面拖过，当它完全缩小突出。 默认值：true
        mChart.setHighlightPerTapEnabled(true); //设置为false，以防止值由敲击姿态被突出显示。 值仍然可以通过拖动或编程方式突出显示。 默认值：true

        mChart.setHighlightFullBarEnabled(true);

        //3. MarkView
        //点击交叉点弹出Y轴对比数据的关键代码调用，调用的是重写MarkerView的MarkerViews类。
        final CLJRMarkerView mv = new CLJRMarkerView(this, R.layout.chart_marker_view, "记录数：", mChart, mXValues);
        mv.setChartView(mChart);
        mChart.setMarker(mv);  // set the marker to the chart

        //4. 动画 Animations
        mChart.animateX(2000);

        //5. 图例
        Legend legend = mChart.getLegend();
        legend.setEnabled(false);//设置Legend启用或禁用。
        //legend.setTextColor(int color) //设置图例标签的颜色。
        //legend.setTextSize(float size) //设置在DP传说标签的文字大小。
        //legend.setWordWrapEnabled(boolean enabled) //如果启用，Legend 的内容将不会超出图表边界之外，而是创建一个新的行。 请注意，这会降低性能和仅适用于”legend 位于图表下面”的情况。
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);//图例水平居中
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); //图例在图表上方
        //legend.setOrientation(Legend.LegendOrientation.HORIZONTAL); //图例的方向为水平
        //legend.setDrawInside(false); //绘制在chart的外侧
        //legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT); //图例中的文字方向
        //legend.setForm(Legend.LegendForm.SQUARE); //图例窗体的形状
        //legend.setFormSize(0f); //图例窗体的大小
        legend.setTextSize(12f); //图例文字的大小
        legend.resetCustom();
        //legend.setCustom(new LegendEntry[]{});  //设置图类的颜色集合
    }

    /**
     * 设置横坐标数据
     */
    private void setAxisXBottom() {
        ArrayList<String> valuesX = new ArrayList<>();
        for (int i = 0; i < mItems.size(); i++) {
            if (!TextUtils.isEmpty(mItems.get(i).getMonth())) {
                valuesX.add(mItems.get(i).getMonth() + "月");
            } else {
                valuesX.add("");
            }
        }
        mXValues = valuesX;
        XAxis bottomAxis = mChart.getXAxis();
        bottomAxis.setEnabled(true);
        bottomAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  // 设置X轴的位置。 TOP、BOTTOM、TOP_INSIDE、BOTTOM_INSIDE、BOTH_SIDED
        bottomAxis.setDrawGridLines(false); //设置为true，则绘制网格线。必须 xAxis.setEnabled(true)才有效
        bottomAxis.setCenterAxisLabels(true);
        bottomAxis.setValueFormatter(new IndexAxisValueFormatter(valuesX));
        bottomAxis.setLabelCount(mItems.size());
        bottomAxis.setLabelRotationAngle(0);
        //bottomAxis.setAxisLineColor(Color.BLUE); //设置该轴轴行的颜色。
        bottomAxis.setAxisLineWidth(1f);//设置该轴轴行的宽度。
        bottomAxis.setAvoidFirstLastClipping(false);
        bottomAxis.setGranularity(1f);//禁止放大后x轴标签重绘
    }

    /**
     * 设置左侧纵坐标数据
     */
    private void setAxisYLeft() {
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setAxisLineWidth(1f);
        /*leftAxis .setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "" + (int) value;//这句是重点!
            }
        });*/
        leftAxis.setValueFormatter(new DefaultAxisValueFormatter(0));
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0);//为坐标轴设置最小值

    }

    /**
     * 设置柱状图绘制数据
     */
    public BarData getBarData() {
        //总量金额
        List<BarEntry> amounts = new ArrayList<>();
        for (int i = 0; i < mItems.size(); i++) {
            if (mItems.get(i).getNumArr() != null && mItems.get(i).getNumArr().size() > 1) {
                amounts.add(new BarEntry(i + 0.5f, Float.parseFloat(mItems.get(i).getNumArr().get(1))));
            }
        }
        //设置总数的柱状图
        BarDataSet barDataSet = new BarDataSet(amounts, "记录总数");
        barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        //单一色
        barDataSet.setColor(getResources().getColor(R.color.chart_blue));
        //多色
        //barDataSet.setColors(new int[]{R.color.chart_purple, R.color.chart_blue, R.color.chart_gray, R.color.chart_green, R.color.chart_red, R.color.chart_red, R.color.chart_green, R.color.chart_red, R.color.chart_green}, AnalysisJCJLActivity.this);
        barDataSet.setValueTextSize(10);
        barDataSet.setDrawValues(true);
        barDataSet.setHighlightEnabled(true);

        BarData barData = new BarData();
        barData.addDataSet(barDataSet);
        barData.setValueFormatter(new DefaultAxisValueFormatter(0));
//        barData.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                return StringUtils.double2String(value, 4);
//            }
//        });

        //设置柱状图显示的大小
        float groupSpace = 0.06f;
        float barSpace = 0.02f;
        float barWidth = 0.6f;
        barData.setBarWidth(barWidth); //柱状图宽度
        //barData.groupBars(0, groupSpace, barSpace); // (起始点、柱状图组间距、柱状图之间间距)
        return barData;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_jcjl);

        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        RxBus.get().register(this);

        initTitle();

        // 年 条件选择
        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
        List<String> years = new ArrayList<>();
        // 获取当前年
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        for (int i = 0; i < 6; i++) {
            years.add(String.valueOf(year-i));
        }
        spinner.setItems(years);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                //ToastUtils.longToast("Clicked " + item);
                mPresenter.getData(spinner.getText().toString(), null, null, null);
            }
        });

        //chart
        mChart = (BarChart) findViewById(R.id.bar_chart);

        //showDataOnChart();
        mPresenter = new JCJLPresenter(this);
        mPresenter.getData(spinner.getText().toString(), null, null, null);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }

    // 数据分析——筛选对应的事件
    /*@Subscribe
    public void refreshByFilter(RefreshAnalysisByFilter paramRefreshAnalysisByFilter) {
        this.mFilterCategoryModelList = paramRefreshAnalysisByFilter.getFilterCategoryModelList();
        mPresenter.getData(mType);
    }*/

    @Override
    public void setPresenter(JCJLContract.Presenter paramT) {

    }

    public void showLoading() {
    }

    public void hideLoading() {
        //((BaseActivity) this).hideLoading();
    }

    public void showFail(String paramString) {
        ToastUtils.shortToast(paramString);
    }

    public void setData(List<JCJLModel> paramList) {
        //ToastUtils.shortToast(paramString);
        if (paramList != null && paramList.size() > 0) {
            mItems = paramList;

            showDataOnChart();

        }
    }


}

