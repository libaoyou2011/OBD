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
import com.baolong.obd.analysis.chartUtils.NOxMarkerView;
import com.baolong.obd.analysis.contract.NOxContract;
import com.baolong.obd.analysis.data.entity.NOxModel;
import com.baolong.obd.analysis.event.RefreshAnalysisByFilter;
import com.baolong.obd.analysis.presenter.NOxPresenter;
import com.baolong.obd.blackcar.activity.BlackCarFilterActivity;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.common.base.BaseActivity;
import com.baolong.obd.common.utils.DateUtils;
import com.baolong.obd.common.utils.ToastUtils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.DefaultAxisValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * OBD：NOx排放统计
 */
public class AnalysisNOxActivity extends BaseActivity implements NOxContract.View {
    private static final String TAG = AnalysisNOxActivity.class.getSimpleName();

    String mTitle;
    String mType = "clzs";  //type=clzs、zxsl、lxsl、bjsl
    List<FilterCategoryModel> mFilterCategoryModelList;


    MaterialSpinner monthSpinner;

    private LineChart mChart;//图表

    private NOxPresenter mPresenter;
    private List<NOxModel> mItems = new ArrayList<>();
    private final ArrayList<String> mXValues = new ArrayList<>();  //X轴坐标


    private void initTitle() {
        ((ImageView) this.findViewById(R.id.image_title_back)).setVisibility(View.GONE);

        final TextView titleTextView = (TextView) this.findViewById(R.id.tv_title);
        titleTextView.setText(mTitle);

        final View lineView = this.findViewById(R.id.v_top);
        TextView mTopBarRightTv = ((TextView) this.findViewById(R.id.tv_right_text));
        mTopBarRightTv.setVisibility(View.GONE);
        mTopBarRightTv.setText("筛选");
        mTopBarRightTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                View convertView = LayoutInflater.from(AnalysisNOxActivity.this).inflate(R.layout.activity_select_filter, null);
                new BlackCarFilterActivity(AnalysisNOxActivity.this, convertView, lineView.getBottom(), "analysis").showAsDropDown(lineView);
            }
        });
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
        description.setPosition(0, 0);  //自定义描述文字在屏幕上d的位置（单位是像素）。
        mChart.setDescription(description);

        //设置当 chart 为空时显示的描述文字。
        mChart.setNoDataText("No chart data available.");

        //设置 chart 网格背景
        mChart.setDrawGridBackground(false); //如果启用，chart 绘图区后面的背景矩形将绘制。
        mChart.setGridBackgroundColor(Color.parseColor("#0000ff"));

        //设置 chart边框
        mChart.setDrawBorders(false); // 启用/禁用绘制图表边框（chart周围的线，4个坐标轴的位置）
        mChart.setBorderColor(Color.parseColor("#ff00ff"));
        mChart.setBorderWidth(2f);

        //1.2其他设置
//        mChart.setDrawBarShadow(false); // 柱图每列的底层背景
//        mChart.setFitBars(true);//使两侧的柱图完全显示
//        mChart.setExtraBottomOffset(130);//距视图窗口底部的偏移，类似与paddingbottom

        //1.3 修改视窗
        mChart.setVisibleXRange(0, mXValues.size()); //设置可见的x范围，我设置成(0, 5)之后，就只会显示序号0到5的内容(6项)。往左滑才会显示第7,8,9…最后一项
        //mChart.setVisibleXRangeMinimum(1); //设定x轴最小可见区域范围的大小。如果设置为17，则不可能进一步放大视图（在x轴超过17的值）。
        //mChart.setVisibleXRangeMaximum(3); //设定x轴最大可见区域范围的大小。 如果设定为3，则在x轴超过3的值被视为不可见（不滑动 chart 的话）


        //2. 设置 chart 交互
        //启用/ 禁止 手势交互
        mChart.setTouchEnabled(true); //启用/禁用与图表的所有可能的触摸交互。默认true

        mChart.setDragEnabled(true); //启用/禁用拖动（平移）图表。
        mChart.setDragDecelerationFrictionCoef(0.9f);

        mChart.setScaleEnabled(false); //启用/禁用缩放图表上的两个轴。
        mChart.setScaleXEnabled(true); //启用/禁用缩放在x轴上。
        mChart.setScaleYEnabled(false); //启用/禁用缩放在y轴。

        mChart.setPinchZoom(true); //如果设置为true，捏缩放功能。 如果false，x轴和y轴可分别放大。

        mChart.setDoubleTapToZoomEnabled(false); //如果设置为true，捏缩放功能。如果false，x轴和y轴可分别放大。
        mChart.setHighlightPerDragEnabled(true); //设置为true，允许每个图表表面拖过，当它完全缩小突出。 默认值：true
        mChart.setHighlightPerTapEnabled(true); //设置为false，以防止值由敲击姿态被突出显示。 值仍然可以通过拖动或编程方式突出显示。 默认值：true

//        mChart.setHighlightFullBarEnabled(true);

        //3. MarkView
        //点击交叉点弹出Y轴对比数据的关键代码调用，调用的是重写MarkerView的MarkerViews类。
        final NOxMarkerView mv = new NOxMarkerView(this, R.layout.chart_marker_view, "NOx浓度均值：", mChart, mXValues);
        mv.setChartView(mChart);
        mChart.setMarker(mv);  // set the marker to the chart

        //4. 动画 Animations
        mChart.animateX(2000);

        //5. 图例
        Legend legend = mChart.getLegend();
        legend.setEnabled(true);//设置Legend启用或禁用。
        //legend.setTextColor(int color) //设置图例标签的颜色。
        //legend.setTextSize(float size) //设置在DP传说标签的文字大小。
        //legend.setWordWrapEnabled(boolean enabled) //如果启用，Legend 的内容将不会超出图表边界之外，而是创建一个新的行。 请注意，这会降低性能和仅适用于”legend 位于图表下面”的情况。
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);//图例水平居中
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP); //图例在图表上方
        //legend.setOrientation(Legend.LegendOrientation.HORIZONTAL); //图例的方向为水平
        //legend.setDrawInside(false); //绘制在chart的外侧
        //legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT); //图例中的文字方向
        legend.setForm(Legend.LegendForm.CIRCLE); //图例窗体的形状
        //legend.setFormSize(10f); //图例窗体的大小
        legend.setTextSize(12f); //图例文字的大小
        legend.resetCustom();
        //legend.setCustom(new LegendEntry[]{});  //设置图类的颜色集合
    }

    /**
     * 设置横坐标数据
     */
    private void setAxisXBottom() {
        List<String> valuesX = new ArrayList<>();
        for (int i = 0; i < mItems.size(); i++) {
            if (!TextUtils.isEmpty(mItems.get(i).getCjsj())) {
                valuesX.add(mItems.get(i).getCjsj());
            } else {
                valuesX.add("");
            }
        }
        XAxis bottomAxis = mChart.getXAxis();
        //确定 此轴涵盖的值的总范围
        bottomAxis.setAxisMinimum(0f);
        bottomAxis.setAxisMaximum(valuesX.size());

        bottomAxis.setEnabled(true);
        bottomAxis.setTypeface(Typeface.DEFAULT_BOLD); //设置坐标轴标签文字样式
        bottomAxis.setAxisLineWidth(1f); //设置坐标轴的宽度
        bottomAxis.setPosition(XAxis.XAxisPosition.BOTTOM);  // 设置X轴的位置。 TOP、BOTTOM、TOP_INSIDE、BOTTOM_INSIDE、BOTH_SIDED
        bottomAxis.setDrawGridLines(false); //设置为true，则绘制网格线。(必须 xAxis.setEnabled(true)才有效)
        bottomAxis.setCenterAxisLabels(false); //设置即可实现时折线图居中相对于x轴居中。
        bottomAxis.setValueFormatter(new IndexAxisValueFormatter(valuesX)); //自定义数值格式
        bottomAxis.setLabelCount(valuesX.size(), false); //设置页签的个数 ，false则不强制执行，true强制执行，比例可能会不均匀
        bottomAxis.setLabelRotationAngle(90);
        bottomAxis.setAxisLineColor(getResources().getColor(R.color.analysis_label_name)); //设置该轴轴行的颜色。
        //bottomAxis.setAxisLineWidth(1f);//设置该轴轴行的宽度。
        bottomAxis.setAvoidFirstLastClipping(false);
    }

    /**
     * 设置左侧纵坐标数据
     */
    private void setAxisYLeft() {
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setEnabled(true);

        leftAxis.setTypeface(Typeface.DEFAULT_BOLD); //设置坐标轴标签文字样式
        leftAxis.setAxisLineWidth(1f); //设置坐标轴的宽度
        leftAxis.setAxisLineColor(getResources().getColor(R.color.analysis_label_name)); //设置坐标轴的颜色
        /*leftAxis .setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "" + (int) value;//这句是重点!
            }
        });*/
        leftAxis.setValueFormatter(new DefaultAxisValueFormatter(0)); //自定义数值格式
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0);//为坐标轴设置最小值

    }

    /**
     * 设置柱状图绘制数据
     */
    public LineData getBarData() {
        //总量金额
        List<Entry> values = new ArrayList<>();
        for (int i = 0; i < mItems.size(); i++) {
            values.add(new Entry(i, Float.parseFloat(mItems.get(i).getNoxjg())));
        }


        //设置总数的柱状图
        LineDataSet lineDataSet = new LineDataSet(values, "NOx浓度均值");
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER); //设置折线图的显示模式
        lineDataSet.setColor(getResources().getColor(R.color.analysis_label_name));//设置线的颜色  单色
        lineDataSet.setLineWidth(1.5f); //设置线的宽度
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setCircleRadius(4f);//设置每个折线点的大小
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT); ////设置线数据依赖于左侧y轴
        lineDataSet.setDrawFilled(true);//设置数据覆盖的阴影层
        lineDataSet.setFillColor(getResources().getColor(R.color.analysis_line_fill)); //设置数据覆盖的阴影层颜色
        lineDataSet.setDrawValues(true); //绘制线的数据
        lineDataSet.setValueTextColor(this.getResources().getColor(R.color.textColor_333333));//设置数据的文本颜色，如果不绘制线的数据 这句代码也不用设置了
        lineDataSet.setValueTextSize(10f);//如果不绘制线的数据 这句代码也不用设置了
        //lineDataSet.setFormSize(0f);//设置当前这条线的图例的大小
        //lineDataSet.setForm(Legend.LegendForm.CIRCLE);//设置图例显示为线
        //lineDataSet.setValueFormatter();

        LineData lineData = new LineData();
        lineData.addDataSet(lineDataSet);
        lineData.setValueFormatter(new DefaultAxisValueFormatter(0)); ////自定义数值格式
//        lineData.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
//                return StringUtils.double2String(value, 4);
//            }
//        });

        return lineData;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_nox);

        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        //mType = intent.getStringExtra("type");

        RxBus.get().register(this);

        initTitle();

        // 年 yearSpinner
        MaterialSpinner yearSpinner = (MaterialSpinner) findViewById(R.id.spinner_year);
        List<String> years = new ArrayList<>();
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        for (int i = 0; i < 6; i++) {
            years.add(String.valueOf(year - i));
        }
        yearSpinner.setItems(years);
        yearSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                //ToastUtils.longToast("Clicked year：" + item);
                mPresenter.getData(null, getStartDate(item, monthSpinner.getText().toString()), getEndDate(item, monthSpinner.getText().toString()));

            }
        });

        // 月 yearSpinner
        monthSpinner = (MaterialSpinner) findViewById(R.id.spinner_month);
        List<String> months = new ArrayList<>();
        // 获取当前日期
        for (int i = 1; i < 13; i++) {
            months.add(String.valueOf(i));
        }
        monthSpinner.setItems(months);
        //monthSpinner.setSelectedIndex(calendar.get(Calendar.MONTH)-1);
        monthSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                //ToastUtils.longToast("Clicked month：" + item);
                mPresenter.getData(null, getStartDate(yearSpinner.getText().toString(), item), getEndDate(yearSpinner.getText().toString(), item));
            }
        });

        mChart = (LineChart) findViewById(R.id.line_chart);
        //showDataOnChart();

        mPresenter = new NOxPresenter(this);
        mPresenter.getData(null, getStartDate(yearSpinner.getText().toString(), monthSpinner.getText().toString()), getEndDate(yearSpinner.getText().toString(), monthSpinner.getText().toString()));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }


    /**
     * 获取指定年月的起始时间
     */
    public String getStartDate(String year, String month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(year), Integer.parseInt(month) - 1, 1, 0, 0, 0);
        return DateUtils.date2Str(calendar.getTime(), DateUtils.FORMAT);
    }

    /**
     * 获取指定年月的结束时间
     */
    public String getEndDate(String year, String month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(year), Integer.parseInt(month), 1, 23, 59, 59);
        calendar.add(Calendar.DATE, -1);
        return DateUtils.date2Str(calendar.getTime(), DateUtils.FORMAT);
    }


    // 数据分析——筛选对应的事件
    /*@Subscribe
    public void refreshByFilter(RefreshAnalysisByFilter paramRefreshAnalysisByFilter) {
        this.mFilterCategoryModelList = paramRefreshAnalysisByFilter.getFilterCategoryModelList();
        mPresenter.getData(mType);
    }*/

    @Override
    public void setPresenter(NOxContract.Presenter paramT) {

    }

    public void showLoading() {
    }

    public void hideLoading() {
        //((BaseActivity) this).hideLoading();
    }

    public void showFail(String paramString) {
        ToastUtils.shortToast(paramString);
    }

    public void setData(List<NOxModel> paramList) {
        //ToastUtils.shortToast(paramString);
        if (paramList != null ) { //&& paramList.size() > 0
            mItems = paramList;

            for (int i = 0; i < mItems.size(); i++) {
                if (!TextUtils.isEmpty(mItems.get(i).getCjsj())) {
                    mXValues.add(mItems.get(i).getCjsj());
                } else {
                    mXValues.add("未知");
                }
            }

            showDataOnChart();

        }
    }


}

