package com.baolong.obd.common.model.notice;

public class ImNoticeContentModel {
    String content;
    String title;

    public ImNoticeContentModel() {
    }

    public ImNoticeContentModel(String paramString1, String paramString2) {
        this.title = paramString1;
        this.content = paramString2;
    }

    public String getContent() {
        return this.content;
    }

    public String getTitle() {
        return this.title;
    }

    public void setContent(String paramString) {
        this.content = paramString;
    }

    public void setTitle(String paramString) {
        this.title = paramString;
    }
}
