package com.baolong.obd.monitor.data.entity;

import java.io.Serializable;

public class Book implements Serializable {
    /*构造函数*/
    //1.安装插件：File -> Settings -> Pugins -> Browse Repositories，输入GsonFormat
    //2.构造函数：选中类名，右键选择Generate

    /*序列化*/
    //1.安装插件：File -> Settings... -> Editor -> Inspections -> Java -> Serialization issues -> Serializable class without 'serialVersionUID'（选中）
    //2.实现接口：implements Serializable
    //3.选中类名，Alt+Enter选择 add 'serialVersionUID' field
    private static final long serialVersionUID = -1540610997694124387L;

    private String bookName;
    private String author;
    private int publishDate;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(int publishDate) {
        this.publishDate = publishDate;
    }
}
