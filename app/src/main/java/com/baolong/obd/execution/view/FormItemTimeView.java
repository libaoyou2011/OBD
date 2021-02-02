package com.baolong.obd.execution.view;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.common.utils.DateUtils;
import com.baolong.obd.common.utils.LogUtil;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.util.Date;

/**
 * 单行标题控件
 * setitem_title(Strins s)  设置标题
 * setitem_content(String s) 设置内容
 */

public class FormItemTimeView extends LinearLayout implements IFormItem {
    private static final String TAG = FormItemTimeView.class.getSimpleName();

    Context mContext;
    String mKey;
    int mPosition;
    String mTitle;
    String mContent;

    private TextView title_tv;
    private EditText content_et;

    private int enableTitleColor; //当content 可编辑时， 对应的 title的颜色
    private boolean content_click_Enable = false;
    private OnItemClickListener mItemClickListener;

    TimePickerView timePickerView;

    public FormItemTimeView(Context context) {
        super(context);
        initView(context);
    }

    public FormItemTimeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    private void initView(Context context) {
        this.mContext = context;
        View.inflate(context, R.layout.form_item_time_view, this);
        title_tv = ((TextView) findViewById(R.id.tv_item));
        content_et = ((EditText) findViewById(R.id.et_item));

        enableTitleColor = getResources().getColor(R.color.form_item_title_default);

        initTimePickerView();

        //必须可获取焦点，否则hideInputKeyboard隐藏键盘 不起作用
        content_et.setFocusable(false);
        content_et.setKeyListener(null);
        // 手机动态登录中输完手机号码的监听事件
        content_et.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //隐藏键盘
                hideInputKeyboard(view);

                //显示时间选择器
                timePickerView.show();
            }
        });
    }

    private void initTimePickerView() {
        timePickerView = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                //特别注意月份  月份从0-11
                String temp = DateUtils.date2Str(date, DateUtils.FORMAT_China);
                //显示选择的值
                content_et.setText(temp);
                //回调
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(mKey, temp, mPosition);
                }

            }
        }).isDialog(false)//1.Dialog 模式下，在底部弹出  2.//默认设置false ，内部实现将DecorView 作为它的父控件。
                .setType(new boolean[]{true, true, true, false, false, false})
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
                .setSubCalSize(16)//确定和取消文字大小
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .setTitleText(mTitle)
                //.setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setContentTextSize(14)//滚轮文字大小
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();

    }

    /**
     * 设置Key
     *
     * @param key
     */
    public void setItem_key(String key, int position) {
        if (isNotNull(key)) {
            this.mKey = key;
            this.mPosition = position;
        }
    }

    /**
     * 设置标题
     *
     * @param title 标题内容
     */
    public void setItem_title(String title) {
        if (isNotNull(title, title_tv)) {
            this.mTitle = title;
            title_tv.setText(title);
        }
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setItem_content(String content) {
        if (isNotNull(content, content_et)) {
            this.mContent = content;
            content_et.setText(content);
        }
    }

    /**
     * 字体大小
     *
     * @param size 内容
     */
    public void setItem_text_size(float size) {
        title_tv.setTextSize(size);
        content_et.setTextSize(size);
    }

    /**
     * 设置标题（在内容可编辑状态下）的颜色
     *
     * @param color 颜色
     */
    public void setEnableTitleColor(int color) {
        enableTitleColor = color;
    }

    /**
     * 设置内容的EditView背景，设为默认
     */
    public void setContentBackground(boolean isEnable) {
        if (isEnable) {
            content_et.setBackgroundResource(android.R.drawable.edit_text);
        } else {
            content_et.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }
    }

    @Override
    public void setItemEnable(boolean enable) {
        this.content_click_Enable = enable;
        if (content_et != null) {
            content_et.setEnabled(content_click_Enable);
        }
        if (!enable) {
            title_tv.setTextColor(getResources().getColor(R.color.form_item_title_default));
        } else {
            title_tv.setTextColor(enableTitleColor);
        }
        setContentBackground(enable);
    }

    public void setItemClickListener(FormItemTimeView.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    /**
     * 判断控件和内容是否为空
     *
     * @param s 内容
     * @return false==》空；true==》不为空
     */
    private boolean isNotNull(String s) {
        return (s != null && !"".equals(s) && !"null".equals(s));
    }

    /**
     * 判断控件和内容是否为空
     *
     * @param s 内容
     * @param t 控件
     * @return false==》空；true==》不为空
     */
    private boolean isNotNull(String s, TextView t) {
        return (s != null && !"".equals(s) && !"null".equals(s) && t != null);
    }

    /**
     * 隐藏键盘 * 弹窗弹出的时候把键盘隐藏掉
     */
    protected void hideInputKeyboard(View view) {
        LogUtil.i(TAG, "隐藏键盘 hideInputKeyboard ");
        if (mContext != null) {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getRootView().getWindowToken(), 0);
            }
        }
    }

    /**
     * 弹起键盘
     */
    protected void showInputKeyboard(View view) {
        if (mContext != null) {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                view.requestFocus();
                imm.showSoftInput(view, 0);
            }
        }
    }


    public static abstract interface OnItemClickListener {
        public abstract void onItemClick(String key, String value, int position);
    }

}
