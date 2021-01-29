package com.baolong.obd.execution.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baolong.obd.R;

/**
 * 单行标题控件
 * setitem_title(Strins s)  设置标题
 * setitem_content(String s) 设置内容
 */

public class FormItemEditView extends LinearLayout implements IFormItem {
    Context context;

    String key;
    int position;
    String title;
    String content;
    String danwei;

    private TextView title_tv;
    private EditText content_et;

    private int enableTitleColor; //当content 可编辑时， 对应的 title的颜色
    private boolean content_click_Enable = false;
    private OnItemClickListener mItemClickListener;

    public FormItemEditView(Context context) {
        super(context);
        initView(context);
    }

    public FormItemEditView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    private void initView(Context context) {
        View.inflate(context, R.layout.form_item_edit_view, this);
        title_tv = ((TextView) findViewById(R.id.tv_item));
        content_et = ((EditText) findViewById(R.id.et_item));

        enableTitleColor = getResources().getColor(R.color.form_item_title_default);

        // 手机动态登录中输完手机号码的监听事件
        content_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //s:变化后的所有字符, 回调
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(key, s.toString(), position);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //s:变化前的所有字符； start:字符开始的位置； count:变化前的总字节数；after:变化后的字节数
            }

            @Override
            public void afterTextChanged(Editable s) {
                //S：变化后的所有字符；start：字符起始的位置；before: 变化之前的总字节数；count:变化后的字节数
            }
        });
    }

    /**
     * 设置Key
     *
     * @param key
     */
    public void setItem_key(String key, int positon) {
        if (isNotNull(key)) {
            this.key = key;
            this.position = positon;
        }
    }

    /**
     * 设置标题
     *
     * @param title 标题内容
     */
    public void setItem_title(String title) {
        if (isNotNull(title, title_tv)) {
            this.title = title;
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
            content_et.setBackgroundResource(android.R.drawable.editbox_background_normal);
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

    public void setItemClickListener(OnItemClickListener listener) {
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


    public static abstract interface OnItemClickListener {
        public abstract void onItemClick(String key, String value, int position);
    }
}
