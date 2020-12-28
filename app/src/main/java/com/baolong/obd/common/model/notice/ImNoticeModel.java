package com.baolong.obd.common.model.notice;

import java.util.List;

public class ImNoticeModel {
    public static final String DISPLAY_POPUP = "POPUP";
    public static final String DISPLAY_ROUND = "ROUND";
    String category;
    String chatContentType;
    ImNoticeContentModel content;
    String contentType;
    String displayType;
    String isGroup;
    boolean isLeader;
    boolean isObtain;
    List<String> list;
    String noticeId;
    String orderId;
    String organizeId;
    String receiverId;
    String senderId;
    String senderName;
    String taskId;
    String time;

    public String getCategory() {
        return this.category;
    }

    public String getChatContentType() {
        return this.chatContentType;
    }

    public ImNoticeContentModel getContent() {
        return this.content;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getDisplayType() {
        return this.displayType;
    }

    public String getIsGroup() {
        return this.isGroup;
    }

    public List<String> getList() {
        return this.list;
    }

    public String getNoticeId() {
        return this.noticeId;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getOrganizeId() {
        return this.organizeId;
    }

    public String getReceiverId() {
        return this.receiverId;
    }

    public String getSenderId() {
        return this.senderId;
    }

    public String getSenderName() {
        return this.senderName;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public String getTime() {
        return this.time;
    }

    public boolean isLeader() {
        return this.isLeader;
    }

    public boolean isObtain() {
        return this.isObtain;
    }

    public void setCategory(String paramString) {
        this.category = paramString;
    }

    public void setChatContentType(String paramString) {
        this.chatContentType = paramString;
    }

    public void setContent(ImNoticeContentModel paramImNoticeContentModel) {
        this.content = paramImNoticeContentModel;
    }

    public void setContentType(String paramString) {
        this.contentType = paramString;
    }

    public void setDisplayType(String paramString) {
        this.displayType = paramString;
    }

    public void setIsGroup(String paramString) {
        this.isGroup = paramString;
    }

    public void setLeader(boolean paramBoolean) {
        this.isLeader = paramBoolean;
    }

    public void setList(List<String> paramList) {
        this.list = paramList;
    }

    public void setNoticeId(String paramString) {
        this.noticeId = paramString;
    }

    public void setObtain(boolean paramBoolean) {
        this.isObtain = paramBoolean;
    }

    public void setOrderId(String paramString) {
        this.orderId = paramString;
    }

    public void setOrganizeId(String paramString) {
        this.organizeId = paramString;
    }

    public void setReceiverId(String paramString) {
        this.receiverId = paramString;
    }

    public void setSenderId(String paramString) {
        this.senderId = paramString;
    }

    public void setSenderName(String paramString) {
        this.senderName = paramString;
    }

    public void setTaskId(String paramString) {
        this.taskId = paramString;
    }

    public void setTime(String paramString) {
        this.time = paramString;
    }
}
