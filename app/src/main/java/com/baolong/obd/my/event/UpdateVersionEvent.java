package com.baolong.obd.my.event;

import com.baolong.obd.common.update.model.CheckVersionResponseModel;

/**
 * 首页检测到新版本  通知 【我的】当前版本提示有新版本
 */
public class UpdateVersionEvent {
    private CheckVersionResponseModel model;

    public UpdateVersionEvent(CheckVersionResponseModel paramString) {
        this.model = paramString;
    }

    public CheckVersionResponseModel getVersionInfo() {
        return this.model;
    }
}
