package com.baolong.obd.monitor.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Book2 implements Parcelable {
    /*构造函数*/
    //1.安装插件：File -> Settings -> Pugins -> Browse Repositories，输入GsonFormat
    //2.构造函数：选中类名，右键选择Generate

    /*序列化*/
    //1.安装插件：File -> Settings -> Pugins -> Browse Repositories，输入android parcelable code generator
    //2.选中类名，Alt+Insert选择Parcelable自定生成
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bookName);
        dest.writeString(this.author);
        dest.writeInt(this.publishDate);
    }

    public Book2() {
    }

    protected Book2(Parcel in) {
        this.bookName = in.readString();
        this.author = in.readString();
        this.publishDate = in.readInt();
    }

    public static final Parcelable.Creator<Book2> CREATOR = new Parcelable.Creator<Book2>() {
        @Override
        public Book2 createFromParcel(Parcel source) {
            return new Book2(source);
        }

        @Override
        public Book2[] newArray(int size) {
            return new Book2[size];
        }
    };

}
