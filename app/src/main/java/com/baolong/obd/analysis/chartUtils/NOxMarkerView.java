package com.baolong.obd.analysis.chartUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.baolong.obd.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

@SuppressLint("ViewConstructor")
public class NOxMarkerView extends MarkerView {//继承MarkerVIew

    private TextView contentTv;//自己定义的xmL
    String countName;

    LineChart lineChart;//图表控件
    ArrayList<String> xValue;//X轴数据源

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public NOxMarkerView(Context context, int layoutResource, String countName, LineChart lineChart, ArrayList<String> xValue) {
        super(context, layoutResource);
        this.contentTv = (TextView) findViewById(R.id.tvContent);
        this.countName = countName;
        this.lineChart = lineChart;
        this.xValue = xValue;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void refreshContent(Entry entry1, Highlight highlight) {//重写refreshContent方法（注意，在  //MPAndroidChart早期版本里这里有三个参数，这里有两个，我这是MPAndroidChart 3.0.2版本
        //下面这里是关键的
        LineData lineData = lineChart.getLineData();//得到已经绘制成型的折线图的数据
        LineDataSet set = (LineDataSet) lineData.getDataSetByIndex(0);//获取第一条折线图Y轴数据
        //LineDataSet set1=(LineDataSet)lineData.getDataSetByIndex(1);//获取第二条折线图Y轴数据

        int DataSetIndex = highlight.getDataSetIndex();//获取点击的是哪条折线上的交叉点，0就是第一条，以此类推
        int index;
        index = set.getEntryIndex(entry1);//根据点击的该条折线的点，获取当前Y轴数据对应的index值，

        //根据index值，分别获取当前X轴上对应的两条折线的Y轴的值
        Entry entry = set.getEntryForIndex(index);
        Log.i("x,y轴", "/" + index + "/" + DataSetIndex);

        if (xValue.size() > 0) {

            //contentTv.setText("时间："+xValue.get(index)+"\n即时形变："+entry.getY()+"\n累计形变："); // set the entry-value as the display text

            contentTv.setText("" + xValue.get(index) + "\n" + countName + (int)entry.getY()); // set the entry-value as the display text
        }

        super.refreshContent(entry1, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());

    }
}


