package com.baolong.obd.blackcar.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;

import java.util.List;

public class LeftProfessionAdapter extends BaseAdapter {
    private Context mContext;
    private List<FilterCategoryModel> dataList;

    public LeftProfessionAdapter(Context paramContext, List<FilterCategoryModel> paramList) {
        this.mContext = paramContext;
        this.dataList = paramList;
    }

    public List<FilterCategoryModel> getDataList() {
        return this.dataList;
    }

    public void setDataList(List<FilterCategoryModel> paramList) {
        this.dataList = paramList;
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.dataList.size();
    }

    public Object getItem(int position) {
        return this.dataList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //使用ViewHolder类来实现显示数据视图的缓存，避免多次调用findViewById来寻找控件，以达到优化程序的目的
        ViewHolder viewHolder;

        //如果view未被实例化过，缓存池中没有对应的缓存
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(this.mContext).inflate(R.layout.item_left_profession, null);
            viewHolder.mNameTextView =  (TextView)convertView.findViewById(R.id.tv_category);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.updateView(position);
        return convertView;
    }

    private class ViewHolder {
        private TextView mNameTextView;

        private ViewHolder() {
        }

        void updateView(int position) {
            this.mNameTextView.setText(((FilterCategoryModel) LeftProfessionAdapter.this.dataList.get(position)).getCategoryName());

            Resources localResources = LeftProfessionAdapter.this.mContext.getResources();
            // 选中时背景为白色，字体为蓝色；非选中时背景为灰色，字体为黑色
            if (((FilterCategoryModel) LeftProfessionAdapter.this.dataList.get(position)).isSelectedCurrentCategory()) {
                this.mNameTextView.setBackgroundColor(Color.rgb(255, 255, 255));
                this.mNameTextView.setTextColor(localResources.getColor(R.color.textColor_4A90E2));
            } else {
                this.mNameTextView.setBackgroundColor(0);
                this.mNameTextView.setTextColor(localResources.getColor(R.color.textColor_4A4A4A));
            }
        }
    }
}
