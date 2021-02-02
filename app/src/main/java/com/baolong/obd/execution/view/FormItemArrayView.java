package com.baolong.obd.execution.view;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baolong.obd.R;
import com.baolong.obd.common.utils.LogUtil;
import com.baolong.obd.execution.data.entity.SpinnerItemMode;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;

import java.util.List;

/**
 * 单行标题控件
 * setitem_title(Strins s)  设置标题
 * setitem_content(String s) 设置内容
 */

public class FormItemArrayView extends LinearLayout implements IFormItem {
    private static final String TAG = FormItemArrayView.class.getSimpleName();

    Context mContext;
    String mKey;
    int position;
    String mTitle;
    String mContent;
    List<SpinnerItemMode> options1Items;

    private TextView title_tv;
    private EditText content_et;
    private TextView danwei_tv;

    private int enableTitleColor; //当content 可编辑时， 对应的 title的颜色
    private boolean content_click_Enable = false;
    private OnItemClickListener mItemClickListener;

    OptionsPickerView optionsPickerView;

    public FormItemArrayView(Context context) {
        this(context, null);
    }

    public FormItemArrayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        View.inflate(context, R.layout.form_item_array_view, this);
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
                optionsPickerView.show();
            }
        });
    }

    private void initTimePickerView() {
        optionsPickerView = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //1.返回的分别是三个级别的选中位置
                SpinnerItemMode item = options1Items.get(options1);
                //2.显示选择的值
                content_et.setText(item.GetValue());
                //3.回调
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(mKey, item.GetID(), position);
                }

            }
        })
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确定按钮文字
                .setSubCalSize(16)//确定和取消文字大小
                .setSubmitColor(Color.parseColor("#0666FF"))//确定按钮文字颜色
                .setCancelColor(Color.parseColor("#4C5165"))//取消按钮文字颜色
                //.setTitleText("请选择" + mTitle + ":")
                .setTitleBgColor(Color.parseColor("#FFFFFF"))//标题背景颜色 Night mode
                .setCyclic(false, true, true)//循环与否
                .setContentTextSize(17)//滚轮文字大小
                .setTextColorCenter(Color.parseColor("#4C5165"))//设置选中项的颜色
                .build();
    }

    /**
     * 设置Key
     *
     * @param key 字段名
     */
    public void setItem_key(String key, int position) {
        if (isNotNull(key)) {
            this.mKey = key;
            this.position = position;
        }
    }

    /**
     * 设置Key
     *
     * @param list 下路选择选项集合
     */
    public void setItem_list(List<SpinnerItemMode> list) {
        if (list != null) {
            this.options1Items = list;
            optionsPickerView.setPicker(options1Items);
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

    public void setItemClickListener(FormItemArrayView.OnItemClickListener listener) {
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
