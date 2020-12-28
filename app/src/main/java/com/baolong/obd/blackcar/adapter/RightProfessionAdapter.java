package com.baolong.obd.blackcar.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.blackcar.data.entity.FilterCategoryModel;
import com.baolong.obd.common.utils.DateUtils;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.common.view.listview.SectionedBaseAdapter;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.util.Date;
import java.util.List;

public class RightProfessionAdapter extends SectionedBaseAdapter {
    private static final String TAG = RightProfessionAdapter.class.getSimpleName();
    private Context mContext;
    private List<FilterCategoryModel> dataList;
    private SubItemChange subItemChange;

    public RightProfessionAdapter(Context paramContext, List<FilterCategoryModel> paramList) {
        this.mContext = paramContext;
        this.dataList = paramList;
    }

    /**
     * 1.设置数据源
     */
    public List<FilterCategoryModel> getDataList() {
        return this.dataList;
    }

    public void setDataList(List<FilterCategoryModel> paramList) {
        this.dataList = paramList;
        notifyDataSetChanged();
    }

    /**
     * 2.增加点击监听
     */
    public SubItemChange getSubItemChange() {
        return this.subItemChange;
    }

    public void setSubItemChange(SubItemChange paramSubItemChange) {
        this.subItemChange = paramSubItemChange;
    }

    /*
     * 3.重写adapter之中的 6个方法
     */
    public Object getItem(int section, int position) {    //section: header的位置数, position:普通item的位置数
        return ((FilterCategoryModel) this.dataList.get(section)).getSubList().get(position);
    }


    public long getItemId(int section, int position) {   //获取item的id  第几个section的 position
        return position;
    }

    // header的总数量
    public int getSectionCount() {
        return this.dataList.size();
    }

    // 普通item的总数量，每个section中item数量
    public int getCountForSection(int section) {
        //本示例只有一个item，此item中包含EditText 和 GridView
        return 1;
        //return this.dataList.get(section).getSubList().size(); 普通案例是这个
    }

    // 普通item, 加载view的方法 类似adapter中getview() 方法
    public View getItemView(int section, int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = (View) LayoutInflater.from(this.mContext).inflate(R.layout.item_right_grid_profession, null);
            viewHolder = new ViewHolder();
            viewHolder.gridView = (GridView) convertView.findViewById(R.id.gv_category);
            viewHolder.valueEditText = (EditText) convertView.findViewById(R.id.et_filter_value);
            viewHolder.timeLinearLayout = (LinearLayout) convertView.findViewById(R.id.ll_filter_Time);
            viewHolder.beginEditText = (TextView) convertView.findViewById(R.id.et_filter_value_begin);
            viewHolder.endEditText = (TextView) convertView.findViewById(R.id.et_filter_value_end);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.valueEditText.setTag(position);
        }

        final FilterCategoryModel filterItem = (FilterCategoryModel) this.dataList.get(section);

        // EditText 数值
        if (!(filterItem).isCanInput()) {
            viewHolder.valueEditText.setVisibility(View.GONE);
        } else {
            viewHolder.valueEditText.setVisibility(View.VISIBLE);
            viewHolder.valueEditText.setText(((FilterCategoryModel) this.dataList.get(section)).getInputValue());
        }
        viewHolder.valueEditText.setTag(section);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //这个方法被调用，说明在s字符串中，从start位置开始的count个字符即将被长度为after的新文本所取代。
                //在这个方法里面改变s，会报错。
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //这个方法被调用，说明在s字符串中，从start位置开始的count个字符刚刚取代了长度为before的旧文本。
                //LogUtil.i(TAG, "section:" + section);
                //LogUtil.i(TAG, "position:" + position);
                //LogUtil.i(TAG, "name:" + filterItem.getCategoryName());
                //LogUtil.i(TAG, "value:" + filterItem.getInputValue());

                if (RightProfessionAdapter.this.subItemChange != null) {
                    RightProfessionAdapter.this.subItemChange.onItemChange();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                //这个方法被调用，那么说明s字符串的某个地方已经被改变。
                int sectionTag = (Integer) viewHolder.valueEditText.getTag();
                //LogUtil.i(TAG, "afterTextChanged" + sectionTag);
                ((FilterCategoryModel) dataList.get(sectionTag)).setInputValue(editable.toString());
            }
        };
        viewHolder.valueEditText.addTextChangedListener(textWatcher);

        //TextView 开始日期  结束日期
//        Calendar startDateEnd = Calendar.getInstance(); //开始时间 最大值
//        Calendar endDateStart = Calendar.getInstance();//结束时间 最小值
        if (!filterItem.isCanInputTime()) {
            viewHolder.timeLinearLayout.setVisibility(View.GONE);
        } else {
            viewHolder.timeLinearLayout.setVisibility(View.VISIBLE);
            viewHolder.beginEditText.setText(((FilterCategoryModel) this.dataList.get(section)).getBeginTime());
            viewHolder.endEditText.setText(((FilterCategoryModel) this.dataList.get(section)).getEndTime());
        }
        viewHolder.beginEditText.setTag(section);
        viewHolder.endEditText.setTag(section);
        viewHolder.beginEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏键盘
                //hideInputKeyboard(v);
                //时间选择器
                TimePickerView timePickerView = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        // 改变标识
                        if (RightProfessionAdapter.this.subItemChange != null) {
                            RightProfessionAdapter.this.subItemChange.onItemChange();
                        }
                        // 文本框中显示日期
                        String beginDateStr = DateUtils.date2Str(date, DateUtils.FORMAT_Default);
                        viewHolder.beginEditText.setText(beginDateStr);
                        // 日期数据保存
                        int sectionTag = (Integer) viewHolder.beginEditText.getTag();
                        LogUtil.i(TAG, "after choose start date ：sectionTag" + sectionTag);
                        ((FilterCategoryModel) dataList.get(sectionTag)).setBeginTime(beginDateStr);
                        // 结束日期 大于 开始日期
//                        LogUtil.i(TAG,  date.getYear() + 1900+"y");
//                        LogUtil.i(TAG,  date.getMonth() +"m");
//                        LogUtil.i(TAG,  date.getDate() +"d");
//                        endDateStart.set(Calendar.YEAR, date.getYear() + 1900); //
//                        endDateStart.set(Calendar.MONTH, date.getMonth()); //特别注意月份  月份从0-11
//                        endDateStart.set(Calendar.DAY_OF_MONTH, date.getDate());
                    }
                }).isDialog(true)
                        .setType(new boolean[]{true, true, true, false, false, false})
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确认")//确认按钮文字
                        .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                        .setTitleText("开始时间")
                        //.setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                        .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .build();

                timePickerView.show();
            }
        });
        viewHolder.endEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏键盘
                //hideInputKeyboard(v);
                //时间选择器
                TimePickerView timePickerView = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        // 改变标识
                        if (RightProfessionAdapter.this.subItemChange != null) {
                            RightProfessionAdapter.this.subItemChange.onItemChange();
                        }
                        // 文本框中显示日期
                        String endDateStr = DateUtils.date2Str(date, DateUtils.FORMAT_Default);
                        viewHolder.endEditText.setText(endDateStr);
                        // 日期数据保存
                        int sectionTag = (Integer) viewHolder.endEditText.getTag();
                        LogUtil.i(TAG, "after choose end date ：sectionTag" + sectionTag);
                        ((FilterCategoryModel) dataList.get(sectionTag)).setEndTime(endDateStr);

                    }
                }).isDialog(true)
                        .setType(new boolean[]{true, true, true, false, false, false})
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确认")//确认按钮文字
                        .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                        .setTitleText("结束时间")
                        //.setRangDate(endDateStart, null)
                        //.setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                        .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .build();

                timePickerView.show();
            }
        });


        // GridView
        viewHolder.gridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return ((FilterCategoryModel) RightProfessionAdapter.this.dataList.get(section)).getSubList().size();
            }

            @Override
            public Object getItem(int position) {
                return ((FilterCategoryModel) RightProfessionAdapter.this.dataList.get(section)).getSubList().get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup viewGroup) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(RightProfessionAdapter.this.mContext).inflate(R.layout.item_right_profession, null);
                }
                TextView textView = (TextView) convertView.findViewById(R.id.tv_sub_category);
                textView.setText(((FilterCategoryModel.FilterSubCategoryModel) ((FilterCategoryModel) RightProfessionAdapter.this.dataList.get(section)).getSubList().get(position)).getSubCategoryName());
                textView.setSelected(((FilterCategoryModel.FilterSubCategoryModel) ((FilterCategoryModel) RightProfessionAdapter.this.dataList.get(section)).getSubList().get(position)).isSelected());
                //原来
//                textView.setOnClickListener(new -..Lambda.RightProfessionAdapter
//                .2.AegrxbCWBNdERVl66mSExW - QdFE(this, paramInt1, position));

                //本地修改后 lambda
                textView.setOnClickListener(v -> {
                    for (int i = 0; i < RightProfessionAdapter.this.dataList.get(section).getSubList().size(); i++) {
                        final FilterCategoryModel.FilterSubCategoryModel filterSubCategoryModel = RightProfessionAdapter.this.dataList.get(section).getSubList().get(i);
                        if (i == position) {
                            filterSubCategoryModel.setSelected(!filterSubCategoryModel.isSelected());
                        } else {
                            filterSubCategoryModel.setSelected(false);
                        }
                    }
                    if (RightProfessionAdapter.this.subItemChange != null) {
                        RightProfessionAdapter.this.subItemChange.onItemChange();
                    }
                    //RightProfessionAdapter.this.notifyDataSetChanged();
                    ((BaseAdapter)viewHolder.gridView.getAdapter()).notifyDataSetChanged();
                });

                //常规
//                textView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (textView.isSelected()) {
//                            // 如果点击前该项被选中，先设置成未被选中，该项数值状态改为false
//                            textView.setSelected(false);
//                            ((FilterCategoryModel.FilterSubCategoryModel) ((FilterCategoryModel) RightProfessionAdapter.this.dataList.get(section)).getSubList().get(position)).setSelected(false);
//                        } else {
//                            // 如果点击前该项未被选中，先设置成被选中，该项数值状态改为true，在其他项改成未被选中
//                            textView.setSelected(true);
//                            List<FilterCategoryModel.FilterSubCategoryModel> subList = (((FilterCategoryModel) RightProfessionAdapter.this.dataList.get(section)).getSubList());
//                            for (int i = 0; i < subList.size(); i++) {
//                                if (i != position) {
//                                    ((FilterCategoryModel.FilterSubCategoryModel) ((FilterCategoryModel) RightProfessionAdapter.this.dataList.get(section)).getSubList().get(position)).setSelected(false);
//                                } else {
//                                    ((FilterCategoryModel.FilterSubCategoryModel) ((FilterCategoryModel) RightProfessionAdapter.this.dataList.get(section)).getSubList().get(position)).setSelected(true);
//                                }
//                            }
//                        }
//                        if (RightProfessionAdapter.this.subItemChange != null) {
//                            RightProfessionAdapter.this.subItemChange.onItemChange();
//                        }
//                        ((BaseAdapter) gridView.getAdapter()).notifyDataSetChanged();
//                    }
//                });
                return convertView;
            }
        });

        return convertView;
    }

    //这里主要是右边ListView头部分类信息展示的实现
    public View getSectionHeaderView(int paramInt, View convertView, ViewGroup parent) {
        LinearLayout layout = null;
        if (convertView == null) {
            LayoutInflater inflator = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflator.inflate(R.layout.item_pinned_profession_header, null);
        } else {
            layout = (LinearLayout) convertView;
        }
        layout.setClickable(false);
        ((TextView) layout.findViewById(R.id.tv_title_category)).setText(((FilterCategoryModel) this.dataList.get(paramInt)).getCategoryName());
        return layout;
    }

    public static abstract interface SubItemChange {
        public abstract void onItemChange();
    }

    public static class ViewHolder {
        public GridView gridView;
        public EditText valueEditText;
        public LinearLayout timeLinearLayout;
        public TextView beginEditText;
        public TextView endEditText;

        public ViewHolder() {

        }
    }

    /**
     * 隐藏键盘 * 弹窗弹出的时候把键盘隐藏掉
     */
    protected void hideInputKeyboard(View v) {
        LogUtil.i(TAG, "隐藏键盘 hideInputKeyboard ");

        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            LogUtil.i(TAG, "隐藏键盘");
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    /**
     * 弹起键盘
     */
    protected void showInputKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(v, 0);
        }
    }
}
