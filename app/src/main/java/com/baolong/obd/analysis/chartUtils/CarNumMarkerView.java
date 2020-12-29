package com.baolong.obd.analysis.chartUtils;

import android.content.Context;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.analysis.data.entity.CarFlowModel;
import com.baolong.obd.analysis.data.entity.CarNumModel;
import com.baolong.obd.common.utils.LogUtil;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.List;

public class CarNumMarkerView extends MarkerView {//继承MarkerVIew

    private TextView tvContent;//自己定义的xmL
    CombinedChart combinedChart;//图表控件
    List<CarNumModel> mItems; //y轴数据源

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public CarNumMarkerView(Context context, int layoutResource, CombinedChart combinedChart, List<CarNumModel> items) {
        super(context, layoutResource);
        tvContent = (TextView) findViewById(R.id.tvContent);
        this.combinedChart = combinedChart;
        this.mItems = items;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {//重写refreshContent方法（注意，在  //MPAndroidChart早期版本里这里有三个参数，这里有两个，我这是MPAndroidChart 3.0.2版本
        try {
            //下面这里是关键的
            CombinedData combinedData = combinedChart.getCombinedData();//得到已经绘制成型的折线图的数据
            LineDataSet set = (LineDataSet) combinedData.getDataSetByIndex(0);//获取第一条折线图Y轴数据
            BarDataSet set1 = (BarDataSet) combinedData.getDataSetByIndex(1);//获取第二条折线图Y轴数据

            int DataIndex = highlight.getDataIndex();//获取点击的是哪条折线上的交叉点，0就是第一条，以此类推
            LogUtil.i("DataIndex", "" + DataIndex);

            int index;
            if (DataIndex == 0) {
                index = set.getEntryIndex(e);//根据点击的该条折线的点，获取当前Y轴数据对应的index值，
            } else {
                index = set1.getEntryIndex(e);//根据点击的该条折线的点，获取当前Y轴数据对应的index值，
            }
            LogUtil.i("EntryIndex", "" + index);

            //tvContent.setText("时间："+xvalue.get(index)+"\n即时形变："+entry.getY()+"\n累计形变："); // set the entry-value as the display text
            tvContent.setText("类型：" + mItems.get(index).getLx()
                    + "\n数量：" + mItems.get(index).getTotal() + "条"
                    + "\n超标率：" + mItems.get(index).getCbl() + "%"
            );

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());

    }
}


