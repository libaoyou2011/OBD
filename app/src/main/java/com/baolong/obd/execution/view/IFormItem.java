package com.baolong.obd.execution.view;

public interface IFormItem {

    //设置Key
    public void setItem_key(String key, int positon);

    //设置标题
    public void setItem_title(String title);

    //设置内容
    public void setItem_content(String content);

    //设置内容 是否可点击
    public void setItemEnable(boolean enable);

}
